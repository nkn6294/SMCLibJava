package com.bkav.util;

import java.io.IOException;
import java.io.StringReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class StringUtil {
	
    public static String deAccentConvert(String input){
        String nfdNormalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
    
    public static String numberToText(String input) {
    	// TODO number to text -> 0 -> 9, 10 -> 100, ....
    	return input;
    }
	
	public static float CheckSame(String[] sources, String[] dests) {
		if (sources == null || dests == null || sources.length == 0 || dests.length == 0) {
			return 0f;
		}
		int count = 0;
        for (String element : sources) {
        	if (count >= dests.length) {
        		return 1f;
        	}
            if (element.equals(dests[count])) {
                count++;
            }
        }
        return 1.0f * count / dests.length;
	}
	public static float CheckSame(String source, String dest) {//source: dau vao->dest can so sanh
		if (source == null || dest == null) {
			return 0f;
		}
		if (source.equals(dest)) {
			return 1f;
		}
		return CheckSame(splitString(source), splitString(dest));
	}
	public static float CheckSame(String source, String[] dest) {//source: dau vao->dest can so sanh
		if (source == null || dest == null || dest.length == 0) {
			return 0f;
		}
		return CheckSame(splitString(source), dest);
	}
    public static String[] splitString(String key) {
        return splitStringToList(key).stream().toArray(String[]::new);
    }
    
    public static List<String> splitStringToList(String key) {
        List<String> list = new ArrayList<>();
        try {
            final InputStreamBlock reader = new InputStreamBlock(new StringReader(key));
            String word = reader.nextWord();
            while (word != null) {
                list.add(word.toLowerCase());
                word = reader.nextWord();
            }
        } catch (IOException ex) {
        }
        return list;
    }
    public static int search(String key, String source) {
        String[] keys = splitString(key);
        String[] sources = splitString(key);
        int count = 0;
        for (String element : sources) {
            if (count >= keys.length) {
                break;
            }
            if (element.equals(keys[count])) {
                count++;
            }
        }
        return count;
    }

    public static List<String> search(String key, String[] sources) {
        int maxItem = splitString(key).length;
        return Arrays.stream(sources)
        		.filter(item -> search(key, item) == maxItem)
        		.collect(Collectors.toList());
    }

    public static List<Integer> searchByIndex(String key, String[] sources) {
        int maxItem = splitString(key).length;
        List<Integer> strings = new ArrayList<>();
        for (int i = 0; i < sources.length; i++) {
            String item = sources[i];
            if (search(key, item) == maxItem) {
                strings.add(i);
            }
        }
        return strings;
    }
}
