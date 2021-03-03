package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// pomocna klasa u kojoj se nalaze sve boje, fontovi i ikonice, kao i metode za kreiranje komponenti u tim bojama
public class DesignLibrary {

    public static final int LEFT = 0;
    public static final int CENTER = 1;

    public static final int SMALL = 10;

    // main color 247, 246, 245
    public static final Color MAIN_BACKGROUND_COLOR = new Color(247, 246, 245);
    public static final Color COMPONENT_BACKGROUND_COLOR = Color.white;
    public static final Color COMPONENT_BACKGROUND_COLOR2 = new Color(247, 246, 245);
    public static final Color BUTTON_GREEN = new Color(118, 179, 163);
    public static final Color BUTTON_RED = new Color(250, 116, 90);
    public static final Color BUTTON_FONT_COLOR = Color.white;
    public static final Color LABEL_FONT_COLOR = Color.black;
    public static final Color TF_BORDER_COLOR = Color.black;

    public static final Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 30);
    public static final Font SMALL_BUTTON_FONT = new Font("SansSerif", Font.BOLD, 20);
    public static final Font LABEL_FONT = new Font("SansSerif", Font.PLAIN, 20);
    public static final Font BIG_LABEL_FONT = new Font("SansSerif", Font.PLAIN, 30);

    // Preuzeto sa: https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html, https://coderanch.com/t/694785/java/setting-Icon
    public static final ImageIcon LOGOUT_ICON = new ImageIcon(ImageIcon.class.getResource("/img/power-button2.png"));
    public static final ImageIcon SETTINGS_ICON = new ImageIcon(ImageIcon.class.getResource("/img/settings.png"));
    public static final ImageIcon MONEY_ICON = new ImageIcon(ImageIcon.class.getResource("/img/money.png"));
    public static final ImageIcon LOGIN_ICON = new ImageIcon(ImageIcon.class.getResource("/img/login.png"));
    public static final ImageIcon REGISTER_ICON = new ImageIcon(ImageIcon.class.getResource("/img/register.png"));
    public static final ImageIcon REGISTER_SMALL_ICON = new ImageIcon(ImageIcon.class.getResource("/img/register-small.png"));
    public static final ImageIcon EXECUTE_ICON = new ImageIcon(ImageIcon.class.getResource("/img/checkmark.png"));
    public static final ImageIcon PASSWORD_ICON = new ImageIcon(ImageIcon.class.getResource("/img/key.png"));
    public static final ImageIcon USER_ICON = new ImageIcon(ImageIcon.class.getResource("/img/user.png"));
    public static final ImageIcon PLUS_ICON = new ImageIcon(ImageIcon.class.getResource("/img/plus2.png"));
    public static final ImageIcon MINUS_ICON = new ImageIcon(ImageIcon.class.getResource("/img/minus2.png"));
    public static final ImageIcon DELETE_ICON = new ImageIcon(ImageIcon.class.getResource("/img/trash.png"));

    public static JTextField createTf(String text) {
        JTextField tf = new JTextField(text);
        tf.setFont(LABEL_FONT);

        return tf;
    }

    public static JTextField createTf(String text, int alignment) {
        JTextField tf = new JTextField(text);
        tf.setFont(LABEL_FONT);

        if (alignment == CENTER) {
            tf.setHorizontalAlignment(JTextField.CENTER);
        }

        if (alignment == LEFT) {
            tf.setHorizontalAlignment(JTextField.LEFT);
        }

        return tf;
    }

    public static JPasswordField createPf() {
        JPasswordField tf = new JPasswordField();
        tf.setFont(LABEL_FONT);

        return tf;
    }

    public static JPasswordField createPf(int alignment) {
        JPasswordField tf = new JPasswordField();
        tf.setFont(LABEL_FONT);

        if (alignment == CENTER) {
            tf.setHorizontalAlignment(JTextField.CENTER);
        }

        if (alignment == LEFT) {
            tf.setHorizontalAlignment(JTextField.LEFT);
        }

        return tf;
    }

    public static JTextField createCashTf(String text) {
        JTextField tf = new JTextField(text);
        tf.setFont(BIG_LABEL_FONT);
        tf.setForeground(LABEL_FONT_COLOR);
        tf.setHorizontalAlignment(JTextField.CENTER);

        return tf;
    }

    public static JButton createButton(String text, ImageIcon icon) {
        JButton b = new JButton(text, icon);
        b.setBackground(BUTTON_GREEN);
        b.setForeground(BUTTON_FONT_COLOR);
        b.setFont(BUTTON_FONT);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return b;
    }

    public static JButton createButton(String text, ImageIcon icon, int fontSize) {
        JButton b = new JButton(text, icon);
        b.setBackground(BUTTON_GREEN);
        b.setForeground(BUTTON_FONT_COLOR);

        if (fontSize == SMALL) {
            b.setFont(SMALL_BUTTON_FONT);
        }

        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return b;
    }

    public static JRadioButton createRadioBtn(String text) {
        JRadioButton r = new JRadioButton(text);
        r.setBackground(COMPONENT_BACKGROUND_COLOR);
        r.setForeground(BUTTON_GREEN);
        r.setFont(BUTTON_FONT);
        r.setHorizontalAlignment(SwingConstants.CENTER);
        r.setSelected(true);
        r.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return r;
    }

    public static JComboBox<String> createComboBox() {
        JComboBox<String> cb = new JComboBox<>();
        cb.setBackground(BUTTON_GREEN);
        cb.setFont(SMALL_BUTTON_FONT);
        cb.setForeground(BUTTON_FONT_COLOR);
        cb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return cb;
    }

    public static JLabel createInfoLabel(String text) {
        JLabel lbl = new JLabel(text, JLabel.CENTER);
        lbl.setForeground(BUTTON_GREEN);
        lbl.setFont(BUTTON_FONT);

        return lbl;
    }

    public static JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text, JLabel.RIGHT);
        lbl.setForeground(LABEL_FONT_COLOR);
        lbl.setFont(LABEL_FONT);
        return lbl;
    }
}
