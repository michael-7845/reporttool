package mysql.bugzilla.utility;

import java.util.List;

public class SqlStatement {
	/* bugs' table */
//	public static String reports_sql = "select * from reports";
//	public static String byid_sql = "SELECT bugs.bug_id, bugs.bug_status, bugs.version, bugs.bug_severity, bugs.cf_encounter, bugs.cf_repeatability, bugs.short_desc " +
//		    "FROM bugs INNER JOIN products " +
//		    "ON bugs.product_id=products.id " +
//		    "WHERE bugs.bug_id='48745'";
	
	public static String getSql_dev_bug_by_version(String product, String version) {
		return "SELECT * " +
			"FROM products, (SELECT * FROM bugs WHERE version='"+ version + "') as t " +
			"WHERE products.name='" + product +"' AND t.product_id=products.id AND t.cf_bug_type='defect' AND " +
			"(t.bug_status='New' OR t.bug_status='Assigned' OR t.bug_status='Fixed' OR t.bug_status='Open')";
	}
	
	/*
	 * version上存在的所有问题（不仅仅是dev问题）
	 */
	public static String getSql_bug_by_version(String product, String version) {
		String sql = "SELECT * FROM bugs WHERE ";
		StringBuffer where = new StringBuffer();
		where.append("version='"+ version + "'");

		return "SELECT * " +
			"FROM products, (" + sql + where.toString() + ") as t " +
			"WHERE products.name='" + product +"' AND t.product_id=products.id AND t.cf_bug_type='defect'";
	}
	
	/*
	 * version和more（定义更多的中间版本）上存在的dev问题
	 */
	public static String getSql_dev_bug_by_version2(String product, String version, 
			List<String> more) {
		String sql = "SELECT * FROM bugs WHERE ";
		StringBuffer where = new StringBuffer();
		where.append("version='"+ version + "'");
		if(more.size() > 0) {
			for(String v: more) {
				where.append(" OR version='"+ v + "'");
			}
		}

		return "SELECT * " +
			"FROM products, (" + sql + where.toString() + ") as t " +
			"WHERE products.name='" + product +"' AND t.product_id=products.id AND t.cf_bug_type='defect' AND " +
			"(t.bug_status='New' OR t.bug_status='Assigned' OR t.bug_status='Fixed' OR t.bug_status='Open')";
	}
	
	/*
	 * version上存在的dev问题 （注意：没有more定义更多的中间版本）
	 */
	public static String getSql_all_version_by_product(String product) {
		return "SELECT DISTINCT version FROM bugs " + 
			"INNER JOIN products ON bugs.product_id=products.id " + 
			"WHERE products.name='" + product + "'";
	}
	
