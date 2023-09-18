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
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");

            con = DriverManager.getConnection(url,un,pwd);
            System.out.println("Connection Established");

//            String query = "insert into emp(id,name,desg,salary) values(?,?,?,?)";
//            pstmt = con.prepareStatement(query);

//            String query = "update emp set salary = salary + salary*0.20";
//            stmt = con.createStatement();
//            System.out.println("No of rows update: "+stmt.executeUpdate(query));

            String query = "delete from emp where name = 'rob'";
            stmt = con.createStatement();
            System.out.println("No of rows Deleted: " + stmt.executeUpdate(query));
//            Scanner sc = new Scanner(System.in);
//            System.out.println("Enter the no the rows to be inserted: ");
//            int n = sc.nextInt();
//
//            con.setAutoCommit(false);
//            for(int i=1;i<=n;i++)
//            {
//                int id = sc.nextInt();
//                String name = sc.next();
//                String desg = sc.next();
//                int salary = sc.nextInt();
//                pstmt.setInt(1,id);
//                pstmt.setString(2,name);
//                pstmt.setString(3,desg);
//                pstmt.setInt(4,salary);
//                pstmt.execute();
//            }
//            con.setAutoCommit(true);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try{
            stmt.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
