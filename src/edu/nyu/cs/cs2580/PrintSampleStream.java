/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.nyu.cs.cs2580;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.internal.org.json.JSONArray;

/**
 * <p>
 * This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 * 
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class PrintSampleStream {
	/**
	 * Main entry of this application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws TwitterException {

		ConfigurationBuilder builder = new ConfigurationBuilder();
		Configuration conf = builder
		    .setOAuthConsumerKey("b6nVOcUGuH6QL3Ir9hJA")
		    .setOAuthConsumerSecret("LazOATLOH3exmsmAIQAZELU5DNgrJpT772H1937Q0")
		    .setOAuthAccessToken(
		        "566829940-6FSE5geXB86ureaKPkYcWlqMNwjTQDWRI9XTiOu1")
		    .setOAuthAccessTokenSecret("pUCQ9RhyIwZw2at44EbfTeXA0YkKdh6Z2NVBtHc")
		    .build();

		Twitter unauthenticatedTwitter = new TwitterFactory(conf).getInstance();
		// First param of Paging() is the page number, second is the number per
		// page (this is capped around 200 I think.
		Paging paging = new Paging(1, 50);
		List<Status> statuses = unauthenticatedTwitter.getUserTimeline("cnnbrk",
		    paging);

		int i = 1;
		for (Status status : statuses) {
			URLEntity[] entities = status.getURLEntities();
			if (entities.length > 0) {
				URLEntity urls = entities[0];
				System.out.println(i + ": " + urls.getURL() + " "
				    + urls.getExpandedURL());
				System.out.println(status.getText());

				String text = status.getText().replaceAll("\\p{Punct}", "")
				    .replaceAll(" ", "_");

				String fileName = text;
				System.out.println("ID " + status.getId());
				System.out.println("Favorite " + getFavoriteCount(status.getId()));				
				System.out.println("RetweetCount " + status.getRetweetCount());
				int favoriteCount = getFavoriteCount(status.getId());
				int freshness = calculateFreshness(status.getCreatedAt());
				// Count of the number of slashes in the URL
				int countSlash = getSlashCount(urls.getExpandedURL());
				String features = status.getRetweetCount() + " " + freshness + " "
				    + status.getUser().getFollowersCount() + " " + countSlash + " " + favoriteCount;
//				DownloadTwitterFeatures.download(features, fileName);
//
//				Download.downloadWebpage(urls.getExpandedURL(), "C:/sem4/corpus/"
//				    + fileName + ".html");
				i++;
			}
		}

	}

	public static int getSlashCount(String expandedURL) {
		int index = -1;
		int count = 0;
		while ((index = expandedURL.indexOf('/', ++index)) != -1) {
			count++;
		}
		return count;
	}

	@SuppressWarnings("deprecation")
	private static int calculateFreshness(Date createdAt) {
		Date currentDate = Calendar.getInstance().getTime();
		Date yesterdayDate = currentDate;
		yesterdayDate.setDate(currentDate.getDate() - 1);

		Date daysBefore_3 = currentDate;
		daysBefore_3.setDate(currentDate.getDate() - 3);

		Date daysBefore_5 = currentDate;
		daysBefore_5.setDate(currentDate.getDate() - 5);

		Date daysBefore_7 = currentDate;
		daysBefore_7.setDate(currentDate.getDate() - 7);

		Date old = currentDate;
		old.setDate(currentDate.getDate() - 8);

		// System.out.println(yesterdayDate);
		// System.out.println(createdAt);
		if (createdAt.before(currentDate) && createdAt.after(yesterdayDate)) {
			return 10;
		} else if (createdAt.before(yesterdayDate) && createdAt.after(daysBefore_3)) {
			return 7;
		} else if (createdAt.before(daysBefore_3) && createdAt.after(daysBefore_5)) {
			return 5;
		} else if (createdAt.before(daysBefore_5) && createdAt.after(daysBefore_7)) {
			return 3;
		} else {
			return 1;
		}
	}

	public static int getFavoriteCount(long id) {
		JSONArray array = null;
		String commonUrl = "http://api.twitter.com/1/statuses/show.json?id=";
		String finalUrl = commonUrl + id;
		BufferedReader br = null;
		int result = 0;
		try {
			URLConnection connection = new URL(finalUrl).openConnection();
			InputStream response = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(response));
			String jsonData = br.readLine();
			twitter4j.internal.org.json.JSONObject status = new twitter4j.internal.org.json.JSONObject(
			    jsonData);
//			System.out.println(status.getInt("favorite_count"));
			result = status.getInt("favorite_count");

		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Inside Catch");
		}
		return result;
	}
}