package ru.karelin.tmsoapclient.command;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmsoapclient.api.util.ServiceLocator;

import java.util.Collections;


@Component
public class UserLogoutCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator locator;

   /* @Autowired
    private SessionEndpoint sessionEndpoint;*/

    private static final boolean SECURED = false;

    public UserLogoutCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "out";
    }

    @Override
    public String getDescription() {
        return "finishes user session";
    }

    @Override
    public void execute(final String... params)  {
        locator.setCookies(Collections.emptyList());
        System.out.println("You have successfully logged out");
    }
}
