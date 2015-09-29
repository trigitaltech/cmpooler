package com.trigitaltechnologies.rsh;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    Properties props = new Properties ();
    FileInputStream fis;
    public PropertyManager(){
        try {
            loadProps();
        } catch (IOException e) {
        	System.out.println("Something Bad Happened : "+e.toString());	
        } finally {
        	try{
        		if(fis != null){
        			fis.close();
        		}
        	} catch(IOException e){
        		System.out.println("Something Bad Happened : "+e.toString());
        	}
        }
    }
 
    private void loadProps() throws IOException{
    	fis = new FileInputStream("/home/expan/Properties.ini");
        props.load (fis); 
    }
 
    public String getProp(String key){
        return props.getProperty(key);
    }
}