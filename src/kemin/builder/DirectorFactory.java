package kemin.builder;

import java.util.ArrayList;
import java.util.List;

import mysql.testlink.utility.TLBean;


public abstract class DirectorFactory {
	//用于VAS APP测试
	public static Director create(String product, String vertype, String testtype, String version, 
			String author, String tester, String rom , String hardware, String comments, 
			List<String> passed, List<String> failed, List<String> moreversion,
			String tlproject, String tltestplan, String tlbuild, String tltester) {	
		ReportBean bean = new ReportBean();
		bean.setProduct(product);
		bean.setVertype(vertype);
		bean.setTesttype(testtype);
		bean.setVersion(version);
		bean.setAuthor(author);
		bean.setTester(tester);
		bean.setRom(rom);
		bean.setHardware(hardware);
		bean.setComments(comments);
		bean.setPassedReleaseNotes(passed);
		bean.setFailedReleaseNotes(failed);
		bean.setMoreVersion(moreversion);
		
		TLBean tlbean = new TLBean();
		tlbean.setProject(tlproject);
		tlbean.setTestplan(tltestplan);
		tlbean.setBuild(tlbuild);
		tlbean.setTester(tltester);
		
		Director d = null;
		if(product.equals("HiBeam")) {
			d = new GeneralDirector(bean, tlbean);
		}else if(vertype.equals("general")) {
			d = new GeneralDirector(bean, tlbean);
		}else if(vertype.equals("stable")) {
			d = new StableDirector(bean, tlbean);
		}else if(vertype.equals("dev")) {
			d = new DevDirector(bean, tlbean);
		}else if(vertype.equals("ali")) {
			d = new AliDirector(bean, tlbean);
		}else if(vertype.equals("mt6592")) {
			d = new Mt6592Director(bean, tlbean);
		}
		return d;
	}
	
	//用于HIKe手机测试
	public static Director create2(String product, String vertype, String testtype, String version, 
			String author, String tester, String rom , String hardware, String comments, 
			List<String> passed, List<String> failed, List<String> moreversion,
			String tlproject, String tltestplan, String tlbuild, String tltester) {	
		ReportBean bean = new ReportBean();
		bean.setProduct(product);
		bean.setVertype(vertype);
		bean.setTesttype(testtype);
		bean.setVersion(version);
		bean.setAuthor(author);
		bean.setTester(tester);
		bean.setRom(rom);
		bean.setHardware(hardware);
		bean.setComments(comments);
		bean.setPassedReleaseNotes(passed);
		bean.setFailedReleaseNotes(failed);
		bean.setMoreVersion(moreversion);
		
		TLBean tlbean = new TLBean();
		tlbean.setProject(tlproject);
		tlbean.setTestplan(tltestplan);
		tlbean.setBuild(tlbuild);
		tlbean.setTester(tltester);
		
		Director d = new GeneralDirector(bean, tlbean);
		return d;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> passed = new ArrayList<String>();
		passed.add("xxxx");
		passed.add("yyyy");
		passed.add("zzzz");
		List<String> failed = new ArrayList<String>();
		failed.add("xxxx");
		failed.add("yyyy");
		failed.add("zzzz");
		List<String> moreversion = new ArrayList<String>();
//		moreversion.add("123");
//		moreversion.add("456");
//		moreversion.add("789");
		moreversion.add("2.2.376_stable");
		moreversion.add("2.0.336_stable");
		moreversion.add("2.0.314_stable");
		Director r = null;
//		r = ReportFactory.createReport("HIKe单反", "stable", "140107_stable", "虞科敏", "余艳", 
//				"xs", "xs", passed, failed);
		r = DirectorFactory.create("HiBeam", "stable", "快速测试", 
				"2.0.353_stable", "虞科敏", "余艳", 
				"xs", "xs", "测试无备注", 
				passed, failed, moreversion, 
				"VAS-X", "14/3APP", "20140303-0307", "jianjun.duan");
		r.generate();
	}

}
