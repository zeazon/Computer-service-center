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
			<form:form commandName="form" id="form" class="jqtransform" action="receivedOutsiteService.html?do=save">
				<table width="100%">
					<tr>
						<td width="13%"><label><fmt:message key="outsiteServiceID" />:</label></td>
						<td colspan="2"><div class="rowElem">${form.outsiteServiceID}<form:hidden path="outsiteServiceID" /></div></td>
						<td><label><fmt:message key="date" />:</label></td>
						<td colspan="2"><div class="rowElem">${form.outsiteServiceDate}</div></td>
					</tr>
					<tr>
						<td><div class="rowElem"><label><fmt:message key="outsiteServiceServiceType" />:</label></div></td>
						<td colspan="5">
							<div class="rowElem">
								<label style="float:left; margin-top:0px;">
									<c:if test="${form.serviceType == 'warranty'}">
										<fmt:message key="outsiteServiceServiceType_inWarranty" />
									</c:if>
									<c:if test="${form.serviceType == 'repair'}">
										<fmt:message key="outsiteServiceServiceType_outWarranty" />
									</c:if>
								</label>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrderID" />:</label></td>
						<td colspan="5"><div class="rowElem">${form.serviceOrderID}</div></td>
					</tr>
					<tr>
						<td colspan="6">
							<fieldset style="background-color:#eeeeee; margin: 0 10px 0 10px; ">
								<legend class="ui-widget-header ui-corner-all">&nbsp;<fmt:message key="serviceOrder_data" />&nbsp;</legend>
								<table width="100%">
									<col width="13%">
									<col width="18%">
									<col width="6%">
									<col width="14%">
									<col width="10%">
									<col width="13%">
									<col width="13%">
									<col width="13%">
									<tr>
										<td width="13%"><label><fmt:message key="serviceOrderDate" />:</label></td>
										<td colspan="7"><div class="rowElem"><span id="serviceOrderDate">${form.serviceOrder.serviceOrderDate}&nbsp;</span></div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="serviceOrderType" />:</label></td>
										<td colspan="7">
											<div class="rowElem">
												<span id="serviceOrderType">
													<c:if test="${form.serviceOrder.serviceType == '1'}">
														<fmt:message key="serviceOrderType_guarantee" />&nbsp;<fmt:message key="guarantee_No" />&nbsp;<c:out value="${form.serviceOrder.guaranteeNo}"/>
													</c:if>
													<c:if test="${form.serviceOrder.serviceType == '2'}">
														<fmt:message key="serviceOrderType_repair" />
													</c:if>
													<c:if test="${form.serviceOrder.serviceType == '3'}">
														<fmt:message key="serviceOrderType_claim" />
													</c:if>
													<c:if test="${form.serviceOrder.serviceType == '4'}">
														<fmt:message key="serviceOrderType_outsiteService" />&nbsp;<fmt:message key="reference" />&nbsp;<c:out value="${form.serviceOrder.refJobID}"/>
													</c:if>
													<c:if test="${form.serviceOrder.serviceType == '5'}">
														<fmt:message key="serviceOrderType_refix" />&nbsp;<fmt:message key="reference" />&nbsp;<c:out value="${form.serviceOrder.refServiceOrder}"/>
													</c:if>
													&nbsp;
												</span>
											</div>
										</td>
									</tr>
									<tr>
										<td><label><fmt:message key="appointmentDate"/>:</label></td>
										<td colspan="7"><div class="rowElem"><span id="appointmentDate">${form.serviceOrder.appointmentDate}&nbsp;</span></div></td>
									</tr>
									<!-- tr align="left">
										<td colspan="8">
											<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="productDetail" /></u></b></div>
										</td>
									</tr>
									<tr>
										<td><label><fmt:message key="productID" />:</label></td>
										<td colspan="7"><div class="rowElem"><span id="productID">${form.serviceOrder.product.productID}&nbsp;</span></div></td>
									</tr>
									<!-- tr>
										<td><label><fmt:message key="type" />:</label></td>
										<td colspan="2">
											<div class="rowElem"><span id="type">${form.serviceOrder.product.type.name}&nbsp;</span></div>
										</td>
										<td><label><fmt:message key="brand" />:</label></td>
										<td colspan="4">
											<div class="rowElem"><span id="brand">${form.serviceOrder.product.brand.name}&nbsp;</span></div>
										</td>
									</tr>
									<tr>
										<td><label><fmt:message key="model" />:</label></td>
										<td colspan="2"><div class="rowElem"><span id="model">${form.serviceOrder.product.model.name}&nbsp;</span></div></td>
										<td><label><fmt:message key="serialNo" />:</label></td>
										<td colspan="4"><div class="rowElem"><span id="serialNo">${form.serviceOrder.product.serialNo}&nbsp;</span></div></td>
									</tr-->
									<!-- tr>
										<td><label><fmt:message key="type" />:</label></td>
										<td><div class="rowElem"><span id="type">${form.serviceOrder.product.type.name}&nbsp;</span></div></td>
										<td><label><fmt:message key="brand" />:</label></td>
										<td><div class="rowElem"><span id="brand">${form.serviceOrder.product.brand.name}&nbsp;</span></div></td>
										<td><label><fmt:message key="model" />:</label></td>
										<td><div class="rowElem"><span id="model">${form.serviceOrder.product.model.name}&nbsp;</span></span></div></td>
										<td><label><fmt:message key="serialNo" />:</label></td>
										<td><div class="rowElem"><span id="serialNo">${form.serviceOrder.product.serialNo}&nbsp;</span></div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="accessories" />:</label></td>
										<td colspan="2"><div class="rowElem"><span id="accessories">${form.serviceOrder.accessories}</span></div></td>
										<td><label><fmt:message key="serviceOrder_desc" />:</label></td>
										<td colspan="4"><div class="rowElem"><span id="serviceOrder_desc">${form.serviceOrder.description}&nbsp;</span></div></td>
									</tr>
									<tr>
										<%--td valign="top" style="padding-top:9px;"><label><fmt:message key="serviceOrder_problem" />:</label></td--%>
										<%--td colspan="5" align="left"><div class="rowElem"><textarea rows="5" col="30" readonly="readonly" class="ignore textareaMockup" style="width:98%" name="problem" ></textarea></div></td--%>
										<td><label><fmt:message key="serviceOrder_problem" />:</label></td>
										<td colspan="7" align="left"><div class="rowElem"><span id="problem" >${form.serviceOrder.problem}&nbsp;</span></div></td>
									</tr>
									<tr align="left">
										<td colspan="8">
											<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="customerDetail" /></u></b></div>
										</td>
									</tr>
									<tr>
										<td><label><fmt:message key="customerID" />:</label></td>
										<td align="left" colspan="7"><div class="rowElem"><span id="customerID">${form.serviceOrder.customer.customerID}&nbsp;</span></div></td>
										<%--d><label><fmt:message key="company" />:</label></td>
										<td colspan="3" align="left"><div class="rowElem"><span id="company">${form.serviceOrder.customer.company}&nbsp;</span></div></td--%>
									</tr>
									<tr>
										<td><label><fmt:message key="contactName" />:</label></td>
										<td><div class="rowElem"><span id="contactName">${form.serviceOrder.customer.name}</span></div></td>
										<td><label><fmt:message key="email" />:</label></td>
										<td><div class="rowElem"><span id="email">&nbsp;${form.serviceOrder.customer.email}</span></div></td>
										<td><label><fmt:message key="tel" />:</label></td>
										<td><div class="rowElem"><span id="tel">${form.serviceOrder.customer.tel}&nbsp;</span></div></td>
										<td><label><fmt:message key="mobileTel" />:</label></td>
										<td width="120px"><div class="rowElem"><span id="mobileTel">${form.serviceOrder.customer.mobileTel}</span></div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="address" />:</label></td>
										<td colspan="7" align="left"><div class="rowElem"><span id="address">${fullAddr}&nbsp;</span></div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="deliveryCustomer" />:</label></td>
										<td><div class="rowElem"><span id="deliveryCustomer">${form.serviceOrder.deliveryCustomer}&nbsp;</span></div></td>
										<td><label><fmt:message key="email" />:</label></td>
										<td><div class="rowElem"><span id="deliveryEmail">${form.serviceOrder.deliveryEmail}&nbsp;</span></div></td>
										<td><label><fmt:message key="tel" />:</label></td>
										<td><div class="rowElem"><span id="deliveryTel">${form.serviceOrder.deliveryTel}&nbsp;</span></div></td>
										<td><label><fmt:message key="mobileTel"/>:</label></td>
										<td><div class="rowElem"><span id="deliveryMobileTel">${form.serviceOrder.deliveryMobileTel}&nbsp;</span></div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="serviceOrder_empCreate" />:</label></td>
										<td colspan="7"><div class="rowElem"><span id="empOpen">${form.serviceOrder.empOpen.name}&nbsp;${form.serviceOrder.empOpen.surname}</span></div></td>
									</tr-->
								</table>
							</fieldset>
						</td>
					</tr>
					<tr>
						<td colspan="6">
							<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="customerDetail" /></u></b></div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="contactName" />:</label></td>
						<td colspan="5"><div class="rowElem"><span id="customerName">${form.customerName}</span></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="tel" />:</label></td>
						<td colspan="2"><div class="rowElem"><span id="tel">${form.tel}</span></div></td>
						<td><label><fmt:message key="mobileTel" />:</label></td>
						<td colspan="2"><div class="rowElem"><span id="mobileTel">${form.mobileTel}</span></div></td>
					</tr>
					<tr>
						<td colspan="6">
							<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="productDetail" /></u></b></div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="type" />:</label></td>
						<td colspan="2">
							<div class="rowElem">
								<span id="type">${form.typeName}</span>
							</div>
						</td>
						<td><label><fmt:message key="brand" />:</label></td>
						<td colspan="2">
							<div class="rowElem">
								<span id="brand">${form.brandName}</span>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="model" />:</label></td>
						<td colspan="2">
							<div class="rowElem">
								<span id="model">${form.modelName}</span>
							</div>
						</td>
						<td><label><fmt:message key="serialNo" />:</label></td>
						<td colspan="2"><div class="rowElem"><span id="serialNo">${form.serialNo}</span></div></td>
					</tr>
					<tr>
						<td style="width:13%"><div class="rowElem"><label><fmt:message key="outsiteService_accessories" />:</label></div></td>
						<td colspan="5"><div class="rowElem">${form.accessories}</div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrder_problem" />:</label></td>
						<td colspan="5" align="left"><div class="rowElem"><span id="os_problem" >${form.problem}&nbsp;</span></div></td>
					</tr>
					<tr>
						<td><div class="rowElem"><label><fmt:message key="outsiteService_outsiteCompany" />:</label></div></td>
						<td colspan="2">
							<div class="rowElem">
								${form.outsiteCompanyName }
							</div>
						</td>
						<td><div class="rowElem"><label><fmt:message key="transportCompany" />:</label></div></td>
						<td colspan="2">
							<div class="rowElem">
								${form.transportCompanyName }
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="outsiteService_sentDate" />:</label></td>
						<td colspan="2"><div class="rowElem">${form.sentDate}</div></td>
						<td><label><fmt:message key="outsiteService_sentTransportNo" />:</label></td>
						<td colspan="2"><div class="rowElem">${form.sentTransportNo}</div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="outsiteService_receivedDate" />:<font color="red">*</font></label></td>
						<td colspan="2"><div class="rowElem"><form:input class="required textboxMockup" id="receivedDate" path="receivedDate" readonly="readonly" size="10"/></div></td>
						<td><label><fmt:message key="outsiteService_receivedTransportNo" />:<font color="red">*</font></label></td>
						<td colspan="2"><div class="rowElem"><form:input class="required textboxMockup" path="receivedTransportNo" /></div></td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:9px;"><label><fmt:message key="outsiteService_repairing" />:</label></td>
						<td colspan="5" align="left"><div class="rowElem"><form:textarea path="repairing" rows="5" col="30" class="textareaMockup" style="width:98%"></form:textarea><label class="error" for="repairing" generated="true" style="display: none; float:left; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td></td>
						<td colspan="5">
							<div class="rowElem">
								<form:radiobutton path="costing" id="costing_cost" value="cost"/> <label style="float:left;"><fmt:message key="outsiteService_costing_cost" /></label> <form:radiobutton path="costing" id="costing_free" value="free"/> <label style="float:left;"><fmt:message key="outsiteService_costing_free" /></label>
							</div>
						</td>
					</tr>
					<tr>
						<td width="13%"></td>
						<td colspan="5">
							<table id="serviceTable">
								<tr>
									<td colspan="2"><fmt:message key="serviceList" /></td>
								</tr>
								<c:forEach var="i" begin="1" end="4" step="1" varStatus ="status">
								<tr class="serviceList">
									<td><form:input path="serviceDesc_${i}" class="serviceDesc textboxMockup" onBlur="calculateNetAmount();"/></td>
									<td><form:input path="servicePrice_${i}" class="number servicePrice textboxMockup" style="text-align:right" size="4" onBlur="calculateNetAmount();"/></td>
								</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrder_netAmount" />:</label></td>
						<td colspan="5"><div class="rowElem"><form:input path="netAmount" class="textboxMockup" id="netAmount" readonly="true" cssStyle="text-align:right" /><span style="float:left; margin-top:6px">&nbsp;<fmt:message key="baht" /></span></div></td>
					</tr>
					<!-- tr>
						<td></td>
						<td colspan="5">
							<div class="rowElem">
								<form:radiobutton path="costing" id="costing_cost" value="cost"/> <label style="float:left;"><fmt:message key="outsiteService_costing_cost" /></label> <form:radiobutton path="costing" id="costing_free" value="free"/> <label style="float:left;"><fmt:message key="outsiteService_costing_free" /></label>
							</div>
						</td>
					</tr>
					<tr id="costingRow" style="display:none">
						<td width="13%">&nbsp;</td>
						<td colspan="5">
							<table id="serviceTable">
								<tr>
									<td colspan="2"><fmt:message key="serviceList" /></td>
								</tr>
								<tr>
									<td colspan="2"><input type="button" id="service_add" value='<fmt:message key="button.add" />'/></td>
								</tr>
								<tr id="serviceList_1" class="serviceList">
									<%--td><form:input path="serviceDesc" class="textboxMockup" value='<fmt:message key="serviceCost" />' /></td--%>
									<td><div class="rowElem"><form:input path="serviceDesc" cssClass="textboxMockup serviceDesc" id="serviceDesc_1" /> <label class="error" for="serviceDesc_1" generated="true" style="display: none; padding-left:10px"></label></div></td>
									<%--td><input type="text" class="textboxMockup" style="text-align:right" size="6"/>&nbsp;<label style="margin-top:3px; margin-left:3px; float:left"><fmt:message key="baht" /></label></td--%>
									<td width="500px"><div class="rowElem"><form:input path="servicePrice" cssClass="number textboxMockup servicePrice" style="text-align:right" size="6" id="servicePrice_1" />&nbsp;<label style="margin-top:6px; margin-left:3px; float:left"><fmt:message key="baht" /></label> <label class="error" for="servicePrice_1" generated="true" style="display: none; padding-left:10px"></label></div></td>
								</tr>
							</table>
							<br/>
							<table id="costingTable">
								<tr>
									<td colspan="2"><fmt:message key="fixList" /></td>
								</tr>
								<tr>
									<td colspan="2"><input type="button" id="repair_add" value='<fmt:message key="button.add" />'/></td>
								</tr>
								<tr id="repairList_1" class="repairList">
									<%--td><input type="text" class="textboxMockup"/></td>
									<td><input type="text" class="textboxMockup" style="text-align:right;" size="6"/>&nbsp;<label style="margin-top:3px; margin-left:3px; float:left"><fmt:message key="baht" /></label></td--%>
									<td><div class="rowElem"><form:input path="repairDesc" cssClass="textboxMockup repairDesc" id="repairDesc_1" /></div></td>
									<td width="500px"><div class="rowElem"><form:input path="repairPrice" cssClass="number textboxMockup repairPrice" style="text-align:right" size="6" id="repairPrice_1" />&nbsp;<label style="margin-top:6px; margin-left:3px; float:left"><fmt:message key="baht" /></label> <label class="error" for="repairPrice_1" generated="true" style="display: none; padding-left:10px"></label></div></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="outsiteService_netAmount" />:</label></td>
						<td colspan="5"><div class="rowElem"><form:input path="netAmount" id="netAmount" class="number textboxMockup" style="text-align:right" readonly="true" />&nbsp;<label style="margin-top:6px; margin-left:3px; float:left"><fmt:message key="baht" /></label></div></td>
					</tr-->
					<tr>
						<td colspan="6" align="center"><div class="rowElem"><input type="submit" value="<fmt:message key='button.ok' />" /></div></td>
					</tr>
				</table>
			</form:form>
		</td>
	</tr>
