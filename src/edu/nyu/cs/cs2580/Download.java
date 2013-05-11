package edu.nyu.cs.cs2580;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Download {

	public static void downloadWebpage(String input, String absoluteFilePath) {
		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;

		try {
			url = new URL(input);
			is = url.openStream(); // throws an IOException
			br = new BufferedReader(new InputStreamReader(is));

			BufferedWriter aoos = new BufferedWriter(new FileWriter(absoluteFilePath, true));

			while ((line = br.readLine()) != null) {
				aoos.write(line);
				aoos.newLine();
			}
			
			aoos.close();
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				is.close();
				
			} catch (IOException ioe) {
				// nothing to see here
			}
		}
		
		
	}

}