/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author john
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtils {
  //public static final Gson gson = new Gson();

  JSONUtils(){}
 
  public static boolean isValidJSON(final String json) {
        boolean valid = false;
        JSONParser parser = new JSONParser();
        try {
           JSONArray jsonArray = (JSONArray) parser.parse(json);
           valid = true;
        } catch (ParseException ex) {
           // Logger.getLogger(JSONUtils.class.getName()).log(Level.SEVERE, null, ex);
           valid = false;
        }
       
   
   return valid;
  }
  
  public static boolean isValidJSONObject(final String json) {
        boolean valid = false;
        JSONParser parser = new JSONParser();
        try {
           
           JSONObject allObj = (JSONObject)parser.parse(json);
           valid = true;
        } catch (ParseException ex) {
           // Logger.getLogger(JSONUtils.class.getName()).log(Level.SEVERE, null, ex);
           valid = false;
        }
       
   
   return valid;
  }
}
