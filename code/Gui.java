import java.sql.Connection;

public class Gui {            //ͼ�ν������̨
    public static void main(String[] args) {
        Gui gui=new Gui();
        MysqlJDBCDriverConnection demo=new MysqlJDBCDriverConnection();
        Connection connection=demo.connect();
        JFrame1 demo1=new JFrame1();  //�򿪵�¼����
        demo1.jFrame1(connection);
    }

}