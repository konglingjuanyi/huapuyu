<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!-- 
 struts1的登陆
 -->
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html"/>
		<title>首页</title>
	</head>
	
	<body>
		<form action="LoginAction.do" method="post">
			name : <input type="text" name="name"><br/>
			pass : <input type="passWord" name="passWord"/>
					<input type="submit" value="确定">
		</form>
	</body>
</html>