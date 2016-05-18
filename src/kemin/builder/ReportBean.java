package kemin.builder;

import java.util.ArrayList;
import java.util.List;

public class ReportBean {
	private double aType = 6.0; // A: >6.0
	private double bType = 4.9; // B: [4.9, 6.0)
	private double cType = 3.4; // C: [3.4, 4.9)
	                            // D: <3.4
	public double getaType() {
		return aType;
	}
	public void setaType(double aType) {
		this.aType = aType;
	}
	public double getbType() {
		return bType;
	}
	public void setbType(double bType) {
		this.bType = bType;
	}
	public double getcType() {
		return cType;
	}
	public void setcType(double cType) {
		this.cType = cType;
	}

	private double totalScore;
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	private String product = "";
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	
	private String vertype = "";
	public String getVertype() {
		return vertype;
	}
	public void setVertype(String vertype) {
		this.vertype = vertype;
	}

	private String version = "";
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
//		if(version == null) return;
		this.version = version;
	}
	
	private String author = "";
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	private String tester = "";
	public String getTester() {
		return tester;
	}
	public void setTester(String tester) {
		this.tester = tester;
	}
	
	private String rom = "";
	public String getRom() {
		return rom;
	}
	public void setRom(String rom) {
		this.rom = rom;
	}
	
	private String hardware = "";
	public String getHardware() {
		return hardware;
	}
	public void setHardware(String hardware) {
		this.hardware = hardware;
	}
	
	private String comments = "";
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	private String releaseDate = "";
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	private String testtype = "";
	public String getTesttype() {
		return testtype;
	}
	public void setTesttype(String testtype) {
		this.testtype = testtype;
	}
	
	private List<String> passedReleaseNotes = new ArrayList<String>();
	public List<String> getPassedReleaseNotes() {
		return passedReleaseNotes;
	}
	public void setPassedReleaseNotes(List<String> releaseNotes) {
		this.passedReleaseNotes = releaseNotes;
	}
	
	private List<String> failedReleaseNotes = new ArrayList<String>();
	public List<String> getFailedReleaseNotes() {
		return failedReleaseNotes;
	}
	public void setFailedReleaseNotes(List<String> releaseNotes) {
		this.failedReleaseNotes = releaseNotes;
	}
	
	private List<String> moreVersion = new ArrayList<String>();
	public List<String> getMoreVersion() {
		return moreVersion;
	}
	public void setMoreVersion(List<String> moreVersion) {
		this.moreVersion = moreVersion;
	}

	private String isReleased = "";
	public String getIsReleased() {
		return isReleased;
	}
	public void setIsReleased(String isReleased) {
		this.isReleased = isReleased;
	}
	
	private List<String> attention = new ArrayList<String>();
	public List<String> getAttention() {
		return attention;
	}
	public void setAttention(List<String> attention) {
		this.attention = attention;
	}
	
	public void printSelf() {
		System.out.println("totalScore: " + totalScore);
		System.out.println("author: " + author);
		System.out.println("version: " + version);
		System.out.println("isReleased: " + isReleased);
		System.out.println("testType: " + testtype);
		System.out.println("verType: " + vertype);
		for(String pass: passedReleaseNotes) {
			System.out.println("passedReleaseNotes: " + pass);
		}
		for(String fail: failedReleaseNotes) {
			System.out.println("failedReleaseNotes: " + fail);
		}
		for(String v: moreVersion) {
			System.out.println("more version: " + v);
		}
	}
}
