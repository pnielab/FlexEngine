package com.hp.opta.flex.functions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Function;

import com.hp.opta.flex.functions.enums.FunctionName;

/**
 * The parameter is a single TimeStamp, which is returned with its year 
 * changed to the current year. The calculation is done in the local timezone, 
 * which will affect the result near either end of the year.
 * __useCurrentYear(date)
 */
public class UseCurrentYear implements Function<List<Object>, Object> {

	private final static DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;

    public static final FunctionName FUNCTION_NAME = FunctionName.UseCurrentYear;
    
    @Override
    public Object apply(List<Object> objects) {

        String timeStampString = (String)objects.get(0);

    	LocalDateTime ts = LocalDateTime.parse(timeStampString, dtf);
    	
    	return ts.withYear(Calendar.getInstance().get(Calendar.YEAR)).format(dtf);

    }
}
