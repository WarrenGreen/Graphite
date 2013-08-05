package structures;

import structures.InvalidKeyException;

/**
 * Interface for a map which stores key-value pairs.
 * 
 * @author WarrenGreen
 *
 * @param <K>
 * @param <V>
 */
public interface Map<K, V> {
	/** Returns the number of entries in the map. */
	public int size();
	
	/** Returns whether the map is empty*/
	public boolean isEmpty();
	
	/** Returns a value in the map with the corresponding key */
	public V get(K k) throws InvalidKeyException;
	
	/**  If an Entry with value k is in the map replace the Entry's value with v and return the old value
	 * 	 else add an entry(k,v) and return null
	 */
	public V put(K k, V v) throws InvalidKeyException;
	
	/** If an entry with a key that is equal to k, remove that entry and return its value else return null*/
	public V remove(K k) throws InvalidKeyException;
	
	/** Returns iterable collection of all keys in the map */
	public Iterable<K> keySet();
	
	/** Returns iterable collection of all values in the map */
	public Iterable<V> values();
	
	/** Returns iterable collection of all key-value entries in the map */
	public Iterable<Entry<K,V>> entrySet();
}
