import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);     
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";         

        for(int i = 0, n = input.length(); i < n; i++) {
            char currChar = input.charAt(i);                    
            int  currCharIndex = alphabet.indexOf(Character.toUpperCase(currChar));      
            
            if (currCharIndex != -1) {  
                if (Character.isUpperCase(currChar)) {   
                    String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";                
                    String alphabet_shiftedUpper = alphabetUpper.substring(key);        
                    alphabet_shiftedUpper += alphabetUpper.substring(0, key);           
                    
                    char charShifted = alphabet_shiftedUpper.charAt(currCharIndex);     
                    encrypted.setCharAt(i, charShifted);    
                }

                if(Character.isLowerCase(currChar)) {   
                    String alphabetLower = "abcdefghijklmnopqrstuvwxyz";                
                    String alphabet_shiftedLower = alphabetLower.substring(key);        
                    alphabet_shiftedLower += alphabetLower.substring(0, key);           

                    char charShifted = alphabet_shiftedLower.charAt(currCharIndex);     
                    encrypted.setCharAt(i, charShifted);    
                }
                
            }
            
        }
        
        return encrypted.toString();
    }
    
    public void testEncrypted() {
	//FileResource fr  = new FileResource();
	//String message   = fr.asString();
	String encrypted = encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15);
	System.out.println(encrypted);
    }
    
    public char encryptChar(char input, int key) {
	String alphabet = "abcdefghijklmnopqrstuvwxyz";

	if (Character.isUpperCase(input)) {
	    String shifted = alphabet.toUpperCase().substring(key);
	    shifted += alphabet.toUpperCase().substring(0, key);

	    int  currIndex = alphabet.toUpperCase().indexOf(input);
	    char encrypted = shifted.charAt(currIndex);
	    return encrypted;
	}

	if (Character.isLowerCase(input)) {
	String shifted = alphabet.toLowerCase().substring(key);
	shifted += alphabet.toLowerCase().substring(0, key);

	int  currIndex = alphabet.toLowerCase().indexOf(input);
	char encrypted = shifted.charAt(currIndex);
	return encrypted;	
	}

	return '\0';
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        
	for (int i = 0, n = input.length(); i < n; i++) {
	    char currChar = input.charAt(i);
	    
	    if ((i + 1) % 2 == 0) {	
		char encrChar = encryptChar(currChar, key2);
		encrypted.setCharAt(i, encrChar);
	    }

	    if((i + 1) % 2 != 0) {	
		char encrChar = encryptChar(currChar, key1);
		encrypted.setCharAt(i, encrChar);	
	    }
	}
	
	return encrypted.toString();
    }
    
    public void testTwoKey() {
	String encrypted = encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8);
	System.out.println(encrypted);
    }
}
