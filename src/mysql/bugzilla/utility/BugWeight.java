package mysql.bugzilla.utility;

import java.util.Map;

public class BugWeight {
	public static double weight(Map<String, String> bug) {
		if(bug.containsKey("weight"))
			return Double.valueOf(bug.get("weight"));
		
		IWeight w = new DoroStrategy(bug.get("bug_severity"), 
				bug.get("cf_repeatability"), 
				bug.get("cf_encounter"));
		double weight = w.weight();
		
		bug.put("weight", String.format("%.1f", weight));
		return weight;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
