package com.bkav.command.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.bkav.command.common.CommandTextProcesser;
import com.bkav.command.common.Function;
import com.bkav.command.common.Predicate;

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
		for (int index = 0; index < collection.length; index++) {
			T value = collection[index];
			if (key.equals(value)) {
				return true;
			}
		}
		return false;
	}

	public static final <T> boolean contains(List<T> collection, T key) {
		if (collection == null) {
			return false;
		}
		return collection.contains(key);
	}

	public static final <T> void combine(List<T> sourceData, List<T> expandData) {
		if (sourceData != null && expandData != null) {
			for (T value : expandData) {
				sourceData.add(value);
			}
		}
	}

	public static <T> void combine(List<T> sourceData, T[] expandData) {
		if (sourceData != null && expandData != null) {
			for (T data : expandData) {
				sourceData.add(data);
			}
		}
	}

	public static final String[][] convert(String[] inputs) {
		List<String[]> listOutput = new ArrayList<>();
		for (String input : inputs) {
			String[] words = StringUtil.splitString(input);
			if (words.length == 0) {
				continue;
			}
			listOutput.add(words);
		}
		String[][] output = new String[listOutput.size()][];
		for (int index = 0; index < output.length; index++) {
			output[index] = listOutput.get(index);
		}
		return output;
	}

	public static final String[][] convert(String[] inputs, CommandTextProcesser textProcesser) {

		List<String[]> listOutput = new ArrayList<>();
		for (String input : inputs) {
			String inputProcessed = textProcesser.apply(input);
			String[] words = textProcesser.textToWords(inputProcessed);
			if (words.length == 0) {
				continue;
			}
			listOutput.add(words);
		}
		String[][] output = new String[listOutput.size()][];
		for (int index = 0; index < output.length; index++) {
			output[index] = listOutput.get(index);
		}
		return output;
	}

	public static final List<List<String>> convert(List<String> inputs) {
		List<List<String>> output = new ArrayList<>();
		for (String input : inputs) {
			List<String> words = StringUtil.splitStringToList(input);
			if (words.isEmpty()) {
				continue;
			}
			output.add(words);
		}
		return output;
	}

	public static final List<List<String>> convert(List<String> inputs, CommandTextProcesser textProcesser) {

		List<List<String>> output = new ArrayList<>();
		for (String input : inputs) {
			String inputProcessed = textProcesser.apply(input);
			List<String> words = StringUtil.splitStringToList(inputProcessed);
			if (words.isEmpty()) {
				continue;
			}
			output.add(words);
		}
		return output;
	}

	public static final String[][] toArray(List<List<String>> input) {
		String[][] output = new String[input.size()][];
		for (int index = 0; index < input.size(); index++) {
			List<String> item = input.get(index);
			output[index] = item.toArray(new String[item.size()]);
		}
		return output;
	}

	public static boolean[] toArray(Boolean[] input) {
		boolean[] output = new boolean[input.length];
		for (int index = 0; index < input.length; index++) {
			output[index] = input[index];
		}
		return output;
	}

	public static int[] toArray(Integer[] input) {
		int[] output = new int[input.length];
		for (int index = 0; index < input.length; index++) {
			output[index] = input[index];
		}
		return output;
	}

	public static long[] toArray(Long[] input) {
		long[] output = new long[input.length];
		for (int index = 0; index < input.length; index++) {
			output[index] = input[index];
		}
		return output;
	}

	public static float[] toArray(Float[] input) {
		float[] output = new float[input.length];
		for (int index = 0; index < input.length; index++) {
			output[index] = input[index];
		}
		return output;
	}

	public static double[] toArray(Double[] input) {
		double[] output = new double[input.length];
		for (int index = 0; index < input.length; index++) {
			output[index] = input[index];
		}
		return output;
	}

	public static int[][] toArray(Integer[][] input) {
		int[][] output = new int[input.length][];
		for (int index = 0; index < input.length; index++) {
			output[index] = toArray(input[index]);
		}
		return output;
	}

	public static long[][] toArray(Long[][] input) {
		long[][] output = new long[input.length][];
		for (int index = 0; index < input.length; index++) {
			output[index] = toArray(input[index]);
		}
		return output;
	}

	public static boolean[][] toArray(Boolean[][] input) {
		boolean[][] output = new boolean[input.length][];
		for (int index = 0; index < input.length; index++) {
			output[index] = toArray(input[index]);
		}
		return output;
	}

	public static double[][] toArray(Double[][] input) {
		double[][] output = new double[input.length][];
		for (int index = 0; index < input.length; index++) {
			output[index] = toArray(input[index]);
		}
		return output;
	}

	public static float[][] toArray(Float[][] input) {
		float[][] output = new float[input.length][];
		for (int index = 0; index < input.length; index++) {
			output[index] = toArray(input[index]);
		}
		return output;
	}

	public static <T> Predicate<T> distinctByProperty(final Function<? super T, ?> keyExtractor) {
		final Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return new Predicate<T>() {
			@Override
			public boolean test(T t) {
				return map.put(keyExtractor.apply(t), Boolean.TRUE) == null;
			}
		};
	}

	public static <T> Predicate<T> distinctByKey(final Function<? super T, ?> keyExtractor) {
		final Set<Object> seen = new HashSet<>();// ConcurrentHashMap.newKeySet();
		return new Predicate<T>() {
			@Override
			public boolean test(T t) {
				return seen.add(keyExtractor.apply(t));
			}
		};
	}

	public static <T> Predicate<T> distinctByComparator(final Comparator<T> compator) {
		final List<T> seen = new ArrayList<>();
		return new Predicate<T>() {
			@Override
			public boolean test(T t) {
				for (T item : seen) {
					if (compator.compare(t, item) != 0) {
						seen.add(item);
						return true;
					}
				}
				return false;
			}
		};
	}

	public static <T> Predicate<T[]> distinctArray() {
		final List<T[]> seen = new ArrayList<>();
		return new Predicate<T[]>() {
			@Override
			public boolean test(T[] itemInput) {

				boolean isContain = false;
				for (T[] item : seen) {
					if (arrayEquals(itemInput, item)) {
						isContain = true;
						break;
					}
				}
				if (isContain) {
					return false;
				}
				seen.add(itemInput);
				return true;
			}
		};
	}

	public static Predicate<int[]> distinctArrayInteger() {
		final List<int[]> seen = new ArrayList<>();
		return new Predicate<int[]>() {

			@Override
			public boolean test(int[] itemInput) {
				boolean isContain = false;
				for (int[] item : seen) {
					if (arrayEquals(itemInput, item)) {
						isContain = true;
						break;
					}
				}
				if (!isContain) {
					seen.add(itemInput);
				}
				return !isContain;
			}
		};
	}

	public static Predicate<long[]> distinctArrayLong() {
		final List<long[]> seen = new ArrayList<>();
		return new Predicate<long[]>() {

			@Override
			public boolean test(long[] itemInput) {
				boolean isContain = false;
				for (long[] item : seen) {
					if (arrayEquals(itemInput, item)) {
						isContain = true;
						break;
					}
				}
				if (!isContain) {
					seen.add(itemInput);
				}
				return !isContain;
			}
		};
	}

	public static Predicate<boolean[]> distinctArrayBoolean() {
		final List<boolean[]> seen = new ArrayList<>();
		return new Predicate<boolean[]>() {
			@Override
			public boolean test(boolean[] itemInput) {
				boolean isContain = false;
				for (boolean[] item : seen) {
					if (arrayEquals(itemInput, item)) {
						isContain = true;
						break;
					}
				}
				if (!isContain) {
					seen.add(itemInput);
				}
				return !isContain;
			}
		};
	}

	public static Predicate<float[]> distinctArrayFloat() {
		final List<float[]> seen = new ArrayList<>();
		return new Predicate<float[]>() {
			@Override
			public boolean test(float[] itemInput) {
				boolean isContain = false;
				for (float[] item : seen) {
					if (arrayEquals(itemInput, item)) {
						isContain = true;
						break;
					}
				}
				if (!isContain) {
					seen.add(itemInput);
				}
				return !isContain;
			}
		};
	}

	public static Predicate<double[]> distinctArrayDouble() {
		final List<double[]> seen = new ArrayList<>();
		return new Predicate<double[]>() {
			@Override
			public boolean test(double[] itemInput) {
				boolean isContain = false;
				for (double[] item : seen) {
					if (arrayEquals(itemInput, item)) {
						isContain = true;
						break;
					}
				}
				if (!isContain) {
					seen.add(itemInput);
				}
				return !isContain;
			}
		};
	}

	public static int[][] optimalArrayInteger(int[][] inputs) {
		List<int[]> listOutput = new ArrayList<>();
		for (int[] input : inputs) {
			if (input.length == 0) {
				continue;
			}
			if (CollectionUtil.distinctArrayInteger().test(input)) {

			}
		}
		int[][] output = new int[listOutput.size()][];
		for (int index = 0; index < listOutput.size(); index++) {
			output[index] = listOutput.get(index);
		}
		return output;
	}

	public static long[][] optimalArrayLong(long[][] inputs) {
		List<long[]> listOutput = new ArrayList<>();
		for (long[] input : inputs) {
			if (input.length == 0) {
				continue;
			}
			if (CollectionUtil.distinctArrayLong().test(input)) {

			}
		}
		long[][] output = new long[listOutput.size()][];
		for (int index = 0; index < listOutput.size(); index++) {
			output[index] = listOutput.get(index);
		}
		return output;
	}

	public static boolean[][] optimalArrayBoolean(boolean[][] inputs) {
		List<boolean[]> listOutput = new ArrayList<>();
		for (boolean[] input : inputs) {
			if (input.length == 0) {
				continue;
			}
			if (CollectionUtil.distinctArrayBoolean().test(input)) {

			}
		}
		boolean[][] output = new boolean[listOutput.size()][];
		for (int index = 0; index < listOutput.size(); index++) {
			output[index] = listOutput.get(index);
		}
		return output;
	}

	public static float[][] optimalArrayFloat(float[][] inputs) {
		List<float[]> listOutput = new ArrayList<>();
		for (float[] input : inputs) {
			if (input.length == 0) {
				continue;
			}
			if (CollectionUtil.distinctArrayFloat().test(input)) {

			}
		}
		float[][] output = new float[listOutput.size()][];
		for (int index = 0; index < listOutput.size(); index++) {
			output[index] = listOutput.get(index);
		}
		return output;
	}

	public static double[][] optimalArrayDouble(double[][] inputs) {
		List<double[]> listOutput = new ArrayList<>();
		for (double[] input : inputs) {
			if (input.length == 0) {
				continue;
			}
			if (CollectionUtil.distinctArrayDouble().test(input)) {

			}
		}
		double[][] output = new double[listOutput.size()][];
		for (int index = 0; index < listOutput.size(); index++) {
			output[index] = listOutput.get(index);
		}
		return output;
	}

	private CollectionUtil() {
	}
}
