package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashSet;

public class GraphTraversal
{


	//TODO level 1: implement BFS traversal starting from s
	public static ArrayList<Tile> BFS(Tile s) {
		Tile first = s;
		ArrayList<Tile> visited = new ArrayList<>();
		HashSet<Tile> v = new HashSet<>();
		ArrayList<Tile> queue = new ArrayList<>();

		visited.add(first);
		v.add(first);
		queue.add(first);

		while (!queue.isEmpty()) {
			first = queue.remove(0);

			for (Tile w : first.adjacentTiles) {
				if (w.isWalkable() && !v.contains(w)) {
					visited.add(w);
					v.add(w);
					queue.add(w);
				}
			}
		}
		return visited;
	}


	//TODO level 1: implement DFS traversal starting from s
	public static ArrayList<Tile> DFS(Tile s) {
		ArrayList<Tile> visited = new ArrayList<>();
		ArrayList<Tile> queue = new ArrayList<>();
		HashSet<Tile> v = new HashSet<>();

		queue.add(s);
		while(!queue.isEmpty()){
			Tile current = queue.get(queue.size() -1);
			queue.remove(queue.size() - 1);
			visited.add(current);
			v.add(current);

			for(Tile adj : current.adjacentTiles){
				if(adj.isWalkable() && !v.contains(adj)) {
					queue.add(adj);
				}
			}
		}
		return visited;
	}

}  