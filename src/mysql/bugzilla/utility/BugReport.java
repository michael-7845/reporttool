package mysql.bugzilla.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kemin.builder.Director;
import kemin.builder.ReportBean;
import kemin.freemarker.HtmlFreemarker;
import kemin.map.ListSortByBugid;
import kemin.map.ListSortByFromCreation;
import kemin.map.ListSortByWeight;
import kemin.tool.DebugDs;

import static kemin.tool.DebugTools.*;
//import static kemin.tool.PrintNullTools.*;
import static kemin.tool.DebugDs.*;

public class BugReport {

	private ReportBean bean = null;
	private Properties prop = null;
	
	public BugReport(ReportBean bean, Properties prop) {
		this.bean = bean;
		this.prop = prop;
		this.init();
	}
	
	private void init() {
		//调用抽象函数实例化
		this.devBuglist = getDevBug();
		this.testBuglist = getTestBug();
		this.devBuglistOfThisRelease = getDevBugOfThisRelease();
		
		//打分
		Double totalScore = scoreBuglist(devBuglist); //dev问题打分
		bean.setTotalScore(totalScore); //根据所有dev问题得分，算出本版本的总得分
		scoreBuglist(testBuglist); //test问题打分
		scoreBuglist(devBuglistOfThisRelease); //本次发布问题打分
		bean.setIsReleased(BugSuggest.isReleased(devBuglistOfThisRelease)); //根据本次发布问题，决定是否发布
				
		//添加创建至今时间信息
		howlongBuglist(devBuglist);
		
		//对bug队列排序
		ListSortByBugid.sortList(devBuglist); //1. devBugList
		ListSortByWeight.sortList(devBuglist);
		ListSortByBugid.sortList(testBuglist); //2. testBugList
		ListSortByWeight.sortList(testBuglist);
		ListSortByBugid.sortList(devBuglistOfThisRelease); //3. devBuglistOfThisRelease
		ListSortByWeight.sortList(devBuglistOfThisRelease);
			
		//一些更细的划分byXxx
		byComponent = getByComponent(devBuglist);
		byWeight = getByWeight(devBuglist);
		byVertype = getByVertype(devBuglist);
		byCreatedDay = getByCreatedDay(devBuglist);
		byWeightOfThis = getByWeight(devBuglistOfThisRelease);
		byVertype_test = getByVertype(testBuglist);
				
		//分析给出测试提请注意
		bean.setAttention(BugSuggest.attention(this.devBuglistOfThisRelease,
				this.devBuglist,
				this.byComponent,
				this.byCreatedDay,
				this.testBuglist));
	}

	//dev问题列表
	public List<Map<String, String>> devBuglist = null;
	public List<Map<String, String>> getDevBug() {
		String sql = String.format(this.prop.getProperty("dev_bug"), 
				this.bean.getProduct());
		println(sql);
		return Query.search(sql);
	}
	
	//test问题列表
	public List<Map<String, String>> testBuglist = null;
	public List<Map<String, String>> getTestBug() {
		String sql = String.format(this.prop.getProperty("test_bug"), 
				this.bean.getProduct());
		println(sql);
		return Query.search(sql);
	}
	
	//本次发布的问题列表
	public List<Map<String, String>> devBuglistOfThisRelease = null;
	public List<Map<String, String>> getDevBugOfThisRelease() {
		String productName = this.bean.getProduct();
		String version = bean.getVersion();
		List<String> more = bean.getMoreVersion();
		String sql1 = SqlStatement.getSql_dev_bug_by_version2(
				productName, version, more);
		String sql2 = SqlStatement.getSql_bug_by_version(productName, version);
		println(sql1);
		println(sql2);
		String sql = sql1 + " UNION " + sql2; //sql2补充本次发布所有类型的新问题，不仅仅是dev问题
		return Query.search(sql);
	}
	
	//所有的component
	public List<Map<String, String>> allComponent = null; 
	public List<Map<String, String>> getAllComponent() {
		String sql = String.format(this.prop.getProperty("all_component"), 
				this.bean.getProduct());
		return Query.search(sql);
	}
	
	//计算buglist中所有bug的总得分
	public Double scoreBuglist(List<Map<String, String>> bugList) {
		Iterator<Map<String, String>> i = bugList.iterator();
		Double totalScore = 0.0;
		while(i.hasNext()) {
			Double w = BugWeight.weight(i.next());
			totalScore += w;
		}
		return totalScore;
	}
	
