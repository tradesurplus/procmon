package procmon;

/**
 *
 * @author John Carruthers
 */
public class ReportLine {
    private String processGroup;
    private String processDescription;
    private boolean isRunning;
    private String processOwner;
    private String configOwner;
    private String processPID;
    private String processRunningTime;

    public ReportLine(String processGroup, String processDescription, boolean isRunning, String processOwner, String configOwner, String processPID, String processRunningTime) {
        this.processGroup = processGroup;
        this.processDescription = processDescription;
        this.isRunning = isRunning;
        this.processOwner = processOwner;
        this.configOwner = configOwner;
        this.processPID = processPID;
        this.processRunningTime = processRunningTime;
    }
    
    public String getGroup() {
        return this.processGroup;
    }

    public String getDescription() {
        return this.processDescription;
    }
    
    public boolean getIsRunning() {
        return this.isRunning;
    }
    
    public String getOwner() {
        return this.processOwner;
    }
    
    public String getConfigOwner() {
        return this.configOwner;
    }
    
    public String getPID() {
        return this.processPID;
    }
    
    public String getRunningTime() {
        return this.processRunningTime;
    }
}
