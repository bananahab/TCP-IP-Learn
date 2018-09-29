import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PutOut extends JFrame2 implements ActionListener{  //打开出库管理系统
    JPanel panel=new JPanel();
    JPanel panel1=new JPanel();
    JButton add=new JButton("添加");
    JButton delete=new JButton("删除");
    JButton search=new JButton("查询");
    JButton display=new JButton("显示");
    JTextField find=new JTextField(5);

    JMenuBar menuBar=new JMenuBar();
    JTable table;
    MysqlJDBCDriverConnection demo=new MysqlJDBCDriverConnection();
    Connection connection=demo.connect();
    public String name3;

    public PutOut(){
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
            String demo = "select * from putout";
            PreparedStatement ps = connection.prepareStatement(demo);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                list.add(result.getString("name"));
                list.add(result.getString("address"));
                list.add(result.getString("phone"));
                list.add(result.getString("product"));
                list.add(result.getString("quantity"));
                list.add(result.getString("price"));
                list.add(result.getString("date"));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[][] playerInfo = new Object[i][7];
        String[] columnName={"客户名称","地址","电话","产品","数量","价格","时间"};
        try {
            String demo1 = "select * from putout";
            PreparedStatement ps1 = connection.prepareStatement(demo1);
            ResultSet result1 = ps1.executeQuery();
            while (result1.next()){
                playerInfo[j][0]=result1.getString("name");
                playerInfo[j][1]=result1.getString("address");
                playerInfo[j][2]=result1.getString("phone");
                playerInfo[j][3]=result1.getString("product");
                playerInfo[j][4]=result1.getString("quantity");
                playerInfo[j][5]=result1.getString("price");
                playerInfo[j][6]=result1.getString("date");
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
        String name1=null,address1=null,phone1=null,product1=null,quantity1=null,price1=null,date1=null;
        if(row==-1){
            JOptionPane.showMessageDialog(null,"请选择要删除的记录");
        }else{
            int num = 0;
            try {
                String demo = "select * from putout";
                PreparedStatement ps = connection.prepareStatement(demo);
                ResultSet result = ps.executeQuery();
                while (result.next() && num <= row) {
                    name1 = result.getString("name");
                    address1 = result.getString("address");
                    phone1 = result.getString("phone");
                    product1= result.getString("product");
                    quantity1 = result.getString("quantity");
                    price1=result.getString("price");
                    date1=result.getString("date");
                    num++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            int num1 = 0;
            try {
                String demo2 = "delete from putout where name='"+name1+"'";
                PreparedStatement ps2 = connection.prepareStatement(demo2);
                int result1 = ps2.executeUpdate();
                JOptionPane.showMessageDialog(null, "记录删除成功！");
                this.dispose();
                new PutOut().display();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void find(String name3) {

        Object[][] playerInfo = new Object[1][7];
        String[] columnNames = {"客户名称", "地址", "电话", "产品", "数量","价格","时间"};
        try {
            String demo = "select * from putout where name='"+name3+"'";
            PreparedStatement ps = connection.prepareStatement(demo);
            ResultSet result = ps.executeQuery();
            while(result.next()) {
                playerInfo[0][0] =result.getString("name") ;
                playerInfo[0][1] = result.getString("address");
                playerInfo[0][2] = result.getString("phone");
                playerInfo[0][3] = result.getString("product");
                playerInfo[0][4] = result.getString("quantity");
                playerInfo[0][5] = result.getString("price");
                playerInfo[0][6] =result.getString("date");
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
        JTextField addPrice=new JTextField(22);
        JTextField addDate=new JTextField(22);

        JLabel name=new JLabel("客户名称");
        JLabel address=new JLabel("地址");
        JLabel phone=new JLabel("电话");
        JLabel product=new JLabel("产品");
        JLabel quantity=new JLabel("数量");
        JLabel price=new JLabel("价格");
        JLabel date=new JLabel("时间");

        JButton submit=new JButton("确认");
        panel2.add(name);panel2.add(addName);panel2.add(address);
        panel2.add(addAddress);panel2.add(phone);panel2.add(addPhone);
        panel2.add(product);panel2.add(addProduct);panel2.add(quantity);
        panel2.add(addQuantity);panel2.add(price);panel2.add(addPrice);
        panel2.add(date);panel2.add(addDate);panel2.add(submit);
        frame.add(panel2);
        frame.setSize(300,300);
        frame.setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String demo = "insert into putout values(?,?,?,?,?,?,?)";
                    PreparedStatement ps = connection.prepareStatement(demo);
                    int quantity1=Integer.parseInt(addQuantity.getText());
                    int price1=Integer.parseInt(addPrice.getText());

                    ps.setString(1,addName.getText());
                    ps.setString(2,addAddress.getText());
                    ps.setString(3,addPhone.getText());
                    ps.setString(4,addProduct.getText());
                    ps.setInt(5,quantity1);
                    ps.setInt(6,price1);
                    ps.setString(7,addDate.getText());
                    int result = ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"添加成功");
                    frame.dispose();
                    new PutOut().display();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public void actionPerformed(ActionEvent e){
        PutOut demo3=new PutOut();
        if(e.getActionCommand()=="添加"){
            this.add();
        }
        if(e.getActionCommand()=="查询"){
            name3 = find.getText();
            this.dispose();
            demo3.find(name3);
        }
        if(e.getActionCommand()=="删除"){
            this.delete();
        }
        if(e.getActionCommand()=="显示"){
            this.dispose();
            demo3.display();
        }
    }
}
