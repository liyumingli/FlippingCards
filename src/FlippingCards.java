/**
 * Created by t00180267 on 15/11/2016.
 */
import javax.swing.*;
import java.awt.*;

public class FlippingCards extends JFrame {
    private int rows = 4;
    private int cols = 4;
    private int numCards = rows * cols;
    private Card[] cards = new Card[numCards];

    public static void main(String[] args) {
        FlippingCards mainFrame = new FlippingCards();

        mainFrame.setVisible(true);
    }

    public FlippingCards () {
        setTitle("Card Flipping Game");

        setSize(850, 900);

        setLocationRelativeTo(null);

        Container imageContainer = new Container();

        imageContainer.setLayout(new GridBagLayout());
        add(imageContainer);

        GridBagConstraints c = new GridBagConstraints();

        for (int i = 0;i < numCards; i++) {
            c.gridx = i % cols;
            c.gridy = i / rows;
            cards[i] = new Card(this, "pic1.jpg");

            imageContainer.add(cards[i], c);
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void cardClicked (Card whichCard) {
        System.out.println("A card has been clicked");
    }

}
