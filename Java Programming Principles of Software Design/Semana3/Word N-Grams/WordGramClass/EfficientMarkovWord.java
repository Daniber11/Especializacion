package WordGramClass;
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> wordGramMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        this.myOrder = order;
        wordGramMap = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i < words.length - myOrder; i++) {
            WordGram curr = new WordGram(words, i, myOrder);
            if (curr.equals(target)) {
                return i;
            }
        }
        
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
       return wordGramMap.get(kGram);
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private void buildMap() {
        this.wordGramMap.clear();
        
        for (int i = 0; i <= myText.length - myOrder; i++) {
            WordGram key = new WordGram(myText, i, myOrder);
            
            if (i == myText.length - myOrder) {
                wordGramMap.put(key, new ArrayList<String>());
                continue;
            }
            
            ArrayList<String> follows;
            
            if (!wordGramMap.containsKey(key)) {
                follows = new ArrayList<String>();
            } else {
                follows = wordGramMap.get(key);
            }
            
            follows.add(myText[i + myOrder]);
            wordGramMap.put(key, follows);
        }
    }
    
    private int getLargestValue() {
        int max = Integer.MIN_VALUE;
        
        for (WordGram key : wordGramMap.keySet()) {
            if (wordGramMap.get(key).size() > max) {
                max = wordGramMap.get(key).size();
            }
        }
        
        return max;
    }
    
    private ArrayList<WordGram> getLargestKey() {
        int max = getLargestValue();
        ArrayList<WordGram> keyList = new ArrayList<WordGram>();
        
        for (WordGram key : wordGramMap.keySet()) {
            if (wordGramMap.get(key).size() == max) {
                keyList.add(key);
            }
        }
        
        return keyList;
    }

    public void printHashMapInfo() {
        for (WordGram key : wordGramMap.keySet()) {
            System.out.println(key + " - " + wordGramMap.get(key));
        }
        
        ArrayList<WordGram> largestKeyList = getLargestKey();
        
        System.out.println("# of keys = " + wordGramMap.size());
        System.out.println("largest value = " + wordGramMap.get(largestKeyList.get(0)).size());
        for (WordGram key : largestKeyList) {
            System.out.println("largest key = " + key);
        }
        
    }
}
