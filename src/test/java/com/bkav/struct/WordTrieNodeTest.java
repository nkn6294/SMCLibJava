package com.bkav.struct;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.SystemManager;
import com.bkav.command.model.entity.HomeDeviceTypeModel;
import com.bkav.home.data.HomeDeviceType;

public class WordTrieNodeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testFindPharases() {
		WordTrieNode<HomeDeviceType> wordTrieNode = new HomeDeviceTypeModel().getWordTrieNode();
		String[] s1 = { "phong", "khach", "phong", "an", "dieu", "hoa", "buoi", "trua", "dieu", "den", "abc"};
		SystemManager.logger.info(Arrays.toString(s1));
		ResultsProcess results = wordTrieNode.findPharases(new ResultsProcess(s1));
		assertTrue(results.size() == 2);
		results.stream().map(Object::toString).forEach(SystemManager.logger::info);
		SystemManager.logger.info(Arrays.toString(results.remains()));
	}

}
