import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class JFrame1 {             //��¼����
    public String username;
    public String password;

    public void jFrame1(Connection connection){
        JFrame frame=new JFrame("user login");
        JLabel label=new JLabel("name");
        JTextField field=new JTextField(20);
        JLabel label1=new JLabel("password");
        JTextField field1=new JTextField(20);
        JButton button=new JButton("login");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username=field.getText();
                password=field1.getText();
                Pass demo1=new Pass();

                if (demo1.login(connection,username,password)){  //�жϵ�¼�Ƿ�ɹ�
                    new JFrame2();       //��¼�ɹ������������
                    frame.dispose();
                }
            }
        });
        JPanel panel=new JPanel();
        panel.add(label);
        panel.add(field);
        panel.add(label1);
        panel.add(field1);
        panel.add(button);
        frame.add(panel);
        frame.setSize(300,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JFrame1();
    }

}
