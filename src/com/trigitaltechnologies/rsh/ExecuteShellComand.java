package com.trigitaltechnologies.rsh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class ExecuteShellComand {

	public static void main(String[] args) {

		ExecuteShellComand esc = new ExecuteShellComand();
		String command = "rsh -l root 125.99.127.30 shcm";
		String output = esc.executeCommand(command);
		System.out.println("Succesfully executed the command");
	}

	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();
		File file;
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			//System.out.println(p);
			
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";	
            file = new File("cm.txt");
            FileWriter fileWriter = new FileWriter(file);
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
				fileWriter.write(line+System.lineSeparator());
				
				System.out.println("+"+line);
			}
			fileWriter.flush();
			fileWriter.close();	
			p.destroy();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

}
