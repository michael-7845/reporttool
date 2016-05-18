package kemin.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kemin.builder.ReportBean;


import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HtmlFreemarker {
	private Configuration cfg ;  
    private Template template;  
    private StringWriter stringWriter;   
    private PrintWriter printWriter;
    
    private String templatedir = null;
    private String templatefile = null;
    public SimpleHash root = new SimpleHash(); // 将会使用默认的包装器
    
    public HtmlFreemarker(String dir, String file) {
    	this.templatedir = dir;
    	this.templatefile = file;
    	try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    /** 
     * 初始化方法 
     * @throws IOException 
     */  
    public void init() throws IOException{  
         // 获取freemarker的Configuration实例  
        cfg = new Configuration();  
        cfg.setDefaultEncoding("UTF-8");
         //设置模板文件目录  
        cfg.setDirectoryForTemplateLoading(new File(templatedir));  
         // 取得模板文件  
        template = cfg.getTemplate(templatefile);  
//         //实例化一个字符串输出流  
//        stringWriter = new StringWriter();  
    }  
    /** 
     * 开始处理 
     * @throws TemplateException 
     * @throws IOException 
     */  
    public void make(String outputfile) throws TemplateException, IOException{ 
    	File dir = new File("report");
    	if( (!dir.exists()) || (!dir.isDirectory()) ) {
    		dir.mkdir();
    	}
        //实例化一个文件输出流  
    	printWriter = new PrintWriter("report/" + outputfile, "UTF-8");  
//        printWriter = new PrintWriter(templatedir + "/" + outputfile, "UTF-8");  
        //合并模板和数据模型，并输出到stringWriter中  
//        template.process(root, stringWriter);  
        template.process(root, printWriter); 
        
//        //打印结果  
//        System.out.println(stringWriter.toString());  
    } 
    
    public String getHtml() throws TemplateException, IOException {
    	//实例化一个文件输出流  
    	stringWriter = new StringWriter();  
        //合并模板和数据模型，并输出到stringWriter中  
        template.process(root, stringWriter);        
        //打印结果  
        System.out.println(stringWriter.toString());
        return stringWriter.toString();
    }
    
    @Deprecated
    public static void iterateMap() {
    	LearnFreemarker lf = new LearnFreemarker();
		HtmlFreemarker hf = new HtmlFreemarker("template", "htmltable.ftl");
        
		// 实例化一个放置参数的map  
	    Map<String, Integer> map = new HashMap(); 
	    
        //放置参数  
        map.put("a", 1); 
        map.put("b", 2);  
        map.put("c", 3);
        hf.root.put("argsMap", map);
		
		try {  
            hf.make("sample1.html");
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        }  
    }
    
    @Deprecated
    public static void iterateList() {
		HtmlFreemarker hf = new HtmlFreemarker("template", "list.ftl");
        
		// 实例化一个放置参数的map  
		List<Map<String, Integer>> l = new ArrayList<Map<String, Integer>>();
	    Map<String, Integer> map1 = new HashMap<String, Integer>(); 
	    Map<String, Integer> map2 = new HashMap<String, Integer>(); 
	    Map<String, Integer> map3 = new HashMap<String, Integer>(); 
	    
        //放置参数  
        map1.put("phone", 1); 
        map1.put("email", 2);  
        map1.put("address", 3);
        map2.put("phone", 4); 
        map2.put("email", 5);  
        map2.put("address", 6);
        map3.put("phone", 7); 
        map3.put("email", 8);  
        map3.put("address", 9);
        l.add(map1);
        l.add(map2);
        l.add(map3);
        hf.root.put("argsList", l);
		
		try {  
            hf.make("sample1.html");
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        }  
    }
    
    @Deprecated
    public static void bugreport(List<Map<String, String>> alldevbug,
    		ReportBean bean) {
		HtmlFreemarker hf = new HtmlFreemarker("template", "report_cn.ftl");
	    
        //放置参数  
        hf.root.put("alldevbug", alldevbug);
        hf.root.put("report", bean);
		
		try {  
            hf.make("sample1.html");
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        }  
    }
    
	public static void main(String[] args) {
		iterateList();
	}
}
