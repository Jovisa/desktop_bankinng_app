package controller;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Racun;
import model.User;
import util.DB;
import util.InputValidation;
import view.DesignLibrary;
import view.LoginFrame;
import view.MainFrame;
import view.OtvoriRacunFrame;
import view.RacunSettingsFrame;
import view.RegisterFrame;
import view.SettingsFrame;

public class Controller {

    // pomocne konstante
    private final int DIN = 0;
    private final int EUR = 1;
    private final int UPLATA = 2;
    private final int ISPLATA = 3;
    private final double KURS_EVRA = 117.58;

    User user;
    Racun trenutniRacun;
    int indeksRacuna;

    LoginFrame loginFrame;
    RegisterFrame registerFrame;
    MainFrame mainFrame;
    OtvoriRacunFrame otvoriRacunFrame;
    SettingsFrame settingsFrame;
    RacunSettingsFrame racunSettingsFrame;

    public Controller() {

        initLoginFrame();

    }

    // kreira glavni frejm i dodaje odgovarajuce slusace dogadjaja
    private void initMainFrame() {
        mainFrame = new MainFrame();
        initRacuniPanel();
        initTransakcijaPanel();
        initMenjacnicaPanel();
        addLIstenersToMainFrame();

    }

    // kreira otvoriRacun frejm i dodaje odgovarajuce slusace dogadjaja
    private void InitOtvoriRacunFrame() {
        otvoriRacunFrame = new OtvoriRacunFrame();
        addListenersOtvoriRacunFrame();
    }

    // kreira Login frejm i dodaje odgovarajuce slusace dogadjaja
    private void initLoginFrame() {
        loginFrame = new LoginFrame();
        addListenersToLoginFrame();
    }

    // kreira Register frejm i dodaje odgovarajuce slusace dogadjaja
    private void initRegisterFrame() {
        registerFrame = new RegisterFrame();
        addListenersToRegisterFrame();
    }

    // kreira Settings frejm i dodaje odgovarajuce slusace dogadjaja
    private void initSettingsFrame() {
        settingsFrame = new SettingsFrame();
        addListenersSettingsFrame();
    }

    // prikazuje panel na kom se nalaze informacije o racunima korisnika
    private void initRacuniPanel() {

        mainFrame.getRacuniPanel().removeAll();

        List<Racun> racuni = user.getRacuni();

        if (racuni.isEmpty()) {
            mainFrame.getRacuniPanel().add(createRacunPanel());

        } else {

            mainFrame.getRacuniPanel().setLayout(new GridLayout(racuni.size() + 1, 1));
            mainFrame.getRacuniPanel().setBorder(BorderFactory.createEmptyBorder(100, 50, setRacuniPanelHeight(racuni.size()), 50));
            mainFrame.getRacuniPanel().add(createRacunPanel());

            for (int i = 0; i < racuni.size(); i++) {
                mainFrame.getRacuniPanel().add(createRacunPanel(racuni.get(i)));
            }

        }

        mainFrame.getRacuniPanel().repaint();
        mainFrame.getRacuniPanel().revalidate();
    }

    // prikazuje panel za transakcije
    private void initTransakcijaPanel() {

        List<Racun> racuni = user.getRacuni();

        mainFrame.getTransakcijaPanel().getSaRacuna_cb().removeAllItems();

        if (!racuni.isEmpty()) {
            for (Racun r : racuni) {
                mainFrame.getTransakcijaPanel().getSaRacuna_cb().addItem(r.getBrRacuna());
            }
        }

        mainFrame.getTransakcijaPanel().revalidate();
        mainFrame.revalidate();

    }

    // prikazuje menjacnicu
    private void initMenjacnicaPanel() {

        List<Racun> racuni = user.getRacuni();

        if (racuni.isEmpty()) {
            return;
        }

        mainFrame.getMenjacnicaPanel().getSaRacuna_cb().removeAllItems();
        mainFrame.getMenjacnicaPanel().getNaRacun_cb().removeAllItems();

        for (Racun r : racuni) {
            mainFrame.getMenjacnicaPanel().getSaRacuna_cb().addItem(r.getBrRacuna());
        }

        initNaRacunCB();

        mainFrame.getMenjacnicaPanel().repaint();
        mainFrame.getMenjacnicaPanel().revalidate();
        mainFrame.revalidate();

    }

