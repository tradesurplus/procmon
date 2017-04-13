package superceded;

/**
 *
 * @author John Carruthers
 */
public class Variables {
    private String ps_command;
    
    public Variables(String ps_command) {
        this.ps_command = ps_command;
    }
    
    public String getPscomm() {
        return ps_command;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getPscomm());
        return sb.toString();
    }
}
