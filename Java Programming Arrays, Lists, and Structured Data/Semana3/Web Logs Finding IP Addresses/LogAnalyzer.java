import java.util.*;
import edu.duke.*;
import java.text.*;

public class LogAnalyzer {
     private ArrayList<LogEntry> records;
     WebLogParser WebLogParser = new WebLogParser(); 
         
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
          
     public void readFile(String filename) {
         FileResource resource = new FileResource();
         for (String line: resource.lines()){
             WebLogParser.parseEntry(line);
             records.add(WebLogParser.parseEntry(line));
         }                        
     }
     
     public int countUniqueIPs() {
         // uniqueIPs starts as an empty list
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         // for each element le in records
         for (LogEntry le: records) {
             // ipAddr is le's ipAddress
             String ipAddr = le.getIpAddress();
             //if ipAddr is not in uniqueIPs
             if (!uniqueIPs.contains(ipAddr)) {
                 //add ipAddr to uniqueIps
                 uniqueIPs.add(ipAddr);
             }
         }        
         // return the size of UniqueIPs
         return uniqueIPs.size();
     }
        
     public void printAllHigherThanNum(int Num) {         
         for (LogEntry le: records) {
             // Status code in LogEntrys
             int statusCode = le.getStatusCode();
             //if StatusCode greater than Num
             if(statusCode > Num) {
                 //print StatusCode
                 System.out.println("StatusCode mayor que "+Num+": "+statusCode);
             }
         }           
     }   
     
     // Este método accede a los registros web en los registros y devuelve un ArrayList
     // de cadenas de direcciones IP únicas que tuvieron acceso en un día determinado.
     public void uniqueIPVisitsOnDay(String someday) { 
         ArrayList<String> myIPs = new ArrayList<String>();
         //ArrayList<String> myDate = new ArrayList<String>();
         String myString = null;
         String kept = null;
         
         //ArrayList<LogEntry> myDate = new ArrayList<LogEntry>();
         for (LogEntry le: records) {
             //DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
             Date d = le.getAccessTime();
             String ipAddr = le.getIpAddress();
             //myString = df.format(d);
             myString = d.toString();
             //kept = myString.substring(0, myString.indexOf(","));
             
             if((myString.contains(someday)) && (!myIPs.contains(ipAddr))){
                myIPs.add(ipAddr);
                }
                
             /*
             if(kept.contains(someday)) {
                  myDate.add(le);
                  for(LogEntry ip: myDate) {
                      String ipAddr = ip.getIpAddress();
                      if(!myIPs.contains(ipAddr)) {
                          myIPs.add(ipAddr);
                      }
                  }
             */
         }
         
         for (int k =0; k < myIPs.size();k++){
             System.out.println(myIPs.get(k)+"\t"); 
         }
         System.out.println("Tamaño de la matriz: "+myIPs.size());           
     }
         
     
     public int countUniqueIPsInRange(int low, int high){
         // uniqueIPs starts as an empty list
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry New: records) {
             // Status code in LogEntrys
             int statusCode = New.getStatusCode();
             String ipAddr = New.getIpAddress();
             //if StatusCode greater than Num
             if((statusCode >= low) && (statusCode <= high)) {
                 if(!uniqueIPs.contains(ipAddr)) {
                     //add ipAddr to uniqueIps
                     uniqueIPs.add(ipAddr);
                 }
             }
         }
         return uniqueIPs.size();
     }
        
     public void printAll() {
         for(LogEntry le : records) {
             System.out.println(le);
         }
     }       
}
