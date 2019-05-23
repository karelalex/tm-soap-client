package ru.karelin.tmsoapclient.command;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmsoapclient.api.util.ServiceLocator;
import ru.karelin.tmsoapclient.util.DateConverter;
import ru.karelin.tmwebspring.soap.TaskDto;
import ru.karelin.tmwebspring.soap.TaskEndpoint;

import java.text.DateFormat;

@Component
public class TaskShowCommand extends AbstractCommand {

    @Autowired
    private DateConverter dateConverter;

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private TaskEndpoint taskEndpoint;


    private static final boolean SECURED = true;

    public TaskShowCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "st";
    }

    @Override
    public String getDescription() {
        return "shows task with specified ID";
    }

    @Override
    public void execute(final String... params) {
        @NotNull final String taskId;
        if (params.length > 0) taskId = params[0];
        else {
            System.out.println("You must enter taskId");
            return;
        }
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        TaskDto task = taskEndpoint.getTaskById(taskId);
        if (task==null) {
            System.out.println("Wrong ID");
            return;
        }
        System.out.println("Task name: " + task.getName());
        System.out.println("Task description: " + task.getDescription());
        System.out.println("Task start date: " + dateFormat.format(dateConverter.convert(task.getStartingDate())));
        System.out.println("Task finish date " + dateFormat.format(dateConverter.convert(task.getFinishDate())));
        System.out.println("Status: " + task.getStatus().toString());
        System.out.println("Project ID: " + task.getProjectId());
        System.out.println();
    }
}
