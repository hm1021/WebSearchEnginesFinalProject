package edu.nyu.cs.cs2580;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TrainingSetRegular {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if (args.length != 2) {
			System.err.println("<path to queries> <path to >");
		}

		File file_queries = new File(args[0]);
		FileReader fr = new FileReader(file_queries);
		BufferedReader br = new BufferedReader(fr);

		String line = null;
		while ((line = br.readLine()) != null) {
			String query = line.replaceAll(" ", "%20");

			List<String> commands = new ArrayList<String>();
			commands.add("/bin/bash");
			commands.add("-c");
			commands.add("http://localhost:25805/search?query=" + query
					+ "&ranker=favorite&format=text&numterms=10&numdocs=10");
			ProcessBuilder pb = new ProcessBuilder(commands);
			Process p = pb.start();
			BufferedReader ois = new BufferedReader(new InputStreamReader(p.getInputStream()));

		}

		br.close();
		fr.close();
	}

}
