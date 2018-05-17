package com.bkav.struct;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.SystemManager;
import com.bkav.command.data.TimeInDay;
import com.bkav.command.model.entity.HomeAreaModel;
import com.bkav.command.model.entity.HomeDeviceModel;
import com.bkav.command.model.entity.HomeDeviceTypeModel;
import com.bkav.command.model.time.TimeInDayModel;
import com.bkav.home.data.HomeArea;
import com.bkav.home.data.HomeDevice;
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
		this.wordTrieNodes = new WordTrieNodeManager();
		WordTrieNode<HomeDeviceType> deviceTypeNode = new HomeDeviceTypeModel().getWordTrieNode();
		WordTrieNode<HomeDevice> deviceNode = new HomeDeviceModel().getWordTrieNode();
		WordTrieNode<HomeArea> areaNode = new HomeAreaModel().getWordTrieNode();
		WordTrieNode<TimeInDay> timeInDayNode = new TimeInDayModel().getWordTrieNode();
		this.wordTrieNodes.addWordTrieNode(areaNode, deviceTypeNode, deviceNode, timeInDayNode);
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
		assertTrue(false); // TODO testFindCommands
	}

	@Test
	public final void testFindPharasesWithRemain() {
		WordTrieNode<HomeDeviceType> wordTrieNode = new HomeDeviceTypeModel().getWordTrieNode();
		String[] s1 = { "phong", "khach", "phong", "an", "dieu", "hoa", "buoi", "den", "trua" };
		SystemManager.logger.info(Arrays.toString(s1));
		WordTrieNodeManager.findPharases(new ResultFind<Object>(null, new String[] {}, s1), wordTrieNode)
				.forEach(resultFind -> {
					assertTrue(resultFind.getValue() instanceof HomeDeviceType);
					String[] detectes = resultFind.getDetects();
					String[] remains = resultFind.getRemains();
					LinkedList<String> strings = new LinkedList<>();
					Arrays.stream(s1).forEach(strings::add);
					Arrays.stream(detectes).forEach(strings::remove);
					Arrays.stream(remains).forEach(strings::remove);
					assertTrue(strings.size() == 0);
					SystemManager.logger.info(resultFind.toString());
				});
	}

	private WordTrieNodeManager wordTrieNodes;
}
