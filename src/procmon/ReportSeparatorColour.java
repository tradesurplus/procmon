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
public class ReportSeparatorColour {
    private String separator;
    
    public ReportSeparatorColour(String separator) {
        this.separator = separator;
    }
    
    public String getReportSeparatorColour() {
        return separator;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Colour details:");
        sb.append("\n ");
        sb.append("Separator -> ").append(getReportSeparatorColour());
        return sb.toString();
    }
}
