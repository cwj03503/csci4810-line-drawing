public class BresenhamLineDrawer extends LineDrawer
{
    public BresenhamLineDrawer(int width, int height)
    {
        super(width, height);
    }

    /**
     * @Override
     * Draws a line with endpoints roughly at (x0,y0) and (y0,y1) using Brensenham's 
     * algorithm and returns an execution time in nanoseconds. This method is heavily
     * based on the implementation provided on the Wikipedia Article for Bresenham's
     * line algorithm. See the Wikipedia implementation 
     * <a href="https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm">here.</a>
     * Note that the execution time includes only the time spent in the critical loop
     * of the algorithm.
     * @param x0  the x-value of the starting point of the line.
     * @param x1  the x-value of the ending point of the line.
     * @param y0  the y-value of the starting point of the line.
     * @param y1  the y-value of the ending point of the line.
     */
    public long drawLine(int x0, int y0, int x1, int y1) {
        
        int deltaX = x1 - x0;
        int deltaY = y1 - y0;

        if (Math.abs(deltaY) < Math.abs(deltaX)) {
            // shallow line
            if (x0 > x1)
            {
                // swap so that the (x0,y0) is to the left of (x1,y1)
                return drawLineShallow(x1, y1, x0, y0);
            }
            else
            {
                return drawLineShallow(x0, y0, x1, y1);
            }
        }
        else
        {
            // steep line
            if (y0 > y1)
            {
                // swap so that the (x0,y0) is to the north of (x1,y1)
                return drawLineSteep(x1, y1, x0, y0);
            }
            else
            {
                return drawLineSteep(x0, y0, x1, y1);
            }
        }
    }

    /**
     * Draws a line with endpoints roughly at (x0,y0) and (y0,y1), given that the slope
     * of the line between these points is between 0 an -1. Returns an execution time 
     * in nanoseconds. Note that the execution time includes only the time spent in the
     * critical loop of the algorithm.
     * @param x0  the x-value of the starting point of the line.
     * @param x1  the x-value of the ending point of the line.
     * @param y0  the y-value of the starting point of the line.
     * @param y1  the y-value of the ending point of the line.
     */
    private long drawLineShallow(int x0, int y0, int x1, int y1)
    {
        // slope is between 0 and -1
        int deltaX = x1 - x0;
        int deltaY = y1 - y0;
        int yi = 1;
        // reverse direction
        if (deltaY < 0) {
            yi = -1;
            deltaY = -deltaY;
        }
        int D = (2 * deltaY) - deltaX;
        int y = y0;

        long startTime = System.nanoTime();
        for (int i = x0; i <= x1; i++)
        {
            buffer.setRGB(i, y, 0xFFFFFF); 
            if (D > 0)
            {
                y = y + yi;
                D = D + (2 * (deltaY - deltaX));
            }
            else
            {
                D = D + (2 * deltaY);
            }
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Draws a line with endpoints roughly at (x0,y0) and (y0,y1), given that the slope
     * of the line between these points is less than -1. Returns an execution time 
     * in nanoseconds. Note that the execution time includes only the time spent in the
     * critical loop of the algorithm.
     * @param x0  the x-value of the starting point of the line.
     * @param x1  the x-value of the ending point of the line.
     * @param y0  the y-value of the starting point of the line.
     * @param y1  the y-value of the ending point of the line.
     */
    private long drawLineSteep(int x0, int y0, int x1, int y1)
    {
        // slope is between 0 and -1
        int deltaX = x1 - x0;
        int deltaY = y1 - y0;
        int xi = 1;
        // reverse direction
        if (deltaX < 0) {
            xi = -1;
            deltaX = -deltaX;
        }
        int D = (2 * deltaX) - deltaY;
        int x = x0;
        long startTime = System.nanoTime();
        for (int i = y0; i <= y1; i++)
        {
            buffer.setRGB(x, i, 0xFFFFFF); 
            if (D > 0)
            {
                x = x + xi;
                D = D + (2 * (deltaX - deltaY));
            }
            else
            {
                D = D + (2 * deltaX);
            }
        }
        return System.nanoTime() - startTime;
    }

}
