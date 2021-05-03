import java.util.*;

public class Tester {    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer LogAnalyzer = new LogAnalyzer();
        LogAnalyzer.readFile("short-test_log");
        LogAnalyzer.printAll();
    }
    
    public void testUniqIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPS = la.countUniqueIPs();
        System.out.println("Hay " + uniqueIPS + " IPs"); 
    }
    
    public void testprintAllHigherthanNum() {
        LogAnalyzer MyLogAnalyser = new LogAnalyzer();
        MyLogAnalyser.readFile("weblog1_log");
        MyLogAnalyser.printAllHigherThanNum(400);
    }
    
    public void testuniqueIPVisitsOnDay() {
        LogAnalyzer MyAnalyser = new LogAnalyzer();
        MyAnalyser.readFile("weblog2_log");
        //MyAnalyser.uniqueIPVisitsOnDay("Sep 14");
        //MyAnalyser.uniqueIPVisitsOnDay("Sep 30");
        MyAnalyser.uniqueIPVisitsOnDay("Sep 24");
        int tm = MyAnalyser.uniqueIPVisitsOnDay("Sep 24").size();
        System.out.println(tm);
    }
    
    public void testcountUniqueIPsInRange() {
        LogAnalyzer Analyser = new LogAnalyzer();
        Analyser.readFile("weblog2_log");
        int countinRange_first = Analyser.countUniqueIPsInRange(400, 499);
        //int countinRange_second = Analyser.countUniqueIPsInRange(300, 399);
        System.out.println("Hay en primer lugar " + countinRange_first + " IPs");
        //System.out.println("Hay en segundo lugar " + countinRange_second + " IPs");
    }
}
