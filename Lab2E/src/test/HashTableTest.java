package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Book;
import model.GenericHashTable;

class HashTableTest {

	GenericHashTable<String,Book> hashTable;
	
	@Test
	void setUpScenario1() {
		
		hashTable = new GenericHashTable<String,Book>();
		
		Book book1 = new Book("14",23000,4);
		Book book2 = new Book("96",48796,2);	
		
		hashTable.put(book1.getIsbn(), book1);
		hashTable.put(book2.getIsbn(), book2);
		
	}
	
	void setUpScenario2() {
		
		// hash table vacia 
		hashTable = new GenericHashTable<String,Book>();
		
	}
	
	@Test
	void testHashtable1() {
		setUpScenario2();
		assertTrue(hashTable.isEmpty());
	}
	
	@Test
	void testHashtable2() {
		setUpScenario1();
		assertFalse(hashTable.isEmpty());
	}
	
	@Test
	void testHashtable3() {
		setUpScenario2();
		Book book = new Book("12",1234,2);
		hashTable.put(book.getIsbn(), book);
		assertEquals(book,hashTable.get(book.getIsbn()));
	}
	
	@Test
	void testHashtable4() {
		setUpScenario2();
		
		Book book = new Book("12",1234,2);
		hashTable.put(book.getIsbn(), book);
		hashTable.remove(book.getIsbn());
		assertNull(hashTable.get(book.getIsbn()));
	}
	
	@Test
	void testHashtable5() {
		setUpScenario1();
		Book book = new Book("45",23423,2);
		hashTable.put(book.getIsbn(), book);
		hashTable.get(book.getIsbn());
		
		assertNotNull(hashTable.get(book.getIsbn()));
	}

}
