package procmon;

/**
 *
 * @author John Carruthers
 */
public class Process {
    
    private String description;
    private String name;
    private String owner;
    private String pidFilename;
    private String searchString;
    private String type;
    private String group;
        
    public Process(
                String description,
                String name,
                String owner,
                String pidFilename,
                String searchString,
                String type,
                String group) {
        this.description = description;
        this.name = name;
        this.owner = owner;
        this.pidFilename = pidFilename;
        this.searchString = searchString;
        this.type = type;
        this.group = group;
    }
        
    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getPidFilename() {
        return this.pidFilename;
    }

    public String getSearchString() {
        return this.searchString;
    }

    public String getType() {
        return this.type;
    }

    public String getGroup() {
        return this.group;
    }
    
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Process details ");
//        sb.append("\n\t");
//        sb.append("Description: ").append(getDescription());
//        sb.append("\n\t");
//        sb.append("Type: ").append(getType());
//        sb.append("\n\t");
//        sb.append("Name: ").append(getName());
//        sb.append("\n\t");
//        sb.append("Group: ").append(getGroup());
//        sb.append("\n\t");
//        sb.append("pid Filename: ").append(getPidFilename());
//        sb.append("\n\t");
//        sb.append("Search String: ").append(getSearchString());
//        return sb.toString();
//    }
    
}
