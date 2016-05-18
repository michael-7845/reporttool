package kemin.builder;

import java.util.List;
import java.util.Map;

import mysql.bugzilla.utility.BugSuggest;
import mysql.testlink.utility.TLBean;

public class StableDirector extends Director {

	public StableDirector(ReportBean bean, TLBean tlbean) {
		super(bean, tlbean);
	}
	
	@Override
	//prepare内容的补充
	protected void afterPrepare() {
		List<Map<String, String>> devBuglist_stable = 
				this.bugreport.byVertype.get("stable");
		List<Map<String, String>> testBuglist_stable = 
				this.bugreport.byVertype_test.get("stable");
		
		Map<String, List<Map<String, String>>> byComponent_dev_stable = 
				this.bugreport.getByComponent(devBuglist_stable);
		Map<String, List<Map<String, String>>> byWeight_dev_stable = 
				this.bugreport.getByWeight(devBuglist_stable);
		Map<String, List<Map<String, String>>> byComponent_test_stable = 
				this.bugreport.getByComponent(testBuglist_stable);
		
		//修正total score的问题
		Double totalScore = this.bugreport.scoreBuglist(devBuglist_stable);
		bean.setTotalScore(totalScore);
		
		//修正问题存活时间的问题
		this.bugreport.byCreatedDay = this.bugreport.getByCreatedDay(devBuglist_stable);
		hf.root.put("bycreatedday", this.bugreport.byCreatedDay);
		
		//修正分析给出测试提请注意
		bean.setAttention(BugSuggest.attention(this.bugreport.devBuglistOfThisRelease,
						devBuglist_stable,
						byComponent_dev_stable,
						this.bugreport.byCreatedDay,
						testBuglist_stable));
		hf.root.put("report", this.bean);
		
		hf.root.put("devbug_stable", devBuglist_stable);
        hf.root.put("testbug_stable", testBuglist_stable);
        hf.root.put("bycomponent_dev_stable", byComponent_dev_stable);
        hf.root.put("byweight_dev_stable", byWeight_dev_stable);
        hf.root.put("bycomponent_test_stable", byComponent_test_stable);
	}
	
	@Override
	//根据信息，读取不同的template文件
	protected void readTemplate() {
		if(this.bean.getTesttype().equals("快速反馈")) {
			prepare("template", "StableReport_fast.ftl");
		} else  {
			prepare("template", "StableReport.ftl");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
