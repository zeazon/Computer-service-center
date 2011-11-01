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
			<form:form commandName="form" id="form" class="jqtransform" action="role.html?do=save">
				<input type="hidden" name="mode" value="${mode}"/>
				<table width="100%">
					<tr>
						<td width="40%" align="right"><label style="float:right; margin-top:5px;"><fmt:message key="roleID" />:</label></td>
						<td><div class="rowElem" style="margin: 5px"><form:input path="roleID" id="roleID" readonly="true" class="textboxMockup" /></div></td>
					</tr>
					<tr>
						<td align="right"><label style="float:right; margin-top:5px;"><fmt:message key="name" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem" style="margin: 5px"><form:input path="name" id="name" class="textboxMockup" /> <label class="error" for="name" generated="true" style="display: none; float:left; padding-left:10px; margin-top:5px; color: red;"></label></div></td>
					</tr>
					<tr>
						<td colspan="2">
							<fmt:message key="menu"/>:<br>
							${treeMenu}
						</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<div class="rowElem">
								<button id="submitButton" class=" jqTransformButton" type="submit" name="">
									<span>
										<span><fmt:message key='button.ok' /></span>
									</span>
								</button>
							
								<!-- input type="submit" id="submitButton" value="<fmt:message key='button.ok' />" /-->
							</div>
						</td>
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
	//$("form.jqtransform").jqTransform();
	//$("submitButton").jqTransInputButton();
	
	$('#menuTree').checkboxTree({
		collapseImage: '././././images/checkboxtree/downArrow.gif',
		expandImage: '././././images/checkboxtree/rightArrow.gif'
	});
});

</script>