package superceded;

/**
 *
 * @author John Carruthers
 */
public class Header {
    private String name;
    private String headerDetail;
    private String separatorChar;
    
    public Header(String name, String headerDetail, String separatorChar) {
        this.name = name;
        this.headerDetail = headerDetail;
        this.separatorChar = separatorChar;
    }
    
    public String getName() {
        return name;
    }
    
    public String getHeaderDetail() {
        return headerDetail;
    }
    
    public String getSeparatorChar() {
        return separatorChar;
    }
    
//        @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Process details - ");
//        sb.append("separatorChar: ").append(getSeparatorChar());
//
//        return sb.toString();
//    }
}
