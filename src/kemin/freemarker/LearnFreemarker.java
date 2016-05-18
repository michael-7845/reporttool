package kemin.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class LearnFreemarker {
	private Configuration cfg ;  
    private Template template;  
    private StringWriter stringWriter;   
    private PrintWriter printWriter;
    
    private String templatedir = "template";
    private String templatefile = "hashmap2.ftl";
    // 实例化一个放置参数的map  
    private Map<String, Integer> map = new HashMap(); 
    private SimpleHash root = new SimpleHash(); // 将会使用默认的包装器
   
    /** 
     * 初始化方法 
     * @throws IOException 
     */  
    public void init() throws IOException{  
         // 获取freemarker的Configuration实例  
        cfg = new Configuration();  
         //设置模板文件目录  
        cfg.setDirectoryForTemplateLoading(new File(templatedir));  
         // 取得模板文件  
        template = cfg.getTemplate(templatefile);  
         //实例化一个字符串输出流  
        stringWriter = new StringWriter();  
         //实例化一个文件输出流  
        printWriter = new PrintWriter("template/hash.html");  
    }  
    /** 
     * 开始处理 
     * @throws TemplateException 
     * @throws IOException 
     */  
    public void process() throws TemplateException, IOException{  
        //放置参数  
        map.put("a", 1); 
        map.put("b", 2);  
        map.put("c", 3);
        root.put("argsMap", map);
        //合并模板和数据模型，并输出到stringWriter中  
//        template.process(root, stringWriter);  
        template.process(root, printWriter);  
        //打印结果  
        System.out.println(stringWriter.toString());  
    } 
    
	public static void main(String[] args) {
		LearnFreemarker lf = new LearnFreemarker();  
        try {  
            lf.init();  
            lf.process();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        }  
	}

}
