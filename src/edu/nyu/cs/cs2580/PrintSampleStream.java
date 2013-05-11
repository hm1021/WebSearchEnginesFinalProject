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

import java.util.List;

import twitter4j.Paging;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.URLEntity;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

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
		Configuration conf = builder.setOAuthConsumerKey("b6nVOcUGuH6QL3Ir9hJA")
				.setOAuthConsumerSecret("LazOATLOH3exmsmAIQAZELU5DNgrJpT772H1937Q0")
				.setOAuthAccessToken("566829940-6FSE5geXB86ureaKPkYcWlqMNwjTQDWRI9XTiOu1")
				.setOAuthAccessTokenSecret("pUCQ9RhyIwZw2at44EbfTeXA0YkKdh6Z2NVBtHc").build();
		TwitterStream twitterStream = new TwitterStreamFactory(conf).getInstance();

		StatusListener listener = new StatusListener() {
			@Override
			public void onStatus(Status status) {
				System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				System.out.println("Got stall warning:" + warning);
			}

			@Override
			public void onException(Exception ex) {
				ex.printStackTrace();
			}
		};

		// twitterStream.addListener(listener);
		// FilterQuery query = new FilterQuery();
		// long cnnbrk = 428333l;
		// long[] following = new long[]{cnnbrk};
		// query.follow(following);
		// twitterStream.filter(query);
		// twitterStream.site(true, following);

		Twitter unauthenticatedTwitter = new TwitterFactory(conf).getInstance();
		// First param of Paging() is the page number, second is the number per
		// page (this is capped around 200 I think.
		Paging paging = new Paging(1, 200);
		List<Status> statuses = unauthenticatedTwitter.getUserTimeline("cnnbrk", paging);
		
		int i = 1;
		for (Status status : statuses) {
			URLEntity[] entities = status.getURLEntities();
			if (entities.length > 0) {
				URLEntity urls = entities[0];
				System.out.println(i + ": " + urls.getURL() + " " + urls.getExpandedURL());
				String fileName = status.getText().split(".")[0];
				Download.downloadWebpage(urls.getExpandedURL(), "/Users/hiral/Documents/WSE/FinalProject/corpus/" + fileName);
				i++;
			}
		}

	}

}