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
			<form:form commandName="form" id="form" class="jqtransform" action="closeServiceOrder.html?do=save">
				<input type="hidden" name="serviceOrderID" value="${form.serviceOrderID}"/>
				<table width="100%">
					<tr>
						<td width="161px"><label><fmt:message key="serviceOrderDate" />:</label></td>
						<td align="left" colspan="3"><div class="rowElem">${form.serviceOrderDate}</div></td>
						<td width="40%"><label><fmt:message key="serviceOrderID" />:</label></td>
						<td><div class="rowElem">${form.serviceOrderID}</div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrderType" />:</label></td>
						<td colspan="5">
							<div class="rowElem">
								<span id="serviceOrderType">
									<c:if test="${form.serviceType == '1'}">
										<fmt:message key="serviceOrderType_guarantee" />&nbsp;<fmt:message key="guarantee_No" />&nbsp;<c:out value="${form.guaranteeNo}"/>
									</c:if>
									<c:if test="${form.serviceType == '2'}">
										<fmt:message key="serviceOrderType_repair" />
									</c:if>
									<c:if test="${form.serviceType == '3'}">
										<fmt:message key="serviceOrderType_claim" />
									</c:if>
									<c:if test="${form.serviceType == '4'}">
										<fmt:message key="serviceOrderType_outsiteService" />&nbsp;<fmt:message key="reference" />&nbsp;<c:out value="${form.refJobID}"/>
									</c:if>
									<c:if test="${form.serviceType == '5'}">
										<fmt:message key="serviceOrderType_refix" />&nbsp;<fmt:message key="reference" />&nbsp;<c:out value="${form.refServiceOrder}"/>
									</c:if>
									&nbsp;
								</span>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="appointmentDate"/>:</label></td>
						<td colspan="5">
							<div class="rowElem" style="z-index:200">${form.appointmentDate}</div>
						</td>
					</tr>
					
					
					
					<tr>
						<td colspan="6">
							<table width="100%" cellpadding="0" cellspacing="0">
								<!-- col width="13%">
								<col width="14%">
								<col width="6%">
								<col width="10%">
								<col width="11%">
								<col width="16%">
								<col width="14%">
								<col width="16%"-->
								
								<col width="161px">
								<col width="300px">
								<col>
								<col>
								<col>
								<col>
								<col>
								<col>
								<tr align="left">
								<tr>
									<td colspan="8">
										<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="productDetail" /></u></b></div>
									</td>
								</tr>
								<tr>
									<td><label><fmt:message key="productID" />:</label></td>
									<td colspan="7"><div class="rowElem"><span id="productID">${product.productID}&nbsp;</span></div></td>
								</tr>
								<tr>
									<td><label><fmt:message key="type" />:</label></td>
									<td>
										<div class="rowElem">
											<span id="typeTxt">${product.type.name}&nbsp;</span>
										</div>
									</td>
									<td><label><fmt:message key="brand" />:</label></td>
									<td>
										<div class="rowElem">
											<span id="brandTxt">${product.brand.name}&nbsp;</span>
										</div>
									</td>
									<td><label><fmt:message key="model" />:</label></td>
									<td><div class="rowElem"><span id="modelTxt">${product.model.name}&nbsp;</span></span></div></td>
									<td><label><fmt:message key="serialNo" />:</label></td>
									<td><div class="rowElem"><span id="serialNoTxt">${product.serialNo}&nbsp;</span></div></td>
								</tr>
								<tr>
									<td><label><fmt:message key="accessories" />:</label></td>
									<td colspan="2"><div class="rowElem"><span id="accessories">${form.accessories}</span></div></td>
									<td><label><fmt:message key="serviceOrder_desc" />:</label></td>
									<td colspan="4"><div class="rowElem"><span id="serviceOrder_desc">${form.desc}&nbsp;</span></div></td>
								</tr>
								<tr>
									<td valign="top" style="padding-top:1px;"><label><fmt:message key="serviceOrder_problem" />:</label></td>
									<td colspan="7" align="left" valign="top"><div class="rowElem"><pre id="problem" class="display" >${form.problem}&nbsp;</pre></div></td>
								</tr>	
							</table>
						</td>
					</tr>
					
					
					
					
					
					<!-- tr align="left">
						<td colspan="6">
							<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="productDetail" /></u></b></div>
						</td>
					</tr>	
					<tr>
						<td><label><fmt:message key="productID" />:</label></td>
						<td colspan="5"><div class="rowElem"><span id="productID">${product.productID}&nbsp;</span></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="type" />:</label></td>
						<td colspan="2">
							<div class="rowElem"><span id="type">${product.type.name}&nbsp;</span></div>
						</td>
						<td><label><fmt:message key="brand" />:</label></td>
						<td colspan="2">
							<div class="rowElem"><span id="brand">${product.brand.name}&nbsp;</span></div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="model" />:</label></td>
						<td colspan="2"><div class="rowElem"><span id="model">${product.model.name}&nbsp;</span></div></td>
						<td><label><fmt:message key="serialNo" />:</label></td>
						<td colspan="2"><div class="rowElem"><span id="serialNo">${product.serialNo}&nbsp;</span></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="accessories" />:</label></td>
						<td colspan="2"><div class="rowElem"><span id="accessories">${form.accessories}</span></div></td>
						<td><label><fmt:message key="serviceOrder_desc" />:</label></td>
						<td colspan="2"><div class="rowElem"><span id="serviceOrder_desc">${form.desc}&nbsp;</span></div></td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:1px;"><label><fmt:message key="serviceOrder_problem" />:</label></td>
						<td colspan="5" align="left" valign="top"><div class="rowElem"><pre id="problem" class="display" >${form.problem}&nbsp;</pre></div></td>
					</tr-->
					<tr align="left">
						<td colspan="6">
							<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="customerDetail" /></u></b></div>
						</td>
					</tr>
					<tr>
						<td colspan="6">
							<table width="100%" cellpadding="0" cellspacing="0">
								<!-- col width="161px">
								<col width="18%">
								<col width="6%">
								<col width="14%">
								<col width="10%">
								<col width="13%">
								<col width="13%">
								<col width="13%"-->
								
								<col width="161px">
								<col width="300px">
								<col>
								<col>
								<col>
								<col>
								<col>
								<col>
								<tr>
									<td><label><fmt:message key="customerID" />:</label></td>
									<td align="left" colspan="7"><div class="rowElem">${form.customerID}</div></td>
								</tr>
								<tr>
									<td><label><fmt:message key="contactName" />:</label></td>
									<td><div class="rowElem"><span id="contactName">${customer.name}&nbsp;</span></div></td>
									<td><label><fmt:message key="email" />:</label></td>
									<td><div class="rowElem"><span id="email">${customer.email}&nbsp;</span></div></td>
									<td><label><fmt:message key="tel" />:</label></td>
									<td><div class="rowElem"><span id="tel">${customer.tel}&nbsp;</span></div></td>
									<td><label><fmt:message key="mobileTel"/>:</label></td>
									<td><div class="rowElem"><span id="mobileTel">${customer.mobileTel}&nbsp;</span></div></td>
								</tr>
								<tr>
									<td><label><fmt:message key="address" />:</label></td>
									<td colspan="7"><div class="rowElem"><span id="address">${fullAddr}&nbsp;</span></div></td>
								</tr>
								<tr>
									<td><label><fmt:message key="deliveryCustomer" />:</label></td>
									<td><div class="rowElem">${form.deliveryCustomer}</div></td>
									<td><label><fmt:message key="email" />:</label></td>
									<td><div class="rowElem">${form.deliveryEmail}</div></td>
									<td><label><fmt:message key="tel" />:</label></td>
									<td><div class="rowElem">${form.deliveryTel}</div></td>
									<td><label><fmt:message key="mobileTel"/>:</label></td>
									<td><div class="rowElem">${form.deliveryMobileTel}</div></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrder_empCreate" />:</label></td>
						<td colspan="5"><div class="rowElem"><span id="empOpen">${form.empOpen.name}&nbsp;${form.empOpen.surname}</span></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrder_startFix" />:</label></td>
						<td colspan="2"><div class="rowElem">${form.startFix}</div></td>
						<td><label><fmt:message key="serviceOrder_endFix" />:</label></td>
						<td colspan="2"><div class="rowElem">${form.endFix}</div></td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:7px;"><label><fmt:message key="serviceOrder_realProblem" />:</label></td>
						<td colspan="5" align="left"><div class="rowElem"><form:textarea path="realProblem" rows="5" col="30" class="textareaMockup" style="width:98%"></form:textarea><label class="error" for="realProblem" generated="true" style="display: none; float:left; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:7px;"><label><fmt:message key="cause" />:</label></td>
						<td colspan="5" align="left"><div class="rowElem"><form:textarea path="cause" rows="5" col="30" class="textareaMockup" style="width:98%"></form:textarea><label class="error" for="cause" generated="true" style="display: none; float:left; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:7px;"><label><fmt:message key="fix" />:</label></td>
						<td colspan="5" align="left"><div class="rowElem"><form:textarea path="fixDesc" rows="5" col="30" class="textareaMockup" style="width:98%"></form:textarea><label class="error" for="fixDesc" generated="true" style="display: none; float:left; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td></td>
						<td colspan="5">
							<div class="rowElem">
								<form:radiobutton path="costing" value="cost" cssClass="costing" cssStyle="margin-top:4px" id="costing_cost" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrder_costing_cost" /></label>
								<form:radiobutton path="costing" value="free" cssClass="costing" cssStyle="margin-top:4px" id="costing_free" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrder_costing_free" /></label>
								<form:radiobutton path="costing" value="warranty" cssClass="costing" cssStyle="margin-top:4px" id="costing_warranty" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrder_costing_warranty" /></label>
								<label class="error" for="costing" generated="true" style="display: none; padding-left:10px"></label>
							</div>
						</td>
					</tr>
					<tr id="serviceCostRow" style="display:none">
					<!--tr id="serviceCostRow"-->
						<!-- td></td>
						<td colspan="5">
							<div class="rowElem">
								<span style="float:left; margin-top:6px"><fmt:message key="fixList" />&nbsp;</span><form:input path="serviceList" class="textboxMockup" style="margin-right:5px" /><form:input path="servicePrice" class="textboxMockup" style="text-align:right" id="serviceCost" size="4" value="0"/><span style="float:left; margin-top:6px">&nbsp;<fmt:message key="baht" /></span>
							</div>
						</td-->
						<td></td>
						<td colspan="5">
							<table border="0">
								<tr>
									<th class="ui-widget-header ui-corner-all"><fmt:message key="serviceOrder_partList" /></th>
									<th class="ui-widget-header ui-corner-all"><fmt:message key="price" /></th>
								</tr>
								<c:forEach var="i" begin="1" end="4" step="1" varStatus ="status">
								<tr class="serviceList">
									<td><form:input path="serviceList_${i}" class="serviceList textboxMockup" onBlur="calculateNetAmount();"/></td>
									<td><form:input path="servicePrice_${i}" class="number servicePrice textboxMockup" style="text-align:right" size="4" onBlur="calculateNetAmount();"/></td>
								</tr>
								</c:forEach>
								<!-- tr>
									<td><form:input path="serviceList_1" class="serviceList textboxMockup" /></td>
									<td><form:input path="servicePrice_1" class="servicePrice textboxMockup" style="text-align:right" id="serviceCost" size="4" value="0"/></td>
								</tr>
								<tr>
									<td><form:input path="serviceList_2" class="serviceList textboxMockup" /></td>
									<td><form:input path="servicePrice_2" class="servicePrice textboxMockup" style="text-align:right" id="serviceCost" size="4" value="0"/><!-- span style="float:left; margin-top:6px">&nbsp;<fmt:message key="baht" /></span--></td>
								<!-- /tr>
								<tr>
									<td><form:input path="serviceList_3" class="serviceList textboxMockup" /></td>
									<td><form:input path="servicePrice_3" class="servicePrice textboxMockup" style="text-align:right" id="serviceCost" size="4" value="0"/></td>
								</tr>
								<tr>
									<td><form:input path="serviceList_4" class="serviceList textboxMockup" /></td>
									<td><form:input path="servicePrice_4" class="servicePrice textboxMockup" style="text-align:right" id="serviceCost" size="4" value="0"/></td>
								</tr-->
							</table>
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="5">
							<div class="rowElem">
								<form:radiobutton path="issuePart" value="noIssuedPart" cssClass="issuePart" cssStyle="margin-top:4px" id="noPart" /><label style="float:left; margin-top:4px"><fmt:message key="noIssuedPart" /></label>
								<form:radiobutton path="issuePart" value="haveIssuedPart" cssClass="issuePart" cssStyle="margin-top:4px" id="hasPart" /><label style="float:left; margin-top:4px"><fmt:message key="haveIssuedPart" /></label>
								<label class="error" for="issuePart" generated="true" style="display: none; padding-left:10px"></label>
							</div>
						</td>
					</tr>
					<tr id="partCostRow" style="display:none;">
					<!-- tr id="partCostRow"-->
						<!-- td></td>
						<td colspan="5">
							<input type="button" id="part_add" value="<fmt:message key='button.add'/>"/>
							<table id="partTable">
								<tr>
									<th class="ui-widget-header ui-corner-all"><fmt:message key="productID" /></th>
									<th class="ui-widget-header ui-corner-all"><fmt:message key="serviceOrder_partList" /></th>
									<th class="ui-widget-header ui-corner-all"><fmt:message key="amount" /></th>
									<th class="ui-widget-header ui-corner-all"><fmt:message key="price" /></th>
								</tr>
								<tr>
									<td><input type="text" class="textboxMockup"/></td>
									<td><input type="text" class="textboxMockup"/></td>
									<td><input type="text" class="textboxMockup" size="2" style="text-align:right"/></td>
									<td><input type="text" class="textboxMockup" size="5" style="text-align:right"/></td>
								</tr>
								<tr>
									<td><input type="text" class="textboxMockup"/></td>
									<td><input type="text" class="textboxMockup"/></td>
									<td><input type="text" class="textboxMockup" size="2" style="text-align:right"/></td>
									<td><input type="text" class="textboxMockup" size="5" style="text-align:right"/></td>
								</tr>
								<tr>
									<td><input type="text" class="textboxMockup"/></td>
									<td><input type="text" class="textboxMockup"/></td>
									<td><input type="text" class="textboxMockup" size="2" style="text-align:right"/></td>
									<td><input type="text" class="textboxMockup" size="5" style="text-align:right"/></td>
								</tr>
								<tr>
									<td><input type="text" class="textboxMockup"/></td>
									<td><input type="text" class="textboxMockup"/></td>
									<td><input type="text" class="textboxMockup" size="2" style="text-align:right"/></td>
									<td><input type="text" class="textboxMockup" size="5" style="text-align:right"/></td>
								</tr>
							</table>
						</td-->
						
						<td></td>
						<td colspan="5">
							<table id="partTable">
								<tr>
									<th class="ui-widget-header ui-corner-all"><fmt:message key="productID" /></th>
									<th class="ui-widget-header ui-corner-all"><fmt:message key="serviceOrder_partList" /></th>
									<th class="ui-widget-header ui-corner-all"><fmt:message key="amount" /></th>
									<th class="ui-widget-header ui-corner-all"><fmt:message key="price" /></th>
								</tr>
								<c:forEach var="i" begin="1" end="11" step="1" varStatus ="status">
								<tr class="issuePartList">
									<td><form:input path="issuePartCode_${i}" class="issuePartCode textboxMockup" onBlur="calculateNetAmount();"/></td>
									<td><form:input path="issuePartName_${i}" class="issuePartName textboxMockup" onBlur="calculateNetAmount();"/></td>
									<td><form:input path="issuePartQty_${i}" class="number issuePartQty textboxMockup" size="2" style="text-align:right" onBlur="calculateNetAmount();"/></td>
									<td><form:input path="issuePartPrice_${i}" class="number issuePartPrice textboxMockup" size="5" style="text-align:right" onBlur="calculateNetAmount();"/></td>
								</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrder_netAmount" />:</label></td>
						<td colspan="5"><div class="rowElem"><form:input path="netAmount" class="textboxMockup" id="netAmount" readonly="true" cssStyle="text-align:right" /><span style="float:left; margin-top:6px">&nbsp;<fmt:message key="baht" /></span></div></td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:7px;"><label><fmt:message key="remark"/>:</label></td>
						<td colspan="5" align="left"><div class="rowElem"><form:textarea path="remark" rows="5" col="30" class="textareaMockup" style="width:98%"></form:textarea></div></td>
					</tr>
					<tr>
						<td colspan="6" align="center"><div class="rowElem"><input type="submit" value="<fmt:message key='button.ok' />" /></div></td>
					</tr>
				</table>
			</form:form>
		</td>
	</tr>
