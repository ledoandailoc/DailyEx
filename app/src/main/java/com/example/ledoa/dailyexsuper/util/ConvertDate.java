package com.example.ledoa.dailyexsuper.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
	
	public static Date convertStringToDate(String sDate) {
		 DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		        Date date = null;
		        try {
		            date = new Date(format.parse(sDate).getTime());
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
		        return date;
		    }
	public static String ConvertDateToString(Date date) {
		Format formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String s = formatter.format(date);
		return s;
	    }
	
	public static String ConvertNgayToString(Date date) {
		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		String s = formatter.format(date);
		return s;
	    }
}
