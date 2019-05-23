package ru.karelin.tmsoapclient;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.karelin.tmsoapclient.command.*;
import ru.karelin.tmsoapclient.config.MainConfig;
import ru.karelin.tmsoapclient.util.Bootstrap;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Bootstrap bootstrap = context.getBean(Bootstrap.class);
        Class[] commandClasses = {
                ProjectCreateCommand.class,
                ProjectEditCommand.class,
                ProjectShowCommand.class,
                ProjectListShowCommand.class,
                ProjectRemoveCommand.class,

                TaskCreateCommand.class,
                TaskEditCommand.class,
                TaskShowCommand.class,
                TaskListShowCommand.class,
                TaskRemoveCommand.class,

               // UserRegisterCommand.class,
                UserAuthorizationCommand.class,
               // UserShowCurrentCommand.class,
                UserLogoutCommand.class,
                //UserPasswordChangeCommand.class,
                //UserProfileEditCommand.class,

                HelpShowCommand.class,
                InfoShowCommand.class,
                //ServerInfoShowCommand.class
        };
        bootstrap.init(commandClasses);
    }
}
