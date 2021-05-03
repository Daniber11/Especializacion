import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> DNA_count;
    
    public CodonCount() {
        DNA_count = new HashMap<String, Integer>();
    }
 
    public void buildCodonMap(int start, String dna) {
        DNA_count.clear();
        int k = 0;   //numbero de codones
        int num = 0; //numbero de iteraciones
        k = (dna.length()-start)/3;
        String current;
        while (num <= k-1) {
            current = dna.substring(num*3+start, num*3+start+3);
            if (!DNA_count.containsKey(current))
            {DNA_count.put(current, 1);}
            else 
            {DNA_count.put(current, DNA_count.get(current)+1);}
            num = num + 1;
        }
        
    }
        
    public String getMostCommonCodon() {
        int largest = 0;
        int current = 0;
        String largest_count = null;
        for (String index : DNA_count.keySet()) {
            current = DNA_count.get(index);
            if (largest < current) {
                largest = current;
                largest_count = index;
            }
        }
        return largest_count;
    }
    
    public void printCodonCounts(int start, int end) {
        int current = 0;
        for (String index : DNA_count.keySet()) {
            current = DNA_count.get(index);
            if (current >= start && current <= end) 
                System.out.println(index+": "+current+"\t");
        }
        
    }
    
    public void Test() {
       //String dna = "CGTTCAAGTTCAA";
       FileResource DNA = new FileResource("dnaMystery2");
       String dna = DNA.asString();
       int start = 1;
       int end = 7;
        
       buildCodonMap(0, dna);
       System.out.println("El marco de lectura que comienza con 0 da como resultado "+DNA_count.size()+" codones únicos"+"\t");
       String the_largest_count = getMostCommonCodon();
       System.out.println("y el codón más común es "+the_largest_count+" con la cuenta "+DNA_count.get(the_largest_count)+"\t");  
       System.out.println("Recuentos de codones entre "+start+" y "+end+" inclusive son:"+"\t");
       printCodonCounts(start, end);
        
       buildCodonMap(1, dna);
       System.out.println("El marco de lectura que comienza con 1 da como resultado "+DNA_count.size()+" codones únicos"+"\t");
       the_largest_count = getMostCommonCodon();
       System.out.println("y el codón más común es "+the_largest_count+" con la cuenta "+DNA_count.get(the_largest_count)+"\t");  
       System.out.println("Recuentos de codones entre "+start+" y "+end+" inclusive son:"+"\t");
       printCodonCounts(start, end);
        
       buildCodonMap(2, dna);
       System.out.println("El marco de lectura que comienza con 2 da como resultado "+DNA_count.size()+" codones únicos"+"\t");
       the_largest_count = getMostCommonCodon();
       System.out.println("y el codón más común es "+the_largest_count+" con la cuenta "+DNA_count.get(the_largest_count)+"\t");  
       System.out.println("Recuentos de codones entre "+start+" y "+end+" inclusive son:"+"\t");
       printCodonCounts(start, end);
    }
}