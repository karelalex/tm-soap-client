package ru.karelin.tmsoapclient.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

@Component
public class TaskEditCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private ProjectEndpoint projectEndpoint;

    @Autowired
    private TaskEndpoint taskEndpoint;

    @Autowired
    private DateConverter dateConverter;

    private static final boolean SECURED = true;

    public TaskEditCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "et";
    }

    @Override
    public String getDescription() {
        return "starts edit task dialog for specified task";
    }

    @Override
    public void execute(final String... params) throws DatatypeConfigurationException {
        @NotNull final String taskId;
        if (params.length > 0) taskId = params[0];
        else {
            System.out.println("You must enter taskId");
            return;
        }
        TaskDto task = taskEndpoint.getTaskById(taskId);
        if (task == null) {
            System.out.println("Wrong ID!");
            return;
        }
        System.out.println("Enter new task name or just press enter if you do not want to change it");
        @NotNull final String taskName = ts.readLn();
        if(!taskName.isEmpty()) task.setName(taskName);
        System.out.println("Enter new task description or just press enter if you do not want to change it");
        @NotNull final String taskDescription = ts.readLn();
        if(!taskDescription.isEmpty()) task.setDescription(taskDescription);
        String taskStatusString;
        @Nullable Status taskStatus;
        stat:
        while (true) {
            System.out.println("Enter new project status or leave empty to keep current status.\nYou must enter one of the these values: ");
            for (Status s : Status.values()
            ) {
                System.out.print(s.toString() + ", ");
            }
            System.out.println("\b\b");
            taskStatusString = ts.readLn();
            if (taskStatusString.isEmpty()) {
                break;
            }
            for (Status s : Status.values()) {
                if (s.toString().equals(taskStatusString)) {
                    task.setStatus(s);
                    break stat;
                }
            }
        }
        @NotNull String date;
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        while (true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or just press enter if you do not want to change it");
            date = ts.readLn();
            if (date.isEmpty()) {
                break;
            } else {
                try {
                    task.setStartingDate(dateConverter.convert(dateFormat.parse(date)));
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
                    task.setFinishDate(dateConverter.convert(dateFormat.parse(date)));
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Enter new project id for task or just press enter if you do not want to change it");
        @NotNull String projectId = ts.readLn();
        while (!projectId.isEmpty() && projectEndpoint.getProjectById(projectId)==null) {
            System.out.println("Wrong project id try again or leave it empty");
            projectId = ts.readLn();
        }
        if(!projectId.isEmpty()) task.setProjectId(projectId);

        taskEndpoint.updateTask(task);

    }
}
