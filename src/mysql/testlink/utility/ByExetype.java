package mysql.testlink.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ByExetype {
	public List<Map<String, String>> executions;
	public List<Map<String, String>> assignments;
	
	public static String[] allExetype = {"Not Run", "Passed", "Failed", "Blocked", "Not Available"};
	//注意： not run使用assignments信息，其他列表使用executions信息！
	public Map<String, List<Map<String, String>>> byType; //按照状态分类用例
	public Map<String, BySuite> byTypeSuite; //全部用例按状态，然后按套件分类
	
	public List<Map<String, String>> all; //包含全部状态的用例，executions不包括not run的用例。
	                              //最后，此成员为列表：包含全部状态；先按套件，后按状态分类的顺序保存
	public BySuite bySuiteType; //全部用例按套件，然后按状态分类
	
	public ByExetype(List<Map<String, String>> executions,
			List<Map<String, String>> assignments) {
		this.executions = executions;
		this.assignments = assignments;
		this.byType = new HashMap<String, List<Map<String, String>>>();
		for(String type: ByExetype.allExetype) {
			this.byType.put(type, new ArrayList<Map<String, String>>());
		}
		initNotrun();
		initOthers();
		initTypeSuite();
		initSuiteType();
	}
	
	private void initNotrun() {
		if((this.executions==null) || (this.assignments==null)) {
			return;
		}
		
		List<String> notrunsList = new LinkedList<String>();
		Iterator<Map<String, String>> ia = this.assignments.iterator();
		while(ia.hasNext()) { //全部分配的用例
			Map<String, String> m = ia.next();
			notrunsList.add(m.get("tcid"));
		}
		Iterator<Map<String, String>> ie = this.executions.iterator();
		while(ie.hasNext()) { //从全部分配中，删除已经执行的用例，剩下为没有执行的用例
			Map<String, String> m = ie.next();
			notrunsList.remove(m.get("tcid"));
		}
		ia = this.assignments.iterator();
		while(ia.hasNext()) { //添加没有执行的用例到byExetype
			Map<String, String> m = ia.next();
			for(String tcid: notrunsList) {
				if(tcid.equals(m.get("tcid"))) {
					this.byType.get(ByExetype.allExetype[0]).add(m); //0: Not Run
				}
			}
		}
		
		//全部的notrun用例添加"status", "notes"信息
		Iterator<Map<String, String>> inr = 
				this.byType.get(ByExetype.allExetype[0]).iterator();
		while(inr.hasNext()) {
			Map<String, String> m = inr.next();
			m.put("status", "n");
			m.put("notes", "");
		}
	}
	
	private void initOthers() {
		if(this.assignments==null) {
			return;
		}
		
		Iterator<Map<String, String>> i = this.executions.iterator();
		while(i.hasNext()) { //删除已经执行的用例
			Map<String, String> m = i.next();
			String status = m.get("status");
			if(status.equals("p")) { //1: Passed
				this.byType.get(ByExetype.allExetype[1]).add(m);
			}else if(status.equals("f")) { //2: Failed
				this.byType.get(ByExetype.allExetype[2]).add(m);
			}else if(status.equals("b")) { //3: Blocked
				this.byType.get(ByExetype.allExetype[3]).add(m);
			}else if(status.equals("x")) { //4: Not Available
				this.byType.get(ByExetype.allExetype[4]).add(m);
			}
		}
	}
	
	private void initTypeSuite() {
		if(this.byType==null) return;
		for(String type: ByExetype.allExetype) {
			if(this.byType.get(type)==null) return;
		}
		this.byTypeSuite = new HashMap<String, BySuite>();
		for(String type: ByExetype.allExetype) {
			this.byTypeSuite.put(type, new BySuite(this.byType.get(type)));
		}
	}
	
	private void initAll() {
		this.all = new ArrayList<Map<String, String>>();
		for(String type: ByExetype.allExetype) {
			this.all.addAll(this.byType.get(type));
		}
	}
	
	private void initSuiteType() {
		initAll();
		if(this.all==null) return;
		this.bySuiteType = new BySuite(this.all);
		this.all = new ArrayList<Map<String, String>>();
		for(String suite: this.bySuiteType.all) {
			this.all.addAll(this.bySuiteType.bySuite.get(suite));
		}
	}
	
	public static void main(String[] args) {
//		for(String type: ByExetype.allExetype) {
//			TLReport.debugList(r.byExetype.get(type));
//		}
	}

}