</table>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#form").validate({
		//errorPlacement: function(error, element) {
            //error.appendTo(element.parent("td").parent("tr").find(".error"));
			//error.appendTo(element.parent("td").find(".error"));
        //},
		//rules: {
		//	serviceOrderID: "required"
		//}
	});
	
/*	$("#serviceDesc_2").validate({
	});
	
	$("#servicePrice_2").validate({
	});
	
	$(".serviceDesc").validate({
		
	});
	
	$(".servicePrice").validate({
		
	});
*/	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
	
	$('#receivedDate').calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));

	
	$('#costing_cost').click(function() {
/*		$('#costingRow').css("display", "table-row");
		//$('#serviceDesc_1').rules("add", {required: function(element){ return $('#servicePrice_1').val() != ''; }});
		//$('#servicePrice_1').rules("add", {required: function(element){ return $('#serviceDesc_1').val() != ''; }});
		
		$('#servicePrice_1').blur(function(){
			/* 19/8/2554 disable validate */
			
			/*$('#serviceDesc_1').rules("remove");
			
			var tst = $('#servicePrice_1').val();
			if (tst !== "" || tst !== "0") {
				$('#serviceDesc_1').rules("add", {
					required: true
				});
			}*/
			
/*			calculateNetAmount();
		});
		
		$('#serviceDesc_1').blur(function() {
			/* 19/8/2554 disable validate */
			
			//remove and existing rules
			/*$('#servicePrice_1').rules("remove");
			
			var tst = $('#serviceDesc_1').val();
			if (tst !== "") {
				$('#servicePrice_1').rules("add", {
					required: true
					//required: function(element) {
					//	return serviceDesc.val() != "";
					//}//,
					//messages: {
					//	number: "Please enter a valid number."
					//}
				});
				
				//servicePrice.attr("title", "* Please enter a valid email address.")
			}*/
			
			// Sum net amount. Check if service desc is described
/*			calculateNetAmount();
			
		});
		
		// add calculate net amount function
		$('#repairPrice_1').blur(function(){
			calculateNetAmount();
		});
		
		// add calculate net amount function
		$('#repairDesc_1').blur(function() {
			calculateNetAmount();
		});
		
		calculateNetAmount();
		*/
		$('#serviceDesc_1').removeAttr('disabled');
		$('#servicePrice_1').removeAttr('disabled');
		$('#serviceDesc_2').removeAttr('disabled');
		$('#servicePrice_2').removeAttr('disabled');
		$('#serviceDesc_3').removeAttr('disabled');
		$('#servicePrice_3').removeAttr('disabled');
		$('#serviceDesc_4').removeAttr('disabled');
		$('#servicePrice_4').removeAttr('disabled');
	});
	
	$('#costing_free').click(function() {
/*		$('#costingRow').css("display", "none");
		$('#serviceCost').val(0);
		
		$("#netAmount").val("0.00");
		//$("#serviceDesc_1").rules("remove", "required");
		//$("#servicePrice_1").rules("remove", "required");
*/
		$('#serviceDesc_1').attr('disabled', 'disabled');
		$('#servicePrice_1').attr('disabled', 'disabled');
		$('#serviceDesc_2').attr('disabled', 'disabled');
		$('#servicePrice_2').attr('disabled', 'disabled');
		$('#serviceDesc_3').attr('disabled', 'disabled');
		$('#servicePrice_3').attr('disabled', 'disabled');
		$('#serviceDesc_4').attr('disabled', 'disabled');
		$('#servicePrice_4').attr('disabled', 'disabled');
	});
	
	$('#repair_add').click(function() {
		//$('#costingTable tbody>tr:last').clone(true).insertAfter('#costingTable tbody>tr:last');
		//$('#costingTable tbody>tr:last').find('input').val('');
		
		var repairRowID = $('#costingTable tbody>tr:last').attr('id').split("_");
		var newID = parseInt(repairRowID[1])+1;
		var newRepairRowID = repairRowID[0]+'_'+newID;
		$('#costingTable tbody>tr:last').clone(true).attr('id', newRepairRowID).insertAfter('#costingTable tbody>tr:last');
		var td1 = '<div class="rowElem"><input id="repairDesc_'+newID+'" class="textboxMockup repairDesc" type="text" value="" name="repairDesc"></div>';
		var td2 = '<div class="rowElem"><input id="repairPrice_'+newID+'" class="number textboxMockup repairPrice" type="text" size="6" value="0" style="text-align:right" name="repairPrice">&nbsp;<label style="margin-top:3px; margin-left:3px; float:left"><fmt:message key="baht" /></label>&nbsp;<label class="error" style="display: none; padding-left:10px" generated="true" for="repairPrice_'+newID+'"></label></div>';
		$('#costingTable tbody>tr:last').html("<td>"+td1+"</td><td>"+td2+"</td>");
		
		// add calculate net amount function
		$('#repairPrice_'+newID).blur(function(){
			calculateNetAmount();
		});
		
		// add calculate net amount function
		$('#repairDesc_'+newID).blur(function() {
			calculateNetAmount();
		});
	});
	
	$('#service_add').click(function() {
		//$('#serviceTable tbody>tr:last').clone(true).insertAfter('#serviceTable tbody>tr:last');
		//$('#serviceTable tbody>tr:last').find('input').val('');
		
		//$('#serviceTable tbody>tr:last').clone().attr('id', 'newid').insertAfter('#serviceTable tbody>tr:last');
		//$('#selector').clone().attr('id','newid').appendTo('#newPlace');
		
		//alert($('#serviceTable tbody>tr:last').attr('id'));
		var serviceRowID = $('#serviceTable tbody>tr:last').attr('id').split("_");
		//alert(serviceRowID[1]);
		var newID = parseInt(serviceRowID[1])+1;
		var newServiceRowID = serviceRowID[0]+'_'+newID;
		$('#serviceTable tbody>tr:last').clone(true).attr('id', newServiceRowID).insertAfter('#serviceTable tbody>tr:last');
		var td1 = '<div class="rowElem"><input id="serviceDesc_'+newID+'" class="textboxMockup serviceDesc" type="text" value="" name="serviceDesc"> <label class="error" for="serviceDesc_'+newID+'" generated="true" style="display: none; padding-left:10px"></label></div>';
		var td2 = '<div class="rowElem"><input id="servicePrice_'+newID+'" class="number textboxMockup servicePrice" type="text" size="6" value="0" style="text-align:right" name="servicePrice">&nbsp;<label style="margin-top:3px; margin-left:3px; float:left"><fmt:message key="baht" /></label>&nbsp;<label class="error" style="display: none; padding-left:10px" generated="true" for="servicePrice_'+newID+'"></label></div>';
		$('#serviceTable tbody>tr:last').html("<td>"+td1+"</td><td>"+td2+"</td>");
		
		// add function for check validate
		$('#servicePrice_'+newID).blur(function(){
			/* 19/8/2554 disable validate */
			
			/*$('#serviceDesc_'+newID).rules("remove");
			
			var tst = $('#servicePrice_'+newID).val();
			if (tst !== "" || tst !== "0") {
				$('#serviceDesc_'+newID).rules("add", {
					required: true
				});
				
				$('#serviceDesc_'+newID).valid();
			}*/
			
			// Sum net amount. Check if service desc is described
			calculateNetAmount();
		});
		
		$('#serviceDesc_'+newID).blur(function() {
			/* 19/8/2554 disable validate */
			
			//remove and existing rules
			/*$('#servicePrice_'+newID).rules("remove");
			$('#servicePrice_'+newID).removeClass("required");
			
			var tst = $('#serviceDesc_'+newID).val();
			if (tst !== "") {
				$('#servicePrice_'+newID).rules("add", {
					required: true
					//required: function(element) {
					//	return serviceDesc.val() != "";
					//}//,
					//messages: {
					//	number: "Please enter a valid number."
					//}
				});
				$('#servicePrice_'+newID).addClass("required");
				
				//servicePrice.attr("title", "* Please enter a valid email address.")
				$('#servicePrice_'+newID).valid();
			}*/
			
			calculateNetAmount();
		});
		
		
		//serviceList_checkRequired();

		
		//$('#servicePrice_'+newID).rules("add", {required: function(element){ return $('#serviceDesc_'+newID).val() !== ''; }});		
		//$('#serviceDesc_'+newID).rules("add", {required: function(element){ return $('#servicePrice_'+newID).val() !== ''; }});
		
		//alert($('#servicePrice_2'));
		
		//$('#servicePrice_2').rules("add", {required: true});		
		//$('#serviceDesc_2').rules("add", {required: true});
		
		
		
		
		//var price = $('#unitprice'+indexArr[1]).val();
		
		//var newServicePriceID = "servicePrice_"+newID;
		//$(newServicePriceID).rules("add",{minlength:3});
		//$("#txtLastName").rules("add", {required:true, minlength:2});
		
		
		//$("#txtLastName").rules("add", {required:true, minlength:2});
		
		 //required: function(element) {
		 //       return $("#age").val() < 13;
		 //     }
		
	});
	
	//serviceList_checkRequired();
	
	// initial value.
	//$("#servicePrice_1").val("0");
	//$("#repairPrice_1").val("0");
});

