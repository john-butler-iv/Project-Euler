package projectEuler;

import java.util.Iterator;

public class CustomLinkedList<E> {
	public class CustomLinkedNode {
		public E value;
		public CustomLinkedNode prev;
		public CustomLinkedNode next;

		public CustomLinkedNode(E value) {
			this.value = value;
			this.prev = null;
			this.next = null;
		}

		public CustomLinkedNode(E value, CustomLinkedNode prev, CustomLinkedNode next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}

	private CustomLinkedNode head;
	private int size;

	public CustomLinkedList() {
		this.head = new CustomLinkedNode(null);
		this.head.next = head;
		this.head.prev = head;
		this.size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator<CustomLinkedNode> nodeIterator() {
		return new Iterator<CustomLinkedNode>() {
			private CustomLinkedNode tracer = head;

			@Override
			public boolean hasNext() {
				return tracer.next != head;
			}

			@Override
			public CustomLinkedNode next() {
				tracer = tracer.next;
				return tracer;
			}
		};
	}

	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private Iterator<CustomLinkedNode> nodeIt = nodeIterator();

			@Override
			public boolean hasNext() {
				return nodeIt.hasNext();
			}

			@Override
			public E next() {
				return nodeIt.next().value;
			}
		};
	}

	public E[] toArray() {
		Object[] array = new Object[size];
		Iterator<E> it = iterator();
		int currIndex = 0;
		while (it.hasNext()) {
			array[currIndex++] = it.next();
		}
		return (E[]) array;
	}

	public void addBack(E e) {
		addBefore(head, e);
	}

	public void addFront(E e) {
		addAfter(head, e);
	}

	public void removeNode(CustomLinkedNode node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		size--;
	}

	public void clear() {
		this.head.next = null;
		this.head.prev = null;
		size = 0;
	}

	public void addBefore(CustomLinkedNode node, E e) {
		CustomLinkedNode newNode = new CustomLinkedNode(e, node.prev, node);
		newNode.prev.next = newNode;
		newNode.next.prev = newNode;

		size++;
	}

	public void addAfter(CustomLinkedNode node, E e) {
		CustomLinkedNode newNode = new CustomLinkedNode(e, node, node.next);
		newNode.next.prev = newNode;
		newNode.prev.next = newNode;

		size++;
	}

	public void remove(CustomLinkedNode node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		size--;
	}
}