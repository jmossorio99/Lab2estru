package threads;

import model.Book;
import model.Client;
import model.GenericStack;
import model.Store;

public class CashierThread extends Thread {

	private Store store;
	private Client currentClient;
	private GenericStack<Book> newStack = new GenericStack<Book>();

	public CashierThread(Store store) {

		this.store = store;

	}

	@Override
	public void run() {

		super.run();

	}

	public void reverseStack(GenericStack<Book> stack1) {

		while (!stack1.isEmpty()) {
			newStack.push(stack1.pop());
		}

	}

}
