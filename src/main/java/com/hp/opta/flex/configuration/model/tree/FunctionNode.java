package com.hp.opta.flex.configuration.model.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * The Class FunctionNode.
 *
 * @param <T> the generic type
 */
public class FunctionNode<T> implements Node<Object> {
	
	/** The function. */
	private Function<List<T>, T> function;
	
	/** The parameters. */
	private List<Node<T>> parameters;
	
	/**
	 * Instantiates a new function node.
	 */
	public FunctionNode() {
		function = null;
		parameters = new LinkedList<>();
	}
	
	/**
	 * Instantiates a new function node.
	 *
	 * @param function the function
	 * @param parameters the parameters
	 */
	public FunctionNode(Function<List<T>, T> function, List<Node<T>> parameters) {
		this.function = function;
		this.parameters = parameters;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hp.opta.flex.configuration.model.tree.Node#computeValue()
	 */
	@Override
	public T computeValue() {
		List<T> results = new LinkedList<>();
		for(Node<T> node : parameters){
			results.add(node.computeValue());
		}
		return function.apply(results);
	}

	/**
	 * Gets the function.
	 *
	 * @return the function
	 */
	public Function<List<T>, T> getFunction() {
		return function;
	}

	/**
	 * Sets the function.
	 *
	 * @param function the function
	 */
	public void setFunction(Function<List<T>, T> function) {
		this.function = function;
	}

	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public List<Node<T>> getParameters() {
		return parameters;
	}

	/**
	 * Sets the parameters.
	 *
	 * @param parameters the new parameters
	 */
	public void setParameters(List<Node<T>> parameters) {
		this.parameters = parameters;
	}
	
	
	/**
	 * Adds the parameters.
	 *
	 * @param parameter the parameter
	 */
	public void addParameters(Node<T> parameter) {
		if(this.parameters == null){
			this.parameters = new LinkedList<>();
		}
		this.parameters.add(parameter);
	}
	
}
