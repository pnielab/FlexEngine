package com.hp.opta.flex.configuration.model.tree;

/**
 * The Class LeafNode.
 *
 * @param <T> the generic type
 */
public class LeafNode<T> implements Node<T> {
	
	/** The value. */
	private T value;
	
	/**
	 * Instantiates a new leaf node.
	 */
	public LeafNode() {
	}
	
	/**
	 * Instantiates a new leaf node.
	 *
	 * @param value the value
	 */
	public LeafNode(T value) {
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see com.hp.opta.flex.configuration.model.tree.Node#computeValue()
	 */
	@Override
	public T computeValue() {
		return getValue();
	}	

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public T getValue() {
		return value;
	}
	
	
	
}
