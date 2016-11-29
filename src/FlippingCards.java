/**
 * Created by t00180267 on 15/11/2016.
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Thread;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class FlippingCards extends JFrame implements ActionListener{
    private int rows;
    private int cols ;
    private int score = 0;
    private int numCards = rows * cols;
    private Card[] cards = new Card[numCards];
    private int[] randomIndexes = getRandomIntSequence();
    private boolean matched =false;
    private Card c1;
    private Card c2;
//    private Timer t;
    private String[] messageStrings = {"Easy", "Normal", "Hard"};
    private JComboBox comboBoxList = new JComboBox(messageStrings);
    private JLabel lblText = new JLabel();
    private int setRows;
    private int setCols;

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

        setLayout(new FlowLayout());
        comboBoxList.setSelectedIndex(1);
        comboBoxList.addActionListener((ActionListener) this);
        add(comboBoxList);
        add(lblText);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public int getRows()
    {
        return rows;
    }
    public void setRows(int rows)
    {
        this.setRows=rows;
    }
    public int getCols()
    {
        return cols;
    }
    public void setCols(int rows){
        this.setCols=cols;
    }


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == comboBoxList){
            JComboBox cb = (JComboBox)e.getSource();
            String msg =(String)cb.getSelectedItem();
                if(e.getSource()=="Easy"){
                     rows=4;
                     cols=4;
                    System.out.println("Easy");
                }
                else if(e.getSource()=="Normal"){
                     rows=6;
                     cols=6;
                    System.out.println("Normal");
                }
                else if(e.getSource()=="Hard"){
                     rows=8;
                     cols=8;
                    System.out.println("Hard");
                }
        }
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

    public void checkCards(Card card1, Card card2){
        System.out.println("Checking card image");
        if (card1.getCardImage().equals(card2.getCardImage()) && card1!=card2){
            card1.setForward(true);
            card2.setForward(true);

            card1.setIsMatched(true);
            card2.setIsMatched(true);
            score += 100;
            System.out.println(score);
        }
        else{
            card1.setForward(false);
            card2.setForward(false);
            score -= 10;
            System.out.println(score);
        }

        gameWon();

    }

    public void gameWon () {
        int i;

        for (i = 0; i < cards.length; i++) {
            if (!cards[i].getIsForward()) {
                break;
            }
        }

        if (i == cards.length) {
            JOptionPane.showMessageDialog(
                this,
                "Congratulations! You won the game!",
                "Game Won",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    public void cardClicked (Card whichCard) {
        if (!whichCard.getIsMatched()) {
            if (c1 == null) {
                c1 = whichCard;
                c1.setForward(true);
            } else if (c2 == null) {
                c2 = whichCard;
                c2.setForward(true);
            }
            if (c1 != null && c2 != null) {

                System.out.println("Two cards have now been selected");
                try  {
                    Thread.sleep(1000);
                    checkCards(c1, c2);
                    c1 = null;
                    c2 = null;

                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }

//    public class CountUpProgressBar extends JPanel {
//
//        private JProgressBar bar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
//        private JLabel label = new JLabel("", JLabel.CENTER);
//        private Timer timer = new Timer(100, new ActionListener() {
//
//            private int counter = 1;
//            public void actionPerformed(ActionEvent ae) {
//                label.setText(String.valueOf(counter));
//                bar.setValue(++counter);
//                if (counter > 100) {
//                    timer.stop();
//                }
//            }
//        });
//
//        CountUpProgressBar() {
//            super.setLayout(new GridLayout(0, 1));
//            bar.setValue(0);
//            timer.start();
//            this.add(bar);
//            this.add(label);
//            JOptionPane.showMessageDialog(null, this);
//        }
//
//        public void main(String[] args) {
//            SwingUtilities.invokeLater(new Runnable() {
//
//                public void run() {
//                    CountUpProgressBar cdpb = new CountUpProgressBar();
//                }
//            });
//        }
//    }
}
