package view;


import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterFrame extends JFrame {
    
    private char passwordChar;

    private JTextField ime_tf;
    private JTextField prezime_tf;
    private JTextField jmbg_tf;
    private JTextField mail_tf;
    private JTextField username_tf;
    private JPasswordField password_pf;
    private JPasswordField password_cfm_pf;
    private JButton register_btn;
    

    public RegisterFrame() {
        initComponents();

    }

    private void initComponents() {
  
        ime_tf = DesignLibrary.createTf(" Ime");
        prezime_tf = DesignLibrary.createTf(" Prezime");
        mail_tf = DesignLibrary.createTf(" Mail");
        jmbg_tf = DesignLibrary.createTf(" JMBG");
        username_tf = DesignLibrary.createTf(" Username");
        password_pf = DesignLibrary.createPf();
        password_cfm_pf = DesignLibrary.createPf();
        register_btn = DesignLibrary.createButton("  REGISTER", DesignLibrary.REGISTER_ICON);
  
        
        // preuzeto sa: https://www.toolbox.com/tech/programming/question/adding-default-text-in-jpasswordfield-062316/
         passwordChar = password_pf.getEchoChar();
        password_pf.setEchoChar((char) 0);
        password_pf.setText(" Password");

        password_cfm_pf.setEchoChar((char) 0);
        password_cfm_pf.setText(" Potvrdite Password");

        JPanel panel = new JPanel(new GridLayout(8, 1, 0, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
        panel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);

        panel.add(ime_tf);
        panel.add(prezime_tf);
        panel.add(mail_tf);
        panel.add(jmbg_tf);
        panel.add(username_tf);
        panel.add(password_pf);
        panel.add(password_cfm_pf);
        panel.add(register_btn);

        this.add(panel);

        this.setTitle("Register");
        this.setSize(500, 850);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    public char getPasswordChar() {
        return passwordChar;
    }

    public JTextField getIme_tf() {
        return ime_tf;
    }

    public JTextField getPrezime_tf() {
        return prezime_tf;
    }

    public JTextField getJmbg_tf() {
        return jmbg_tf;
    }

    public JTextField getMail_tf() {
        return mail_tf;
    }

    public JTextField getUsername_tf() {
        return username_tf;
    }

    public JPasswordField getPassword_pf() {
        return password_pf;
    }

    public JPasswordField getPassword_cfm_pf() {
        return password_cfm_pf;
    }

    public JButton getRegister_btn() {
        return register_btn;
    }
    
    

}
