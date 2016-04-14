package com.hp.opta.flex.configuration.model.tree;

import java.io.Serializable;

/**
 * The Interface Node.
 *
 * @param <T> the generic type
 */
public interface Node<T> extends Serializable{

	/**
	 * Compute value.
	 *
	 * @return the t
	 */
	public T computeValue();

}
