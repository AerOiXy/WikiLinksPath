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
    
    
    
    
    
    
    
    public static int process(ArrayList<String> indirectLinks, String tT, boolean f, int count) {
    	Document activePage = null;
    	String activeTitle = "";
    	boolean found = false;
    	String fixURL = "";
    	
    	if (f) {
    		int x = 0;
        	
        	while (!found && x < indirectLinks.size()) {
           		try {
           			fixURL = "https://en.wikipedia.org" + indirectLinks.get(x);
           			activePage = Jsoup.connect(fixURL).get();
           			activeTitle = activePage.title();
           			if (activeTitle.equals(tT)) {
           				found = true;
           				return 1;
           			}
         
           			} catch (Exception e) {
           				System.out.println(">>>PROCESSING ERROR<<<");
           		}
           		x++;
           	} // end while
        	return process(indirectLinks, tT, false, 1);
    	} // only for first degree run
    	
    	ArrayList<String> bulkLinks = new ArrayList<String>();
    	
    	for (int i = 0; i < indirectLinks.size(); i++)
    	{    	
    		try {
         		 activePage = Jsoup.connect(indirectLinks.get(i)).get();
         	 } catch (Exception e) {
         		 System.out.println(">>>CONNECTION FAILURE<<<");
         	 } // connects with starting page
    		
    		ArrayList<String> tempLinks = getLinks(activePage);
    		
    		for (int b = 0; b < tempLinks.size(); b++) {
    	   		System.out.println(tempLinks.get(b));
    	   	} // prints out retrieved links
    		
    		for (int a = 0; a < tempLinks.size(); a++) {
    			bulkLinks.add(tempLinks.get(a));
    		} // adds links of one page to bigger ArrayList
    		
    		
    	} // adds all links to the bulkLinks ArrayList
    	
    	int x = 0;
    	
    	while (!found && x < bulkLinks.size()) {
       		try {
       			fixURL = "https://en.wikipedia.org" + bulkLinks.get(x);
       			activePage = Jsoup.connect(fixURL).get();
       			activeTitle = activePage.title();
       			if (activeTitle.equals(tT)) {
       				found = true;
       			}
       			
       			} catch (Exception e) {
       				System.out.println(">>>PROCESSING ERROR<<<");
       		}
       		x++;
       	} // end while
    	if (found) {
    		return count;
    	}
    	else
    		return process(bulkLinks, tT, false, count + 1);
    } // end recursive process
    
} // end class