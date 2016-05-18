package mysql.bugzilla.utility;

public class BigConvert implements IConvert {

	/*
	 * bug_severity
	 *  critial: 8, major: 4, minor: 2, enhancement: 1
	 */
	@Override
	public double getSeverity(String severity) {
		if(severity.equalsIgnoreCase("critical"))
			return 8.0;
		if(severity.equalsIgnoreCase("major"))
			return 4.0;
		if(severity.equalsIgnoreCase("minor"))
			return 2.0;
		if(severity.equalsIgnoreCase("enhancement"))
			return 1.0;
		return -1.0;
	}

	/*
	 * cf_repeatability
	 *  Always: 5, Frequently: 4, Normal: 3, Sometimes:2, Rarely: 1
	 */
	@Override
	public double getRepeatability(String repeatability) {
		if(repeatability.equalsIgnoreCase("always"))
			return 5.0;
		if(repeatability.equalsIgnoreCase("frequently"))
			return 4.0;
		if(repeatability.equalsIgnoreCase("normal"))
			return 3.0;
		if(repeatability.equalsIgnoreCase("sometimes"))
			return 2.0;
		if(repeatability.equalsIgnoreCase("rarely"))
			return 1.0;
		return -1.0;
	}

	/*
	 * cf_encounter
	 *  Likely: 4, Maybe: 3, Unlikely: 2, OnlyCKT: 1
	 */
	@Override
	public double getEncounter(String encounter) {
		if(encounter.equalsIgnoreCase("likely"))
			return 4.0;
		if(encounter.equalsIgnoreCase("maybe"))
			return 3.0;
		if(encounter.equalsIgnoreCase("unlikey"))
			return 2.0;
		if(encounter.equalsIgnoreCase("onlyckt"))
			return 1.0;
		return -1.0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
