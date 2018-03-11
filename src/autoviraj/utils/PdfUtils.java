/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Sehan Rathnayake
 */
public class PdfUtils {

    public static String formatDouble(double value) {
        String numberAsString = String.format("%.2f", value);
        if (numberAsString.length() > 6) {
            String part1 = numberAsString.substring(0, numberAsString.length() - 6);
            String Part2 = numberAsString.substring(numberAsString.length() - 6, numberAsString.length());
            numberAsString = part1 + "," + Part2;
        }
        return numberAsString;
    }

    public static String formatDate(Date date) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
    
    public static String formatMeterReading (double reading) {
        if(reading == 0)return "";
        else return String.valueOf(reading);        
    }
    
    

}
