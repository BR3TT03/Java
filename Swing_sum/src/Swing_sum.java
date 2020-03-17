import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Swing_sum extends JFrame implements ActionListener {
	JLabel num1,num2,sum;
	JTextField n1,n2,sm;
	JMenu jm;
	JMenuItem one,two;
	JMenuBar jmb;
	JButton Jb;
	public Swing_sum()
	{
		jmb = new JMenuBar();
		jm =new JMenu("Sum");
		one = new JMenuItem("one");
		two = new JMenuItem("two");
		jm.add(two);
		jm.add(one);
		one.addActionListener(this);
    	two.addActionListener(this);
    	
		num1= new JLabel("Num1: ");
		num2= new JLabel("Num2: ");
		sum= new JLabel("Sum: ");
		n1=new JTextField(5);
		n2=new JTextField(5);
		sm=new JTextField(5);
		Jb=new JButton("ADD");
		Jb.addActionListener(this);
    	add(num1);
    	add(n1);
    	add(num2);
    	add(n2);
    	add(sum);
    	add(sm);
    	add(Jb);
    	jmb.add(jm);
    	setJMenuBar(jmb);
    	setLayout(new FlowLayout());
		setSize(500,500);
		setVisible(true);
		
	}
	private void getValueAt() {
		// TODO Auto-generated method stub
		
	}
	public void actionPerformed(ActionEvent e)
	{
		int N1=Integer.parseInt(n1.getText());
		int N2=Integer.parseInt(n2.getText());
		sm.setText(Integer.toString(N1+N2));
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Swing_sum();

	}

}
