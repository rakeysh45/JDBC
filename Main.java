import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/employee";
        String user = "root";
        String pass = "root";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver successfully loaded");
            Connection con = DriverManager.getConnection(url,user,pass);
            System.out.println("Connection established");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}