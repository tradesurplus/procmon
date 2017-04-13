package procmon;

/**
 *
 * @author John Carruthers
 */
public class StringLength {
    private int highestCharCount;

    public StringLength(int highestCharCount) {
        this.highestCharCount = highestCharCount;
    }
    
    public int getHighestCharCount() {
        return highestCharCount;
    }
    
    public int setHighestCharCount(String text) {
        highestCharCount = text.length();
        return highestCharCount;
    }
    //tochararray() may be useful in determining the length of an output string
}
