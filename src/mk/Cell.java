package mk;

import java.util.HashMap;
import java.util.Map;

public class Cell
{
    Map<Integer, Cell> neighbours = new HashMap<>( );

    private boolean northWall = true;
    private boolean eastWall = true;
    private boolean southWall = true;
    private boolean westWall = true;

    private boolean visited = false;

    public Map<Integer, Cell> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Map<Integer, Cell> neighbours) {
        this.neighbours = neighbours;
    }

    public boolean isNorthWall() {
        return northWall;
    }

    public void setNorthWall(boolean northWall) {
        this.northWall = northWall;
    }

    public boolean isEastWall() {
        return eastWall;
    }

    public void setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
    }

    public boolean isSouthWall() {
        return southWall;
    }

    public void setSouthWall(boolean southWall) {
        this.southWall = southWall;
    }

    public boolean isWestWall() {
        return westWall;
    }

    public void setWestWall(boolean westWall) {
        this.westWall = westWall;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
