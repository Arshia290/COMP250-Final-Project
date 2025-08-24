package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class PathFindingService {
	Tile source;
	Graph g;
	
	public PathFindingService(Tile start) {
    	this.source = start;
    }

	public abstract void generateGraph();
    
    //TODO level 4: Implement basic dijkstra's algorithm to find a path to the final unknown destination
    public ArrayList<Tile> findPath(Tile startNode) {
        ArrayList<Tile> allTiles = new ArrayList<>();
        for(Tile tile : g.vertices){
            tile.predecessor = null;
            tile.costEstimate = Double.MAX_VALUE;
            if(tile.equals(startNode)){
                tile.costEstimate = 0;
            }
            allTiles.add(tile);
        }
        TilePriorityQ queue = new TilePriorityQ(allTiles);
        ArrayList<Tile> visitedVertices = new ArrayList<>();

        Tile current = null;
        while(!queue.queues.isEmpty()) {
            current = queue.removeMin();
            visitedVertices.add(current);

            if(current.isDestination){
                break;
            }

            ArrayList<Tile> neighbors = g.getNeighbors(current);
            for(Tile neighbor : neighbors){
                if(visitedVertices.contains(neighbor)){
                    continue;
                }
                ArrayList<Tile> tmp = new ArrayList<>();
                tmp.add(current);
                tmp.add(neighbor);
                double edgeCost = g.computePathCost(tmp);
                if(current.costEstimate + edgeCost < neighbor.costEstimate){
                    queue.updateKeys(neighbor, current, current.costEstimate + edgeCost);
                }
            }
        }
        ArrayList<Tile> path = new ArrayList<Tile>();
        while(current != null){
            path.addFirst(current);
            current = current.predecessor;
        }
        return path;
    }
    
    //TODO level 5: Implement basic dijkstra's algorithm to path find to a known destination
    public ArrayList<Tile> findPath(Tile start, Tile end) {
        ArrayList<Tile> allTiles = new ArrayList<>();
        for(Tile tile : g.vertices){
            tile.predecessor = null;
            tile.costEstimate = Double.MAX_VALUE;
            if(tile.equals(start)){
                tile.costEstimate = 0;
            }
            allTiles.add(tile);
        }
        TilePriorityQ queue = new TilePriorityQ(allTiles);
        ArrayList<Tile> visitedVertices = new ArrayList<>();

        Tile current = null;
        while(!queue.queues.isEmpty()) {
            current = queue.removeMin();
            visitedVertices.add(current);

            if(current.equals(end)){
                break;
            }

            ArrayList<Tile> neighbors = g.getNeighbors(current);
            for(Tile neighbor : neighbors){
                if(visitedVertices.contains(neighbor)){
                    continue;
                }
                ArrayList<Tile> tmp = new ArrayList<>();
                tmp.add(current);
                tmp.add(neighbor);
                double edgeCost = g.computePathCost(tmp);
                if(current.costEstimate + edgeCost < neighbor.costEstimate){
                    queue.updateKeys(neighbor, current, current.costEstimate + edgeCost);
                }
            }
        }
        ArrayList<Tile> path = new ArrayList<Tile>();
        while(current != null){
            path.addFirst(current);
            current = current.predecessor;
        }
        return path;
    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to the final destination passing through given waypoints
    public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints){
        ArrayList<Tile> path = new ArrayList<>();
        path.add(start);
        Tile current = start;
    	for(Tile waypoint : waypoints){
            ArrayList<Tile> tmp = findPath(current, waypoint);
            tmp.removeFirst();
            path.addAll(tmp);
            current = waypoint;
        }
        ArrayList<Tile> tmp2 = findPath(path.getLast());
        tmp2.removeFirst();
        path.addAll(tmp2);
        return path;
    }
        
}

