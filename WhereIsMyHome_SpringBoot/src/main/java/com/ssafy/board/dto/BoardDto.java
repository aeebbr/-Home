package com.ssafy.board.dto;

public class BoardDto {
	private int pg;
	private int spp;
	private int start;
	private String key;
	private String word;
	
	public BoardDto() {
		pg = 1;
		spp = 10;
	}

	public int getPg() {
		return pg;
	}

	public void setPg(int pg) {
		pg = pg == 0 ? 1 : pg;
		this.pg = pg;
	}

	public int getSpp() {
		return spp;
	}

	public void setSpp(int spp) {
		this.spp = spp;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
		
	}

	@Override
	public String toString() {
		return "BoardDto [pg=" + pg + ", spp=" + spp + ", start=" + start + ", key=" + key + ", word=" + word + "]";
	}
}
