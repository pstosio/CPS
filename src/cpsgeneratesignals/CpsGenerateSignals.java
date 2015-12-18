/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsgeneratesignals;

import cpsControllers.PropertiesController;
import cpsViews.MainWindow;
import cpsViews.ParamsForm;

/**
 *
 * @author koperingnet
 */
public class CpsGenerateSignals {

    static MainWindow  mainWindow = new MainWindow();
    static ParamsForm  paramsForm = new ParamsForm();
    static PropertiesController pc = new PropertiesController();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello Apps");
        mainWindow.show(true);
        
        // title of windows
        mainWindow.setTitle("Cyfrowe Przetwarzanie Sygnału by Koprowski && Stosio && Szczygieł");
        paramsForm.setTitle("Generuj sygnał");
        
        
    }
    
}
