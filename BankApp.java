import java.sql.*;
import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/tapbank";
        String un = "root";
        String pwd = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,un,pwd);
            Scanner sc = new Scanner(System.in);
            //login module
            System.out.println("<------ Welcome to Our Bank ---------->");
            System.out.println("Enter Account number: ");
            int acc_num = sc.nextInt();
            System.out.println("Enter your pin: ");
            int pin = sc.nextInt();
            PreparedStatement pstmt1 = con.prepareStatement("Select * from account where acc_num = ? " +
                    "and pin = ?");
            pstmt1.setInt(1,acc_num);
            pstmt1.setInt(2,pin);
            ResultSet res1 = pstmt1.executeQuery();
            res1.next();
            String name = res1.getString(2);
            int bal = res1.getInt(4);

            System.out.println("Welcome "+name);
            System.out.println("Available balance: "+bal);

            //Transfer Module
            System.out.println("<-------- Transfer Detail ----------->");
            System.out.println("Enter the beneficiary account number: ");
            int bacc_num = sc.nextInt();
            System.out.println("Enter the transfer amount: ");
            int t_amount = sc.nextInt();

            con.setAutoCommit(false);
            Savepoint s = con.setSavepoint();
            PreparedStatement pstmt2 = con.prepareStatement("update account set balance = balance - ?" +
                    " where acc_num = ?");
            pstmt2.setInt(1,t_amount);
            pstmt2.setInt(2,acc_num);
            pstmt2.executeUpdate();

            System.out.println("<------ Incoming Credit Request ----> ");
            System.out.println(name+ "account number "+acc_num+" wants to " +
                    "transfer "+t_amount);
            System.out.println("Press Y to Receive");
            System.out.println("Press N to Reject");
            String choice = sc.next();
            if(choice.equals("Y")){
                PreparedStatement pstmt3 = con.prepareStatement("update account set balance = balance + ?" +
                        " where acc_num = ?");
                pstmt3.setInt(1,t_amount);
                pstmt3.setInt(2,bacc_num);
                pstmt3.executeUpdate();
                PreparedStatement pstmt4 = con.prepareStatement("select * from account where " +
                        "acc_num = ? ");
                pstmt4.setInt(1,bacc_num);
                ResultSet res2 = pstmt4.executeQuery();
                res2.next();

                System.out.println("Your updated balance is : "+res2.getInt(4));
            }else {
                con.rollback(s);
                PreparedStatement pstmt5 = con.prepareStatement("select * from account where " +
                        "acc_num = ? ");
                pstmt5.setInt(1,bacc_num);
                ResultSet res2 = pstmt5.executeQuery();
                res2.next();

                System.out.println("Your updated balance is : "+res2.getInt(4));
            }
            con.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
