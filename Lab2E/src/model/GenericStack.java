package model;

import java.util.ArrayList;

public class GenericStack<T> {

	private ArrayList<T> stack;

	public GenericStack() {

		stack = new ArrayList<T>();

	}

	public GenericStack(T[] arr) {

		stack = new ArrayList<T>();
		for (int i = 0; i < arr.length; i++) {
			stack.add(arr[i]);
		}

	}

	public ArrayList<T> getStack() {
		return stack;
	}

	public void setStack(ArrayList<T> stack) {
		this.stack = stack;
	}

	public T peek() {
		return stack.get(stack.size() - 1);
	}

	public T pop() {
		T r = stack.get(stack.size() - 1);
		stack.remove(r);
		return r;
	}

	public void push(T elem) {
		stack.add(elem);
	}

	public int search(T elem) {
		int r = -1;
		for (int i = 0; i < stack.size(); i++) {
			if (stack.get(i).equals(elem)) {
				r = i;
			}
		}
		return r;
	}

	public boolean isEmpty() {
		return (stack.isEmpty() ? true : false);
	}

}
