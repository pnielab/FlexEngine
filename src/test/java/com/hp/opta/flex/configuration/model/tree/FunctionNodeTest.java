package com.hp.opta.flex.configuration.model.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
		FunctionNode<Object> test = new FunctionNode<>();
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
			Node<Object> node = new LeafNode();
			l.add(node);
		}
		
		test = new FunctionNode<>(f,l);
		Assert.assertNotNull(test);
		Assert.assertNotNull(test.getParameters());
		Assert.assertNotNull(test.getFunction());
		Assert.assertEquals(5, test.getParameters().size());
	}	
	

	@Test
	public void testSetFunction() {
		FunctionNode<Object> test = new FunctionNode<>();
		test.setFunction(new Function<List<Object>, Object>() {
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
	public void testSetParameter() {
		FunctionNode<Object> test = new FunctionNode<>();
		
		List<Node<Object>> parametersList = new LinkedList<>();
		for(int i =0; i < 5; i++){
			Node<Object> node = new LeafNode();
			parametersList.add(node);
		}
		
		test.setParameters(parametersList);
		Assert.assertNotNull(test.getParameters());
		Assert.assertEquals(5, test.getParameters().size());
	}	
	
	
	@Test
	public void testAddParameter() {
		FunctionNode<Object> test = new FunctionNode<>();
		
		for(int i =0; i < 5; i++){
			Node<Object> node = new LeafNode();
			test.addParameters(node);
		}
		
		Assert.assertNotNull(test.getParameters());
		Assert.assertEquals(5, test.getParameters().size());
	}	
	
	@Test
	public void testCompute() {
		FunctionNode<Object> test = new FunctionNode<>();
		
		test.setFunction(new Function<List<Object>, Object>() {
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
			test.addParameters(node);
		}
		
		System.out.println(test.computeValue());
		Assert.assertEquals("01234", test.computeValue());
	}	


}
