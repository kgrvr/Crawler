package spider;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider extends Thread {
    
    private static int MAX_PAGES_TO_CRAWL = 10;
    private Set<String> pagesVisited = new HashSet<String>(); //Why is pagesVisited a Set? Remember that a set, by definition, contains unique entries. In other words, no duplicates. All the pages we visit will be unique (or at least their URL will be unique). We can enforce this idea by choosing the right data structure, in this case a set.
    private List<String> pagesToVisit = new LinkedList<String>(); //Why is pagesToVisit a List? This is just storing a bunch of URLs we have to visit next. When the crawler visits a page it collects all the URLs on that page and we just append them to this list. Recall that Lists have special methods that Sets ordinarily do not, such as adding an entry to the end of a list or adding an entry to the beginning of a list. Every time our crawler visits a webpage, we want to collect all the URLs on that page and add them to the end of our big list of pages to visit. Is this necessary? No. But it makes our crawler a little more consistent, in that it'll always crawl sites in a breadth-first approach (as opposed to a depth-first approach).
    private static String returnResponse;
    
    public Spider() {}
    
    /**
   * Our main launching point for the Spider's functionality. Internally it creates spider legs
   * that make an HTTP request and parse the response (the web page).
   * 
   * @param url
   *            - The starting point of the spider
   * @param searchWord
   *            - The word or string that you are searching for. Pass null if you don't want to search any word.
   * @param MAX_PAGES_TO_CRAWL 
   *            - Changes maximum pages to crawl by spider. Default is 10.
   */
    public String crawlAndSearch(String url, String searchWord, int MAX_PAGES_TO_CRAWL) {
        
        this.MAX_PAGES_TO_CRAWL = MAX_PAGES_TO_CRAWL;
        return crawlAndSearch(url, searchWord);
        
    }
    
    /**
   * Our main launching point for the Spider's functionality. Internally it creates spider legs
   * that make an HTTP request and parse the response (the web page).
   * 
   * @param url
   *            - The starting point of the spider
   * @param searchWord
   *            - The word or string that you are searching for. Pass null if you don't want to search any word.
   */
    public String crawlAndSearch(String url, String searchWord) {
        
        returnResponse = "";
        int count = 0;
        while(count < MAX_PAGES_TO_CRAWL) {
            count++;
            String currentUrl;
            SpiderLeg leg = new SpiderLeg();
            if(this.pagesToVisit.isEmpty()) {
                currentUrl = url;
                this.pagesVisited.add(url);
            }
            else {
                currentUrl = this.nextURL();
            }
            returnResponse += "\n <br><br>" + leg.crawl(currentUrl); 
            if(searchWord != null) {
                boolean success = leg.searchForWord(searchWord);
                if(success)
                {
                    System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
                    returnResponse += String.format("<br><br> **Success** Word \"%s\" found at <a href=\"%s\"> %s </a>", searchWord, currentUrl, currentUrl);
                    break;
                }
            }
            this.pagesToVisit.addAll(leg.getLinks());
        }
        returnResponse += String.format("\n <br><br> **Done** Visited %s web page(s)", this.pagesVisited.size());
        return returnResponse;
        
    }
    
    /**
   * Returns the next URL to visit (in the order that they were found). We also do a check to make
   * sure this method doesn't return a URL that has already been visited.
   * 
   * @return  
   */
    public String nextURL() {
        
        String nextURL;
        do {
            nextURL = this.pagesToVisit.remove(0);
        } while(this.pagesToVisit.contains(nextURL));
        this.pagesVisited.add(nextURL);
        return nextURL;
    
    }
    
}
