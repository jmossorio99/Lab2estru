package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Client;
import model.GenericMinHeap;

public class MinHeapTest {

	private GenericMinHeap<Client> heap;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setUpScenario1() {
		heap = new GenericMinHeap(5);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setUpScenario2() {
		heap = new GenericMinHeap(5);
		Client c1 = new Client("1", 5);
		Client c2 = new Client("2", 4);
		Client c3 = new Client("3", 6);
		heap.insert(c1);
		heap.insert(c2);
		heap.insert(c3);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setUpScenario3() {
		Client c1 = new Client("1", 5);
		Client c2 = new Client("2", 4);
		Client c3 = new Client("3", 6);
		Client[] cArray = {c1, c2, c3};
		heap = new GenericMinHeap(cArray, 5);
	}
	
	@Test
	void testCreation() {
		setUpScenario1();
		assertEquals(0, heap.getSize());
	}
	
	@Test
	void testCreation2() {
		setUpScenario2();
		assertEquals(3, heap.getSize());
	}
	
	@Test
	void testBuildingHeap() {
		setUpScenario3();
		assertEquals("2", heap.get(1).getId());
		assertEquals("1", heap.get(2).getId());
		assertEquals("3", heap.get(3).getId());
	}
	
	@Test
	void testCreationAndInsertionAndSwapping() {
		setUpScenario3();
		assertEquals(3, heap.getSize());
	}
	
	@Test
	void testInsertion() {
		setUpScenario3();
		Client c = new Client("4", 1);
		heap.insert(c);
		assertEquals(c, heap.get(1));
	}
	
	@Test
	void testInsertion2() {
		setUpScenario3();
		Client c = new Client("4", 8);
		heap.insert(c);
		assertNotNull(heap.get(4));
		assertEquals(c, heap.get(4));
	}
	
	@Test
	void testRemoval() {
		setUpScenario3();
		Client c = new Client("4", 8);
		heap.insert(c);
		assertEquals(c, heap.remove(4));
	}
	
	@Test
	void testRemoval2() {
		setUpScenario3();
		Client c = new Client("4", 1);
		heap.insert(c);
		assertEquals(c, heap.remove(1));
		assertEquals("2", heap.get(1).getId());
	}
	
}
