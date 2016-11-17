/**
 * Created by t00180267 on 15/11/2016.
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class FlippingCards extends JFrame {
    private int rows = 4;
    private int cols = 4;
    private int numCards = rows * cols;
    private Card[] cards = new Card[numCards];
    private int[] randomIndexes = getRandomIntSequence();

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
            c.gridx = randomIndexes[i] % cols;
            c.gridy = randomIndexes[i] / rows;


            cards[i] = new Card(this, "pic" + (int)Math.ceil((i + 1) / 2d) + ".jpg");

            imageContainer.add(cards[i], c);
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private int[] getRandomIntSequence () {
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < numCards; i++) {
            list.add(i);
        }

        Collections.shuffle(list);

        Integer[] array1 = list.toArray(new Integer[list.size()]);

        int[] array2 = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array2[i] = list.get(i);
        }

        return array2;
    }

    public void cardClicked (Card whichCard) {
        System.out.println("A card has been clicked");
    }

}
