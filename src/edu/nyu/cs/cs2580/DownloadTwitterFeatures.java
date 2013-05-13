package edu.nyu.cs.cs2580;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DownloadTwitterFeatures {
	static int count = 0;

	public static void download(String features, String fileName) {
		// Map which maps the file name to the docid(the name of the file the
		// feature sare stired in).
		Map<String, Integer> documentMap = new HashMap<String, Integer>();
		documentMap.put(fileName, ++count);
		// features format: retweetCount + freshness + getFollowersCount +
		// slashCounts of the Url + favoriteCount
		String line = features + "";

		try {
			BufferedWriter aoos = new BufferedWriter(new FileWriter(
			    "C:/sem4/twitter_features/" + count + ".ftr", true));
			aoos.write(line);
			aoos.newLine();
			aoos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
}
