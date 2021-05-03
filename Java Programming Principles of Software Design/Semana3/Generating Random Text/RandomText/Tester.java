import edu.duke.*;
import java.util.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> followList = markov.getFollows(".");
        System.out.println("size = " + followList.size());
        System.out.println(followList);
    }
    
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String text = fr.asString();
        text = text.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(text);
        ArrayList<String> followList = markov.getFollows("th");
        System.out.println("size = " + followList.size());
        System.out.println(followList);
    }
}
