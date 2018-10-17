package mk.generators;

import java.util.Deque;
import java.util.LinkedList;

import mk.Cell;
import mk.Maze;

public class DepthFirst implements MazeGenerator
{
    @Override
    public void generate( Maze maze){
        //Eingang
        maze.getStart().setNorthWall( false );
        //Ausgang
        maze.getEnd().setSouthWall( false );

        Deque<Cell> visited = new LinkedList<>(  );

        //Startpunkt
        Cell visit =   maze.getMaze()[0][0];
        Cell next;

        while(true)
        {
            if(visit.getNeighbours().size() == 0)
            {
                if (visited.size() == 0)
                {
                    break;
                }
                visit.setVisited( true );
                visit = visited.pop();
                continue;
            }

            Object[] keys = visit.getNeighbours().keySet().toArray();
            int key = (int) keys[ maze.getRandom().nextInt(keys.length)];
            next = visit.getNeighbours().get(key);

            if(next.isVisited()){
                visit.getNeighbours().remove( key );
                continue;
            }

            if( key == 1 )
            {
                visit.setNorthWall( false );
                visit.getNeighbours().remove(1);
                next.setSouthWall( false );
                next.getNeighbours().remove(3);

            }
            else if( key == 2 )
            {
                visit.setEastWall( false );
                next.getNeighbours().remove(2);
                next.setWestWall( false );
                next.getNeighbours().remove(4);
            }
            else if( key == 3 )
            {
                visit.setSouthWall( false );
                visit.getNeighbours().remove(3);
                next.setNorthWall( false );
                next.getNeighbours().remove(1);
            }
            else if( key == 4 )
            {
                visit.setWestWall( false );
                visit.getNeighbours().remove(4);
                next.setEastWall( false );
                next.getNeighbours().remove(2);
            }
            visited.addFirst(visit);
            visit.setVisited( true );
            visit = next;
        }
    }
}
