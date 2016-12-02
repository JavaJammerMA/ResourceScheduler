package hibernateHelloWorld;

import java.util.Scanner;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="students")
public class student {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email")
	private String email;
	public student(){
		
	}
	public student(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public student(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	public static void main(String[] args){
		
		Scanner asd = new Scanner(System.in);
	SessionFactory factory = new Configuration()
							     .configure("hibernate.cfg.xml")
							     .addAnnotatedClass(student.class)
							     .buildSessionFactory();
	Session session = factory.getCurrentSession();
	
	
	try{
		System.out.println("Creating object");
		student Javed = new student(asd.next(), asd.next(), asd.next());
		student Miguel = new student("Miguel", "Antunes", "miguel.antunes@soprasteria.com");
		System.out.println("Transaction");
		session.beginTransaction();
		System.out.println("Save Object");
		session.save(Javed);
		//session.save(Miguel);
		System.out.println("Commit Object");
		session.getTransaction().commit();
		
//		session.beginTransaction();
//		session.save(Javed);
//		session.getTransaction().commit();
		session.close();
		
		Session session1 = factory.getCurrentSession();
		session1.beginTransaction();
		session1.save(Miguel);
		session1.getTransaction().commit();
		
	}finally{
		factory.close();
	}
	}
}
