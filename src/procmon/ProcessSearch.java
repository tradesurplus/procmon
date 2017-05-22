/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procmon;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author john
 */
public class ProcessSearch {
    private Process[] processesToFind;
    private List<String> runningProcs;
    
    public ProcessSearch (Process[] processesToFind, List<String> runningProcs) {
        this.processesToFind = processesToFind;
        this.runningProcs = runningProcs;
    }
    
    public Process[] searchForProcess() {
        boolean isMatched = false;
        for (Process processToFind:processesToFind) {
            if (processToFind.getIsPID()) {
                File f = new File(((ProcessByPID) processToFind).getPidFilename());
                boolean exists = f.exists();
                if (exists) {
                    String processID = ReadFile.getContents(f);
                    Pattern pattern = Pattern.compile("^\\s*" + processID + ".*$");
                    for (String runningProc: runningProcs) {
                        Matcher matcher = pattern.matcher(runningProc);
                        isMatched = matcher.find();
                        if (isMatched) {
                            processToFind.setProcessDetails(matcher.group());
                            processToFind.setIsRunning(true);
                            break;
                        }
                    }
                }
            } else {
                Pattern pattern = Pattern.compile("(.*)(" + ((ProcessByString) processToFind).getSearchString() + ")(.*)");
                for (String runningProc: runningProcs) {
                    Matcher matcher = pattern.matcher(runningProc);
                    isMatched = matcher.find();
                    if (isMatched) {
                        processToFind.setProcessDetails(matcher.group());
                        processToFind.setIsRunning(true);
                        break;
                    }
                }
            }
        }
        return processesToFind;
    }
}
