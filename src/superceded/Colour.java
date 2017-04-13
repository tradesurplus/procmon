package superceded;

/**
 *
 * @author John Carruthers
 */
public class Colour {
    private String header;
    private String not_running;
    private String running;
    private String separator;
    private String standard;
    private String warning;
    private String seqterm;
    private String escseq;

    public Colour(
        String header,
        String not_running,
        String running,
        String separator,
        String standard,
        String warning,
        String seqterm,
        String escseq) {
        this.header = header;
        this.not_running = not_running;
        this.running = running;
        this.separator = separator;
        this.standard = standard;
        this.warning = warning;
        this.seqterm = seqterm;
        this.escseq = escseq;
    }
    
    public String getHeaderColour() {
        return header;
    }
    
    public String getNotRunningColour() {
        return not_running;
    }
    
    public String getRunningColour() {
        return running;
    }
    
    public String getSeparatorColour() {
        return separator;
    }
    
    public String getStandardColour() {
        return standard;
    }
    
    public String getWarningColour() {
        return warning;
    }
    
    public String getSeqTerm() {
        return this.seqterm;
    }
    
    public String getEscSeq() {
        return this.escseq;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Colour details:");
        sb.append("\n ");
        sb.append("Header -> ").append(getHeaderColour());
        sb.append("\n ");
        sb.append("Not_running -> ").append(getNotRunningColour());
        sb.append("\n ");
        sb.append("Running -> ").append(getRunningColour());
        sb.append("\n ");
        sb.append("Separator -> ").append(getSeparatorColour());
        sb.append("\n ");
        sb.append("Standard -> ").append(getStandardColour());
        sb.append("\n ");
        sb.append("Warning -> ").append(getWarningColour());
        sb.append("\n ");
        sb.append("seqterm -> ").append(getSeqTerm());
        sb.append("\n ");
        sb.append("escseq -> ").append(getEscSeq());
        return sb.toString();
    }
}
