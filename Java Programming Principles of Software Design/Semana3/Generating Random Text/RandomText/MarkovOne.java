import java.util.Random;
import java.util.ArrayList;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
    	myRandom = new Random();
    }
    
    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
    	myText = s.trim();
    }
    
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
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> result = new ArrayList<String>();
        int startPos = 0;
        
        while(true) {
            int index = myText.indexOf(key, startPos);
            
            if (index == -1 || index == myText.length() - key.length()) {
                break;
            }
            
            result.add(myText.substring(index + key.length(), index + key.length() + 1));
            startPos = index + key.length();
        }
        
        return result;
    }
}
