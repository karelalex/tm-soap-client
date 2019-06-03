package ru.karelin.tmsoapclient.command;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmsoapclient.api.util.ServiceLocator;
import ru.karelin.tmwebspring.soap.LoginEndpoint;

import java.util.Collections;


@Component
public class UserLogoutCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator locator;

    @Autowired
    LoginEndpoint loginEndpoint;

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
        if(loginEndpoint.singOut()) {
            locator.setCookies(Collections.emptyList());
            locator.setCurrentUserId(null);
            System.out.println("You have successfully logged out");
        }
        else {
            System.out.println("Something went wrong. Do you want force logout? (y/n");
            if(ts.readLn().equals("y")){
                locator.setCookies(Collections.emptyList());
                locator.setCurrentUserId(null);
                System.out.println("Force logout made");
            }
        }
    }
}
