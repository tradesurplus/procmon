/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procmon;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author john
 */
public class ProcessDetails {
    private List<String> foundRunningProcs;
    private List<String> processDetails = new ArrayList<String>();
    private String processID;
    private String processOwner;
    private String processStarttime;
    private String processString;
    
    public ProcessDetails(List<String> foundRunningProcs) {
        this.foundRunningProcs = foundRunningProcs;
    }
    
    public List<String> getProcessDetails() {
        if (foundRunningProcs != null) {
            for (String process: foundRunningProcs) {
                Pattern pattern = Pattern.compile("^\\s*(\\w+)\\W+(\\w+)\\W+(.{5})\\W+(.*)$");
                Matcher matcher = pattern.matcher(process);
                if (matcher.find()) {
                    processID = matcher.group(1);
                    processOwner = matcher.group(2);
                    processStarttime = matcher.group(3);
                }
                processString = processID + " " + processOwner + " " + processStarttime;
                processDetails.add(processString);
                //System.out.println("Running process details -> " + processID + " " + processOwner + " " + processStarttime);
            }
        }
        return processDetails;
    }
}
