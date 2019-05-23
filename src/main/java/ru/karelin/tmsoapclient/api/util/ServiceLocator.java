package ru.karelin.tmsoapclient.api.util;


import ru.karelin.tmsoapclient.command.AbstractCommand;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;

public interface ServiceLocator {
    List<String> getCookies();

    void setCookies(List<String> cookies);

    String getCurrentUserId();

    void setCurrentUserId(String userId);

    Map<String, AbstractCommand> getCommands();

    DateFormat getDateFormat();

    void init(Class[] commandClasses);
}
