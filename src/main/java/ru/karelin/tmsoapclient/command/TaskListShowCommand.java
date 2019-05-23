package ru.karelin.tmsoapclient.command;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmsoapclient.api.util.ServiceLocator;
import ru.karelin.tmsoapclient.util.DateConverter;
import ru.karelin.tmwebspring.soap.ProjectEndpoint;
import ru.karelin.tmwebspring.soap.TaskDto;
import ru.karelin.tmwebspring.soap.TaskEndpoint;

import java.text.DateFormat;
import java.util.List;

@Component
public class TaskListShowCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private DateConverter dateConverter;

    @Autowired
    private TaskEndpoint taskEndpoint;

    @Autowired
    private ProjectEndpoint projectEndpoint;

    private static final boolean SECURED = true;

    public TaskListShowCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "tl";
    }

    @Override
    public String getDescription() {
        return "shows task list for specified project. If not shows all tasks";
    }

    @Override
    public void execute(final String... params) {
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @NotNull final String projectId;
        boolean isSorted = false;
        String sortField = "";
        boolean isStraight = true;
        String searchItem = "";
        boolean isFind = false;
        if (params.length > 0) {
            int iter;
            if (!params[0].startsWith("-")) {
                projectId = params[0];
                iter = 1;
            } else {
                iter = 0;
                projectId = "";
            }
            while (iter < params.length) {
                if (params[iter].equals("-sort")) {
                    isSorted = true;
                    iter++;
                    if (iter < params.length && !params[iter].startsWith("-")) {
                        sortField = params[iter];
                        iter++;
                        if (iter < params.length && !params[iter].startsWith("-")) {
                            if (params[iter].startsWith("desc")) isStraight = false;

                        }
                    }
                    if (iter < params.length && params[iter].equals("-search")) {
                        isFind = true;
                        iter++;
                        if (iter < params.length && !params[iter].startsWith("-")) {
                            searchItem = params[iter];
                        }
                    }
                    iter++;
                }
                iter++;
            }

        } else projectId = "";
        @NotNull final List<TaskDto> tasks;
        boolean showProjectId = true;
       /* if (isFind) tasks = taskEndpoint.getTaskListByKeyword(session, searchItem);
        else*/ if (projectId.isEmpty()) {
           /* if (isSorted) {
                tasks = taskEndpoint.getSortedTaskList(session, sortField, isStraight);
            } else*/ tasks = taskEndpoint.getTaskList();
        } else if (projectEndpoint.getProjectById(projectId)!=null) {
            /*if (isSorted) {
                tasks = taskEndpoint.getSortedTaskListByProjectId(session, projectId, sortField, isStraight);
            } else*/ tasks = taskEndpoint.getTaskListByProjectId(projectId);
            showProjectId = false;
        } else {
            System.out.println("Wrong Project ID");
            return;
        }
        for (TaskDto t : tasks) {
            System.out.println("Task: " + t.getId());
            System.out.println("Task name: " + t.getName());
            System.out.println("Task description: " + t.getDescription());
            System.out.println("Task start date: " + dateFormat.format(dateConverter.convert(t.getStartingDate())));
            System.out.println("Task finish date " + dateFormat.format(dateConverter.convert(t.getFinishDate())));
            System.out.println("Status: " + t.getStatus().toString());
            if (showProjectId) System.out.println("Project ID: " + t.getProjectId());
            System.out.println();
        }
    }
}
