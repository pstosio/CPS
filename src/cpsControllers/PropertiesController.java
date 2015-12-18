/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsControllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author koperingnet
 */
public class PropertiesController {

    Properties prop = new Properties();
    OutputStream output = null;
    InputStream input = null;
    
    /**
     * Method set Properties.
     * 
     * @param fileoutput
     * @param propertyName
     * @param propertyValue 
     */
    public void setProperties(String fileoutput, String propertyName, String propertyValue) {

        try {

            output = new FileOutputStream(fileoutput);

            // set the properties value
            prop.setProperty(propertyName, propertyValue);

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    
    /**
     * Method get Properties.
     * 
     * @param fileinput
     * @param propertyName
     * @return 
     */
    public Object getProperties(String fileinput, String propertyName) {
        
        try {
                
    		input = new FileInputStream(fileinput);
    		if(input==null){
    	            System.out.println("Sorry, unable to find " + fileinput);
    		    return null;
    		}

    		//load a properties file from class path, inside static method
    		prop.load(input);
 
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        } finally{
        	if(input!=null){
        		try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
        }
        return prop.getProperty(propertyName);
    }
}