</table>

<form:form commandName="docForm" id="printJasperForm" method="post" action="serviceOrder.xls?do=printCloseExcel">
	<form:hidden path="serviceOrderID" />
</form:form>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#form").validate({
		rules: {
			costing: "required",
			issuePart: "required"
		}
	});
	
	<c:if test="${action == 'print'}">
		document.forms["printJasperForm"].submit();
	</c:if>
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
	
	checkCosting();
	
	checkIssuePart();
	
	$('#costing_cost').click(function() {
		$('#serviceCostRow').css("display", "table-row");
	});
	
	$('#costing_free').click(function() {
		$('#serviceCostRow').css("display", "none");
		$('#serviceCost').val(0);
	});
	
	$('#costing_warranty').click(function() {
		$('#serviceCostRow').css("display", "none");
		$('#serviceCost').val(0);
	});
	
	$('#noPart').click(function() {
		$('#partCostRow').css("display", "none");		
	});
	
	$('#hasPart').click(function() {
		$('#partCostRow').css("display", "table-row");
	});
	
	$('#part_add').click(function() {
		$('#partTable tbody>tr:last').clone(true).insertAfter('#partTable tbody>tr:last');
	});
	
	$('.costing').change(function() {
		calculateNetAmount();
	});
	
	$('.issuePart').change(function() {
		calculateNetAmount();
	});
	
});

