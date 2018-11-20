/*
  #This question was asked in ICPC South Central Regional Programming Competition
Elvira is processing shipments for the Christmas season! There are various shipments of toys coming into her warehouse throughout the day,
and her boss wants a summary report at the end. Each shipment consists of some number of a single kind of toy.

The summary report is an aggregated list of all the toys that arrived at the warehouse over the day.
The boss is most interested in the biggest sellers, so toys should be sorted in decreasing order of count. 
If the warehouse received the same number of two kinds of toys, sort them in alphabetical order1.

Input
The first line of input contains the number of test cases, T (1≤T≤100).

Each test case begins with a line containing an integer, N (1≤N≤100). This indicates that N shipments are coming in for processing.
Each of the next N lines describes a single shipment. The line contains a string and an integer, the name of the toy and how many of that
toy are in the given shipment. The name of a toy is a string of at most 10 lowercase letters (a–z) and between 1 and 10 toys arrive in a
given shipment.

Output
For each test case, output K, the number of unique toys that have arrived in the warehouse.

Then output K lines, each containing the name of a toy and how many of that toy that have arrived, summed up over all the shipments.
Output toys in decreasing order of count, breaking ties alphabetically.
Example

Sample Input 1	
2
4
furby 4
elmo 5
furby 1
kirby 10
2
funfungame 1
funfun 1

Sample Output 1
3
kirby 10
elmo 5
furby 5
2
funfun 1
funfungame 1
*/
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
      int noOfTimes = sc.nextInt();
      for(int k=0;k<noOfTimes;k++) {
        int noOfItems = sc.nextInt();
        Map<String, Integer> table = new HashMap<String, Integer>();

        for(int i=0;i<noOfItems;i++) {
          String itemName = sc.next();
          int quantity = sc.nextInt();
          if(table.containsKey(itemName)) {
            table.put(itemName, table.getOrDefault(itemName, 0) + quantity);
          } else {
            table.put(itemName, quantity);
          }
        }
        
        Map<String, Integer> sortedByKeys = sortByKeys(table);
        Map<String, Integer> sortedByValues = sortByValues(sortedByKeys);
        System.out.println(sortedByValues);
      }
	}
	
	public static <K extends Comparable, V extends Comparable> Map<K, V> sortByKeys(Map<K, V> map) {
		List<K> keys = new LinkedList<K>(map.keySet());
		Collections.sort(keys);
		Map<K, V> sortedMap = new LinkedHashMap<K, V>();
		for(K key: keys) {
			sortedMap.put(key, map.get(key));
		}
		return sortedMap;
	}
	
	public static <K extends Comparable, V extends Comparable> Map<K,V> sortByValues(Map<K,V> map) {
		List <Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		
		Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		Map<K, V> sortedMap = new LinkedHashMap<K, V>();
		for(Map.Entry<K, V> entry: entries) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
	
		return sortedMap;
	}
}
