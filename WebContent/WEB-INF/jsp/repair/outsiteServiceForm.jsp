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
				<form:hidden path="status"/>
				<table width="100%">
					<tr>
						<td width="161px"><label><fmt:message key="outsiteServiceID" />:</label></td>
						<td colspan="2"><div class="rowElem"><form:input path="outsiteServiceID" readonly="true" class="textboxMockup" /></div></td>
						<td><label><fmt:message key="date" />:</label></td>
						<td colspan="2">
							<c:choose>
								<c:when test="${form.status != 'close'}">
									<div class="rowElem"><form:input path="outsiteServiceDate" id="outsiteServiceDate" class="textboxMockup" size="15" /></div>
								</c:when>
								<c:otherwise>
									<div class="rowElem">${form.outsiteServiceDate}</div>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td><div class="rowElem"><label><fmt:message key="outsiteServiceServiceType" />:</label></div></td>
						<td colspan="5">
							<div class="rowElem">
							<c:choose>
								<c:when test="${form.status != 'close'}">
									<form:radiobutton path="serviceType" value="warranty" cssStyle="margin-top:4px;" /><label style="float:left; margin-top:4px"><fmt:message key="outsiteServiceServiceType_inWarranty" /></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><form:radiobutton  path="serviceType" value="repair" cssStyle="margin-top:4px;" /><label style="float:left; margin-top:4px;" ><fmt:message key="outsiteServiceServiceType_outWarranty" /></label>
								</c:when>
								<c:otherwise>
									<form:radiobutton path="serviceType" value="warranty" cssStyle="margin-top:4px;" disabled="true" /><label style="float:left; margin-top:4px"><fmt:message key="outsiteServiceServiceType_inWarranty" /></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><form:radiobutton  path="serviceType" value="repair" cssStyle="margin-top:4px;" disabled="true"/><label style="float:left; margin-top:4px;" ><fmt:message key="outsiteServiceServiceType_outWarranty" /></label>
								</c:otherwise>
							</c:choose>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrderID" />:<c:if test="${form.status != 'close'}"></c:if></label></td>
						<td colspan="5">
							<div class="rowElem">
								<c:choose>
									<c:when test="${form.status != 'close'}">
										<form:input path="serviceOrderID" class="textboxMockup" style="float:left" id="serviceOrderID" readonly="readonly" size="10"/> <input type="button" id="lov" class="lov_button" value="..." > <label class="error" id="ui-state-error" for="serviceOrderID" generated="true" style="display: none; float:left; padding-left:10px"></label>
									</c:when>
									<c:otherwise>
										${form.serviceOrderID}
									</c:otherwise>
								</c:choose>
							</div>
						</td>
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
									
									
									
									
									<!-- tr>
										<td colspan="6">
											<table width="100%" cellpadding="0" cellspacing="0">												
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
													<td colspan="7"><div class="rowElem"><span id="productID">${form.serviceOrder.product.productID}&nbsp;</span></div></td>
												</tr>
												<tr>
													<td><label><fmt:message key="type" />:</label></td>
													<td>
														<div class="rowElem">
															<span id="typeTxt">${form.serviceOrder.product.type.name}&nbsp;</span>
														</div>
													</td>
													<td><label><fmt:message key="brand" />:</label></td>
													<td>
														<div class="rowElem">
															<span id="brandTxt">${form.serviceOrder.product.brand.name}&nbsp;</span>
														</div>
													</td>
													<td><label><fmt:message key="model" />:</label></td>
													<td><div class="rowElem"><span id="modelTxt">${form.serviceOrder.product.model.name}&nbsp;</span></span></div></td>
													<td><label><fmt:message key="serialNo" />:</label></td>
													<td><div class="rowElem"><span id="serialNoTxt">${form.serviceOrder.product.serialNo}&nbsp;</span></div></td>
												</tr>
												<tr>
													<td><label><fmt:message key="accessories" />:</label></td>
													<td colspan="2"><div class="rowElem"><span id="accessories">${form.serviceOrder.accessories}</span></div></td>
													<td><label><fmt:message key="serviceOrder_desc" />:</label></td>
													<td colspan="4"><div class="rowElem"><span id="serviceOrder_desc">${form.serviceOrder.description}&nbsp;</span></div></td>
												</tr>
												<tr>
													<td valign="top" style="padding-top:2px;"><label><fmt:message key="serviceOrder_problem" />:</label></td>
													<td colspan="7" align="left" valign="top"><div class="rowElem"><pre id="problem" class="display" >${form.serviceOrder.problem}&nbsp;</pre></div></td>
												</tr>	
											</table>
										</td>
									</tr-->

									<!-- tr>
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
						<td colspan="5"><div class="rowElem"><form:input path="customerName" id="customerName" style="width:99%"/></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="tel" />:</label></td>
						<td colspan="2"><div class="rowElem"><form:input path="tel" id="tel"/></div></td>
						<td><label><fmt:message key="mobileTel" />:</label></td>
						<td colspan="2"><div class="rowElem"><form:input path="mobileTel" id="mobileTel"/></div></td>
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
								<form:select path="typeID" id="type">
									<form:options items="${typeList}" itemValue="typeID" itemLabel="name"/>
								</form:select>
							</div>
						</td>
						<td><label><fmt:message key="brand" />:</label></td>
						<td colspan="2">
							<div class="rowElem">
								<form:select path="brandID" id="brand">
									<form:options items="${brandList}" itemValue="brandID" itemLabel="name"/>
								</form:select>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="model" />:</label></td>
						<td colspan="2">
							<div class="rowElem">
								<form:select path="modelID" id="model">
									<form:options items="${modelList}" itemValue="modelID" itemLabel="name"/>
								</form:select>
							</div>
						</td>
						<td><label><fmt:message key="serialNo" />:</label></td>
						<td colspan="2"><div class="rowElem"><form:input path="serialNo" id="serialNo" /></div></td>
					</tr>
					
					
					
					
					<tr>
						<td style="width:13%"><div class="rowElem"><label><fmt:message key="outsiteService_accessories" />:</label></div></td>
						<td colspan="5">
							<div class="rowElem">
								<c:choose>
									<c:when test="${form.status != 'close'}">
										<form:input path="accessories" class="textboxMockup" style="width:99%"/>
									</c:when>
									<c:otherwise>
										<div style="margin-top:3px">${form.accessories}</div>
									</c:otherwise>
								</c:choose>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:7px;"><label><fmt:message key="serviceOrder_problem" />:<c:if test="${form.status != 'close'}"><font color="red">*</font></c:if></label></td>
						<td colspan="5" align="left">
							<c:choose>
								<c:when test="${form.status != 'close'}">
									<div class="rowElem"><form:textarea path="problem" id="os_problem" rows="5" col="30" class="textareaMockup" style="width:98%" name="problem" ></form:textarea><label class="error" for="problem" generated="true" style="display: none; float:left; padding-left:10px"></label></div>
								</c:when>
								<c:otherwise>
									<div class="rowElem" style="margin-top:3px"><pre class="display">${form.problem}</pre></div>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td><div class="rowElem"><label><fmt:message key="outsiteService_outsiteCompany" />:</label></div></td>
						<td colspan="2">
							<div class="rowElem">
								<c:choose>
									<c:when test="${form.status != 'close'}">
										<form:select path="outsiteCompanyID" id="outsiteCompany">
											<form:option value="">All</form:option>
											<form:options items="${outsiteCompanyList}" itemValue="outsiteCompanyID" itemLabel="name"/>
										</form:select>
										<label class="error" id="ui-state-error" for="outsiteCompany" generated="true" style="display: none; float:left; padding-left:10px"></label>
									</c:when>
									<c:otherwise>
										<form:select path="outsiteCompanyID" id="outsiteCompany" class="disabled">
											<form:option value="">All</form:option>
											<form:options items="${outsiteCompanyList}" itemValue="outsiteCompanyID" itemLabel="name"/>
										</form:select>
									</c:otherwise>
								</c:choose>
							</div>
						</td>
						<td><div class="rowElem"><label><fmt:message key="transportCompany" />:</label></div></td>
						<td colspan="2">
							<div class="rowElem">
								<c:choose>
									<c:when test="${form.status != 'close'}">
										<form:select path="transportCompanyID" id="transportCompany">
											<form:option value="">All</form:option>
											<form:options items="${transportCompanyList}" itemValue="transportCompanyID" itemLabel="name"/>
										</form:select>
										<label class="error" id="ui-state-error" for="transportCompany" generated="true" style="display: none; float:left; padding-left:10px"></label>
									</c:when>
									<c:otherwise>
										<form:select path="transportCompanyID" id="transportCompany" disabled="disabled">
											<form:option value="">All</form:option>
											<form:options items="${transportCompanyList}" itemValue="transportCompanyID" itemLabel="name"/>
										</form:select>
									</c:otherwise>
								</c:choose>
							</div>
						</td>
					</tr>
					
					<c:choose>
						<c:when test="${form.status == 'close'}">
							<tr>
								<td><label><fmt:message key="outsiteService_sentDate" />:</label></td>
								<td colspan="2"><div class="rowElem">${form.sentDate}</div></td>
								<td><label><fmt:message key="outsiteService_sentTransportNo" />:</label></td>
								<td colspan="2"><div class="rowElem">${form.sentTransportNo}</div></td>
							</tr>
							<tr>
								<td><label><fmt:message key="outsiteService_receivedDate" />:</label></td>
								<td colspan="5"><div class="rowElem">${form.receivedDate}</div></td>
							</tr>
							<tr>
								<td><label><fmt:message key="transportCompany" />:</label></td>
								<td colspan="2">
									<div class="rowElem">
										<form:select path="receivedTransportCompanyID" id="receivedTransportCompany" disabled="disabled">
											<form:option value="">-</form:option>
											<form:options items="${transportCompanyList}" itemValue="transportCompanyID" itemLabel="name"/>
										</form:select>
									</div>
								</td>
								<td><label><fmt:message key="outsiteService_receivedTransportNo" />:</label></td>
								<td colspan="2"><div class="rowElem">${form.receivedTransportNo}</div></td>
							</tr>
							<tr>
								<td valign="top" style="padding-top:9px;"><label><fmt:message key="outsiteService_repairing" />:</label></td>
								<td colspan="5" align="left"><div class="rowElem"><pre style="font-size:16px; padding-top:4px">${form.repairing}</pre></div></td>
							</tr>
							<tr>
								<td></td>
								<td colspan="5">
									<div class="rowElem">
										<form:radiobutton path="costing" id="costing_cost" value="cost" disabled="true"/> <label style="float:left;"><fmt:message key="outsiteService_costing_cost" /></label> <form:radiobutton path="costing" id="costing_free" value="free" disabled="true"/> <label style="float:left;"><fmt:message key="outsiteService_costing_free" /></label>
									</div>
								</td>
							</tr>
							<c:if test="${osdList.size() > 0}">
							<tr>
								<td width="13%"></td>
								<td colspan="5">
									<table id="serviceTable">
										<tr>
											<td colspan="2"><fmt:message key="serviceList" /></td>
										</tr>
										<c:forEach var="osd" items="${osdList}" varStatus="status">
										<tr class="serviceList">
											<td>${osd.desc}</td>
											<td>${osd.price}</td>
										</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
							<tr>
								<td><label><fmt:message key="serviceOrder_netAmount" />:</label></td>
								<td colspan="5"><div class="rowElem">${form.netAmount}&nbsp;<fmt:message key="baht" /></div></td>
							</tr>
							</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${form.status == 'sent' || form.status == 'received'}">
							<tr>
								<td><label><fmt:message key="outsiteService_sentDate" />:<font color="red">*</font></label></td>
								<td colspan="2"><div class="rowElem"><form:input class="required textboxMockup" id="sentDate" path="sentDate" readonly="readonly" size="10"/></div></td>
								<td><label><fmt:message key="outsiteService_sentTransportNo" />:<font color="red">*</font></label></td>
								<td colspan="2"><div class="rowElem"><form:input class="required textboxMockup" path="sentTransportNo" /></div></td>
							</tr>
							</c:if>
							<c:if test="${form.status == 'received'}">
							<tr>
								<td><label><fmt:message key="outsiteService_receivedDate" />:<font color="red">*</font></label></td>
								<td colspan="2"><div class="rowElem"><form:input class="required textboxMockup" id="receivedDate" path="receivedDate" readonly="readonly" size="10"/></div></td>
								<td><label><fmt:message key="outsiteService_receivedTransportNo" />:<font color="red">*</font></label></td>
								<td colspan="2"><div class="rowElem"><form:input class="required textboxMockup" path="receivedTransportNo" /></div></td>
							</tr>
							<tr>
								<td valign="top" style="padding-top:9px;"><label><fmt:message key="outsiteService_repairing" />:</label></td>
								<td colspan="5" align="left"><div class="rowElem"><pre style="font-size:16px; padding-top:4px">${form.repairing}</pre></div></td>
							</tr>
							</c:if>
							<tr>
								<td></td>
								<td colspan="5">
									<div class="rowElem">
										<form:radiobutton path="costing" id="costing_cost" value="cost" disabled="true"/> <label style="float:left;"><fmt:message key="outsiteService_costing_cost" /></label> <form:radiobutton path="costing" id="costing_free" value="free" disabled="true"/> <label style="float:left;"><fmt:message key="outsiteService_costing_free" /></label>
									</div>
								</td>
							</tr>
							<c:if test="${osdList.size() > 0}">
							<tr>
								<td width="13%"></td>
								<td colspan="5">
									<table id="serviceTable">
										<tr>
											<td colspan="2"><fmt:message key="serviceList" /></td>
										</tr>
										<c:forEach var="osd" items="${osdList}" varStatus="status">
										<tr class="serviceList">
											<td>${osd.desc}</td>
											<td>${osd.price}</td>
										</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
							<tr>
								<td><label><fmt:message key="serviceOrder_netAmount" />:</label></td>
								<td colspan="5"><div class="rowElem">${form.netAmount}&nbsp;<fmt:message key="baht" /></div></td>
							</tr>
							</c:if>
						</c:otherwise>
					</c:choose>
					
					<c:if test="${form.status != 'close'}">
					<tr>
						<td colspan="6" align="center"><div class="rowElem"><input type="submit" value="<fmt:message key='button.ok' />" /></div></td>
					</tr>
					</c:if>
				</table>
			</form:form>
		</td>
	</tr>