    // u zavisnosti od valute racuna koji je izabran u ComboBox-u naRacun
    // ce se pojaviti samo racuni u drugoj valuti
    private void initNaRacunCB() {

        List<Racun> racuni = user.getRacuni();

        String brRacuna = (String) mainFrame.getMenjacnicaPanel().getSaRacuna_cb().getSelectedItem();
        Racun racun = null;

        for (Racun r : racuni) {
            if (brRacuna.equals(r.getBrRacuna())) {
                racun = r;

            }
        }

        String valuta = (racun.getValuta().equals("DIN")) ? "EUR" : "DIN";

        ArrayList<Racun> racuniUvaluti = new ArrayList<>();

        for (Racun r : racuni) {
            if (r.getValuta().equals(valuta)) {
                racuniUvaluti.add(r);
            }
        }

        mainFrame.getMenjacnicaPanel().getNaRacun_cb().removeAllItems();

        for (Racun r : racuniUvaluti) {
            mainFrame.getMenjacnicaPanel().getNaRacun_cb().addItem(r.getBrRacuna());
        }

        mainFrame.getMenjacnicaPanel().revalidate();
        mainFrame.revalidate();

    }
    // bavi se prikazom Username Panela u Settings frejmu

    private void initUsernamePanel() {
        settingsFrame.getMainPanel().remove(settingsFrame.getPasswordPanel());
        settingsFrame.getMainPanel().add(settingsFrame.getUsernamePanel());
        settingsFrame.getMainPanel().repaint();
        settingsFrame.getMainPanel().revalidate();

        settingsFrame.getNewUsername_tf().setText("New Username");
        addFocusListener(settingsFrame.getNewUsername_tf());
    }

    // bavi se prikazom Password Panela u Settings frejmu
    private void initPasswordPanel() {
        settingsFrame.getMainPanel().remove(settingsFrame.getUsernamePanel());
        settingsFrame.getMainPanel().add(settingsFrame.getPasswordPanel());
        settingsFrame.getMainPanel().repaint();
        settingsFrame.getMainPanel().revalidate();

        setPfText(settingsFrame.getNewPassword_pf(), "New Password");
        setPfText(settingsFrame.getNewPasswordCfm_pf(), "Confirm Password");

        addFocusListener(settingsFrame.getNewPassword_pf(), settingsFrame.getPasswordChar());
        addFocusListener(settingsFrame.getNewPasswordCfm_pf(), settingsFrame.getPasswordChar());
    }

