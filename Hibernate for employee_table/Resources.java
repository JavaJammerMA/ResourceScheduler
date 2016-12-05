
import java.io.Serializable;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resources")
public class Resources implements Serializable {

    @Id
    @GeneratedValue
    @ManyToOne
    @JoinColumn(name = "Resource_ID")
    private int resourceID;
    @Column(name = "Resource_Name")
    private String resourceName;

    public Resources() {

    }

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
