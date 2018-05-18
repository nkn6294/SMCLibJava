package com.bkav.util;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {
	public static <T> Stream<T> createStream(Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false);
	}
	public static <T> Stream<T> createStream(Iterator<T> iterator) {
		Iterable<T> iterable = () -> iterator;
		return createStream(iterable);
	}
	public static <T> Stream<T> createTream2(Iterator<T> iterator) {
		Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);
		return StreamSupport.stream(spliterator, false);
	}
	
}
