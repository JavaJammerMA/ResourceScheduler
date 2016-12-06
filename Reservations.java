

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "reservations")
public class Reservations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "fk_resourceId")
	private String fk_resourceId;
	@Column(name = "dateAndTime")
	private String dateAndTime;
	@Column(name = "timeSlot")
	private int timeSlot;
	
	public String getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	public int getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}
	public int getId() {
		return id;
	}
	public String getFk_resourceId() {
		return fk_resourceId;
	}
}
