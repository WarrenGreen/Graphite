package structures;

import structures.Entry;

public class HashEntry<K, V> implements Entry<K, V> {
	
	private K key;
	private V value;
	
	public HashEntry(K k, V v){
		this.key 	= k;
		this.value 	= v;
	}
	
	@Override
	public K getKey() {
		return this.key;
	}

	@Override
	public V getValue() {
		return this.value;
	}
	
	public V setValue(V val){
		V oldValue = this.value;
		this.value = val;
		return oldValue;
	}
	
	public boolean equals(Object o){
		HashEntry<K, V> ent;
		try{
			ent = (HashEntry<K, V>) o;
		}catch (ClassCastException ex){
			return false;
		}
		return (ent.getKey() == key) && (ent.getValue() == value);
	}
}