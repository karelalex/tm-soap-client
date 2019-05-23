package ru.karelin.tmsoapclient.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmsoapclient.api.util.ServiceLocator;


@Component
public class UserShowCurrentCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator locator;

    /*@Autowired
    private UserEndpoint userEndpoint;*/

    private static final boolean SECURED = true;

    public UserShowCurrentCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "su";
    }

    @Override
    public String getDescription() {
        return "shows current user description";
    }

    @Override
    public void execute(final String... params) {
       /* @NotNull final User currentUser = userEndpoint.getCurrentUser(locator.getCurrentSession());
        System.out.println("Login: " + currentUser.getLogin());
        System.out.println("Name: " + currentUser.getUserName());
        System.out.println("Role: " + currentUser.getRole());*/
    }
}
