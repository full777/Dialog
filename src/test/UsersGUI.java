package test;
import user.Diary;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document ;


public class UsersGUI extends JFrame{
	private JPanel contentPane;
	private   JInternalFrame interframe ;
	private boolean isVisable = false;
	private UsersGUI frame;
    private JTextField textField;
    //文件选择组件，用于用户阅读日记和删除日记时选择文件。
    private JFileChooser chooser;
	private static String pathname;
	public  static void init(String name) {
		pathname = name;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersGUI frame = new UsersGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    
                }
			}
		});
	}
	
	public UsersGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //如果不设置用默认值的话，用户关闭程序知识隐藏窗体不会杀死进程
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setToolTipText("KonwYou");
        tabbedPane.setBounds(0, 0, 574, 67);
        contentPane.add(tabbedPane);

        final JPanel panel = new JPanel(); //面板
        tabbedPane.addTab("Management Journal", null, panel, null);

        chooser = new JFileChooser(".\\"+pathname);//初始化JFileChooser，并设置默认目录为用户目录
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Allowed","ky");//文件选择器，只允许选择.ky文件
        chooser.setFileFilter(filter);
        
        JButton readbutton = new JButton("Read my dialog");
        readbutton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int value = chooser.showOpenDialog(panel);
        		interframe = new JInternalFrame("Read the diary",false, true, false, false);//建立一个可关闭、不可更改大小、具有标题、可最大化与最小化
        		interframe.setBounds(0, 77, 580, 270);
        		contentPane.add(interframe);
        		interframe.getContentPane().setLayout(null);
        		
        		JTextPane txtDiary = new JTextPane();  //显示text的面板
        		txtDiary.setBounds(0, 0, 568, 270);
        		txtDiary.setBackground(Color.CYAN);
        		txtDiary.setEditable(false);
        		interframe.getContentPane().add(txtDiary);   // 不要误写成面板对象
        		
        		Document doc = txtDiary.getDocument();   //写入文本到text面板
	
        		
        		if (value == JFileChooser.APPROVE_OPTION) {
        			 
        	        //得到用户选择的文件
        	        File file = chooser.getSelectedFile();
        	            if(file.exists())    //如果文件存在
        	                {
        	                    //Diary.read()方法读取日记;
        	                Diary.read(file, doc);
        	                interframe.setVisible(true);
        	                isVisable = true;  //表示interframe此时是可见状态
        	                }else {
        	                	frame.setVisible(false);
        	                }
        	            
        		}else {
        			interframe.setVisible(false);
        			frame.setVisible(true);
        		}
        	}
        }); 
        panel.add(readbutton);
        //创建新建dialog按钮
        JButton addbutton = new JButton("Create my dialog");
        addbutton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(isVisable) {
        			isVisable = false;
        			interframe.setVisible(false);
        		}
        		interframe = new JInternalFrame(pathname,false, true, false, false);//建立一个可关闭、不可更改大小、不具有标题、不可最大化与最小化
        		interframe.setBounds(0, 77, 580, 270);
        		contentPane.add(interframe);
        		interframe.getContentPane().setLayout(null);
        		textField = new JTextField();
        		textField.setBounds(76, 0, 492, 22);
        		interframe.getContentPane().add(textField);
        	    textField.setColumns(10);
        	    
        	    JLabel label = new JLabel("日记标题");
        	    
        	    label.setFont(new Font("黑体", Font.PLAIN, 12));
        	    label.setBounds(20, 3, 52, 20);
        	    interframe.getContentPane().add(label);
        	    
        	    JEditorPane editorPane = new JEditorPane();
        	    editorPane.setBounds(0,31,568,179);
        	    editorPane.setBackground(Color.CYAN);
        	    interframe.getContentPane().add(editorPane);
        	    
        	    JButton savebutton = new JButton("保存");
        	    savebutton.setBounds(465, 213, 93, 23);
        	    savebutton.addMouseListener(new MouseAdapter() {
        	    	@Override
        	    	public void mouseClicked(MouseEvent e) {
        	    		//获取标题
        	            String title = textField.getText();    
        	            //获取内容
        	            String txt = editorPane.getText();
        	            //调用Diary.addDiary()方法建立日记
        	            String path = Diary.addDiary(pathname, title, txt);
        	            JOptionPane.showMessageDialog(editorPane, "保存成功！保存地址："+path);
        	            interframe.setVisible(false); 
        	            
        	    	}
        	    });
        	    interframe.getContentPane().add(savebutton);
        	    interframe.setVisible(true); 	    
        	}
        	
        });
        
        panel.add(addbutton);
      //删除按钮
        JButton delButton = new JButton("Delete");
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file=null;
                int value=chooser.showOpenDialog(panel);
                if(value==JFileChooser.APPROVE_OPTION)
                {
                    file=chooser.getSelectedFile();

                    //删除确认框，用于确定用户是否确定删除      
                    int x= JOptionPane.showConfirmDialog(panel,"Confirm delete?","Please confirm",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);

                    if(file.exists())
                    {
                    	//当用户选择了OK时，调用删除方法
                    	if(x==JOptionPane.OK_OPTION)
                    	{
                    		file.delete();

                    		//打印删除成功提示信息
                    		JOptionPane.showMessageDialog(panel, "Delete Success!","information", JOptionPane.PLAIN_MESSAGE);
                    	}

                    }

                }

            }
        	});
        	panel.add(delButton);
        	JButton back = new JButton("BACK");
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	setVisible(false);
                    IndexGUI.init();    
                }
            });
            panel.add(back);
	}
}


