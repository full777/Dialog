package user;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
 
    public class JDOM {
 
        //注册用户信息
        public static String write(String n, String p, String id) {
            //UserInfo.xml文档的路径
        	//System.out.println("directory:"+directory);
            String path = "D:\\test\\userInfo.xml";
            //将xml文档封装成file
            File file = new File(path);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc; //声明document文档
            Element root;
            FileWriter fw;
            if (!file.exists()) {
            	//文件不存在,创建文件，写入根节点Users
            	String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<Users></Users>";
            	File dir = new File("D:\\test\\");
            	dir.mkdirs(); //创建多级目录
            	file = new File(dir,"userInfo.xml");
            	try {
            		
            		fw = new FileWriter(path);
            		fw.write(xml);
            		fw.flush();
            		fw.close();
            		}catch (IOException e){
            			e.printStackTrace();}	          	
            }
            //使用默认的sax解析器

            try {
            	doc = saxBuilder.build(file);
 
                //元素对应到xml文档中就是标签
                root = doc.getRootElement(); //得到根元素
                Element user = new Element("User"); //建立User元素
                Element name = new Element("name");//建立name元素
                Element passwd = new Element("passwd");//建立passwd元素
 
                /*首先检测xml文档中是否已经存在了ID号相同的用户，如果不存在才可以继续注册*/
                if (checkID(id, root)) {
                    //将ID设置为user的属性
                    user.setAttribute(new Attribute("id", id)); 
                    
                    //设置姓名和密码
                    name.setText(n);
                    passwd.setText(p);
 
                    //将name，passwd元素添加到user元素下
                    user.addContent(name);
                    user.addContent(passwd);
 
                    //将user元素添加到根元素下
                    root.addContent(user);
 
                    //输出xml文档
            		XMLOutputter out = new XMLOutputter();
                    out.output(doc, new FileOutputStream(file));
//                    OutputFormat format = OutputFormat.createPrettyPrint();
//            		XMLWriter os = new XMLWriter(new FileWriter(file), format);
//            		os.close();
                    return "Successful registration";//返回注册成功
                } else
                    //返回ID存在信息，重新输入ID
                    return "ID already exists, please input again";
 
            } catch (JDOMException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "ERROR";//未知错误
            
        }
 
        public static boolean checkID(String id, Element root) {
            // 检测ID是否存在
            boolean flag = true;
            @SuppressWarnings("unchecked")
            //得到User标签的所有子元素，并加入到map集合中
            List<Element> list = root.getChildren("User");
            //迭代检测是否存在ID
            Iterator<Element> it = list.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                if (e.getAttributeValue("id").equals(id)) {
                    flag = false;
                }
            }
            return flag;
 
        }
 
        //读取xml文档用于登录
        public static String read(String id, String passwd) {
            String path = "D:\\test\\userInfo.xml";
            File file = new File(path);
            SAXBuilder saxBuilder = new SAXBuilder();
 
            try {
                Document doc = saxBuilder.build(file);
                Element root = doc.getRootElement();
 
                //取出用户密码和姓名
                String info = getPasswd(root).get(id);
                if (info == null) {
                    return "User does not exist!!";
                }
                String[] buf = info.split("/");
 
                if (buf[0].equals(passwd)) {
                    return "Successful landing/" + buf[1];
                }
 
            } catch (JDOMException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "Wrong password!!";
        }
 
        @SuppressWarnings("unchecked")  //阻止没有进行类检查的警告
        /*将用户的密码和姓名添加到map集合中*/
    private static Map<String, String> getPasswd(Element root) {
            Map<String, String> map = new TreeMap<String, String>();//存贮用户信息
            List<Element> list = new ArrayList<Element>();
            //得到User标签的所有子元素信息
            list = root.getChildren("User");
            Iterator<Element> it = list.iterator();
            while (it.hasNext()) {
                Element e = it.next();
                String id = e.getAttributeValue("id");
                String passwd = e.getChildText("passwd");
                String name = e.getChildText("name");
                map.put(id, getInfo(passwd, name));
            }
            
            return map;
 
        }
 
        //处理用户密码和信息
        private static String getInfo(String passwd, String name) {
 
            return passwd + "/" + name;
 
        }
}