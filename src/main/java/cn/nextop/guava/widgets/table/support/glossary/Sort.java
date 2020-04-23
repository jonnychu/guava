package cn.nextop.guava.widgets.table.support.glossary;

/**
 * @author jonny
 */
public enum Sort {
	ASC, DESC, NONE, ETERNAL;
	
	public Sort next() {
		switch (this) {
		case NONE: return ASC;
		case ASC: return DESC;
		case DESC: return NONE;
		default: return ETERNAL;
		}
	}
}
