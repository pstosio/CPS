/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsModels;

import java.util.ArrayList;

/**
 *
 * @author koperingnet
 */
public class AmplitudaModel {
    
    
    ArrayList <Integer> t;
    ArrayList<Double> amp;

    public AmplitudaModel(ArrayList<Integer> t, ArrayList<Double> amp) {
        this.t = t;
        this.amp = amp;
    }

    public ArrayList<Integer> getT() {
        return t;
    }

    public void setT(ArrayList<Integer> t) {
        this.t = t;
    }

    public ArrayList<Double> getAmp() {
        return amp;
    }

    public void setAmp(ArrayList<Double> amp) {
        this.amp = amp;
    }

    
    
}