function calculateNetAmount(){	
	var netAmount = 0.00;
	
	if($('[name="costing"]:checked').val() == 'cost'){
		$('.serviceList').each(function() {
			var serviceList = $(this).find(".serviceList").val();
	        var servicePrice = $(this).find(".servicePrice").val();
	        
	       // alert('serviceList = '+serviceList+' servicePrice = '+servicePrice);
	        
			if (serviceList !== "" && !isNaN(servicePrice)){
				netAmount = netAmount + parseFloat(servicePrice)
			}
	
		});
	}

	if($('[name="issuePart"]:checked').val() == 'haveIssuedPart'){
		$('.issuePartList').each(function() {
			var issuePartCode = $(this).find(".issuePartCode").val();
			var issuePartName = $(this).find(".issuePartName").val();
			var issuePartQty = $(this).find(".issuePartQty").val();
			var issuePartPrice = $(this).find(".issuePartPrice").val();
	        
	        //alert('issuePartCode = '+issuePartCode+' issuePartName = '+issuePartName+' issuePartQty = '+issuePartQty+' issuePartPrice = '+issuePartPrice);
	        
			if (issuePartCode !== "" && issuePartName !== "" && issuePartQty !== "" && !isNaN(issuePartQty) && issuePartPrice !== "" && !isNaN(issuePartPrice)){
				//alert('!isNaN(issuePartQty) = '+!isNaN(issuePartQty)+' !isNaN(issuePartPrice) = '+!isNaN(issuePartPrice));
				//alert('issuePartQty = '+issuePartQty+' issuePartPrice = '+issuePartPrice);
				netAmount = netAmount + (parseInt(issuePartQty) * parseFloat(issuePartPrice));
			}
	
		});
	}

	
	$("#netAmount").val(netAmount);
}

function checkCosting(){	
	if($('[name="costing"]:checked').val() == 'cost'){
		$('#serviceCostRow').css("display", "table-row");
	}
}

function checkIssuePart(){
	if($('[name="issuePart"]:checked').val() == 'haveIssuedPart'){
		$('#partCostRow').css("display", "table-row");
	}
}

</script>