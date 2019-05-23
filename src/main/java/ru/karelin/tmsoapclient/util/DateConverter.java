package ru.karelin.tmsoapclient.util;

import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class DateConverter {
    public XMLGregorianCalendar convert(Date date) throws DatatypeConfigurationException {
        if (date==null) return null;
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }

    public Date convert(XMLGregorianCalendar calendar){
        if (calendar==null) return null;
        return calendar.toGregorianCalendar().getTime();
    }
}
