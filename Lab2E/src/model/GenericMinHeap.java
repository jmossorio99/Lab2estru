package model;

public class GenericMinHeap<T extends Comparable<T>> {

	private T[] heap;
	private int size;
	private int maxsize;

	@SuppressWarnings("unchecked")
	public GenericMinHeap(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		heap = (T[]) new Comparable[maxsize];
	}
	
	@SuppressWarnings("unchecked")
	public GenericMinHeap(T[] array, int maxsize) {
		this.size = array.length;
		this.maxsize = maxsize;
		heap = (T[]) new Comparable[maxsize];
		for (int i = 0; i < array.length; i++) {
			heap[i + 1] = array[i];
		}
		buildMinHeap();
	}

	private int parent(int position) {
		return position / 2;
	}

	private int leftChild(int position) {
		return 2 * position;
	}

	private int rightChild(int position) {
		return (2 * position) + 1;
	}

	public int getSize() {
		return size;
	}

	public T get(int pos) {
		return heap[pos];
	}

	private void exchange(int pos1, int pos2) {
		T temp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = temp;
	}

	@SuppressWarnings("unchecked")
	private void minHeapify(int pos) {
		int smallest;
		int left = leftChild(pos);
		int right = rightChild(pos);
		if (left <= size && heap[left].compareTo(heap[pos]) < 0) {
			smallest = left;
		} else {
			smallest = pos;
		}
		if (right <= size && heap[right].compareTo(heap[smallest]) < 0) {
			smallest = right;
		}
		if (smallest != pos) {
			exchange(smallest, pos);
			minHeapify(smallest);
		}
	}

	private void buildMinHeap() {
		for (int i = size / 2; i >= 1; i--) {
			minHeapify(i);
		}
	}

	public void insert(T elem) {
		heap[++size] = elem;
		int current = size;
		if(size == 1) {
			heap[1] = elem;
		}else {
			while (size > 1 && heap[current].compareTo(heap[parent(current)]) < 0) {
				exchange(current, parent(current));
				current = parent(current);
			}
		}
	}

	public void set(int pos, T elem) {
		heap[pos] = elem;
	}

	public T remove(int pos) {
		T removed = heap[pos];
		heap[pos] = heap[size--];
		minHeapify(pos);
		return removed;
	}

}
