package WordGramClass;


public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        
        for (int i = 0; i < myWords.length; i++) {
            ret += myWords[i] + " ";
        }

        return ret.trim();
    }
    
    public WordGram shiftAdd(String word) {
        String[] myWordsCopy = new String[myWords.length];
        
        for (int i = 0; i < this.length() - 1; i++) {
            myWordsCopy[i] = this.wordAt(i + 1);
        }
        
        myWordsCopy[this.length() - 1] = word; 
        
        WordGram out = new WordGram(myWordsCopy, 0, this.length());
        
        return out;
    }
    
    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        
        if (this.length() != other.length()) {
            return false;
        }
        
        for (int i = 0; i < this.length(); i++) {
            if (!this.wordAt(i).equals(other.wordAt(i))) {
                return false;
            }
        }
        
        return true;
    }
    
    public int hashCode() {
        myHash = this.toString().hashCode();
        return myHash;
    }
}