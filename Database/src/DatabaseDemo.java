import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DatabaseDemo extends JFrame implements ActionListener {
	JTextField id,name,age;
	JLabel ID,NAME,AGE;
	JButton jb;
	String Name,Id;
	int Age;
	public DatabaseDemo() {
		setBounds(400,100,300,300);
		setLayout(null);
		ID=new JLabel("ID:");
		NAME=new JLabel("NAME:");
		AGE=new JLabel("AGE:");
		id=new JTextField(10);
		name=new JTextField(50);
		age=new JTextField(10);
		jb=new JButton("Insert");
		ID.setBounds(10, 50, 50, 20);
		add(ID);
		id.setBounds(70, 50, 100, 20);
		add(id);
		NAME.setBounds(10, 80, 50, 20);
		add(NAME);
		name.setBounds(70, 80, 100, 20);
		add(name);
		AGE.setBounds(10, 110, 50, 20);
		add(AGE);
		age.setBounds(70,110,100,20);
		add(age);
		jb.setBounds(70, 135, 100, 20);
		add(jb);
		jb.addActionListener(this);
		//setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		this.setTitle("DataBaseDemo");
		setSize(500,500);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Id=id.getText();
		Name=name.getText();
		Age=Integer.parseInt(age.getText());
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
		String q="insert into student values(?,?,?)";
		PreparedStatement pre=con.prepareStatement(q);
		pre.setString(1,Id);
		pre.setString(2,Name);
		pre.setInt(3,Age);
		pre.executeUpdate();
		System.out.println("Data Inserted");
		pre.close();
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}

	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DatabaseDemo();



}
}
