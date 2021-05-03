import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        /* for (QuakeEntry qe : list) {
            System.out.println(qe);
        } */
        
        System.out.println("Read data for " + list.size());
        
        // System.out.println("Index with largest magnitude = " + indexOfLargest(list));
        
        ArrayList<QuakeEntry> highestMagnitude = getLargest(list, 50);
        
        System.out.println("Data with highest magnitude:");
        
        for (QuakeEntry qe : highestMagnitude) {
            System.out.println(qe);
        }
    }
    
    private int indexOfLargest(ArrayList<QuakeEntry> data) {
        double maxMagnitude = Integer.MIN_VALUE;
        int maxIndex = -1;
        
        for (int i = 0; i < data.size(); i++) {
            double currMagnitude = data.get(i).getMagnitude();
            
            if (currMagnitude > maxMagnitude) {
                maxMagnitude = currMagnitude;
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
    
    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        
        for (int i = 0; i < howMany; i++) {
            int maxIndex = indexOfLargest(copy);
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        
        return ret;
    }
}
