/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procmon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author john
 */
public class ProcessIsRunningSearch {
    private List<String> procsToFindByString;
    private List<String> procsToFindByPID;
    private List<String> runningProcs;
    private List<String> processDetails = new ArrayList<String>();
    
    public ProcessIsRunningSearch(List<String> procsToFindByString, List<String> procsToFindByPID, List<String> runningProcs) {
        this.procsToFindByString = procsToFindByString;
        this.procsToFindByPID = procsToFindByPID;
        this.runningProcs = runningProcs;
    }
    
    public List<String> searchForProcess() {
        for (String processToFind: procsToFindByString) {
            Pattern pattern = Pattern.compile(".*" + processToFind + ".*");
            for (String runningProc: runningProcs) {
                Matcher matcher = pattern.matcher(runningProc);
                if (matcher.find()) {
                    processDetails.add(matcher.group());
                    break;
                }
            }
        }
        for (String processToFind: procsToFindByPID) {
            File f = new File(processToFind);
            String processID = ReadFile.getContents(f);
            Pattern pattern = Pattern.compile("^\\s*" + processID + ".*$");
            for (String runningProc: runningProcs) {
                Matcher matcher = pattern.matcher(runningProc);
                if (matcher.find()) {
                    processDetails.add(matcher.group());
                    break;
                }
            }
        }
        return processDetails;
    }
}
