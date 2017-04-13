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
public class ReportStandardColour {
    private String standard;
    
    public ReportStandardColour(String standard) {
        this.standard = standard;
    }
    
    public String getReportStandardColour() {
        return standard;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Colour details:");
        sb.append("\n ");
        sb.append("Standard -> ").append(getReportStandardColour());
        return sb.toString();
    }
}
