import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    // Variables Instanciadas
    private ArrayList<String>  myWords;
    private ArrayList<Integer> myFreqs;
    
    // Constructor e inicializacion de variables
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    // Recuento de cuantas veces aparece una palabra
    public void findUnique() { 
        myWords.clear();
        myFreqs.clear();
        FileResource Resource = new FileResource("errors.txt");
        
        for (String s: Resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1); 
            }
            
        }
        
    }
    
    public void tester() {
        findUnique();
               
        for ( int k =0; k < myWords.size();k++){
            System.out.println(myFreqs.get(k)+ " " + myWords.get(k));
            
            if (myFreqs.get(k) > 3){
                System.out.println(myWords.get(k));
            }           
                
        }
        
        int maxIndex = findIndexOfMax();
        System.out.println("La palabra que aparece con mas frecuencia y su recuento son: "
        + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
        System.out.println("# unico de palabras: " + myWords.size());
    }
        
    // Devuelve la ubicacion del indice de valor mas grande en mi frecuencia 
    public int findIndexOfMax() {
        int max = myFreqs.get(0);
        int maxIndex = 0;        
        
        for (int k=0; k < myFreqs.size(); k++) {
            if (myFreqs.get(k) >max) {
                max = myFreqs.get(k);
                maxIndex = k;
            }
            
        }
        
        return maxIndex;
    }

}
