
public class MagnitudeFilter implements Filter {
    private double minMagnitude;
    private double maxMagnitude;
    private String filterName;
    
    public MagnitudeFilter(double minMagnitude, double maxMagnitude, String filterName) {
        this.minMagnitude = minMagnitude;
        this.maxMagnitude = maxMagnitude;
        this.filterName = filterName;
    }
    
    public  boolean satisfies(QuakeEntry qe) {
        if (qe.getMagnitude() >= minMagnitude && qe.getMagnitude() <= maxMagnitude) {
            return true;
        } else {
            return false;
        }
    } 
    
    public String getName() {
        return filterName;
    }
}
