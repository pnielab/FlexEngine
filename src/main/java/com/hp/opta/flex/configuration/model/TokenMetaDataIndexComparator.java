package com.hp.opta.flex.configuration.model;

import java.util.Comparator;

public class TokenMetaDataIndexComparator implements Comparator<TokenMetaData>{

	@Override
	public int compare(TokenMetaData o1, TokenMetaData o2) {
		return Integer.compare(o1.getIndex(), o2.getIndex());
	}
	
}