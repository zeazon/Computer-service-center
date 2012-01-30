<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<table width="100%">
	<tr>
		<td>
			<form:form commandName="searchForm" id="searchForm" class="jqtransform" action="numSaleByEmpReport.html?do=printReport" method="post" target="_blank">
				<table>
					<tr>
						<td><label><fmt:message key="month" />:</label></td>
						<td>
							<div class="rowElem" style="z-index:100">
								<form:select path="month" id="month">
									<form:option value="1"><fmt:message key="january" /></form:option>
									<form:option value="2"><fmt:message key="february" /></form:option>
									<form:option value="3"><fmt:message key="march" /></form:option>
									<form:option value="4"><fmt:message key="april" /></form:option>
									<form:option value="5"><fmt:message key="may" /></form:option>
									<form:option value="6"><fmt:message key="june" /></form:option>
									<form:option value="7"><fmt:message key="july" /></form:option>
									<form:option value="8"><fmt:message key="august" /></form:option>
									<form:option value="9"><fmt:message key="september" /></form:option>
									<form:option value="10"><fmt:message key="october" /></form:option>
									<form:option value="11"><fmt:message key="november" /></form:option>
									<form:option value="12"><fmt:message key="december" /></form:option>
								</form:select>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="year" />:<font color="red">*</font></label></td>
						<td><div class="rowElem" style="z-index:200"><form:input path="year" id="year" size="4" maxlength="4"/></div></td>
					</tr>
					<tr>
						<td colspan="2"><div class="rowElem"><input type="submit" id="searchButton" value="<fmt:message key='button.search' />" /></div></td>
					</tr>
				</table>
			</form:form>
		</td>
	</tr>
	
</table>

<script type="text/javascript">
	jQuery().ready(function (){
		//find all form with class jqtransform and apply the plugin
		$("form.jqtransform").jqTransform();
		
		$("#searchForm").validate({
			rules: {
				year: "required number"
			}
		});
	});
</script>