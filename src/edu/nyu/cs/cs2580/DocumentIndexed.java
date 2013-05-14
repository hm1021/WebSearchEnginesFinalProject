package edu.nyu.cs.cs2580;

import java.util.HashMap;

/**
 * @CS2580: implement this class for HW2 to incorporate any additional
 *          information needed for your favorite ranker.
 */
public class DocumentIndexed extends Document {
	private static final long serialVersionUID = 9184892508124423115L;
	private long _totalWords = 0;
	private long _numWordsInTitle = 0;
	private long _numWordsInBody = 0;
	private HashMap<String, Integer> wordFrequency = new HashMap<String, Integer>();
	private HashMap<String, Integer> titleWordFrequency = new HashMap<String, Integer>();
	private HashMap<String, Integer> bodyWordFrequency = new HashMap<String, Integer>();

	public HashMap<String, Integer> getTitleWordFrequency() {
		return titleWordFrequency;
	}

	public void setTitleWordFrequency(HashMap<String, Integer> titleWordFrequency) {
		this.titleWordFrequency = titleWordFrequency;
	}

	public HashMap<String, Integer> getBodyWordFrequency() {
		return bodyWordFrequency;
	}

	public void setBodyWordFrequency(HashMap<String, Integer> bodyWordFrequency) {
		this.bodyWordFrequency = bodyWordFrequency;
	}

	public HashMap<String, Integer> getWordFrequency() {
		return wordFrequency;
	}

	public void setWordFrequency(HashMap<String, Integer> wordFrequency) {
		this.wordFrequency = wordFrequency;
	}

	public DocumentIndexed(int docid) {
		super(docid);
	}

	public long getTotalWords() {
		return _totalWords;
	}

	public void setTotalWords(long totalWords) {
		this._totalWords = totalWords;
	}

	public long getTotalWordsInTitle() {
		return _numWordsInTitle;
	}

	public void setTotalWordsInTitle(long totalWords) {
		this._numWordsInTitle = totalWords;
	}

	public long getTotalWordsInBody() {
		return _numWordsInBody;
	}

	public void setTotalWordsInBody(long totalWords) {
		this._numWordsInBody = totalWords;
	}

	public void incrementWordFrequency(String word) {

		if (wordFrequency.containsKey(word)) {
			int i = wordFrequency.get(word);
			i++;
			wordFrequency.put(word, i);
		} else {
			wordFrequency.put(word, 1);
		}
	}

	public int getWordFrequencyOf(String word) {
		if (wordFrequency.containsKey(word))
			return wordFrequency.get(word);
		else
			return 0;
	}

	public void incrementTitleWordFrequency(String word) {

		if (titleWordFrequency.containsKey(word)) {
			int i = titleWordFrequency.get(word);
			i++;
			titleWordFrequency.put(word, i);
		} else {
			titleWordFrequency.put(word, 1);
		}
	}

	public int getWordFrequencyOfTitle(String word) {
		if (titleWordFrequency.containsKey(word))
			return titleWordFrequency.get(word);
		else
			return 0;
	}

	public void incrementBodyWordFrequency(String word) {

		if (bodyWordFrequency.containsKey(word)) {
			int i = bodyWordFrequency.get(word);
			i++;
			bodyWordFrequency.put(word, i);
		} else {
			bodyWordFrequency.put(word, 1);
		}
	}

	public int getWordFrequencyOfBody(String word) {
		if (bodyWordFrequency.containsKey(word))
			return bodyWordFrequency.get(word);
		else
			return 0;
	}
}
