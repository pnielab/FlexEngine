package com.hp.opta.flex.configuration.model.tree;

/**
 * The Interface Node.
 *
 * @param <T> the generic type
 */
public interface Node<T> {

	/**
	 * Compute value.
	 *
	 * @return the t
	 */
	public T computeValue();

}
