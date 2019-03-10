package model;

public class Book {

	private String isbn;
	private double price;
	private int units;

	public Book(String isbn, double price, int units) {

		this.isbn = isbn;
		this.price = price;
		this.units = units;

	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

}
