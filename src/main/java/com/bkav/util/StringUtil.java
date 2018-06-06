package com.bkav.util;

import java.io.IOException;
import java.io.StringReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.bkav.command.SystemManager;
import com.bkav.command.common.Function2;

public final class StringUtil {
	public static final String CELSIUS_UNIT = "\u2103";
	public static final String PERCENT_UNIT = "%";
    public static String deAccentConvert(String input){
        String nfdNormalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("")
        		.replaceAll("đ", "d");
    }
    public static String textToUnit(String input) {
    	String output = input;
    	output = celsiusToText(output);
    	output = percentToText(output);
    	return output;
    }
    public static String textToNumber(String input) {
    	String output = input;
    	String[] inputNumbers = {
    		"không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín", "mười"
    	};
    	for (int index = 1; index < inputNumbers.length; index++) {
    		output = output.replaceAll(inputNumbers[index], "" + index);
    	}
    	output = text1xToNumber(output);
    	output = text1xToNumber2(output);
    	output = textx1ToNumber2(output);
    	output = textx4ToNumber2(output);
    	output = textx1ToNumber(output);
    	output = textx4ToNumber(output);
    	output = textx0ToNumber(output);
    	output = textxxToNumber(output);
    	output = textx00ToNumber(output);
    	output = textxxxToNumber(output);
    	return output;
    }
    public static String timeToNormal(String input) {
    	String output = input;
    	output = normalHourUnit(output);
    	output = normalMinuteUnit(output);
    	output = normalSecondUnit(output);
    	output = longTimeToShort(output);
    	output = longTimeOnlyHourToShort(output);
    	output = normalAMPM(output);
    	output = timeAMToShort(output);
    	output = timePMToShort(output);
    	return output;
    }
	public static float checkSame(String[] sources, String[] dests) {
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
	public static float checkSame(String source, String dest) {//source: dau vao->dest can so sanh
		if (source == null || dest == null) {
			return 0f;
		}
		if (source.equals(dest)) {
			return 1f;
		}
		return checkSame(splitString(source), splitString(dest));
	}
	public static float checkSame(String source, String[] dest) {//source: dau vao->dest can so sanh
		if (source == null || dest == null || dest.length == 0) {
			return 0f;
		}
		return checkSame(splitString(source), dest);
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
        	SystemManager.logger.info(ex.getMessage());
        }
        return list;
    }
    public static int search(String key, String source) {
        String[] keys = splitString(key);
        String[] sources = splitString(source);
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
    
    protected static String normalAMPM(String input) {
    	String patternString = "\\b(\\d+)\\s*(a|p)(\\s*\\.?\\s*m)\\b";
		return input.replaceAll(patternString, "$1$2m");
    }
    protected static String timeAMToShort(String input) {
    	String patternString = "\\b(\\d+)(:\\d{2})?am\\b";
    	return textProcessByRegex(input, patternString, (matcher, builder) -> 
		builder.append(matcher.group(1))
			.append(matcher.group(2) == null ? ":00" : matcher.group(2)));
//		return input.replaceAll(patternString, "$1:$2");
    }
    protected static String timePMToShort(String input) {
    	String patternString = "\\b(\\d+)(:\\d{2})?pm\\b";
    	return textProcessByRegex(input, patternString, (matcher, builder) -> 
    		builder.append(Integer.parseInt(matcher.group(1)) + 12)
    			.append(matcher.group(2) == null ? ":00" : matcher.group(2)));
    }
    protected static String longTimeToShort(String input) {
		String patternString = "\\b(\\d+)h\\s+(\\d+)m?\\b";
		return input.replaceAll(patternString, "$1:$2");
    }
    protected static String longTimeOnlyHourToShort(String input) {
		String patternString = "\\b(\\d+)h\\b";
		return input.replaceAll(patternString, "$1:00");
    }
    protected static String normalHourUnit(String input) {
    	String patternString = "\\b(\\d+)\\s*giờ\\b";
    	return input.replaceAll(patternString, "$1h");
    }
    protected static String normalMinuteUnit(String input) {
    	String patternString = "\\b(\\d+)\\s*phút\\b";
    	return input.replaceAll(patternString, "$1m");
    }
    protected static String normalSecondUnit(String input) {
    	String patternString = "\\b(\\d+)\\s*giây\\b";
    	return input.replaceAll(patternString, "$1s");
    }
    //////////////////////
    protected static String celsiusToText(String input) {
    	String patternString = "\\b(\\d+)\\s+độ\\s+c\\b";
    	return input.replaceAll(patternString, "$1" + CELSIUS_UNIT);
    }    
    protected static String percentToText(String input) {
    	String patternString = "\\b(\\d+)\\s+phần\\s+trăm\\b";
    	return input.replaceAll(patternString, "$1" + PERCENT_UNIT);
    }
    
    // textToNumber
    protected static String textToNumber2(String input) {
		String patternString = "((\\d)\\s*trăm\\s*(\\d))+";
		return input.replaceAll(patternString, "$2$3");
	}
    
    protected static String textxxxToNumber(String input) {
		String patternString = "((\\d)00\\s*(\\d\\d))+";
		return input.replaceAll(patternString, "$2$3");
	}
    protected static String textxxToNumber(String input) {
		String patternString = "((\\d)\\s*(\\d))+";
		return input.replaceAll(patternString, "$2$3");
	}
    protected static String text1xToNumber(String input) {
		String patternString = "(mười\\s*(\\d))+";
		return input.replaceAll(patternString, "1$2");
	}
    protected static String text1xToNumber2(String input) {
		String patternString = "(10\\s*(\\d))+";
		return input.replaceAll(patternString, "1$2");
	}
    protected static String textx0ToNumber(String input) {
		String patternString = "((\\d)\\s*mươi)+";
		return input.replaceAll(patternString, "$20");
	}
	protected static String textx00ToNumber(String input) {
		String patternString = "((\\d)\\s*trăm)+";
		return input.replaceAll(patternString, "$200");
	}
	protected static String textx1ToNumber(String input) {
		String patternString = "((\\d)\\s*mốt)+";	
		return input.replaceAll(patternString, "$21");
	}
	protected static String textx4ToNumber(String input) {
		String patternString = "((\\d)\\s*tư)+";
		return input.replaceAll(patternString, "$24");
	}
	protected static String textx1ToNumber2(String input) {
		String patternString = "((\\d)\\s*mươi\\s*mốt)+";
		return input.replaceAll(patternString, "$21");
	}
	protected static String textx4ToNumber2(String input) {
		String patternString = "((\\d)\\s*mươi\\s*tư)+";
		return input.replaceAll(patternString, "$24");
	}
	protected static String textProcessByRegex(String input, String patternString, Function2<Matcher, StringBuilder, StringBuilder> stringBuilder) {
		return textProcessByRegex(input, Pattern.compile(patternString), stringBuilder);
	}
	protected static String textProcessByRegex(String input, Pattern pattern, Function2<Matcher, StringBuilder, StringBuilder> stringBuilder) {
    	Matcher matcher = pattern.matcher(input);
    	StringBuilder builder = new StringBuilder();
    	int start = 0;
    	while (matcher.find()) {
	    	builder.append(input.substring(start, matcher.start()));
	    	start = matcher.end();
	    	stringBuilder.apply(matcher, builder);
    	}
    	if (start < input.length()) {
    		builder.append(input.substring(start));    		
    	}
    	return builder.toString();
	}
    
    private StringUtil() {}
}
