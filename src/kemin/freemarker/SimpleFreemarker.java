package kemin.freemarker;

import java.io.File;  
import java.io.IOException;  
import java.io.StringWriter;  
import java.util.HashMap;  
import java.util.Map;  
  
import freemarker.template.Configuration;  
import freemarker.template.Template;  
import freemarker.template.TemplateException; 

/** 
 * 简单的freemarker测试类 
 * @author Administrator 
 * 
 */  
public class SimpleFreemarker {  
    private Configuration cfg ;  
    private Template template;  
    private StringWriter stringWriter;  
    private Map args;  
    /** 
     * 初始化方法 
     * @throws IOException 
     */  
    public void init() throws IOException{  
         // 获取freemarker的Configuration实例  
        cfg = new Configuration();  
         //设置模板文件目录  
        cfg.setDirectoryForTemplateLoading(new File("template"));  
         // 取得模板文件  
        template = cfg.getTemplate("template.ftl");  
         //实例化一个字符串输出流  
        stringWriter = new StringWriter();  
         // 实例化一个放置参数的map  
        args = new HashMap();  
    }  
    /** 
     * 开始处理 
     * @throws TemplateException 
     * @throws IOException 
     */  
    public void process() throws TemplateException, IOException{  
        //放置一个参数  
        args.put("message", "hello world");  
        //合并模板和数据模型，并输出到stringWriter中  
        template.process(args, stringWriter);  
        //打印结果  
        System.out.println(stringWriter.toString());  
    } 
    
    public static void main(String[] args) {
    	SimpleFreemarker s = new SimpleFreemarker();  
        try {  
            s.init();  
            s.process();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        }  
    }
}  
