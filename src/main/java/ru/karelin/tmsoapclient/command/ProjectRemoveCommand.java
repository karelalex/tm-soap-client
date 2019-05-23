package ru.karelin.tmsoapclient.command;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmwebspring.soap.ProjectEndpoint;


@Component
public class ProjectRemoveCommand extends AbstractCommand {

    @Autowired
    private ProjectEndpoint projectEndpoint;

    private static final boolean SECURED = true;

    public ProjectRemoveCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "rp";
    }

    @Override
    public String getDescription() {
        return "removes project with specified number and all its tasks";
    }

    @Override
    public void execute(final String... params) {
        String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        projectEndpoint.removeProjectById(projectId);
    }
}