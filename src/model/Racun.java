package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

@Entity
public class Racun {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String brRacuna;
    private String valuta;
    private double stanje;

    public Racun() {
    }

    public Racun(String brRacuna, int valuta, double stanje) {
        this.brRacuna = brRacuna;
        this.stanje = stanje;

        if (valuta == 0) {
            this.valuta = "DIN";
        } else {
            this.valuta = "EUR";
        }
    }
    
     public Racun(String brRacuna, int valuta) {
        this.brRacuna = brRacuna;
        this.stanje = 0.00;

        if (valuta == 0) {
            this.valuta = "DIN";
        } else {
            this.valuta = "EUR";
        }
    }

    public int getId() {
        return id;
    }

    public String getBrRacuna() {
        return brRacuna;
    }

    public String getValuta() {
        return valuta;
    }

    public double getStanje() {
        return stanje;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrRacuna(String brRacuna) {
        this.brRacuna = brRacuna;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public void setStanje(double stanje) {
        this.stanje = stanje;
    }

    @Override
    public String toString() {
        return "\n" + brRacuna + " "+ valuta + " " + stanje;
    }

}
