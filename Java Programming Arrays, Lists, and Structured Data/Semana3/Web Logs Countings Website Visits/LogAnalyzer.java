import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
     private ArrayList<LogEntry> records;
     private ArrayList<String> maxDate;
     private ArrayList<String> maxIPs;
     private ArrayList<Integer> myFreqs;
     WebLogParser WebLogParser = new WebLogParser(); 
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
         maxDate = new ArrayList<String>();
         maxIPs  = new ArrayList<String>();
         myFreqs = new ArrayList<Integer>();
     }
      
     // Complete el método readFile para crear un FileResource y
     // iterar sobre todas las líneas, crear un LogEntry y almacenarlo en el
     // registra ArrayList.
     public void readFile(String filename) {
         FileResource resource = new FileResource();
         for (String line: resource.lines()){
             WebLogParser.parseEntry(line);
             records.add(WebLogParser.parseEntry(line));
         }                        
     }
     
     public int countUniqueIPs() {
         // UniqueIPs comienza como una lista vacía
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         // para cada archivo de elemento en registros
         for (LogEntry le: records) {
             // ipAddr es la dirección ip del archivo
             String ipAddr = le.getIpAddress();
             // si ipAddr no está en uniqueIPs
             if (!uniqueIPs.contains(ipAddr)) {
                 // agregar ipAddr a uniqueIps
                 uniqueIPs.add(ipAddr);
             }
         }
         // devolver el tamaño de UniqueIPs
         return uniqueIPs.size();
     }
        
     public void printAllHigherThanNum(int Num) {          
         for (LogEntry le: records) {
             // Código de estado en LogEntrys
             int statusCode = le.getStatusCode();
             //si StatusCode mayor que Num
             if (statusCode > Num) {
                 // imprimir StatusCode
                 System.out.println("StatusCode mayor que "+Num+": "+statusCode);
             }
         }           
     }   
     
     // Este método accede a los registros web en los registros y devuelve un ArrayList
     // de cadenas de direcciones IP únicas que tuvieron acceso en un día determinado.
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> myIPs = new ArrayList<String>();
         String myString = null;
            for (LogEntry le: records) {
             Date d = le.getAccessTime();
             String ipAddr = le.getIpAddress();
             myString = d.toString();
             int index = myIPs.indexOf(ipAddr);
             
             if ((myString.contains(someday)) && (!myIPs.contains(ipAddr))){
                 myIPs.add(ipAddr);
                 myFreqs.add(1);
             }         
             for (int k =0; k < myIPs.size();k++) {
                 //System.out.println(myIPs.get(k)+"\t"); 
             }         
             // System.out.println("Tamaño de la matriz: "+myIPs.size());   
         }
         return myIPs; 
     } 
     
     public int countUniqueIPsInRange(int low, int high){
         // uniqueIPs comienza como una lista vacía
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry New: records) {
             // Código de estado en LogEntrys
             int statusCode = New.getStatusCode();
             String ipAddr = New.getIpAddress();
             // si StatusCode mayor que Num  
             if ((statusCode >= low) && (statusCode <= high)) {
                 if (!uniqueIPs.contains(ipAddr)) {
                     // agregar ipAddr a uniqueIps
                     uniqueIPs.add(ipAddr);
                }
             }
         }
         return uniqueIPs.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP() {
         // Hacer un recuento de HashMap <String, Integer> vacío
         HashMap<String,Integer> counts = new HashMap<String, Integer>();
         // Para cada archivo en registros
         for (LogEntry le: records) {
             // ip es la dirección ip del archivo
             String ip = le.getIpAddress();
             // Compruebe si ip está en recuentos
             if (!counts.containsKey(ip)) {
                 // Si no, ponga ip con un valor de 1
                 counts.put(ip,1);
             }
             // Si es así; actualice el valor para que sea 1 más
             else {
                 counts.put(ip,counts.get(ip) + 1);
             }
         }
         // cuenta es la respuesta
         return counts;
     }
      
      // En la clase Log Analyzer, escriba el método iPsMostVisits que tiene uno
      // parámetro, un HashMap <String, Integer> que asigna una dirección IP al número de
      // veces que esa dirección IP aparece en el archivo de registro web. Este método devuelve un
      // ArrayList de cadenas de direcciones IP que tienen el número máximo de visitas
      // a este sitio web. Por ejemplo, la llamada iPsMostVisits en un HashMap formado usando
      // el archivo weblog3-short_log devuelve ArrayList con estas dos direcciones IP,
      // 61.15.121.171 y 84.133.195.161. Ambos visitaron el sitio tres veces, lo que
      // es el número máximo de veces que una dirección IP visitó el sitio.      
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> addressNumberTime){   
         ArrayList<String> maxIps = new ArrayList<String>();    
         int greatest;
         greatest = mostNumberVisitsByIP(addressNumberTime);
         for (String s: addressNumberTime.keySet()) {
             if (addressNumberTime.get(s) == greatest) {
                 maxIps.add(s);
             }
         }
    
         return maxIps;
     }
    
      // En la clase LogAnalyser, escriba el método iPsForDays que no tiene parámetros. Esta
      // El método devuelve un HashMap <String, ArrayList <String>> que usa registros y mapas de días
      // de los registros web a una ArrayList de direcciones IP que ocurrieron ese día.
      // formato "MMM DD" donde MMM son los primeros tres caracteres del nombre del mes con el
      // primera letra en mayúscula y las demás en minúsculas, y DD es el día en dos dígitos.
      // Por ejemplo, para el archivo weblog3-short_log, después de construir este HashMap, si
      // imprímalo, verá que el 14 de septiembre se asigna a una dirección IP, el 21 de septiembre se asigna a cuatro
      // Direcciones IP y mapas del 30 de septiembre a cinco direcciones IP.     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> dayIpThatDay = new HashMap<String,ArrayList<String>>();
         ArrayList<String> myIPs = new ArrayList<String>();
         String myString = null;
         for (LogEntry le: records) {
             Date d = le.getAccessTime();
             String ipAddr = le.getIpAddress();
             myString = d.toString();
             myIPs = uniqueIPVisitsOnDay(myString);
             dayIpThatDay.put(myString,myIPs);  
         }
         return dayIpThatDay;
     }
    
     public int findMax(){
         int theMax = myFreqs.get(0);
         int maxIndex = 0;
         for(int k=0; k < myFreqs.size(); k++){
             if (myFreqs.get(k) > theMax){
                 theMax = myFreqs.get(k);
                 maxIndex = k;
             }
         }
         return maxIndex;
     }  
   
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> dayIPs){
         String date;  
         String maxKey_date= null;
         for (String s : dayIPs.keySet()) {
             int index = maxDate.indexOf(s);
             if (index == -1) {
                 maxDate.add(s);
                 myFreqs.add(1);
             }
             else {
                 int freq = myFreqs.get(index);
                 myFreqs.set(index,freq+1);
             }
         }
       
         int max = findMax();
         System.out.println("fecha máxima: "+ maxDate.get(max)+"frecuencia máxima:  "+ myFreqs.get(max));
         //myFreqs.clear();
         return maxDate.get(max);
     }
    
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> dayandIPs, String aDay){
         myFreqs.clear();
         ArrayList<String> mostIPs = new ArrayList<String>();
         mostIPs = uniqueIPVisitsOnDay(aDay);
         HashMap<String,Integer> counts = new HashMap<String,Integer>();      
        
         for (int k=0;k<mostIPs.size();k++) {
             String s = mostIPs.get(k) ; 
             if (!counts.containsKey(s)) {
                 counts.put(s,1);
             }
             else {
                 int freq = counts.get(s);
                 counts.put(s,freq+1);
             }
         }
          return iPsMostVisits(counts);
     }
    
     public int mostNumberVisitsByIP(HashMap<String,Integer> myCounts){ 
         int max = 0;
         for (String s: myCounts.keySet()){
             int currentNum = myCounts.get(s);
             if (currentNum > max) {
                 max = currentNum;
             }
         }
         return max; 
     }
     
     public void printAll() {
         for(LogEntry le : records) {
             System.out.println(le);
         }
     }         
}