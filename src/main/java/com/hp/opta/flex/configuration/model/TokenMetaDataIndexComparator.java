package com.hp.opta.flex.configuration.model;

import java.util.Comparator;

/**
 * The Class TokenMetaDataIndexComparator.
 */
public class TokenMetaDataIndexComparator implements Comparator<TokenMetaData>{

	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(TokenMetaData o1, TokenMetaData o2) {
		return Integer.compare(o1.getIndex(), o2.getIndex());
	}
	
}