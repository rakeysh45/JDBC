import java.sql.*;

public class Demo {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/employee";
        String un = "root";
        String pwd = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");

            Connection con = DriverManager.getConnection(url, un, pwd);
            System.out.println("Connection established");

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 0);
            String query = "select * from emp";
            stmt.executeQuery(query);

            ResultSet res = stmt.executeQuery(query);
            System.out.println("Query Executed Successfully");

//            ResultSetMetaData metaData = res.getMetaData();
//            for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                System.out.println(metaData.getColumnName(i) + " " + metaData.getColumnTypeName(i));
//            }
//            //res.absolute(2);


//            System.out.println(metaData.getColumnCount());
//            System.out.println(metaData.getColumnName(1)+" "+metaData.getColumnName(2)
//            +" "+metaData.getColumnName(3)+" "+metaData.getColumnName(4)+" ");
            while (res.next()) {
                System.out.println(res.getInt("id") + " " + res.getString("name") +
                        " " + res.getString("desg") + " " + res.getInt("salary"));
            }
            res.close();
            stmt.close();
            con.close();
//            res.first();
//            System.out.println(res.getInt("id") + " " + res.getString("name") +
//                    " " + res.getString("desg") + " " + res.getInt("salary"));
//
//            res.absolute(2);
//            System.out.println(res.getInt("id") + " " + res.getString("name") +
//                    " " + res.getString("desg") + " " + res.getInt("salary"));


        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
