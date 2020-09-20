package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    public static ArrayList<String> pickStr(String csvFile, int cellNum, int rowNum) {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        ArrayList<String> str = new ArrayList<String>();

        try {
        	int i = 0;
            br = new BufferedReader(new FileReader(csvFile));
            
            while ((line = br.readLine()) != null) {
            	if (i >= rowNum) {
            		// use comma as separator
            		String[] rowDetails = line.split(cvsSplitBy);
//            		System.out.println(rowDetails[11]);
            		str.add(rowDetails[cellNum]);
               	}
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return str;
    }

}