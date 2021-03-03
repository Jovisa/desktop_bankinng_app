package view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TransakcijaPanel extends JPanel {

    private JLabel tansakcija_lbl;
    private JLabel saRacuna_lbl;
    private JComboBox<String> saRacuna_cb;
    private JTextField naRacun_tf;
    private JTextField iznos_tf;
    private JButton izvrsi_btn;

    public TransakcijaPanel() {
        initComponents();

    }

    private void initComponents() {

        tansakcija_lbl = DesignLibrary.createInfoLabel("TRANSAKCIJE");
        saRacuna_lbl = DesignLibrary.createLabel("SA RACUNA:     ");
        naRacun_tf = DesignLibrary.createTf("NA RACUN:", DesignLibrary.CENTER);
        iznos_tf = DesignLibrary.createTf("IZNOS:", DesignLibrary.CENTER);
        saRacuna_cb = DesignLibrary.createComboBox();
        izvrsi_btn = DesignLibrary.createButton("  IZVRSI", DesignLibrary.EXECUTE_ICON);

        naRacun_tf.setBorder(BorderFactory.createLineBorder(DesignLibrary.TF_BORDER_COLOR));
        iznos_tf.setBorder(BorderFactory.createLineBorder(DesignLibrary.TF_BORDER_COLOR));

        this.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        this.setLayout(new GridLayout(5, 1, 0, 40));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        panel.add(saRacuna_lbl);
        panel.add(saRacuna_cb);

        this.add(tansakcija_lbl);
        this.add(panel);
        this.add(naRacun_tf);
        this.add(iznos_tf);
        this.add(izvrsi_btn);

    }

    public JComboBox<String> getSaRacuna_cb() {
        return saRacuna_cb;
    }

    public JTextField getNaRacun_tf() {
        return naRacun_tf;
    }

    public JTextField getIznos_tf() {
        return iznos_tf;
    }

    public JButton getIzvrsi_btn() {
        return izvrsi_btn;
    }

}
