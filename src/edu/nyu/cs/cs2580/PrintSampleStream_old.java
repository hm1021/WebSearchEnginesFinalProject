///*
// * Copyright 2007 Yusuke Yamamoto
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package edu.nyu.cs.cs2580;
//
//import java.util.List;
//
//import twitter4j.Paging;
//import twitter4j.Status;
//import twitter4j.Twitter;
//import twitter4j.TwitterException;
//import twitter4j.TwitterFactory;
//import twitter4j.URLEntity;
//import twitter4j.conf.Configuration;
//import twitter4j.conf.ConfigurationBuilder;
//import edu.nyu.cs.cs2580.*;
///**
// * <p>
// * This is a code example of Twitter4J Streaming API - sample method support.<br>
// * Usage: java twitter4j.examples.PrintSampleStream<br>
// * </p>
// * 
// * @author Yusuke Yamamoto - yusuke at mac.com
// */
//public final class PrintSampleStream_old {
//	/**
//	 * Main entry of this application.
//	 * 
//	 * @param args
//	 */
//	public static void maine(String[] args) throws TwitterException {
//
//		ConfigurationBuilder builder = new ConfigurationBuilder();
//		Configuration conf = builder.setOAuthConsumerKey("b6nVOcUGuH6QL3Ir9hJA")
//				.setOAuthConsumerSecret("LazOATLOH3exmsmAIQAZELU5DNgrJpT772H1937Q0")
//				.setOAuthAccessToken("566829940-6FSE5geXB86ureaKPkYcWlqMNwjTQDWRI9XTiOu1")
//				.setOAuthAccessTokenSecret("pUCQ9RhyIwZw2at44EbfTeXA0YkKdh6Z2NVBtHc").build();
//
//		Twitter unauthenticatedTwitter = new TwitterFactory(conf).getInstance();
//		// First param of Paging() is the page number, second is the number per
//		// page (this is capped around 200 I think.
//		Paging paging = new Paging(6, 200);
//		List<Status> statuses = unauthenticatedTwitter.getUserTimeline("cnnbrk", paging);
//
//		int i = 1;
//		for (Status status : statuses) {
//			URLEntity[] entities = status.getURLEntities();
//			status.isFavorited();
//			if (entities.length > 0) {
//				URLEntity urls = entities[0];
//				System.out.println(i + ": " + urls.getURL() + " " + urls.getExpandedURL());
//				System.out.println(status.getText());
//
//				String text = status.getText().replaceAll("\\p{Punct}", "").replaceAll(" ", "_");
//
//				String fileName = text;
//
//				Download.downloadWebpage(urls.getExpandedURL(), "/Users/hiral/Documents/WSE/FinalProject/corpus/"
//						+ fileName + ".html");
//				i++;
//			}
//		}
//
//	}
//}