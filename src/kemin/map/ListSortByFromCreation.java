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

public class ListSortByFromCreation {
	public static List<Map<String, String>> sortList(List<Map<String, String>> list) {  
        if (list == null || list.isEmpty()) {  
            return null;  
        }  
        Collections.sort(list, new ListComparatorByFromCreation());  
        return list;  
    }  
	
	public static void main(String[] args) {
	}
}

class ListComparatorByFromCreation implements Comparator<Map<String, String>> {  
    @Override  
    public int compare(Map<String, String> me1, Map<String, String> me2) {  
    	long d1 = Long.valueOf(me1.get("fromCreation"));
    	long d2 = Long.valueOf(me2.get("fromCreation"));
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
