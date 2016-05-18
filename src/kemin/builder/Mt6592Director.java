package kemin.builder;

import java.util.List;
import java.util.Map;

import mysql.bugzilla.utility.BugSuggest;
import mysql.testlink.utility.TLBean;

public class Mt6592Director extends Director {

	public Mt6592Director(ReportBean bean, TLBean tlbean) {
		super(bean, tlbean);
	}
	
	@Override
	//prepare内容的补充
	protected void afterPrepare() {
		List<Map<String, String>> devBuglist_6592 = 
				this.bugreport.byVertype.get("mt6592");
		List<Map<String, String>> testBuglist_6592 = 
				this.bugreport.byVertype_test.get("mt6592");
		
		Map<String, List<Map<String, String>>> byComponent_dev_6592 = 
				this.bugreport.getByComponent(devBuglist_6592);
		Map<String, List<Map<String, String>>> byWeight_dev_6592 = 
				this.bugreport.getByWeight(devBuglist_6592);
		Map<String, List<Map<String, String>>> byComponent_test_6592 = 
				this.bugreport.getByComponent(testBuglist_6592);
		
		//修正total score的问题
		Double totalScore = this.bugreport.scoreBuglist(devBuglist_6592);
		bean.setTotalScore(totalScore);
		
		//修正问题存活时间的问题
		this.bugreport.byCreatedDay = this.bugreport.getByCreatedDay(devBuglist_6592);
		hf.root.put("bycreatedday", this.bugreport.byCreatedDay);
		
		//修正分析给出测试提请注意
		bean.setAttention(BugSuggest.attention(this.bugreport.devBuglistOfThisRelease,
				devBuglist_6592,
				byComponent_dev_6592,
				this.bugreport.byCreatedDay,
				testBuglist_6592));
		hf.root.put("report", this.bean);
		
		hf.root.put("devbug_6592", devBuglist_6592);
        hf.root.put("testbug_6592", testBuglist_6592);
        hf.root.put("bycomponent_dev_6592", byComponent_dev_6592);
        hf.root.put("byweight_dev_6592", byWeight_dev_6592);
        hf.root.put("bycomponent_test_6592", byComponent_test_6592);
	}
	
	@Override
	//根据信息，读取不同的template文件
	protected void readTemplate() {
		if(this.bean.getTesttype().equals("快速反馈")) {
			prepare("template", "Mt6592Report_fast.ftl");
		} else  {
			prepare("template", "Mt6592Report.ftl");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
