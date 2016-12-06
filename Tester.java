import java.util.ArrayList;

public class Tester {
	public static void main(String[] args){
		ArrayList<Employee> e;
        UserDataModel m = new UserDataModel();
        e = m.getUser("charlie");
        
        long phoneNumber = 8549648674L;
        boolean del = m.updateUser(new Employee("Charlie", "Draper", "FFFFFFFF", "charlie.draper@SopraSteria.com", phoneNumber), 2 );
        
        System.out.println(del);
	}
}
