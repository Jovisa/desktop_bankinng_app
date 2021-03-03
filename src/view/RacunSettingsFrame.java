package view;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RacunSettingsFrame extends JFrame {

    JTextField iznos_tf;
    JButton uplata_btn;
    JButton isplata_btn;
    JButton obrisiRacun_btn;

    public RacunSettingsFrame() {
        initComponents();
    }

    private void initComponents() {
        iznos_tf = DesignLibrary.createCashTf("IZNOS:");
        uplata_btn = DesignLibrary.createButton("  UPLATA", DesignLibrary.PLUS_ICON);
        isplata_btn = DesignLibrary.createButton("  ISPLATA", DesignLibrary.MINUS_ICON);
        obrisiRacun_btn = DesignLibrary.createButton("  OBRISI RACUN", DesignLibrary.DELETE_ICON);
        
        obrisiRacun_btn.setBackground(DesignLibrary.BUTTON_RED);

        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 30));
        panel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(60, 30, 30, 30));

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 30, 5));
        panel2.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
        panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        panel2.add(uplata_btn);
        panel2.add(isplata_btn);

        panel.add(iznos_tf);
        panel.add(panel2);
        panel.add(obrisiRacun_btn);

        this.add(panel);

        this.setTitle("Racun Settings");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public JTextField getIznos_tf() {
        return iznos_tf;
    }

    public JButton getUplata_btn() {
        return uplata_btn;
    }

    public JButton getIsplata_btn() {
        return isplata_btn;
    }

    public JButton getObrisiRacun_btn() {
        return obrisiRacun_btn;
    }
    
    

}
