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
			<form:form commandName="form" id="form" class="jqtransform" action="brand.html?do=save">
				<table width="100%">
					<tr>
						<td width="40%"><label><fmt:message key="brandID" />:</label></td>
						<td><div class="rowElem"><form:input type="text" path="brandID" readonly="true" class="textboxMockup" /></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="name" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input type="text" path="name" class="textboxMockup" /> <label class="error" for="name" generated="true" style="display: none; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:7px;"><label><fmt:message key="type" />:</label></td>
						<td>
							<div class="rowElem">
								<table cellpadding="1">
									<c:set var="rowEleNum" value="3"/> <!-- Number of element for each row -->
									<!-- Generate row -->
									<c:forEach var="i" begin="0" end="${fn:length(typeList)}" step="${rowEleNum}">
										<!-- If j mod rowEleNum == 0, generate new row -->
										<c:if test="${j mod rowEleNum == 0 }">
										<tr>
										</c:if>
										<!-- Generate column -->
										<c:forEach var="j" begin="${i}" end="${i+(rowEleNum-1)}" step="1">
											<!-- If value of j more than array length, not write and if typeID is null, not write -->
											<c:if test="${j < fn:length(typeList) && typeList[j].typeID != null}">
												<td><div style="float:left" ><form:checkbox path="typeID" value="${typeList[j].typeID}" label="${typeList[j].name}" /></div></td>
											</c:if>
										</c:forEach>
										<c:if test="${j mod rowEleNum == 0 }">
										</tr>
										</c:if>
									</c:forEach>
								</table>
								<!-- form:checkboxes path="typeID" items="${typeList}" itemValue="typeID" itemLabel="name" element="div style='float:left'" /-->
							</div>
						</td>
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
	$("#form").validate({
		rules: {
			name: "required"
		}
	});
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
});

</script>