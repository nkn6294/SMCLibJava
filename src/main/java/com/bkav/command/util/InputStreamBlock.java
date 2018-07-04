package com.bkav.command.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import com.bkav.command.common.Consumer;
import com.bkav.command.common.Predicate;

public class InputStreamBlock {

	public static final Character[] DELIMETER_WORD = { ' ', '\n', '\t', (char) 13, (char) 65279 };

	public InputStreamBlock(Reader reader) {
		this.reader = reader;
	}

	public InputStreamBlock(InputStream stream) {
		this.reader = new BufferedReader(new InputStreamReader(stream));
	}

	public int next() throws IOException {
		return this.reader.read();
	}

	public String nextElement(Predicate<Character> predicate) throws IOException {
		if (predicate == null) {
			return null;
		}
		int next = -1;
		do {
			if ((next = reader.read()) < 0) {
				return null;
			}
		} while (!predicate.test((char) next));
		StringWriter writer = new StringWriter();
		do {
			writer.write(next);
			if ((next = reader.read()) < 0) {
				break;
			}
		} while (predicate.test((char) next));
		return writer.toString();
	}

	public String nextElement() throws IOException {
		return this.nextElement(new Predicate<Character>() {
			@Override
			public boolean test(Character value) {
				return Character.isLetterOrDigit(value);
			}
		});
	}

	public String nextElement(final Character[] delimeterChar) throws IOException {
		return this.nextElement(new Predicate<Character>() {

			@Override
			public boolean test(Character value) {
				return !CollectionUtil.contains(delimeterChar, value);
			}
		});
	}

	public String nextElementAdvance(final Character[] delimeterChar, final Consumer<Character> consumer)
			throws IOException {
		return this.nextElement(new Predicate<Character>() {
			@Override
			public boolean test(Character value) {
				if (CollectionUtil.contains(delimeterChar, value)) {
					if (consumer != null) {
						consumer.accept(value);
					}
					return false;
				}
				return true;
			}
		});
	}

	public Reader getReader() {
		return this.reader;
	}

	private final Reader reader;

	public void close() throws IOException {
		this.reader.close();
	}

	public String nextWord() throws IOException {
		return this.nextElement(DELIMETER_WORD);
	}
}
