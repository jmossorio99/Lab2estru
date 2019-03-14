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

	public void addBookToCart(Book b) {
		cart.push(b);
		setTime(time + 1);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public GenericStack<Book> getCart() {
		return cart;
	}

	public void setCart(GenericStack<Book> cart) {
		this.cart = cart;
	}

	@Override
	public int compareTo(Client c) {

		if (getTime() > c.getTime()) {
			return 1;
		} else if (getTime() < c.getTime()) {
			return -1;
		} else {
			return 0;
		}

	}

}
