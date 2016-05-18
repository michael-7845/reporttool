package kemin.frame;

import info.clearthought.layout.TableLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import mysql.bugzilla.utility.BugReport;
import mysql.testlink.utility.TLReport;

import kemin.builder.Director;
import kemin.builder.DirectorFactory;
import kemin.mail.HtmlMailSender;
import kemin.tool.PropertiesTool;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ReportFrame extends javax.swing.JFrame {
	private JLabel productLabel;
	private JLabel versionLabel;
	private JComboBox productComboBox;
	private JComboBox tlTesterComboBox;
	private JComboBox tlBuildComboBox;
	private JComboBox tlTestplanComboBox;
	private JComboBox tlProjectComboBox;
	private JLabel tlTesterLabel;
	private JLabel tlBuildLabel;
	private JLabel tlTestplanLabel;
	private JLabel tlProjectLabel;
	private JSeparator jSeparator3;
	private JTextField commentsTextField;
	private JLabel commentsLabel;
	private JTextArea ccTextArea;
	private JScrollPane ccScrollPane1;
	private JLabel ccLabel;
	private JButton restoreButton;
	private JButton saveButton;
	private JTextField moreversionTextField;
	private JCheckBox moreversionCheckBox;
	private JTextField versionTextField;
	private JCheckBox nobugCheckBox;
	private JComboBox reporttypeComboBox;
	private JLabel reporttypeLabel;
	private JLabel vertypeLabel;
	private JComboBox vertypeComboBox;
	private JTextField passwordTextField;
	private JTextField senderTextField;
	private JTextArea recipientTextArea;
	private JScrollPane recipientScrollPane;
	private JButton sendButton;
	private JLabel recipientLabel;
	private JLabel passwordLabel;
	private JLabel senderLabel;
	private JButton reportButton;
	private JSeparator lineSeparator2;
	private JTextArea failTextArea;
	private JTextArea passTextArea;
	private JLabel failLabel;
	private JScrollPane failScrollPane;
	private JScrollPane passScrollPane;
	private JLabel passLabel;
	private JLabel releasenotesLabel;
	private JComboBox<String> versionComboBox;
	private JLabel authorLabel;
	private JTextField testerTextField;
	private JSeparator lineSeparator1;
	private JTextField hardwareTextField;
	private JLabel hardwareLabel;
	private JTextField romTextField;
	private JLabel romLabel;
	private JTextField authorTextField;
	private JLabel testerLabel;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ReportFrame inst = new ReportFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
	
	public ReportFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			TableLayout thisLayout = new TableLayout(new double[][] {{TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL}, {TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL}});
			thisLayout.setHGap(5);
			thisLayout.setVGap(5);
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				productLabel = new JLabel();
				getContentPane().add(productLabel, "0, 0");
				productLabel.setText("\u4ea7\u54c1");
			}
			{
				versionLabel = new JLabel();
				getContentPane().add(versionLabel, "0, 1");
				versionLabel.setText("\u7248\u672c");
			}
			{
				ComboBoxModel<String> versionComboBoxModel = 
						new DefaultComboBoxModel<String>();
//							BugReport.getAllVersion("HiBeam").toArray(new String[] {""}));
				versionComboBox = new JComboBox<String>();
				getContentPane().add(versionComboBox, "1, 1, 4, 1");
				versionComboBox.setModel(versionComboBoxModel);
			}
			{
				authorLabel = new JLabel();
				getContentPane().add(authorLabel, "4, 2");
				authorLabel.setText("\u62a5\u544a\u4f5c\u8005");
			}
			{
				testerLabel = new JLabel();
				getContentPane().add(testerLabel, "6, 2");
				testerLabel.setText("\u6d4b\u8bd5\u4eba\u5458");
			}
			{
				authorTextField = new JTextField();
				getContentPane().add(authorTextField, "5, 2");
			}
			{
				testerTextField = new JTextField();
				getContentPane().add(testerTextField, "7, 2");
			}
			{
				romLabel = new JLabel();
				getContentPane().add(romLabel, "0, 3");
				romLabel.setText("\u624b\u673aROM");
			}
			{
				romTextField = new JTextField();
				getContentPane().add(romTextField, "1, 3, 3, 3");
			}
			{
				hardwareLabel = new JLabel();
				getContentPane().add(hardwareLabel, "4, 3");
				hardwareLabel.setText("\u624b\u673a\u786c\u4ef6");
			}
			{
				hardwareTextField = new JTextField();
				getContentPane().add(hardwareTextField, "5, 3, 7, 3");
			}
			{
				lineSeparator1 = new JSeparator();
				getContentPane().add(lineSeparator1, "0, 4, 7, 4");
			}
			{
				releasenotesLabel = new JLabel();
				getContentPane().add(releasenotesLabel, "0, 4, 2, 4");
				releasenotesLabel.setText("ReleaseNotes\u60c5\u51b5");
			}
			{
				passLabel = new JLabel();
				getContentPane().add(passLabel, "0, 5");
				passLabel.setText("\u901a\u8fc7");
			}
			{
				passScrollPane = new JScrollPane();
				getContentPane().add(passScrollPane, "1, 5, 7, 7");
				{
					passTextArea = new JTextArea();
					passScrollPane.setViewportView(passTextArea);
				}
			}
			{
				failScrollPane = new JScrollPane();
				getContentPane().add(failScrollPane, "1, 8, 7, 10");
				{
					failTextArea = new JTextArea();
					failScrollPane.setViewportView(failTextArea);
				}
			}
			{
				failLabel = new JLabel();
				getContentPane().add(failLabel, "0, 8");
				failLabel.setText("\u5931\u8d25");
			}
			{
				lineSeparator2 = new JSeparator();
				getContentPane().add(lineSeparator2, "0, 13, 5, 13");
			}
			{
				reportButton = new JButton();
				getContentPane().add(reportButton, "6, 12, 7, 14");
				reportButton.setText("\u751f\u6210\u62a5\u544a");
				reportButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						reportButtonActionPerformed(evt);
					}
				});
			}
			{
				senderLabel = new JLabel();
				getContentPane().add(senderLabel, "0, 15");
				senderLabel.setText("\u53d1\u9001\u8d26\u53f7");
			}
			{
				passwordLabel = new JLabel();
				getContentPane().add(passwordLabel, "4, 15");
				passwordLabel.setText("\u5bc6\u7801");
			}
			{
				recipientLabel = new JLabel();
				getContentPane().add(recipientLabel, "0, 16");
				recipientLabel.setText("\u63a5\u6536\u8d26\u53f7");
			}
			{
				sendButton = new JButton();
				getContentPane().add(sendButton, "6, 16, 7, 16");
				sendButton.setText("\u53d1\u9001\u62a5\u544a\u90ae\u4ef6");
				sendButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						sendButtonActionPerformed(evt);
					}
				});
			}
			{
				recipientScrollPane = new JScrollPane();
				getContentPane().add(recipientScrollPane, "1, 16, 2, 19");
				{
					recipientTextArea = new JTextArea();
					recipientScrollPane.setViewportView(recipientTextArea);
				}
			}
			{
				senderTextField = new JTextField();
				getContentPane().add(senderTextField, "1, 15, 3, 15");
			}
			{
				passwordTextField = new JTextField();
				getContentPane().add(passwordTextField, "5, 15, 7, 15");
			}
			{
				Properties prop = PropertiesTool.read("config\\config.prop");
				String[] products = prop.getProperty("products").split(",");
				ComboBoxModel productComboBoxModel = 
						new DefaultComboBoxModel(products);
//								new String[] { "HiCamera3.0", "HiCamera_6592", 
//										"HiGallery3.0", "HiGallery_6592", "HIKe快传", "HIKe单反" });
				productComboBox = new JComboBox();
				getContentPane().add(productComboBox, "1, 0, 2, 0");
				productComboBox.setModel(productComboBoxModel);
				productComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						productComboBoxActionPerformed(evt);
					}
				});
			}
			{
				ComboBoxModel vertypeComboBoxModel = 
						new DefaultComboBoxModel(
								new String[] { "stable", "ali" });
				vertypeComboBox = new JComboBox();
				getContentPane().add(vertypeComboBox, "4, 0, 5, 0");
				vertypeComboBox.setVisible(false);
				vertypeComboBox.setModel(vertypeComboBoxModel);
				vertypeComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						vertypeComboBoxActionPerformed(evt);
					}
				});
			}
			{
				vertypeLabel = new JLabel();
				getContentPane().add(vertypeLabel, "3, 0");
				vertypeLabel.setText("\u7248\u672c\u7c7b\u578b");
				vertypeLabel.setVisible(false);
			}
			{
				reporttypeLabel = new JLabel();
				getContentPane().add(reporttypeLabel, "0, 2");
				reporttypeLabel.setText("\u62a5\u544a\u7c7b\u578b");
			}
			{
				ComboBoxModel reporttypeComboBoxModel = 
						new DefaultComboBoxModel(
								new String[] { "正式报告", "快速反馈" });
				reporttypeComboBox = new JComboBox();
				getContentPane().add(reporttypeComboBox, "1, 2");
				reporttypeComboBox.setModel(reporttypeComboBoxModel);
			}
			{
				nobugCheckBox = new JCheckBox();
				getContentPane().add(nobugCheckBox, "2, 2");
				nobugCheckBox.setText("\u65e0\u95ee\u9898");
				nobugCheckBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						nobugCheckBoxActionPerformed(evt);
					}
				});
			}
			{
				versionTextField = new JTextField();
				getContentPane().add(versionTextField, "3, 2");
				versionTextField.setEnabled(false);
				versionTextField.setEditable(false);
			}
			{
				moreversionCheckBox = new JCheckBox();
				getContentPane().add(moreversionCheckBox, "0, 11");
				moreversionCheckBox.setText("\u4e2d\u95f4\u7248\u672c");
				moreversionCheckBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						moreversionCheckBoxActionPerformed(evt);
					}
				});
			}
			{
				moreversionTextField = new JTextField();
				getContentPane().add(moreversionTextField, "1, 11, 7, 11");
				moreversionTextField.setEditable(false);
				moreversionTextField.setEnabled(false);
			}
			{
				saveButton = new JButton();
				getContentPane().add(saveButton, "0, 17");
				saveButton.setText("\u4fdd\u5b58");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						saveButtonActionPerformed(evt);
					}
				});
			}
			{
				restoreButton = new JButton();
				getContentPane().add(restoreButton, "0, 18");
				restoreButton.setText("\u6062\u590d");
				restoreButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						restoreButtonActionPerformed(evt);
					}
				});
			}
			{
				ccLabel = new JLabel();
				getContentPane().add(ccLabel, "3, 16");
				ccLabel.setText("\u6284\u9001\u8d26\u53f7");
			}
			{
				ccScrollPane1 = new JScrollPane();
				getContentPane().add(ccScrollPane1, "4, 16, 5, 19");
				{
					ccTextArea = new JTextArea();
					ccScrollPane1.setViewportView(ccTextArea);
				}
			}
			{
				commentsLabel = new JLabel();
				getContentPane().add(commentsLabel, "0, 12");
				commentsLabel.setText("\u6d4b\u8bd5\u5907\u6ce8");
			}
			{
				commentsTextField = new JTextField();
				getContentPane().add(commentsTextField, "1, 12, 5, 12");
			}
			{
				jSeparator3 = new JSeparator();
				getContentPane().add(jSeparator3, "0, 15, 7, 15");
			}
			{
				tlProjectLabel = new JLabel();
				getContentPane().add(tlProjectLabel, "0, 13");
				tlProjectLabel.setText("TestLink\u9879\u76ee");
			}
			{
				tlTestplanLabel = new JLabel();
				getContentPane().add(tlTestplanLabel, "3, 13");
				tlTestplanLabel.setText("\u6d4b\u8bd5\u8ba1\u5212");
			}
			{
				tlBuildLabel = new JLabel();
				getContentPane().add(tlBuildLabel, "0, 14");
				tlBuildLabel.setText("Build");
			}
			{
				tlTesterLabel = new JLabel();
				getContentPane().add(tlTesterLabel, "3, 14");
				tlTesterLabel.setText("\u6267\u884c\u8005\u8d26\u53f7");
				tlTesterLabel.setVisible(false);
			}
			{
				Properties prop = PropertiesTool.read("config\\config.prop");
				String[] projects = prop.getProperty("projects").split(",");
				ComboBoxModel tlProjectComboBoxModel = 
						new DefaultComboBoxModel(projects);
				tlProjectComboBox = new JComboBox();
				getContentPane().add(tlProjectComboBox, "1, 13, 2, 13");
				tlProjectComboBox.setModel(tlProjectComboBoxModel);
				tlProjectComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						tlProjectComboBoxActionPerformed(evt);
					}
				});
			}
			{
				ComboBoxModel tlTestplanComboBoxModel = 
						new DefaultComboBoxModel(
								new String[] { });
				tlTestplanComboBox = new JComboBox();
				getContentPane().add(tlTestplanComboBox, "4, 13, 5, 13");
				tlTestplanComboBox.setModel(tlTestplanComboBoxModel);
				tlTestplanComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						tlTestplanComboBoxActionPerformed(evt);
					}
				});
			}
			{
				ComboBoxModel tlBuildComboBoxModel = 
						new DefaultComboBoxModel(
								new String[] { });
				tlBuildComboBox = new JComboBox();
				getContentPane().add(tlBuildComboBox, "1, 14, 5, 14");
				tlBuildComboBox.setModel(tlBuildComboBoxModel);
				tlBuildComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						tlBuildComboBoxActionPerformed(evt);
					}
				});
			}
			{
				ComboBoxModel tlTesterComboBoxModel = 
						new DefaultComboBoxModel(
								new String[] { });
				tlTesterComboBox = new JComboBox();
				getContentPane().add(tlTesterComboBox, "4, 14, 5, 14");
				tlTesterComboBox.setVisible(false);
				tlTesterComboBox.setModel(tlTesterComboBoxModel);
			}
			pack();
			this.setSize(688, 568);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void productComboBoxActionPerformed(ActionEvent evt) {
        String product = productComboBox.getSelectedItem().toString();

        String productname = product; //Report.byProduct(product); 
        //HIKe测试中没有vertype概念
//        String[] vertypes = BugReport.byVertype(product);
//        ComboBoxModel vertypeComboBoxModel = new DefaultComboBoxModel(vertypes);
//        vertypeComboBox.setModel(vertypeComboBoxModel);
        
//        String postfix = (String)vertypeComboBox.getSelectedItem();
//        ComboBoxModel<String> versionComboBoxModel = 
//				new DefaultComboBoxModel<String>(
//					BugReport.getAllVersion(productname, postfix).toArray(new String[] {""}));
        
        ComboBoxModel<String> versionComboBoxModel = 
				new DefaultComboBoxModel<String>(
					BugReport.getAllVersion(productname).toArray(new String[] {""}));
		versionComboBox.setModel(versionComboBoxModel);
	}
	
	//HIKe测试中没有使用vertype概念，不调用此函数
	private void vertypeComboBoxActionPerformed(ActionEvent evt) {
		String product = productComboBox.getSelectedItem().toString();
		String vertype = vertypeComboBox.getSelectedItem().toString(); 
		String productname = product; //Report.byProduct(product);
        
        ComboBoxModel<String> versionComboBoxModel = 
				new DefaultComboBoxModel<String>(
					BugReport.getAllVersion(productname, vertype).toArray(new String[] {""}));
		versionComboBox.setModel(versionComboBoxModel);
	}
	
	private void nobugCheckBoxActionPerformed(ActionEvent evt) {
//		System.out.println("nobugCheckBox.actionPerformed, event="+evt);
		JCheckBox box = (JCheckBox)evt.getSource();// 返回事件的产生程序
		if (box.isSelected()) {
			versionTextField.setEditable(true);
			versionTextField.setEnabled(true);
			versionComboBox.setEditable(false);
			versionComboBox.setEnabled(false);
		}else {
			versionTextField.setEditable(false);
			versionTextField.setEnabled(false);
			versionComboBox.setEditable(true);
			versionComboBox.setEnabled(true);
		}
	}
	
	private void moreversionCheckBoxActionPerformed(ActionEvent evt) {
//		System.out.println("moreversionCheckBox.actionPerformed, event="+evt);
		JCheckBox box = (JCheckBox)evt.getSource();// 返回事件的产生程序
		if (box.isSelected()) {
			moreversionTextField.setEditable(true);
			moreversionTextField.setEnabled(true);
		}else {
			moreversionTextField.setEditable(false);
			moreversionTextField.setEnabled(false);
		}
	}
	
	private void reportButtonActionPerformed(ActionEvent evt) {
		System.out.println("reportButton.actionPerformed, event="+evt);
		
		String product = (String)productComboBox.getSelectedItem();
		String vertype = (String)vertypeComboBox.getSelectedItem();
		String testtype = (String)reporttypeComboBox.getSelectedItem();
		String version = null;
		if(nobugCheckBox.isSelected()) {
			version = versionTextField.getText();
		} else {
			version = (String)versionComboBox.getSelectedItem();
		}
		String author = authorTextField.getText();
		String tester = testerTextField.getText();
		String rom = romTextField.getText();
		String hardware = hardwareTextField.getText();
		String[] passlist = passTextArea.getText().split("\n");
		String[] faillist = failTextArea.getText().split("\n");
		String[] moreversion = {""};
		if(moreversionCheckBox.isSelected()) {
			moreversion = moreversionTextField.getText().split(",");
		}
		if(moreversion != null) {
			int size = moreversion.length;
			for(int i=0; i<size; i++) {
				moreversion[i] = moreversion[i].trim(); //去除前后空格
			}
		}
		String comments = commentsTextField.getText();
		String tlproject = (String)tlProjectComboBox.getSelectedItem();
		String tltestplan = (String)tlTestplanComboBox.getSelectedItem();
		String tlbuild = (String)tlBuildComboBox.getSelectedItem();
		//在HIKe手机测试中多个测试人员测试同一个版本
		//String tltester = (String)tlTesterComboBox.getSelectedItem();
		StringBuffer tltester = new StringBuffer();
		String[] a = TLReport.getAllTester(tlbuild).toArray(new String[] {""});
		for(String t: a) {
			tltester.append(t).append(", ");
		}
		
		Director d = DirectorFactory.create2(product, vertype, testtype, version, 
				author, tester, rom, hardware, 
				comments, Arrays.asList(passlist), 
				Arrays.asList(faillist), Arrays.asList(moreversion), 
				tlproject, tltestplan, tlbuild, tltester.toString());
		d.bean.printSelf(); //调试语句
		d.generate();
	}
	
	private void sendButtonActionPerformed(ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
	      		"html file", "html");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File report = chooser.getSelectedFile();
			String sender = senderTextField.getText();
			String password = passwordTextField.getText();
			String[] recipients= recipientTextArea.getText().split("\n");
			String[] ccs= ccTextArea.getText().split("\n");
			HtmlMailSender s = new HtmlMailSender(report, sender, password, recipients, ccs);
			s.send();
		}
	}
	
	private void saveButtonActionPerformed(ActionEvent evt) {
		String sender = senderTextField.getText();
		String password = passwordTextField.getText();
		String[] recipients= recipientTextArea.getText().split("\n");
		String[] ccs= ccTextArea.getText().split("\n");
		StringBuffer tolist = savelist(recipients);
		StringBuffer cclist = savelist(ccs);
		
		Properties prop = new Properties();
		prop.put("sender", sender);
		prop.put("password", password);
		prop.put("tolist", tolist.toString());
		prop.put("cclist", cclist.toString());
		
    	JFileChooser jfc = new JFileChooser(); 
    	jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        jfc.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);  
        jfc.showDialog(null,null);  
        File savedfile = jfc.getSelectedFile(); 
        if(savedfile != null) {
             PropertiesTool.write(savedfile.getPath(), prop, "Saved my configuration");
        }
	}

	private StringBuffer savelist(String[] recipients) {
		StringBuffer tolist = new StringBuffer();
		int size = recipients.length;
		for(int i=0; i<size; i++) {
			if(i==(size-1)){
				tolist.append(recipients[i]);
			} else {
				tolist.append(recipients[i]).append(",");
			}	
		}
		return tolist;
	}
	
	private void restoreButtonActionPerformed(ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
//		FileNameExtensionFilter filter = new FileNameExtensionFilter(
//	      		"text file", "txt");
//		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			Properties prop = new Properties();
			prop = PropertiesTool.read(f.getPath());
			
			String sender = prop.getProperty("sender");
			String password = prop.getProperty("password");
			String[] recipients= prop.getProperty("tolist").split(",");
			String[] ccs= prop.getProperty("cclist").split(",");
			StringBuffer tolist = restorelist(recipients);
			StringBuffer cclist = restorelist(ccs);
			senderTextField.setText(sender);
			passwordTextField.setText(password);
			recipientTextArea.setText(tolist.toString());
			ccTextArea.setText(cclist.toString());
		}
	}

	private StringBuffer restorelist(String[] recipients) {
		StringBuffer tolist = new StringBuffer();
		int size = recipients.length;
		for(int i=0; i<size; i++) {
			if(i==(size-1)){
				tolist.append(recipients[i]);
			} else {
				tolist.append(recipients[i]).append("\n");
			}	
		}
		return tolist;
	}
	
	private void tlProjectComboBoxActionPerformed(ActionEvent evt) {
		String tlproject = tlProjectComboBox.getSelectedItem().toString();
        ComboBoxModel<String> testplanComboBoxModel = 
				new DefaultComboBoxModel<String>(
						TLReport.getAllTestplan(tlproject).toArray(new String[] {""}));
		tlTestplanComboBox.setModel(testplanComboBoxModel);
		ComboBoxModel<String> voidComboBoxModel =
				new DefaultComboBoxModel<String>(new String[] {""});
		tlBuildComboBox.setModel(voidComboBoxModel);
		tlTesterComboBox.setModel(voidComboBoxModel);
	}
	
	private void tlTestplanComboBoxActionPerformed(ActionEvent evt) {
		String tltestplan = tlTestplanComboBox.getSelectedItem().toString();
		System.out.println(tltestplan);
        ComboBoxModel<String> buildComboBoxModel = 
				new DefaultComboBoxModel<String>(
						TLReport.getAllBuild(tltestplan).toArray(new String[] {""}));
		tlBuildComboBox.setModel(buildComboBoxModel);
		ComboBoxModel<String> voidComboBoxModel =
				new DefaultComboBoxModel<String>(new String[] {""});
		tlTesterComboBox.setModel(voidComboBoxModel);
	}
	
	//在HIKe手机测试中多个tester测试同一个版本
	private void tlBuildComboBoxActionPerformed(ActionEvent evt) {
		String tlbuild = tlBuildComboBox.getSelectedItem().toString();
        ComboBoxModel<String> testerComboBoxModel = 
				new DefaultComboBoxModel<String>(
						TLReport.getAllTester(tlbuild).toArray(new String[] {""}));
		tlTesterComboBox.setModel(testerComboBoxModel);
	}
}
