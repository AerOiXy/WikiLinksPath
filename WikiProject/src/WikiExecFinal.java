import java.util.ArrayList;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiExecFinal {

    public static void main(String[] args) {
   	 
   	 System.out.println(">>>WikiWonder<<<\n");
   	 
   	 Scanner keyB = new Scanner(System.in);
   	 
   	 System.out.println("Start URL: ");
   	 String startURL = keyB.nextLine();
   	 
   	 Document activePage = null;
   	 String activeTitle = "";
   	 try {
   		 activePage = Jsoup.connect(startURL).get();
   	 } catch (Exception e) {
   		 System.out.println(">>>CONNECTION FAILURE<<<");
   	 } // connects with starting page
   	 
   	 System.out.println("Target URL: ");
   	 String targetURL = keyB.nextLine();

   	 Document targetPage = null;
   	 String targetTitle = "";
   	 try {
   		 targetPage = Jsoup.connect(targetURL).get();
   		 targetTitle = targetPage.title();
   	 } catch (Exception e) {
   		 System.out.println(">>>CONNECTION FAILURE<<<");
   	 } // connects with the ending page and gets the title
   	 
   	 boolean found = false;
   	 int linkCount = 1; // not in use yet due to lack of recursive call
   	 String fixURL = "";
   	 int i = 0;
   	 
   
   	ArrayList<String> links = WikiPathFinal.getLinks(activePage);
   	
   	for (int x = 0; x < links.size(); x++) {
   		System.out.println(links.get(x));
   	} // prints out retrieved links
   	
   	while (!found && i < links.size()) {
   		try {
   			fixURL = "https://en.wikipedia.org" + links.get(i);
   			activePage = Jsoup.connect(fixURL).get();
   			activeTitle = activePage.title();
   			if (activeTitle.equals(targetTitle)) {
   				System.out.println("\nTarget page found! It took " + linkCount + " links to reach it!");
   				found = true;
   			}
   			
   			} catch (Exception e) {
   				System.out.println(">>>PROCESSING ERROR<<<");
   		}
   		i++;
   	} // end while
   	 
   	 
   	 
   	 
    } // end main

} // end class
