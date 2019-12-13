package test;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import user.JDOM;



public class RegisterGUI extends JFrame{
	private JPanel contentPane;
	private JTextField nametext;
	private JTextField idtext;
	private JTextField passwdtext;
	private RegisterGUI frame;
	public static void main(String[] args) {
		
	}
	//初始化方法
	public void registerGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RegisterGUI();
					frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//构造方法
	public RegisterGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置默认关闭方式，点击窗体关闭按钮可关闭窗
		setBounds(100,100,600,400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));//设置面板边界宽度
		setContentPane(contentPane);//frame添加面板
		contentPane.setLayout(null);// 页面布局
		
		JLabel newlabel = new JLabel("Welcome to use KnowYou");  //设置一个label标题
		newlabel.setBounds(100, 35, 386, 35);
		newlabel.setFont(new Font("黑体", Font.BOLD | Font.ITALIC, 30));
		contentPane.add(newlabel);  //将label添加到面板中
		
		JLabel namelabel = new JLabel("Please input user name");//设置提示姓名输入标签
        namelabel.setBounds(102,110,151,23);
        contentPane.add(namelabel);

        JLabel IDlabel = new JLabel("please input user ID");//设置提示ID输入标签
        IDlabel.setBounds(102,180,151,23);
        contentPane.add(IDlabel);

        JLabel passwdlaber = new JLabel("please input user password");//设置提示密码输入标签
        passwdlaber.setBounds(102,244,163,23);
        contentPane.add(passwdlaber);
	
        nametext = new JTextField();//普通文本输入框
        nametext.setBounds(271, 111, 92, 21);//设置位置及大小
        contentPane.add(nametext);
        nametext.setColumns(10);  //设置长度

        //ID
        idtext = new JTextField();
        idtext.setBounds(271, 181, 92, 21);
        contentPane.add(idtext);
        idtext.setColumns(8);

        //密码
        passwdtext = new JTextField();
        passwdtext.setBounds(271, 245, 92, 21);
        contentPane.add(passwdtext);
        passwdtext.setColumns(10);

      //注册按钮
        JButton register = new JButton("Sign Up");
        register.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String name = nametext.getText();
        		String id = idtext.getText();
        		String passwd = passwdtext.getText();
        		String errtext;  //注册提示信息
        		int errid = 0;   //失败为0，成功为1
        		if(name==null) {
        			errtext = "姓名不能为空";
        			errid = 0;
        		}else if((id ==null) ||(id.length() <4)) {
        			errtext = "ID不能为空或长度小于4位";
        			errid = 0;
        		}else if((passwd ==null)||(passwd.length()<6 )) {
        			errtext = "密码不能为空或长度小于6位";
        			errid = 0;
        		}else {
        			String text = JDOM.write(name, passwd, id);
        			errtext = text;
        			if(text.contains("Successful")) {
        				errid = 1;
        			}
        			}
        		
        		JOptionPane.showMessageDialog(contentPane, errtext,"information", JOptionPane.PLAIN_MESSAGE);
        		if(errid ==1) {
        			//回到初始页重新登陆
            		setVisible(false);	
            		new IndexGUI().init();
        		}     		
        	}
        });
        register.setBounds(161, 305, 93, 23);
        contentPane.add(register);     
        
        JButton back = new JButton("BACK"); //返回按钮
        back.addMouseListener(new MouseAdapter(){
        	@Override
            public void mouseClicked(MouseEvent e){
                IndexGUI.init(); //创建首页
                setVisible(false);//当前页面不可见
            }
        });
        back.setBounds(301,305,93,23);
        contentPane.add(back);
        
        JLabel lblNewLabel = new JLabel("(There are 4 + numbers)");
        lblNewLabel.setBounds(373, 184, 163, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("(There are 6 + numbers)");
        lblNewLabel_1.setBounds(373, 248, 163, 15);
        contentPane.add(lblNewLabel_1);

        
	}



}
