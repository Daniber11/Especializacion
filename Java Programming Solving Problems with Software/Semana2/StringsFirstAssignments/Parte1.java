package StringsFirstAssignments;

public class Parte1 {
     public String findSimpleGene(String dna) {
        String result = "";
        int start = dna.indexOf("ATG");
        
        if (start == -1) {
            return "No contiene el codón ATG";
        }
        
        int stop = dna.indexOf("TAA", start);
        
        if (stop == -1) {
            return "No contiene el codón TAA";
        }
        
        if ((stop - start) % 3 == 0) {
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
        String c5 = "GCAGCATGGCATTCGCTAACAC";
                	
	System.out.println("El string es: " + c1 + ". El gen es: " + findSimpleGene(c1));
	System.out.println("El string es: " + c2 + ". El gen es: " + findSimpleGene(c2));
	System.out.println("El string es: " + c3 + ". El gen es: " + findSimpleGene(c3));
	System.out.println("El string es: " + c4 + ". El gen es: " + findSimpleGene(c4));
	System.out.println("El string es: " + c5 + ". El gen es: " + findSimpleGene(c5));    
    }
    
    public static void main (String[] args) {
        Parte1 gene = new Parte1();
        gene.testSimpleGene();
    }
}


