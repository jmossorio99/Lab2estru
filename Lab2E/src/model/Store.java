package model;

import java.util.ArrayList;

import controller.MainWindowController;
import threads.CashierThread;

public class Store {

	private int finishedCashiers = 0;
	private ArrayList<Client> clients;
	private ArrayList<Client> clientsExit = new ArrayList<Client>();
	private CashierThread[] cashierThreads;
	private ArrayList<Shelf> shelves;
	private GenericMinPriorityQueue<Client> queue;
	private MainWindowController controller;

	public Store(int cashierNum, MainWindowController controller) {

		this.controller = controller;
		cashierThreads = new CashierThread[cashierNum];
		for (int i = 0; i < cashierThreads.length; i++) {
			cashierThreads[i] = new CashierThread(this);
		}
		shelves = new ArrayList<Shelf>();

	}

	public void setClientsSize(int num) {
		clients = new ArrayList<Client>();
		queue = new GenericMinPriorityQueue<Client>(num + 2);
	}

	public void addClient(String id, int time) {
		clients.add(new Client(id, time));
	}

	public void addShelf(String id, int bookNum) {
		shelves.add(new Shelf(id, bookNum));
	}

	public void addBook(String isbn, double price, int units, int shelfIndex) {
		shelves.get(shelfIndex).addBook(new Book(isbn, price, units));
	}

	public void addBookToCart(String isbn, int clientIndex) {

		Client c = clients.get(clientIndex);
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

		for (int i = 0; i < clients.size(); i++) {
			queue.offer(clients.get(i));
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
		GenericStack<Book> s = clientsExit.get(index).getCart();
		GenericStack<Book> copy = new GenericStack<Book>();
		while (!s.isEmpty()) {

			cart += s.peek().getIsbn() + " ";
			copy.push(s.pop());

		}
		while (!copy.isEmpty()) {
			s.push(copy.pop());
		}
		return cart;

	}
	
	public String getClientCartTemp(int index) {
		String cart = "";
		GenericStack<Book> s = clients.get(index).getCart();
		GenericStack<Book> copy = new GenericStack<Book>();
		while (!s.isEmpty()) {

			cart += s.peek().getIsbn() + " ";
			copy.push(s.pop());

		}
		while (!copy.isEmpty()) {
			s.push(copy.pop());
		}
		return cart;
	}

	public String getClientValue(int index) {

		String value;
		int v = 0;
		GenericStack<Book> s = clientsExit.get(index).getCart();
		GenericStack<Book> copy = new GenericStack<Book>();
		while (!s.isEmpty()) {

			v += s.peek().getPrice();
			copy.push(s.pop());

		}
		while (!copy.isEmpty()) {
			s.push(copy.pop());
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

	public ArrayList<Client> clients() {
		// TODO Auto-generated method stub
		return clients;
	}

}
