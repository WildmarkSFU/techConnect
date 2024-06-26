package techconnect.techconnectproject.models;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String name;
    private String username;
    private String email;
    private String password;
    private String directory;
    private Double latitude;
    private Double longitude;

    public User() {
    }
    public User(String name, String username, String email, String password, String directory) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.directory = directory;
    }
    
    public User(String name, String username, String email, String password, Double latitude, Double longitude) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFormattedUid(){
        return "u" + uid;
    }
    public String getDirectory() {
        return directory;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
