package com.hp.opta.flex.configuration.model;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TokenMetaDataIndexComparatorTest {

	private static TokenMetaDataIndexComparator comparator;
	
	@BeforeClass
	public static void setUp() throws Exception {
		comparator = new TokenMetaDataIndexComparator();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBasic() {
		TokenMetaData t1 = new TokenMetaData();
		t1.setIndex(1);
		
		TokenMetaData t2 = new TokenMetaData();
		t2.setIndex(2);
		
		int result = comparator.compare(t1, t2);
		
		Assert.assertEquals(-1, result);
	}
	
	
	@Test
	public void testListSort() {
		
		List<TokenMetaData> list = new LinkedList<>();
		for(int i = 5; i > 0; i--){
			TokenMetaData t = new TokenMetaData();
			t.setIndex(i);
			list.add(t);
		}

		StringBuilder resultBeforeSort = new StringBuilder();
		
		for(TokenMetaData t : list){
			resultBeforeSort.append(t.getIndex());
		}
		
		System.out.println(resultBeforeSort.toString());
		
		list.sort(comparator);
		
		StringBuilder resultAfterSort = new StringBuilder();
		
		for(TokenMetaData t : list){
			resultAfterSort.append(t.getIndex());
		}

		System.out.println(resultAfterSort.toString());

		Assert.assertNotEquals(resultBeforeSort, resultAfterSort);
		Assert.assertEquals("12345", resultAfterSort.toString());
	}
	
	
	

}
