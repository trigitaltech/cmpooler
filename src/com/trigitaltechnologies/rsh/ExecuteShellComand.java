package com.trigitaltechnologies.rsh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecuteShellComand {

	public static void main(String[] args) {
		final ExecuteShellComand esc = new ExecuteShellComand();
		final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

		// Task to Run Goes Here
		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println("Process started");
				for (String cmtsIP : resourceBundle.getString("CMTS_IP").split(",")) {
					esc.executeCommand("rsh -l root "+cmtsIP+" shcm", cmtsIP);
					System.out.println("Process started");
				}
			}
		};

		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 10000, TimeUnit.MILLISECONDS);
		
	}

	private String executeCommand(String command, String cmtsIP) {

		StringBuffer output = new StringBuffer();
		File file;
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";
			file = new File("shcm_" + cmtsIP + ".txt");
			FileWriter fileWriter = new FileWriter(file);
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
				fileWriter.write(line + System.lineSeparator());
				System.out.println("+" + line);
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
