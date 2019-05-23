package ru.karelin.tmsoapclient.command;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmsoapclient.api.util.ServiceLocator;
import ru.karelin.tmsoapclient.util.DateConverter;
import ru.karelin.tmwebspring.soap.ProjectDto;
import ru.karelin.tmwebspring.soap.ProjectEndpoint;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.text.DateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProjectListShowCommand extends AbstractCommand {

    @Autowired
    private ProjectEndpoint projectEndpoint;

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private DateConverter dateConverter;

    private static final boolean SECURED = true;

    public ProjectListShowCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "pl";
    }

    @Override
    public String getDescription() {
        return "shows list of projects";
    }

    @Override
    public void execute(final String... params) {
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        boolean isSorted = false;
        @NotNull String sortField = "";
        boolean isStraight = true;
        boolean isFind = false;
        String searchItem="";
        if (params.length > 0) {
            int iter=0;
            while (iter < params.length) {
                if (params[iter].equals("-sort")) {
                    isSorted = true;
                    iter++;
                    if (iter < params.length && !params[iter].startsWith("-")) {
                        sortField = params[iter];
                        iter++;
                        if (iter < params.length && !params[iter].startsWith("-")) {
                            if (params[iter].startsWith("desc")) isStraight = false;
                            iter++;
                        }
                    }
                }
                if(iter<params.length && params[iter].equals("-search")){
                    isFind = true;
                    iter ++;
                    if(iter<params.length && !params[iter].startsWith("-")){
                        searchItem = params[iter];
                    }
                }
                iter++;

            }

        }
        @NotNull final List<ProjectDto> projects;
        /*if (isFind) projects = projectEndpoint.getProjectListByKeyword(session, searchItem);
        else if (isSorted) projects= projectEndpoint.getProjectSortedList(session, sortField, isStraight);
        else*/ projects = projectEndpoint.getProjectList();
        for (ProjectDto p :projects) {
            System.out.println("Project ID: " + p.getId());
            System.out.println("Project name: " + p.getName() );
            System.out.println("Project description: " + p.getDescription());
            System.out.println("Start Date: " + dateFormat.format(dateConverter.convert(p.getStartingDate())));
            System.out.println("End Date: " + dateFormat.format(dateConverter.convert(p.getFinishDate())));
            System.out.println("Status: " + p.getStatus().toString());
            System.out.println();
        }
    }
}
