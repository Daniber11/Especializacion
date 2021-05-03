import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    // Creacion de variables instanciadas 
    private ArrayList<String>  character_name;
    private ArrayList<Integer> count;
    
    // Creacion de constructor e inicializacion de variables
    public CharactersInPlay() {
        character_name = new ArrayList<String>();
        count = new ArrayList<Integer>();
    }
    
    // Actualizacion de las variables
    public void update(String person){        
        person    = person.toLowerCase();
        int index = character_name.indexOf(person);
        
        if (index == -1) {
            character_name.add(person);
            count.add(1);
        }        
        else {
            int freq = count.get(index);
            count.set(index,freq+1); 
        }
        
    }
    
    // Extrae el posible nombre de la aprte hablante
    public void findAllCharacters() {
        character_name.clear();
        count.clear();        
        FileResource Resource = new FileResource("errors.txt");
        
        for (String line: Resource.lines()){
                       
            if (line.contains(".")) {               
                int idx = line.indexOf(".");
                String possible_name = line.substring(0,idx);
                update(possible_name);
            
            }            
            
        }
                                              
    }
            
    public void tester() {
        findAllCharacters();
        
        for (int k =0; k < count.size();k++) {
           
            if (count.get(k) > 1) {
            
                System.out.println("El personaje principal es: "+ character_name.get(k) +"\t"
                +"El numero de partes hablandas es: "+ count.get(k));
             
            }
            
        }
            
        int num1 = 10;
        int num2 = 15;
        charactersWithNumParts(num1, num2);    
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        
        for (int k =0; k < count.size();k++) {
           
            if (count.get(k) >= num1 && count.get(k)<= num2) {
            
                System.out.println("El personaje principal entre: " + num1 + " y " + num2 
                + " es " + character_name.get(k) +"\t"
                +"El numero de partes hablandas es: "+ count.get(k));
             
            }
            
        }
    
    }   
}