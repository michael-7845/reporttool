<#include "/lib/common.ftl"> 
<#include "/lib/head.ftl"> 

<html>
<@html_head/>
<body>

<h1><b>${report.product} ${report.version} 测试${report.testtype}(${report.releaseDate})</b></h1>
<p>
报告作者: ${report.author}<br />
测试人员: ${report.tester}<br />
手机rom: ${report.rom}<br />
手机硬件: ${report.hardware}<br />
报告发布时间: ${report.releaseDate}<br />
备注: ${report.comments}<br />
</p>

<h2>本版本情况</h2>
<hr />
 <p>
 	<h3>Release Notes验证</h3>
 	<h4>通过</h4>
 	<ul indent='h4'>
 	<#list report.passedReleaseNotes as item>
 	<li>${item}</li>
 	</#list>
 	</ul>
 	<h4>失败</h4>
 	<ul indent='h4'>
 	<#list report.failedReleaseNotes as item>
 	<li>${item}</li>
 	</#list>
 	</ul>
 </p>

<h2>问题单详情</h2>
<hr />
  <h3>本次发布新发现的问题</h3>
  <#if (bugofthis?size=0)>
  <p indent='h3'>无</p>
  <#else>
  <@row_by_map byweight_this/>
  <table>
  <@table_head/>
  <@table_by_list bugofthis/>
  </table>
  </#if>

</body>
</html>