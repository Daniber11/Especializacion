import edu.duke.*;
import java.io.*;
import java.lang.*;

public class CaesarBreaker {
    public int[] CountLetters(String message) {
        String alph  = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length();k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            
            if (dex!=-1) {
                counts[dex]++;
            }

        }
        
        return counts;
    }
    
    public int maxIndex(int[] values) {
        int max = 0;
        int indexOfMax = 0;
        
        for(int k = 0;k<values.length;k++) {
            if (values[k]> max) {
            max = values[k];
            indexOfMax = k;
            }
        
        }
        //System.out.println("The Array maxIndex is :"+ indexOfMax);
        return indexOfMax; 
    }
    
    public String decrypt(String encrypted, int Key) {
       CaesarCipher cc  = new CaesarCipher();
       String decrypted = cc.encrypt(encrypted, 26 - Key);
       return decrypted;    
    }
    
    public void testDecrypt() {
        int key = 23;
        FileResource fr  = new FileResource("wordsLotsOfEs.txt");
        String message   = fr.asString();
        CaesarCipher cc  = new CaesarCipher();
        String decrypted = cc.encrypt(message, 26 - key);
        System.out.println("la llave es"+ key+"\n"+decrypted);    
    }
    
    public String halfOfString(String message, int start){
        String result = new String();
        
        for (int k = start; k< message.length();k = k+2) {
          result = result + message.charAt(k);
        }
        
        return result;
    }
    
    public void halfOfStringTest() {
        FileResource resource = new FileResource("data/wordsLotsOfEs.txt");
        String message = resource.asString();
        System.out.println(message);
        System.out.println("half of String starts with 0"+ halfOfString(message, 0));
        System.out.println("half of String starts with 1"+ halfOfString(message, 1));
       
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(halfOfString(message, 0), 20);
        String decrypted = decrypt(encrypted,26 - 20);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
    
    public int getKey(String s) {
        int[] freqs = CountLetters(s);
        int maxDex  = maxIndex(freqs);
        int dKey    = maxDex - 4;
       
        if (maxDex < 4) {
            dKey = 26 - (4 -maxDex);
        } 
       
        return dKey;        
    }

    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String message1 = halfOfString(encrypted,0);
        String message2 = halfOfString(encrypted,1);
        StringBuilder decrypt_two = new StringBuilder(encrypted);
        int key1 = getKey(message1);
        int key2 = getKey(message2);       
        String encrypt_message1 = cc.encrypt(message1,(26-key1));
        String encrypt_message2 = cc.encrypt(message2,(26-key2));

        for (int k=0; k<(message1.length());k++) {
            decrypt_two.setCharAt((2*k), encrypt_message1.charAt(k) );
        }
           
        for (int k=0; k<(message2.length());k++) {
            decrypt_two.setCharAt((2*k)+1, encrypt_message2.charAt(k) );
        }
           
        System.out.println("La primer llave es: "+key1+"\n"+"La segunda llave es: "+ key2); 
        System.out.println("El mensaje desemcriptado con dos llaves es:"+"\n" + decrypt_two.toString());
        return decrypt_two.toString(); 
    }
    
     public void decryptTwoKeysTest() {
        FileResource resource = new FileResource("data/Question 8");
        String message = resource.asString();
       
        //String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        String decrypted_message = decryptTwoKeys(message); 
        System.out.println(message);
        System.out.println(decrypted_message);
    }
}
