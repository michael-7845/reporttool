package kemin.builder;

import java.util.List;
import java.util.Map;

import mysql.bugzilla.utility.BugSuggest;
import mysql.testlink.utility.TLBean;

public class DevDirector extends Director {

	public DevDirector(ReportBean bean, TLBean tlbean) {
		super(bean, tlbean);
	}
	
	@Override
	//prepare内容的补充
	protected void afterPrepare() {
		List<Map<String, String>> devBuglist_devver = 
				this.bugreport.byVertype.get("dev");
		List<Map<String, String>> testBuglist_devver = 
				this.bugreport.byVertype_test.get("dev");
		
		Map<String, List<Map<String, String>>> byComponent_dev_devver = 
				this.bugreport.getByComponent(devBuglist_devver);
		Map<String, List<Map<String, String>>> byWeight_dev_devver = 
				this.bugreport.getByWeight(devBuglist_devver);
		Map<String, List<Map<String, String>>> byComponent_test_devver = 
				this.bugreport.getByComponent(testBuglist_devver);
		
		//修正total score的问题
		Double totalScore = this.bugreport.scoreBuglist(devBuglist_devver);
		bean.setTotalScore(totalScore);
		
		//修正问题存活时间的问题
		this.bugreport.byCreatedDay = this.bugreport.getByCreatedDay(devBuglist_devver);
		hf.root.put("bycreatedday", this.bugreport.byCreatedDay);
		
		//修正分析给出测试提请注意
		bean.setAttention(BugSuggest.attention(this.bugreport.devBuglistOfThisRelease,
				devBuglist_devver,
				byComponent_dev_devver,
				this.bugreport.byCreatedDay,
				testBuglist_devver));
		hf.root.put("report", this.bean);
		
		hf.root.put("devbug_dev", devBuglist_devver);
        hf.root.put("testbug_dev", testBuglist_devver);
        hf.root.put("bycomponent_dev_dev", byComponent_dev_devver);
        hf.root.put("byweight_dev_dev", byWeight_dev_devver);
        hf.root.put("bycomponent_test_dev", byComponent_test_devver);
	}
	
	@Override
	//根据信息，读取不同的template文件
	protected void readTemplate() {
		if(this.bean.getTesttype().equals("快速反馈")) {
			prepare("template", "DevReport_fast.ftl");
		} else  {
			prepare("template", "DevReport.ftl");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
