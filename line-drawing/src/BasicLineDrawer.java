public class BasicLineDrawer extends LineDrawer {

    public BasicLineDrawer(int width, int height)
    {
        super(width,height);
    }

    /*
     * @Override
     * Draws a line with endpoints roughly at (x0,y0) and (y0,y1) using the naive 
     * approach and returns an execution time in nanoseconds. Note that the execution
     * time includes only the time spent in the critical loop of the algorithm.
     * @param x0  the x-value of the starting point of the line.
     * @param x1  the x-value of the ending point of the line.
     * @param y0  the y-value of the starting point of the line.
     * @param y1  the y-value of the ending point of the line.
     */
    public long drawLine(int x0, int y0, int x1, int y1) 
    {

        // if end point is to the left of start point, reverse them
        if (x1 < x0)
        {
            int temp = x0;
            x0 = x1;
            x1 = temp;

            temp = y0;
            y0 = y1;
            y1 = temp;
        }

        double deltaX = (double)(x1 - x0);
        double deltaY = (double)(y1 - y0);
        long startTime;

        if (deltaX <= 0.0000001)
        {
            // line is vertical
            int y;
            int x = x0;
            startTime = System.nanoTime();
            for (int i = 0; i <= (deltaY - 1); i++)
            {
                y = y0 + i;
                buffer.setRGB(x, y, 0xFFFFFF);
            }
        }
        else
        {
            double slope = deltaY/deltaX;
            double yIntercept = y1 - slope * x1;

            if (Math.abs(deltaX) > Math.abs(deltaY)) 
            {
                // line is shallow
                int x;
                double y;
                startTime = System.nanoTime();

                for (int i = 0; i <= (deltaX - 1); i++)
                {
                    x = x0 + i;
                    y = (slope * x) + yIntercept;
                    buffer.setRGB(x, (int)y, 0xFFFFFF); // truncation occurs with cast
                }
            }
            else
            {
                // line is steep
                int y;
                double x;
                int direction = Integer.signum((int)deltaY);
                startTime = System.nanoTime();

                for (int i = 0; i <= (Math.abs(deltaY) - 1); i++)
                {
                    y = y0 + (i * direction);
                    x = (y - yIntercept)/slope;
                    buffer.setRGB((int)x, y, 0xFFFFFF); // truncation occurs with cast
                }
            } 
        }
        return System.nanoTime() - startTime;
    }

}
