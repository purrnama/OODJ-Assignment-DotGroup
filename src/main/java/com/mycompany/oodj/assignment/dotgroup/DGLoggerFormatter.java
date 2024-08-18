package com.mycompany.oodj.assignment.dotgroup;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class DGLoggerFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return record.getLongThreadID() + "::" + record.getSourceClassName()+"::"
                +record.getSourceMethodName()+"::"
                +new Date(record.getMillis())+"::"
                +record.getMessage()+"\n";
    }
}
