package ru.karelin.tmsoapclient.command;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmsoapclient.api.util.ServiceLocator;
import ru.karelin.tmsoapclient.util.DateConverter;
import ru.karelin.tmwebspring.soap.ProjectDto;
import ru.karelin.tmwebspring.soap.ProjectEndpoint;
import ru.karelin.tmwebspring.soap.Status;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.DateFormat;
import java.text.ParseException;

@Component
public class ProjectEditCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private ProjectEndpoint projectEndpoint;

    @Autowired
    private DateConverter dateConverter;


    private static final boolean SECURED = true;


    public ProjectEditCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "ep";
    }

    @Override
    public String getDescription() {
        return "starts edit project dialog for specified project";
    }

    @Override
    public void execute(final String... params) throws DatatypeConfigurationException {
        @NotNull String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        ProjectDto project = projectEndpoint.getProjectById(projectId);
        if (project==null) {
            System.out.println("Wrong ID " + projectId);
            return;
        }
        System.out.println("Enter new project name or just press enter if you do not want to change it");
        final String projectName = ts.readLn();
        if(!projectName.isEmpty()) project.setName(projectName);
        System.out.println("Enter new project description or just press enter if you do not want to change it");
        final String projectDescription = ts.readLn();
        if(!projectDescription.isEmpty()) project.setDescription(projectDescription);
        String projectStatusString;
        stat:
        while (true){
            System.out.println("Enter new project status or leave empty to keep current status.\nYou must enter one of the these values: ");
            for (Status s: Status.values()
            ) {
                System.out.print(s.toString()+", ");
            }
            System.out.println("\b\b");
            projectStatusString = ts.readLn();
            if(projectStatusString.isEmpty()) {
                break;
            }
            for (Status s : Status.values()) {
                if (s.toString().equals(projectStatusString)) {
                    project.setStatus(s);
                    break stat;
                }
            }
        }
        String date;
        while (true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or just press enter if you do not want to change it");
            date = ts.readLn();
            if (date.isEmpty()) {
                break;
            } else {
                try {
                    project.setStartingDate(dateConverter.convert(dateFormat.parse(date)));
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        while (true) {
            System.out.println("Enter ending date (format DD.MM.YYYY) or just press enter if you do not want to change it");
            date = ts.readLn();
            if (date.isEmpty()) {
                break;
            } else {
                try {
                    project.setFinishDate(dateConverter.convert(dateFormat.parse(date)));
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        projectEndpoint.updateProject(project);
    }
}


