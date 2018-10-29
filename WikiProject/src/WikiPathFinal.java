import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.Scanner;

public class WikiPathFinal {
    
    public static ArrayList<String> getLinks(Document aP) {
   	 ArrayList<String> allLinks = new ArrayList<String>();
   	 Elements retrievedLinks = null;
   	 String addPoss = "";
   	 try {
   		 retrievedLinks = aP.select("a");
   		 if (retrievedLinks != null) {
   			 for (Element temp : retrievedLinks) {
   				 addPoss = temp.attr("href");
   				 if (addPoss.contains("/wiki/"))
   					 allLinks.add(addPoss);
   			 }
   		 }
   	 } catch (Exception e) {
   		 System.out.println(">>>LINK RETRIEVAL ERROR<<<");
   	 }
   	 return allLinks;
    } // retrieves the article links in the Wikipedia page
    
    public static void process(ArrayList<String> indirectLinks) {
    	ArrayList<String> temp = new ArrayList<String>();
    	
    }
    
} // end class
