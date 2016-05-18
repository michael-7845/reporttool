<#-- bug划分等级的得分参考 -->
<#assign A=report.aType/>
<#assign B=report.bType/>
<#assign C=report.cType/>

<#-- 打印表头 -->
<#macro table_head>
<tr>
  <th field='id'>ID</th>
  <th field='score'>评分</th>
  <th field='desc'>标题</th>
  <th field='sev'>严重程度</th>
  <th field='enc'>客户可见</th>
  <th field='rep'>重现性</th>
  <th field='status'>状态</th>
  <th field='ver'>版本</th>
</tr>
</#macro>

<#-- 打印表的一行，字体颜色color -->
<#macro line map color>
  <#assign id=map['bug_id']>
  <#assign weight=map['weight']?number>
  <#assign desc=map['short_desc']>
  <#assign sev=map['bug_severity']>
  <#assign encounter=map['cf_encounter']>
  <#assign repeat=map['cf_repeatability']>
  <#assign status=map['bug_status']>
  <#assign version=map['version']>
  <td class=${color} field='id'><a href='<@bug_link id/>' target='_blank'>${id}</a></td>
  <td class=${color} field='score'>${weight}</td>
  <td class=${color} field='desc'>${desc}</td>
  <td class=${color} field='sev'>${sev}</td>
  <td class=${color} field='enc'>${encounter}</td>
  <td class=${color} field='rep'>${repeat}</td>
  <td class=${color} field='status'>${status}</td>
  <td class=${color} field='ver'>${version}</td>
</#macro>

<#-- 生成bug到bugzilla的链接信息 -->
<#macro bug_link id>
http://10.120.10.150/bugzilla/show_bug.cgi?id=${id}
</#macro>

<#-- 用list打印表 -->
<#macro table_by_list list>
<#list list as line_map>
<tr>
  <#assign weight=line_map['weight']?number>
<#if (weight>=A)>
  <@line line_map "red"/>
<#elseif (weight>=B)> 
  <@line line_map "darkred"/>
<#elseif (weight>=C)>
  <@line line_map "blue"/> 
<#else>
  <@line line_map "black"/> 
</#if>
</tr>
</#list>
</#macro>

<#macro fulltable_by_list list>
 <table id='customers'>
 <@table_head/>
 <@table_by_list list/>
 </table>
</#macro>

<#macro table_by_map_and_selected_key map selected>
<table>
<#-- @table_head/ -->
<#assign keys=map?keys>
<#list keys as key>
  <#list selected as key2>
  <#if key=key2>
    <#assign listitem=map[key]>
    <@table_by_list listitem/>
  </#if>
  </#list>
</#list>
</table>
</#macro>

<#-- 用map打印表 -->
<#macro by_map map>
<#assign keys=map?keys>
<#list keys as key>
 <#assign list=map[key]>
 ${key}(${list?size})<br />
 <@fulltable_by_list list/>
</#list>
</#macro>

<#-- 用map打印类别中项目数量，以字符串的形式 -->
<#macro row_by_map map>
<#assign keys=map?keys?sort>
<#list keys as key>
 <#assign list=map[key]>
 ${key} - ${map[key]?size},
</#list>
<br />
</#macro>

<#-- 用map打印类别中项目数量，以表的形式 -->
<#macro count_table_by_map map>
<#assign keys=map?keys?sort>
<table>
<#list keys as key>
 <#assign list=map[key]>
 <tr>
 <td>${key}</td>
 <td>${map[key]?size}</td>
 </tr>
</#list>
</table>
</#macro>

<#-- count_table_by_map，表的置换形式打印 -->
<#macro revert_count_table_by_map map>
<#assign keys=map?keys?sort>
<table>
<tr>
<#list keys as key>
 <td>${key}</td>
</#list>
</tr>
<tr>
<#list keys as key>
 <#assign list=map[key]>
 <td>${map[key]?size}</td>
</#list>
 </tr>
</table>
</#macro>

<#-- bug_links_ordered_list，打印问题单详情中的各表格的链接有序列表 -->
<#macro bug_links_ordered_list>
<ol>
<li><a href="#new_bug">本次发布新发现的问题</a></li>
<li><a href="#all_bug">全部问题的评分情况</a></li>
<li><a href="#by_component">模块分布情况</a></li>
<li><a href="#by_howlong">存活时间分布情况</a></li>
<li><a href="#for_test">待测试人员处理的问题单</a></li>
<li><a href="#testlink">附：测试用例情况</a></li>
</ol>
</#macro>

<#-- bug_score_rule，打印问题打分的规则 -->
<#macro bug_score_rule>
<p class=comment>
问题单评分 = 严重程度 * 客户可见 + 重现性<br />
A: >6.0; B: [4.9, 6.0); C: [3.4, 4.9); D: <3.4
</p>
<ul>
<li class=comment>严重程度 - Critical:4; Major:3; Minor:2; Enhancement:1</li>
<li class=comment>客户可见 - Likely:1.5; Maybe:1.3; UnLikely:0.8; OnlyCKT:0.4</li>
<li class=comment>重现性 - Always:1; Frequently:0.5; Normal:0; Sometimes:-1; Rarely:-2</li>
</ul>
</#macro>
