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
public class ReportProcNotRunningColour {
    private String notrunning;
    
    public ReportProcNotRunningColour(String notrunning) {
        this.notrunning = notrunning;
    }
    
    public String getReportNotRunningColour() {
        return notrunning;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Colour details:");
        sb.append("\n ");
        sb.append("Not Running -> ").append(getReportNotRunningColour());
        return sb.toString();
    }
}
