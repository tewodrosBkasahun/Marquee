import javax.swing.*;

import cmscMarqueeLib.MarqueeController;
import cmscMarqueeLib.MarqueeDisplay;
/**
 * @author Tewodros Kasahun
 */
public class Marquee {
    public static void main(String[] args) {
        int animationDelayMilliSecs = 50, animationPattern = 0;
        String message = JOptionPane.showInputDialog("Enter message:");
        MarqueeDisplay display = new MarqueeDisplay();
        MarqueeDataManager dataManager = new MarqueeDataManager(message,animationPattern);
        MarqueeController controller = new MarqueeController(display, dataManager,
              							                 animationDelayMilliSecs);
        if(!message.equals("")) {
         	controller.start();
        }
        
    }
}