package model;

import java.util.ArrayList;

public class GenericStack<T> {

	private ArrayList<T> stack = new ArrayList<T>();

	public void push(T element) {
		stack.add(element);
	}

	public T peek() {
		return stack.get(stack.size() - 1);
	}

	public T pop() {
		T r = stack.get(stack.size() - 1);
		stack.remove(stack.get(stack.size() - 1));
		return r;
	}

	public int search(T element) {
		for (int i = 0; i < stack.size(); i++) {
			if (stack.get(i).equals(element)) {
				return i;
			}
		}
		return -1;
	}

	public boolean isEmpty() {
		return stack.size() == 0 ? true : false;
	}
}
