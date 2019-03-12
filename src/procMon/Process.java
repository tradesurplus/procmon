package procMon;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author John Carruthers
 */
public class Process implements Comparable<Process> {
    private String description;
    private String owner;
    private String group;
    private String processDetails;
    private String pidFilename;
    private String processSearchString;
    private boolean isRunning;
    private boolean hasPIDfile;
    private List<String> runningProcs;
    private String processID;
    private String processOwner;
    private String processStarttime;
    private String processString;
        
    public Process(
                String description,
                String owner,
                String group,
                String processDetails,
                String pidFilename,
                String processSearchString,
                boolean isRunning,
                boolean hasPIDfile) {
        this.description = description;
        this.owner = owner;
        this.group = group;
        this.processDetails = processDetails;
        this.pidFilename = pidFilename;
        this.processSearchString = processSearchString;
        this.isRunning = isRunning;
        this.hasPIDfile = hasPIDfile;
    }
    
    public Process() {
    }
    
    public Process[] searchForProcess(Process[] processesToFind, List<String> runningProcs) {
        boolean isMatched = false;
        for (Process processToFind:processesToFind) {
            if (processToFind.getHasPIDfile()) {
                File f = new File(processToFind.getPidFilename());
                try {
                    processID = FileUtils.readFileToString(f, "UTF-8").trim();
                    Pattern pattern = Pattern.compile("^\\s*" + processID + ".*$");
                    for (String runningProc:runningProcs) {
                        Matcher matcher = pattern.matcher(runningProc);
                        isMatched = matcher.find();
                        if (isMatched) {
                            processToFind.setProcessDetails(matcher.group());
                            processToFind.setIsRunning(true);
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error opening file: " + e.toString());
                }
            } else {
                Pattern pattern = Pattern.compile("(.*)(" + processToFind.getProcessSearchString() + ")(.*)");
                for (String runningProc:runningProcs) {
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
    
    public String getProcessDetails() {
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
    
    public List<String> getRunningProcs(String pscommand) {
        try {
            String process;
            String[] cmd = { "/bin/sh", "-c", pscommand };
            runningProcs = new ArrayList<>();

            java.lang.Process processes = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(processes.getInputStream()));
            try {
                while ((process = br.readLine()) != null) {
                    runningProcs.add(process);
                }
            } catch (IOException ioe) {
                System.err.println(ioe);
                System.exit(1);
            }
        } catch (IOException e1) {
            System.err.println(e1);
            System.exit(1);
        }
        return runningProcs;
    }
    
    public String getDescription() {
        return this.description;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getGroup() {
        return this.group;
    }
    
    public String getPidFilename() {
        return this.pidFilename;
    }
    
    public String getProcessSearchString() {
        return this.processSearchString;
    }
    
    public boolean getIsRunning() {
        return this.isRunning;
    }

    public boolean getHasPIDfile() {
        return this.hasPIDfile;
    }
      
    public void setGroup(String group) {
        this.group = group;
    }
    
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    
    public void setProcessDetails(String processDetails) {
        this.processDetails = processDetails;
    }
    
    @Override
    public int compareTo(Process p) {
//        return p.getGroup().compareTo(this.getGroup());  //decending
        return this.getGroup().compareTo(p.getGroup());  //ascending
    }
}
