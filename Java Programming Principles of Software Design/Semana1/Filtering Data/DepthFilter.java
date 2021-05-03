
public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;
    private String filterName;
    
    public DepthFilter(double minDepth, double maxDepth, String filterName) {
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
        this.filterName = filterName;
    }
    
    public  boolean satisfies(QuakeEntry qe) {
        if (qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth) {
            return true;
        } else {
            return false;
        }
    } 
    
    public String getName() {
        return filterName;
    }
}
