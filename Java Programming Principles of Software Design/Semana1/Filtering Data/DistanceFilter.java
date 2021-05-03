public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;
    private String filterName;
    
    public DistanceFilter(Location location, double maxDistance, String filterName) {
        this.location = location;
        this.maxDistance = maxDistance;
        this.filterName = filterName;
    }
    
    public  boolean satisfies(QuakeEntry qe) {
        if (qe.getLocation().distanceTo(location) < maxDistance) {
            return true;
        } else {
            return false;
        }
    } 
    
    public String getName() {
        return filterName;
    }
}
