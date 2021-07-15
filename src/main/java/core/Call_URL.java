/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.w3c.dom.Document;


/**
 *
 * @author john
 */
public class Call_URL {
    /**
     *
     * @param myURL
     * @return
     */
	public static String callURL12(String myURL, String token) {
        //System.out.println("Requested URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization","Bearer token="+ token);
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestMethod("GET");
//            urlConn = url.openConnection();
            if (conn != null) {
            	conn.setReadTimeout(0);
            }
            if (conn != null && conn.getInputStream() != null) {
                in = new InputStreamReader(conn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            //throw new RuntimeException("Exception while calling URL:" + myURL, e);
            return "Error";
            //throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }
        return sb.toString();
    }
	
	public static String callURL1(String ReopUrl, String bearerToken) {
	    BufferedReader reader = null;
	    String response = "";
	    try {
	        URL url = new URL(ReopUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestProperty("Authorization", "token " + bearerToken);
	        connection.setDoOutput(true);
	        connection.setRequestMethod("GET");
	        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line = null;
	        StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
	        while ((line = reader.readLine()) != null) {
	            out.append(line);
	        }
	        response = out.toString();
//	        System.out.println(response);
	    } catch (Exception e) {

	    }
	    return response;
	}
	
    public static String callURL(String myURL) {
        //System.out.println("Requested URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                urlConn.setReadTimeout(0);
            }
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            //throw new RuntimeException("Exception while calling URL:" + myURL, e);
            return "Error";
            //throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }
        return sb.toString();
    }
    
//    public static String getURLTitle(String url) {
//        String title = "404";
//        try {
//            Document doc = null;
//            Boolean OK = true;
//            try {
//                Connection.Response res = Jsoup.connect(url)
//                        .method(Connection.Method.GET)
//                        .execute();
//                if (res != null) {
//                    doc = (Document) res.parse();
//                    title = ((org.jsoup.nodes.Document) doc).title();
//                    return title;
//                }
//            } catch (Exception e) {
//                //e.printStackTrace();
//                return title;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return title;
//    }
    
    public static String callURLfDroid(String myURL) {
        //System.out.println("Requested URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                urlConn.setReadTimeout(60 * 1000);
            }
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            //throw new RuntimeException("Exception while calling URL:" + myURL, e);
            return "Error";
            //throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }
        return sb.toString();
    }
    
    public static String processXML(String project, String path, String[] tokens, int ct) {
    	String forkPackage = "";
    	
    	try {
            if (ct == (tokens.length)) {/// the the index for the tokens array...
                ct = 0; //// go back to the first index......
            }
            //https://raw.githubusercontent.com/Cymrodor/bitcoin-wallet/master/sample-integration-android/AndroidManifest.xml
            String manifest_xml = "";
            manifest_xml = 	Call_URL.callURL("https://raw.githubusercontent.com/" +project+"/master/"+path+ "?access_token=" + tokens[ct++]);
            	
	                Document doc  = XmlDoc.document(manifest_xml);
	                if (doc != null) {
	                    doc.getDocumentElement().normalize();
	                    forkPackage = doc.getDocumentElement().getAttribute("package");
	                    //System.out.println("      Package :" + package_name);
	                }
            	
                
                String isValid = Call_URL.callURL("https://play.google.com/store/apps/details?id=" + forkPackage);
                
                
            }catch(Exception e){
            	System.out.println("Failed..!!");
            }
        
    	
    	return forkPackage+"="+ct;
    }
}
