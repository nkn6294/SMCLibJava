package com.bkav.struct;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bkav.command.SystemManager;
import com.bkav.command.data.SampleData;
import com.bkav.command.data.TimeInDay;
import com.bkav.command.model.ModelData;
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
		WordTrieNode<HomeDeviceType> deviceTypeNode = new HomeDeviceTypeModel(ModelData.DEVICE_TYPE).getWordTrieNode();
		WordTrieNode<HomeDevice> deviceNode = new HomeDeviceModel(SampleData.DEVICES).getWordTrieNode();
		WordTrieNode<HomeArea> areaNode = new HomeAreaModel(SampleData.AREAS).getWordTrieNode();
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
	public final void testFindPharasesWithReset() {
		WordTrieNode<HomeDeviceType> wordTrieNode = new HomeDeviceTypeModel(ModelData.DEVICE_TYPE).getWordTrieNode();
		String[] s1 = { "phong", "khach", "phong", "an", "dieu", "hoa", "buoi", "den", "trua" };
		SystemManager.logger.info(Arrays.toString(s1));
		WordTrieNodeManager.findPharasesWithReset(new ResultFind<Object>(null, new String[] {}, s1), wordTrieNode)
				.forEach(resultFind -> {
					assertTrue(resultFind.getValue() instanceof HomeDeviceType);
					String[] detectes = resultFind.getDetects();
					String[] remains = resultFind.getRemains();
					List<String> strings = Arrays.stream(s1).collect(Collectors.toList());
					Stream.concat(Arrays.stream(detectes), Arrays.stream(remains)).forEach(strings::remove);
					assertTrue(strings.size() == 0);
					SystemManager.logger.info(resultFind.toString());
				});
	}
	@Test
	public final void testFindPharases() {
		WordTrieNode<HomeDeviceType> wordTrieNode = new HomeDeviceTypeModel(ModelData.DEVICE_TYPE).getWordTrieNode();
		String[] s1 = { "phong", "khach", "phong", "an", "dieu", "hoa", "buoi", "trua", "dieu", "den", "abc"};
		SystemManager.logger.info(Arrays.toString(s1));
		ResultsFind<HomeDeviceType> results = WordTrieNodeManager.findPharases(new ResultsFind<>(s1), wordTrieNode);
		assertTrue(results.size() == 2);
		results.stream().map(HomeDeviceType::getName).forEach(SystemManager.logger::info);
		SystemManager.logger.info(Arrays.toString(results.remains()));
	}
	private WordTrieNodeManager wordTrieNodes;
}
