package ru.karelin.tmsoapclient.command;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmsoapclient.api.util.ServiceLocator;
import ru.karelin.tmsoapclient.util.DateConverter;
import ru.karelin.tmwebspring.soap.ProjectEndpoint;
import ru.karelin.tmwebspring.soap.Status;
import ru.karelin.tmwebspring.soap.TaskDto;
import ru.karelin.tmwebspring.soap.TaskEndpoint;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Component
public class TaskCreateCommand  extends AbstractCommand {

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private ProjectEndpoint projectEndpoint;

    @Autowired
    private TaskEndpoint taskEndpoint;

    @Autowired
    private DateConverter dateConverter;

    private static final boolean SECURED = true;

    public TaskCreateCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "ct";
    }

    @Override
    public String getDescription() {
        return "starts create new task dialog. Task will be created inside " +
                "specified project or project ID will be asked later.";
    }

    @Override
    public void execute(final String... params) throws DatatypeConfigurationException {
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @NotNull String projectId="";
        if (params.length>0) projectId=params[0];
        TaskDto task = new TaskDto();
        System.out.println("Enter task name");
        @NotNull final String taskName = ts.readLn();
        System.out.println("Enter task description");
        @NotNull final String taskDescription = ts.readLn();
        @NotNull String date;
        @NotNull Date taskStartDate;
        while (true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or leave empty for today");
            date = ts.readLn();
            if (date.isEmpty()) {
                taskStartDate = new Date();
                break;
            } else {
                try {
                    taskStartDate = dateFormat.parse(date);
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        @NotNull Date taskFinishDate;
        while (true) {
            System.out.println("Enter ending date (format DD.MM.YYYY)");
            date = ts.readLn();
            try {
                taskFinishDate = dateFormat.parse(date);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (projectId.isEmpty()) {
            System.out.println("Enter project id where task will be added");
            projectId = ts.readLn();
        }
        while (projectId.isEmpty() || projectEndpoint.getProjectById(projectId)==null) {
            System.out.println("Wrong project id try again or print END to close this dialog");
            projectId = ts.readLn();
            if (projectId.equals("END")) return;
        }
        task.setId(UUID.randomUUID().toString());
        task.setName(taskName);
        task.setDescription(taskDescription);
        task.setStartingDate(dateConverter.convert(taskStartDate));
        task.setFinishDate(dateConverter.convert(taskFinishDate));
        task.setProjectId(projectId);
        task.setUserId(locator.getCurrentUserId());
        task.setStatus(Status.PLANNED);
        taskEndpoint.updateTask(task);

    }
}
