package mysql.testlink.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BySuite {
	public List<Map<String, String>> cases;
	
	public Set<String> all;
	//注意： not run使用assignments信息，其他列表使用executions信息！
	public Map<String, List<Map<String, String>>> bySuite;
	
	public BySuite(List<Map<String, String>> cases) {
		this.cases = cases;
		init();
	}
	
	private void init() {
		if(this.cases==null) {
			return;
		}
		
		this.all = new HashSet<String>();
		Iterator<Map<String, String>> i = this.cases.iterator();
		while(i.hasNext()) { //全部分配的用例
			Map<String, String> m = i.next();
			this.all.add(m.get("tsname"));
		}
		
		initBySuite();
	}
	
	private void initBySuite() {
		if(this.cases==null) {
			return;
		}
		
		this.bySuite = new HashMap<String, List<Map<String, String>>>();
		for(String suite: this.all) {
			this.bySuite.put(suite, new ArrayList<Map<String, String>>());
		}
		
		Iterator<Map<String, String>> i = this.cases.iterator();
		while(i.hasNext()) { //删除已经执行的用例
			Map<String, String> m = i.next();
			String ts = m.get("tsname");
			for(String suite: this.all) {
				if(ts.equals(suite)) {
					this.bySuite.get(suite).add(m);
					continue;
				}
			}
		}
	}
	
	public void debug() {
		for(String suite: this.all) {
			System.out.println(suite);
			TLReport.debugList(this.bySuite.get(suite));
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
