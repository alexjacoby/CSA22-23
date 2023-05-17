package mazesolver;

import java.awt.*;

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

    public void draw() {
        StdDraw.setXscale(-0.1, width() + 0.1);
        StdDraw.setYscale(-0.1, height() + 0.1);
        StdDraw.clear();
        for (int r = 0; r < height(); r++) {
            for (int c = 0; c < width(); c++) {
                Color color;
                switch (maze[r][c]) {
                    case EMPTY: color = Color.YELLOW; break;
                    case WALL: color = Color.BLACK; break;
                    default: color = Color.RED;
                }
                double x = c + 0.5;
                double y = r + 0.5;
                StdDraw.setPenColor(color);
                StdDraw.filledSquare(x, y, 0.5);
            }
        }
    }

    public static void main(String[] args) {
        Maze m = new Maze(10, 10, 0.3);
        m.draw();
    }
}
