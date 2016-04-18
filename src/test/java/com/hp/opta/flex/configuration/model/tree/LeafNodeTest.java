package com.hp.opta.flex.configuration.model.tree;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LeafNodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBasic() {
		LeafNode ln = new LeafNode<>("The new Value");
		
		assertEquals("The new Value", ln.getValue());
		assertEquals("The new Value", ln.computeValue());
		
		ln = new LeafNode<>("Another Value");
		
		assertEquals("Another Value", ln.getValue());
		assertEquals("Another Value", ln.computeValue());
		
	}

}
