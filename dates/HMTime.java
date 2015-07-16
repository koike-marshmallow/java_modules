class HMTime {
	private int minute;
	
	HMTime(int m0){
		setTime(m0);
	}
	
	HMTime(int h0, int m0){
		setTime(h0, m0);
	}
	
	HMTime(){
		setTime(0);
	}
	
	
	private int normMinute(int m0){
		return Math.min(Math.max(m0, 0), 59);
	}
	
	private int normTime(int t0){
		return Math.max(t0, 0);
	}
	
	
	void setTime(int m0){
		minute = normTime(m0);
	}
	
	void setTime(int h0, int m0){
		setTime(
			normTime(h0) * 60 + normMinute(m0));
	}
	
	void setMinute(int m0){
		minute = getHour() * 60;
		minute += normMinute(m0);
	}
	
	void setHour(int h0){
		minute = getMinute();
		minute += normTime(h0) * 60;
	}
	
	
	int getTime(){
		return minute;
	}
	
	int getMinute(){
		return minute % 60;
	}
	
	int getHour(){
		return minute / 60;
	}
	
	
	String getString(){
		return String.format("%d:%02d",
			getHour(), getMinute());
	}
	
	
	public String toString(){
		return "[" + super.toString() + " " + 
			String.format("\"%02d:%02d\"", getHour(), getMinute()) + "]";
	}
}
			
