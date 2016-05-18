package mysql.bugzilla.utility;

import static kemin.tool.PrintNullTools.println;

public class DoroStrategy extends BaseStrategy {
	public DoroStrategy(String bug_severity, 
			String cf_repeatability, 
			String cf_encounter) {
		super(bug_severity, cf_repeatability, cf_encounter);
		this.convert = new DoroConvert();
	}
	
	/*
	 * weight = bug_severity * cf_encounter + cf_repeatability 
	 */
	@Override
	public double weight() {
		println(this.bug_severity + " = " + getSeverity());
		println(this.cf_repeatability + " = " + getRepeatability());
		println(this.cf_encounter + " = " + getEncounter());
		return getSeverity() * getEncounter() + getRepeatability();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
