package mysql.testlink.utility;

import java.util.Properties;

import kemin.tool.PropertiesTool;

public class SqlStatement {
	
	/*
	 * 指定的project,tp,build，获得全部分配的用例情况
	 */
	public static String getSql_assignments(String project, String testplan,
			String build) {
		Properties prop = PropertiesTool.read("config\\testlink.prop");
		String sql = String.format(prop.getProperty("assignments"),
				project, testplan, build);
		return sql;
	}
	
	/*
	 * 指定project, testplan, build，获得所有用例的最新执行情况
	 */
	public static String getSql_executions(String project, String testplan,
			String build) {
		Properties prop = PropertiesTool.read("config\\testlink.prop");
		String sql = String.format(prop.getProperty("executions"),
				project, testplan, build);
		return sql;
	}
	
	/*
	 * project下所有的testplan名字
	 */
	public static String getSql_testplans(String project) {
		Properties prop = PropertiesTool.read("config\\testlink.prop");
		String sql = String.format(prop.getProperty("testplans"), project);
		return sql;
	}
	
	/*
	 * testplan下所有的build
	 */
	public static String getSql_builds(String testplan) {
		Properties prop = PropertiesTool.read("config\\testlink.prop");
		String sql = String.format(prop.getProperty("builds"), testplan);
		return sql;
	}
	
	/*
	 * build分配了用例的全部执行测试人员
	 */
	public static String getSql_testers(String build) {
		Properties prop = PropertiesTool.read("config\\testlink.prop");
		String sql = String.format(prop.getProperty("testers"), build);
		return sql;
	}
	
	public static void main(String[] args) {
		System.out.println(getSql_assignments("VAS-X", "14/3APP", "20140303-0307"));
		System.out.println(getSql_executions("VAS-X", "14/3APP", "20140303-0307"));
		System.out.println(getSql_testplans("VAS-X"));
		System.out.println(getSql_builds("14/3APP"));
		System.out.println(getSql_testers("20140303-0307"));
	}

//	public static String getSql_executions(String project, String testplan,
//			String build, String tester) {
//		String sql = String.format(
//				"SELECT * " +
//				"FROM executions, users, " +
//				"(SELECT MAX(executions.id) maxium FROM builds, executions, " + 
//				"(SELECT * FROM nodes_hierarchy where " + 
//				"(parent_id=(SELECT id FROM nodes_hierarchy where (name='%s')) " +
//				"AND (node_type_id=5))) as tp " + 
//				"WHERE (((tp.id=executions.testplan_id) AND " +
//				"(tp.name='%s')) AND " +  
//				"(executions.build_id=builds.id) AND " +
//				"(builds.name='%s'))" +
//				"GROUP BY executions.tcversion_id) as m " + 
//				"WHERE (executions.id=m.maxium) AND " + 
//				"(executions.tester_id=users.id) AND (users.login='%s')", 
//				project, testplan, build, tester);
//		return sql;
//	}
//	
//	public static String getSql_assignments(String project, String testplan,
//			String build, String tester) {
//		String sql = String.format(
//				"SELECT * " +
//				"FROM builds, user_assignments, users, " +
//				"(SELECT * FROM nodes_hierarchy WHERE " +
//				"(parent_id=" +
//				"(SELECT id FROM nodes_hierarchy WHERE (name='%s')) " +
//				"AND (node_type_id=5))) as tp " +
//				"WHERE ( (tp.id=builds.testplan_id) AND " +
//				"(tp.name='%s') AND " +
//				"(builds.name='%s') AND " +
//				"(user_assignments.build_id=builds.id) AND " +
//				"(user_assignments.user_id=users.id) AND " +
//				"(users.login='%s') )", 
//				project, testplan, build, tester);
//		return sql;
//	}
	
}
