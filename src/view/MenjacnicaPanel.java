package view;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenjacnicaPanel extends JPanel {

    private JLabel menjacnica_lbl;
    private JLabel saRacuna_lbl;
    private JLabel naRacun_lbl;
    private JComboBox<String> saRacuna_cb;
    private JComboBox<String> naRacun_cb;
    private JTextField iznos_tf;
    private JButton izvrsi_btn;

    public MenjacnicaPanel() {
        initComponents();

    }

    private void initComponents() {

        menjacnica_lbl = DesignLibrary.createInfoLabel("MENJACNICA");
        saRacuna_lbl = DesignLibrary.createLabel("SA RACUNA:     ");
        naRacun_lbl = DesignLibrary.createLabel("NA RACUN:     ");
        iznos_tf = DesignLibrary.createTf(" IZNOS:", DesignLibrary.CENTER);
        izvrsi_btn = DesignLibrary.createButton("  IZVRSI", DesignLibrary.EXECUTE_ICON);
        saRacuna_cb = DesignLibrary.createComboBox();
        naRacun_cb = DesignLibrary.createComboBox();
        
        iznos_tf.setBorder(BorderFactory.createLineBorder(DesignLibrary.TF_BORDER_COLOR));

        this.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        this.setLayout(new GridLayout(5, 1, 0, 40));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        panel.add(saRacuna_lbl);
        panel.add(saRacuna_cb);

        JPanel panel2 = new JPanel(new GridLayout(1, 2));
        panel2.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        panel2.add(naRacun_lbl);
        panel2.add(naRacun_cb);

        this.add(menjacnica_lbl);
        this.add(panel);
        this.add(panel2);
        this.add(iznos_tf);
        this.add(izvrsi_btn);

    }

    public JComboBox<String> getSaRacuna_cb() {
        return saRacuna_cb;
    }

    public JComboBox<String> getNaRacun_cb() {
        return naRacun_cb;
    }

    public JTextField getIznos_tf() {
        return iznos_tf;
    }

    public JButton getIzvrsi_btn() {
        return izvrsi_btn;
    }

}