	/* HiBeam */
//	public static String hibeam_dev_bug = 
//		    "SELECT * " +
//		    "FROM bugs INNER JOIN products " +
//		    "ON bugs.product_id=products.id " +
//		    "WHERE products.name='HiBeam' AND bugs.cf_bug_type='defect' AND " + 
//		    "(bugs.bug_status='Assigned' OR bugs.bug_status='Fixed' OR bugs.bug_status='Open')";
	public static String hibeam_test_bug = 
		    "SELECT * " +
		    "FROM bugs INNER JOIN products " +
		    "ON bugs.product_id=products.id " +
		    "WHERE products.name='HiBeam' AND bugs.cf_bug_type='defect' AND " + 
		    "(bugs.bug_status='New' OR bugs.bug_status='Feedback' OR bugs.bug_status='Resolved')";
//	public static String hibeam_test_bug_fixsince =
//		    "SELECT * " + 
//		    "FROM products, (SELECT * FROM bugs WHERE bug_status='Resolved') as t " + 
//		    "WHERE products.name='HiBeam' AND t.product_id=products.id AND t.cf_bug_type='defect'";
//	public static String getSql_hibeam_dev_bug_by_severity(String severity) {
//		return "SELECT * " +
//			"FROM products, (SELECT * FROM bugs WHERE bug_severity='"+ severity + "') as t " +
//			"WHERE products.name='HiBeam' AND t.product_id=products.id AND t.cf_bug_type='defect' AND " +
//			"(t.bug_status='Assigned' OR t.bug_status='Fixed' OR t.bug_status='Open')";
//	}
//	public static String hibeam_all_component = "SELECT components.id, components.name FROM products, components " +
//			"WHERE products.name='HiBeam' AND components.product_id=products.id ";
//	public static String getSql_hibeam_dev_bug_after_id(String id) {
//		return "SELECT * " +
//			"FROM products, (SELECT * FROM bugs WHERE bug_id>"+ id + ") as t " +
//			"WHERE products.name='HiBeam' AND t.product_id=products.id AND t.cf_bug_type='defect' AND " +
//			"(t.bug_status='Assigned' OR t.bug_status='Fixed' OR t.bug_status='Open')";
//	}
//	public static String getSql_hibeam_test_bug_by_severity(String severity) {
//		return "SELECT * " +
//			"FROM products, (SELECT * FROM bugs WHERE bug_severity='"+ severity + "') as t " +
//			"WHERE products.name='HiBeam' AND t.product_id=products.id AND t.cf_bug_type='defect' AND " +
//			"(t.bug_status='New' OR t.bug_status='Feedback' OR t.bug_status='Resolved')";
//	}
	
//    public static String hibeam_dev_bug = 
//    "SELECT bugs.bug_id, bugs.bug_status, bugs.version, bugs.bug_severity, bugs.cf_encounter, bugs.cf_repeatability, bugs.short_desc " +
//    "FROM bugs INNER JOIN products " +
//    "ON bugs.product_id=products.id " +
//    "WHERE products.name='HiBeam' AND bugs.cf_bug_type='defect' AND " + 
//    "(bugs.bug_status='Assigned' OR bugs.bug_status='Fixed' OR bugs.bug_status='Open')";
//    public static String hibeam_test_bug = 
//    "SELECT bugs.bug_id, bugs.bug_status, bugs.version, bugs.bug_severity, bugs.cf_encounter, bugs.cf_repeatability, bugs.short_desc " +
//    "FROM bugs INNER JOIN products " +
//    "ON bugs.product_id=products.id " +
//    "WHERE products.name='HiBeam' AND bugs.cf_bug_type='defect' AND " + 
//    "(bugs.bug_status='New' OR bugs.bug_status='Feedback' OR bugs.bug_status='Resolved')";
//    public static String hibeam_test_bug_fixsince =
//    "SELECT t.bug_id, t.bug_status, t.short_desc, t.cf_fixed_since, t.cf_root_cause " + 
//    "FROM products, (SELECT * FROM bugs WHERE bug_status='Resolved') as t " + 
//    "WHERE products.name='HiBeam' AND t.product_id=products.id AND t.cf_bug_type='defect'";
//    public static String getSql_hibeam_dev_bug_by_severity(String severity) {
//		return "SELECT t.bug_id, t.bug_status, t.version, t.bug_severity, t.cf_encounter, t.cf_repeatability, t.short_desc " +
//				"FROM products, (SELECT * FROM bugs WHERE bug_severity='"+ severity + "') as t " +
//				"WHERE products.name='HiBeam' AND t.product_id=products.id AND t.cf_bug_type='defect' AND " +
//				"(t.bug_status='Assigned' OR t.bug_status='Fixed' OR t.bug_status='Open')";
//	}
//	public static String getSql_hibeam_test_bug_by_severity(String severity) {
//		return "SELECT t.bug_id, t.bug_status, t.version, t.bug_severity, t.cf_encounter, t.cf_repeatability, t.short_desc " +
//				"FROM products, (SELECT * FROM bugs WHERE bug_severity='"+ severity + "') as t " +
//				"WHERE products.name='HiBeam' AND t.product_id=products.id AND t.cf_bug_type='defect' AND " +
//				"(t.bug_status='New' OR t.bug_status='Feedback' OR t.bug_status='Resolved')";
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
