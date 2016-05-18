package mysql.bugzilla.utility;

import java.util.Map;

import kemin.time.SecondsDelta;
import kemin.time.Timestamp;

public class BugFromCreation {
	
	public static void delta(Map<String, String> bug, long current) {
		
		
		Timestamp creation_ts = new Timestamp(bug.get("creation_ts"));
		Timestamp cur = new Timestamp(current);
//		System.out.println(cur.from(creation_ts));
		SecondsDelta sd = new SecondsDelta(cur.from(creation_ts));
//		System.out.println(sd);
		
		if(!bug.containsKey("fromCreation"))
			bug.put("fromCreation", String.format("%d", sd.seconds));
		if(!bug.containsKey("howlong"))
			bug.put("howlong",  sd.toString());
		return;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
