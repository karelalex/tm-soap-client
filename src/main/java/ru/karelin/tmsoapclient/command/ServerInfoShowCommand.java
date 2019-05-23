package ru.karelin.tmsoapclient.command;

import org.springframework.stereotype.Component;


@Component
public class ServerInfoShowCommand extends AbstractCommand {


   /* @Autowired
    SessionEndpoint sessionEndpoint;

    private static final boolean SECURED = false;

    public ServerInfoShowCommand() {
        super(SECURED);
    }



   */

    @Override
    public String getName() {
        return "sinfo";
    }

    @Override
    public String getDescription() {
        return "shows information about server connected to";
    }
    @Override
    public void execute(String... params) {
       // System.out.println(sessionEndpoint.serverInfo());
    }
}
