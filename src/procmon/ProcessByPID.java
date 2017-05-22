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
public class ProcessByPID extends Process {
    private String pidFilename;
    
    public ProcessByPID (String pidFilename, String description, String owner, String group, String processDetails, boolean isRunning, boolean isPID) {
        super(description, owner, group, processDetails, isRunning, isPID);
        this.pidFilename = pidFilename;
    }
    
    public String getPidFilename() {
        return this.pidFilename;
    }
}
