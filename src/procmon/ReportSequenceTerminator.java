/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procmon;

/**
 *
 * @author john
 */
public class ReportSequenceTerminator {
    private String sequence;
    
    public ReportSequenceTerminator(String sequence) {
        this.sequence = sequence;
    }
    
    public String getReportSequenceTerminator() {
        return sequence;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Details:");
        sb.append("\n ");
        sb.append("Sequence Terminator -> ").append(getReportSequenceTerminator());
        return sb.toString();
    }
}
