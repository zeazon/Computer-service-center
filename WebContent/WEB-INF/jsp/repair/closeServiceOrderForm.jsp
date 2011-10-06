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
			<form:form commandName="form" id="form" class="jqtransform" action="outsiteService.html?do=save">
				<table width="100%">
					<tr>
						<td width="13%"><label><fmt:message key="serviceOrderDate" />:</label></td>
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
					<tr align="left">
						<td colspan="6">
							<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="customerDetail" /></u></b></div>
						</td>
					</tr>
					<tr>
						<td colspan="6">
							<table width="100%" cellpadding="0" cellspacing="0">
								<col width="13%">
								<col width="18%">
								<col width="6%">
								<col width="14%">
								<col width="10%">
								<col width="13%">
								<col width="13%">
								<col width="13%">
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
					<tr align="left">
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
						<td><label><fmt:message key="serviceOrder_problem" />:</label></td>
						<td colspan="5" align="left" valign="top"><div class="rowElem"><pre id="problem" class="display" >${form.problem}&nbsp;</pre></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrder_empCreate" />:</label></td>
						<td colspan="5"><div class="rowElem"><span id="empOpen">${form.empOpen.name}&nbsp;${form.empOpen.surname}</span></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrder_startFix" />:</label></td>
						<td colspan="2"><div class="rowElem">${form.startFix}</div></td>
						<td><label><fmt:message key="serviceOrder_endFix" /></label></td>
						<td colspan="2"><div class="rowElem">${form.endFix}</div></td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:7px;"><label><fmt:message key="serviceOrder_problem" />:</label></td>
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
								<form:radiobutton path="costing" value="cost" cssStyle="margin-top:4px" id="costing_cost" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrder_costing_cost" /></label>
								<form:radiobutton path="costing" value="free" cssStyle="margin-top:4px" id="costing_free" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrder_costing_free" /></label>
								<form:radiobutton path="costing" value="warranty" cssStyle="margin-top:4px" id="costing_warranty" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrder_costing_warranty" /></label>
							</div>
						</td>
					</tr>
					<tr id="serviceCostRow" style="display:none">
					<!--tr id="serviceCostRow"-->
						<td></td>
						<td colspan="5">
							<div class="rowElem">
								<span style="float:left; margin-top:3px"><fmt:message key="fixList" />&nbsp;</span><form:input path="serviceList" class="textboxMockup" style="margin-right:5px" /><form:input path="servicePrice" class="textboxMockup" style="text-align:right" id="serviceCost" size="4" value="0"/><span style="float:left; margin-top:3px">&nbsp;<fmt:message key="baht" /></span>
							</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="5">
							<div class="rowElem">
								<input type="radio" name="issuePart" id="noPart" value=""/><label style="float:left"><fmt:message key="noIssuedPart" /></label><input type="radio" name="issuePart" id="hasPart" value=""/><label style="float:left"><fmt:message key="haveIssuedPart" /></label>
							</div>
						</td>
					</tr>
					<tr id="partCostRow" style="display:none;">
					<!-- tr id="partCostRow"-->
						<td></td>
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
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrder_netAmount" />:</label></td>
						<td colspan="5"><div class="rowElem"><form:input path="netAmount" class="textboxMockup" id="netAmount" readonly="true" /><span style="float:left; margin-top:3px">&nbsp;<fmt:message key="baht" /></span></div></td>
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



<script type="text/javascript">

$(document).ready(function(){
	
	$("#form").validate({
		rules: {
			serviceOrderID: "required"
		}
	});
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
	
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
	
});

</script>