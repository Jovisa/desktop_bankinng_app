package view;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class OtvoriRacunFrame extends JFrame {

    JRadioButton din_rbtn;
    JRadioButton eur_rbtn;
    ButtonGroup tip_btn_g;
    JTextField iznos_tf;
    JButton otvoriRacun_btn;

    public OtvoriRacunFrame() {
        initComponents();
    }

    private void initComponents() {
        otvoriRacun_btn = DesignLibrary.createButton(" OTVORI RACUN", DesignLibrary.EXECUTE_ICON);
        din_rbtn = DesignLibrary.createRadioBtn(" DIN");
        eur_rbtn = DesignLibrary.createRadioBtn(" EUR");
        tip_btn_g = new ButtonGroup();
        iznos_tf = DesignLibrary.createCashTf(" IZNOS:");

        tip_btn_g.add(din_rbtn);
        tip_btn_g.add(eur_rbtn);

        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 30));
        panel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panel2 = new JPanel(new GridLayout(1, 2));
        panel2.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);

        panel2.add(din_rbtn);
        panel2.add(eur_rbtn);

        panel.add(panel2);
        panel.add(iznos_tf);
        panel.add(otvoriRacun_btn);

        this.add(panel);

        this.setTitle("Otvori racun");
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    public JRadioButton getDin_rbtn() {
        return din_rbtn;
    }

    public JRadioButton getEur_rbtn() {
        return eur_rbtn;
    }

    public JTextField getIznos_tf() {
        return iznos_tf;
    }

    public JButton getOtvoriRacun_btn() {
        return otvoriRacun_btn;
    }

}
