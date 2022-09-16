import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class LineDrawer extends JPanel {

    protected BufferedImage buffer;
    protected int width;
    protected int height;

    /**
     * Constructs a new line drawer instance, with a buffered image of the given
     * width and height.
     * @param width  the width of the BufferedImage
     * @param height  the height of the BufferedImage
     */
    public LineDrawer(int width, int height)
    {
        this.width = width;
        this.height = height;
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Paints this JPanel with the buffer.
     */
    public void drawImage()
    {
        ImageIcon icon = new ImageIcon(buffer);
        JLabel iconLabel = new JLabel(icon);
        
        this.add(iconLabel);
        this.revalidate();
        this.repaint();
    }

    /**
     * Empties the buffer.
     */
    public void clearImage()
    {
        this.buffer.flush();
    }

    /*
     * Draws a line with endpoints at (x0,y0) and (y0,y1) and returns an execution 
     * time in nanoseconds. Note that the execution time includes only the time
     * spent in the critical loop of the algorithm.
     * @param x0  the x-value of the starting point of the line.
     * @param x1  the x-value of the ending point of the line.
     * @param y0  the y-value of the starting point of the line.
     * @param y1  the y-value of the ending point of the line.
     */
    abstract public long drawLine(int x0, int y0, int x1, int y1);
    
}
