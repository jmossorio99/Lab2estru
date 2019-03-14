package model;

import java.util.ArrayList;

import controller.MainWindowController;
import threads.CashierThread;

public class Store {

	private int finishedCashiers = 0;
	private Client[] clients;
	private ArrayList<Client> clientsExit = new ArrayList<Client>();
	private CashierThread[] cashierThreads;
	private ArrayList<Shelf> shelves;
	private GenericMinPriorityQueue<Client> queue;
	private int lastClientAdded = 0;
	private MainWindowController controller;

	public Store(int cashierNum, MainWindowController controller) {

		this.controller = controller;
		cashierThreads = new CashierThread[cashierNum];
		for (int i = 0; i < cashierThreads.length; i++) {
			cashierThreads[i] = new CashierThread(this);
		}
		shelves = new ArrayList<Shelf>();

	}

	public void addClient(String id, int time) {
		clients[lastClientAdded] = new Client(id, time);
		lastClientAdded++;
	}

	public void addShelf(String id, int bookNum) {
		shelves.add(new Shelf(id, bookNum));
	}

	public void addBook(String isbn, double price, int units, int shelfIndex) {
		shelves.get(shelfIndex).addBook(new Book(isbn, price, units));
	}

	public void addBookToCart(String isbn, int clientIndex) {

		Client c = clients[clientIndex];
		Book book = searchBook(isbn);
		if (book != null) {
			c.addBookToCart(book);
		}

	}

	public Book searchBook(String isbn) {

		Book b = null;
		for (int i = 0; i < shelves.size() && b == null; i++) {

			if (shelves.get(i).getBook(isbn) != null) {
				b = shelves.get(i).getBook(isbn);
			}

		}
		return b;

	}

	@SuppressWarnings("unchecked")
	public void insertClientsIntoQueue() {

		for (int i = 0; i < clients.length; i++) {
			queue.offer(clients[i]);
		}

	}

	public void startThreads() {
		for (int i = 0; i < cashierThreads.length; i++) {
			cashierThreads[i].start();
		}
	}

	public boolean isQueueEmpty() {
		return queue.isEmpty();
	}

	public Client getClientFromQueue() {
		return queue.poll();
	}

	public String getClientCart(int index) {

		String cart = "";
		GenericStack<Book> s = clients[index].getCart();
		while (!s.isEmpty()) {

			cart += s.peek().getIsbn() + " ";

		}
		return cart;

	}

	public String getClientValue(int index) {

		String value;
		int v = 0;
		GenericStack<Book> s = clientsExit.get(index).getCart();
		while (!s.isEmpty()) {

			v += s.peek().getPrice();

		}
		value = "" + v;
		return value;

	}

	public void addClientExit(Client c) {
		clientsExit.add(c);
	}

	public void cashierFinished() {
		finishedCashiers++;
		if (finishedCashiers == cashierThreads.length) {
			controller.printOutput();
		}
	}

	public ArrayList<Client> getClientsExit() {
		return clientsExit;
	}

}
