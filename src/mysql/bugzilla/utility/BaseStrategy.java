package mysql.bugzilla.utility;

import static kemin.tool.DebugTools.*;

public abstract class BaseStrategy implements IWeight {
	protected String bug_severity;
	protected String cf_repeatability;
	protected String cf_encounter;
	protected IConvert convert = null;
	
	protected BaseStrategy(String bug_severity, String cf_repeatability, String cf_encounter) {
		this.bug_severity = bug_severity;
		this.cf_encounter = cf_encounter;
		this.cf_repeatability = cf_repeatability;
	}
	
	protected double getSeverity() {
		return convert.getSeverity(this.bug_severity);
	}
	
	protected double getRepeatability() {
		return convert.getRepeatability(this.cf_repeatability);
	}
	
	protected double getEncounter() {
		return convert.getEncounter(this.cf_encounter);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
