package kemin.builder;

import java.io.IOException;
import java.util.Properties;

import freemarker.template.TemplateException;

import kemin.freemarker.HtmlFreemarker;
import kemin.tool.PropertiesTool;
import mysql.bugzilla.utility.BugReport;
import mysql.testlink.utility.TLBean;
import mysql.testlink.utility.TLReport;

public abstract class Director {
	
	public ReportBean bean = new ReportBean();
	public Properties prop = null;
	public HtmlFreemarker hf = null;
	
	public BugReport bugreport = null;
	public TLReport tlreport = null; 
	
	public Director(ReportBean bean, TLBean tlbean) {
		this.bean = bean;
		initProp();
		
		this.bugreport = new BugReport(this.bean, this.prop);
		this.tlreport = new TLReport(tlbean);
	}

	protected void initProp() {
		this.prop = Director.getProp();
	}
	
	/*
	 * 工具函数：得到产品对应的配置文件
	 */
	public static Properties getProp() {
		Properties prop = null;
		prop = PropertiesTool.read("config\\config.prop");
		return prop;
	}
	
	/*
	 * 对外提供的函数：选用不同的template文件；数据传递给freemarker对象
	 */
	public void prepare(String dir, String template) {
		this.hf = new HtmlFreemarker(dir, template);
		this.bugreport.prepare(this.hf);
		this.tlreport.prepare(this.hf);
		this.afterPrepare();
	}
	
	protected abstract void afterPrepare(); //prepare内容的补充
	
	protected abstract void readTemplate(); //根据信息，读取不同的template文件
	
	public void generate() {
		String reportName = this.getReportName();
		this.readTemplate();
		make(reportName);
	}
	
	/*
	 * 工具函数：报告文件名字
	 */
	public String getReportName() {
		String reportName = null;
		if(bean.getTesttype().equals("快速反馈")) {
			reportName = String.format("%s_%s_fast.html", 
					this.bean.getProduct(), this.bean.getVersion());
		} else  {
			reportName = String.format("%s_%s_final.html", 
					this.bean.getProduct(), this.bean.getVersion());
		}
		return reportName;
	}
	
	/*
	 * 工具函数：输出报告文件
	 */
	public void make(String reportfile) {
		try {  
            hf.make(reportfile);
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        }  
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