    // metod koji se bavi dodavanjem listenera na Settings view
    private void addListenersSettingsFrame() {

        // klikom na dugme PasswordChange na desnoj polovini frejma se prikazuje 
        // panel koji se bavi promenom pasvorda
        settingsFrame.getPasswordChange_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                initPasswordPanel();
            }

        });

        // klikom na dugme UsernamChange na desnoj polovini frejma se prikazuje 
        // panel koji se bavi promenom username-a
        settingsFrame.getUsernamChange_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                initUsernamePanel();
            }

        });

        // klikom na dugme POTVRDA okida se metod changeUsername koji se bavi promenom username-a
        settingsFrame.getConfirmUsername_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                changeUsername();
            }

        });

        // klikom na dugme POTVRDA okida se metod changePassword koji se bavi promenom lozinke
        settingsFrame.getConfirmPassword_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                changePassword();
            }

        });

        addFocusListener(settingsFrame.getNewUsername_tf());

    }

    // metod koji se bavi dodavanjem listenera na Login formu
    private void addListenersToLoginFrame() {

        // klikom da dugme REGISTER otvara se registraciona forma
        loginFrame.getRegister_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                initRegisterFrame();
            }

        });

        // klikom na dugme LOGIN pokrece se postupak logovanja korisnika
        loginFrame.getLogin_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                login();
            }
        });

        addKeyListener(loginFrame.getUser_tf());
        addFocusListener(loginFrame.getPassword_pf(), loginFrame.getPasswordChar());

    }

    // metod koji dodaje slusace dogadjaja na registracionu formu
    private void addListenersToRegisterFrame() {

        // klikom na dugme REGISTER pokrece se postupak registracije novog korisnika
        registerFrame.getRegister_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                register();
            }

        });

        // dodaju se key i focus listeneri na polja
        addKeyListener(registerFrame.getIme_tf());
        addFocusListener(registerFrame.getPrezime_tf());
        addFocusListener(registerFrame.getMail_tf());
        addFocusListener(registerFrame.getJmbg_tf());
        addFocusListener(registerFrame.getUsername_tf());
        addFocusListener(registerFrame.getPassword_pf(), registerFrame.getPasswordChar());
        addFocusListener(registerFrame.getPassword_cfm_pf(), registerFrame.getPasswordChar());
    }

    // metod koji se bavi dodavanjem listenera na glavni view
    private void addLIstenersToMainFrame() {

        // klikom da dugme LOGOUT korisnik ce se izlogovati
        mainFrame.getLogout_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                logOut();
            }

        });

        // klikom na dugme OTVORI RACUN korisniku ce se prikazati 
        // OtvoriRacun frejm koji mu omogucava kreiranje novog racuna
        mainFrame.getOtvoriRacnun_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                InitOtvoriRacunFrame();
            }

        });

        // klikom na dugme SETTINGS otvara se SettingsFrame gde korisnik
        // moze menjati username i lozinku
        mainFrame.getSettings_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                initSettingsFrame();
            }

        });

        // slusac na ComboBox-u menjacnice 
        mainFrame.getMenjacnicaPanel().getSaRacuna_cb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                initNaRacunCB();
            }

        });

        // klikom na dugme IZVRSI menjacnica ce pokrenuti svoj  postupak
        mainFrame.getMenjacnicaPanel().getIzvrsi_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                exchange();
            }

        });

        // klikom na dugme IZVRSI pokrece se postupak slanja novca sa jednog racuna na drugi
        mainFrame.getTransakcijaPanel().getIzvrsi_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                sendMoney();
            }

        });

        addFocusListener(mainFrame.getMenjacnicaPanel().getIznos_tf());
        addFocusListener(mainFrame.getTransakcijaPanel().getIznos_tf());
        addFocusListener(mainFrame.getTransakcijaPanel().getNaRacun_tf());
    }

    // pomocni metod koji dodaje listenere na view koji se bavi kreiranjem novog racuna
    private void addListenersOtvoriRacunFrame() {

        otvoriRacunFrame.getOtvoriRacun_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {

                otvoriRacun();
                otvoriRacunFrame.dispose();
            }

        });

        addFocusListener(otvoriRacunFrame.getIznos_tf());
    }

    // metod koji se bavi logovanjem korisnika
    private void login() {

        // provera da li su polja za unos popunjena
        if (!checkEmptyFields(loginFrame)) {
            return;
        }

        String username = loginFrame.getUser_tf().getText();
        String password = new String(loginFrame.getPassword_pf().getPassword());

        // provera da li korsnik postoji u bazi
        user = DB.loginUser(username, password);

        // obradjuje se slucaj da logovanje nije uspelo
        if (user == null) {

            // proverava se da li username koji je korinik uneo postoji u bazi
            if (!DB.checkUsername(username)) {
                JOptionPane.showMessageDialog(null, "ERROR: nalog ne postoji");
                loginFrame.getUser_tf().setText("");
                loginFrame.getPassword_pf().setText("");
                return;
            }

            // ukoliko username postoji u bazi sledi da je korisnik pogresio lozinlku
            JOptionPane.showMessageDialog(null, "ERROR: pogresna lozinka");
            loginFrame.getPassword_pf().setText("");
            return;
        }

        // ukoliko je logovanje uspelo otvara se glavni view
        initMainFrame();
        loginFrame.dispose();

    }

    // metod koji se bavi registracijom novih korisnika
    private void register() {

        // provera da li su sva polja popunjena
        if (!checkEmptyFields(registerFrame)) {
            return;
        }

        // provera da li je mail validan
        if (!InputValidation.validateEmail(registerFrame.getMail_tf())) {
            JOptionPane.showMessageDialog(null, "Niste dobro uneli EMAIL");
            return;
        }

        // provera da li je JMGB validan
        if (!InputValidation.validateJMBG(registerFrame.getJmbg_tf())) {
            JOptionPane.showMessageDialog(null, "Niste dobro uneli JMBG");
            return;
        }

        String ime = registerFrame.getIme_tf().getText();
        String prezime = registerFrame.getPrezime_tf().getText();
        String jmbg = registerFrame.getJmbg_tf().getText();
        String mail = registerFrame.getMail_tf().getText();
        String username = registerFrame.getUsername_tf().getText();
        String password = new String(registerFrame.getPassword_pf().getPassword());
        String passwordCfm = new String(registerFrame.getPassword_cfm_pf().getPassword());

        // provara da li je polje CONFIRM PASSWORD tacno popunjeno
        if (!password.equals(passwordCfm)) {
            JOptionPane.showMessageDialog(null, "polja PASSWORD i POTVRDITE PASSWORD  se ne poklapaju");
            return;
        }

        // provera da li je username koji je korisnik odabrao zauzet
        if (DB.checkUsername(username)) {
            JOptionPane.showMessageDialog(null, "Username koji ste odabrali vac postoji");
            registerFrame.getUsername_tf().setText("");
            return;

        }

        // kreiranje novog korisnika i upis istog u bazu
        User u = new User(ime, prezime, jmbg, mail, username, password);
        user = DB.registerUser(u);

        // zatvaranje registracione forme i prikaz login forme 
        //  sa popunjenim poljima USERNAME i PASSWORD
        registerFrame.dispose();
        loginFrame.getUser_tf().setText(user.getUsername());
        loginFrame.getPassword_pf().setText(user.getPassword());
        loginFrame.getPassword_pf().setEchoChar(loginFrame.getPasswordChar());

    }

    // metoda koja omogucava korisniku da se izloguje
    private void logOut() {
        mainFrame.dispose();
        initLoginFrame();
    }

    // metoda koja se bacvi slanjem novca (transakcijama)
    private void sendMoney() {

        //  Provera da li je polje IZNOS prazno
        if (!InputValidation.isTfNotEmty(mainFrame.getTransakcijaPanel().getIznos_tf(), "IZNOS:")) {
            JOptionPane.showMessageDialog(null, "Niste popunili polje IZNOS");
            return;
        }

        // provera da li je broj ukucan u polje IZNOS
        if (!InputValidation.isNumber(mainFrame.getTransakcijaPanel().getIznos_tf())) {
            return;
        }

        // validacija racuna (regEX)
        if (!InputValidation.validateBrRacuna(mainFrame.getTransakcijaPanel().getNaRacun_tf())) {
            JOptionPane.showMessageDialog(null, "niste dobro uneli broj racuna na koji saljete novac");
            return;
        }

        double iznos = Double.parseDouble(mainFrame.getTransakcijaPanel().getIznos_tf().getText());
        String brSaRacuna = (String) mainFrame.getTransakcijaPanel().getSaRacuna_cb().getSelectedItem();
        String brNaRacun = mainFrame.getTransakcijaPanel().getNaRacun_tf().getText();

        // Dohvatanje racuna, prvo proveravamo da li se korisnig zeli da posalje novac na neki od svojih racuna
        // ukoliko ne proveravamo da li racun postoji u bazi.
        Racun saRacuna = findRacun(brSaRacuna);
        Racun naRacun = findRacun(brNaRacun);

        if (naRacun == null) {
            naRacun = DB.getRacun(brNaRacun);
        }

        // provera da li racun na koji se salje novac postoji u bazi
        if (naRacun == null) {
            JOptionPane.showMessageDialog(null, "Racun na koji ste zeleli da posaljete novac ne postoji u bazi");
            mainFrame.getTransakcijaPanel().getNaRacun_tf().setText("");
            return;
        }

        // provera da li su oba racuna u istoj valuti
        if (!saRacuna.getValuta().equals(naRacun.getValuta())) {
            JOptionPane.showMessageDialog(null, "Oba racuna moraju biti u istoj valuti");
            mainFrame.getTransakcijaPanel().getIznos_tf().setText("");
            return;
        }

        // azuriranje novog stanja na racunima, novo stnje se zaokruzujr na 2 decimale radi lepseg prikaza
        double saRacunaNoviIznos = Math.round((saRacuna.getStanje() - iznos) * 100) / 100;
        double naRacunNoviIznos = Math.round((naRacun.getStanje() + iznos) * 100) / 100;

        saRacuna.setStanje(saRacunaNoviIznos);
        naRacun.setStanje(naRacunNoviIznos);

        // azuriranje stanja na racunima u bazi
        DB.updateRacun(saRacuna);
        DB.updateRacun(naRacun);

        // azurira se view radi prikaza novog stanja
        initRacuniPanel();

        mainFrame.getTransakcijaPanel().getIznos_tf().setText("IZNOS:");
        mainFrame.getTransakcijaPanel().getNaRacun_tf().setText("NA RACUN:");
        addFocusListener(mainFrame.getTransakcijaPanel().getIznos_tf());
        addFocusListener(mainFrame.getTransakcijaPanel().getNaRacun_tf());
    }

    // metod koji obavlja posao menjacnice
    private void exchange() {

        //  Provera da li je polje IZNOS prazno
        if (!InputValidation.isTfNotEmty(mainFrame.getMenjacnicaPanel().getIznos_tf(), "IZNOS:")) {
            JOptionPane.showMessageDialog(null, "Niste popunili polje IZNOS");
            return;
        }

        // provera da li je broj ukucan u polje IZNOS
        if (!InputValidation.isNumber(mainFrame.getMenjacnicaPanel().getIznos_tf())) {
            return;
        }

        double iznos = Double.parseDouble(mainFrame.getMenjacnicaPanel().getIznos_tf().getText());
        String brSaRacuna = (String) mainFrame.getMenjacnicaPanel().getSaRacuna_cb().getSelectedItem();
        String brNaRacun = (String) mainFrame.getMenjacnicaPanel().getNaRacun_cb().getSelectedItem();

        if (brNaRacun == null) {
            JOptionPane.showMessageDialog(null, "Usluge menjacnice vam trenutno nisu dostupne, svi vasi racuni su u istoj valuti");
            return;
        }

        // dohvataju se racuni iz liste racuna koje korisnik poseduje
        Racun saRacuna = findRacun(brSaRacuna);
        Racun naRacun = findRacun(brNaRacun);

        // konvertuje se iznos (dinari u evre ili obrnuto) po srefnjem kursu NBS
        double konvertovanIznos = konvertujValutu(iznos, naRacun.getValuta());

        // azuriranje novog stanje na racunima
        saRacuna.setStanje(saRacuna.getStanje() - iznos);
        naRacun.setStanje(naRacun.getStanje() + konvertovanIznos);

        // azuriranje novig stanja u bazi
        DB.updateRacun(saRacuna);
        DB.updateRacun(naRacun);

        // azuriranje view-a 
        initRacuniPanel();

        mainFrame.getMenjacnicaPanel().getIznos_tf().setText("IZNOS:");
        addFocusListener(mainFrame.getMenjacnicaPanel().getIznos_tf());
    }

    // pomocni metod za konvertovanje valjuta po srednjem kursu NBS
    private double konvertujValutu(double iznos, String kupovnaValuta) {

        double konvertovanIznos = 0;

        if (kupovnaValuta.equals("EUR")) {

            konvertovanIznos = Math.round((iznos / KURS_EVRA) * 100.0) / 100.0;
        }

        if (kupovnaValuta.equals("DIN")) {

            konvertovanIznos = iznos * KURS_EVRA;
        }

        return konvertovanIznos;
    }

    // pomocni metod koji dohvata racun iz liste racuna datog korisnika
    private Racun findRacun(String brRacuna) {

        List<Racun> racuni = user.getRacuni();
        Racun racun = null;

        for (Racun r : racuni) {
            if (brRacuna.equals(r.getBrRacuna())) {
                racun = r;
            }
        }

        return racun;
    }

    // metod koji se bavi promenom USERNAME-a
    private void changeUsername() {

        //  Provera da li je polje NEW USERNAME prazno
        if (!InputValidation.isTfNotEmty(settingsFrame.getNewUsername_tf(), "New Username")) {
            JOptionPane.showMessageDialog(null, "Niste uneli novi USERNAME");
            return;
        }

        String newUsername = settingsFrame.getNewUsername_tf().getText();

        // setovanje novog username-a i azuriranje baze
        user.setUsername(newUsername);
        DB.updateUser(user);

        // provera da li je novi username azuriran u 
        if (DB.checkUsername(user.getUsername())) {
            JOptionPane.showMessageDialog(null, "Uspesno ste promenili Username");
        } else {
            JOptionPane.showMessageDialog(null, "ERROR: promena Username neuspesna");
        }

    }

    // metod koji se bavi promenom PASSWORD-a
    private void changePassword() {

        //  Provera da li je polje NEW PASSWORD prazno
        if (!InputValidation.isTfNotEmty(settingsFrame.getNewPassword_pf(), "New Password")) {
            JOptionPane.showMessageDialog(null, "Niste uneli novi PASSWORD");
            return;
        }

        //  Provera da li je polje CONFIRM NEW PASSWORD prazno
        if (!InputValidation.isTfNotEmty(settingsFrame.getNewPasswordCfm_pf(), "Confirm Password")) {
            JOptionPane.showMessageDialog(null, "Niste potvrdili novi PASSWORD");
            return;
        }

        String newPassword = new String(settingsFrame.getNewPassword_pf().getPassword());
        String newPasswordCfm = new String(settingsFrame.getNewPasswordCfm_pf().getPassword());

        // provera da li je korisnik ptvrdio novi PASSWORD
        if (!newPassword.equals(newPasswordCfm)) {
            JOptionPane.showMessageDialog(null, "Milomo vas da u oba polja ukucate novi Password");
            settingsFrame.getNewPasswordCfm_pf().setText("");
            return;
        }

        // azuriranje novog password-a u bazi
        user.setPassword(newPassword);
        DB.updateUser(user);

        // provera da li je pasword azuriran u bazi
        User updatedUser = DB.loginUser(user.getUsername(), user.getPassword());
        if (updatedUser == null) {
            JOptionPane.showMessageDialog(null, "ERROR: promena Passworda neuspesna");
        } else {
            JOptionPane.showMessageDialog(null, "Uspesno ste promenili Password");
        }

    }

    // metod koji se bavi kreiranjem novog racuna
    private void otvoriRacun() {

        //  Provera da li je polje IZNOS prazno
        if (!InputValidation.isTfNotEmty(otvoriRacunFrame.getIznos_tf(), "IZNOS:")) {
            JOptionPane.showMessageDialog(null, "Niste popunili polje IZNOS");
            return;
        }

        // provera da li je broj ukucan u polje IZNOS
        if (!InputValidation.isNumber(otvoriRacunFrame.getIznos_tf())) {
            return;
        }

        // dohvatanje podataka koje je korisnik uneo i generisanje broja racuna 
        int valuta = otvoriRacunFrame.getDin_rbtn().isSelected() ? DIN : EUR;
        String brRacuna = generisiBrRacuna(valuta);
        Double stanje = Double.parseDouble(otvoriRacunFrame.getIznos_tf().getText());

        // kreiranje novog objekta Racun, dodavanje istog u listu racuna korisnika i u bazu
        Racun noviRacun = new Racun(brRacuna, valuta, stanje);
        user.addRacun(noviRacun);

        DB.updateUser(user);

        // azuriranje view-a
        initRacuniPanel();
        initTransakcijaPanel();

        mainFrame.getMenjacnicaPanel().getSaRacuna_cb().addItem(noviRacun.getBrRacuna());
        mainFrame.getMenjacnicaPanel().revalidate();
        mainFrame.revalidate();
        initNaRacunCB();
        //initMenjacnicaPanel();
    }

    // metod koji se bavi prikazom racuna korisnika
    private JPanel createRacunPanel(Racun racun) {
        JLabel brRacuna = new JLabel(racun.getBrRacuna(), JLabel.CENTER);
        JLabel valuta = new JLabel(racun.getValuta(), JLabel.CENTER);
        JLabel stanje = new JLabel(racun.getStanje() + "", JLabel.CENTER);

        brRacuna.setFont(DesignLibrary.LABEL_FONT);
        valuta.setFont(DesignLibrary.LABEL_FONT);
        stanje.setFont(DesignLibrary.LABEL_FONT);

        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);

        panel.add(brRacuna);
        panel.add(valuta);
        panel.add(stanje);

        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                panel.setBackground(DesignLibrary.BUTTON_GREEN);

            }

            @Override
            public void mouseExited(MouseEvent me) {
                panel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);
            }

        });

        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                //indeksRacuna = user.getRacuni().indexOf(racun);
                trenutniRacun = racun;
                racunSettingsFrame = new RacunSettingsFrame();
                addListenersRacunSettingsFrame();
            }

        });

        return panel;
    }

    private void addListenersRacunSettingsFrame() {

        racunSettingsFrame.getUplata_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                promenaStanja(UPLATA);

            }
        });

        racunSettingsFrame.getIsplata_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                promenaStanja(ISPLATA);

            }
        });

        racunSettingsFrame.getObrisiRacun_btn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {

                obrisiRacun();
            }

        });

        addKeyListener(racunSettingsFrame.getIznos_tf());
    }

    private void obrisiRacun() {
        user.getRacuni().remove(trenutniRacun);
        DB.updateUser(user);
        DB.deleteRacun(trenutniRacun);

        initRacuniPanel();
        initTransakcijaPanel();

        mainFrame.getMenjacnicaPanel().getSaRacuna_cb().removeItem(trenutniRacun.getBrRacuna());
        mainFrame.getMenjacnicaPanel().revalidate();
        mainFrame.revalidate();
        initNaRacunCB();
        racunSettingsFrame.dispose();
    }

    private void promenaStanja(int operacija) {

        //  Provera da li je polje IZNOS prazno
        if (!InputValidation.isTfNotEmty(racunSettingsFrame.getIznos_tf(), "IZNOS:")) {
            JOptionPane.showMessageDialog(null, "Niste popunili polje IZNOS");
            return;
        }

        // provera da li je broj ukucan u polje IZNOS
        if (!InputValidation.isNumber(racunSettingsFrame.getIznos_tf())) {
            return;
        }

        double iznos = Double.parseDouble(racunSettingsFrame.getIznos_tf().getText());

        if (operacija == UPLATA) {
            trenutniRacun.setStanje(trenutniRacun.getStanje() + iznos);
        }

        if (operacija == ISPLATA) {

            double stanje = trenutniRacun.getStanje();

            if (stanje < iznos) {
                JOptionPane.showMessageDialog(null, "Nemate dovoljno sretstava na racunu");
                racunSettingsFrame.getIznos_tf().setText("");
                return;
            }

            trenutniRacun.setStanje(stanje - iznos);
        }

        DB.updateRacun(trenutniRacun);

        initRacuniPanel();
    }

    // overloadovan isti medod koji ispisuje prvi red sa informacijama o racunima korisnika
    private JPanel createRacunPanel() {
        JLabel brRacuna = new JLabel("RACUN", JLabel.CENTER);
        JLabel valuta = new JLabel("VALUTA", JLabel.CENTER);
        JLabel stanje = new JLabel("STANJE", JLabel.CENTER);

        brRacuna.setForeground(DesignLibrary.BUTTON_GREEN);
        brRacuna.setFont(DesignLibrary.SMALL_BUTTON_FONT);

        valuta.setForeground(DesignLibrary.BUTTON_GREEN);
        valuta.setFont(DesignLibrary.SMALL_BUTTON_FONT);

        stanje.setForeground(DesignLibrary.BUTTON_GREEN);
        stanje.setFont(DesignLibrary.SMALL_BUTTON_FONT);

        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(DesignLibrary.COMPONENT_BACKGROUND_COLOR);

        panel.add(brRacuna);
        panel.add(valuta);
        panel.add(stanje);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        return panel;
    }

    // pomocni metod koji dodaje key listneer na textfield
    private void addKeyListener(JTextField tf) {

        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                tf.setText("");
                tf.removeKeyListener(this);

            }

        });
    }

    // pomocni metod koji dodaje focus listneer na paswordField
    private void addFocusListener(JPasswordField pf, char passwordChar) {

        pf.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent fe) {
                pf.setEchoChar(passwordChar);
                pf.setText("");
                pf.removeFocusListener(this);

            }

            @Override
            public void focusLost(FocusEvent fe) {

            }

        });
    }

    // isti metod overlodovan da radi sa textField-om
    private void addFocusListener(JTextField tf) {

        tf.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent fe) {
                tf.setText("");
                tf.removeFocusListener(this);

            }

            @Override
            public void focusLost(FocusEvent fe) {

            }

        });
    }

    // metod koji proverava da li je korisnik popunio polja u Login frejmu
    private boolean checkEmptyFields(LoginFrame loginFrame) {

        // koristi se staticni metod isTfNotEmty klase InputValidation
        boolean isUsernameNotEmpty = InputValidation.isTfNotEmty(loginFrame.getUser_tf(), "Username");
        boolean isPasswordNotEmpty = InputValidation.isTfNotEmty(loginFrame.getPassword_pf(), "Password");

        if (!isUsernameNotEmpty && !isPasswordNotEmpty) {
            JOptionPane.showMessageDialog(null, "Niste popunili polja USERNAME i PASSWORD");
            return false;
        } else if (!isUsernameNotEmpty) {
            JOptionPane.showMessageDialog(null, "NIste popunili polje USERNAME");
            return false;
        } else if (!isPasswordNotEmpty) {
            JOptionPane.showMessageDialog(null, "NIste popunili polje PASSWORD");
            return false;
        }

        return true;
    }

    // isti metod overlodaovan da primi register frejm
    private boolean checkEmptyFields(RegisterFrame registerFrame) {

        boolean isUsernameNotEmpty = InputValidation.isTfNotEmty(registerFrame.getUsername_tf(), " Username");
        boolean isPasswordNotEmpty = InputValidation.isTfNotEmty(registerFrame.getPassword_pf(), " Password");
        boolean isPasswordCfmNotEmpty = InputValidation.isTfNotEmty(registerFrame.getPassword_pf(), " Potvrdite Password");
        boolean isImeNotEmpty = InputValidation.isTfNotEmty(registerFrame.getIme_tf(), " Ime");
        boolean isPrezimeNotEmpty = InputValidation.isTfNotEmty(registerFrame.getPrezime_tf(), " Prezime");
        boolean isMailNotEmpty = InputValidation.isTfNotEmty(registerFrame.getMail_tf(), " Mail");
        boolean isJmbgNotEmpty = InputValidation.isTfNotEmty(registerFrame.getJmbg_tf(), " JMBG");

        if (!isUsernameNotEmpty || !isPasswordNotEmpty || !isPasswordCfmNotEmpty || !isImeNotEmpty || !isPrezimeNotEmpty || !isMailNotEmpty || !isJmbgNotEmpty) {
            JOptionPane.showMessageDialog(null, "NIste popunili sva polja!");
            return false;
        }
        return true;
    }

    // pomocni metod koji se bavi azuriranjem visine Racun Panel-a 
    // (view koji prikazije racune korisnika) u zavisnosti od broja racuna (redova)
    private int setRacuniPanelHeight(int racuniLenght) {
        int h = 300;

        for (int i = 0; i < racuniLenght; i++) {
            h -= 45;
        }
        return h;
    }

    // pomocni metod koji generise broj racuna na osnovu algoritma koj sam smislio
    private String generisiBrRacuna(int valuta) {
        Random r = new Random();

        // broj koji se dobija dohvatanjem poslednjeg racuna u bazi
        int lastId = DB.getLastRacunId();
        int num = 100 + lastId + 1;

        // dinarski racuni pocinju sa 11 a devizni sa 22
        String tip = (valuta == DIN) ? "11" : "22";

        // poslednje 3 cifre JMBG
        String imbgLast2 = user.getJmbg().substring(10);

        // random 6-cifreni broj
        int randomNumber = r.nextInt(899999) + 10000;

        // br racuna od 14 cifara
        return tip + imbgLast2 + num + randomNumber;

    }

    // mtod koji pretvara password fild u text fild
    private void setPfText(JPasswordField pf, String text) {
        pf.setEchoChar((char) 0);
        pf.setText(text);
    }
}
