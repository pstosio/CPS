/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsControllers;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author koperingnet
 */
public class Signal04 {
    
    private static final Random random = new Random();
    HelperController helper;
    HelperController.rodzaj_sygnalu _rodzaj = HelperController.rodzaj_sygnalu.DYSKRETNY;
    public Signal04(HelperController helper) {
        this.helper = helper;
    }
    
    /**
     * Sygnał sinusoidalny wyprostowany jednopołówkowo
     * 
     * @param t
     * @return 
     */
    public double sygnalS4(double t) {
        return (1.0 / 2.0)
                * ((helper.getA() * helper.sin2piTtminT1(t - helper.gett1())) + Math
                .abs(helper.sin2piTtminT1(t - helper.gett1())));
    }
    
    /**
     * Obliczanie wartości średniej
     *
     * @param punktyY
     * @return
     */
    public double obl_sredniawartosc(ArrayList<Double> punktyY) {
        int i;
        double srednia = 0;
        int size = 0;
        
                for (i = 0; i < punktyY.size(); i++) {
                    srednia = srednia + helper.getPunktzindexu(i, punktyY);
                }
                size = punktyY.size();
          
       
        return srednia / size;
    }

    
    /**
     * Obliczanie średniej wartości bezwzględnej
     * 
     * @param punktyY
     * @return 
     */
    public double obl_sredniawartoscbezwzgledna(ArrayList<Double> punktyY) {
        int i;
        double srednia = 0;
        double liczba = 0;
        
            for (i = 0; i < punktyY.size(); i++) {
                liczba = Math.abs(helper.getPunktzindexu(i, punktyY));
                srednia = srednia + liczba;
            }
        
        return srednia / punktyY.size();
    }

    /**
     * Obliczanie mocy średniej
     * 
     * @param punktyY
     * @return 
     */
    public double obl_mocsrednia(ArrayList<Double> punktyY) {
        int i;
        double moc = 0;
       
            for (i = 0; i < punktyY.size(); i++) {
                moc = moc + (helper.getPunktzindexu(i, punktyY) * helper.getPunktzindexu(i, punktyY));
            }
        
        return moc / punktyY.size();
    }
    
    /**
     * Obliczanie wartości skutecznej 
     * 
     * @param punktyY
     * @return 
     */
    public double obl_wartoscskuteczna(ArrayList<Double> punktyY) {
        int i;
        double moc = 0;
        
            for (i = 0; i < punktyY.size(); i++) {
                moc = moc + (helper.getPunktzindexu(i, punktyY) * helper.getPunktzindexu(i, punktyY));
            }
        
        return Math.sqrt(moc / punktyY.size());
    }

    /**
     * Obliczanie wariacji sygnału
     * 
     * @param punktyY
     * @return 
     */
    public double obl_wariancja(ArrayList<Double> punktyY) {
        int i;
        double wariancja = 0;
        
            for (i = 0; i < punktyY.size(); i++) {
                double _wartosc = helper.getPunktzindexu(i, punktyY)
                        - this.obl_sredniawartosc(punktyY);
                wariancja = wariancja + (_wartosc * _wartosc);
            }
        
        return wariancja / punktyY.size();
    }
}
