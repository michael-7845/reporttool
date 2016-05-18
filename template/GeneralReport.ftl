<#include "/lib/common.ftl"> 
<#include "/lib/head.ftl"> 
<#include "/lib/common_4test.ftl"> 
<#include "/lib/common_howlong.ftl"> 
<#include "/lib/common_tl.ftl"> 

<html>

<@html_head/>

<body>

<h1><b>${report.product} ${report.version} 测试报告</b></h1>
<p>
报告作者: ${report.author}<br />
测试人员: ${report.tester}<br />
手机rom: ${report.rom}<br />
手机硬件: ${report.hardware}<br />
报告发布时间: ${report.releaseDate}<br />
中间版本:
<#list report.moreVersion as l>
${l},
</#list>
<br />
备注: ${report.comments}<br />
</p>

<h2>本版本情况</h2>
<hr />
 <p>
 	<h3>本版本得分: <b>${report.totalScore}</b></h3>
 	<h3>可否对外发布：<b>${report.isReleased}</b> </h3>
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
 	<h3>影响发布的新问题</h3>
 	<#assign failkeys=["A", "B"]/>
 	<@table_by_map_and_selected_key byweight_this failkeys/>
 	<h3>测试提请注意事项</h3>
 	<#list report.attention as item>
 	<li>${item}</li>
 	</#list>
 </p>

<h2><a name="title">问题单详情</a></h2>
<@bug_links_ordered_list/>
<hr />
<@bug_score_rule/>

<hr />
  <h3><a name="new_bug">本次发布新发现的问题</a>[<a href="#title">返回:问题单详情<a>]</h3>
  <#if (bugofthis?size=0)>
  <p indent='h3'>无</p>
  <#else>
  <@row_by_map byweight_this/>
  <table>
  <@table_head/>
  <@table_by_list bugofthis/>
  </table>
  </#if>
  
<hr />
  <h3 ><a name="all_bug">全部问题的评分情况</a>[<a href="#title">返回:问题单详情<a>]</h3>
  <@row_by_map byweight/>
  <table>
  <@table_head/>
  <@table_by_list devbug/>
  </table>
  
<hr />
<h3><a name="by_component">模块分布情况</a>[<a href="#title">返回:问题单详情<a>]</h3>
  <@revert_count_table_by_map bycomponent/>
  <@by_map bycomponent />

<hr />
<h3><a name="by_howlong">存活时间分布情况</a>[<a href="#title">返回:问题单详情<a>]</h3>
  <@revert_count_table_by_map bycreatedday/>
  <@by_map_howlong bycreatedday />

<hr />
<h3><a name="for_test">待测试人员处理的问题单</a>[<a href="#title">返回:问题单详情<a>]</h3>
  <table>
  <@table_head_4test/>
  <@table_by_list_4test testbug/>
  </table>

<hr />
<h2><a name="testlink">TestLink测试用例情况</a>[<a href="#title">返回:问题单详情<a>]</h2>
项目(Project): ${tlreport.project}<br />
测试计划(Testplan): ${tlreport.testplan}<br />
构建(Build): ${tlreport.build}<br />
执行者账号(Tester): ${tlreport.tester}<br />
<hr />
<h3>测试用例执行情况统计</h3>
<table>
<@table_tl_statistics/>
</table>
<hr />
<h3>测试用例执行详情</h3>
<@fulltable_tl_by_tsmap tlallbyts/>

<hr />

</body>
</html>