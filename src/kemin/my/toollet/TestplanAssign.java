package kemin.my.toollet;
import info.clearthought.layout.TableLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import mysql.testlink.utility.TLReport;


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
public class TestplanAssign extends javax.swing.JFrame {
	private JLabel projectLabel;
	private JTextField projectTextField;
	private JScrollPane testplanScrollPane;
	private JButton getButton;
	private JList testplanList;
	private JLabel testplanLabel;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TestplanAssign inst = new TestplanAssign();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public TestplanAssign() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			TableLayout thisLayout = new TableLayout(new double[][] {{TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL}, {TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL}});
			thisLayout.setHGap(5);
			thisLayout.setVGap(5);
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				projectLabel = new JLabel();
				getContentPane().add(projectLabel, "0, 0");
				projectLabel.setText("Project");
			}
			{
				projectTextField = new JTextField();
				getContentPane().add(projectTextField, "1, 0, 2, 0");
				projectTextField.setText("VASAPP");
			}
			{
				testplanLabel = new JLabel();
				getContentPane().add(testplanLabel, "0, 1");
				testplanLabel.setText("Testplan");
			}
			{
				testplanScrollPane = new JScrollPane();
				getContentPane().add(testplanScrollPane, "1, 1, 6, 10");
				{
					ListModel testplanListModel = 
							new DefaultComboBoxModel(
									TLReport.getAllTestplan(projectTextField.getText())
									.toArray(new String[]{}));
					testplanList = new JList();
					testplanScrollPane.setViewportView(testplanList);
					testplanList.setModel(testplanListModel);
					testplanList.setSelectedIndices(
							new int[] {6,7,8,9,10}); //kemin yu
				}
			}
			{
				getButton = new JButton();
				getContentPane().add(getButton, "4, 0, 5, 0");
				getButton.setText("\u751f\u6210");
				getButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						getButtonActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(459, 366);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void getButtonActionPerformed(ActionEvent evt) {
		System.out.println("getButton.actionPerformed, event="+evt);
		
		StringBuffer sb = new StringBuffer();
		sb.append("project: " + this.projectTextField.getText()).append("\n\n");
		
		Object[] testplans = this.testplanList.getSelectedValues();
		int index = 0;
		for(Object tp: testplans) {
			String t = (String)tp;
			index++;
			sb.append(index + ". testplan: " + t).append("\n");
			List<String> builds = TLReport.getAllBuild(t);
			for(String bld: builds) {
				sb.append("* build: " + bld).append(" - ");
				List<String> testers = TLReport.getAllTester(bld);
				for(String tst: testers) {
					sb.append(tst).append(", ");
				}
				sb.append("\n");
			}
		}
		
		TextFile.write("text/tlproject.txt", sb.toString());
	}

}
