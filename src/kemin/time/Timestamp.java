package kemin.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamp {
	public String date;
	public long timestamp;
	
	public Timestamp(String date) {
		this.date = date;
		this.timestamp = this.date2Timestamp(this.date);
	}
	
    private long date2Timestamp(String dateString) {
        Date date;
        long temp = 0;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
			temp = date.getTime();//JAVA的时间戳长度是13位
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return temp;
    }
    
    public Timestamp(long timestamp) {
    	this.timestamp = timestamp;
    	this.date = this.timestamp2Date(this.timestamp);
    }
	
    private String timestamp2Date(long timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long unixLong = timestamp;
        String date = "";
        date = sdf.format(unixLong);
        return date;
    }
    
   /*
    * @Description: 从ts时间戳到本时间戳的相差秒数，即self-ts
    * @param ts
    * @return
    */
   public long from(Timestamp ts){
       return (this.timestamp - ts.timestamp)/1000; //JAVA的时间戳长度是13位
   }
   
   /*
    * @Description: 从本时间戳到ts时间戳的相差秒数，即ts-self
    * @param ts
    * @return
    */
   public long to(Timestamp ts){
       return (ts.timestamp - this.timestamp)/1000; //JAVA的时间戳长度是13位
   }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String t1 = "2014-03-10 14:54:33";
		long current = System.currentTimeMillis();
		
		Timestamp ts = new Timestamp(t1);
		Timestamp ts2 = new Timestamp(current);
		System.out.println(ts2.from(ts));
		SecondsDelta sd = new SecondsDelta(ts2.from(ts));
		System.out.println(sd);
	}

}
