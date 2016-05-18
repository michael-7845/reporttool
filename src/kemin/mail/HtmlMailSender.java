package kemin.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class HtmlMailSender {
	public File report;
	public InternetAddress[] tolist;
	public InternetAddress[] cclist;
	public String sender;
	public String password;
	public String mailserver = "";
	
	public HtmlMailSender(File report, String sender, String password, 
			String[] recipients, String[] ccs) {
		this.report = report;
		this.sender = sender;
		this.password = password;
		setTolist(recipients);
		setCclist(ccs);
	}
	
	public void setTolist(String[] recipients) {
		List<InternetAddress> l = new ArrayList<InternetAddress>();
		for(String r: recipients) {
			try {
				if(r.length()>0) { //无内容的地址非法
					InternetAddress to = new InternetAddress(r);
					l.add(to);
				}
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		tolist = l.toArray(new InternetAddress[]{new InternetAddress()});
	}
	
	public void setCclist(String[] ccs) {
		List<InternetAddress> l = new ArrayList<InternetAddress>();
		for(String r: ccs) {
			try {
				if(r.length()>0) { //无内容的地址非法
					InternetAddress cc = new InternetAddress(r);
					l.add(cc);
				}
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		cclist = l.toArray(new InternetAddress[]{new InternetAddress()});
	}
	
	public String readHtml(File f) {
		StringBuilder sb = new StringBuilder();
		BufferedReader in;
		try {
//			in = new BufferedReader( new FileReader(f));
			in = new BufferedReader(new InputStreamReader(
	                new FileInputStream(f), "utf-8"));
			String s;
			while((s = in.readLine())!=null)
				sb.append(s + "\n");
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public void send() {
		String ttitle = 
				report.getName().split("\\.html")[0] + " test report";
		
		Properties props=new Properties();
		props.put("mail.smtp.host", this.mailserver);
		props.put("mail.smtp.auth", "true");
		
		Session s=Session.getInstance(props);
		s.setDebug(true);
		MimeMessage message=new MimeMessage(s);
		String tcontent = readHtml(report);

		try{
			//给消息对象设置发件人/收件人/主题/发信时间
			InternetAddress from=new InternetAddress(this.sender);
		    message.setFrom(from);
		    if((this.tolist.length>0) && (this.tolist[0] != null)) { //非法的收件人列表才赋值到to
		    	message.setRecipients(Message.RecipientType.TO, this.tolist);
		    }
		    if((this.cclist.length>0) && (this.cclist[0] != null)) { //非法的抄送人列表才赋值到cc
		    	message.setRecipients(Message.RecipientType.CC, this.cclist);
		    }
		    message.setSubject(ttitle);
		    message.setSentDate(new Date());

		    //给消息对象设置内容
		    BodyPart mdp=new MimeBodyPart();//新建一个存放信件内容的BodyPart对象
		    mdp.setContent(tcontent,"text/html;charset=gb2312");//给BodyPart对象设置内容和格式/编码方式
		    Multipart mm=new MimeMultipart();//新建一个MimeMultipart对象用来存放BodyPart对象(事实上可以存放多个)
		    mm.addBodyPart(mdp);//将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
		    message.setContent(mm);//把mm作为消息对象的内容

		    message.saveChanges();
		    Transport transport=s.getTransport("smtp");
		    transport.connect(this.mailserver, this.sender , this.password);
		    transport.sendMessage(message,message.getAllRecipients());
		    transport.close();
		    System.out.println("发送成功!");
		}catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File report = new File("report/dfsfs.txt");
		String sender = "abc@def.com";
		String password = "abcdef";
		String[] recipients= {"def@def.com"};
		String[] ccs= {"ghi@def.com"};
		HtmlMailSender s = new HtmlMailSender(report, sender, password, 
				recipients, ccs);
		s.send();
	}

}
