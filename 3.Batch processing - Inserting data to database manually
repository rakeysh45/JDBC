import java.sql.*;

public class Batch {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/employee";
        String un = "root";
        String pwd = "root";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");

            con = DriverManager.getConnection(url,un,pwd);
            System.out.println("Connection Established");

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,0);

            String query1 = "insert into emp(id,name,desg,salary) values(9,'rob','CMO',2000)";
            String query2 = "insert into emp(id,name,desg,salary) values(10,'rob','CMO',2000)";
            String query3 = "insert into emp(id,name,desg,salary) values(11,'rob','CMO',2000)";

            stmt.addBatch(query1);
            stmt.addBatch(query2);
            stmt.addBatch(query3);
            stmt.executeBatch();
            System.out.println("Queries Executed successfully");

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
