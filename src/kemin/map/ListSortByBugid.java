package kemin.map;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mysql.bugzilla.utility.BugWeight;
import mysql.bugzilla.utility.Query;
import mysql.bugzilla.utility.SqlStatement;

public class ListSortByBugid {
	public static List<Map<String, String>> sortList(List<Map<String, String>> list) {  
        if (list == null || list.isEmpty()) {  
            return null;  
        }  
        Collections.sort(list, new ListComparatorById());  
        return list;  
    }  
	
	public static void main(String[] args) {
		List<Map<String, String>> allbug = Query.search(SqlStatement.hibeam_test_bug);

		Iterator<Map<String, String>> i = allbug.iterator();
		while(i.hasNext()) {
			BugWeight.weight(i.next());
		}
		
		sortList(allbug);
		
		i = allbug.iterator();
		while(i.hasNext()) {
			Map<String, String> bug = i.next();
			Set<String> keys = bug.keySet();
			Iterator<String> ik = keys.iterator();
			while(ik.hasNext()) {
				System.out.print(bug.get(ik.next()));
				System.out.print("\t");
			}
			System.out.println("");
		}
	}
}

class ListComparatorById implements Comparator<Map<String, String>> {  
    
    @Override  
    public int compare(Map<String, String> me1, Map<String, String> me2) {  
    	double d1 = Double.valueOf(me1.get("bug_id"));
    	double d2 = Double.valueOf(me2.get("bug_id"));
        if (d1 > d2) {
        	return -1;
        }	
        else if (d1 < d2) {
        	return 1;
        } else {
        	return 0;
        }	
    }  
}  
