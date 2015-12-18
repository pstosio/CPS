/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartPanel;

public class ConversionController {
    HelperController helper;
    LineChartController lcc;
    ChartPanel lccPanel;
    
    public ArrayList<Double> sygSprobkowanyX = new ArrayList<>();
    public ArrayList<Double> sygSprobkowanyY = new ArrayList<>();
    public ArrayList<Double> sygSkwantowanyX = new ArrayList<Double>();
    public ArrayList<Double> sygSkwantowanyY = new ArrayList<Double>();
    public ArrayList<Double> zrekonstuowanyX = new ArrayList<Double>();
    public ArrayList<Double> zrekonstuowanyY = new ArrayList<Double>();
    public ArrayList<Double> sincX = new ArrayList<Double>();
    public ArrayList<Double> sincY = new ArrayList<Double>();
    public ArrayList<Double> prostokatnyX = new ArrayList<Double>();
    public ArrayList<Double> prostokatnyY = new ArrayList<Double>();
    ArrayList<Double> splotX = new ArrayList<Double>();
    ArrayList<Double> splotY = new ArrayList<Double>();
    ArrayList<Double> korelacjaX = new ArrayList<Double>();
    ArrayList<Double> korelacjaY = new ArrayList<Double>();
    ArrayList<Double> dolnoprzepustowyX = new ArrayList<Double>();
    ArrayList<Double> dolnoprzepustowyY = new ArrayList<Double>();
    ArrayList<Double> gornoprzepustowyX = new ArrayList<Double>();
    ArrayList<Double> gornoprzepustowyY = new ArrayList<Double>();
//ArrayList<Double> transformataX = new ArrayList<Double>();
//ArrayList<Double> trnasformataY = new ArrayList<Double>();
//ArrayList<Double> transformataSzybkaX = new ArrayList<Double>();
//ArrayList<Double> transformataSzybkaY = new ArrayList<Double>();
    double iloscProbek;
    double iloscPoziomowKwantyzacji;
    double czasTrwania;
    private double pi = 3.14;

    public ConversionController() {
    }

    public ConversionController(double probki, double poziomy, double czas) {
        iloscProbek = probki;
        iloscPoziomowKwantyzacji = poziomy;
        czasTrwania = czas;
    }
    
