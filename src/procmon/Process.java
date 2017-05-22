package procmon;

/**
 *
 * @author John Carruthers
 */
public class Process implements Comparable<Process> {
    private String description;
    private String owner;
    private String group;
    private String processDetails;
    private boolean isRunning;
    private boolean isPID;
        
    public Process(
                String description,
                String owner,
                String group,
                String processDetails,
                boolean isRunning,
                boolean isPID) {
        this.description = description;
        this.owner = owner;
        this.group = group;
        this.processDetails = processDetails;
        this.isRunning = isRunning;
        this.isPID = isPID;
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

    public String getProcessDetails() {
        return this.processDetails;
    }
    
    public boolean getIsRunning() {
        return this.isRunning;
    }

    public boolean getIsPID() {
        return this.isPID;
    }
      
    public void setGroup(String group) {
        this.group = group;
    }
     
    public void setProcessDetails(String processDetails) {
        this.processDetails = processDetails;
    }
    
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    
    @Override
    public int compareTo(Process p) {
//        return p.getGroup().compareTo(this.getGroup());  //decending
        return this.getGroup().compareTo(p.getGroup());  //ascending
    }
}
