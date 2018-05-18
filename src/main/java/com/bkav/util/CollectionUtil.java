package com.bkav.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.bkav.SystemManager;
import com.bkav.command.common.TextProcesser;

public class CollectionUtil {

	
    public static <T> boolean Contains(T[] collection, T key) {
        if (collection == null) {
            return false;
        }
        return Arrays.stream(collection)
        		.filter(key::equals)
        		.findFirst().isPresent();
    }

    public static <T> boolean Contains(List<T> collection, T key) {
        if (collection == null) {
            return false;
        }
        return collection.contains(key);
    }
    public static boolean Contains(Map<String, String[]> collection, String value) {
        if (collection == null || value == null) {
            return false;
        }
    	return collection.entrySet().stream()
    			.filter(entry -> value.startsWith(entry.getKey()))
    			.flatMap(entry -> Arrays.stream(entry.getValue()))
    			.filter(value::equals)
    			.findFirst().isPresent(); 
    }

    public static <T> void Combine(List<T> sourceData, List<T> expandData) {
        if (sourceData != null && expandData != null) {
            for (T item : expandData) {
                sourceData.add(item);
            }
        }
    }

    public static <T> void Combine(List<T> sourceData, T[] expandData) {
        if (sourceData != null && expandData != null) {
            sourceData.addAll(Arrays.asList(expandData));
        }
    }

    public static boolean isExited(Map<Character, Map<Integer, Set<String>>> saveMap, String item) {
        if (saveMap == null || item == null || item.length() == 0) {
            return false;
        }
        char[] chars = item.toCharArray();
        Map<Integer, Set<String>> datas = saveMap.get(chars[0]);
        if (datas != null) {
            Set<String> strings = datas.get(chars.length);
            if (strings == null) {
                return false;
            } else {
                return strings.contains(item);
            }
        } else {
            return false;
        }
    }

    public static void Insert(Map<Character, Map<Integer, Set<String>>> saveMap, String item) {
        if (saveMap == null || item == null || item.length() == 0) {
            return;
        }
        char[] chars = item.toCharArray();
        Map<Integer, Set<String>> datas = saveMap.get(chars[0]);
        if (datas != null) {
            Set<String> strings = datas.get(chars.length);
            if (strings == null) {
                strings = new TreeSet<>();
                strings.add(item);
                datas.put(chars.length, strings);
            } else {
                strings.add(item);
            }
        } else {
            TreeSet<String> strings = new TreeSet<>();
            strings.add(item);
            datas = new TreeMap<>();
            datas.put(chars.length, strings);
            saveMap.put(chars[0], datas);
        }
    }
    
    public static void showAllItem(Map<Character, Map<Integer, Set<String>>> saveMap) {
		saveMap.values().stream()//.parallel()  parallelStream()
			.flatMap(map -> map.values().stream())
			.flatMap(set -> set.stream())
			.forEach(SystemManager.logger::info);
    }
    
    public final static String[][] convert(String[] inputs) {    	
    	return Arrays.stream(inputs)
    			.map(StringUtil::splitString)
    			.filter(item -> item.length > 0)
    			.toArray(String[][]::new);
    }
    public final static String[][] convert(String[] inputs, TextProcesser textProcesser) {
    	return Arrays.stream(inputs)
	    		.map(textProcesser::preProccessText)
	    		.map(textProcesser::textToWords)
	    		.filter(item -> item.length > 0)
	    		.toArray(String[][]::new);
    }
    public final static List<List<String>> convert(List<String> inputs) {
		return inputs.stream()
				.map(StringUtil::splitStringToList).filter(item -> item.size() > 0)
				.collect(Collectors.toList());
    }
    public final static List<List<String>> convert(List<String> inputs, TextProcesser textProcesser) {
    	return inputs.stream()
		    	.map(textProcesser::preProccessText)
				.map(textProcesser::textToListWords)
				.filter(item -> item.size() > 0)
				.collect(Collectors.toList());
    }
}
