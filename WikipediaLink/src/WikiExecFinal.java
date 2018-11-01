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
   
   	ArrayList<String> links = WikiPathFinal.getLinks(activePage);
   	
   	for (int x = 0; x < links.size(); x++) {
   		System.out.println(links.get(x));
   	} // prints out retrieved links
   	
   	boolean first = true;
   	
   	System.out.println("\nTarget page found! It took " + WikiPathFinal.process(links, targetTitle, first, 0) + " links to reach it!");
   
   	 
   	 
   	 
   	 
    } // end main

} // end class