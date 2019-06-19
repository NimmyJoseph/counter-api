package au.com.optus.counter.api.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import au.com.optus.counter.api.models.SearchRequest;
import au.com.optus.counter.api.models.SearchResponse;

@Component
public class CounterApiProcessor {
	
	@Value("${text.file}")
	protected String textFile;

	public SearchResponse stringCountProcess(SearchRequest request) throws IOException {
		
		SearchResponse response = new SearchResponse();
		ArrayList<HashMap<String, Integer>> txt = new ArrayList<HashMap<String, Integer>>();
		for (String key : request.getSearchText()) {
			HashMap<String, Integer> map1 = new HashMap<String, Integer>();
			map1.put(key, getCount().getOrDefault(key.toLowerCase(), 0));
			txt.add(map1);
		}
		response.setCount(txt);
	    
		return response;
	}

	public String topCountProcess(String val) throws IOException {
		String returnStr = new String();
		Integer top = Integer.parseInt(val);
		/*LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		 
		getCount().entrySet()
		    .stream()
		    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		Map<String, Integer> sortedMap = getCount();
		int count = 1;

		for (String key : sortedMap.keySet()) {
			if (count <= top) {
				returnStr = returnStr.concat(key+"|"+sortedMap.getOrDefault(key, 0)+"\n");
			}
			count++;
		}*/
Map<String, Integer> map = getCount();
		
		List<Map.Entry<String, Integer>> resultList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(resultList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
       
        int count = 1;

		for (Entry<String, Integer> key : resultList) {
			if (count <= top) {
				returnStr = returnStr.concat(key.getKey()+"|"+key.getValue()+"\n");
			}
			count++;
		}
	    return returnStr;
	}
	    
	public Map<String, Integer> getCount() throws IOException {
		File file = new File(getClass().getClassLoader().getResource(textFile).getFile());
		 StringBuilder stringBuilder = new StringBuilder();
	        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
	        String line;
	        while ((line = bufferedReader.readLine()) != null) {
	        	stringBuilder.append(line).append(" ");
	        }
	         
	       
	        Map<String, Integer> map = new HashMap<String, Integer>();  
	        String sentence = stringBuilder.toString(); 
	        StringTokenizer token = new StringTokenizer(sentence); 
	        while (token.hasMoreTokens()) { 
	            String words = token.nextToken(", ?.!:\"\"''\n").toLowerCase();  
	            
	            if (map.containsKey(words)) {
	                int count = map.get(words);
	                map.put(words, count + 1); 
	            } else {
	                map.put(words, 1); 
	            }
	        }
		return map;
	}
}
