/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procmon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author john
 */
public class ProcessDetails {
    private String processDetails;
    private String processID;
    private String processOwner;
    private String processStarttime;
    private String processString;
    
    public ProcessDetails(String processDetails) {
        this.processDetails = processDetails;
    }
    
    public String processDetails() {
        Pattern pattern = Pattern.compile("^\\s*(\\w+)\\W+(\\w+)\\W+(.{5})\\W+(.*)$");
        Matcher matcher = pattern.matcher(processDetails);
        if (matcher.find()) {
            processID = matcher.group(1);
            processOwner = matcher.group(2);
            processStarttime = matcher.group(3);
        }
        processString = processID + " " + processOwner + " " + processStarttime;
        return processString;
    }
}
