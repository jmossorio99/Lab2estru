package threads;

import model.Book;
import model.Client;
import model.GenericStack;
import model.Store;

public class CashierThread extends Thread {

	private boolean running = false;
	private Store store;
	private Client currentClient;
	private GenericStack<Book> newStack = new GenericStack<Book>();

	public CashierThread(Store store) {

		this.store = store;

	}

	public void init() {
		running = true;
	}

	@Override
	public void run() {
		super.run();
		while (!store.isQueueEmpty()) {
			newStack = new GenericStack<Book>();
			currentClient = store.getClientFromQueue();
			GenericStack<Book> stack1 = currentClient.getCart();
			while (!stack1.isEmpty()) {
				newStack.push(stack1.pop());
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			currentClient.setCart(newStack);
			store.addClientExit(currentClient);
		}
		store.cashierFinished();

	}

}
