package techconnect.techconnectproject.models;

import jakarta.persistence.*;

@Entity
@Table(name="inquiries")
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inqNumber;
    private int userId;
    private String userName;
    private String title;
    private String type;
    private String description;
    private String directory;
    private String channel;
    private boolean resolved;
    
    public Inquiry() {
    }

    public Inquiry(int userId, String title, String userName, String type, String description, boolean resolved, String directory, String channel) {
        this.userId = userId;
        this.userName = userName;
        this.type = type;
        this.description = description;
        this.resolved = resolved;
        this.title = title;
        this.userName = userName;
        this.directory = directory;
        this.channel = channel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getInqNumber() {
        return inqNumber;
    }

    public void setInqNumber(int inqNumber) {
        this.inqNumber = inqNumber;
    }

    public String getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    } 

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
}
