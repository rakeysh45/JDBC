import java.sql.*;
import java.util.Scanner;

public class Batch {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/employee";
        String un = "root";
        String pwd = "root";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");

            con = DriverManager.getConnection(url,un,pwd);
            System.out.println("Connection Established");

            String query = "insert into emp(id,name,desg,salary) values(?,?,?,?)";
            pstmt = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Id: ");
            int id = sc.nextInt();
            System.out.println("Enter name: ");
            String name = sc.next();
            System.out.println("Enter Designation: ");
            String desg = sc.next();
            System.out.println("Enter Salary: ");
            int salary = sc.nextInt();

            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,desg);
            pstmt.setInt(4,salary);

            pstmt.execute();
            System.out.println("Query executed successfully");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try{
            pstmt.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
