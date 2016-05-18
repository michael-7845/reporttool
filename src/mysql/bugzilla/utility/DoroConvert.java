package mysql.bugzilla.utility;

public class DoroConvert implements IConvert {

	/*
	 * bug_severity
	 *  critial: 4, major: 3, minor: 2, enhancement: 1
	 */
	@Override
	public double getSeverity(String severity) {
		if(severity.equalsIgnoreCase("critical"))
			return 4.0;
		if(severity.equalsIgnoreCase("major"))
			return 3.0;
		if(severity.equalsIgnoreCase("minor"))
			return 2.0;
		if(severity.equalsIgnoreCase("enhancement"))
			return 1.0;
		return -1.0;
	}

	/*
	 * cf_repeatability
	 *  Always: 1, Frequently: 0.5, Normal: 0, Sometimes:-1, Rarely: -2
	 */
	@Override
	public double getRepeatability(String repeatability) {
		if(repeatability.equalsIgnoreCase("always"))
			return 1.0;
		if(repeatability.equalsIgnoreCase("frequently"))
			return 0.5;
		if(repeatability.equalsIgnoreCase("normal"))
			return 0;
		if(repeatability.equalsIgnoreCase("sometimes"))
			return -1.0;
		if(repeatability.equalsIgnoreCase("rarely"))
			return -2.0;
		return -1000.0;
	}

	/*
	 * cf_encounter
	 *  Likely: 1.5, Maybe: 1.3, Unlikely: 0.8, OnlyCKT: 0.4(Modify Doro parameter: 1.0)
	 */
	@Override
	public double getEncounter(String encounter) {
		if(encounter.equalsIgnoreCase("likely"))
			return 1.5;
		if(encounter.equalsIgnoreCase("maybe"))
			return 1.3;
		if(encounter.equalsIgnoreCase("unlikely"))
			return 0.8;
		if(encounter.equalsIgnoreCase("onlyckt"))
			return 0.4;
		return -1.0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
