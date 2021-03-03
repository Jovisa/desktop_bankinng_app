
package model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String ime, prezime, jmbg, mail;
    
    @Column (nullable = false)
    private String username;
    
    @Column (nullable = false)
    private String password;
    
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    private List<Racun> racuni;

    public User() {
    }

    public User(String ime, String prezime, String jmbg, String mail, String username, String password) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Racun> getRacuni() {
        return racuni;
    }

    public void setRacuni(List<Racun> racuni) {
        this.racuni = racuni;
    }
    
    public void addRacun(Racun racun) {
        racuni.add(racun);
    }
    
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", mail=" + mail + ", username=" + username + ", password=" + password + '}';
    }
    
    
}
