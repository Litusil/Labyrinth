package mk;

import mk.generators.DepthFirst;

import java.io.IOException;

public class Main {

    public static void main ( String[] args ) throws IOException
    {
        Maze test = new Maze(new DepthFirst(),2000,2000);

        ImageCreator image = new ImageCreator( test );
        image.createPicture();;
    }
}
