package com.hp.opta.flex.configuration.model.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FunctionNodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testBasic() {
		FunctionNode<Object> test = new FunctionNode<>(null);
		Assert.assertNotNull(test);
		Assert.assertNotNull(test.getParameters());
		
		
		Function<List<Object>, Object> f = new Function<List<Object>, Object>() {
			@Override
			public Object apply(List<Object> t) {
				return t.size();
			}
		};
		
		List<Node<Object>> l = new LinkedList<>();
		for(int i =0; i < 5; i++){
			Node<Object> node = new LeafNode(null);
			l.add(node);
		}
		
		test = new FunctionNode<>(f,l);
		Assert.assertNotNull(test);
		Assert.assertNotNull(test.getParameters());
		Assert.assertNotNull(test.getFunction());
		
		final LongAdder adr = new LongAdder();
		test.getParameters().forEach((n) -> adr.increment());
		
		Assert.assertEquals(5, adr.sum());
	}	
	

	@Test
	public void testSetFunction() {
		FunctionNode<Object> test = new FunctionNode<>(new Function<List<Object>, Object>() {
			@Override
			public Object apply(List<Object> t) {
				return t.size();
			}
		});
		
		List<Object> list = Arrays.asList("1","2","3");
		Assert.assertNotNull(test.getFunction());
		Assert.assertEquals(3, test.getFunction().apply(list));
	}
	
	
	@Test
	public void testAddParameter() {
		FunctionNode<Object> test = new FunctionNode<>(null);
		
		for(int i =0; i < 5; i++){
			Node<Object> node = new LeafNode(i);
			test.addParameter(node);
		}
		
		Assert.assertNotNull(test.getParameters());
		final LongAdder adr = new LongAdder();
		test.getParameters().forEach((n) -> adr.increment());
		Assert.assertEquals(5, adr.sum());
	}	
	
	@Test
	public void testCompute() {
		FunctionNode<Object> test = new FunctionNode<>(new Function<List<Object>, Object>() {
			@Override
			public Object apply(List<Object> t) {
				StringBuilder strBuilder = new StringBuilder();
				for(Object obj : t){
					strBuilder.append(String.valueOf(obj));
				}
				return strBuilder.toString();
			}
		});
		
		
		for(int i =0; i < 5; i++){
			Node<Object> node = new LeafNode(i);
			test.addParameter(node);
		}
		
		System.out.println(test.computeValue());
		Assert.assertEquals("01234", test.computeValue());
	}	


}
