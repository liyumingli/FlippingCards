/**
 * Created by t00180267 on 15/11/2016.
 */
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Card extends JLabel {
    private boolean isForward = false;
    private boolean isMatched = false;
    private String cardImage;
    private static String defaultImage = "back.jpg";
    FlippingCards parent;

    public Card (FlippingCards parent, String cardImage) {
        this.cardImage = cardImage;

        this.parent = parent;

        setIcon(new ImageIcon(this.getClass().getResource("/images/" + defaultImage)));

        setBorder(new MatteBorder(1, 1, 1, 1, Color.blue));

        addMouseListener(new CardMouseListener());

    }

    public String getCardImage() {
        return cardImage;
    }

    public boolean setForward (boolean isForward) {
        this.isForward = isForward;

        if (isForward) {
            Card.this.setIcon(new ImageIcon(this.getClass().getResource("/images/" + cardImage)));
            super.paintComponent(getGraphics());
        } else {
            Card.this.setIcon(new ImageIcon(this.getClass().getResource("/images/" + defaultImage)));
        }
        return isForward;
    }

    public boolean getIsMatched () {
        return isMatched;
    }

    public void setIsMatched (boolean isMatched) {
        this.isMatched = isMatched;
    }

    public boolean getIsForward () {
        return isForward;
    }

    private class CardMouseListener extends MouseAdapter {
        public void mousePressed (MouseEvent e) {
            parent.cardClicked(Card.this);
        }
    }
    public void setVisible(boolean setVisible)
    {
        return ;
    }
}



