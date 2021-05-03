import java.util.*;

public class CountTester {
    private ArrayList<String> maximumIPs;
    private ArrayList<String> mostAccessesDay;
    
    public CountTester(){
        maximumIPs = new ArrayList<String>();
        mostAccessesDay = new ArrayList<String>();
    }
    
    public HashMap<String,Integer> testCounts() {   
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        return counts;
    }
    
    public void testmostNumberVisitsByIP() {
        LogAnalyzer le = new LogAnalyzer();
        //le.readFile("weblog3-short_log");
        //le.readFile("weblog1_log");
        le.readFile("weblog2_log");
        int max_value = le.mostNumberVisitsByIP(testCounts());
        System.out.println("valor máximo en el HashMap "+ max_value);
    }
    
    public void testiPsMostVisits() {
        LogAnalyzer Sol = new LogAnalyzer();
        //Sol.readFile("weblog3-short_log");
        //Sol.readFile("weblog1_log");
        Sol.readFile("weblog2_log");
        HashMap<String,Integer> myCounts = Sol.countVisitsPerIP();
        maximumIPs = Sol.iPsMostVisits(myCounts);
        for (int k=0;k<maximumIPs.size();k++) {
            System.out.println("Direcciones IP que tienen el número máximo de visitas. "+ maximumIPs.get(k));
        }
    }
    
    public void testiPsForDays() {
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map_day_ipaddress = LA.iPsForDays(); 
        for (String s: map_day_ipaddress.keySet()) {
            System.out.println(s+ "maps to "+"\t"+map_day_ipaddress.get(s));
        }
    }
     
    public void testdayWithMostIPVisits() {
        String dayMostIP;
        LogAnalyzer LogA = new LogAnalyzer();
        //LogA.readFile("weblog3-short_log");
        //LogA.readFile("weblog1_log");
        LogA.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map_day_ipaddress = LogA.iPsForDays(); 
        dayMostIP = LogA.dayWithMostIPVisits(map_day_ipaddress);
        System.out.println("El día que tiene más direcciones IP"+ dayMostIP);
    }
    
    public void testiPsWithMostVisitsOnDay() {
        LogAnalyzer myLog = new LogAnalyzer();
        //myLog.readFile("weblog3-short_log");
        //myLog.readFile("weblog1_log");
        myLog.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> day_and_ipaddress = myLog.iPsForDays();
        //mostAccessesDay = myLog.iPsWithMostVisitsOnDay(day_and_ipaddress,"Sep 30");
        mostAccessesDay = myLog.iPsWithMostVisitsOnDay(day_and_ipaddress,"Sep 30");      
        for (int k=0;k<mostAccessesDay.size();k++) {
            System.out.println("Direcciones IP que tienen el número máximo de visitas el 29 de septiembre "+ mostAccessesDay.get(k));
        }
    }
}