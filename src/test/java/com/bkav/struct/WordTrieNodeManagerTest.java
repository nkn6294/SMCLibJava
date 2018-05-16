package com.bkav.struct;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.command.model.entity.HomeDeviceTypeModel;
import com.bkav.home.data.HomeDeviceType;

public class WordTrieNodeManagerTest {

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
	public final void testWordTrieNodeManager() {
		assertTrue(true); // TODO testWordTrieNodeManager
	}

	@Test
	public final void testAddWordTrieNode() {
		assertTrue(true); // TODO testAddWordTrieNode
	}

	@Test
	public final void testFindPharasesStringArrayCollectionOfTWordTrieNodeOfT() {
		assertTrue(true); // TODO testFindPharasesStringArrayCollectionOfTWordTrieNodeOfT
	}

	@Test
	public final void testFindPharasesWithRemain() {
		WordTrieNode<HomeDeviceType> wordTrieNode = new HomeDeviceTypeModel().getWordTrieNode();

		String[] s1 = { "phong", "khach", "dieu", "hoa", "buoi", "den", "trua" };
		List<HomeDeviceType> types = new ArrayList<>();
		System.out.println(Arrays.toString(s1));
		WordTrieNodeManager.findPharasesWithRemain(s1, types, wordTrieNode).forEach(System.out::println);
		assertTrue(true); // TODO testFindPharasesWithRemain
	}

	@Test
	public final void testFindPharasesIteratorOfStringCollectionOfTWordTrieNodeOfT() {
		assertTrue(true); // TODO testFindPharasesIteratorOfStringCollectionOfTWordTrieNodeOfT
	}

}
