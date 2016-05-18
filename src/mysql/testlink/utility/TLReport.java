package mysql.testlink.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import freemarker.template.TemplateException;

import kemin.freemarker.HtmlFreemarker;

public class TLReport {
	public String project;
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}

	public String testplan;
	public String getTestplan() {
		return testplan;
	}
	public void setTestplan(String testplan) {
		this.testplan = testplan;
	}
	
	public String build; 
	public String getBuild() {
		return build;
	}
	public void setBuild(String build) {
		this.build = build;
	}
	
	public String tester;
	public String getTester() {
		return tester;
	}
	public void setTester(String tester) {
		this.tester = tester;
	}
	
	public List<Map<String, String>> executions;
	public List<Map<String, String>> assignments;
	public ByExetype byExetype;
	
	public TLReport(String project, String testplan, String build, String tester) {
		super();
		this.project = project;
		this.testplan = testplan;
		this.build = build;
		this.tester = tester; //在HIKe手机测试中，此值不被使用
		init();
	}
	
	public TLReport(TLBean bean) {
		super();
		this.project = bean.getProject();
		this.testplan = bean.getTestplan();
		this.build = bean.getBuild();
		this.tester = bean.getTester();
		init();
	}
	
	private void init() {
		this.executions = 
				Query.search(SqlStatement.getSql_executions(
						this.project, this.testplan,
						this.build));
		this.assignments = 
				Query.search(SqlStatement.getSql_assignments(
						this.project, this.testplan,
						this.build));
		this.byExetype = new ByExetype(this.executions, this.assignments);
		
		System.out.println(SqlStatement.getSql_executions(
						this.project, this.testplan,
						this.build));
		System.out.println(SqlStatement.getSql_assignments(
				this.project, this.testplan,
				this.build));
	}
	
	/*
	 * 准备数据：把处理好的数据放入freemarker对象
	 */
	public void prepare(HtmlFreemarker hf) {
		/*testlink部分*/
        hf.root.put("tlreport", this);
        hf.root.put("tlexe", this.executions);
        hf.root.put("tlass", this.assignments);
        hf.root.put("tlnotrun", this.byExetype.byType.get(ByExetype.allExetype[0]));
        hf.root.put("tlpass", this.byExetype.byType.get(ByExetype.allExetype[1]));
        hf.root.put("tlfail", this.byExetype.byType.get(ByExetype.allExetype[2]));
        hf.root.put("tlblock", this.byExetype.byType.get(ByExetype.allExetype[3]));
        hf.root.put("tlnotavail", this.byExetype.byType.get(ByExetype.allExetype[4]));
        hf.root.put("tlall", this.byExetype.all);
        hf.root.put("tlallbyts", this.byExetype.bySuiteType.bySuite);
	}
	
	/**
	 * 工具函数： 根据TestLink的project获得下面的所有testplan
	 */
	public static List<String> getAllTestplan(String project) {
		List<Map<String, String>> templist = 
				Query.search(SqlStatement.getSql_testplans(project));
		List<String> list = new ArrayList<String>();
		for(Map<String, String> map: templist) {
			list.add(map.get("name"));
		}
		return list;
	}
	
	/**
	 * 工具函数： 根据TestLink的testplan获得下面的所有build
	 */
	public static List<String> getAllBuild(String testplan) {
		List<Map<String, String>> templist = 
				Query.search(SqlStatement.getSql_builds(testplan));
		debugList(templist);
		System.out.println(SqlStatement.getSql_builds(testplan));
		List<String> list = new ArrayList<String>();
		for(Map<String, String> map: templist) {
			list.add(map.get("name"));
			System.out.println(map.get("name"));
		}
		return list;
	}
	
	/**
	 * 工具函数： 根据TestLink的build获得下面的指派过用例的所有tester
	 */
	public static List<String> getAllTester(String build) {
		List<Map<String, String>> templist = 
				Query.search(SqlStatement.getSql_testers(build));
		List<String> list = new ArrayList<String>();
		for(Map<String, String> map: templist) {
			list.add(map.get("login"));
		}
		return list;
	}

	public static void debugList(List<Map<String, String>> list) {
		System.out.println("size: " + list.size());
		Iterator<Map<String, String>> i = list.iterator();
		while(i.hasNext()) {
			Map<String, String> m = i.next();
			Iterator<String> im = m.keySet().iterator();
			while(im.hasNext()) {
				String key = im.next();
				System.out.print(String.format("%s:%s, ", key, m.get(key)));
			}
			System.out.println();
		}
	}
	
	public static void debugMap(Map<String, String> map) {
		Iterator<String> i = map.keySet().iterator();
		while(i.hasNext()) {
			String key = i.next();
			System.out.print(String.format("%s:%s, ", key, map.get(key)));
			System.out.println();
		}
	}
	
	public static void debugBy(Map<String, List<Map<String, String>>> by) {
		Iterator<String> i = by.keySet().iterator();
		while(i.hasNext()) {
			String key = i.next();
			System.out.println("key: " + key);
			debugList(by.get(key));
		}
	}
	
	//调试函数
	public void report() {
		HtmlFreemarker hf = new HtmlFreemarker("template", "testlink.ftl");
        
		//放置参数  
        hf.root.put("tlexe", this.executions);
        hf.root.put("tlass", this.assignments);
        hf.root.put("tlnotrun", this.byExetype.byType.get(ByExetype.allExetype[0]));
        hf.root.put("tlpass", this.byExetype.byType.get(ByExetype.allExetype[1]));
        hf.root.put("tlfail", this.byExetype.byType.get(ByExetype.allExetype[2]));
        hf.root.put("tlblock", this.byExetype.byType.get(ByExetype.allExetype[3]));
        hf.root.put("tlnotavail", this.byExetype.byType.get(ByExetype.allExetype[4]));
        hf.root.put("tlall", this.byExetype.all);
        hf.root.put("tlallbyts", this.byExetype.bySuiteType.bySuite);
        
        hf.root.put("failbyts", this.byExetype.byTypeSuite.get(ByExetype.allExetype[2]).bySuite);
		
		try {  
            hf.make("sample1.html");
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } 
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		TLReport r = new TLReport("VAS-X", "14/3APP", "XS_140313_rom", "jianjun.duan");
		TLReport r = new TLReport("VASAPP", "XS_ROM_140324回归", "XS_ROM_140321", "jianjun.duan");
		debugList(r.executions);
		debugList(r.assignments);
//		debugMap(r.assignments.get(0));
//		for(String type: ByExetype.allExetype) {
//			debugList(r.byExetype.byType.get(type));
//		}
//		ByTestsuite bt = new ByTestsuite(r.assignments);
//		bt.debug();
//		r.byExetype.byTypeSuite.get("Not Run").debug();
//		r.byExetype.byTypeSuite.get("Failed").debug();
//		r.byExetype.bySuiteType.debug();
//		TLReport.debugList(r.byExetype.all);
//		r.report();
	}

}
