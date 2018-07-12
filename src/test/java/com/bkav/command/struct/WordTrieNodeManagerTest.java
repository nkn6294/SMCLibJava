package com.bkav.command.struct;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.command.SystemManager;
import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.data.HomeArea;
import com.bkav.command.demo.data.HomeDevice;
import com.bkav.command.demo.data.HomeDeviceType;
import com.bkav.command.demo.model.HomeAreaModel;
import com.bkav.command.demo.model.HomeDeviceModel;
import com.bkav.command.demo.model.HomeDeviceTypeModel;

public class WordTrieNodeManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.wordTrieNodes = new WordTrieNodeManager();
		WordTrieNode<HomeDeviceType> deviceTypeNode = new HomeDeviceTypeModel(SampleData.DEVICE_TYPE).getWordTrieNode();
		WordTrieNode<HomeDevice> deviceNode = new HomeDeviceModel(SampleData.DEVICES).getWordTrieNode();
		WordTrieNode<HomeArea> areaNode = new HomeAreaModel(SampleData.AREAS).getWordTrieNode();
		this.wordTrieNodes.addWordTrieNode(areaNode, deviceTypeNode, deviceNode);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testFindCommands() {
		String[] s1 = { "phong", "khach", "phong", "trung", "tam", "den", "dieu", "hoa", "buoi", "trua" };
		ResultNode<?> node = this.wordTrieNodes.find(s1);
		SystemManager.logger.info(node.toString());
		assertTrue(true); // TODO testFindCommands
	}

	@Test
	public final void testFindAdvanceCommands() {
		String[] s1 = { "phong", "khach", "phong", "trung", "tam", "den", "dieu", "hoa", "buoi", "trua" };
		ResultTreeNode<?> node = this.wordTrieNodes.findAdvance(s1);
		SystemManager.logger.info(node.toString());
		assertTrue(true); // TODO testFindCommands
	}

	@Test
	public final void testFindPharasesWithReset() {
		WordTrieNode<HomeDeviceType> wordTrieNode = new HomeDeviceTypeModel(SampleData.DEVICE_TYPE).getWordTrieNode();
		String[] s1 = { "phong", "khach", "phong", "an", "dieu", "hoa", "buoi", "den", "trua" };
		SystemManager.logger.info(Arrays.toString(s1));
		for (ResultFind<?> resultFind : WordTrieNodeManager
				.findPharasesWithReset(new ResultFind<Object>(null, new String[] {}, s1), wordTrieNode)) {
			assertTrue(resultFind.getValue() instanceof HomeDeviceType);
			String[] detectes = resultFind.getDetects();
			String[] remains = resultFind.getRemains();
			List<String> strings = new ArrayList<>();
			for (String s : s1) {
				strings.add(s);
			}
			for (String detecte : detectes) {
				strings.remove(detecte);
			}
			for (String remain : remains) {
				strings.remove(remain);
			}
			assertTrue(strings.size() == 0);
			SystemManager.logger.info(resultFind.toString());
		}

	}

	@Test
	public final void testFindPharases() {
		WordTrieNode<HomeDeviceType> wordTrieNode = new HomeDeviceTypeModel(SampleData.DEVICE_TYPE).getWordTrieNode();
		String[] s1 = { "phong", "khach", "phong", "an", "dieu", "hoa", "buoi", "trua", "dieu", "den", "abc" };
		SystemManager.logger.info(Arrays.toString(s1));
		ResultsFind<HomeDeviceType> results = WordTrieNodeManager.findPharases(new ResultsFind<>(s1), wordTrieNode);
		// assertTrue(results.size() == 2);
		assertTrue(true);// TODO testFindPharases
		for (HomeDeviceType deviceType : results) {
			SystemManager.logger.info(deviceType.getName());
		}
		SystemManager.logger.info(Arrays.toString(results.remains()));
	}

	private WordTrieNodeManager wordTrieNodes;
}
