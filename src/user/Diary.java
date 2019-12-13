package user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class Diary {

		public static String addDiary(String pathname, String title, String txt) {
			File dirFile = new File(pathname);
			BufferedWriter buff_w = null;  //声明
			dirFile.mkdirs();  //创建多级目录
			String path = dirFile.getAbsolutePath();
			File file = new File(dirFile,title+".ky");
			try {
				buff_w = new BufferedWriter(new FileWriter(file,true));  //实例化字符流对象
				buff_w.write(txt);
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				if(buff_w != null) {
					try {
						buff_w.close();  //关闭字符流
					}catch(IOException e) {
						e.printStackTrace();
					}
				}		
			}	
			return path;
		}
		
		public static void read(File file,Document doc) {
			BufferedReader buff_r = null;  //声明读取字符流
			String txt = null;  //行读取字符流
			try {
				buff_r = new BufferedReader(new FileReader(file));
				// 获取换行符,因为Linux和Windows下的换行符是不一样的。这样可以增强跨平台性
                String line = System.getProperty("line.separator");  
                while((txt = buff_r.readLine())!= null) {
                	try {
                		doc.insertString(doc.getLength(), txt+line,null);
                	}catch(BadLocationException e) {
                		e.printStackTrace();
                	}
                }
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				if(buff_r != null) {
					try {buff_r.close();}catch(IOException e) {
						e.printStackTrace();
					}			
				}
			}
		}
}
