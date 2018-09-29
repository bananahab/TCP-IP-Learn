import java.sql.Connection;

public class Gui {            //图形界面控制台
    public static void main(String[] args) {
        Gui gui=new Gui();
        MysqlJDBCDriverConnection demo=new MysqlJDBCDriverConnection();
        Connection connection=demo.connect();
        JFrame1 demo1=new JFrame1();  //打开登录界面
        demo1.jFrame1(connection);
    }

}