function serviceList_checkRequired(){
	$('.serviceList').each(function() {
		var serviceDesc = $(this).find(".serviceDesc");
        var servicePrice = $(this).find(".servicePrice");
        
      	//Setup validation for service desc
		servicePrice.blur(function() {
			//remove any existing rules
			serviceDesc.rules("remove");

			var tst = servicePrice.val();

			if (tst !== "") {
				alert('servicePrice val not null = '+tst);
			//	serviceDesc.attr("title", "* Selection of a type is required.");
				serviceDesc.rules("add", {
					required: true
				});
			}
			//else {
			//	serviceDesc.attr("title", "");
			//}
			
        });
        
		//Setup validation for service price
		serviceDesc.blur(function() {
			//remove and existing rules
			servicePrice.rules("remove");
			
			var tst = serviceDesc.val();
			if (tst !== "") {
				alert('serviceDesc val not null = '+tst);
				servicePrice.rules("add", {
					required: true
					//required: function(element) {
					//	return serviceDesc.val() != "";
					//}//,
					//messages: {
					//	number: "Please enter a valid number."
					//}
				});
				
				//servicePrice.attr("title", "* Please enter a valid email address.")
			}
			
		});
      	
	});
}

function calculateNetAmount(){
	//alert('calculateNetAmount');
	var netAmount = 0.00;
	
	$('.serviceList').each(function() {
		var serviceDesc = $(this).find(".serviceDesc").val();
        var servicePrice = $(this).find(".servicePrice").val();
        
       // alert('serviceDesc = '+serviceDesc+' servicePrice = '+servicePrice);
        
		if (serviceDesc !== "" && !isNaN(servicePrice)){
			netAmount = netAmount + parseFloat(servicePrice);
		}

	});
	
	/*$('.repairList').each(function() {
		var repairDesc = $(this).find(".repairDesc").val();
        var repairPrice = $(this).find(".repairPrice").val();
        
        //alert('repairDesc = '+repairDesc+' repairPrice = '+repairPrice);
        
		if (repairDesc !== "" && !isNaN(repairPrice)){
			netAmount = netAmount + parseFloat(repairPrice);
		}

	});*/

	$("#netAmount").val(netAmount);
}


</script>