import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrame2 extends JFrame implements ActionListener{  //����������
    public JFrame2(){
        JFrame frame2=new JFrame("��Ʒ����ϵͳ");
        JLabel label=new JLabel("ϵͳ����Ա");
        JButton product=new JButton("��Ʒ��Ϣ����");
        JButton order=new JButton("������Ϣ");
        JButton putOut=new JButton("��������");
        JButton putIn=new JButton("���������");
        JButton storeHouse=new JButton("�ֿ����");

        JPanel panel=new JPanel(new GridLayout(6,1,10,10));
        panel.add(label);
        panel.add(product);
        panel.add(order);
        panel.add(putIn);
        panel.add(putOut);
        panel.add(storeHouse);
        frame2.add(panel);
        frame2.setSize(300,200);
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //�԰������м���

        product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Product();
            }
        });

        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Order();
            }
        });

        putOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        putIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PutIn();
            }
        });
        putOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PutOut();
            }
        });
        storeHouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StoreHouse();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
