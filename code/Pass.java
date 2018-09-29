import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Pass {              //��¼���������ݿ����Ӻ�Ĳ���
    public Boolean login(Connection connection, String userName1, String password1){

        String name="select * from user where username=?";
        try {
            PreparedStatement ps=connection.prepareStatement(name);
            ps.setString(1,userName1);
            ResultSet result=ps.executeQuery();
            if (result.next()) {
                if (result.getString("password").equals(password1)) {
                    System.out.println("Login successfully!!");  //��¼�ɹ�
                    return true;
                } else {
                    System.out.println("The password is wrong");  //��¼ʧ��
                    return false;
                }
            }
            else{
                System.out.println("The UserName is wrong");
                return false;
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;
    }

}
