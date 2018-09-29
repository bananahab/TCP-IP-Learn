import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlJDBCDriverConnection {  //与数据库进行连接
    public static Connection connect() {
        Connection conn = null;
        try {
            //
            String url = "jdbc:mysql://localhost:3306/user1?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false" ;
            String name="root";
            String psw="123456";
            conn = DriverManager.getConnection(url,name,psw);

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Statement stat=conn.createStatement();
            System.out.println("Connection to MySQL has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) {
        connect();

    }

}


