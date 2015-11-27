/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsControllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import cpsModels.AmplitudaModel;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author koperingnet
 */
public class XMLController {
    
    XStream xstream = new XStream(new DomDriver());
    ArrayList<AmplitudaModel> pktY = new ArrayList<>();
    String xmlFileName = "testFile";
    
    public void saveXML (AmplitudaModel pktY) {
        
        xstream.alias("apmplitude", AmplitudaModel.class);
        String toXML = xstream.toXML(pktY);
         try {
            try (PrintWriter out = new PrintWriter(xmlFileName)) {
                out.println(toXML);
            }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

    }
    
    public ArrayList<Double> load(String xmlFileName) throws FileNotFoundException {
      
        pktY = (ArrayList<AmplitudaModel>) xstream.fromXML(new FileReader(xmlFileName));
        
    return pktY.get(0).getAmp();  
    }
}
