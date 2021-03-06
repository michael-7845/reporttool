package kemin.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MapSortByValue {

	public static void main(String[] args) {  
		  
        Map<String, String> map = new TreeMap<String, String>();  
  
        map.put("KFC", "kfc");  
        map.put("WNBA", "wnba");  
        map.put("NBA", "nba");  
        map.put("CBA", "cba");  
  
//        Map<String, String> resultMap = sortMapByKey(map);    //按Key进行排序  
      Map<String, String> resultMap = sortMapByValue(map); //按Value进行排序  
  
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {  
            System.out.println(entry.getKey() + " " + entry.getValue());  
        }  
    }  
      
    /** 
     * 使用 Map按value进行排序 
     * @param map 
     * @return 
     */  
    public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {  
        if (oriMap == null || oriMap.isEmpty()) {  
            return null;  
        }  
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();  
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(  
                oriMap.entrySet());  
        Collections.sort(entryList, new MapValueComparator());  
  
        Iterator<Map.Entry<String, String>> iter = entryList.iterator();  
        Map.Entry<String, String> tmpEntry = null;  
        while (iter.hasNext()) {  
            tmpEntry = iter.next();  
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  
        }  
        return sortedMap;  
    }  

}

class MapValueComparator implements Comparator<Map.Entry<String, String>> {  
    
    @Override  
    public int compare(Entry<String, String> me1, Entry<String, String> me2) {  
  
        return me1.getValue().compareTo(me2.getValue());  
    }  
}  