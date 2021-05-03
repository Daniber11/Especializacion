import java.util.Random;
import java.util.ArrayList;

public class MarkovOne extends AbstractMarkovModel {
    public String getRandomText(int numChars){
    	if (myText == null){
    	    return "";
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	int index = myRandom.nextInt(myText.length() - 1);
    	String key = myText.substring(index, index + 1);
    	sb.append(key);
    	
    	for(int k=0; k < numChars - 1; k++){
    	    ArrayList<String> followList = getFollows(key);
    	    
    	    if (followList.size() == 0) {
    	        break;
    	    }
    	    
    	    index = myRandom.nextInt(followList.size());
    	    String next = followList.get(index);
    	    sb.append(next);
    	    key = next;
    	}
    	
    	return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order 1.";
    }
}
