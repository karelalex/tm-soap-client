package ru.karelin.tmsoapclient.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmsoapclient.api.util.ServiceLocator;
import ru.karelin.tmwebspring.soap.LoginEndpoint;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;


@Component
public class UserAuthorizationCommand extends AbstractCommand {



    @Autowired
    private ServiceLocator locator;

    @Autowired
    private LoginEndpoint loginEndpoint;

    @Autowired

    private static final boolean SECURED = false;

    public UserAuthorizationCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "in";
    }

    @Override
    public String getDescription() {
        return "starts user authorization dialog";
    }

    @Override
    public void execute(final String... params)  {
       System.out.println("Enter login");
        @NotNull final String login = ts.readLn();
        System.out.println("Enter password");
        final char[] pass = ts.readPass();
        @Nullable final String userId  = loginEndpoint.singIn(login, new String(pass));
        if (userId == null || userId.isEmpty()) {
            System.out.println("Wrong login or pass");
        } else {
            locator.setCurrentUserId(userId);
            final Map<String, List<String>> headers  = (Map<String, List<String>>) ((BindingProvider) loginEndpoint).getResponseContext().get(MessageContext.HTTP_RESPONSE_HEADERS);
            final List<String> cookies = headers.get("Set-Cookie");
            locator.setCookies(cookies);
            System.out.println("Welcome in system");
        }

    }

}
