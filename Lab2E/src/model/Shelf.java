package model;

public class Shelf {

	private String id;
	private int bookNum;
	private GenericHashTable<String, Book> hashTable;

	public Shelf(String id, int bookNum) {

		this.id = id;
		this.bookNum = bookNum;
		hashTable = new GenericHashTable<String, Book>();

	}

	public void addBook(Book b) {
		hashTable.put(b.getIsbn(), b);
	}

	public Book getBook(String isbn) {
		Book b = hashTable.get(isbn);
		subtractUnit(b);
		return b;
	}

	public void subtractUnit(Book b) {
		if (b.getUnits() > 0) {
			b.setUnits(b.getUnits() - 1);
		} else {
			hashTable.remove(b.getIsbn());
		}
	}

}
