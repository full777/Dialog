package test;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IndexGUI extends JFrame{
	private JPanel contentPane;
	private static IndexGUI frame;
	public static void main(String[] args) {
		init();
	}
	//初始化方法
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new IndexGUI();
					frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public IndexGUI() {
		setTitle("knowYou");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置默认关闭方式，点击窗体关闭按钮可关闭窗
		setBounds(100,100,700,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));//设置面板边界宽度
		setContentPane(contentPane);//frame添加面板
		contentPane.setLayout(null);// 页面布局
		JLabel newlabel = new JLabel("Welcome to use KnowYou");  //设置一个label标题
		newlabel.setBounds(150,20,500,200);
		newlabel.setFont(new Font("黑体", Font.BOLD | Font.ITALIC, 30));
		contentPane.add(newlabel);  //将label添加到面板中
		
		JButton login = new JButton("Login");
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				event_Login(); //登陆
			}
		});
		login.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				event_Login();
			}
		});
		
		login.setBounds(132,270,180,45);
		contentPane.add(login); 
		
		JButton register = new JButton("Register");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				event_register(); //登陆
			}
		});
		register.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					event_register();
				}
				
			}
		});
		
		register.setBounds(450,270,180,45);
		contentPane.add(register); 	
	}
	
	private void event_Login() {
		setVisible(false);
		new LoginGUI().loginGUI();
	}
	private void event_register() {
		setVisible(false);
		new RegisterGUI().registerGUI();
	}
	
}
