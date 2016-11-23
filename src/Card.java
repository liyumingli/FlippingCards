/**
 * Created by t00180267 on 15/11/2016.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;

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

    public void setForward (boolean isForward) {
        this.isForward = isForward;

        if (isForward) {
            Card.this.setIcon(new ImageIcon(this.getClass().getResource("/images/" + cardImage)));
            super.paintComponent(getGraphics());
        } else {
            Card.this.setIcon(new ImageIcon(this.getClass().getResource("/images/" + defaultImage)));
        }
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
}



