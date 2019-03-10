package model;

public class GenericHashTable<K, T> {

	public final static int STARTED_SIZE = 100;
	private int tableSize;
	private HashTableNode<K, T>[] hashTable;
	private double numberOfKeysUsed;

	// private T [] reHashing;

	@SuppressWarnings("unchecked")
	public GenericHashTable() {

		hashTable = new HashTableNode[STARTED_SIZE];
		numberOfKeysUsed = 0;
		tableSize = STARTED_SIZE;

	}

	public int hash(int k) {

		double A = (Math.sqrt(5) + 1) / 2d;
		int hash = (int) (tableSize * (Math.floor((k * A) % 1.0)));
		return hash;

	}

	public void put(K key, T element) {

		HashTableNode<K, T> node = new HashTableNode<K, T>(key, element);

		int keyL = hash(node.getKey().hashCode());

		if (hashTable[keyL] == null) {
			hashTable[keyL] = node;
			numberOfKeysUsed++;
		}
		// colision
		else {
			putR(node, keyL + 1);
		}

		if (numberOfKeysUsed / tableSize > 0.50) {
			reHashing();
		}

	}

	public void putR(HashTableNode<K, T> node, int k) {

		int position = realValue(k);

		if (hashTable[position] == null) {
			hashTable[position] = node;
			numberOfKeysUsed++;
		}
		// colision
		else {
			putR(node, position + 1);
		}

	}

	public int realValue(int k) {

		return (int) (k % tableSize);

	}

	@SuppressWarnings("unchecked")
	public void reHashing() {

		// Hashtable<Integer,String> ht = new Hashtable<Integer,String>();

		tableSize = tableSize * 3;
		numberOfKeysUsed = 0;

		HashTableNode<K, T>[] copy = hashTable.clone();

		hashTable = new HashTableNode[tableSize];

		for (int i = 0; i < copy.length; i++) {

			if (copy[i] != null) {

				put(copy[i].getKey(), copy[i].getElement());

			}

		}
	}

	public boolean isEmpty() {
		if (numberOfKeysUsed == 0) {
			return true;
		} else {
			return false;
		}
	}

	public T get(K key) {
		T value = null;

		if (numberOfKeysUsed != 0) {
			int position = hash(key.hashCode());
			if (hashTable[position].getKey().equals(key)) {
				value = (T) hashTable[position].getElement();
			}
			// hubo colision
			else {
				value = getR(position + 1, key);
			}
		}

		return value;
	}

	public T getR(int position, K key) {

		int posi = realValue(position);

		if (hashTable[posi] != null) {

			if (hashTable[posi].getKey().equals(key)) {
				return (T) hashTable[posi].getElement();
			} else {
				return getR(position + 1, key);
			}
		} else {
			return null;
		}
	}

	public T remove(K key) {

		T value = null;

		if (numberOfKeysUsed != 0) {
			int position = hash(key.hashCode());

			if (hashTable[position] != null) {

				if (hashTable[position].getKey().equals(key)) {
					value = (T) hashTable[position].getElement();
					hashTable[position] = null;
					numberOfKeysUsed--;
				}
				// hubo colision
				else {
					value = removeR(position + 1, key);
				}
			}
		}

		return value;

	}

	public T removeR(int position, K key) {

		int posi = realValue(position);

		if (hashTable[posi] != null) {

			if (hashTable[posi].getKey().equals(key)) {
				T valor = (T) hashTable[posi].getElement();
				hashTable[posi] = null;
				numberOfKeysUsed--;
				return valor;
			}

			else {
				return getR(position + 1, key);
			}

		} else {
			return null;
		}
	}

	/**
	 * public boolean contains(K key) { boolean found=false;
	 * 
	 * 
	 * 
	 * return found; }
	 */

}
