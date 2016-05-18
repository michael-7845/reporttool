package mysql.testlink.utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static kemin.tool.PrintNullTools.*;

public class Query {
	private static String user = "your_user"; //please provide account
	private static String password = "your_password"; //please provide password
	//查询要支持中文，需要在url中添加“?useUnicode=true&characterEncoding=UTF-8”
	private static String url = "jdbc:mysql://10.120.10.150:3306/testlink?useUnicode=true&characterEncoding=UTF-8";
	private static String driver = "com.mysql.jdbc.Driver";
    
	public static List<Map<String, String>> search(String sqlstr) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
	    List<Map<String, String>> alltc = new ArrayList<Map<String, String>>();
	    List<String> keys = new ArrayList<String>();
	    
		try{
		    Class.forName(driver);
		    con = DriverManager.getConnection(url, user, password);
		    stmt = con.createStatement();
		    
		    rs = stmt.executeQuery(sqlstr);
		    
		    ResultSetMetaData rsmd = rs.getMetaData();
		    int j = 0;
		    j = rsmd.getColumnCount();
		    for(int k = 0; k<j; k++)
		    {
		        print(rsmd.getColumnName(k+1));
		        print("\t");
		        keys.add(rsmd.getColumnName(k+1));
		    }
		    println("");
		    
		    while(rs.next())
		    {
		    	Map<String, String> bug = new HashMap<String, String>();
		        for(int i=0;i<j;i++)
		        {
		            print(rs.getString(i+1));
		            print("\t");
		            bug.put(keys.get(i), rs.getString(i+1));
		        }
		        println("");
		        alltc.add(bug);
		    }
		}
		catch(ClassNotFoundException e1)
		{
		    println("数据库驱动不存在！");
		    println(e1.toString());
		}
		catch(SQLException e2)
		{
		    println("数据库存在异常！");
		    println(e2.toString());
		}
		finally
		{
		    try
		    {
		        if(rs != null) rs.close();
		        if(stmt != null) stmt.close();
		        if(con != null) con.close();
		    }
		    catch(SQLException e)
		    {
		        println(e.toString());
		    }            
		}
		
		return alltc;
	}
	
	public static void debugList(List<Map<String, String>> list) {
		Iterator<Map<String, String>> i = list.iterator();
		while(i.hasNext()) {
			Map<String, String> m = i.next();
			Iterator<String> im = m.keySet().iterator();
			while(im.hasNext()) {
				String key = im.next();
				System.out.print(String.format("%s:%s, ", key, m.get(key)));
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
//		List<Map<String, String>> l1 = 
//				search(SqlStatement.getSql_executions("VAS-X", "14/3APP", "20140303-0307", "jianjun.duan"));
//		List<Map<String, String>> l2 = 
//				search(SqlStatement.getSql_assignments("VAS-X", "14/3APP", "20140303-0307", "jianjun.duan"));
//		System.out.println("executions: " + l1.size());
//		debugList(l1);
//		System.out.println("assignments: " + l2.size());
//		debugList(l2);
		
		List<Map<String, String>> l3 = 
				search(SqlStatement.getSql_testplans("VAS-X"));
		String stmt = SqlStatement.getSql_builds("阿里云XS");
		System.out.println(stmt);
		List<Map<String, String>> l4 = search(stmt);
		List<Map<String, String>> l5 = 
				search(SqlStatement.getSql_testers("20140303-0307"));
		System.out.println("testplans: " + l3.size());
		debugList(l3);
		System.out.println("builds: " + l4.size());
		debugList(l4);
		System.out.println("testers: " + l5.size());
		debugList(l5);
	}
}
