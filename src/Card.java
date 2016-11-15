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
    private String cardImage;
    private static String defaultImage = "back.jpg";

    public Card (String cardImage) {
        this.cardImage = cardImage;

        setIcon(new ImageIcon(this.getClass().getResource("/images/" + defaultImage)));

        setBorder(new MatteBorder(1, 1, 1, 1, Color.blue));

        addMouseListener(new CardMouseListener());
    }

    private class CardMouseListener extends MouseAdapter {
        public void mousePressed (MouseEvent e) {
            Card.this.setIcon(new ImageIcon(this.getClass().getResource("/images/" + cardImage)));;
        }
    }
}



