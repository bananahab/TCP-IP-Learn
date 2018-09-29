import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrame2 extends JFrame implements ActionListener{  //进入主界面
    public JFrame2(){
        JFrame frame2=new JFrame("商品管理系统");
        JLabel label=new JLabel("系统管理员");
        JButton product=new JButton("产品信息管理");
        JButton order=new JButton("订单信息");
        JButton putOut=new JButton("出货管理");
        JButton putIn=new JButton("入库管理管理");
        JButton storeHouse=new JButton("仓库管理");

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

        //对按键进行监听

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
