package procmon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John Carruthers
 */
public class ProcessList {
    private String pscommand;
    private List<String> runningProcs;
    
    public ProcessList() {
    }
    public ProcessList(String pscommand) {
    }
    
    public void setPSCommand(String pscommand) {
        this.pscommand = pscommand;
    }
    
    public List<String> getRunningProcs() {
        try {
            String process;
            String[] cmd = { "/bin/sh", "-c", pscommand };
            runningProcs = new ArrayList<String>();

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
}