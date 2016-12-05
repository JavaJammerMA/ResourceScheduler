
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateManager {

    Employee employee = new Employee("Elliot", "Jenkins", "elliot.jenkins@soprasteria.com", "FF_FF_FF_FF", "07780114166");

    private static SessionFactory factory;

    public static void main(String[] args) {
        factory = CustomSessionFactory.getInstance();
        HibernateManager hbManager = new HibernateManager();

//        // Example adding an employee to the table
//        boolean addEmployee = hbManager.addEmployee(new Employee("firstNameHere", "lastNameHere", "email@address.com", "MAC", "phoneNumber"));
//        if (addEmployee) {
//            System.out.println("Successflly addded employee to the system");
//        } else {
//            System.out.println("Failed to add an employee to the system");
//        }
        // Example removing an employee from the table
//        int id = 4; // This cannot ever be the same
//        boolean removeEmployee = hbManager.removeEmployee(id);
//        if(removeEmployee){
//            System.out.println("Successfully removed " + id);            
//        } else {
//            System.out.println("Failed to remove " + id);
//        }
//        // Example displaying employees
//        List<Employee> employees = hbManager.getEmployees();
//        for (Employee e : employees) {
//            System.out.println(e.getFirstName() + " " + e.getLastName());
//        }
        hbManager.addResource(new Resources("Desk 2"));
        hbManager.addReservation(new Reservations(1, new Date(05, 12, 16), "5"));

    }

    /**
     * Method to add an employee to the employee_table within the database
     *
     * @param employee The employee that you would like to add
     * @return boolean whether the operation was successful
     */
    public boolean addEmployee(Employee employee) {
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ex) {
            session.close();

            return false;
        }
        return true;
    }

    /**
     * Method to remove an employee from the employee_table within the database
     *
     * @param EmployeeID The ID of the employee that you wish to remove
     * @return boolean whether the operation was successful
     */
    public boolean removeEmployee(int EmployeeID) {
        Session session = factory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            session.close();
            return false;
        }
    }

    /**
     * Method to get a list of all of the employees in the employee_table
     *
     * @return a List of Employees
     */
    public List<Employee> getEmployees() {
        Session session = factory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            List<Employee> employee = new ArrayList<>();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
                employee.add((Employee) iterator.next());
            }
            tx.commit();
            session.close();
            return employee;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.close();
            return null;
        }
    }

    public boolean addReservation(Reservations reservation) {
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(reservation);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.close();
            return false;
        }
        return true;
    }

    public boolean addResource(Resources resource) {
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(resource);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.close();
            return false;
        }
        return true;
    }
}