	//计算buglist中所有bug的存活情况：fromCreation、howlong
	public void howlongBuglist(List<Map<String, String>> bugList) {
		long current = System.currentTimeMillis();
		Iterator<Map<String, String>> i = bugList.iterator();
		while(i.hasNext()) {
			BugFromCreation.delta(i.next(), current);
		}
	}
	
	/*
	 * 按weight划分的bug列表
	 */
	public Map<String, List<Map<String, String>>> byWeight = null; //所有问题
	public Map<String, List<Map<String, String>>> byWeightOfThis = null; //限于本次发布
	public Map<String, List<Map<String, String>>> 
	getByWeight(List<Map<String, String>> buglist) {
		ReportBean rb = new ReportBean();
		Map<String, List<Map<String, String>>> resMap = 
				new HashMap<String, List<Map<String, String>>>();

		List<Map<String, String>> alist =
				new ArrayList<Map<String, String>>();
		List<Map<String, String>> blist =
				new ArrayList<Map<String, String>>();
		List<Map<String, String>> clist =
				new ArrayList<Map<String, String>>();
		List<Map<String, String>> dlist =
				new ArrayList<Map<String, String>>();
		
		scoreBuglist(buglist);
		Iterator<Map<String, String>> bugIt = buglist.iterator();
		while(bugIt.hasNext()) {
			Map<String, String> bug = bugIt.next();
			double w = Double.valueOf(bug.get("weight"));
			if( w >= rb.getaType() ) {
				alist.add(bug);
			} else if ( w >= rb.getbType() ) {
				blist.add(bug);
			} else if ( w >= rb.getcType() ) {
				clist.add(bug);
			} else {
				dlist.add(bug);
			}
		}
		
		if(alist.size() > 0) {
			resMap.put("A", alist);
		}
		if(blist.size() > 0) {
			resMap.put("B", blist);
		}
		if(clist.size() > 0) {
			resMap.put("C", clist);
		}
		if(dlist.size() > 0) {
			resMap.put("D", dlist);
		}
		
		return resMap;
	}
	
	/*
	 * 按component划分的bug列表
	 */
	public Map<String, List<Map<String, String>>> byComponent = null; 
	public Map<String, List<Map<String, String>>> 
	getByComponent(List<Map<String, String>> buglist) {
		Map<String, List<Map<String, String>>> resMap = 
				new HashMap<String, List<Map<String, String>>>();

		allComponent = getAllComponent();
		Iterator<Map<String, String>> compIt = allComponent.iterator();
		while(compIt.hasNext()) {
			Map<String, String> comp = compIt.next();
			
			List<Map<String, String>> cBuglist =
					new ArrayList<Map<String, String>>();
			int count = 0;
			Iterator<Map<String, String>> bugIt = buglist.iterator();
			while(bugIt.hasNext()) {
				Map<String, String> bug = bugIt.next();
				if(comp.get("id").equals(bug.get("component_id"))) {
					cBuglist.add(bug);
					count++;
				}
			}
			if(count > 0) {
				resMap.put(comp.get("name"), cBuglist);
			}
		}
		return resMap;
	}
	
	/*
	 * 按发布种类划分的bug列表
	 * 发布种类：{"stable", "dev", "mt6592", "ali"}
	 */
	public Map<String, List<Map<String, String>>> byVertype = null; 
	public Map<String, List<Map<String, String>>> byVertype_test = null;
	public Map<String, List<Map<String, String>>> 
	getByVertype(List<Map<String, String>> buglist) {
		Map<String, List<Map<String, String>>> resMap = 
				new HashMap<String, List<Map<String, String>>>();

		String[] allVertype = {"stable", "dev", "mt6592", "ali"};
		for(String vertype: allVertype) {
			resMap.put(vertype, new ArrayList<Map<String, String>>());
		}
		
		Iterator<Map<String, String>> bugIt = buglist.iterator();
		while(bugIt.hasNext()) {
			Map<String, String> bug = bugIt.next();
			for(String vertype: allVertype) {
				if(bug.get("version").endsWith(vertype)) {
					resMap.get(vertype).add(bug);
				}
			}
		}
		
		return resMap;
	}
	
