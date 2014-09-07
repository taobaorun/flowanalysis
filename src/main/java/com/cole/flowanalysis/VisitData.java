package com.cole.flowanalysis;

public class VisitData {
	
	//ID	ApMacAddress	SourceMacAddress	Rssi	Time
	private String id;
	
	private String apMacAddress;
	
	private String sourceMacAddress;
	
	private String rssi;
	
	private String time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApMacAddress() {
		return apMacAddress;
	}

	public void setApMacAddress(String apMacAddress) {
		this.apMacAddress = apMacAddress;
	}

	public String getSourceMacAddress() {
		return sourceMacAddress;
	}

	public void setSourceMacAddress(String sourceMacAddress) {
		this.sourceMacAddress = sourceMacAddress;
	}

	public String getRssi() {
		return rssi;
	}

	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

}
