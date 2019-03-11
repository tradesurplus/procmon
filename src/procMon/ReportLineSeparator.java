/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procMon;

/**
 *
 * @author john
 */
public class ReportLineSeparator {
    private String separatorChar;
    
    public ReportLineSeparator(String separatorChar) {
        this.separatorChar = separatorChar;
    }
    
    public String reportLineSeparator(int separatorlength) {
        StringBuilder underline = new StringBuilder(separatorlength);
        for (int i = 0; i < separatorlength; i++){
            underline.append(separatorChar);
        }
        return underline.toString();
    }
}