	/*
	 * 按存在的时间划分的bug列表
	 * 存在时间分类：{"90+", "60+", "30+", "15+", "7+", "0-7"}
	 */
	public Map<String, List<Map<String, String>>> byCreatedDay = null; 
	public Map<String, List<Map<String, String>>> 
	getByCreatedDay(List<Map<String, String>> buglist) {
		Map<String, List<Map<String, String>>> resMap = 
				new HashMap<String, List<Map<String, String>>>();

		String[] howlongs = {"90+", "60+", "30+", "15+", "7+", "0-7"};
		for(String hl: howlongs) {
			resMap.put(hl, new ArrayList<Map<String, String>>());
		}
		long day90 = 3600*24*90;
		long day60 = 3600*24*60;
		long day30 = 3600*24*30;
		long day15 = 3600*24*15;
		long day7 = 3600*24*7;
		
		Iterator<Map<String, String>> bugIt = buglist.iterator();
		while(bugIt.hasNext()) {
			Map<String, String> bug = bugIt.next();
			long from = Long.valueOf(bug.get("fromCreation"));
			if(from >= day90) {
				resMap.get("90+").add(bug);
			} else if(from >= day60) {
				resMap.get("60+").add(bug);
			} else if(from >= day30) {
				resMap.get("30+").add(bug);
			} else if(from >= day15) {
				resMap.get("15+").add(bug);
			} else if(from >= day7) {
				resMap.get("7+").add(bug);
			} else {
				resMap.get("0-7").add(bug);
			}
		}
		
		for(String hl: howlongs) {
			List<Map<String, String>> l = resMap.get(hl);
			l = ListSortByFromCreation.sortList(l);
		}
		
		return resMap;
	}
	
	/*
	 * 准备数据：把处理好的数据放入freemarker对象
	 */
	public void prepare(HtmlFreemarker hf) {
		/*bugzilla部分*/
        hf.root.put("devbug", devBuglist);
        hf.root.put("testbug", testBuglist);
        hf.root.put("bugofthis", devBuglistOfThisRelease);
        hf.root.put("bycomponent", byComponent);
        hf.root.put("byweight", byWeight);
        hf.root.put("byvertype", byVertype);
        hf.root.put("bycreatedday", byCreatedDay);
        hf.root.put("byweight_this", byWeightOfThis);
        hf.root.put("byvertype_test", byVertype);
        hf.root.put("report", bean);
	}
	
	/*
	 * 工具函数：所有的版本信息
	 */
	public static List<String> getAllVersion(String product, String postfix) {
		List<Map<String, String>> list = Query.search(
				SqlStatement.getSql_all_version_by_product(product));
		List<String> res = new ArrayList<String>();
		//在HiCamera_6592, HiGallery_6592中vertype为general，不作为后缀筛查版本信息
		if((postfix == null) || (postfix.equals("general"))) {
			postfix = null;
		}
		for(Map<String, String> map: list) {
			String version = map.get("version");
			if(postfix != null) {
				if(version.endsWith(postfix)) {
					res.add(version);
				}
			} else {
				res.add(version);
			}
		}
		return res;
	}
	public static List<String> getAllVersion(String product) {
		return getAllVersion(product, null);
	}
	
	/*
	 * 工具函数：根据产品名字，得到对应的发布分类
	 *        为了向前兼容，减少改动，保留本函数。
	 */
	public static String[] byVertype(String product) {
		String[] vertypes = null;
		if(product.equals("HiBeam")) { //"HIKe快传"
        	vertypes = new String[] { "stable", "ali" };
        }else if(product.equals("SLRAssistant")) { //"HIKe单反"
        	vertypes = new String[] { "stable", "dev", "ali" };
        }else if(product.equals("HiCamera3.0")) {
        	vertypes = new String[] { "stable", "dev", "mt6592" };
        }else if(product.equals("HiGallery3.0")) {
        	vertypes = new String[] { "stable", "dev", "mt6592" };
        }else { //除了以上的产品全部为general版本
        	vertypes = new String[] { "general" };
        }
		return vertypes;
	}
	
	public static void debug() {
		ReportBean bean = new ReportBean();
//		bean.setProduct("HiBeam");
//		bean.setVersion("2.0.353_stable");
		bean.setProduct("X-Product");
		bean.setVersion("X-S0A_CKT_L26EN_100_130624");
		List<String> moreversion = new ArrayList<String>();
//		moreversion.add("2.2.376_stable");
//		moreversion.add("2.0.336_stable");
//		moreversion.add("2.0.353_stable");
//		moreversion.add("2.0.314_stable");
//		bean.setMoreVersion(moreversion);
		HtmlFreemarker hf = new HtmlFreemarker("template", "GeneralReport.ftl");
		
		BugReport r = new BugReport(bean, Director.getProp());
		r.devBuglist = r.getDevBug();
		r.testBuglist = r.getTestBug();
		r.devBuglistOfThisRelease = r.getDevBugOfThisRelease();
//		DebugDs.printAllbug(r.devBuglist);
		DebugDs.printAllbug(r.testBuglist);
//		DebugDs.printAllbug(r.devBuglistOfThisRelease);
		
	}
	
	public static void main(String[] args) {
		debug();
	}

}
