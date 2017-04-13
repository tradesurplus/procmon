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
public class ReportProcRunningColour {
    private String running;
    
    public ReportProcRunningColour(String running) {
        this.running = running;
    }
    
    public String getReportRunningColour() {
        return running;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Colour details:");
        sb.append("\n ");
        sb.append("Running -> ").append(getReportRunningColour());
        return sb.toString();
    }
}
