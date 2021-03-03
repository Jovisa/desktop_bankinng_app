package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// klasa u kojoj se nalaze pomocni metodi za validaciju unosa
public class InputValidation {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)+.com$";
    private static final String JMBG_PATTERN = "[0-9]{13}";
    private static final String BR_RACUNA_PATTERN = "[0-9]{14}";

    private static boolean validateInput(String pattern, String input) {

        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(input);
        return matcher.find();
    }
    
    public static boolean isTfNotEmty(JTextField tf, String placeholder) {
        
        if(tf.getText().isEmpty() || (tf.getText().equals(placeholder)))
            return false;
        
        return true;
    }
    
    public static boolean validateEmail(JTextField mail_tf) {

        return validateInput(EMAIL_PATTERN, mail_tf.getText());
    }

    public static boolean validateJMBG(JTextField jmbg_tf) {
        return validateInput(JMBG_PATTERN, jmbg_tf.getText());
    }

    public static boolean validateBrRacuna(JTextField brRacuna_tf) {
        return validateInput(BR_RACUNA_PATTERN, brRacuna_tf.getText());
    }

    public static boolean isNumber(JTextField iznos_tf) {

        try {
            String korisnickiUnos = iznos_tf.getText();
            double iznos = Double.parseDouble(korisnickiUnos);
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "U polje IZNOS se mogu uneti iskljucivo brojevi!");
            iznos_tf.setText("");
            return false;
        }

    }
}
