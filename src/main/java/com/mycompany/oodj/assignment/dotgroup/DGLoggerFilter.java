package com.mycompany.oodj.assignment.dotgroup;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class DGLoggerFilter implements Filter {
	@Override
	public boolean isLoggable(LogRecord log) {
		if (log.getLevel() == Level.CONFIG) 
			return false;
		return true;
	}
}
