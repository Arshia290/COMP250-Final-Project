package finalproject;

import finalproject.system.Tile;
import finalproject.tiles.MetroTile;

public class FastestPath extends PathFindingService {
    //TODO level 6: find time prioritized path
    public FastestPath(Tile start) {
        super(start);
        generateGraph();
    }

	@Override
	public void generateGraph() {
		// TODO Auto-generated method stub
        super.g = new Graph(GraphTraversal.BFS(source));

        for (Tile tile : super.g.vertices) {
            for (Tile neighbor : tile.adjacentTiles) {
                if (neighbor.isWalkable()){
                    double cost;
                    if(tile instanceof MetroTile && neighbor instanceof MetroTile){
                        MetroTile tmp = (MetroTile) tile;
                        tmp.fixMetro(neighbor);
                        cost = tmp.metroTimeCost;
                    }else{
                        cost = neighbor.timeCost;
                    }
                    super.g.addEdge(tile, neighbor, cost);
                }
            }
        }
	}
}
