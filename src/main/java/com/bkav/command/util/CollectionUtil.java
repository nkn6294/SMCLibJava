package com.bkav.command.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.bkav.command.SystemManager;
import com.bkav.command.common.CommandTextProcesser;

public class CollectionUtil {
	
	public static <T> boolean arrayEquals(T[] object1, T[] object2) {
		if (object1.length != object2.length) {
			return false;
		}
		for (int index = 0; index < object1.length; index++) {
			if (object1[index] != object2[index]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean arrayEquals(int[] object1, int[] object2) {
		if (object1.length != object2.length) {
			return false;
		}
		for (int index = 0; index < object1.length; index++) {
			if (object1[index] != object2[index]) {
				return false;
			}
		}
		return true;
	}
	public static boolean arrayEquals(long[] object1, long[] object2) {
		if (object1.length != object2.length) {
			return false;
		}
		for (int index = 0; index < object1.length; index++) {
			if (object1[index] != object2[index]) {
				return false;
			}
		}
		return true;
	}
	public static boolean arrayEquals(boolean[] object1, boolean[] object2) {
		if (object1.length != object2.length) {
			return false;
		}
		for (int index = 0; index < object1.length; index++) {
			if (object1[index] != object2[index]) {
				return false;
			}
		}
		return true;
	}
	public static boolean arrayEquals(float[] object1, float[] object2) {
		if (object1.length != object2.length) {
			return false;
		}
		for (int index = 0; index < object1.length; index++) {
			if (object1[index] != object2[index]) {
				return false;
			}
		}
		return true;
	}
	public static boolean arrayEquals(double[] object1, double[] object2) {
		if (object1.length != object2.length) {
			return false;
		}
		for (int index = 0; index < object1.length; index++) {
			if (object1[index] != object2[index]) {
				return false;
			}
		}
		return true;
	}
    public static final <T> boolean contains(T[] collection, T key) {
        if (collection == null) {
            return false;
        }
        return Arrays.stream(collection).anyMatch(key::equals);
    }

    public static final <T> boolean contains(List<T> collection, T key) {
        if (collection == null) {
            return false;
        }
        return collection.contains(key);
    }
    public static final boolean contains(Map<String, String[]> collection, String value) {
        if (collection == null || value == null) {
            return false;
        }
    	return collection.entrySet().stream()
    			.filter(entry -> value.startsWith(entry.getKey()))
    			.flatMap(entry -> Arrays.stream(entry.getValue()))
    			.anyMatch(value::equals); 
    }

    public static final <T> void combine(List<T> sourceData, List<T> expandData) {
    	if (sourceData != null && expandData != null) {
    		expandData.forEach(sourceData::add);
    	}
    }

    public static <T> void combine(List<T> sourceData, T[] expandData) {
        if (sourceData != null && expandData != null) {
        	Arrays.stream(expandData).forEach(sourceData::add);
        }
    }

    public static boolean isExited(Map<Character, Map<Integer, Set<String>>> saveMap, String item) {
        if (saveMap == null || item == null || item.length() == 0) {
            return false;
        }
        char[] chars = item.toCharArray();
        Map<Integer, Set<String>> datas = saveMap.get(chars[0]);
        if (datas == null) {
        	return false;        	
        }
        Set<String> strings = datas.get(chars.length);
        return strings == null ? false : strings.contains(item);
    }

    public static void insert(Map<Character, Map<Integer, Set<String>>> saveMap, String item) {
        if (saveMap == null || item == null || item.length() == 0) {
            return;
        }
        char[] chars = item.toCharArray();
        Map<Integer, Set<String>> datas = saveMap.get(chars[0]);
        if (datas != null) {
            Set<String> strings = datas.get(chars.length);
            if (strings == null) {
                strings = new HashSet<>();
                strings.add(item);
                datas.put(chars.length, strings);
            } else {
                strings.add(item);
            }
        } else {
            Set<String> strings = new HashSet<>();
            strings.add(item);
            datas = new HashMap<>();
            datas.put(chars.length, strings);
            saveMap.put(chars[0], datas);
        }
    }
    
    public static void showAllItem(Map<Character, Map<Integer, Set<String>>> saveMap) {
		saveMap.values().stream()
			.flatMap(map -> map.values().stream())
			.flatMap(Set::stream)
			.forEach(SystemManager.logger::info);
    }
    
    public static final String[][] convert(String[] inputs) {    	
    	return Arrays.stream(inputs)
    			.map(StringUtil::splitString)
    			.filter(item -> item.length > 0)
    			.toArray(String[][]::new);
    }
    public static final String[][] convert(String[] inputs, CommandTextProcesser textProcesser) {
    	return Arrays.stream(inputs)
//    			.peek(SystemManager.logger::info)
	    		.map(textProcesser::apply)
//	    		.peek(SystemManager.logger::info)
	    		.map(textProcesser::textToWords)
	    		.filter(item -> item.length > 0)
	    		.toArray(String[][]::new);
    }
    public static final List<List<String>> convert(List<String> inputs) {
		return inputs.stream()
				.map(StringUtil::splitStringToList).filter(item -> !item.isEmpty())
				.collect(Collectors.toList());
    }
    public static final List<List<String>> convert(List<String> inputs, CommandTextProcesser textProcesser) {
    	return inputs.stream()
//    			.peek(SystemManager.logger::info)
		    	.map(textProcesser::apply)
//		    	.peek(SystemManager.logger::info)
				.map(textProcesser::textToListWords)
				.filter(item -> !item.isEmpty())
				.collect(Collectors.toList());
    }
    
    public static final String[][] toArray(List<List<String>> input) {
    	String[][] output = new String[input.size()][];
		IntStream.range(0, input.size()).forEach(index -> {
			List<String> item = input.get(index);
			output[index] = item.toArray(new String[item.size()]);
		});
		return output;
    }
    
    public static boolean[] toArray(Boolean[] input) {	
		boolean[] output = new boolean[input.length];
		IntStream.range(0, input.length).forEach(index -> output[index] = input[index]);
		return output;
	}
	
	public static int[] toArray(Integer[] input) {	
		int[] output = new int[input.length];
		IntStream.range(0, input.length).forEach(index -> output[index] = input[index]);
		return output;
	}
	
	public static long[] toArray(Long[] input) {
		long[] output = new long[input.length];
		IntStream.range(0, input.length).forEach(index -> output[index] = input[index]);
		return output;
	}
	
	public static float[] toArray(Float[] input) {
		float[] output = new float[input.length];
		IntStream.range(0, input.length).forEach(index -> output[index] = input[index]);
		return output;
	}
	
	public static double[] toArray(Double[] input) {
		double[] output = new double[input.length];
		IntStream.range(0, input.length).forEach(index -> output[index] = input[index]);
		return output;
	}
	
	public static int[][] toArray(Integer[][] input) {
		int[][] output = new int[input.length][];
		IntStream.range(0, input.length).forEach(index -> output[index] = toArray(input[index]));
		return output;
	}
	
	public static long[][] toArray(Long[][] input) {
		long[][] output = new long[input.length][];
		IntStream.range(0, input.length).forEach(index -> output[index] = toArray(input[index]));
		return output;
	}
	
	public static boolean[][] toArray(Boolean[][] input) {
		boolean[][] output = new boolean[input.length][];
		IntStream.range(0, input.length).forEach(index -> output[index] = toArray(input[index]));
		return output;
	}
	
	public static double[][] toArray(Double[][] input) {
		double[][] output = new double[input.length][];
		IntStream.range(0, input.length).forEach(index -> output[index] = toArray(input[index]));
		return output;
	}
	
	public static float[][] toArray(Float[][] input) {
		float[][] output = new float[input.length][];
		IntStream.range(0, input.length).forEach(index -> output[index] = toArray(input[index]));
		return output;
	}
	
	public static <T> Predicate<T> distinctByProperty(Function<? super T, ?> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Set<Object> seen = ConcurrentHashMap.newKeySet();
	    return t -> seen.add(keyExtractor.apply(t));
	}
	
	public static <T> Predicate<T> distinctByCompator(Comparator<T> compator) {
		List<T> seen = new ArrayList<>();
		return t -> seen.stream()
				.filter(item -> compator.compare(t, item) != 0)
				.peek(seen::add).findFirst().isPresent();
	}
	
	public static <T> Predicate<T[]> distinctArray() {
		List<T[]> seen = new ArrayList<>();
		return itemInput -> {
			boolean isContain = seen.stream().anyMatch(item -> arrayEquals(itemInput, item));
			if (isContain) {
				return false;
			}
			seen.add(itemInput);
			return true;
		};
	}
	
	public static Predicate<int[]> distinctArrayInteger() {
		List<int[]> seen = new ArrayList<>();
		return itemInput -> {
			boolean isContain = seen.stream().anyMatch(item -> arrayEquals(itemInput, item));
			if (!isContain) {
				seen.add(itemInput);
			}
			return !isContain;
		};
	}
	
	public static Predicate<long[]> distinctArrayLong() {
		List<long[]> seen = new ArrayList<>();
		return itemInput -> {
			boolean isContain = seen.stream().anyMatch(item -> arrayEquals(itemInput, item));
			if (!isContain) {
				seen.add(itemInput);
			}
			return !isContain;
		};
	}
	public static Predicate<boolean[]> distinctArrayBoolean() {
		List<boolean[]> seen = new ArrayList<>();
		return itemInput -> {
			boolean isContain = seen.stream().anyMatch(item -> arrayEquals(itemInput, item));
			if (!isContain) {
				seen.add(itemInput);
			}
			return !isContain;
		};
	}
	public static Predicate<float[]> distinctArrayFloat() {
		List<float[]> seen = new ArrayList<>();
		return itemInput -> {
			boolean isContain = seen.stream().anyMatch(item -> arrayEquals(itemInput, item));
			if (!isContain) {
				seen.add(itemInput);
			}
			return !isContain;
		};
	}
	public static Predicate<double[]> distinctArrayDouble() {
		List<double[]> seen = new ArrayList<>();
		return itemInput -> {
			boolean isContain = seen.stream().anyMatch(item -> arrayEquals(itemInput, item));
			if (!isContain) {
				seen.add(itemInput);
			}
			return !isContain;
		};
	}
	public static <T> Stream<T[]> optimalArray(T[][] input) {
		return Arrays.stream(input)
				.filter(array -> array.length > 0)
				.filter(CollectionUtil.distinctArray());
	}
	public static int[][] optimalArrayInteger(int[][] input) {
		return Arrays.stream(input)
				.filter(array -> array.length > 0)
				.filter(CollectionUtil.distinctArrayInteger())
				.toArray(int[][]::new);
	}
	public static long[][] optimalArrayLong(long[][] input) {
		return Arrays.stream(input)
				.filter(array -> array.length > 0)
				.filter(CollectionUtil.distinctArrayLong())
				.toArray(long[][]::new);
	}
	public static boolean[][] optimalArrayBoolean(boolean[][] input) {
		return Arrays.stream(input)
				.filter(array -> array.length > 0)
				.filter(CollectionUtil.distinctArrayBoolean())
				.toArray(boolean[][]::new);
	}
	public static float[][] optimalArrayFloat(float[][] input) {
		return Arrays.stream(input)
				.filter(array -> array.length > 0)
				.filter(CollectionUtil.distinctArrayFloat())
				.toArray(float[][]::new);
	}
	public static double[][] optimalArrayDouble(double[][] input) {
		return Arrays.stream(input)
				.filter(array -> array.length > 0)
				.filter(CollectionUtil.distinctArrayDouble())
				.toArray(double[][]::new);
	}
	private CollectionUtil() {}
}
=======
package com.bkav.command.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.bkav.command.SystemManager;
import com.bkav.command.common.CommandTextProcesser;

public class CollectionUtil {
	
	public static <T> boolean arrayEquals(T[] object1, T[] object2) {
		if (object1.length != object2.length) {
			return false;
		}
		for (int index = 0; index < object1.length; index++) {
			if (object1[index] != object2[index]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean arrayEquals(int[] object1, int[] object2) {
		if (object1.length != object2.length) {
			return false;
		}
		for (int index = 0; index < object1.length; index++) {
			if (object1[index] != object2[index]) {
				return false;
			}
		}
		return true;
	}
	public static boolean arrayEquals(long[] object1, long[] object2) {
		if (object1.length != object2.length) {
			return false;
		}
		for (int index = 0; index < object1.length; index++) {
			if (object1[index] != object2[index]) {
				return false;
			}
		}
		return true;
	}
	public static boolean arrayEquals(boolean[] object1, boolean[] object2) {
		if (object1.length != object2.length) {
			return false;
		}
		for (int index = 0; index < object1.length; index++) {
			if (object1[index] != object2[index]) {
				return false;
			}
		}
		return true;
	}
	public static boolean arrayEquals(float[] object1, float[] object2) {
		if (object1.length != object2.length) {
			return false;
		}
		for (int index = 0; index < object1.length; index++) {
			if (object1[index] != object2[index]) {
				return false;
			}
		}
		return true;
	}
	public static boolean arrayEquals(double[] object1, double[] object2) {
		if (object1.length != object2.length) {
			return false;
		}
		for (int index = 0; index < object1.length; index++) {
			if (object1[index] != object2[index]) {
				return false;
			}
		}
		return true;
	}
    public static final <T> boolean contains(T[] collection, T key) {
        if (collection == null) {
            return false;
        }
        return Arrays.stream(collection).anyMatch(key::equals);
    }

    public static final <T> boolean contains(List<T> collection, T key) {
        if (collection == null) {
            return false;
        }
        return collection.contains(key);
    }
    public static final boolean contains(Map<String, String[]> collection, String value) {
        if (collection == null || value == null) {
            return false;
        }
    	return collection.entrySet().stream()
    			.filter(entry -> value.startsWith(entry.getKey()))
    			.flatMap(entry -> Arrays.stream(entry.getValue()))
    			.anyMatch(value::equals); 
    }

    public static final <T> void combine(List<T> sourceData, List<T> expandData) {
    	if (sourceData != null && expandData != null) {
    		expandData.forEach(sourceData::add);
    	}
    }

    public static <T> void combine(List<T> sourceData, T[] expandData) {
        if (sourceData != null && expandData != null) {
        	Arrays.stream(expandData).forEach(sourceData::add);
        }
    }

    public static boolean isExited(Map<Character, Map<Integer, Set<String>>> saveMap, String item) {
        if (saveMap == null || item == null || item.length() == 0) {
            return false;
        }
        char[] chars = item.toCharArray();
        Map<Integer, Set<String>> datas = saveMap.get(chars[0]);
        if (datas == null) {
        	return false;        	
        }
        Set<String> strings = datas.get(chars.length);
        return strings == null ? false : strings.contains(item);
    }

    public static void insert(Map<Character, Map<Integer, Set<String>>> saveMap, String item) {
        if (saveMap == null || item == null || item.length() == 0) {
            return;
        }
        char[] chars = item.toCharArray();
        Map<Integer, Set<String>> datas = saveMap.get(chars[0]);
        if (datas != null) {
            Set<String> strings = datas.get(chars.length);
            if (strings == null) {
                strings = new HashSet<>();
                strings.add(item);
                datas.put(chars.length, strings);
            } else {
                strings.add(item);
            }
        } else {
            Set<String> strings = new HashSet<>();
            strings.add(item);
            datas = new HashMap<>();
            datas.put(chars.length, strings);
            saveMap.put(chars[0], datas);
        }
    }
    
    public static void showAllItem(Map<Character, Map<Integer, Set<String>>> saveMap) {
		saveMap.values().stream()
			.flatMap(map -> map.values().stream())
			.flatMap(Set::stream)
			.forEach(SystemManager.logger::info);
    }
    
    public static final String[][] convert(String[] inputs) {    	
    	return Arrays.stream(inputs)
    			.map(StringUtil::splitString)
    			.filter(item -> item.length > 0)
    			.toArray(String[][]::new);
    }
    public static final String[][] convert(String[] inputs, CommandTextProcesser textProcesser) {
    	return Arrays.stream(inputs)
//    			.peek(SystemManager.logger::info)
	    		.map(textProcesser::apply)
//	    		.peek(SystemManager.logger::info)
	    		.map(textProcesser::textToWords)
	    		.filter(item -> item.length > 0)
	    		.toArray(String[][]::new);
    }
    public static final List<List<String>> convert(List<String> inputs) {
		return inputs.stream()
				.map(StringUtil::splitStringToList).filter(item -> !item.isEmpty())
				.collect(Collectors.toList());
    }
    public static final List<List<String>> convert(List<String> inputs, CommandTextProcesser textProcesser) {
    	return inputs.stream()
//    			.peek(SystemManager.logger::info)
		    	.map(textProcesser::apply)
//		    	.peek(SystemManager.logger::info)
				.map(textProcesser::textToListWords)
				.filter(item -> !item.isEmpty())
				.collect(Collectors.toList());
    }
    
    public static final String[][] toArray(List<List<String>> input) {
    	String[][] output = new String[input.size()][];
		IntStream.range(0, input.size()).forEach(index -> {
			List<String> item = input.get(index);
			output[index] = item.toArray(new String[item.size()]);
		});
		return output;
    }
    
    public static boolean[] toArray(Boolean[] input) {	
		boolean[] output = new boolean[input.length];
		IntStream.range(0, input.length).forEach(index -> output[index] = input[index]);
		return output;
	}
	
	public static int[] toArray(Integer[] input) {	
		int[] output = new int[input.length];
		IntStream.range(0, input.length).forEach(index -> output[index] = input[index]);
		return output;
	}
	
	public static long[] toArray(Long[] input) {
		long[] output = new long[input.length];
		IntStream.range(0, input.length).forEach(index -> output[index] = input[index]);
		return output;
	}
	
	public static float[] toArray(Float[] input) {
		float[] output = new float[input.length];
		IntStream.range(0, input.length).forEach(index -> output[index] = input[index]);
		return output;
	}
	
	public static double[] toArray(Double[] input) {
		double[] output = new double[input.length];
		IntStream.range(0, input.length).forEach(index -> output[index] = input[index]);
		return output;
	}
	
	public static int[][] toArray(Integer[][] input) {
		int[][] output = new int[input.length][];
		IntStream.range(0, input.length).forEach(index -> output[index] = toArray(input[index]));
		return output;
	}
	
	public static long[][] toArray(Long[][] input) {
		long[][] output = new long[input.length][];
		IntStream.range(0, input.length).forEach(index -> output[index] = toArray(input[index]));
		return output;
	}
	
	public static boolean[][] toArray(Boolean[][] input) {
		boolean[][] output = new boolean[input.length][];
		IntStream.range(0, input.length).forEach(index -> output[index] = toArray(input[index]));
		return output;
	}
	
	public static double[][] toArray(Double[][] input) {
		double[][] output = new double[input.length][];
		IntStream.range(0, input.length).forEach(index -> output[index] = toArray(input[index]));
		return output;
	}
	
	public static float[][] toArray(Float[][] input) {
		float[][] output = new float[input.length][];
		IntStream.range(0, input.length).forEach(index -> output[index] = toArray(input[index]));
		return output;
	}
	
	public static <T> Predicate<T> distinctByProperty(Function<? super T, ?> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Set<Object> seen = ConcurrentHashMap.newKeySet();
	    return t -> seen.add(keyExtractor.apply(t));
	}
	
	public static <T> Predicate<T> distinctByCompator(Comparator<T> compator) {
		List<T> seen = new ArrayList<>();
		return t -> seen.stream()
				.filter(item -> compator.compare(t, item) != 0)
				.peek(seen::add).findFirst().isPresent();
	}
	
	public static <T> Predicate<T[]> distinctArray() {
		List<T[]> seen = new ArrayList<>();
		return itemInput -> {
			boolean isContain = seen.stream().anyMatch(item -> arrayEquals(itemInput, item));
			if (isContain) {
				return false;
			}
			seen.add(itemInput);
			return true;
		};
	}
	
	public static Predicate<int[]> distinctArrayInteger() {
		List<int[]> seen = new ArrayList<>();
		return itemInput -> {
			boolean isContain = seen.stream().anyMatch(item -> arrayEquals(itemInput, item));
			if (!isContain) {
				seen.add(itemInput);
			}
			return !isContain;
		};
	}
	
	public static Predicate<long[]> distinctArrayLong() {
		List<long[]> seen = new ArrayList<>();
		return itemInput -> {
			boolean isContain = seen.stream().anyMatch(item -> arrayEquals(itemInput, item));
			if (!isContain) {
				seen.add(itemInput);
			}
			return !isContain;
		};
	}
	public static Predicate<boolean[]> distinctArrayBoolean() {
		List<boolean[]> seen = new ArrayList<>();
		return itemInput -> {
			boolean isContain = seen.stream().anyMatch(item -> arrayEquals(itemInput, item));
			if (!isContain) {
				seen.add(itemInput);
			}
			return !isContain;
		};
	}
	public static Predicate<float[]> distinctArrayFloat() {
		List<float[]> seen = new ArrayList<>();
		return itemInput -> {
			boolean isContain = seen.stream().anyMatch(item -> arrayEquals(itemInput, item));
			if (!isContain) {
				seen.add(itemInput);
			}
			return !isContain;
		};
	}
	public static Predicate<double[]> distinctArrayDouble() {
		List<double[]> seen = new ArrayList<>();
		return itemInput -> {
			boolean isContain = seen.stream().anyMatch(item -> arrayEquals(itemInput, item));
			if (!isContain) {
				seen.add(itemInput);
			}
			return !isContain;
		};
	}
	public static <T> Stream<T[]> optimalArray(T[][] input) {
		return Arrays.stream(input)
				.filter(array -> array.length > 0)
				.filter(CollectionUtil.distinctArray());
	}
	public static int[][] optimalArrayInteger(int[][] input) {
		return Arrays.stream(input)
				.filter(array -> array.length > 0)
				.filter(CollectionUtil.distinctArrayInteger())
				.toArray(int[][]::new);
	}
	public static long[][] optimalArrayLong(long[][] input) {
		return Arrays.stream(input)
				.filter(array -> array.length > 0)
				.filter(CollectionUtil.distinctArrayLong())
				.toArray(long[][]::new);
	}
	public static boolean[][] optimalArrayBoolean(boolean[][] input) {
		return Arrays.stream(input)
				.filter(array -> array.length > 0)
				.filter(CollectionUtil.distinctArrayBoolean())
				.toArray(boolean[][]::new);
	}
	public static float[][] optimalArrayFloat(float[][] input) {
		return Arrays.stream(input)
				.filter(array -> array.length > 0)
				.filter(CollectionUtil.distinctArrayFloat())
				.toArray(float[][]::new);
	}
	public static double[][] optimalArrayDouble(double[][] input) {
		return Arrays.stream(input)
				.filter(array -> array.length > 0)
				.filter(CollectionUtil.distinctArrayDouble())
				.toArray(double[][]::new);
	}
	private CollectionUtil() {}
}
