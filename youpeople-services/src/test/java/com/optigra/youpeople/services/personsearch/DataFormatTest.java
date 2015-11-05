package com.optigra.youpeople.services.personsearch;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by romanmudryi on 12.08.15.
 */
public class DataFormatTest {

    private static final String START_MONTH = "January";

    @Test
    public void test0() {
        String value = "June 2013 – July 2014 (1 year 2 months)Ukraine, Lviv";
        try {
            System.out.println("------------------------");
            parseLinkedinDateString(value);
            System.out.println("------------------------");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test1() {
        String value = "May 2014 – September 2014 (5 months)";
        try {
            System.out.println("------------------------");
            parseLinkedinDateString(value);
            System.out.println("------------------------");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test2() {
        String value = "January 2015 – Present (8 months)";
        try {
            System.out.println("------------------------");
            parseLinkedinDateString(value);
            System.out.println("------------------------");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test3() {
        String value = "2014Health";
        try {
            System.out.println("------------------------");
            parseLinkedinDateString(value);
            System.out.println("------------------------");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test4() {
        String value = "January 2013Children";
        try {
            System.out.println("------------------------");
            parseLinkedinDateString(value);
            System.out.println("------------------------");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test5() {
        String value = "2011 – 2012";
        try {
            System.out.println("------------------------");
            parseLinkedinDateString(value);
            System.out.println("------------------------");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void parseLinkedinDateString(String dateString) throws ParseException {
        String[] jobStartEnd = dateString.split(" – ");
        String[] start = jobStartEnd[0].split(" ");
        if(StringUtils.isNumeric(start[0])){
            parseStringToDate(START_MONTH, start[0]);
        } else {
            if((start.length > 1)){
                if(StringUtils.isNumeric(start[1])){
                    parseStringToDate(start[0], start[1]);
                } else {
                    parseStringToDate(start[0], start[1].substring(0,4));
                }
            } else {
                parseStringToDate(START_MONTH, start[0].substring(0,4));
            }
        }
        if(jobStartEnd.length > 1){
            String[] end = jobStartEnd[1].split(" ");
            if (StringUtils.isNumeric(end[0])) {
                parseStringToDate(START_MONTH, end[0]);
            } else if (StringUtils.isNumeric(end[1])) {
                parseStringToDate(end[0], end[1]);
            }
        }
    }

    private void parseStringToDate(String month, String year) throws ParseException {
        String format = "MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(month + " " + year);
        System.out.println(sdf.format(date));
    }

}
