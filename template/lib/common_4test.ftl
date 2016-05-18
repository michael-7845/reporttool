<#-- bug划分等级的得分参考 -->
<#assign A=report.aType/>
<#assign B=report.bType/>
<#assign C=report.cType/>

<#-- 打印表头 -->
<#macro table_head_4test>
<tr>
  <th field='id'>ID</th>
  <th field='score'>评分</th>
  <th field='desc'>标题</th>
  <th field='sev'>严重程度</th>
  <th field='enc'>客户可见</th>
  <th field='rep'>重现性</th>
  <th field='status'>状态</th>
  <th field='ver'>版本</th>
  <th field='cause'>原因</th>
  <th field='resver'>修复版本</th>
  <th field='since'>修复日期</th>
</tr>
</#macro>

<#-- 打印表的一行，字体颜色color -->
<#macro line_4test map color>
  <#assign id=map['bug_id']>
  <#assign weight=map['weight']?number>
  <#assign desc=map['short_desc']>
  <#assign sev=map['bug_severity']>
  <#assign encounter=map['cf_encounter']>
  <#assign repeat=map['cf_repeatability']>
  <#assign status=map['bug_status']>
  <#assign version=map['version']>
  <#assign rootcause=map['cf_root_cause']>
  <#assign resversion=map['cf_resolved_verison']>
  <#assign since=map['cf_fixed_since']>
  <td class=${color} field='id'><a href='<@bug_link id/>' target='_blank'>${id}</a></td>
  <td class=${color} field='score'>${weight}</td>
  <td class=${color} field='desc'>${desc}</td>
  <td class=${color} field='sev'>${sev}</td>
  <td class=${color} field='enc'>${encounter}</td>
  <td class=${color} field='rep'>${repeat}</td>
  <td class=${color} field='status'>${status}</td>
  <td class=${color} field='ver'>${version}</td>
  <td class=${color} field='cause'>${rootcause}</td>
  <td class=${color} field='resver'>${resversion}</td>
  <td class=${color} field='since'>${since}</td>
</#macro>

<#-- 生成bug到bugzilla的链接信息 -->
<#macro bug_link id>
http://10.120.10.150/bugzilla/show_bug.cgi?id=${id}
</#macro>

<#-- 用list打印表 -->
<#macro table_by_list_4test list>
<#list list as line_map>
<tr>
  <#assign weight=line_map['weight']?number>
<#if (weight>=A)>
  <@line_4test line_map "red"/>
<#elseif (weight>=B)> 
  <@line_4test line_map "darkred"/>
<#elseif (weight>=C)>
  <@line_4test line_map "blue"/> 
<#else>
  <@line_4test line_map "black"/> 
</#if>
</tr>
</#list>
</#macro>

<#macro fulltable_by_list_4test list>
 <table id='customers'>
 <@table_head_4test/>
 <@table_by_list_4test list/>
 </table>
</#macro>

