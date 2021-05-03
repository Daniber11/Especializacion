package StringsFirstAssignments;

public class Parte4 {
     public void printUrls(String url) {
        UrlResource myurl = new UrlResource(url);
        
        for(String word : myurl.words()) {
            
            if (word.toLowerCase().indexOf("youtube.com") != -1) {
                int quoteIndex = word.indexOf("\"");
                int lastQouteIndex = word.indexOf("\"", quoteIndex+1);
                System.out.println(word.substring(quoteIndex+1, lastQouteIndex));
                
            }
            
        }
        
    }
    
    public void testUrl() {
        printUrls("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
    
    public static void main() {
        Parte4 url = new Parte4();
        url.testUrl();
    }
}
