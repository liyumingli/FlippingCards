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
    private int NumCard;
    private int numCards = rows * cols;
    private Card[] cards = new Card[numCards];
    private int[] randomIndexes = getRandomIntSequence();
    private boolean matched=false;
    private Card c1;
    private Card c2;
    private Card selectedCard;
    private Timer t;


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
    public void setMatched(boolean matched)
    {
        this.matched=matched;
    }
    public boolean getMatched()
    {
        return this.matched;
    }
    public void setNumCard(int numCard)
    {
        this.NumCard= NumCard;
    }
    public int getNumCard()
    {
        return this.NumCard;
    }
    public void doTurn(){
        if (c1 == null && c2 == null){
            c1 = selectedCard;
            c1.setText(String.valueOf(c1.getNumCard()));
        }

        if (c1 != null && c1 != selectedCard && c2 == null){
            c2 = selectedCard;
            c2.setText(String.valueOf(c2.getNumCard()));
            t.start();

        }
    }

    public void checkCards(){
        if (c1.getNumCard() == c2.getNumCard()){
            c1.setEnabled(false);
            c2.setEnabled(false);
            c1.setMatched(true);
            c2.setMatched(true);
            if (this.GameWon()){
                JOptionPane.showMessageDialog(this, "You win!");
                System.exit(0);
            }
        }

        else{
            c1.setText(""); //'hides' text
            c2.setText("");
        }
        c1 = null; //reset c1 and c2
        c2 = null;
    }



    //    public boolean GameWon() {
//        for (Card c : this.cards) {
//            if (c.getMatched() == false) {
//                return false;
//            }
//        }
//        return true;
//    }
    public void cardClicked (Card whichCard) {
        System.out.println("A card has been clicked");
    }

}