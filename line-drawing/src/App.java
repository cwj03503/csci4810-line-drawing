import javax.swing.*;

import java.awt.Dimension;
import java.lang.Math;

public class App {
    static final int FRAME_WIDTH = 800;
    static final int FRAME_HEIGHT = 800;
    public static void main(String[] args) throws Exception {

        long totalRunTime = 0;

        // initialize frame
        JFrame frame = new JFrame();
        frame.setTitle("Line Drawer");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // initialize panel
        BasicLineDrawer panel = new BasicLineDrawer(FRAME_WIDTH, FRAME_HEIGHT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        // draw random lines
        for (int i = 0; i < 10000; i++)
        {
            int x0 = (int)(Math.random() * FRAME_WIDTH);
            int x1 = (int)(Math.random() * FRAME_WIDTH);
            int y0 = (int)(Math.random() * FRAME_HEIGHT);
            int y1 = (int)(Math.random() * FRAME_HEIGHT);

            long start = System.currentTimeMillis();
            panel.drawLineBasic(x0, y0, x1, y1);
            long end = System.currentTimeMillis();
            totalRunTime += (end - start);

            panel.drawImage();
            System.out.println("line drawn from (" 
            + x0 + "," + y0 + ") to (" + x1 + "," + y1 + ").");
        }
        
        System.out.println("Total time: " + totalRunTime + " ms.");
        frame.add(panel);
        frame.setVisible(true);
    }
}
