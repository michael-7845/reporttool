package mysql.bugzilla.utility;

import static kemin.tool.PrintNullTools.*;

public class MultipleStrategy extends BaseStrategy {
	public MultipleStrategy(String bug_severity, 
			String cf_repeatability, 
			String cf_encounter) {
		super(bug_severity, cf_repeatability, cf_encounter);
		this.convert = new BigConvert();
	}
	
	/*
	 * weight = bug_severity * cf_repeatability * cf_encounter
	 */
	@Override
	public double weight() {
		println(this.bug_severity + " = " + getSeverity());
		println(this.cf_repeatability + " = " + getRepeatability());
		println(this.cf_encounter + " = " + getEncounter());
		return getSeverity() * getRepeatability() * getEncounter();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
