package structures;

import java.util.ArrayList;

public class HashMap<K, V> implements Map<K, V>{
	
	private Entry<K, V> AVAILABLE = new HashEntry<K, V>(null, null);
	private int size;
	private int scale, shift; 		// shift and scaling factors
	private int prime, capacity; 	// prime factor and capacity of bucket array
	private Entry<K, V>[] bucket;
	
	/**
	 * Constructor
	 */
	public HashMap(int prime, int cap){
		size		= 0;
		capacity 	= cap;
		bucket 		= new HashEntry[capacity];
		this.prime = prime;
	}
	
	public HashMap(int cap){
		this(109345121, cap);
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size<=0;
	}

	@Override
	public V get(K k) throws InvalidKeyException {
		int i = findEntry(k);
		if(i<0) return null;
		return bucket[i].getValue();
		
	}

	@Override
	public V put(K k, V v) throws InvalidKeyException{
		int i = findEntry(k);
		if(i >= 0)
			return ((HashEntry<K, V>) bucket[i]).setValue(v);
		if(size >= capacity/2){
			rehash();
			i = findEntry(k);
		}
		bucket[-i-1] = new HashEntry<K, V>(k, v);
		size++;
		return null;
	}

	@Override
	public V remove(K k) throws InvalidKeyException{
		int i = findEntry(k);
		if(i < 0) return null;
		V toReturn = bucket[i].getValue();
		bucket[i] = AVAILABLE;
		size--;
		return toReturn;
	}

	@Override
	public Iterable<K> keySet() {
		ArrayList<K> keys = new ArrayList<K>();
		for(int i=0; i<capacity; i++){
			if((bucket[i] != null) && (bucket[i] != AVAILABLE)){
				keys.add(bucket[i].getKey());
			}
		}
		return keys;
	}

	@Override
	public Iterable<V> values() {
		ArrayList<V> values = new ArrayList<V>();
		for(int i=0; i<capacity; i++){
			if((bucket[i] != null) && (bucket[i] != AVAILABLE)){
				values.add(bucket[i].getValue());
			}
		}
		
		return values;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> entries = new ArrayList<Entry<K, V>>();
		for(int i=0; i<capacity; i++){
			if((bucket[i] != null) && (bucket[i] != AVAILABLE)){
				entries.add(bucket[i]);
			}
		}
		return entries;
	}
	
	protected void checkKey(K k) throws InvalidKeyException {
		if(k == null) throw new InvalidKeyException("Invalid key: null");
	}
	
	protected void rehash() throws InvalidKeyException{
		this.capacity = 2 * this.capacity;
		Entry<K, V>[] old = this.bucket;
		bucket = (Entry<K, V>[]) new Entry[this.capacity];
		java.util.Random rand = new java.util.Random();
		
		scale = rand.nextInt(this.prime - 1) + 1;
		shift = rand.nextInt(this.prime);
		
		for(int i=0; i< old.length; i++){
			Entry<K,V> e = old[i];
			if((e != null) && (e != AVAILABLE)){
				int j = -1 - findEntry(e.getKey());
				bucket[j] = e;
			}
		}
	}
	
	protected int findEntry(K k) throws InvalidKeyException {
		checkKey(k);
	
		int avail = -1;
		int i = compressionValue(k);
		int j = i;
		
		do
		{
			Entry<K, V> e = bucket[i];
			if( e == null){
				if(avail < 0)
					avail = i;
				break;
			}
			
			if(k.equals(e.getKey()))
				return i;
			if(e == AVAILABLE) {
				if(avail < 0)
					avail = i;
			}
			
			i= (i + 1) % capacity;
		} while(i != j);
		
		return -(avail + 1);
	}
	
	protected int compressionValue(K k) {
		return (int) ((Math.abs(hashCode(k.toString()) * scale + shift) % prime) % capacity);
	}
	
	protected int hashCode(String s){
		int h = 0;
		
		for(int i=0; i<s.length(); i++){
			h = (h << 5) | (h >>> 27);
			h += (int) s.charAt(i);
		}
		
		return h;
	}

}
