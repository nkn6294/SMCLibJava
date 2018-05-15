package com.bkav.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.bkav.command.common.TextProcesser;

public class CollectionUtil {

    public static <T> boolean Contains(T[] collection, T key) {
        if (collection == null) {
            return false;
        }
        for (T t : collection) {
            if (key.equals(t)) {
                return true;
            }
        }
        return false;
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
        for (String key : collection.keySet()) {
            if (value.startsWith(key)) {
                return Contains(collection.get(key), value);
            }
        }
        return false;
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
        for(char c : saveMap.keySet()) {
            Map<Integer, Set<String>> datas = saveMap.get(c);
            for(int i : datas.keySet()) {
                for(String str : datas.get(i)) {
                    System.out.println(str);
                }
            }
        }
    }
    
    public final static String[][] convert(String[] inputs) {
    	List<String[]> output = new ArrayList<>();
    	for (String input : inputs) {
    		String[] item = StringUtil.splitString(input);
    		if (item.length > 0) {
    			output.add(StringUtil.splitString(input));    			
    		}
    	}
    	return output.toArray(new String[output.size()][]);
    }
    public final static String[][] convert(String[] inputs, TextProcesser textProcesser) {
    	List<String[]> output = new ArrayList<>();
    	for (String input : inputs) {
    		String[] item = textProcesser.textToWords(textProcesser.preProccessText(input));
    		if (item.length > 0) {
    			output.add(StringUtil.splitString(input));    			
    		}
    		output.add(item);
    	}
    	return output.toArray(new String[output.size()][]);
    }
    public final static List<List<String>> convert(List<String> inputs) {
    	List<List<String>> output = new ArrayList<>();
    	for (String input : inputs) {
    		List<String> item = StringUtil.splitStringToList(input);
    		if (item.size() > 0) {
    			output.add(StringUtil.splitStringToList(input));    			
    		}
    	}
    	return output;
    }
    public final static List<List<String>> convert(List<String> inputs, TextProcesser textProcesser) {
    	List<List<String>> output = new ArrayList<>();
    	for (String input : inputs) {
    		List<String> item = textProcesser.textToListWords(textProcesser.preProccessText(input));
    		if (item.size() > 0) {
    			output.add(item);    			
    		}
    	}
    	return output;
    }
}
