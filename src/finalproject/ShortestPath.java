package finalproject;


import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.MetroTile;

public class ShortestPath extends PathFindingService {
    //TODO level 4: find distance prioritized path
    public ShortestPath(Tile start) {
        super(start);
        generateGraph();
    }

	@Override
	public void generateGraph() {
        // TODO Auto-generated method stub
        super.g = new Graph(GraphTraversal.BFS(source));

        for (Tile tile : super.g.vertices) {
            for (Tile neighbor : tile.adjacentTiles) {
                if(neighbor.isWalkable()){
                    double cost;
                    if(tile instanceof MetroTile && neighbor instanceof MetroTile){
                        MetroTile tmp = (MetroTile) tile;
                        tmp.fixMetro(neighbor);
                        cost = tmp.metroDistanceCost;
                    }else{
                        cost = neighbor.distanceCost;
                    }
                    super.g.addEdge(tile, neighbor, cost);
                }
            }
        }
    }
}