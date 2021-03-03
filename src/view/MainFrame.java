package view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    private TransakcijaPanel transakcijaPanel;
    private MenjacnicaPanel menjacnicaPanel;
    private JPanel racuniPanel;
    private JPanel buttonPanel;
    private JButton logout_btn;
    private JButton settings_btn;
    private JButton otvoriRacnun_btn;

    public MainFrame() {
        initComponents();

    }

    private void initComponents() {

        initRacuniPanel();
        initButtonPanel();
        transakcijaPanel = new TransakcijaPanel();
        menjacnicaPanel = new MenjacnicaPanel();

        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));
        panel.setBackground(DesignLibrary.MAIN_BACKGROUND_COLOR);

        JPanel panel2 = new JPanel(new GridLayout(1, 3, 70, 0));
        panel2.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        panel2.setBackground(DesignLibrary.MAIN_BACKGROUND_COLOR);

        panel2.add(transakcijaPanel);
        panel2.add(menjacnicaPanel);
        panel2.add(buttonPanel);

        panel.add(racuniPanel);
        panel.add(panel2);

        this.add(panel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1800, 1000);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void initRacuniPanel() {
        racuniPanel = new JPanel(new GridLayout(1, 1));
        racuniPanel.setBackground(DesignLibrary.MAIN_BACKGROUND_COLOR);
        racuniPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 300, 50));
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel(new GridLayout(3, 1, 0, 70));
        buttonPanel.setBackground(DesignLibrary.MAIN_BACKGROUND_COLOR);

        logout_btn = DesignLibrary.createButton("   Logout", DesignLibrary.LOGOUT_ICON);
        settings_btn = DesignLibrary.createButton("   Settings", DesignLibrary.SETTINGS_ICON);
        otvoriRacnun_btn = DesignLibrary.createButton("  Otvori Racun", DesignLibrary.MONEY_ICON);
        logout_btn.setBackground(DesignLibrary.BUTTON_RED);

        buttonPanel.add(otvoriRacnun_btn);
        buttonPanel.add(settings_btn);
        buttonPanel.add(logout_btn);

    }

    public JButton getSettings_btn() {
        return settings_btn;
    }

    public JButton getLogout_btn() {
        return logout_btn;
    }

    public JButton getOtvoriRacnun_btn() {
        return otvoriRacnun_btn;
    }

    public JPanel getRacuniPanel() {
        return racuniPanel;
    }

    public TransakcijaPanel getTransakcijaPanel() {
        return transakcijaPanel;
    }

    public MenjacnicaPanel getMenjacnicaPanel() {
        return menjacnicaPanel;
    }

}
