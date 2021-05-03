import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        
        Filter f = new MinMagFilter(1, "Magnitude"); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            //System.out.println(qe);
        } 
        
        f = new MagnitudeFilter(1, 4, "Magnitude");
        ArrayList<QuakeEntry> result = filter(list, f);
        
        f = new DepthFilter(-180000.0, -30000.0, "Depth");
        result = filter(result, f);
        int r = result.size();        
        System.out.println("Result of Magnitude Filter and Depth Filter:" + r);
        
        for (QuakeEntry qe: result) { 
            System.out.println(qe);
        }
        
        /*
        Location denver = new Location(39.7392, -104.9903);
        Filter f = new DistanceFilter(denver, 1000000, "Distance");
        ArrayList<QuakeEntry> result = filter(list, f);
        
        f = new PhraseFilter("end", "a", "Phrase");
        result = filter(result, f);
        int r = result.size(); 
        System.out.println("Result of Distance Filter and Phrase Filter:" + r);
        
        for (QuakeEntry qe: result) { 
            System.out.println(qe);
        }
        */
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);       
        
        /*for(QuakeEntry qe : list) {
            System.out.println(qe);
        }*/
        
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1, 4.0, "Magnitude"));
        maf.addFilter(new DepthFilter(-180000.0, -30000.0, "Depth"));
        maf.addFilter(new PhraseFilter("any", "o", "Phrase"));
        
        ArrayList<QuakeEntry> result = filter(list, maf);
        int r = result.size();        
        System.out.println("Match all filter result:" + r);
        
        for (QuakeEntry qe : result) {
            System.out.println(qe);
        }
        
        System.out.println("Filters used are " + maf.getName());
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);       
        
        /*for(QuakeEntry qe : list) {
            System.out.println(qe);
        }*/
        
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0, "Magnitude"));
        maf.addFilter(new DistanceFilter(new Location(55.7308, 9.1153), 3000000, "Distance"));
        maf.addFilter(new PhraseFilter("any", "e", "Phrase"));
        
        ArrayList<QuakeEntry> result = filter(list, maf);
        
        int r = result.size(); 
        
        System.out.println("Match all filter 2 result:" + r);
        
        for (QuakeEntry qe : result) {
            System.out.println(qe);
        }
        
        System.out.println("Filters used are " + maf.getName());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
