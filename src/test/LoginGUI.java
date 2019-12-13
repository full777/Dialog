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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import user.JDOM;

public class LoginGUI extends JFrame {
	 private JPanel contentPane;  //面板
     private JTextField IDtxt; //ID输入框
     private JLabel Passwdlabel;//密码标签
     private JPasswordField passwordField;//密码输入框
     private JButton login;//登陆按钮
     private JButton back;//返回按钮
     private LoginGUI frame;
     
 	//初始化方法
 	public void loginGUI() {
 		EventQueue.invokeLater(new Runnable() {
 			public void run() {
 				try {
 					frame = new LoginGUI();
 					frame.setVisible(true);		
 				}catch(Exception e) {
 					e.printStackTrace();
 				}
 			}
 		});
 	}
 	
 	public LoginGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,700,500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        JLabel label = new JLabel("Welcome to use KnowYou");
        label.setFont(new Font("黑体", Font.BOLD | Font.ITALIC, 30));
        label.setBounds(142, 54, 386, 35);
        contentPane.add(label);

        

        JLabel IDlabel = new JLabel("Please input ID");//ID标签
        IDlabel.setBounds(118, 150, 91, 39);
        contentPane.add(IDlabel);

        IDtxt = new JTextField();
        IDtxt.setBounds(276, 160, 126, 21);
        contentPane.add(IDtxt);
        IDtxt.setColumns(10);

        
        Passwdlabel = new JLabel("Please input password");
        Passwdlabel.setBounds(118, 190, 150, 50);
        contentPane.add(Passwdlabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(276, 200, 126, 21);
        contentPane.add(passwordField);

        login = new JButton("login");
        login.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		event_login();
        	}
        });
        login.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
        			event_login();
        		}
        	}
        });
        login.setBounds(160, 320, 93, 23);
        contentPane.add(login);
        
        //返回到最初界面
        back = new JButton("back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 IndexGUI.init();
                 setVisible(false);
            }
        });
        back.setBounds(400, 320, 93, 23);
        contentPane.add(back);      
 	}

 	private void event_login() {
 		String idtext = IDtxt.getText();
 		String passwdtext = new String(passwordField.getPassword());
 		String flag = JDOM.read(idtext, passwdtext);
 		System.out.println(flag);
 		if(flag.contains("Successful")) {
 			JOptionPane.showMessageDialog(contentPane, "Welcome："+idtext,"Welcome",JOptionPane.PLAIN_MESSAGE);
            UsersGUI.init(idtext); //用户写日记界面
            setVisible(false);
 		}else {
 			JOptionPane.showMessageDialog(contentPane, "login failed!","login failed!",JOptionPane.PLAIN_MESSAGE);
 		}
 	}
}
