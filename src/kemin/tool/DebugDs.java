package kemin.tool;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DebugDs {

	//调试函数
	public static void printAllbug(List<Map<String, String>> allbug) {
		Iterator<Map<String, String>> i = allbug.iterator();
		while(i.hasNext()) {
			Map<String, String> bug = i.next();
			Set<String> keys = bug.keySet();
			Iterator<String> ik = keys.iterator();
			while(ik.hasNext()) {
				String key = ik.next();
				System.out.print(key + ":");
				System.out.print(bug.get(key));
				System.out.print("\t");
			}
			System.out.println("");
		}
	}
	
	//调试函数
	public static void printCategory(Map<String, List<Map<String, String>>> all) {
		Set<String> keys = all.keySet();
		Iterator<String> i = keys.iterator();
		while(i.hasNext()) {
			String key = i.next();
			System.out.println("all's " + key + ": ");
			
			List<Map<String, String>> list = all.get(key);
			printAllbug(list);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
