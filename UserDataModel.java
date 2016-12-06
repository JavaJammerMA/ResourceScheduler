import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.Session;




public class UserDataModel {
	
	
	public String setUser(Employee e){
		Employee employee = e;
		ArrayList<Object> contentHolder = new ArrayList<Object>();
		String eMail = employee.geteMail();
		
	
		CustomSessionFactory.getInstance();
		Session s = CustomSessionFactory.getSession();
		s.beginTransaction();
		
		
		Query q = s.createQuery("from Employee where eMail = :eMail");
		q.setParameter("eMail" , eMail);
		
		contentHolder =(ArrayList) q.getResultList();
		
		if	(contentHolder.isEmpty()){
		s.save(employee);
		s.getTransaction().commit();
		s.close();
		return "true";
		
		
	}else{
		s.close();
		return "Sorry, that e-mail is already being used by another account";	
	}
		
	}
	
	public ArrayList<Employee> getUser(String eMail){
		String geteMail = eMail;
		CustomSessionFactory.getInstance();
		Session s = CustomSessionFactory.getSession();
		s.beginTransaction();
		
		Query q = s.createQuery("from Employee where eMail = :eMail");
		q.setParameter("eMail" , geteMail);
		ArrayList<Employee> e = (ArrayList<Employee>)q.getResultList();
		s.close();
		
		return e;
		
	}
	
	public String getUserMAC(String eMail){
		String geteMail = eMail;
		CustomSessionFactory.getInstance();
		Session s = CustomSessionFactory.getSession();
		s.beginTransaction();
		Query q = s.createQuery("from Employee where eMail = :eMail");
		q.setParameter("eMail" , geteMail);
		ArrayList<Employee> e = (ArrayList<Employee>)q.getResultList();
		Employee emp = e.get(0);
		String MACAddress = emp.getMAC();
		
		return MACAddress;
	}
	
	public boolean deleteUser(String eMail){
		String geteMail = eMail;
		CustomSessionFactory.getInstance();
		Session s = CustomSessionFactory.getSession();
		s.beginTransaction();
		Query q = s.createQuery("delete from Employee where eMail = :eMail");
		q.setParameter("eMail" , geteMail);
		
		int entriesDeleted = q.executeUpdate();
		s.close();
		if(entriesDeleted == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean updateUser(Employee e, int id){
		Employee emp = e;
		String hqlQuery = "update Employee set firstName = :firstName, " +
						  "lastName = :lastName, MAC = :MAC, " +
						  "eMail = :eMail, phoneNumber = :phoneNumber " +
						  "where id = :id";
		int identifier = id;
		String firstName = emp.getFirstName();
		String lastName = emp.getLastName();
		String MAC = emp.getMAC();
		String eMail = emp.geteMail();
		long phoneNumber = emp.getPhoneNumber();
		CustomSessionFactory.getInstance();
		Session s = CustomSessionFactory.getSession();
		s.beginTransaction();
		Query q = s.createQuery(hqlQuery);
		q.setParameter("firstName" , firstName);
		q.setParameter("lastName" , lastName);
		q.setParameter("MAC" , MAC);
		q.setParameter("eMail" , eMail);
		q.setParameter("phoneNumber" , phoneNumber);
		q.setParameter("id" , identifier);
		
		int i = q.executeUpdate();
		
		if(i == 0){
			s.close();
			return false;
		}else{
			s.close();
			return true;
		}
		
	}
	
	
}