    /**
     * Błąd średniokwadratowy (MSE, ang. <i>Mean Squared Error</i>)
     *     
     * @param punktyY
     * @param _doPorownania
     *     
    * @return
     */
    public double obl_MSE(ArrayList<Double> punktyY, ArrayList<Double> _doPorownania) {
        double wynik = 0;
        try {
            if (!punktyY.isEmpty() && !_doPorownania.isEmpty()) {
                for (int i = 0; i < _doPorownania.size(); i++) {
                    wynik = wynik + (punktyY.get(i) - _doPorownania.get(i))
                            * (punktyY.get(i) - _doPorownania.get(i));
                }
                wynik = (1.0D / _doPorownania.size()) * wynik;
            } else {
                if (punktyY.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Brak sygnału.", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                } else if (_doPorownania.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Brak konwersji sygnału.", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception exc_MSE) {
//            JOptionPane.showMessageDialog(null, "Nie można obliczyć:\n" + exc_MSE.getMessage(),
//                    "Błąd", JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println("MSE = " + wynik);
        return wynik;
    }

    /**
     * Stosunek sygnał - szum (SNR, ang. <i>Signal to Noise Ratio</i>)
     *     
     * @param punktyY
* @return
     */
    public double obl_SNR(ArrayList<Double> punktyY, ArrayList<Double> _doPorownania) {
        double wynik = 0;
        try {
            if (!punktyY.isEmpty() && !_doPorownania.isEmpty()) {
                double licznik = 0, mianownik = 0;
                for (int i = 0; i < _doPorownania.size(); i++) {
                    licznik = licznik
                            + (punktyY.get(i) * punktyY.get(i));
                }
                for (int i = 0; i < _doPorownania.size(); i++) {
                    mianownik = mianownik
                            + (punktyY.get(i) - _doPorownania.get(i))
                            * (punktyY.get(i) - _doPorownania.get(i));
                }
                wynik = licznik / mianownik;
                wynik = 10.0D * Math.log10(wynik);
            } else {
                if (punktyY.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Brak sygnału.", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                } else if (_doPorownania.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Brak konwersji sygnału.", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception exc_MSE) {
//            JOptionPane.showMessageDialog(null, "Nie można obliczyć:\n" + exc_MSE.getMessage(),
//                    "Błąd", JOptionPane.ERROR_MESSAGE);
            wynik = -1;
        }
        
        System.out.println("SNR = " + wynik);
        return wynik;
    }

    /**
     * Szczytowy stosunek sygnał - szum (PSNR, ang. <i>Peak Signal to Noise
     * Ratio</i>)
     *     
* @return
     */
    public double obl_PSNR(ArrayList<Double> pynktyY, ArrayList<Double> _doPorownania) {
        double wynik = 0;
        try {
            if (!pynktyY.isEmpty() && !_doPorownania.isEmpty()) {
                double licznik = pynktyY.get(0), mianownik = 0;
                for (int i = 1; i < _doPorownania.size(); i++) {
                    if (licznik < pynktyY.get(i)) {
                        licznik = pynktyY.get(i);
                    }
                }
                mianownik = this.obl_MSE(pynktyY, _doPorownania);
                wynik = licznik / mianownik;
                wynik = 10.0D * Math.log10(wynik);
            } else {
                if (pynktyY.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Brak sygnału.", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                } else if (_doPorownania.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Brak zapisanej konwersji sygnału.",
                            "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception exc_MSE) {
//            JOptionPane.showMessageDialog(null, "Nie można obliczyć:\n" + exc_MSE.getMessage(),
//                    "Błąd", JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println("PSNR = " + wynik);
        return wynik;
    }

    
    /**
     * Maksymalna różnica (MD, ang. <i>Maximum Difference</i>)
     *     
     * @return
     */
    public double obl_MD(ArrayList<Double> punktyY, ArrayList<Double> _doPorownania) {
        double wynik = 0;
        try {
            if (!punktyY.isEmpty() && !_doPorownania.isEmpty()) {
                double tmp;
                wynik = Math.abs(punktyY.get(0) - _doPorownania.get(0));
                for (int i = 1; i < _doPorownania.size(); i++) {
                    tmp = Math.abs(punktyY.get(i) - _doPorownania.get(i));
                    if (wynik < tmp) {
                        wynik = tmp;
                    }
                }
            } else {
                if (punktyY.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Brak sygnału.", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                } else if (_doPorownania.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Brak konwersji sygnału.", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception exc_MSE) {
//            JOptionPane.showMessageDialog(null, "Nie można obliczyć:\n" + exc_MSE.getMessage(),
//                    "Błąd", JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println("MD = " + wynik);
        return wynik;
    }
    

    public ArrayList<Double> probkowanie(ArrayList<Integer> xSygnalu, ArrayList<Double> ySygnalu) {
        int rozmiarSygnalu = xSygnalu.size();
        int deltaN = rozmiarSygnalu / (int) iloscProbek;
//================================================================
// ------------------PRÓBKOWANIE 2--------------------------------
//================================================================
        for (double i = rounding(xSygnalu.get(0)); rounding(i) < rounding(xSygnalu.size()); i = i + deltaN) {
            Double tempX, tempY;
            tempX = rounding(i);
            for (int a = 0; a < xSygnalu.size(); a++) {
                if (a == rounding(i)) {
                    sygSprobkowanyX.add(rounding(xSygnalu.get(a)));
                    sygSprobkowanyY.add(rounding(ySygnalu.get(a)));
                }
//                System.out.println("probkowanie");
            }
        }
//         System.out.print(sygSprobkowanyX);
//        System.out.println("||");
        return sygSprobkowanyY;
    }
//MOJE PRÓBKOWANIE

    public ArrayList<Double> probkowanie2(ArrayList<Integer> xSygnalu, ArrayList<Double> ySygnalu) {
        int rozmiarSygnalu = xSygnalu.size();
        int deltaN = rozmiarSygnalu / (int) iloscProbek;
        for (int i = 0; i < (rozmiarSygnalu - deltaN + 1 / (2 * deltaN)); i = i + deltaN) {
//            sygSprobkowanyX.add((xSygnalu.get(i)));
            sygSprobkowanyY.add((ySygnalu.get(i)));
        }
        return sygSprobkowanyY;
    }
//=============================================================
//-------------------KWANTYZACJA-------------------------------
//=============================================================

    public ArrayList<Double> kwantyzacjaZobcieciem(ArrayList<Double> sygSprobkowanyY) {
//List<Double[]> results = signal;
//List<Double[]> results2 = new ArrayList<Double[]>();
        double max = sygSprobkowanyY.get(0);
        double min = sygSprobkowanyY.get(0);
        for (int i = 0; i < sygSprobkowanyY.size(); i++) {
            if (max < sygSprobkowanyY.get(i)) {
                max = sygSprobkowanyY.get(i);
            }
            if (min > sygSprobkowanyY.get(i)) {
                min = sygSprobkowanyY.get(i);
            }
        }
        double sub = max - min;
        TreeSet<Double> treeset = new TreeSet<Double>();
        for (int i = 0; i < iloscPoziomowKwantyzacji; i++) {
            treeset.add(min + ((sub / (iloscPoziomowKwantyzacji)) * i));
        }
        for (int i = 0; i < sygSprobkowanyY.size(); i++) {
            Double tempX, tempY;
//            tempX = sygSprobkowanyX.get(i);
            tempY = treeset.floor(sygSprobkowanyY.get(i));
//            sygSkwantowanyX.add(tempX);
            sygSkwantowanyY.add(tempY);
        }
        return sygSkwantowanyY;
    }
//=============================================================
//----------------Ekstrapolacja zerowego rzędu-----------------
//=============================================================

    public ArrayList<Double> ekstrapolacja(ArrayList<Double> sygSprobkowanyY, double frequency) {//ArrayList <Double> xSygnalu){
        double przedzialCzasowy = sygSprobkowanyX.get(1) - sygSprobkowanyX.get(0);
        double ilosc = sygSprobkowanyX.get(sygSprobkowanyX.size() - 1) * frequency;
        double iloscPrzedzialow = sygSprobkowanyX.get(sygSprobkowanyX.size() - 1) / przedzialCzasowy;
        int probekNAprzedzial = (int) ilosc / (int) iloscPrzedzialow;
        int licz = 0, licz2 = 0;
        for (double t = sygSprobkowanyX.get(0); t <= sygSprobkowanyX.get(sygSprobkowanyX.size() - 1) + (1.0 / (1.5 * frequency)); t += (1 / frequency)) {
            if (licz == probekNAprzedzial) {
                licz = 0;
                licz2++;
            }
            zrekonstuowanyX.add(t);
            zrekonstuowanyY.add(sygSprobkowanyY.get(licz2));
            licz++;
        }
//System.out.println("ilosc "+sygSprobkowanyX.get(sygSprobkowanyX.size()-1));
//System.out.println("probki "+sygSprobkowanyX.get(sygSprobkowanyX.size()-1) * frequency);
//System.out.println("probki na przedział "+probekNAprzedzial);
//System.out.println("przedział czasowy "+przedzialCzasowy);
//System.out.println(" "+iloscPrzedzialow);
//System.out.println(zrekonstuowanyY.size()+" dlugosc rekonstrkcji");
//System.out.println(xSygnalu.size()+" dlugosc oryginału");
        return zrekonstuowanyY;
    }
//=============================================================
// --------------------- SINC ---------------------------------
//=============================================================

    public void rekonstrukcjaSinc(double frequency) {
//int rozmiarSygnalu = sygSprobkowanyX.size();
//int deltaN = rozmiarSygnalu / (int)iloscProbek;
        double deltaN = czasTrwania / iloscProbek;
        double sum = 0;
        for (double t = sygSprobkowanyX.get(0); t <= sygSprobkowanyX.get(sygSprobkowanyX.size() - 1) + (1.0 / (1.5 * frequency)); t += (1 / frequency)) {
            sum = 0;
            for (int i = 0; i < sygSprobkowanyX.size(); i++) {
                double arg = t / deltaN - i;
                sum += sygSprobkowanyY.get(i) * sinc(arg);
            }
            sincX.add(t);
            sincY.add(sum);
        }
//System.out.println(sincX.size()+"dlugosc");
    }

    private double sinc(double t) {
        if (t == 0) {
            return 1;
        } else {
            return Math.sin(Math.PI * t) / (Math.PI * t);
        }
    }
//=============================================================
// --------------------- SPLOT ---------------------------------
//=============================================================

    public ChartPanel splot(ArrayList<Integer> x, ArrayList<Double> y, ArrayList<Integer> x1, ArrayList<Double> y1) {
        int size = x.size() + x1.size() - 1;
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum = 0;
            for (int j = 0; j < x.size(); j++) {
                if ((i - j) >= 0 && (i - j) < x.size()) {
//                System.out.println("in");
                    try {
                        sum += y.get(j) * y1.get(i - j);
                    } catch (Exception e) {
                    }
                }
//                System.out.println(j);

            }
            if (i * 0.01 < 0.5) {
                splotX.add(i * 0.01);
                splotY.add(sum);
            }
        }

        
        lcc= new LineChartController();
        lccPanel = new ChartPanel(lcc.printChart2(splotX, splotY));
        
        return lccPanel;
    }
//=============================================================
// --------------------- KORELACJA ----------------------------
//=============================================================

    public ChartPanel korelacja(ArrayList<Integer> Hx, ArrayList<Double> Hy, ArrayList<Integer> Sx, ArrayList<Double> Sy) {
        int size = Hx.size() + Sx.size() - 1;
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum = 0;
            for (int j = 0; j < Hx.size(); j++) {
                try {
                    sum += Hy.get(j) * Sy.get(j - i);
                } catch (Exception e) {
                }
            }
            korelacjaX.add(i * 0.01);
            korelacjaY.add(sum);
        }
        
        lcc= new LineChartController();
        lccPanel = new ChartPanel(lcc.printChart2(korelacjaX, korelacjaY));
        
        return lccPanel;
        
//System.out.println(korelacjaX);
//System.out.println(korelacjaY);
/*
         for (int i=0; i<Sx.size(); i++)
         {
         Double tempX, tempY;
         tempX = Sx.get(i).doubleValue();
         tempY = Sy.get(i).doubleValue();
         korelacjaX.add(tempX);
         korelacjaY.add(tempY);
         }
         for (int i=0; i<Hx.size(); i++)
         {
         Double tempX, tempY;
         tempX = Hx.get(i).doubleValue();
         tempY = Hy.get(i).doubleValue();
         x.add(temp);
         }
         return f.correlation(h, x);
         */
    }
//===================================================================
// ------------------------- TRANSFORMATA DFT ----------------------
//===================================================================

//    public List<ComplexPoint> discreteFourierTransform(ArrayList<Double> xSygnalu, ArrayList<Double> ySygnalu, double fp) {
//        List<Double[]> signal = new ArrayList<Double[]>();
//        List<ComplexPoint> transform = new ArrayList<ComplexPoint>();
//        for (int i = 0; i < xSygnalu.size(); i++) {
//            Double[] temp = new Double[2];
//            temp[0] = xSygnalu.get(i).doubleValue();
//            temp[1] = ySygnalu.get(i).doubleValue();
//            signal.add(temp);
//        }
//        long startTime = System.nanoTime();
//        for (int m = 0; m < signal.size(); m++) {
//            transform.add(discreteFourierTransformValue(signal, m, fp));
////transformataX.add(e)
//        }
//        long stopTime = System.nanoTime();
//        System.out.println("Discret Fourier " + (long) ((stopTime - startTime)));
//        return transform;
//    }
//===================================================================
// ------------------------- TRANSFORMATA FFT ----------------------
//===================================================================

//    public List<ComplexPoint> fastDiscreteFourierTransform(ArrayList<Double> xSygnalu, ArrayList<Double> ySygnalu, double fp) {
//        double a = 1.7;
//        List<Double[]> signal = new ArrayList<Double[]>();
//        List<ComplexPoint> transform = new ArrayList<ComplexPoint>();
//        for (int i = 0; i < xSygnalu.size(); i++) {
//            Double[] temp = new Double[2];
//            temp[0] = xSygnalu.get(i).doubleValue();
//            temp[1] = ySygnalu.get(i).doubleValue();
//            signal.add(temp);
//        }
//        signal = changeSamplesPositions(signal);
//// for (int m=0; m<signal.size(); m++)
//// {
//// transform.add(fastDiscreteFourierTransformValue(signal, m, fp));
//// }
//        long startTime = System.nanoTime();
//        transform = fastDiscreteFourierTransformValue(signal, fp);
//        long stopTime = System.nanoTime();
//        System.out.println("Fast Fourier " + (long) ((stopTime - startTime) / a));
//        return transform;
//    }
//===================================================================
// ------------------ TRANSFORMATA Hadamarda -----------------------
//===================================================================

//    public List<Double[]> walshHadamard(ArrayList<Double> xSygnalu, ArrayList<Double> ySygnalu) {
//        List<Double[]> signal = new ArrayList<Double[]>();
//        int m = (int) (Math.log(xSygnalu.size()) / Math.log(2));
//        List<Double[]> output = new ArrayList<Double[]>();
//        ArrayList<ArrayList<Integer>> hadamard = new ArrayList<ArrayList<Integer>>();
//        long startTime = System.nanoTime();
//        int[][] h = new int[1][1];
//        h[0][0] = 1;
//        int i = 1;
//        do {
//            h = hadamard(h, i, m);
//            i++;
//        } while (i <= m);
//        Double[][] hadamardMulti = new Double[h.length][h[0].length];
//        hadamardMulti = hadamardMultiply(h, rounding(Math.pow(1.0 / Math.sqrt(2), m)));
//        for (int k = 0; k < xSygnalu.size(); k++) {
//            Double[] temp = new Double[2];
//            temp[0] = xSygnalu.get(k).doubleValue();
//            temp[1] = ySygnalu.get(k).doubleValue();
//            signal.add(temp);
//        }
//        output = matrixMultiply(hadamardMulti, signal);
//        long stopTime = System.nanoTime();
//        System.out.println("Walsh Hadamard " + (stopTime - startTime));
//        return output;
//    }
//===================================================================
// -------------- SZYBKA TRANSFORMATA Hadamarda --------------------
//===================================================================

//    public List<Double[]> fastWalshHadamard(ArrayList<Double> xSygnalu, ArrayList<Double> ySygnalu) {
//        List<Double[]> signal = new ArrayList<Double[]>();
//        int m = (int) (Math.log(xSygnalu.size()) / Math.log(2));
//        List<Double[]> output = new ArrayList<Double[]>();
////ArrayList<ArrayList<Integer>> hadamard = new ArrayList<ArrayList<Integer>>();
//        for (int k = 0; k < xSygnalu.size(); k++) {
//            Double[] temp = new Double[2];
//            temp[0] = xSygnalu.get(k).doubleValue();
//            temp[1] = xSygnalu.get(k).doubleValue();
//            signal.add(temp);
//        }
//        List<Double[]> first = new ArrayList<Double[]>();
//        List<Double[]> second = new ArrayList<Double[]>();
//        for (int k = 0; k < signal.size() / 2; k++) {
//            first.add(signal.get(k));
//        }
//        for (int k = signal.size() / 2; k < signal.size(); k++) {
//            second.add(signal.get(k));
//        }
//        int[][] h = new int[1][1];
//        h[0][0] = 1;
//        int i = 1;
//        do {
//            h = hadamard(h, i, m);
//            i++;
//        } while (i <= m);
//        Double[][] hadamardMulti = new Double[h.length][h[0].length];
//        long startTime = System.nanoTime();
//        hadamardMulti = hadamardMultiply(h, rounding(Math.pow(1.0 / Math.sqrt(2), m)));
//        output = partOfMatrixMultiply(hadamardMulti, first, second);
//        long stopTime = System.nanoTime();
//        System.out.println("Fast Walsh Hadamard " + (stopTime - startTime));
//        return output;
//    }
//
//    private ComplexPoint discreteFourierTransformValue(List<Double[]> sig, int m, double fp) {
//        int nMax = sig.size();
//        double real = 0.0;
//        double complex = 0.0;
//        double f = fp / nMax;
//        for (int n = 0; n < nMax; n++) {
//            real += sig.get(n)[1] * Math.cos(2 * Math.PI * m * n / nMax);
//            complex += sig.get(n)[1] * (-Math.sin(2 * Math.PI * m * n / nMax));
//        }
////System.out.println(real + " " + complex);
//        return new ComplexPoint(real, complex, m * f);
//    }
//
//    private List<ComplexPoint> fastDiscreteFourierTransformValue(List<Double[]> sig, double fp) {
//// int nMax = sig.size();
//// double real = 0.0;
//// double complex = 0.0;
//// double f = fp/nMax;
////
//// List<ComplexPoint> complexList = new ArrayList<ComplexPoint>();
//        return discreteFastFourierTransformValue(sig, fp);
//    }
//
//    private List<ComplexPoint> discreteFastFourierTransformValue(List<Double[]> sig, double fp) {
//        int nMax = sig.size();
//        double real = 0.0;
//        double real2 = 0.0;
//        double complex = 0.0;
//        double complex2 = 0.0;
//        double f = fp / nMax;
//        List<Double[]> output = sig;
//        int rec = (int) (Math.log(sig.size()) / Math.log(2));
//        for (int m = 0; m < rec; m++) {
//            Double[][] matrix = new Double[2][2];
//            double wc = Math.cos(2 * Math.PI * -m / nMax);
//            double ws = Math.sin(2 * Math.PI * -m / nMax);
//            matrix[0][0] = 1.0;
//            matrix[0][1] = wc;
//            matrix[1][0] = 1.0;
//            matrix[1][1] = ws;
//            output = butterfly(output, matrix, (int) Math.pow(2, m));
//        }
//        for (int n = 0; n < nMax / 2; n++) {
//// real += sig.get(n)[1] + Math.cos(2 * Math.PI * -m * n / (nMax/2)) * sig.get(n+1)[1]
//// + Math.sin(2 * Math.PI * -m * n / (nMax/2));
//// complex += sig.get(n)[1] + Math.cos(2 * Math.PI * -m * n / (nMax/2)) * sig.get(n+1)[1] - sig.get(n+1)[1]
//// * Math.sin(2 * Math.PI * -m * n / (nMax/2));
//// real += sig.get(n)[1] * Math.cos(2 * Math.PI * -m * n / (nMax/2)) + Math.cos(2 * Math.PI * -m / nMax);
//// real2 += sig.get(n+1)[1] * Math.cos(2 * Math.PI * -m * (n+1) / (nMax/2));
//// complex += sig.get(n)[1] * (-Math.sin(2 * Math.PI * -m * n / (nMax/2))) + (-Math.sin(2 * Math.PI * -m / nMax));
//// complex2 += sig.get(n+1)[1] * (-Math.sin(2 * Math.PI * -m * (n+1) / (nMax/2)));
//// real += sig.get(n)[1] * Math.cos(2 * Math.PI * -m * n / (nMax));
//// real2 += sig.get(n+1)[1] * Math.cos(2 * Math.PI * -m * (n+1) / (nMax));
//// complex += sig.get(n+1)[1] * (-Math.sin(2 * Math.PI * -m * (n+1) / (nMax)));
//// complex2 += sig.get(n+1)[1] * (-Math.sin(2 * Math.PI * -m * (n+1) / (nMax)));
//            if (n == 0) {
//                n = 1;
//            }
//        }
//        List<ComplexPoint> cp = new ArrayList<ComplexPoint>();
//        for (int i = 0; i < output.size(); i++) {
//            cp.add(new ComplexPoint(output.get(i)[0], output.get(i)[1], fp));
//        }
//// real *= real2;
//// complex *= complex2;
//        return cp;
////return new ComplexPoint(real, complex, m*f);
//    }

    private int[][] hadamard(int[][] hadamard, int m, int mMax) {
        int[][] had = new int[hadamard.length * 2][hadamard[0].length * 2];
        for (int i = 0, ii = 0; i < had.length / 2; i++, ii++) {
            for (int j = 0, jj = 0; j < had[0].length / 2; j++, jj++) {
                had[i][j] = hadamard[ii][jj];
            }
        }
        for (int i = 0, ii = 0; i < had.length / 2; i++, ii++) {
            for (int j = had[0].length / 2, jj = 0; j < had[0].length; j++, jj++) {
                had[i][j] = hadamard[ii][jj];
            }
        }
        for (int i = had.length / 2, ii = 0; i < had.length; i++, ii++) {
            for (int j = 0, jj = 0; j < had[0].length / 2; j++, jj++) {
                had[i][j] = hadamard[ii][jj];
            }
        }
        for (int i = had.length / 2, ii = 0; i < had.length; i++, ii++) {
            for (int j = had[0].length / 2, jj = 0; j < had[0].length; j++, jj++) {
                had[i][j] = -hadamard[ii][jj];
            }
        }
        return had;
    }

    private Double[][] hadamardMultiply(int[][] hadamard, double multiplier) {
        Double[][] had = new Double[hadamard.length][hadamard[0].length];
        for (int i = 0; i < hadamard.length; i++) {
            for (int j = 0; j < hadamard[0].length; j++) {
                had[i][j] = hadamard[i][j] * multiplier;
            }
        }
        return had;
    }

    private List<Double[]> partOfMatrixMultiply(Double[][] hadamard, List<Double[]> first, List<Double[]> second) {
        List<Double[]> output = new ArrayList<Double[]>();
        for (int i = 0, ii = 0; i < hadamard.length / 2; i++, ii++) {
            double sum = 0.0;
            for (int j = 0, jj = 0; j < hadamard[0].length / 2; j++, jj++) {
                sum += hadamard[i][j] * (first.get(jj)[1] + second.get(jj)[1]);
            }
            Double[] temp = new Double[2];
            temp[0] = first.get(ii)[0];
            temp[1] = sum;
            output.add(temp);
        }
// for (int i=hadamard[0].length/2, ii=0; i<hadamard.length; i++, ii++)
// {
// double sum = 0.0;
// for (int j=hadamard[0].length/2, jj=0; j<hadamard[0].length; j++, jj++)
        for (int i = 0, ii = 0; i < hadamard.length / 2; i++, ii++) {
            double sum = 0.0;
            for (int j = 0, jj = 0; j < hadamard[0].length / 2; j++, jj++) {
                sum += hadamard[i][j] * (first.get(jj)[1] - second.get(jj)[1]);
            }
            Double[] temp = new Double[2];
            temp[0] = second.get(ii)[0];
            temp[1] = sum;
            output.add(temp);
        }
        return output;
    }
//====================================================================
// ------------ DOLNOPRZEPUSTOWY OKNO PROSTOKATNE --------------------
//===================================================================
//Sx, Sy - xi y sygnału
//m -
//fp - czestotliwośc próbkowania sygnału
//f0 - częstotliwośc obcięcia sygnału
// filtration = TRUE

    public ChartPanel dolnoPrzepustowyOknoProstokatne(ArrayList<Integer> Sx, ArrayList<Double> Sy, int m, double fp, double fo, boolean filtration) {
//List<Double[]> filter = new ArrayList<Double[]>();
        ArrayList<Double> Hx = new ArrayList<Double>();
        ArrayList<Double> Hy = new ArrayList<Double>();
        ArrayList<Double> x = new ArrayList<Double>();
        ArrayList<Double> y = new ArrayList<Double>();
        double k = fp / fo;
        for (int i = 0; i < m; i++) {
            Double tempX, tempY;
            tempX = (double) i;
            tempY = lowPassFilter(i, m, k);
            Hx.add(tempX);
            Hy.add(tempY);
        }
        if (filtration) {
            for (int i = 0; i < Sx.size(); i++) {
                Double tempX, tempY;
                tempX = Sx.get(i).doubleValue();
                tempY = Sy.get(i).doubleValue();
                x.add(tempX);
                y.add(tempY);
            }
//            splot(Hx, Hy, x, y);
            dolnoprzepustowyX = splotX;
            dolnoprzepustowyY = splotY;
//filter = f.splot(h, x);
        } else {
            dolnoprzepustowyX = Hx;
            dolnoprzepustowyY = Hy;
        }
        
        lcc= new LineChartController();
        lccPanel = new ChartPanel(lcc.printChart2(dolnoprzepustowyX, dolnoprzepustowyY));
        
        return lccPanel;
    }
//===================================================================
// ------------ GÓRNOPRZEPUSTOWY OKNO PROSTOKATNE -------------------
//===================================================================

    public ChartPanel gornoPrzepustowyOknoProstokatne(ArrayList<Integer> Sx, ArrayList<Double> Sy, int m, double fp, double fo, boolean filtration) {
//List<Double[]> filter = new ArrayList<Double[]>();
        ArrayList<Double> Hx = new ArrayList<Double>();
        ArrayList<Double> Hy = new ArrayList<Double>();
        ArrayList<Double> x = new ArrayList<Double>();
        ArrayList<Double> y = new ArrayList<Double>();
        double k = fp / fo;
        for (int i = 0; i < m; i++) {
            Double tempX, tempY;
            tempX = (double) i;
            tempY = lowPassFilter(i, m, k);
            tempY = tempY * Math.pow(-1.0, i);
            Hx.add(tempX);
            Hy.add(tempY);
        }
        if (filtration) {
            for (int i = 0; i < Sx.size(); i++) {
                Double tempX, tempY;
                tempX = Sx.get(i).doubleValue();
                tempY = Sy.get(i).doubleValue();
                x.add(tempX);
                y.add(tempY);
            }
            gornoprzepustowyX = splotX;
            gornoprzepustowyY = splotY;
        } else {
            gornoprzepustowyX = Hx;
            gornoprzepustowyY = Hy;
        }
        
        lcc= new LineChartController();
        lccPanel = new ChartPanel(lcc.printChart2(dolnoprzepustowyX, dolnoprzepustowyY));
        
        return lccPanel;
    }

    public double lowPassFilter(int n, int m, double k) {
        if (n == (m - 1) / 2) {
            return 2 / k;
        } else {
            double temp = Math.sin((2 * pi * (n - (m - 1) / 2)) / k);
// System.out.println(temp + " " + (temp/(pi * (n - (m - 1)/2 ))));
            return temp / (pi * (n - (m - 1) / 2));
        }
    }

    public double lowPassFilter(int n, double k) {
        if (n == 0) {
            return 2 / k;
        } else {
            double temp = Math.sin((2 * pi * n) / k);
            return temp / (pi * n);
        }
    }
//=============================================================
// ----------- OBLICZANIE BŁĘDÓW ITP --------------------------
//=============================================================

    public void czyscListy() {
        sygSprobkowanyX.clear();
        sygSprobkowanyY.clear();
        sygSkwantowanyX.clear();
        sygSkwantowanyY.clear();
        zrekonstuowanyX.clear();
        zrekonstuowanyY.clear();
        sincX.clear();
        sincY.clear();
    }

    public void wyswietl() {
        System.out.println("probkowanie");
        System.out.print(sygSprobkowanyX);
        System.out.println();
        System.out.print(sygSprobkowanyY);
        System.out.println();
        System.out.println("kwantyzacja");
        System.out.print(sygSkwantowanyX);
        System.out.println();
        System.out.print(sygSkwantowanyY);
        System.out.println();
        System.out.println("0 rzad");
        System.out.print(zrekonstuowanyX);
        System.out.println();
        System.out.print(zrekonstuowanyY);
        System.out.println();
        System.out.println("SINC");
        System.out.print(sincX);
        System.out.println();
        System.out.print(sincY);
        System.out.println("------------------------------------");
    }

    private double rounding(double x) {
        x = Math.round(x * 100);
        x /= 100;
        return x;
    }

    private List<Double[]> changeSamplesPositions(List<Double[]> sig) {
        List<Double[]> output1 = new ArrayList<Double[]>();
        List<Double[]> output2 = new ArrayList<Double[]>();
        List<Double[]> outputLast = new ArrayList<Double[]>();
        for (int i = 0; i < sig.size(); i = i + 2) {
            output1.add(sig.get(i));
        }
        if (output1.size() > 1) {
            output1 = changeSamplesPositions(output1);
        }
        for (int i = 1; i < sig.size(); i = i + 2) {
            output2.add(sig.get(i));
        }
        if (output2.size() > 1) {
            output2 = changeSamplesPositions(output2);
        }
        for (int i = 0; i < output1.size(); i++) {
            outputLast.add(output1.get(i));
        }
        for (int i = 0; i < output2.size(); i++) {
            outputLast.add(output2.get(i));
        }
        return outputLast;
    }

    private List<Double[]> butterfly(List<Double[]> sig, Double[][] matrix, int dist) {
        List<Double[]> temp = new ArrayList<Double[]>();
        List<Double[]> results = sig;
        List<Integer> indexes = new ArrayList<Integer>();
        Double[] tab = new Double[2];
        for (int i = 0; i < sig.size(); i++) {
            if (!indexes.contains(i)) {
                tab = matrixMultiply2(sig.get(i)[1], sig.get(i + dist)[1], matrix);
                indexes.add(i);
                indexes.add(i + dist);
                Double[] temp2 = new Double[2];
                temp2[0] = results.get(i)[0];
                temp2[1] = tab[0];
                results.set(i, tab);
                Double[] temp3 = new Double[2];
                temp3[0] = results.get(i + dist)[0];
                temp3[1] = tab[1];
                results.set(i, tab);
            }
        }
        return results;
    }

    private Double[] matrixMultiply2(double a, double b, Double[][] matrix) {
        Double[] temp = new Double[2];
        temp[0] = a;
        temp[1] = b;
        Double[] result = new Double[2];
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                sum += matrix[i][j] * temp[j];
            }
            result[i] = sum;
        }
        return result;
    }

    private List<Double[]> matrixMultiply(Double[][] hadamard, List<Double[]> signal) {
        List<Double[]> output = new ArrayList<Double[]>();
        for (int i = 0; i < hadamard.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < hadamard.length; j++) {
                sum += hadamard[i][j] * signal.get(j)[1];
            }
            Double[] temp = new Double[2];
            temp[0] = signal.get(i)[0];
            temp[1] = sum;
            output.add(temp);
        }
        return output;
    }
//------------------------------------------ 0 RZEDU PRZY POMOCY SPLOTU-----------------------------

    public void prostokatny() {
        prostokatnyX.add(0.0);
        prostokatnyY.add(0.0);
        prostokatnyX.add(0.0);
        prostokatnyY.add(1.0);
        for (double t = 0.01; t < 0.5 + 0.0001; t += 0.01) {
            double temp = Math.round(t * 100);
            temp /= 100;
            prostokatnyX.add(temp);
            prostokatnyY.add(1.0);
            /*else if(t==0){
             prostokatnyX.add(0.0);
             prostokatnyY.add(0.0);
             prostokatnyX.add(0.0);
             prostokatnyY.add(1.0);
             }
             /*else{
             /*prostokatnyX.add(0.5);
             prostokatnyY.add(1.0);*/
            /*prostokatnyX.add(0.5);
             prostokatnyY.add(0.0);*/
//}
        }
        prostokatnyX.add(0.5);
        prostokatnyY.add(0.0);
//i = rounding(i);
        System.out.println("prostokatny");
        System.out.println(prostokatnyX);
        System.out.println(prostokatnyY);
        /*sygSprobkowanyX.clear();
         sygSprobkowanyY.clear();
         for(double i=0.0;i<1;i+=0.01){
         sygSprobkowanyX.add(i);
         sygSprobkowanyY.add(Math.sin(i));
         }*/
        /*sygSprobkowanyX.clear();
         sygSprobkowanyY.clear();
         prostokatnyX.add(1.0);
         prostokatnyX.add(2.0);
         prostokatnyX.add(3.0);
         prostokatnyX.add(4.0);
         prostokatnyY.add(1.0);
         prostokatnyY.add(2.0);
         prostokatnyY.add(4.0);
         prostokatnyY.add(8.0);
         sygSprobkowanyX.add(1.0);
         sygSprobkowanyX.add(2.0);
         sygSprobkowanyX.add(3.0);
         sygSprobkowanyX.add(4.0);
         sygSprobkowanyY.add(2.0);
         sygSprobkowanyY.add(3.0);
         sygSprobkowanyY.add(4.0);
         sygSprobkowanyY.add(5.0);*/
                                    

//!!!!!!!!!!        splot(sygSprobkowanyX, sygSprobkowanyY, prostokatnyX, prostokatnyY);



//splot(prostokatnyX,prostokatnyY,sygSprobkowanyX,sygSprobkowanyY);
    }
    /*
     public void ekstrapolacja(double frequency)
     {
     double rozmiarSygnalu = sygSprobkowanyX.size();
     double deltaN = rozmiarSygnalu / iloscProbek;
     //List<Double[]> results = sampling(ts, signal);
     //List<Double[]> results2 = new ArrayList<Double[]>();
     //double sum = 0;
     int counter = 0;
     //int counter2 = 0;
     //double temp=0;
     //for (int t=0; t< sygSprobkowanyX.size(); t++){
     //if(sygSprobkowanyY.get(t) > temp)
     //temp=sygSprobkowanyY.get(t);
     //}
     //System.out.println("max"+temp);
     for (double t=sygSprobkowanyX.get(0); t<= sygSprobkowanyX.get(sygSprobkowanyX.size()-1) + 1/(2*frequency); t+=1/frequency){
     double tempX, tempY;
     double k=0;
     t=Math.round(t*100);
     t/=100;
     if (rounding(t) == sygSprobkowanyX.get(counter)){
     System.out.print(counter + "c");
     counter++;
     }
     tempX=t;
     tempY=sygSprobkowanyY.get(counter-1);
     System.out.print(tempY);
     */
    /*if (t >= deltaN * (k + 1) + sygSprobkowanyX.get(0)){
     k++;
     }
     //-----------------
     if( t >= (k * deltaN + sygSprobkowanyX.get(0)) && t < (deltaN * deltaN + k * deltaN + sygSprobkowanyX.get(0))){
     tempY=1;
     }
     else if( t >= (deltaN * deltaN - k * deltaN +sygSprobkowanyX.get(0)) && t < (deltaN + k * deltaN + sygSprobkowanyX.get(0))){
     tempY=0.0;;
     }
     else{
     tempY=0.0;
     }
     //i = rounding(i);*/
    /*
     zrekonstuowanyX.add(tempX);
     zrekonstuowanyY.add(tempY);
     }
     //splot(sygSprobkowanyX,sygSprobkowanyY,zrekonstuowanyX,zrekonstuowanyY);
     System.out.println("zrekonstruowany");
     System.out.println(zrekonstuowanyX);
     System.out.println(zrekonstuowanyY);
     //prostokatny();
     }
     */
 
    
}
