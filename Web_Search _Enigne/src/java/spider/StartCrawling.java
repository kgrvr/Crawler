package spider;

public class StartCrawling {
    
    public static void main(String[] a) {
        
        Spider spider = new Spider();
        spider.crawlAndSearch("http://google.com/", null, 100);
    
    }
    
}
