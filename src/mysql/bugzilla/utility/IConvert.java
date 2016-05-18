package mysql.bugzilla.utility;

public interface IConvert {
	public double getSeverity(String severity);
	public double getRepeatability(String repeatability);
	public double getEncounter(String encounter);
}
