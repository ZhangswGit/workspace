<!DOCTYPE html> 
<html lang="en"> 
<head> 
	<title>DEMO_B主页</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
</head> 
	<body>
	<#if Request.name?exists>  
  	${Request.name}
	<#else>
	${account}
   </#if> 
    
</body>
</html>