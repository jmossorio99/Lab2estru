package model;

public class Book implements Comparable<Book>{

	private String isbn;
	private double price;
	private int units;
	private double priority;

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
	
	public void setPriority(int shelve, int depth) {
		this.priority = (shelve*0.8) + (depth*0.2);
	}
	
	public double getPriority() {
		return priority;
	}

	@Override
	public int compareTo(Book o) {
		
		if(o.getPriority()==priority) {
			return 0;
		}
		else if(o.getPriority()>priority) {
			return -1;
		}
		else {
			return 1;
		}
		
	}
	
    
}
