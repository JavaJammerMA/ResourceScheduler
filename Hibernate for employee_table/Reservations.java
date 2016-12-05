import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservations implements Serializable {
    @Id
    @Column(name = "Reservation_ID")
    private int reservationID;    
    @OneToMany(mappedBy = "Resource_ID")
    private Set<Integer> resourceID = null;
    @Column(name = "Date")
    private Date date;
    @Column(name = "Time_Slot")
    private String timeSlot;
    public Reservations(){
        
    }
    public Reservations(int resourceID, Date date, String timeSlot) {
        this.resourceID = resourceID;
        this.date = date;
        this.timeSlot = timeSlot;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
    
}
