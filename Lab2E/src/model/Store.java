package model;

import java.util.ArrayList;

import threads.CashierThread;

public class Store {

	private Client[] clients;
	private CashierThread[] cashierThreads;
	private ArrayList<Shelf> shelves;
	private GenericMinPriorityQueue queue;
	private int lastClientAdded = 0;

	public Store(int clientNum, int cashierNum) {

		clients = new Client[clientNum];
		cashierThreads = new CashierThread[cashierNum];
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

}
