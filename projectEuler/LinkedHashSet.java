package projectEuler;


class LinkedHashSet<E extends Comparable> {
	private class LinkedHashEntry<E extends Comparable>{
		LinkedHashEntry<E> prev;
		LinkedHashEntry<E> next;
		E val;

		public LinkedHashEntry(E val){
			prev = null;
			next = null;
			this.val = val;
		}
	}

	private LinkedHashEntry<E>[] set;
	private Function<E, Integer> hash;
	private LinkedHashEntry<E> head;
	private LinkedHashEntry<E> tail;
	private int size;
	private double loadFactor;

	public LinkedHashSet(){
		this.LinkedHashSet(16, 0.75, (E e)->e.hashCode());
	}

	public LinkedHashSet(int initialSize, double loadFactor, Function<E, Integer> hash) {
		this.loadFactor = Math.min(loadFactor, 1.0);
		this.set = new LinkedHashEntry<E>[(int)Math.max(initialSize), 1)];
		this.head = null;
		this.hash = hash;
		this.size = 0;
	}

	private boolean rebalance(){
		if(((double)size) / ((double) set.length) >= loadFactor){
			// we still have head reference
			set = new LinkedHashEntry<E>[set.length * 2];
			
			LinkedHashEntry<E> tracer = head;
			while(head != tracer){
				set[index(tracer)] = tracer;
				tracer = tracer.next;
			}
			
			return true;
		}
		return false;
	}

	public void add(E item){
		int index = index(item);
		if(index != -1) return;
		
		LinkedHashEntry<E> newEntry = new LinkedHashEntry<E>(item);
		tail.next = newEntry;
		newEntry.prev = tail;
		
		set[index] = entry;
		size ++;

		rebalance();
	}

	public boolean contains(E item){ 
		return set[index(item)] != null;
	}

	private int index(E item){
		int index = hash(item) % set.length;
		// TODO use a more sophisticated hashing set up (buckets??)
		while(set[index] != null && !set[index].val.equals(item)) {
			index = (index + 1) % set.length;
		}
		return index;
	}

	public boolean remove(E item){
		int index = index(item);
		if(set[index] == null) return false;

		// remove from list
		LinkedHashEntry<E> removedEntry = set[index];
		removedEntry.prev.next = removedEntry.next;
		removedEntry.next.prev = prev;
		size --;

		// rehash everything to the right
		LinkedHashEntry curr = set[++index];
		while(curr != null){
			set[index] = null;
			set[index(curr.val)]= curr;
			curr = set[++index];
		}

		return true;
	}

	public int size(){
		return size;
	}

	public List<E> toList(){
		List<E> list = new ArrayList<E>();
		LinkedHashEntry<E> tracer = head;
		while(tracer != null){
			list.add(tracer.val);
			tracer = tracer.next;
		}
		return list;
	}
	
}
