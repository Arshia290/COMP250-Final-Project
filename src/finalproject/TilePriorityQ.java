package finalproject;

import java.util.ArrayList;
import java.util.Arrays;


import finalproject.system.Tile;

public class TilePriorityQ {
	// TODO level 3: Add fields that can help you implement this data type
	public ArrayList<Tile> queues;
	public int size;

	// TODO level 3: implement the constructor for the priority queue
	public TilePriorityQ (ArrayList<Tile> vertices) {
		this.queues = new ArrayList<>();
		this.size = 0;

		for(Tile v : vertices){
			queues.add(v);
			size++;
		}

		for (int i = size / 2 - 1; i >= 0; i--) {
			downHeap(i, size);
		}
	}

	// TODO level 3: implement remove min as seen in class
	public Tile removeMin() {
		Tile tmp = queues.get(0);
		queues.set(0, queues.get(size-1));
		queues.remove(size-1);
		size = size - 1;
		downHeap(0, size);
		return tmp;
	}

	// TODO level 3: implement updateKeys as described in the pdf
	public void updateKeys(Tile t, Tile newPred, double newEstimate) {
			for(Tile q : queues){
				if(q.equals(t)){
					q.predecessor = newPred;
					q.costEstimate = newEstimate;
					}
				}
			for (int i = size / 2 - 1; i >= 0; i--) {
				downHeap(i, size);
			}
	}

	public void downHeap(int start, int max) {
		int i = start;
		int leftChild = 2 * i + 1;

		while (leftChild < max) {
			int rightChild = leftChild + 1;
			int swap = i;

			if (queues.get(leftChild).costEstimate < queues.get(swap).costEstimate) {
				swap = leftChild;
			}

			if (rightChild < max && queues.get(rightChild).costEstimate < queues.get(swap).costEstimate) {
				swap = rightChild;
			}

			if (swap == i) {
				break;
			}

			Tile tmp = queues.get(swap);
			queues.set(swap, queues.get(i));
			queues.set(i, tmp);

			i = swap;
			leftChild = 2 * i + 1;
		}
	}
}
