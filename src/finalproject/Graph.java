package finalproject;

import java.util.ArrayList;
import java.util.HashSet;

import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.*;

public class Graph {
	// TODO level 2: Add fields that can help you implement this data type
    public ArrayList<Tile> vertices;
    public ArrayList<Edge> edges;

    // TODO level 2: initialize and assign all variables inside the constructor
	public Graph(ArrayList<Tile> vertices) {
		this.vertices =  vertices;
        this.edges = new ArrayList<>();

	}
	
    // TODO level 2: add an edge to the graph
    public void addEdge(Tile origin, Tile destination, double weight){
    	edges.add(new Edge(origin, destination, weight));
    }
    
    // TODO level 2: return a list of all edges in the graph
	public ArrayList<Edge> getAllEdges() {
		return edges;
	}
  
	// TODO level 2: return list of tiles adjacent to t
	public ArrayList<Tile> getNeighbors(Tile t) {
    	ArrayList<Tile> neighbors = new ArrayList<>();
        for(Edge w: edges){
            if(w.getStart().getNodeID() == t.getNodeID()){
                neighbors.add(w.getEnd());
            }
        }
        return neighbors;
    }
	
	// TODO level 2: return total cost for the input path
	public double computePathCost(ArrayList<Tile> path) {
        double cost = 0;

        for(int i = 0; i < path.size() - 1; i++){
            Tile current = path.get(i);
            Tile next = path.get(i + 1);
            for(Edge e : getAllEdges()){
                if(e.getStart().getNodeID() == current.getNodeID() && e.getEnd().getNodeID() == next.getNodeID()){
                    cost += e.weight;
                }
            }
        }
        return cost;
	}
	
   
    public static class Edge{
    	public Tile origin;
    	public Tile destination;
    	public double weight;

        // TODO level 2: initialize appropriate fields
        public Edge(Tile s, Tile d, double cost){
        	this.origin = s;
            this.destination = d;
            this.weight = cost;
        }
        
        // TODO level 2: getter function 1
        public Tile getStart(){
            return origin;
        }

        
        // TODO level 2: getter function 2
        public Tile getEnd() {
            return destination;
        }
        
    }
    
}