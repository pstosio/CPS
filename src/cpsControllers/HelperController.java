/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author koperingnet
 */
public class HelperController {

    int typ;

    public enum rodzaj_sygnalu {

        CIAGLY, DYSKRETNY
    }
    rodzaj_sygnalu rodzaj = rodzaj_sygnalu.CIAGLY;
    double A, t1, d, T, kw, ts, krok, kroczek;
    double skok;
    Random gaus;

    private static final Random random = new Random();

//    public HelperController() {
//
//        this.typ = 1;
//        this.A = 10;
//        this.t1 = 0;
//        this.ts = 0;
//        this.d = 20;
//        this.T = 1;
//        this.kw = 0.5;
//        this.krok = 0.5;
//        this.kroczek = (this.getd() - this.gett1()) / 1000.0;
//        this.skok = 10;
//
//    }
    public HelperController(int typ, double A, double t1, double ts, double d, double T,
            double kw, double krok, double kroczek, double skok) {

        this.typ = typ;
        this.A = A;
        this.t1 = t1;
        this.ts = ts;
        this.d = d;
        this.T = T;
        this.kw = kw;
        this.krok = krok;
        this.kroczek = (this.getd() - this.gett1()) / 1000.0;
        this.skok = 10;

    }

    /**
     * Obliczanie wartości średniej
     *
     * @param _rodzaj
     * @param punktyY
     * @return
     */
    public double obl_sredniawartosc(rodzaj_sygnalu _rodzaj, ArrayList<Double> punktyY) {
        int i;
        double srednia = 0;
        int size = 0;
        if (_rodzaj == rodzaj_sygnalu.DYSKRETNY) {
            if (this.gettyp() == 3 || this.gettyp() == 4 || this.gettyp() == 5
                    || this.gettyp() == 6 || this.gettyp() == 7
                    || this.gettyp() == 8) {
// double now= this.getT();
                double _krok = this.gett1();
                int liczba = 0;
                while (_krok < this.getT()) {
                    srednia = srednia + this.getPunktzindexu(liczba, punktyY);
                    liczba = liczba + 1;
                    _krok = _krok + this.getkroczek();
                }
                size = liczba;
            } else {
                for (i = 0; i < punktyY.size(); i++) {
                    srednia = srednia + this.getPunktzindexu(i, punktyY);
                }
                size = punktyY.size();
            }
        }
        return srednia / size;
    }

    
    public double obl_sredniawartoscbezwzgledna(rodzaj_sygnalu _rodzaj, ArrayList<Double> punktyY) {
        int i;
        double srednia = 0;
        double liczba = 0;
        if (_rodzaj == rodzaj_sygnalu.DYSKRETNY) {
            for (i = 0; i < punktyY.size(); i++) {
                liczba = Math.abs(this.getPunktzindexu(i, punktyY));
                srednia = srednia + liczba;
            }
        }
        return srednia / punktyY.size();
    }

    public double obl_mocsrednia(rodzaj_sygnalu _rodzaj, ArrayList<Double> punktyY) {
        int i;
        double moc = 0;
        if (_rodzaj == rodzaj_sygnalu.DYSKRETNY) {
            for (i = 0; i < punktyY.size(); i++) {
                moc = moc + (this.getPunktzindexu(i, punktyY) * this.getPunktzindexu(i, punktyY));
            }
        }
        return moc / punktyY.size();
    }

    public double obl_wartoscskuteczna(rodzaj_sygnalu _rodzaj, ArrayList<Double> punktyY) {
        int i;
        double moc = 0;
        if (_rodzaj == rodzaj_sygnalu.DYSKRETNY) {
            for (i = 0; i < punktyY.size(); i++) {
                moc = moc + (this.getPunktzindexu(i, punktyY) * this.getPunktzindexu(i, punktyY));
            }
        }
        return Math.sqrt(moc / punktyY.size());
    }

    public double obl_wariancja(rodzaj_sygnalu _rodzaj, ArrayList<Double> punktyY) {
        int i;
        double wariancja = 0;
        if (_rodzaj == rodzaj_sygnalu.DYSKRETNY) {
            for (i = 0; i < punktyY.size(); i++) {
                double _wartosc = this.getPunktzindexu(i, punktyY)
                        - this.obl_sredniawartosc(rodzaj_sygnalu.DYSKRETNY, punktyY);
                wariancja = wariancja + (_wartosc * _wartosc);
            }
        }
        return wariancja / punktyY.size();
    }

    public double sin2piTtminT1(double _t) {
        return Math.sin(((2 * Math.PI) / this.getT()) * (_t - this.gett1()));
    }

    public double getkrok() {
        return this.krok;
    }

    public void setrodzajciagly() {
        this.rodzaj = rodzaj_sygnalu.CIAGLY;
    }

    public void setrodzajdyskretny() {
        this.rodzaj = rodzaj_sygnalu.DYSKRETNY;
    }

    public rodzaj_sygnalu getrodzaj() {
        return this.rodzaj;
    }

    public void setRodzaj(rodzaj_sygnalu rodzaj) {
        this.rodzaj = rodzaj;
    }

    public void ustawPunkty(ArrayList<Double> punktyY) {
        double ta = this.gett1();
        double punkt = this.gett1();
        while (ta <= this.gett1() + this.getd()) {
//            punkt = this.wykres_punkty(punkt, ta);
            this.setPunktyY(punkt, punktyY);
            ta = ta + this.getkroczek();
        }
    }

    public List<Double> getPunktyY(ArrayList<Double> punktyY) {
        return punktyY;
    }

    public double getPunktzindexu(int index, ArrayList<Double> punktyY) {
        return punktyY.get(index);
    }

    public void setPunktyY(double punkt, ArrayList<Double> punktyY) {
        punktyY.add(punkt);
    }

    public void setkroczek(double _kroczek) {
        this.kroczek = _kroczek;
    }

    public double getkroczek() {
        if (this.getrodzaj() == rodzaj_sygnalu.CIAGLY) {
            this.kroczek = (this.gett1() + this.getd()) / 1000;
        }
        return this.kroczek;
    }

    public double getskok() {
        return this.skok;
    }

    public void setskok(double _skok) {
        this.skok = _skok;
    }

    public int gettyp() {
        return typ;
    }

    public void settyp(int _typ) {
        this.typ = _typ;
    }

    public double getA() {
        return A;
    }

    public void setA(double _A) {
        this.A = _A;
    }

    public final double gett1() {
        return t1;
    }

    public void sett1(double _t1) {
        this.t1 = _t1;
    }

    public double getts() {
        return ts;
    }

    public void setts(double _ts) {
        this.ts = _ts;
    }

    public final double getd() {
        return d;
    }

    public void setd(double _d) {
        this.d = _d;
    }

    public double getT() {
        return T;
    }

    public void setT(double _T) {
        this.T = _T;
    }

    public double getKw() {
        return kw;
    }

    public void setKw(double _kw) {
        this.kw = _kw;
    }
   
}
