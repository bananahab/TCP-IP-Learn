import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Product extends JFrame2 implements ActionListener {  //打开产品信息管理界面
    JPanel panel=new JPanel();
    JPanel panel1=new JPanel();
    JButton add=new JButton("添加");
    JButton delete=new JButton("删除");
    JButton alter=new JButton("修改");
    JButton search=new JButton("查询");
    JButton display=new JButton("显示");
    JTextField find=new JTextField(5);

    JMenuBar menuBar=new JMenuBar();
    JTable table;
    MysqlJDBCDriverConnection demo=new MysqlJDBCDriverConnection();
    Connection connection=demo.connect();
    String name3;

    public Product(){
        super();
        add("South",panel);
        this.add("Center",panel1);
        menuBar.add(add);
        menuBar.add(delete);
        menuBar.add(alter);
        menuBar.add(display);
        menuBar.add(search);
        menuBar.add(find);
        this.setBounds(300,300,600,450);
        add.addActionListener(this);
        delete.addActionListener(this);
        alter.addActionListener(this);
        search.addActionListener(this);
        display.addActionListener(this);
        this.setJMenuBar(menuBar);
        this.setResizable(true);
        show();
    }

    private void display() {   //显示产品信息
        int i = 0,j = 0;
        List<String> list = new ArrayList<>();
        try {
            String demo = "select * from product";
            PreparedStatement ps = connection.prepareStatement(demo);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                list.add(result.getString("name"));
                list.add(result.getString("price"));
                list.add(result.getString("place"));
                list.add(result.getString("assortment"));
                list.add(result.getString("storeHouse"));
                list.add(result.getString("stock"));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[][] playerInfo = new Object[i][6];
        String[] columnName={"名称","价格","产地","所属分类","所属仓库","库存"};
        try {
            String demo1 = "select * from product";
            PreparedStatement ps1 = connection.prepareStatement(demo1);
            ResultSet result1 = ps1.executeQuery();
            while (result1.next()){
                playerInfo[j][0]=result1.getString("name");
                playerInfo[j][1]=result1.getString("price");
                playerInfo[j][2]=result1.getString("place");
                playerInfo[j][3]=result1.getString("assortment");
                playerInfo[j][4]=result1.getString("storeHouse");
                playerInfo[j][5]=result1.getString("stock");
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

    public void delete(){    //删除产品信息
        int row=-1;
        row=table.getSelectedRow();
        String name1=null,price1=null,place1=null,assortment1=null,storeHouse1=null,stock1=null;
        if(row==-1){
            JOptionPane.showMessageDialog(null,"请选择要删除的记录");
        }else{
            int num = 0;
            try {
                String demo = "select * from product";
                PreparedStatement ps = connection.prepareStatement(demo);
                ResultSet result = ps.executeQuery();
                while (result.next() && num <= row) {
                    name1 = result.getString("name");
                    price1 = result.getString("price");
                    place1 = result.getString("place");
                    assortment1 = result.getString("assortment");
                    storeHouse1 = result.getString("storeHouse");
                    stock1 = result.getString("stock");
                    num++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            int num1 = 0;
            try {
                String demo2 = "delete from product where name='"+name1+"'";
                PreparedStatement ps2 = connection.prepareStatement(demo2);
                int result1 = ps2.executeUpdate();
                JOptionPane.showMessageDialog(null, "记录删除成功！");
                this.dispose();
                new Product().display();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){    //更新产品信息
        int row=-1;
        row=table.getSelectedRow();
        String name=null,price=null,place=null,assortment=null,storeHouse=null,stock=null;
        if(row==-1){
            JOptionPane.showMessageDialog(null,"请选择要修改的选项");
        }else try {
            int num = 0;
            String demo = "select * from product";
            PreparedStatement ps = connection.prepareStatement(demo);
            ResultSet result = ps.executeQuery();
            while (result.next() && num <= row) {
                name = result.getString("name");
                price = result.getString("price");
                place = result.getString("place");
                assortment = result.getString("assortment");
                storeHouse = result.getString("storeHouse");
                stock = result.getString("stock");
                num++;
            }

            JFrame frame = new JFrame();
            JPanel panel2 = new JPanel();
            JTextField addName = new JTextField(22);
            JTextField addPrice = new JTextField(22);
            JTextField addPlace = new JTextField(22);
            JTextField addAssortment = new JTextField(22);
            JTextField addStoreHouse = new JTextField(22);
            JTextField addStock = new JTextField(22);

            JLabel name1 = new JLabel("名称");
            JLabel price1 = new JLabel("价格");
            JLabel place1 = new JLabel("产地");
            JLabel assortment1 = new JLabel("所属分类");
            JLabel storeHouse1 = new JLabel("仓库");
            JLabel stock1 = new JLabel("库存");

            JButton submit = new JButton("确认");panel2.add(name1);panel2.add(addName);
            panel2.add(price1);panel2.add(addPrice);panel2.add(place1);
            panel2.add(addPlace);panel2.add(assortment1);panel2.add(addAssortment);
            panel2.add(storeHouse1);panel2.add(addStoreHouse);panel2.add(stock1);
            panel2.add(addStock);panel2.add(submit);frame.add(panel2);
            frame.setSize(300, 300);
            frame.setVisible(true);

            String finalName = name;
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String demo1 = "update product set name=?, price=?,place=?,assortment=?,storeHouse=?,stock=? where name=?";
                        PreparedStatement ps1 = connection.prepareStatement(demo1);
                        int price2 = Integer.parseInt(addPrice.getText());
                        int storeHouse2 = Integer.parseInt(addStoreHouse.getText());
                        int stock2 = Integer.parseInt(addStock.getText());

                        ps1.setString(1, addName.getText());
                        ps1.setInt(2, price2);
                        ps1.setString(3, addPlace.getText());
                        ps1.setString(4, addAssortment.getText());
                        ps1.setInt(5, storeHouse2);
                        ps1.setInt(6, stock2);
                        ps1.setString(7,finalName);
                        int result1 = ps1.executeUpdate();
                        JOptionPane.showMessageDialog(null, "更新成功");
                        frame.dispose();
                        new Product().display();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void find(String name3) {   //查询产品信息

        Object[][] playerInfo = new Object[1][6];
        String[] columnNames = {"名称", "价格", "产地", "所属分类", "所属仓库", "库存"};
        try {
            String demo = "select * from product where name='"+name3+"'";
            PreparedStatement ps = connection.prepareStatement(demo);
            ResultSet result = ps.executeQuery();
            System.out.println(name3);
            while(result.next()) {
                playerInfo[0][0] =result.getString("name") ;
                playerInfo[0][1] = result.getString("price");
                playerInfo[0][2] = result.getString("place");
                playerInfo[0][3] = result.getString("assortment");
                playerInfo[0][4] = result.getString("storeHouse");
                playerInfo[0][5] = result.getString("stock");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        table=new JTable(playerInfo,columnNames);
        panel1.add(table);
        JScrollPane scroll=new JScrollPane(table);
        this.add(scroll);
    }

    public void add(){   //添加产品
        JFrame frame=new JFrame();
        JPanel panel2=new JPanel();
        JTextField addName=new JTextField(22);
        JTextField addPrice=new JTextField(22);
        JTextField addPlace=new JTextField(22);
        JTextField addAssortment=new JTextField(22);
        JTextField addStoreHouse=new JTextField(22);
        JTextField addStock=new JTextField(22);

        JLabel name=new JLabel("名称");
        JLabel price=new JLabel("价格");
        JLabel place=new JLabel("产地");
        JLabel assortment=new JLabel("所属分类");
        JLabel storeHouse=new JLabel("仓库");
        JLabel stock=new JLabel("库存");

        JButton submit=new JButton("确认");
        panel2.add(name);panel2.add(addName);panel2.add(price);
        panel2.add(addPrice);panel2.add(place);panel2.add(addPlace);
        panel2.add(assortment);panel2.add(addAssortment);panel2.add(storeHouse);
        panel2.add(addStoreHouse);panel2.add(stock);panel2.add(addStock);panel2.add(submit);
        frame.add(panel2);
        frame.setSize(300,300);
        frame.setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String demo = "insert into product values(?,?,?,?,?,?)";
                    PreparedStatement ps = connection.prepareStatement(demo);
                    int price1=Integer.parseInt(addPrice.getText());
                    int storeHouse1=Integer.parseInt(addStoreHouse.getText());
                    int stock1=Integer.parseInt(addStock.getText());

                    ps.setString(1,addName.getText());
                    ps.setInt(2,price1);
                    ps.setString(3,addPlace.getText());
                    ps.setString(4,addAssortment.getText());
                    ps.setInt(5,storeHouse1);
                    ps.setInt(6,stock1);
                    int result = ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"添加成功");
                    frame.dispose();
                    new Product().display();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public void actionPerformed(ActionEvent e){
        Product demo3=new Product();
        if(e.getActionCommand()=="添加"){
            this.add();
        }
        if(e.getActionCommand()=="查询"){
            name3 = find.getText();
            this.dispose();
            demo3.find(name3);
        }
        if(e.getActionCommand()=="修改"){
            this.dispose();
            this.update();
        }

        if(e.getActionCommand()=="删除"){
            this.dispose();
            this.delete();
        }
        if(e.getActionCommand()=="显示"){
            this.dispose();
            demo3.display();
        }
    }
