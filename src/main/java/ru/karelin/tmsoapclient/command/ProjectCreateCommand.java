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
import java.util.Date;
import java.util.UUID;

@Component
public class ProjectCreateCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private ProjectEndpoint projectEndpoint;

    @Autowired
    private DateConverter dateConverter;

    private static final boolean SECURED = true;

    public ProjectCreateCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "cp";
    }

    @Override
    public String getDescription() {
        return "starts create new project dialog";
    }

    @Override
    public void execute(final String... params) throws DatatypeConfigurationException {
        @NotNull final DateFormat dateFormat = locator.getDateFormat();

        System.out.println("Enter project name");
        @NotNull final String projectName = ts.readLn();
        System.out.println("Enter project description");
        final String projectDescription = ts.readLn();
        @NotNull String date;
        @NotNull Date projectStartDate;
        while (true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or leave empty for today");
            date = ts.readLn();
            if (date.isEmpty()) {
                projectStartDate = new Date();
                break;
            } else {
                try {
                    projectStartDate = dateFormat.parse(date);
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        @NotNull Date projectFinishDate;
        while (true) {
            System.out.println("Enter ending date (format DD.MM.YYYY)");
            date = ts.readLn();
            try {
                projectFinishDate = dateFormat.parse(date);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        ProjectDto project = new ProjectDto();
        project.setId(UUID.randomUUID().toString());
        project.setName(projectName);
        project.setDescription(projectDescription);
        project.setStartingDate(dateConverter.convert(projectStartDate));
        project.setFinishDate(dateConverter.convert(projectFinishDate));
        project.setUserId(locator.getCurrentUserId());
        project.setStatus(Status.PLANNED);
        projectEndpoint.updateProject(project);
    }
}
