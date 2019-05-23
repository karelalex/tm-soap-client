package ru.karelin.tmsoapclient.util;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ru.karelin.tmsoapclient.api.util.ServiceLocator;
import ru.karelin.tmsoapclient.api.util.TerminalService;
import ru.karelin.tmsoapclient.command.AbstractCommand;
import ru.karelin.tmsoapclient.exception.CommandRegisteredException;
import ru.karelin.tmwebspring.soap.LoginEndpoint;
import ru.karelin.tmwebspring.soap.ProjectEndpoint;
import ru.karelin.tmwebspring.soap.TaskEndpoint;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Bootstrap implements ServiceLocator, ApplicationContextAware {

    @Autowired
    private TerminalService terminalService;

    @Autowired
    LoginEndpoint loginEndpoint;

    @Autowired
    ProjectEndpoint projectEndpoint;

    @Autowired
    TaskEndpoint taskEndpoint;

    private List endpoints = new ArrayList();

    private ApplicationContext applicationContext;

    private static final String QUIT = "exit";

    private String currentUserId;

    private List<String> cookies;

    @Override
    public List<String> getCookies() {
        return cookies;
    }

    @Override
    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
        for (Object endpoint : endpoints) {
            Map<String, List<String>> headers = (Map) ((BindingProvider) endpoint).getRequestContext().get(MessageContext.HTTP_REQUEST_HEADERS);
            if (headers == null) {
                headers = new HashMap<String, List<String>>();
                ((BindingProvider) endpoint).getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, headers);
            }
            headers.put("Cookie", cookies);
        }
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    @NotNull
    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();
    @NotNull
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @NotNull
    @Override
    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    @NotNull
    @Override
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void init(Class[] commandClasses) {

        //command registration block
        for (Class commandClass : commandClasses) {
            try {
                registerCommand(commandClass);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        // endpoints reg
        endpoints.add(loginEndpoint);
        endpoints.add(projectEndpoint);
        endpoints.add(taskEndpoint);

        // main loop

        String command;
        String[] commandParts, params;
        while (true) {
            System.out.print(">");
            command = terminalService.readLn();
            if (command.equals(QUIT)) break;
            commandParts = command.split(" ");
            command = commandParts[0];
            params = Arrays.copyOfRange(commandParts, 1, commandParts.length);
            AbstractCommand abstractCommand = commands.get(command);
            if (abstractCommand == null) {
                System.out.println("Wrong command name");
                continue;
            }
            if (!abstractCommand.isSecured() || (currentUserId != null && !currentUserId.isEmpty()))
                try {
                    abstractCommand.execute(params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            else System.out.println("Login first.");
        }

        // end of main loop


    }

    private void registerCommand(@NotNull final Class commandClass) throws IllegalAccessException, InstantiationException {
        if (AbstractCommand.class.isAssignableFrom(commandClass)) {
            AbstractCommand command = (AbstractCommand) applicationContext.getBean(commandClass);
            final String commandName = command.getName();
            if (commands.containsKey(commandName))
                throw new CommandRegisteredException("Command with name " + commandName + " is already registered");
            //command.setLocator(this);
            commands.put(commandName, command);
        } else {
            System.out.println("команда не клёвая");
        }
    }


}
