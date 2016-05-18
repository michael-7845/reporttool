package kemin.builder;

import mysql.testlink.utility.TLBean;

public class GeneralDirector extends Director {

	public GeneralDirector(ReportBean bean, TLBean tlbean) {
		super(bean, tlbean);
	}
	
	@Override
	//prepare内容的补充
	protected void afterPrepare() {
	}
	
	@Override
	//根据信息，读取不同的template文件
	protected void readTemplate() {
		if(this.bean.getTesttype().equals("快速反馈")) {
			prepare("template", "GeneralReport_fast.ftl");
		} else  {
			prepare("template", "GeneralReport.ftl");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
