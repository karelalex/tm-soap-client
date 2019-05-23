package ru.karelin.tmsoapclient.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmsoapclient.api.util.ServiceLocator;

@Component
public class UserPasswordChangeCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator locator;

   /* @Autowired
    private UserEndpoint userEndpoint;*/

    private static final boolean SECURED = true;

    public UserPasswordChangeCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "pc";
    }

    @Override
    public String getDescription() {
        return "starts password change dialog";
    }

    @Override
    public void execute(String... params)  {
        /*@NotNull final SessionDto session = locator.getCurrentSession();
        System.out.println("Enter your old pass");
        final char[] oldPass;
        char[] newPass, newPassRepeat;
        oldPass = ts.readPass();
        while (true){
            System.out.println("Enter new password");
            newPass = ts.readPass();
            System.out.println("Repeat new password");
            newPassRepeat = ts.readPass();
            if(!Arrays.equals(newPass, newPassRepeat)){
                System.out.println("Passwords don't match, try again");
                continue;
            }
            break;
        }
        final boolean success = userEndpoint.changePassword(session, new String(oldPass),new String( newPass));
        if(success) System.out.println("Password was changed");
        else System.out.println("Password was NOT changed. Make sure you typed correct old password");
*/
    }

}
