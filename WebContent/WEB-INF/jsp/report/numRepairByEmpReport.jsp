<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<table width="100%">
	<tr>
		<td>
			<form:form commandName="searchForm" id="searchForm" class="jqtransform" action="numRepairByEmpReport.html?do=printReport" method="post" target="_blank">
				<table>
					<tr>
						<td><label><fmt:message key="date" />:</label></td>
						<td><div class="rowElem" style="z-index:200"><form:input path="startDate" class="textboxMockup" id="dateInput" size="9"/><span style="float:left">&nbsp;-&nbsp;</span><form:input path="endDate" type="text" class="textboxMockup" id="endDateInput" size="9"/></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="employee" />:</label></td>
						<td>
							<div class="rowElem" style="z-index:100">
								<form:select path="employeeID" id="employeeID" cssClass="selectSearch">
									<form:option value="">All</form:option>
									<form:options items="${employeeList}" itemValue="employeeID" itemLabel="name"/>
								</form:select>
							</div>
						</td>
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
	//$("#dateInput").calendarsPicker($.extend({calendar: $.calendars.instance('thai','th')}));
	//$("#endDateInput").calendarsPicker($.extend({calendar: $.calendars.instance('thai','th')}));
	$("#dateInput").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
	$("#endDateInput").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));

	jQuery().ready(function (){
		//find all form with class jqtransform and apply the plugin
		$("form.jqtransform").jqTransform();
		
		$("#searchForm").validate({
			rules: {
				startDate: "required"
			}
		});
	});
</script>