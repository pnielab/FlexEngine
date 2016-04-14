package com.hp.opta.flex.execution;

/**
 * The Interface EventFilter.
 *
 * @param <T> the generic type
 * @param <R> the generic type
 */
public interface EventParser<T,R>{
	
	
	/**
	 * Apply.
	 *
	 * @param input the input
	 * @return the r
	 */
	public R parse(T input);
	
}