</table>


<div id="dialog-modal" title='<fmt:message key="searchServiceOrder" />'>
	<table>
		<tr>
			<td>
				<!--form id="lovform" class="jqtransform" action="#"-->
				<form id="lovform" class="lov" action="#">
					<table>
						<tr>
							<td><label><fmt:message key="name" />:</label></td>
							<td><div class="rowElem"><input type="text" id="name" class="textboxMockup"></div></td>
						</tr>
						<%--tr>
							<td><label><fmt:message key="surname" />:</label></td>
							<td><div class="rowElem"><input type="text" id="surname" class="textboxMockup"></div></td>
						</tr--%>
						<tr>
							<td></td>
							<td><div class="rowElem"><input type="button" id="searchServiceOrderButton" value='<fmt:message key="button.search" />' ></div></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<table id="list"></table>
				<div id="pager"></div>
			</td>
		</tr>
	</table>
	<form id="lovValueForm">
		<input type="hidden" id="lov_id" name="serviceOrderID" />
		<input type="hidden" id="lov_serviceOrderDate" name="serviceOrderDate" />
		<input type="hidden" id="lov_serviceType" name="serviceType" />
		<input type="hidden" id="lov_appointmentDate" name="appointmentDate" />
		<input type="hidden" id="lov_guaranteeNo" name="guaranteeNo" />
		<input type="hidden" id="lov_customerID" name="customer.customerID" />
		<input type="hidden" id="lov_name" name="name" />
		<input type="hidden" id="lov_company" name="company" />
		<input type="hidden" id="lov_address" name="customerFullAddress" />
		<input type="hidden" id="lov_address" name="email" />
		<input type="hidden" id="lov_tel" name="tel" />
		<input type="hidden" id="lov_mobileTel" name="mobileTel" />
		<input type="hidden" id="lov_deliveryCustomer" name="deliveryCustomer" />
		<input type="hidden" id="lov_deliveryEmail" name="deliveryEmail" />
		<input type="hidden" id="lov_deliveryTel" name="deliveryTel" />
		<input type="hidden" id="lov_deliveryMobileTel" name="deliveryMobileTel" />
		<input type="hidden" id="lov_productID" name="productID" />
		<input type="hidden" id="lov_type" name="type" />
		<input type="hidden" id="lov_brand" name="brand" />
		<input type="hidden" id="lov_model" name="model" />
		<input type="hidden" id="lov_serialNo" name="serialNo" />
		<input type="hidden" id="lov_accessories" name="accessories" />
		<input type="hidden" id="lov_description" name="description" />
		<input type="hidden" id="lov_problem" name="problem" />
		<input type="hidden" id="lov_empOpen" name="empOpen" />
		<input type="hidden" id="lov_typeID" name="typeID" />
		<input type="hidden" id="lov_brandID" name="brandID" />
		<input type="hidden" id="lov_modelID" name="modelID" />
	</form>
