package com.tinnova.processo.domain;

public enum Marca {
	FIAT("FIAT"),
	FORD("FORD"),
	RENAULT("RENAULT"),
	FERRARI("FERRARI"),
	CHEVROLET("CHEVROLET"),
	VOLKSWAGEN("VOLKSWAGEN"),
	HONDA("HONDA");

	private String displayName;

	Marca(final String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return this.displayName;
	}
}
