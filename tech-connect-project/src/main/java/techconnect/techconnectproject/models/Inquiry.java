package techconnect.techconnectproject.models;

import jakarta.persistence.*;

@Entity
@Table(name="inquiries")
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inqNumber;
    private String type;
    private String description;
    private boolean resolved;
    
    public Inquiry() {
    }

    public Inquiry(String type, String description, boolean resolved) {
        this.type = type;
        this.description = description;
        this.resolved = resolved;
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
}
