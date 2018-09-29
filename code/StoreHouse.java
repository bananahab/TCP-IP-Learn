import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StoreHouse extends JFrame2 implements ActionListener{  //�򿪳����������
    JPanel panel=new JPanel();
    JPanel panel1=new JPanel();
    JButton add=new JButton("���");
    JButton delete=new JButton("ɾ��");
    JButton search=new JButton("��ѯ");
    JButton display=new JButton("��ʾ");
    JTextField find=new JTextField(5);

    JMenuBar menuBar=new JMenuBar();
    JTable table;
    MysqlJDBCDriverConnection demo=new MysqlJDBCDriverConnection();
    Connection connection=demo.connect();
    public String name3;

    public StoreHouse(){
        super();
        add("South",panel);
        this.add("Center",panel1);
        menuBar.add(add);
        menuBar.add(delete);
        menuBar.add(display);
        menuBar.add(search);
        menuBar.add(find);
        this.setBounds(300,300,600,450);
        add.addActionListener(this);
        delete.addActionListener(this);
        search.addActionListener(this);
        display.addActionListener(this);
        this.setJMenuBar(menuBar);
        this.setResizable(true);
        show();
    }

    public void display() {
        int i = 0,j = 0;
        List<String> list = new ArrayList<>();
        try {
            String demo = "select * from storehouse";
            PreparedStatement ps = connection.prepareStatement(demo);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                list.add(result.getString("name"));
                list.add(result.getString("address"));
                list.add(result.getString("phone"));
                list.add(result.getString("product"));
                list.add(result.getString("quantity"));
                list.add(result.getString("storehouse"));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[][] playerInfo = new Object[i][6];
        String[] columnName={"�ͻ�����","��ַ","�绰","��Ʒ","����","�ֿ�"};
        try {
            String demo1 = "select * from storehouse";
            PreparedStatement ps1 = connection.prepareStatement(demo1);
            ResultSet result1 = ps1.executeQuery();
            while (result1.next()){
                playerInfo[j][0]=result1.getString("name");
                playerInfo[j][1]=result1.getString("address");
                playerInfo[j][2]=result1.getString("phone");
                playerInfo[j][3]=result1.getString("product");
                playerInfo[j][4]=result1.getString("quantity");
                playerInfo[j][5]=result1.getString("storehouse");
                j++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table=new JTable(playerInfo,columnName);
        panel1.add(table);
        JScrollPane scroll=new JScrollPane(table);
        this.add(scroll);
    }

    public void delete(){
        int row=-1;
        row=table.getSelectedRow();
        String name1=null,address1=null,phone1=null,product1=null,quantity1=null,storehouse1=null;
        if(row==-1){
            JOptionPane.showMessageDialog(null,"��ѡ��Ҫɾ���ļ�¼");
        }else{
            int num = 0;
            try {
                String demo = "select * from storehouse";
                PreparedStatement ps = connection.prepareStatement(demo);
                ResultSet result = ps.executeQuery();
                while (result.next() && num <= row) {
                    name1 = result.getString("name");
                    address1 = result.getString("address");
                    phone1 = result.getString("phone");
                    product1= result.getString("product");
                    quantity1 = result.getString("quantity");
                    storehouse1=result.getString("storehouse");
                    num++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            int num1 = 0;
            try {
                String demo2 = "delete from storehouse where name='"+name1+"'";
                PreparedStatement ps2 = connection.prepareStatement(demo2);
                int result1 = ps2.executeUpdate();
                JOptionPane.showMessageDialog(null, "��¼ɾ���ɹ���");
                this.dispose();
                new StoreHouse().display();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void find(String name3) {

        Object[][] playerInfo = new Object[1][6];
        String[] columnNames = {"�ͻ�����", "��ַ", "�绰", "��Ʒ", "����","�ֿ�"};
        try {
            String demo = "select * from storehouse where name='"+name3+"'";
            PreparedStatement ps = connection.prepareStatement(demo);
            ResultSet result = ps.executeQuery();
            while(result.next()) {
                playerInfo[0][0] =result.getString("name") ;
                playerInfo[0][1] = result.getString("address");
                playerInfo[0][2] = result.getString("phone");
                playerInfo[0][3] = result.getString("product");
                playerInfo[0][4] = result.getString("quantity");
                playerInfo[0][5] =result.getString("storehouse");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        table=new JTable(playerInfo,columnNames);
        panel1.add(table);
        JScrollPane scroll=new JScrollPane(table);
        this.add(scroll);
    }

    public void add(){
        JFrame frame=new JFrame();
        JPanel panel2=new JPanel();
        JTextField addName=new JTextField(22);
        JTextField addAddress=new JTextField(22);
        JTextField addPhone=new JTextField(22);
        JTextField addProduct=new JTextField(22);
        JTextField addQuantity=new JTextField(22);
        JTextField addStorehouse=new JTextField(22);

        JLabel name=new JLabel("�ͻ�����");
        JLabel address=new JLabel("��ַ");
        JLabel phone=new JLabel("�绰");
        JLabel product=new JLabel("��Ʒ");
        JLabel quantity=new JLabel("����");
        JLabel storehouse=new JLabel("�ֿ�");

        JButton submit=new JButton("ȷ��");
        panel2.add(name);panel2.add(addName);panel2.add(address);
        panel2.add(addAddress);panel2.add(phone);panel2.add(addPhone);
        panel2.add(product);panel2.add(addProduct);panel2.add(quantity);
        panel2.add(addQuantity);panel2.add(storehouse);panel2.add(addStorehouse);panel2.add(submit);
        frame.add(panel2);
        frame.setSize(300,300);
        frame.setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String demo = "insert into storehouse values(?,?,?,?,?,?)";
                    PreparedStatement ps = connection.prepareStatement(demo);
                    int quantity1=Integer.parseInt(addQuantity.getText());

                    ps.setString(1,addName.getText());
                    ps.setString(2,addAddress.getText());
                    ps.setString(3,addPhone.getText());
                    ps.setString(4,addProduct.getText());
                    ps.setInt(5,quantity1);
                    ps.setString(6,addStorehouse.getText());
                    int result = ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"��ӳɹ�");
                    frame.dispose();
                    new StoreHouse().display();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public void actionPerformed(ActionEvent e){
        StoreHouse demo3=new StoreHouse();
        if(e.getActionCommand()=="���"){
            this.add();
        }
        if(e.getActionCommand()=="��ѯ"){
            name3 = find.getText();
            this.dispose();
            demo3.find(name3);
        }
        if(e.getActionCommand()=="ɾ��"){
            this.delete();
        }
        if(e.getActionCommand()=="��ʾ"){
            this.dispose();
            demo3.display();
        }
    }
}

