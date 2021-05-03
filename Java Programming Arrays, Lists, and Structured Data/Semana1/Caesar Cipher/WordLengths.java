import edu.duke.*;
import java.util.*;

public class WordLengths {
    public void countWordLengths(FileResource Resource, int[] counts) { 
  
        for (String word : Resource.words()){
            int Wordlength = word.length();
            
            for (int i=0; i<word.length();i++){
                char currChar = word.charAt(i);
                
                if ((i==0) || (i==word.length()-1)){
                    if (!Character.isLetter(currChar)) Wordlength--;
                }
                
            }
            
            counts[Wordlength]++;   
            System.out.println("La longitud de la palabra es; "+ Wordlength +" "+ word);
        }
        
    }
    
    public void indexOfMax(int[] values) {
        int max = 0;
        int position = 0;
        
        for (int i = 0; i <values.length;i++) {               
            if (values[i] > max) {
                max = values[i];
                position = i;
            } 
            
        }
        
        System.out.println("La palabra con la longitud mas comun es :"+ position); 
    }
   
    public void testCountWordLengths(){
        FileResource Resource = new FileResource("manywords.txt.");
        int [] counts = new int[31];
        countWordLengths(Resource,counts);     
        indexOfMax(counts);    
    }             
}   

