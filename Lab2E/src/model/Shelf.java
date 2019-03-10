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

}
