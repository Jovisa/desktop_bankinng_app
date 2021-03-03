package view;


import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

    private char passwordChar;

    private JTextField user_tf;
    private JPasswordField password_pf;
    private JLabel register_l;
    private JButton login_btn;
    private JButton register_btn;

    public LoginFrame() {
        initComponents();

    }

    private void initComponents() {
   
        user_tf = DesignLibrary.createTf("Username", DesignLibrary.CENTER);
        password_pf = DesignLibrary.createPf(DesignLibrary.CENTER);
        register_l = new JLabel("Nemate nalog? >>");
        login_btn = DesignLibrary.createButton("  LOGIN", DesignLibrary.LOGIN_ICON, DesignLibrary.SMALL);
        register_btn = DesignLibrary.createButton(" REGISTER", DesignLibrary.REGISTER_ICON, DesignLibrary.SMALL);

        // preuzeto sa: https://www.toolbox.com/tech/programming/question/adding-default-text-in-jpasswordfield-062316/
        passwordChar = password_pf.getEchoChar();
        password_pf.setEchoChar((char) 0);
        password_pf.setText("Password");

        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
        panel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);

        JPanel rPanel = new JPanel(new GridLayout(1, 2, 5, 50));
        rPanel.setBorder(BorderFactory.createEmptyBorder(15, 60, 5, 0));
        rPanel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        rPanel.add(register_l);
        rPanel.add(register_btn);

        panel.add(user_tf);
        panel.add(password_pf);
        panel.add(login_btn);
        panel.add(rPanel);

        this.add(panel);

        this.setTitle("Login");
        this.setSize(480, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }

    public char getPasswordChar() {
        return passwordChar;
    }

    public JTextField getUser_tf() {
        return user_tf;
    }

    public JPasswordField getPassword_pf() {
        return password_pf;
    }

    public JButton getLogin_btn() {
        return login_btn;
    }

    public JButton getRegister_btn() {
        return register_btn;
    }
    
    

}
