package paint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Panel extends Canvas{

   private static final long serialVersionUID = 8524587089709471630L;
    
   Dimension d;
   
   public Panel (int width, int height, String title, Game game){
       d = new Dimension(width, height);
       JFrame frame = new JFrame();
       //frame.setPreferredSize(d);
       System.out.println(frame.getPreferredSize());
       
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(false);
       frame.add(game);
       frame.setTitle("Yeet");
       game.setPreferredSize(d);
       frame.pack();
       frame.setFocusable(true);
       frame.requestFocus();
       frame.setVisible(true);
       frame.setLocationRelativeTo(null);
       game.start();
   }
}
