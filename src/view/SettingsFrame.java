package view;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SettingsFrame extends JFrame {

    private char passwordChar;

    private JButton usernamChange_btn;
    private JButton passwordChange_btn;
    private JButton confirmUsername_btn;
    private JButton confirmPassword_btn;
    private JTextField newUsername_tf;
    private JPasswordField newPassword_pf;
    private JPasswordField newPasswordCfm_pf;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    private JPanel mainPanel;
    private JPanel optionPanel;

    public SettingsFrame() {
        initComponents();
    }

    private void initComponents() {

        usernamChange_btn = DesignLibrary.createButton("  USERNAME", DesignLibrary.USER_ICON);
        passwordChange_btn = DesignLibrary.createButton("  PASSWORD", DesignLibrary.PASSWORD_ICON);
        confirmUsername_btn = DesignLibrary.createButton("  POTVRDA", DesignLibrary.EXECUTE_ICON);
        confirmPassword_btn = DesignLibrary.createButton("  POTVRDA", DesignLibrary.EXECUTE_ICON);
        newUsername_tf = DesignLibrary.createTf("New Username", DesignLibrary.CENTER);
        newPassword_pf = DesignLibrary.createPf(DesignLibrary.CENTER);
        newPasswordCfm_pf = DesignLibrary.createPf(DesignLibrary.CENTER);

        // preuzeto sa: https://www.toolbox.com/tech/programming/question/adding-default-text-in-jpasswordfield-062316/
        passwordChar = newPassword_pf.getEchoChar();

        mainPanel = new JPanel(new GridLayout(1, 2, 50, 0));
        mainPanel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        optionPanel = new JPanel(new GridLayout(2, 1, 0, 50));
        optionPanel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        optionPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));

        usernamePanel = new JPanel(new GridLayout(2, 1, 0, 50));
        usernamePanel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        usernamePanel.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 20));

        passwordPanel = new JPanel(new GridLayout(3, 1, 0, 40));
        passwordPanel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 20));

        usernamePanel.add(newUsername_tf);
        usernamePanel.add(confirmUsername_btn);

        passwordPanel.add(newPassword_pf);
        passwordPanel.add(newPasswordCfm_pf);
        passwordPanel.add(confirmPassword_btn);

        optionPanel.add(usernamChange_btn);
        optionPanel.add(passwordChange_btn);

        mainPanel.add(optionPanel);
        mainPanel.add(usernamePanel);

        this.add(mainPanel);

        this.setTitle("Settings");
        this.setSize(1000, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    public JPanel getOptionPanel() {
        return optionPanel;
    }
    
    

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public char getPasswordChar() {
        return passwordChar;
    }

    public JButton getUsernamChange_btn() {
        return usernamChange_btn;
    }

    public JButton getPasswordChange_btn() {
        return passwordChange_btn;
    }

    public JButton getConfirmUsername_btn() {
        return confirmUsername_btn;
    }

    public JButton getConfirmPassword_btn() {
        return confirmPassword_btn;
    }

    public JTextField getNewUsername_tf() {
        return newUsername_tf;
    }

    public JPasswordField getNewPassword_pf() {
        return newPassword_pf;
    }

    public JPasswordField getNewPasswordCfm_pf() {
        return newPasswordCfm_pf;
    }

    public JPanel getUsernamePanel() {
        return usernamePanel;
    }

    public JPanel getPasswordPanel() {
        return passwordPanel;
    }

}
