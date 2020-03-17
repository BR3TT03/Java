import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.TextArea;

public class Builder extends JFrame {

	private JPanel contentPane;
	private JTextField txtPassword;
	private JTextField textField;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Builder frame = new Builder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Builder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 75, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
		lblUserName.setBounds(190, 163, 137, 32);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
		lblPassword.setBounds(190, 225, 137, 32);
		contentPane.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
		txtPassword.setBounds(337, 163, 242, 32);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		textField = new JTextField();
		textField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
		textField.setBounds(337, 225, 242, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 24));
		btnLogin.setBounds(389, 280, 150, 32);
		contentPane.add(btnLogin);
		
		label = new JLabel("new");
		label.setBorder(new LineBorder(Color.GRAY, 2, true));
		label.setIcon(new ImageIcon("C:\\Users\\rockb\\Downloads\\login3 (1).jpg"));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);
		
		
	}

	public JPanel getContentPane() {
		return contentPane;
	}
	public JLabel getLabel() {
		return label;
	}
}
