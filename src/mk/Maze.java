package mk;

import java.util.Random;

import mk.generators.MazeGenerator;

public class Maze
{
    private int sizeX;

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getCELLSIZE() {
        return CELLSIZE;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    private int sizeY;
    private Cell[][] maze;
    private MazeGenerator generator;
    private final int CELLSIZE = 3;
    private Random random;
    private Cell Start;
    private Cell End;

    public Maze(MazeGenerator generator, int sizeX, int sizeY, long seed)
    {
        this.generator = generator;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.random = new Random( seed );
        this.maze = new Cell[sizeY][sizeX];
        this.generateBlankMaze();
        this.generateStartEnd();
        this.generator.generate( this);
    }

    public Maze(MazeGenerator generator, int sizeX, int sizeY)
    {
        this.generator = generator;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.random = new Random();
        this.maze = new Cell[sizeY][sizeX];
        this.generateBlankMaze();
        this.generateStartEnd();
        this.generator.generate( this);
    }

    private void generateBlankMaze()
    {
        for(int y = 0; y < sizeY; y++ ){
            for( int x = 0; x < sizeX; x++){

                //Neue Zelle erstellen
                this.maze[y][x] = new Cell();

                //Gibt es im Norden eine angrenzende Zelle ?
                if(y-1 >= 0) {
                    if(this.maze[y-1][x] != null) {
                        this.maze[y][x].neighbours.put( 1,this.maze[y-1][x] );
                        this.maze[y-1][x].neighbours.put(3, this.maze[y][x] );
                    }
                }
                //Gibt es im Osten eine angrenzende Zelle ?
                if(x+1 < sizeX) {
                    if(this.maze[y][x+1] != null) {
                        this.maze[y][x].neighbours.put( 2,this.maze[y][x+1] );
                        this.maze[y][x+1].neighbours.put(4, this.maze[y][x] );
                    }
                }
                //Gibt es im Süden eine angrenzende Zelle ?
                if(y+1 < sizeY) {
                    if(this.maze[y+1][x] != null) {
                        this.maze[y][x].neighbours.put( 3,this.maze[y+1][x] );
                        this.maze[y+1][x].neighbours.put(1, this.maze[y][x] );
                    }
                }
                //Gibt es im Westen eine angrenzende Zelle ?
                if(x-1 >= 0) {
                    if(this.maze[y][x-1] != null) {
                        this.maze[y][x].neighbours.put( 4,this.maze[y][x-1] );
                        this.maze[y][x-1].neighbours.put(2, this.maze[y][x] );
                    }
                }
            }
        }
    }

    private void generateStartEnd(){
        this.Start = this.maze[0][random.nextInt( sizeX )];
        this.End = this.maze[sizeY-1][random.nextInt( sizeX )];
    }

    public Cell getStart() {
        return Start;
    }

    public void setStart(Cell start) {
        Start = start;
    }

    public Cell getEnd() {
        return End;
    }

    public void setEnd(Cell end) {
        End = end;
    }

    public Cell[][] getMaze() {

        return maze;
    }

    public void setMaze(Cell[][] maze) {
        this.maze = maze;
    }
}
