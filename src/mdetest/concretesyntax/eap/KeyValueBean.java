package mdetest.concretesyntax.eap;

public class KeyValueBean<K, V> {
	public K getKey() {
		return key;
	}
	public V getVal() {
		return val;
	}
	public KeyValueBean(K key, V val) {
		super();
		this.key = key;
		this.val = val;
	}
	K key;
	V val;

}
