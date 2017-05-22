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
public class ProcessByString extends Process {
    private String searchString;
    
    public ProcessByString (String searchString, String description, String owner, String group, String processDetails, boolean isRunning, boolean isPID) {
        super(description, owner, group, processDetails, isRunning, isPID);
        this.searchString = searchString;
    }
    
    public String getSearchString() {
        return this.searchString;
    }
}