</div>


<form:form commandName="docForm" id="printJasperForm" method="post" action="outsiteService.html?do=print" target="original" onSubmit="window.open('', 'original', 'width=450,height=300,status=yes,resizable=yes,scrollbars=yes')">
	<form:hidden path="outsiteCompany"/>
	<form:hidden path="outsiteServiceDate"/>
	<form:hidden path="customerName"/>
	<form:hidden path="tel"/>
	<form:hidden path="mobileTel"/>
	<form:hidden path="type"/>
	<form:hidden path="brandModel"/>
	<form:hidden path="serialNo"/>
	<form:hidden path="accessories"/>
	<form:hidden path="problem"/>
	<form:hidden path="transportCompany"/>
	<form:hidden path="serviceOrderID"/>
	<form:hidden path="empOpen"/>
</form:form>

<c:url var="findBrandURL" value="/brand.html?do=getBrandByType" />
<c:url var="findModelURL" value="/model.html?do=getModel" />

<script type="text/javascript">

$(document).ready(function(){
	
	<%--c:if test="${action == 'print' && form.status != 'new'}"--%>
	<c:if test="${action == 'print'}">
		document.forms["printJasperForm"].submit();
	</c:if>
	
	<c:if test="${form.status != 'close'}">
		$('#outsiteServiceDate').datetimeEntry({datetimeFormat: 'D/O/Y H:M'});
	</c:if>
	
	$('#sentDate').calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
	$('#receivedDate').calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
	
	$("#form").validate({
		rules: {
			outsiteCompanyID: "required",
			transportCompanyID: "required",
			problem: "required"
		}
	});
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
	$("form.lov").jqTransform();
	
	var tDialog = $( "#dialog-modal" ).dialog({
		autoOpen: false,
		height: 540,
		width: 680,
		modal: true
	});
	
	function initLov(){
		tDialog = $( "#dialog-modal" ).dialog({
			autoOpen: false,
			height: 540,
			width: 680,
			modal: true
		});
	}
	
	// Add event click to lov button
	$("#lov").click( function(e) {
		tDialog.dialog("open");
	});
	
	
	jQuery("#list").jqGrid({
		url:"searchServiceOrder.html",
		datatype: "json",
		//height: 230,
		height: "100%",
		//autoheight: true,
		//width: "100%",
		autowidth: true,
		colNames:['<fmt:message key="serviceOrderID" />','<fmt:message key="serviceOrderDate" />', 'serviceType', 'customerID','<fmt:message key="name" />','<fmt:message key="company" />','<fmt:message key="address" />','email','<fmt:message key="tel" />','<fmt:message key="mobileTel" />','deliveryCustomer','deliveryEmail','deliveryTel','deliveryMobileTel','productID','type','brand','model','serialNo','accessories','description','problem','empOpen','appointmentDate','problem','typeID','brandID','modelID'],
		colModel:[
			{name:'serviceOrderID',index:'serviceOrderID', width:'180'},
			{name:'serviceOrderDate', index:'serviceOrderDate', align:'center', sorttype:'date',formatter:'date', formatoptions: {srcformat:'d/m/Y H:i',newformat:'d/m/Y H:i'}, width:'140', firstSortOrder:'desc'},
			{name:'serviceType',index:'serviceType', hidden:true},
			{name:'customer.customerID',index:'customerID', hidden:true},
			{name:'name',index:'name', editable:true},
			{name:'company',index:'company', hidden:true},
			{name:'customerFullAddress',index:'address', hidden:true},
			{name:'email',index:'email', hidden:true},
			{name:'tel',index:'tel', hidden:true},
			{name:'mobileTel',index:'mobileTel', hidden:true},
			{name:'deliveryCustomer',index:'deliveryCustomer', hidden:true},
			{name:'deliveryEmail',index:'deliveryEmail', hidden:true},
			{name:'deliveryTel',index:'deliveryTel', hidden:true},
			{name:'deliveryMobileTel',index:'deliveryMobileTel', hidden:true},
			{name:'productID',index:'productID', hidden:true},
			{name:'type',index:'type', hidden:true},
			{name:'brand',index:'brand', hidden:true},
			{name:'model',index:'model', hidden:true},
			{name:'serialNo',index:'serialNo', hidden:true},
			{name:'accessories',index:'accessories', hidden:true},
			{name:'description',index:'description', hidden:true},
			{name:'problem',index:'problem', hidden:true},
			{name:'empOpen',index:'empOpen', hidden:true},
			{name:'appointmentDate',index:'appointmentDate', hidden:true},
			{name:'problem',index:'problem', hidden:true},
			{name:'typeID', index:'typeID', hidden:true},
			{name:'brandID', index:'brandID', hidden:true},
			{name:'modelID', index:'modelID', hidden:true}],
		multiselect: false,
		//caption: "Manipulating Array Data",
		rownumbers: true,
		rowNum:10,
		rowList:[10,20,30,40,50],
		viewrecords: true,
		jsonReader:{
			repeatitems: false,
			id: "serviceOrderID"
		},
		pager: '#pager',
		toppager: true,
		onSelectRow: function(id){
			selectRow(id);
		}
	}).navGrid("#pager",{edit:false,add:false,del:false,search:false,refresh:false,cloneToTop:true});
	
	var topPagerDiv = $("#list_toppager")[0];
	$("#list_toppager_center", topPagerDiv).remove();
	$(".ui-paging-info", topPagerDiv).remove();
	
	var bottomPagerDiv = $("div#pager")[0];
	$("#search_list", bottomPagerDiv).remove();
	
	function selectRow(id){
		// get value
		jQuery("#list").GridToForm(id,"#lovValueForm");
		
		// set value to form
		var lov_id_val = $("#lov_id").val();
		var lov_name_val = $("#lov_name").val();
		var lov_tel_val = $("#lov_tel").val();
		$("#serviceOrderID").val(lov_id_val);
		$("#serviceOrderDate").html($("#lov_serviceOrderDate").val());
		$("#serviceOrderType").html($("#lov_serviceType").val());
		$("#appointmentDate").html($("#lov_appointmentDate").val());
		$("#customerID").html($("#lov_customerID").val());
		$("#contactName").html(lov_name_val);
		//$("#contactName").val(lov_name_val+" "+lov_surname_val);
		$("#customerName").val(lov_name_val);
		$("#company").html($("#lov_company").val());
		$("#address").html($("#lov_address").val());
		$("#email").html($("#lov_email").val());
		//$("#tel").html(lov_tel_val);
		$("#tel").val(lov_tel_val);
		//$("#mobileTel").html($("#lov_mobileTel").val());
		$("#mobileTel").val($("#lov_mobileTel").val());
		$("#deliveryCustomer").html($("#lov_deliveryCustomer").val());
		$("#deliveryEmail").html($("#lov_deliveryEmail").val());
		$("#deliveryTel").html($("#lov_deliveryTel").val());
		$("#deliveryMobileTel").html($("#lov_deliveryMobileTel").val());
		$("#productID").html($("#lov_productID").val());
		//$("#type").html($("#lov_type").val());
		//$("#brand").html($("#lov_brand").val());
		//$("#model").html($("#lov_model").val());
		//$("#serialNo").html($("#lov_serialNo").val());
		$("#serialNo").val($("#lov_serialNo").val());
		$("#accessories").html($("#lov_accessories").val());
		$("#description").html($("#lov_description").val());
		$("#problem").html($("#lov_problem").val());
		$("#os_problem").val($("#lov_problem").val());
		$("#empOpen").html($("#lov_empOpen").val());
		
		setTypeBrandModel();
		
		tDialog.dialog( "destroy" );
		// init lov for call again
		initLov();
	}
	
	$("#searchServiceOrderButton").click(function() {
		gridReload();
	});
	
	
	$( "#type_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#type");
			var selected = select.children( ":selected" );
			if ( !ui.item ) {
				var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
					valid = false;
				select.children( "option" ).each(function() {
					if ( $( this ).text().match( matcher ) ) {
						this.selected = valid = true;
						return false;
					}
				});
			 	if ( !valid ){
					// remove invalid value, as it didn't match anything
					//$(this).val( "" );
					//select.val( "" );
					//this.data( "autocomplete" ).term = "";
					$( "#type_autoComplete" ).data( "autocomplete" ).term = "";
					// get text from blank value option
					$( this ).val(select.children( ":selected" ).text());
					
					//$("#brand_autoComplete").trigger('change');
					//getModelSelectList();
					return false;
				}
			 }else{
				 $.getJSON('${findBrandURL}', {
					typeID : select.val(),
					ajax : 'true'
				}, function(data) {
					var html = '';
					var len = data.length;
					if(len > 0){
						for ( var i = 0; i < len; i++) {
							html += '<option value="' + data[i].brandID + '">'
									+ data[i].name + '</option>';
						}
						html += '</option>';
					}else{
						html += '<option value=""></option>';
					}
					
					$('#brand').html(html);
					
					$('#brand_autoComplete').width($('#brand').width());
					$('#brand_autoComplete').val($("#brand :selected").text());
					
					$("#brandRow").css("z-index", 9);
					
					$("#brand_autoComplete").trigger('change');
					getModelSelectList();
				});
			 }
		}
	});
	
	$( "#brand_autoComplete" ).autocomplete({
		change: function(event, ui) {
	//$( "#brand_autoComplete" ).bind( "autocompletechange", function(event, ui) {
			var select = $("#brand");
			var selected = select.children( ":selected" );
			if ( !ui.item ) {
				var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
					valid = false;
				select.children( "option" ).each(function() {
					if ( $( this ).text().match( matcher ) ) {
						this.selected = valid = true;
						return false;
					}
				});
			 	if ( !valid ){
					// remove invalid value, as it didn't match anything
				//	$(this).val( "" );
				//	select.val( "" );
					$( "#brand_autoComplete" ).data( "autocomplete" ).term = "";
					// get text from blank value option
					$( this ).val(select.children( ":selected" ).text());
					
					// set model to empty
				//	html += '<option value=""></option>';
				//	$('#model').html(html);
				//	$('#model_autoComplete').width($('#model').width());
				//	$('#model_autoComplete').val($("#model :selected").text());
					
				//	$("#modelRow").css("z-index", 8);
					return false;
				}
			 }else{
				 getModelSelectList();
			 }
		}
	});
	
	$( "#model_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#model");
			var selected = select.children( ":selected" );
			if ( !ui.item ) {
				var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
					valid = false;
				select.children( "option" ).each(function() {
					if ( $( this ).text().match( matcher ) ) {
						this.selected = valid = true;
						return false;
					}
				});
			 	if ( !valid ){
					// remove invalid value, as it didn't match anything
					//$(this).val( "" );
					//select.val( "" );
					//this.data( "autocomplete" ).term = "";
					$( "#model_autoComplete" ).data( "autocomplete" ).term = "";
					// get text from blank value option
					$( this ).val(select.children( ":selected" ).text());
					
					//$("#brand_autoComplete").trigger('change');
					//getModelSelectList();
					return false;
				}
			 }
		}
	});
	
	function getModelSelectList(){
		var select = $("#brand");
		$.getJSON('${findModelURL}', {
			typeID : $("#type").val(),
			brandID :select.val(),
			ajax : 'true'
		}, function(data) {
			var html = '';
			var len = data.length;
			if(len > 0){
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].modelID + '">'
							+ data[i].name + '</option>';
				}
				html += '</option>';
			}else{
				html += '<option value=""></option>';
			}
			
			$('#model').html(html);
			
			$('#model_autoComplete').width($('#model').width());
			$('#model_autoComplete').val($("#model :selected").text());
			
			$("#modelRow").css("z-index", 8);
		});
	}
	
	function setTypeBrandModel(){
		$("#type").val($("#lov_typeID").val());
		$('#type_autoComplete').val($("#type :selected").text());
	
		$("#type_autoComplete").trigger('change');

		var select = $("#type");
		console.info(select.val());
		$.getJSON('${findBrandURL}', {
			typeID : select.val(),
			ajax : 'true'
		}, function(data) {
			var html = '';
			var len = data.length;
			if(len > 0){
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].brandID + '">'
							+ data[i].name + '</option>';
				}
				html += '</option>';
			}else{
				html += '<option value=""></option>';
			}
			
			$('#brand').html(html);
			
			$('#brand').val($('#lov_brandID').val());
			
			$('#brand_autoComplete').width($('#brand').width());
			$('#brand_autoComplete').val($("#brand :selected").text());
			
			$("#brandRow").css("z-index", 9);
			
			$("#brand_autoComplete").trigger('change');


			var selectBrand = $("#brand");
			$.getJSON('${findModelURL}', {
				typeID : $("#type").val(),
				brandID :selectBrand.val(),
				ajax : 'true'
			}, function(data) {
				var html = '';
				var len = data.length;
				if(len > 0){
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].modelID + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';
				}else{
					html += '<option value=""></option>';
				}
				
				$('#model').html(html);
				
				$('#model').val($('#lov_modelID').val());
				
				$('#model_autoComplete').width($('#model').width());
				$('#model_autoComplete').val($("#model :selected").text());
				
				$("#modelRow").css("z-index", 8);
			});
			
		});
		
	}
	
	function setBrandModelSelectList(){
		
	}
	
});

function gridReload(){
	var name = jQuery("#name").val();
	//var surname = jQuery("#surname").val();
	jQuery("#list").jqGrid('setGridParam',{url:"searchServiceOrder.html?name="+name,page:1}).trigger("reloadGrid");
}





//function doPrint(){
//	document.forms["printForm"].submit();
//}


</script>