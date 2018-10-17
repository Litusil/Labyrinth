package mk;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class ImageCreator
{

    private static final String PATH = "C:\\Users\\ma721kup\\Desktop\\Maze\\";
    private BufferedImage img;
    private Maze maze;
    private int pictureXSize;
    private int pictureYSize;

    public ImageCreator( Maze maze) {
        this.maze = maze;
        pictureXSize = ( this.maze.getSizeX() * this.maze.getCELLSIZE() ) - ( ( this.maze.getSizeX() - 1 * 1 ) );
        pictureYSize = this.maze.getSizeY() * this.maze.getCELLSIZE() - ( ( this.maze.getSizeY() - 1 * 1 ) );
        this.img = new BufferedImage( pictureXSize, pictureYSize, BufferedImage.TYPE_INT_RGB );
    }


    public void createPicture( ) throws IOException
    {
        int black = ( 0 << 16 ) | ( 0 << 8 );
        int white = ( 255 << 16 ) | ( 255 << 8 ) | 255;
        int red = ( 255 << 16 ) | ( 0 << 8 );

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        File f = new File( PATH+ "Maze_"+ timeStamp + ".png" );

        //Jede Zelle bekommt in der Regel 3x3 Pixel
        for( int y = 0; y < maze.getSizeY(); y++ )
        {
            for( int x = 0; x < maze.getSizeX(); x++ )
            {
                int cellCenterX = ( 1 + ( ( maze.getCELLSIZE() - 1 ) * x ) );
                int cellCenterY = ( 1 + ( ( maze.getCELLSIZE() - 1 ) * y ) );

                img.setRGB( cellCenterX, cellCenterY, white );

                //Wand im Norden
                if(maze.getMaze()[ y ][ x ].isNorthWall() )
                {
                    img.setRGB( cellCenterX - 1, cellCenterY - 1, black );
                    img.setRGB( cellCenterX, cellCenterY - 1, black );
                    img.setRGB( cellCenterX + 1, cellCenterY - 1, black );
                }
                else
                {
                    img.setRGB( cellCenterX, cellCenterY - 1, white );
                }

                //Wand im Osten
                if(  maze.getMaze()[ y ][ x ].isEastWall() )
                {
                    img.setRGB( cellCenterX + 1, cellCenterY - 1, black );
                    img.setRGB( cellCenterX + 1, cellCenterY, black );
                    img.setRGB( cellCenterX + 1, cellCenterY + 1, black );
                }
                else
                {
                    img.setRGB( cellCenterX  +1 , cellCenterY, white );
                }

                //Wand im Süden
                if(  maze.getMaze()[ y ][ x ].isSouthWall() )
                {
                    img.setRGB( cellCenterX - 1, cellCenterY + 1, black );
                    img.setRGB( cellCenterX, cellCenterY + 1, black );
                    img.setRGB( cellCenterX + 1, cellCenterY + 1, black );
                }
                else
                {
                    img.setRGB( cellCenterX, cellCenterY + 1, white );
                }
                //Wand im Westen
                if(  maze.getMaze()[ y ][ x ].isWestWall() )
                {
                    img.setRGB( cellCenterX - 1, cellCenterY - 1, black );
                    img.setRGB( cellCenterX - 1, cellCenterY, black );
                    img.setRGB( cellCenterX - 1, cellCenterY + 1, black );
                }
                else
                {
                    img.setRGB( cellCenterX - 1, cellCenterY, white );
                }

                //Ende Rot markieren
                if(maze.getMaze()[ y ][ x ] == maze.getEnd() ){
                    img.setRGB(cellCenterX,cellCenterY + 1,red);
                }

                //Start Rot markieren
                if(maze.getMaze()[ y ][ x ] == maze.getStart() ){
                    img.setRGB(cellCenterX,cellCenterY -1 ,red);
                }
            }
        }

        //write image
        try
        {
            ImageIO.write( img, "png", f );
        }
        catch( IOException e )
        {
            System.out.println( "Error: " + e );
        }
    }
}
