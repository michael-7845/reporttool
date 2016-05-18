package kemin.time;

public class SecondsDelta {
	public long day;
	public long hour;
	public long minute;
	public long second;
	
	public long seconds;
	
	public SecondsDelta(long seconds) {
		this.seconds = seconds;
		toTime();
	}
	
	private void toTime() {
		long s = this.seconds;
		this.day = s/(3600*24);
	    s = s%(3600*24);
		this.hour = s/3600;
	    s = s%3600;
	    this.minute = s/60;
	    s = s%60;
	    this.second = s;
	}
	
	public SecondsDelta(long day, long hour, long minute, long second) {
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		toSeconds();
	}
	
	private void toSeconds() {
		this.seconds = (3600*24*this.day) + (3600*this.hour) +
				(60*this.minute) + this.second;
	}
	
	public int compare(SecondsDelta s) {
		if(this.seconds > s.seconds) {
			return 1;
		}else if(this.seconds < s.seconds) {
			return -1;
		} else {
			return 0;
		}
	}

	
	public String toString() {
		return String.format("%d天%d小时%d分%d秒", this.day, this.hour, this.minute, this.second);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SecondsDelta st = new SecondsDelta(5266);
		System.out.println(st);
		
		SecondsDelta st2 = new SecondsDelta(0, 1, 27, 45);
		System.out.println(st2);
		
		System.out.println(st.compare(st2));
	}

}
