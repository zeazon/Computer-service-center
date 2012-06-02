<%@page pageEncoding="TIS620" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=tis-620" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="TIS620">
<title>ระบบจัดการงานซ่อม</title>
<link rel="shortcut icon" href="././images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pro_dropdown_2/pro_dropdown_2.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/jQuery/jquery-ui-1.8.13.custom.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/jqGrid/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/jqTransform/jqtransform.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/default.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/jQuery-calendar/2bytes.calendars.picker.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/jQuery-datetimeentry/jquery.datetimeentry.css">

<script src="<%=request.getContextPath()%>/script/jQuery/jquery-1.6.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/jQuery/jquery-ui-1.8.13.custom.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/jqGrid/jquery.jqGrid.src-fixed3.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/jqGrid/grid.locale-th.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/jqTransform/jquery.jqtransform.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/jQueryValidate/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/jQueryValidate/messages_th.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/inputFieldEffect.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/jQuery-calendar/jquery.calendars.all.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/jQuery-calendar/jquery.calendars.thai.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/jQuery-calendar/jquery.calendars.picker-th.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/jQuery-calendar/jquery.calendars.lang.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/jQuery-calendar/jquery.calendars.thai-th.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/jQuery-datetimeentry/jquery.datetimeentry.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/script/autoComplete.js" type="text/javascript"></script>
</head>
<body marginheight="0" marginwidth="0">
<div style="background: url('./images/header_logo.png'); background-repeat:no-repeat; height: 80px;" align="right">
<br>
<br>
<br>
<font size="-1" style="margin-right:10px">
Loggin as <c:if test="${not empty UserLogin}"><c:out value="${UserLogin.login}"/></c:if>, <a href="logout.html">Logout</a>
</font>
</div>
<nav>
	<c:if test="${menuStr != null}">
		<c:out value='${menuStr}' escapeXml="false"/>
	</c:if>
</nav>
<tiles:insertAttribute name="body" />
</body>
</html>