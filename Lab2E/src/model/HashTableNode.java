package model;

public class HashTableNode<K,T> {

	private K key;
	private T element;
	
	public HashTableNode(K key,T element) {
		this.key=key;
		this.element=element;
	}
	
	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}
}