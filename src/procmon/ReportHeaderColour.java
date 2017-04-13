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
public class ReportHeaderColour {
    private String header;
    
    public ReportHeaderColour(String header) {
        this.header = header;
    }
    
    public String getReportHeaderColour() {
        return header;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Colour details:");
        sb.append("\n ");
        sb.append("Header -> ").append(getReportHeaderColour());
        return sb.toString();
    }
}
