package StringsFirstAssignments;

public class Parte2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String result = "";
        
        if( Character.isUpperCase(dna.charAt(0)) ) {
            startCodon = startCodon.toUpperCase();
            stopCodon  = stopCodon.toUpperCase();
        } else {
            startCodon = startCodon.toLowerCase();
            stopCodon  = stopCodon.toLowerCase();
        }
        
        int start = dna.indexOf(startCodon);
        
        if (start == -1) {
            return "No contiene el codón ATG";
        }
        
        int stop = dna.indexOf(stopCodon, start);
        
        if (stop == -1) {
            return "No contiene el codón TAA";
        }
               
        if((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "No es multiplo de 3";
        }
        
    }
    
    public void testSimpleGene() {
        String c1 = "CTAATCCGGATCCGA";
        String c2 = "CGTATGAAGGGATTC";
        String c3 = "GGCGCTTGCCAGTCAGCTAACAA";
        String c4 = "ACATGCCCTAACTAGATTTAGAACCG";
        String c5 = "acatgccctaactagatttagaaccg";
        String c6 = "gcagcatggattcgctaacac";
                	
	System.out.println("El string es: " + c1 + ". El gen es: " + findSimpleGene(c1, "ATG", "TAA"));
	System.out.println("El string es: " + c2 + ". El gen es: " + findSimpleGene(c2, "ATG", "TAA"));
	System.out.println("El string es: " + c3 + ". El gen es: " + findSimpleGene(c3, "ATG", "TAA"));
	System.out.println("El string es: " + c4 + ". El gen es: " + findSimpleGene(c4, "ATG", "TAA"));
	System.out.println("El string es: " + c5 + ". El gen es: " + findSimpleGene(c5, "ATG", "TAA"));
	System.out.println("El string es: " + c6 + ". El gen es: " + findSimpleGene(c6, "ATG", "TAA")); 
        }
    
    public static void main (String[] args) {
        Parte2 gene = new Parte2();
        gene.testSimpleGene();
    }
}


