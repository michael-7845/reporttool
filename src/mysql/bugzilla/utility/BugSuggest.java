package mysql.bugzilla.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kemin.builder.ReportBean;

public class BugSuggest {
	
	//根据buglist，如果有权重大于aType的bug则不能发布
	public static String isReleased(List<Map<String, String>> bugList) {
		ReportBean rb = new ReportBean();
		String no = "<font size='5' color='Red'>否 </font>"; //☠
		String yes = "<font size='5' color='Green'>是</font>"; //☺
		
		Iterator<Map<String, String>> i = bugList.iterator();
		while(i.hasNext()) {
			Map<String, String> bug = i.next();
			if(!bug.containsKey("weight"))
				return no;
			if(Double.valueOf(bug.get("weight")) >= rb.getbType())
				return no;
		}
		return yes;
	}
	
	//根据bug情况，给出一些提请注意的事项
	public static int hasABBug(List<Map<String, String>> bugList) {
		ReportBean rb = new ReportBean();
		Iterator<Map<String, String>> i = bugList.iterator();
		int count = 0;
		while(i.hasNext()) {
			Map<String, String> bug = i.next();
			if(Double.valueOf(bug.get("weight")) >= rb.getbType())
				count++;
		}
		return count;
	}
	
	//Module中存在>Border的问题，提示
	public static int badModuleBorder = 10;
	public static boolean hasBadModule(Map<String, List<Map<String, String>>> bycomponent) {
		Iterator<String> compIt = bycomponent.keySet().iterator();
		while(compIt.hasNext()) {
			String comp = compIt.next();
			if(bycomponent.get(comp).size() > badModuleBorder) {
				return true;
			}
		}
		return false;
	}
	
	//90+, 60+, 30+, 15+的问题数>{5, 10, 15, 20}任一，提示
	public static int[] toolongs = {5, 10, 15, 20}; //90+, 60+, 30+, 15+
	public static boolean hasToooldBug(Map<String, List<Map<String, String>>> bycreatedday) {
		if(bycreatedday.get("90+").size() > toolongs[0]) {
			return true;
		}
		if(bycreatedday.get("60+").size() > toolongs[1]) {
			return true;
		}
		if(bycreatedday.get("30+").size() > toolongs[2]) {
			return true;
		}
		if(bycreatedday.get("15+").size() > toolongs[3]) {
			return true;
		}
		return false;
	}
	
	//Resolved的问题没有提供足够信息，提示
	public static boolean hasBadResolvedBug(List<Map<String, String>> bugList) {
		Iterator<Map<String, String>> i = bugList.iterator();
		while(i.hasNext()) {
			Map<String, String> bug = i.next();
			String cause = bug.get("cf_root_cause");
			String resolvedVersion = bug.get("cf_resolved_verison");
			String fixedSince = bug.get("cf_fixed_since");
			if(cause.length() == 0) return true;
			if(cause.equals("无")) return true;
			if(cause.equals("fix")) return true;
			if(cause.equals("fixed")) return true;
			
			if(resolvedVersion.length() == 0) return true;
			if(resolvedVersion.equals("---")) return true;
			
			if(fixedSince.length() == 0) return true;
			if(fixedSince.equals("无")) return true;
			if(fixedSince.equals("fix")) return true;
			if(fixedSince.equals("fixed")) return true;
		}
		return false;
	}
	
	//根据问题情况，提示
	public static List<String> attention(
			List<Map<String, String>> devBuglistOfThisRelease,
			List<Map<String, String>> devBuglist,
			Map<String, List<Map<String, String>>> bycomponent,
			Map<String, List<Map<String, String>>> bycreatedday,
			List<Map<String, String>> testBuglist) {
		List<String> lookout = new ArrayList<String>();
		if(BugSuggest.isReleased(devBuglistOfThisRelease).equals("<font size='5' color='Red'>否 </font>")) {
			lookout.add("发布引入了B+级别问题，请参考<a href=\"#new_bug\">本次发布新发现的问题</a>");
		}
		int abBugCount = BugSuggest.hasABBug(devBuglist);
		if(abBugCount > 0) {
			lookout.add(
				String.format("软件中共存在%d个B+级别问题，请参考<a href=\"#all_bug\">全部问题的评分情况</a>",
				abBugCount));
		}
		if(BugSuggest.hasBadModule(bycomponent)) {
			lookout.add(
				String.format("软件中存在有超过%d个问题的模块，请参考<a href=\"#by_component\">模块分布情况</a>",
				badModuleBorder));
		}
		if(BugSuggest.hasToooldBug(bycreatedday)) {
			lookout.add("软件中存在有较多很久未处理的问题，请参考<a href=\"#by_howlong\">存活时间分布情况</a>");
		}
		if(BugSuggest.hasBadResolvedBug(testBuglist)) {
			lookout.add("软件中存在未提供修复信息（root cause/resolved version/fixed since）的已处理问题，请参考<a href=\"#for_test\">待测试人员处理的问题单</a>");
		}
		return lookout;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
