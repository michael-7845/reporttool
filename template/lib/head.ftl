<#-- 打印html头部 -->
<#macro html_head>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <style type="text/css">
  h3 {text-indent: 1em;}
  h4 {text-indent: 2em;}
  [indent=h3] {text-indent: 1em;}
  [indent=h4] {text-indent: 2em;}
  
  table
  {
  font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
  width:100%;
  border-collapse:collapse;
  }
  table, td, th
  {
  font-size:0.9em;
  border:1px solid #98bf21;
  padding:3px 7px 2px 7px;
  }
  th
  {
  font-size:0.9em;
  text-align:left;
  padding-top:5px;
  padding-bottom:4px;
  background-color:#A7C942;
  color:#ffffff;
  }
  tr.alt td 
  {
  background-color:#EAF2D3;
  }
  
  .red {color:red;}
  .darkred {color:darkred;}
  .green {color:green;}
  .blue {color:blue;}
  .black {color:black;}
  .orange {color:orange;}
  .gray {color:gray;}
  
  .comment {color:gray;font-size:0.8em;font-style:italic;}
  
  [field=id] {width:50px;height:20px;}
  [field=score] {width:40px;height:20px;}
  [field=desc] {width:300px;height:20px;}
  [field=sev] {width:100px;height:20px;}
  [field=enc] {width:80px;height:20px;}
  [field=rep] {width:80px;height:20px;}
  [field=status] {width:80px;height:20px;}
  [field=ver] {width:220px;height:20px;}
  [field=cause] {width:220px;height:20px;}
  [field=resver] {width:120px;height:20px;}
  [field=since] {width:120px;height:20px;}
  [field=howlong] {width:100px;height:20px;}
</style>
</head>
</#macro>
