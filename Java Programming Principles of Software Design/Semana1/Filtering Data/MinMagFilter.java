
public class MinMagFilter implements Filter
{
    private double magMin; 
    private String filterName;
    
    public MinMagFilter(double min, String filterName) { 
        magMin = min;
        this.filterName = filterName;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 
    
    public String getName() {
        return filterName;
    }
}
