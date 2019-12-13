package user;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class treexml{
	public static void main(String[] args) throws IOException {
		//先创建一个Document对象，代表xml
		Document doc = DocumentHelper.createDocument();
		//创建根元素
		Element root = doc.addElement("Users");
		//创建子元素
		Element oneson = root.addElement("zhangsan");

		oneson.addElement("id").addText("0001");
		oneson.addElement("city").addText("beijing");
		oneson.addElement("age").addText("89");
		
		//根元素上再增加一个子元素
		Element twoson = root.addElement("lisi");
		twoson.addElement("id").addText("0002");
		twoson.addElement("city").addText("shanghai");
		twoson.addElement("age").addText("58");
		
		/**
		 * 利用IO流把xml写出去
		 */
		/**
		 *  FileWriter os = new FileWriter(new File("D:\\test\\user.xml"));
			doc.write(os);
			os.flush();
			os.close();
		 */
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter os = new XMLWriter(new FileWriter(new
		File("D:\\test\\user.xml")), format);
		os.write(doc);
		os.close();

	}
	
}