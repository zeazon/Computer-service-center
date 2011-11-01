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
			<form:form commandName="form" id="form" class="jqtransform" action="changePassword.html?do=save">
				<table width="100%">
					<col width="40%">
					<col>
					<tr>
						<td><label><fmt:message key="password" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input type="password" path="oldPassword" class="textboxMockup" maxlength="100"/> <label class="error" for="oldPassword" generated="true" style="display: none; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="newPassword" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:password path="newPassword" class="textboxMockup" maxlength="100"/> <label class="error" for="newPassword" generated="true" style="display: none; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="confirmPassword" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input type="password" path="confirmNewPassword" class="textboxMockup" maxlength="100"/> <label class="error" for="confirmNewPassword" generated="true" style="display: none; padding-left:10px"></label></div></td>
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
			name: "required",
			userName: "required",
			oldPassword: {
				required: true
			},
			newPassword: {
				required: true
			},
			confirmNewPassword: {
				required: true,
				equalTo: "#newPassword"
			}
		}
	});
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();

});
</script>