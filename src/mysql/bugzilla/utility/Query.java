package mysql.bugzilla.utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kemin.freemarker.HtmlFreemarker;
import kemin.map.ListSortByBugid;
import kemin.map.ListSortByWeight;

import static kemin.tool.PrintNullTools.*;

public class Query {
	private static String user = "your_user"; //please provide account
	private static String password = "your_password"; //please provide password
	private static String url = "jdbc:mysql://10.120.10.150:3306/bugs?useUnicode=true&characterEncoding=UTF-8";
	private static String driver = "com.mysql.jdbc.Driver";
    
	public static List<Map<String, String>> search(String sqlstr) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
	    List<Map<String, String>> allbug = new ArrayList<Map<String, String>>();
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
		        allbug.add(bug);
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
		
		return allbug;
	}
	
	public static void main(String[] args) {
		
	}
}
