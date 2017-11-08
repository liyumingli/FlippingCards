/**
 * Created by t00180267 on 15/11/2016.
 */

import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Thread;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FlippingCards extends JFrame implements ActionListener {
    public  int rows;
    public int cols;
    private int step=0;
    private int numCards = rows * cols;
    private Card[] cards = new Card[numCards];
    private int[] randomIndexes = getRandomIntSequence();
    private Card c1;
    private Card c2;
    private Container imageContainer;
    private GridBagConstraints c;
    private int height=950;
    private int width=950;
    private String player;
    private JMenu menu;
    private JMenu GameMenu;
    private JMenuBar menuBar;

    public FlippingCards(int rows, int cols,int numCards)
    {
        setCows(cols);
        setRows(rows);
    }
    public void setRows(int rows){
        this.rows=rows;
    }
    public int getRows(){
        return getRows();
    }
    public void setCows(int cols){
        this.cols=cols;
    }
    public int getCows(){
        return getCows();
    }
    public void setNumCards(int numCards){
        this.numCards=numCards;
    }
    public int getNumCards(){
        return getNumCards();
    }

    public static void main(String[] args) {
        FlippingCards mainFrame = new FlippingCards();
        mainFrame.setVisible(true);
        mainFrame.getContentPane().setBackground( Color.white );
    }
    public FlippingCards () {
        this.rows=0;
        this.cols=0;
        this.numCards=0;

        setTitle("Card Flipping Game");

        setSize(height, width);

        setLocationRelativeTo(null);

        createMenu();
        createGameMenu();

        JMenuBar menuBar= new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(menu);
        menuBar.add(GameMenu);

        imageContainer = new Container();

        imageContainer.setLayout(new GridBagLayout());
        add(imageContainer);

        c = new GridBagConstraints();

        setLayout(new FlowLayout());


        for (int i = 0;i < numCards; i++) {
            c.gridx = randomIndexes[i] % cols;
            c.gridy = randomIndexes[i] / rows;

            cards[i] = new Card(this, "pic" + (int)Math.ceil((i + 1) / 2d) + ".jpg");

            imageContainer.add(cards[i], c);
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent event)
    {

        String  menuName;
        menuName = event.getActionCommand();

        if(menuName.equals("Save"))
        {
            File outFile = new File("C:/Users/t00180267/Desktop/cards.data");
            FileOutputStream outFileStream = null;
            try {
                outFileStream = new FileOutputStream(outFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ObjectOutputStream os = null;
            try {
                os = new ObjectOutputStream(outFileStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.writeObject(numCards);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"Saved!");
        }

        else if(menuName.equals("Open"))
        {
            File inFile = new File("card.data");
            FileInputStream inFileStream = null;
            try {
                inFileStream = new FileInputStream(inFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(inFileStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            File inFile = new File(System.getProperty("user.home"), "Desktop");
//            System.out.println(inFile.getPath() + " " + inFile.exists());
//
//            try {
//                Desktop.getDesktop().open(inFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        else if(menuName.equals("Exit"))
        {
            System.exit(0);
            System.out.println("out");
        }
        else if(menuName.equals("Add Player"))
        {
            player = JOptionPane.showInputDialog(null, "Please enter your name");
            System.out.println(player);
        }
        else if(menuName.equals("Log Out"))
        {
            player = null;
            System.out.println("OUT");
        }
        else if(menuName.equals("Easy"))
        {
            this.dispose();
            FlippingCards mainFrame = new FlippingCards();
            mainFrame.setVisible(true);
            setRows(4);
            setCows(4);
            System.out.println(rows);
        }
        else if(menuName.equals("Normal"))
        {
            this.dispose();
            FlippingCards mainFrame = new FlippingCards();
            mainFrame.setVisible(true);
            this.setRows(6);
            this.setCows(6);
            System.out.println(rows);
        }
        else if(menuName.equals("Hard"))
        {
            this.dispose();
            FlippingCards mainFrame = new FlippingCards();
            mainFrame.setVisible(true);
            this.setRows(8);
            this.setCows(8);
            System.out.println(rows);
        }
        else if(menuName.equals("Restart"))
        {
            this.dispose();
            FlippingCards mainFrame = new FlippingCards();
            mainFrame.setVisible(true);
            System.out.println("Restart");
        }

    }
    private void createMenu(){

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menu = new JMenu("Menu");
        menuBar.add(menu);


        JMenuItem save = new JMenuItem("Save");
        save.addActionListener( this );
        menu.add(save);

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener( this );
        menu.add(open);

        menu.addSeparator();

        JMenuItem addMenu = new JMenuItem("Add Player");
        addMenu.addActionListener( this );
        menu.add(addMenu);

        JMenuItem quitMenu= new JMenuItem("Log Out");
        quitMenu.addActionListener( this );
        menu.add(quitMenu);

        menu.addSeparator();

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener( this );
        menu.add(exit);


    }
    private void createGameMenu(){
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        GameMenu = new JMenu("Game");
        menuBar.add(GameMenu);

        JMenuItem easy = new JMenuItem("Easy");
        easy.addActionListener( this );
        GameMenu.add(easy);

        JMenuItem normal = new JMenuItem("Normal");
        normal.addActionListener( this );
        GameMenu.add(normal);

        JMenuItem Hard = new JMenuItem("Hard");
        Hard.addActionListener( this );
        GameMenu.add(Hard);

        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener( this );
        GameMenu.add(restart);

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
            step ++;
            System.out.println(step);
        }
        else{
            card1.setForward(false);
            card2.setForward(false);
            step ++;
            System.out.println(step);
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
                "Congratulations " + player + "!" + " You won the game! " + step + " steps ",
                "Game Won",
                JOptionPane.INFORMATION_MESSAGE
            );
            this.dispose();
            FlippingCards mainFrame = new FlippingCards();
            mainFrame.setVisible(true);
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
                try {
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
}
