import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BasicLineDrawer extends JPanel {

    private BufferedImage buffer;

    public BasicLineDrawer(int width, int height)
    {
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    /*
     * Draws a line with endpoints roughly at (x0,y0) and (y0,y1).
     * This method is heavily based on the C++ DDA Algorithm hosted on the
     * Wikipedia Page for the DDA Algorithm.
     * (See https://en.wikipedia.org/wiki/Digital_differential_analyzer_(graphics_algorithm)).
     * @param x0  the x-value of the starting point of the line.
     * @param x1  the x-value of the ending point of the line.
     * @param y0  the y-value of the starting point of the line.
     * @param y1  the y-value of the ending point of the line.
     */
    public void drawLineBasic(int x0, int y0, int x1, int y1) {
        double deltaX = x1 - x0;
        double deltaY = y1 - y0;
        double x, y;
        double step;
        if (Math.abs(deltaX) >= Math.abs(deltaY))
        {
            step = Math.abs(deltaX);
        }
        else
        {
            step = Math.abs(deltaY);
        }
        deltaX = deltaX / step;
        deltaY = deltaY / step;
        x = x0;
        y = y0;
        int i = 1;

        // critical loop
        while (i <= (int)step)
        {
            buffer.setRGB((int)x, (int)y, 0xFFFFFF);
            x = (x + deltaX);
            y = (y + deltaY);
            i++;
        }

    }

    public void drawImage()
    {
        ImageIcon icon = new ImageIcon(buffer);
        JLabel iconLabel = new JLabel(icon);
        
        this.add(iconLabel);
        this.revalidate();
        this.repaint();
    }

}


