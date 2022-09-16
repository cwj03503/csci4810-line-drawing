import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BasicLineDrawer extends JPanel {

    private BufferedImage buffer;
    private int width;
    private int height;
    public BasicLineDrawer(int width, int height)
    {
        this.width = width;
        this.height = height;
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void drawImage()
    {
        ImageIcon icon = new ImageIcon(buffer);
        JLabel iconLabel = new JLabel(icon);
        
        this.add(iconLabel);
        this.revalidate();
        this.repaint();
    }

    public void clearImage()
    {
        this.buffer.flush();
    }

    /*
     * Draws a line with endpoints roughly at (x0,y0) and (y0,y1) and returns 
     * an execution time in nanoseconds. Note that the execution time includes
     * only the time spent in the critical loop of the algorithm.
     * @param x0  the x-value of the starting point of the line.
     * @param x1  the x-value of the ending point of the line.
     * @param y0  the y-value of the starting point of the line.
     * @param y1  the y-value of the ending point of the line.
     */
    public long drawLineBasic(int x0, int y0, int x1, int y1) {

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

        // debugging line information
        System.out.println("--- Line from (" + x0 + "," + y0 + ") to (" + x1 + "," + y1 + "). ---");
        System.out.println("delta x: " + deltaX);
        System.out.println("delta y: " + deltaY);
        System.out.println("slope: " + deltaX/deltaY);

        if (deltaX <= 0.0000001)
        {
            // line is vertical
            int y;
            int x = x0;
            System.out.println("type: vertical");
            startTime = System.nanoTime();
            for (int i = 0; i <= (deltaY - 1); i++)
            {
                y = y0 + i;
                System.out.println("* drawing point at (" + x + "," + y + ").");
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
                System.out.println("type: shallow");
                startTime = System.nanoTime();

                for (int i = 0; i <= (deltaX - 1); i++)
                {
                    x = x0 + i;
                    y = (slope * x) + yIntercept;
                    System.out.println("* drawing point at (" + x + "," + y + ").");
                    buffer.setRGB(x, (int)y, 0xFFFFFF); // truncation occurs with cast
                }
            }
            else
            {
                // line is steep
                int y;
                double x;
                int direction = Integer.signum((int)deltaY);
                System.out.println("type: steep");
                startTime = System.nanoTime();

                for (int i = 0; i <= (Math.abs(deltaY) - 1); i++)
                {
                    y = y0 + (i * direction);
                    x = (y - yIntercept)/slope;
                    System.out.println("* drawing point at (" + x + "," + y + ").");
                    buffer.setRGB((int)x, y, 0xFFFFFF); // truncation occurs with cast
                }
            } 
        }
        
        System.out.println("--------------------------------------------");
        return System.nanoTime() - startTime;
    }

}


