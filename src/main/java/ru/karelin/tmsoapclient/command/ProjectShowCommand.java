package ru.karelin.tmsoapclient.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmsoapclient.api.util.ServiceLocator;
import ru.karelin.tmsoapclient.util.DateConverter;
import ru.karelin.tmwebspring.soap.ProjectDto;
import ru.karelin.tmwebspring.soap.ProjectEndpoint;

import java.text.DateFormat;

@Component
public class ProjectShowCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private DateConverter dateConverter;

    @Autowired
    private ProjectEndpoint projectEndpoint;

    private static final boolean SECURED = true;

    public ProjectShowCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "sp";
    }

    @Override
    public String getDescription() {
        return "shows project info";
    }

    @Override
    public void execute(final String... params) {
        @Nullable final String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        @Nullable final DateFormat dateFormat = locator.getDateFormat();

        @NotNull final ProjectDto project = projectEndpoint.getProjectById(projectId);
        if (project == null) System.out.println("No projects with id = "+ projectId);
        System.out.println("Project name: " + project.getName());
        System.out.println("Project description: " + project.getDescription());
        System.out.println("Start Date: " + dateFormat.format(dateConverter.convert(project.getStartingDate())));
        System.out.println("End Date: " + dateFormat.format(dateConverter.convert(project.getFinishDate())));
        System.out.println("Status: " + project.getStatus().toString());
        System.out.println();
    }
}
