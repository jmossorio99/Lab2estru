package model;

import java.util.ArrayList;

public class GenericStack<T> {

	private Object<T> first;
	private long elements;

	public GenericStack() {
		elements = 0;
	}

	@SuppressWarnings("unchecked")
	public T pop() {
		T value = first.getObject();

		if (elements == 1) {
			first = null;
			elements--;
		} else {
			first = first.getNext();
			elements--;
		}

		return value;
	}

	public T peek() {
		return first.getObject();
	}

	public void push(T element) {
		Object<T> obj = new Object<T>(element);
		if (first == null) {
			first = obj;
			elements++;
		} else {
			obj.setNext(first);
			first = obj;
			elements++;
		}
	}

	public boolean isEmpty() {
		if (elements == 0) {
			return true;
		} else {
			return false;
		}
	}
}
