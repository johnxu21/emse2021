/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.field.OffsetDateTimeField;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;


/**
 *
 * @author john
 */
public class DateOperations {

    /**
     * *
     *
     * @param date1
     * @param date2
     * @return
     */
    public static String diff(String date1, String date2) {

        //System.out.println(date1+"\t"+date2);
        long dsecs = 0;
        long dminutes = 0;
        long dhours = 0;
        long ddays = 0;

        try {
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            //System.out.println(date1+"\t"+date2+" \t\t"+date1.substring(0, 10) + " " + date1.substring(11, date1.length() - 1));

            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar1.setTime(s.parse(date1.substring(0, 10) + " " + date1.substring(11, date1.length() - 1)));
            calendar2.setTime(s.parse(date2.substring(0, 10) + " " + date2.substring(11, date2.length() - 1)));
            long milsecs1 = calendar1.getTimeInMillis();
            long milsecs2 = calendar2.getTimeInMillis();
            long diff = milsecs2 - milsecs1;
            dsecs = diff / 1000;
            dminutes = diff / (60 * 1000);
            dhours = diff / (60 * 60 * 1000);
            ddays = diff / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ddays + "/" + dhours + ":" + dminutes + ":" + dsecs;
    }

    public static String dateLessBy1Sec(String s1) {
    	
    	DateTime odt = DateTime.parse(s1) ;
    	
    	DateTime subtracted = odt.minusSeconds(10);

//    	System.out.println(subtracted.toString().replace(".000", ""));
    			
    	return subtracted.toString().replace(".000", "");
    	
    }
    
    public static String sorts(List<String> fList, List<String> lList) throws ParseException {
        List<Date> dates = new ArrayList<Date>();
        List<Date> dates2 = new ArrayList<Date>();
        //System.out.println(fList+"\t"+lList);

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for (int i = 0; i < fList.size(); i++) {
            Date date = fmt.parse(fList.get(i).substring(0, 10) + " " + fList.get(i).substring(11, fList.get(i).length() - 1));

            Date date2 = fmt.parse(lList.get(i).substring(0, 10) + " " + lList.get(i).substring(11, lList.get(i).length() - 1));
            dates.add(date);
            dates2.add(date2);
        }
        String min_date = fmt.format(java.util.Collections.min(dates));
        String d_m = min_date.substring(0, 10) + "T" + min_date.substring(11, min_date.length()) + ":00Z";

        String max_date = fmt.format(java.util.Collections.max(dates2));
        String d_m2 = max_date.substring(0, 10) + "T" + max_date.substring(11, max_date.length()) + ":00Z";

        return d_m + "/" + d_m2;
    }
    
    public static String sorts1(List<String> fList) throws ParseException {
        String date = "";
        
        String date0 = fList.get(0).replaceAll("-", "").replaceAll("T", "").replace("Z", "").replaceAll(":", "");
        String date1 = fList.get(1).replaceAll("-", "").replaceAll("T", "").replace("Z", "").replaceAll(":", "");
        
        long dt0 = Long.parseLong(date0);
        long dt1 = Long.parseLong(date1);
        
        if (dt0 < dt1)
        	date = fList.get(0);
        else
        	date = fList.get(1);

        //System.out.println("Stop :: " + date);
        return date;
    }

    public static String addDates(String date, int increament_by) {
        String dt = "";  // Start date
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(date.substring(0, 10) + " " + date.substring(11, date.length() - 1)));
            c.add(Calendar.DAY_OF_YEAR, increament_by);  // number of days to add
            date = sdf.format(c.getTime());  // dt is now the new date
            dt = date.substring(0, 10) + "T" + date.substring(11, date.length()) + ":00Z";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }
    
    public static String addMinutes(String date, int increament_by) {
        String dt = "";  // Start date
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(date.substring(0, 10) + " " + date.substring(11, date.length() - 1)));
            c.add(Calendar.MINUTE, increament_by);  // number of days to add
            date = sdf.format(c.getTime());  // dt is now the new date
            dt = date.substring(0, 10) + "T" + date.substring(11, date.length()) + ":00Z";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    public static boolean compareDates(String d1, String d2) {
        boolean is_before = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = sdf.parse(d1.substring(0, 10) + " " + d1.substring(11, d1.length() - 1));
            Date date2 = sdf.parse(d2.substring(0, 10) + " " + d2.substring(11, d2.length() - 1));
            if (date1.after(date2)) {
                is_before = false;
            }
            // before() will return true if and only if date1 is before date2
            if (date1.before(date2)) {
                //System.out.println("Date1 is before Date2");
                is_before = true;
            }

            //equals() returns true if both the dates are equal
            if (date1.equals(date2)) {
                is_before = true;
            }

            //System.out.println();
        } catch (Exception ex) {
            //ex.printStackTrace();
             return is_before;
        }
        return is_before;
    }

    public static String format(String dateStr, String format1, String format2) {
        //String dateStr = "";
        try {
            DateFormat srcDf = new SimpleDateFormat(format1);
            Date date = srcDf.parse(dateStr);
            DateFormat destDf = new SimpleDateFormat(format2);
            dateStr = destDf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateStr;
    }
    
    public static String dates(String date1, String date2) {
    	date1 = date1.replaceAll(":", "").replaceAll("-", "").replace("T", "").replace("Z", "");
    	date2 = date2.replaceAll(":", "").replaceAll("-", "").replace("T", "").replace("Z", "");
    	if (Long.parseLong(date1) >= Long.parseLong(date2))
    		return "true";
    	else
    		return "false";
    	
    }

    public static void main(String[] args) {
        String date1 = "2018-10-02T07:58:29Z";
        String date2 = "2018-10-02T08:58:24Z";
        
        System.out.println(dateLessBy1Sec(date1));
    }
}
