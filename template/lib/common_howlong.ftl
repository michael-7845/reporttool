<#-- bug划分等级的得分参考 -->
<#assign A=report.aType/>
<#assign B=report.bType/>
<#assign C=report.cType/>

<#-- 用map打印表 -->
<#macro by_map_howlong map>
<#assign keys=map?keys>
<#list ['90+', '60+', '30+', '15+', '7+', '0-7'] as key>
 <#assign list=map[key]>
 ${key}天(${list?size})<br />
 <@fulltable_by_list_howlong list/>
</#list>
</#macro>

<#-- 打印表头 -->
<#macro table_head_howlong>
<tr>
  <th field='id'>ID</th>
  <th field='howlong'>已存活时间</th>
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
<#macro line_howlong map color>
  <#assign id=map['bug_id']>
  <#assign weight=map['weight']?number>
  <#assign desc=map['short_desc']>
  <#assign sev=map['bug_severity']>
  <#assign encounter=map['cf_encounter']>
  <#assign repeat=map['cf_repeatability']>
  <#assign status=map['bug_status']>
  <#assign version=map['version']>
  <#assign howlong=map['howlong']>
  <td class=${color} field='id'><a href='<@bug_link id/>' target='_blank'>${id}</a></td>
  <td class=${color} field='howlong'>${howlong}</td>
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
<#macro table_by_list_howlong list>
<#list list as line_map>
<tr>
  <#assign weight=line_map['weight']?number>
<#if (weight>=A)>
  <@line_howlong line_map "red"/>
<#elseif (weight>=B)> 
  <@line_howlong line_map "darkred"/>
<#elseif (weight>=C)>
  <@line_howlong line_map "blue"/> 
<#else>
  <@line_howlong line_map "black"/> 
</#if>
</tr>
</#list>
</#macro>

<#macro fulltable_by_list_howlong list>
 <table id='customers'>
 <@table_head_howlong/>
 <@table_by_list_howlong list/>
 </table>
</#macro>

