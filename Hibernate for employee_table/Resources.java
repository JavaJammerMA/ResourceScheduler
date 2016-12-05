import java.time.LocalDate;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resources_table")
public class Resources {
    @Id
    @Column(name = "Resource_ID")
    private int resourceID;
    @Column(name = "Resource_Name")
    private String resourceName;

    public Resources(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    
    
}
