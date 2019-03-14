package model;

public class Object<T> {

	private T object;
	private Object next;
	private Object last;

	public Object(T object) {
		this.object = object;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public Object getNext() {
		return next;
	}

	public void setNext(Object next) {
		this.next = next;
	}

	public Object getLast() {
		return last;
	}

	public void setLast(Object last) {
		this.last = last;
	}

}
