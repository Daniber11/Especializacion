import java.util.*;
import edu.duke.*;
import java.lang.*;
import java.io.*;
 
public class VigenereCipherTest {
    
    public void testCaesarCipher() {
        CaesarCipher se = new CaesarCipher(3);
        FileResource fr = new FileResource();
        String str = fr.asString();
        System.out.println(str);
        System.out.println(se.encrypt(str));
        System.out.println(se.decrypt(se.encrypt(str)));
    }
    
    public void testCaesarCracker() {
        CaesarCracker cc = new CaesarCracker();
        FileResource fr = new FileResource();
        String str = fr.asString();
        System.out.println(cc.decrypt(str));
    }
    
    public void testVigenereCipher() {
        int[] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource();
        String str = fr.asString();
        System.out.println(vc.encrypt(str));
    }
    
    public void testVigenereBreaker() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String str = fr.asString();
        int[] key = vb.tryKeyLength(str, 4, 'e');
        for (int k = 0; k < key.length; k++) {
            System.out.println(key[k]);
        }
    }
    
    public void testVigenereBreaker2() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere2();
    }
    
    public void testVigenereBreaker3() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere3();
    }
    
    public void TestCountWords(){
        int count = 0;
        FileResource fr = new FileResource();
        String message = fr.asString();
        HashSet<String> DictContent = new HashSet<String>();
        VigenereBreaker VBR = new VigenereBreaker();
        FileResource dictResource = new FileResource("English");
        DictContent = VBR.readDictionary(dictResource);
        count = VBR.countWords(message, DictContent);
        System.out.println("Number of words counted "+ count);
        
    }
    
     public void TestTryKeyLength(){
        VigenereBreaker VB = new VigenereBreaker();
        FileResource fr = new FileResource("secretmessage2.txt");
        String message  = fr.asString();
        int keyReturn[] = VB.tryKeyLength(message,4,'e');
        for (int i =0 ;i < keyReturn.length;i+=1){
            System.out.println("Return Keys "+ keyReturn[i] + "\t");
        }
    }
}
