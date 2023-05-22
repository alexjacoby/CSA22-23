package mazesolver;

import java.awt.*;
import java.util.Scanner;

/**
 * 2D rectangular maze of rooms that can be solved automatically.
 * <p>
 * Extensions: Find shortest path, load maze from file, let user
 *  interactively solve maze, ???
 *
 * @author AP CS A (May '23)
 */
public class Maze {
    private Room[][] maze;

    /**
     * Create new maze with given dimensions and wall fill percentage.
     */
    public Maze(int height, int width, double fill) {
        maze = new Room[height][width];
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                if (Math.random() < fill) {
                    maze[row][col] = Room.WALL;
                } else {
                    maze[row][col] = Room.EMPTY;
                }
            }
        }
        // Set top left to start
        maze[0][0] = Room.START;
        // Set bottom right to exit
        maze[height-1][width-1] = Room.EXIT;
    }

    public int width() { return maze[0].length; }
    public int height() { return maze.length; }

    /** Draws maze, highlighting "current" room at given row and col. */
    public void draw(int currRow, int currCol) {
        StdDraw.enableDoubleBuffering();
        double hMargin = 0.02 * width(); // horizontal margin: 2%
        double vMargin = 0.02 * height(); // vertical margin: 2%
        StdDraw.setXscale(-hMargin, width() + hMargin);
        StdDraw.setYscale(height() + vMargin, -vMargin);
        StdDraw.clear();
        for (int r = 0; r < height(); r++) {
            for (int c = 0; c < width(); c++) {
                // New style switch statement
                Color color = switch (maze[r][c]) {
                    case EMPTY -> Color.YELLOW;
                    case WALL -> Color.BLACK;
                    case CRUMB -> Color.BLUE;
                    default -> Color.RED;
                };
                double x = c + 0.5;
                double y = r + 0.5;
                StdDraw.setPenColor(color);
                StdDraw.filledSquare(x, y, 0.5);
                // Highlight if current room
                if (r == currRow && c == currCol) {
                    StdDraw.setPenColor(Color.GREEN);
                    StdDraw.filledSquare(x, y, 0.5);
                }
            }
        }
        StdDraw.show();
        StdDraw.pause(30);
    }

    /**
     * Can we solve the maze well passing through this room?
     * Draws maze to StdDraw while trying to solve it in order
     * to help visualize the recursion.
     */
    public boolean isSolvableFrom(int r, int c) {
        // Base cases
        if (r < 0 || c < 0 || r >= height() || c >= width()) {
            return false;
        }
        Room room = maze[r][c];
        draw(r, c);
        if (room == Room.WALL || room == Room.CRUMB) {
            return false;
        }
        if (room == Room.EXIT) {
            return true;
        }

        // Drop crumb so we know we've been here!
        maze[r][c] = Room.CRUMB;

        // Recursive step: check neighboring rooms: up, right, down, left
        if (isSolvableFrom(r-1, c) || // up
            isSolvableFrom(r, c+1) || // right
            isSolvableFrom(r+1, c) || // down
            isSolvableFrom(r, c-1))   // left
        {
            return true;
        } else {
            return false;
        }
    }

    /** Creates new maze and checks if it's solvable. */
    public static void main(String[] args) {
        Maze m = new Maze(10, 10, 0.3);
        m.draw(0, 0);
        System.out.println("Hit return to check if solvable!");
        // Wait for user to hit return
        (new Scanner(System.in)).nextLine();
        System.out.println("Solvable from (0,0)? " + m.isSolvableFrom(0, 0));
        m.draw(0, 0);
    }
}
