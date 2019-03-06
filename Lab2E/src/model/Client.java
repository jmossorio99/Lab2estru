package model;

public class Client implements Comparable<Client> {

	private String id;
	private GenericStack<Book> cart;
	private int time;

	public Client(String id, int time) {

		this.id = id;
		this.time = time;
		cart = new GenericStack<Book>();

	}

	@Override
	public int compareTo(Client arg0) {
		return 0;
	}

}
