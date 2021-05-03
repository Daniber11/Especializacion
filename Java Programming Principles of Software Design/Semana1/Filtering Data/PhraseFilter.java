
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    private String filterName;
    
    public PhraseFilter(String where, String phrase, String filterName) {
        this.where = where;
        this.phrase = phrase;
        this.filterName = filterName;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if (where.equals("start")) {
            if (qe.getInfo().startsWith(phrase)) {
                return true;
            }
        } else if (where.equals("end")) {
            if (qe.getInfo().endsWith(phrase)) {
                return true;
            }
        } else if (where.equals("any")) {
            if (qe.getInfo().contains(phrase)) {
                return true;
            }
        }
        
        return false;
    }
    
    public String getName() {
        return filterName;
    }
}
