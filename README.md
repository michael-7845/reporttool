1. 获得和运行工具  
生成工具  
导入项目到Eclipse,编译生成可执行.jar文件  
  
注意:  
以下内容涉及账号密码安全, 已经被隐藏, 编译使用前需要提供.  
\src\mysql\bugzilla\utility\Query.java  
public class Query {  
	private static String user = "your_user";  
	private static String password = "your_password";  
...  
\src\mysql\testlink\utility\Query.java  
public class Query {  
	private static String user = "your_user";  
	private static String password = "your_password";  
...  
\src\kemin\mail  
public class HtmlMailSender {  
...  
	public String mailserver = "";  
...  
  
启动工具  
如果有关联jar文件用java打开，可以双击打开  
或者  
如果没有关联，可以使用命令行打开：  
1. 打开命令行窗口  
2. 进入bugreport.jar所在目录  
3. 执行命令“java -jar bugreport.jar”  
注意：  
* 请使用1.7以上版本的java运行！  
* \config 可配置自定义的开发关注Bug, 测试关注Bug, 系统分类, 产品, 项目, 执行情况等  
  
2. 填写报告内容  
  
产品版本  
选择正确的“产品”  
根据“产品”工具会列出可选的“版本类型”，请选择正确的“版本类型”  
根据“产品”、“版本类型”，工具会列出Bugzilla中存在的版本信息  
* 如果本版本有报告bug，请选择正确的“版本”  
* 如果本版本没有报告bug，勾中“无问题”，然后在后面的文本框填写正确的版本信息   
* 如果本版本距离上一次发布，开发过程中还有中间版本产生，并有交付测试进行测试，勾中“中间版本”，填写正确的中间版本信息  
  
报告类型  
测试报告 - 用于版本测试完成后的正式报告。模板会提供更详细的发布意见和问题情况  
快速反馈 - 用于版本还未完成，测试过程中的问题快速反馈。模板不会提供发布意见，只提供当前版本发现的问题情况  
Release Notes  
“通过”文本编辑区：请将测试验证通过的Release Notes内容填入  
“失败”文本编辑区：请将测试验证未通过的Release Notes内容填入  
备注  
如果报告作者有需要补充说明的情况，请在“备注”文本编辑区书写。无任何情况说明，可以留空。  
  
其他  
报告作者、测试人员、手机ROM、手机硬件：请如实填写  
  
3. 生成报告  
填写好内容以后，点击生成报告，报告会产生在jar文件所在目录下的report目录中  
  
4. 发送报告邮件  
确认报告正确  
填写邮件配置：发送方（只能为公司邮箱），密码，接收方，抄送方  
点击“发送报告邮件”，会弹出文件选择框，选择准备发送的报告  
确认，发送出报告邮件  
保存邮件配置  
保存  
  
 可以将发送方，密码，接收方，抄送方信息保存到文件  
（不支持多次保存，重复保存不会覆盖之前的文件内容。如需要保存新的内容，请用新的文件名或者删掉之前的文件再保存）  
恢复 - 选择之前保存的文件，会自动把文件中的发送方，密码，接收方，抄送方信息恢复到界面设置中  

michael yu
2014/3/10
