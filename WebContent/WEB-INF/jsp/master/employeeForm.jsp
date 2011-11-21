<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<table width="100%">
	<tr>
		<td>
			<c:if test="${errMsg != null}">
				<div style="align:center; width: 99%; margin: auto;" class="ui-state-error ui-corner-all">
					<c:out value='${errMsg}' escapeXml="false" />
				</div>
			</c:if>
			<form:form commandName="form" id="form" class="jqtransform" action="employee.html?do=save">
				<form:hidden path="employeeID" />
				<table width="100%">
					<col width="40%">
					<col>
					<%--tr>
						<td width="40%"><label><fmt:message key="employeeID" />:</label></td>
						<td><div class="rowElem"><form:input path="employeeID" readonly="true" class="textboxMockup" /></div></td>
					</tr--%>
					<tr>
						<td><label><fmt:message key="employeeCode" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input path="employeeCode" class="textboxMockup" maxlength="5" /> <label class="error" for="employeeCode" generated="true" style="display: none; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="name" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input type="text" path="name" class="textboxMockup" maxlength="100" /> <label class="error" for="name" generated="true" style="display: none; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="surname" />:</label></td>
						<td><div class="rowElem"><form:input type="text" path="surname" class="textboxMockup" maxlength="100" /></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="position" />:</label></td>
						<td>
							<div class="rowElem">
								<form:select path="roleID">
									<form:option value=""/>
									<form:options items="${roleList}" itemValue="roleID" itemLabel="name" />
								</form:select>
								<label class="error" for="roleID" generated="true" style="display: none; padding-left:10px"></label>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="userName" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input type="text" path="login" class="textboxMockup" maxlength="100"/> <label class="error" for="userName" generated="true" style="display: none; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="password" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input type="password" path="password" class="textboxMockup" maxlength="100"/> <label class="error" for="password" generated="true" style="display: none; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="confirmPassword" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input type="password" path="confirmPwd" class="textboxMockup" maxlength="100"/> <label class="error" for="confirmPwd" generated="true" style="display: none; padding-left:10px"></label></div></td>
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
			employeeCode: "required",
			name: "required",
			userName: "required",
			password: {
				required: true
			},
			confirmPwd: {
				required: true,
				equalTo: "#password"
			},
			roleID: "required"
		}
	});
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
});

</script>