package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import model.Client;
import model.GenericMinPriorityQueue;

public class MinPriorityQueueTest {

private GenericMinPriorityQueue<Client> queue;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setUpScenario1() {
		queue = new GenericMinPriorityQueue(5);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setUpScenario2() {
		queue = new GenericMinPriorityQueue(5);
		Client c1 = new Client("1", 1);
		Client c2 = new Client("2", 2);
		Client c3 = new Client("3", 3);
		queue.offer(c1);
		queue.offer(c2);
		queue.offer(c3);
	}
	
	@Test
	void testCreation() {
		setUpScenario1();
		assertEquals(true, queue.isEmpty());
	}
	
	@Test
	void testCreation2() {
		setUpScenario2();
		assertEquals(false, queue.isEmpty());
	}
	
	@Test
	void testBuildingHeap() {
		setUpScenario2();
		assertEquals("1", queue.poll().getId());
		assertEquals("2", queue.poll().getId());
		assertEquals("3", queue.poll().getId());
	}
	
	@Test
	void testInsertion() {
		setUpScenario2();
		Client c = new Client("4", 0);
		queue.offer(c);
		assertEquals(c, queue.peek());
	}
	
	@Test
	void testInsertion2() {
		setUpScenario2();
		Client c = new Client("4", 4);
		queue.offer(c);
		queue.poll();
		queue.poll();
		queue.poll();
		assertEquals("4", queue.peek().getId());
	}
	
}
