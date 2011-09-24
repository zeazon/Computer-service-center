<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<table width="100%">
	<tr>
		<td>
			<c:if test="${errMsg != null}">
				<div style="align:center; width: 99%; margin: auto;" class="ui-state-error ui-corner-all">
					<c:out value='${errMsg}' escapeXml="false" />
				</div>
			</c:if>
			<form:form commandName="form" id="form" class="jqtransform" action="shopCustomer.html">
				<table width="100%">
					<tr>
						<td width="40%"><label><fmt:message key="customerID" />:</label></td>
						<td><div class="rowElem">${form.cuscod}</div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="name" />:</label></td>
						<td>
							<div class="rowElem">
								<c:if test="${fn:trim(form.prenam) != ''}">${fn:trim(form.prenam)}&nbsp;</c:if>
								<c:if test="${fn:trim(form.cusnam) != ''}">${fn:trim(form.cusnam)}</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="address" />:</label></td>
						<td>
							<div class="rowElem">
								<c:if test="${fn:trim(form.addr01) != ''}">${fn:trim(form.addr01)}&nbsp;</c:if>
								<c:if test="${fn:trim(form.addr02) != ''}">${fn:trim(form.addr02)}&nbsp;</c:if>
								<c:if test="${fn:trim(form.addr03) != ''}">${fn:trim(form.addr03)}&nbsp;</c:if>
								<c:if test="${fn:trim(form.zipcod) != ''}">${fn:trim(form.zipcod)}&nbsp;</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="tel" />:</label></td>
						<td><div class="rowElem">${form.telnum}</div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="contactName" />:</label></td>
						<td><div class="rowElem">${form.contact}&nbsp;</td>
					</tr>
					<tr>
						<td><label><fmt:message key="remark" />:</label></td>
						<td><div class="rowElem">${form.remark}&nbsp;</td>
					</tr>
					<tr align="center">
						<td colspan="2"><div class="rowElem"><input type="submit" value="<fmt:message key='button.ok' />" /></div></td>
					</tr>
				</table>
			</form:form>
		</td>
	</tr>
</table>

<script type="text/javascript">

$(document).ready(function(){
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
	
});

</script>