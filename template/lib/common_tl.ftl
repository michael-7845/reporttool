<#-- 打印testlink统计信息表 -->
<#macro table_tl_statistics>
<#assign allsize=tlass?size>
<tr>
  <th>用例总数</th>
  <th>未跑</th>
  <th>通过</th>
  <th>失败</th>
  <th>阻塞</th>
  <th>不可执行</th>
</tr>
<tr>
  <td>${allsize}</td>
  <td>${tlnotrun?size}</td>
  <td>${tlpass?size}</td>
  <td>${tlfail?size}</td>
  <td>${tlblock?size}</td>
  <td>${tlnotavail?size}</td>
</tr>
<#if (allsize>0)>
<tr>
  <td>-</td>
  <td>${100*tlnotrun?size/allsize}%</td>
  <td>${100*tlpass?size/allsize}%</td>
  <td>${100*tlfail?size/allsize}%</td>
  <td>${100*tlblock?size/allsize}%</td>
  <td>${100*tlnotavail?size/allsize}%</td>
</tr>
</#if>
</#macro>

<#-- 打印testlink执行情况一览表头 -->
<#macro table_tl_head>
<tr>
  <th>测试套件</th>
  <th>测试用例</th>
  <th>执行结果</th>
  <th>执行备注</th>
</tr>
</#macro>

<#-- 打印表的一行，字体颜色color -->
<#macro line_tl map color>
  <#assign ts=map['tsname']>
  <#assign tc=map['tcname']>
  <#assign status=map['status']>
  <#assign notes=map['notes']>
  <#if (status='f')>
    <#assign status2='failed'>
  <#elseif (status='p')> 
    <#assign status2='passed'>
  <#elseif (status='b')> 
    <#assign status2='blocked'>
  <#elseif (status='x')> 
    <#assign status2='not available'>
  <#else>
    <#assign status2='not run'>
  </#if>
  <td class=${color} field='ts'>${ts}</td>
  <td class=${color} field='tc'>${tc}</td>
  <td class=${color} field='status'>${status2}</td>
  <td class=${color} field='notes'>${notes}</td>
</#macro>

<#-- 用list打印表 -->
<#macro table_tl_by_list list>
<#list list as line_map>
<#assign status=line_map['status']>
<#assign ts=line_map['tsname']>
<tr>  
<#if (status='f')>
  <@line_tl line_map "red"/>
<#elseif (status='p')> 
  <@line_tl line_map "green"/>
<#elseif (status='b')> 
  <@line_tl line_map "darkred"/>
<#elseif (status='x')> 
  <@line_tl line_map "blue"/>
<#else>
  <@line_tl line_map "black"/> 
</#if>
</tr>
</#list>
</#macro>

<#macro fulltable_tl_by_list list>
 <table id='customers'>
 <@table_tl_head/>
 <@table_tl_by_list list/>
 </table>
</#macro>

<#-- 用map打印表 -->
<#macro table_tl_by_map map>
<#assign keys=map?keys>
<#list keys as key>
 <#assign list=map[key]>
 <#-- ${key}(${list?size})<br /> -->
 <@fulltable_tl_by_list list/>
</#list>
</#macro>

<#-- 用根据suite分类的list打印表，suite直接用颜色区分 -->
<#macro table_tl_by_tsmap map>
<#assign x=0>
<#assign keys=map?keys>
<#list keys as key>
 <#assign x=x+1>
 <#assign list=map[key]>
 <#list list as line_map>
 <#assign status=line_map['status']>
<#if (x%2=0)>
<tr class=alt>
<#else>
<tr>
</#if>
<#if (status='f')>
  <@line_tl line_map "red"/>
<#elseif (status='p')> 
  <@line_tl line_map "green"/>
<#elseif (status='b')> 
  <@line_tl line_map "darkred"/>
<#elseif (status='x')> 
  <@line_tl line_map "blue"/>
<#else>
  <@line_tl line_map "black"/> 
</#if>
</tr>
 </#list>
</#list>
</#macro>

<#macro fulltable_tl_by_tsmap map>
 <table>
 <@table_tl_head/>
 <@table_tl_by_tsmap map/>
 </table>
</#macro>
