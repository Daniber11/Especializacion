import edu.duke.*;

public class WordPlay {
    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        
        if (ch == 'i' || ch == 'e' || ch == 'a' || ch == 'o' || ch == 'u') {
           return true; 
        } 
        else {
            return false;
        }
    }
    
    public void testIsVowel() {
        System.out.println(isVowel('e')); // true
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        
        for (int i = 0, n = phrase.length(); i < n; i++) {
            char currentChar   = newPhrase.charAt(i);
            int  currCharIndex = phrase.indexOf(currentChar, i);
            
            if (isVowel(currentChar)) {    
                newPhrase.setCharAt(currCharIndex, ch);
            }
            
        }
        
        return newPhrase.toString();
    }
    
    public void testReplaceVowels() {
        System.out.println(replaceVowels("Hello World", '*')); 
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        
        for (int i = 0, n = phrase.length(); i < n; i++) {
            char currentChar   = newPhrase.charAt(i);
            int  currCharIndex = phrase.indexOf(currentChar, i);
            
            
            if (Character.toLowerCase(currentChar) == Character.toLowerCase(ch) && (currCharIndex+1) % 2 == 0) {    
                newPhrase.setCharAt(currCharIndex, '+');
            }
                        
            if (Character.toLowerCase(currentChar) == Character.toLowerCase(ch) && (currCharIndex+1) % 2 != 0) {
                newPhrase.setCharAt(currCharIndex, '*');
            }
            
        }
        
        return newPhrase.toString();
    }
    
     public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a')); 
        System.out.println(emphasize("Mary Bella Abracadabra", 'a')); 
    }
}
