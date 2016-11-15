/**
 * Created by t00180267 on 15/11/2016.
 */
import javax.swing.*;

public class FlippingCards extends JFrame {

    public static void main(String[] args) {
        FlippingCards mainFrame = new FlippingCards();

        mainFrame.setVisible(true);
    }

    public FlippingCards () {
        setTitle("Card Flipping Game");

        setSize(600, 600);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
