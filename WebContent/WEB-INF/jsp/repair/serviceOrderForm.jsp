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
			<form:form commandName="form" id="form" class="jqtransform" action="serviceOrder.html?do=save">
				<input type="hidden" name="mode" value="${mode}"/>
				<table width="100%">
					<tr>
						<td width="13%"><label><fmt:message key="serviceOrderDate" />:</label></td>
						<td align="left" colspan="3"><div class="rowElem"><form:input path="serviceOrderDate" class="textboxMockup" readonly="true" /></div></td>
						<td width="40%"><label><fmt:message key="serviceOrderID" />:</label></td>
						<td><div class="rowElem"><form:input path="serviceOrderID" readonly="true" class="textboxMockup" /></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrderType" />:</label></td>
						<td colspan="5">
							<div class="rowElem">
								<c:if test="${mode=='add'}">
									<form:radiobutton path="serviceType" value="1" cssStyle="margin-top:4px" id="serviceType_guarantee" onclick="checkServiceType()" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrderType_guarantee" /></label><form:select path="guaranteeNo" id="guaranteeNo" ><c:forEach var="i" begin="1" end="7" step="1"><form:option value="${i}" /></c:forEach></form:select>
									<form:radiobutton path="serviceType" value="2" cssStyle="margin-top:4px;" id="serviceType_repair" onclick="checkServiceType()" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrderType_repair" /></label>
									<form:radiobutton path="serviceType" value="3" cssStyle="margin-top:4px" id="serviceType_claim" onclick="checkServiceType()" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrderType_claim" /></label>
									<form:radiobutton path="serviceType" value="4" cssStyle="margin-top:4px" id="serviceType_outsiteService" onclick="checkServiceType()" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrderType_outsiteService" /></label><form:input path="refJobID" class="textboxMockup" id="refJobID" maxlength="30" />
									<form:radiobutton path="serviceType" value="5" cssStyle="margin-top:4px" id="serviceType_refix" onclick="checkServiceType()" /><label style="float:left; margin-top:4px;"><fmt:message key="serviceOrderType_refix" /></label><form:input path="refServiceOrder" class="textboxMockup" id="refServiceOrder" maxlength="20" />
								</c:if>
								<c:if test="${mode=='edit'}">
									<form:radiobutton path="serviceType" value="1" cssStyle="margin-top:4px" id="serviceType_guarantee" disabled="true" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrderType_guarantee" /></label><form:select path="guaranteeNo" id="guaranteeNo" disabled="true" ><c:forEach var="i" begin="1" end="7" step="1"><form:option value="${i}" /></c:forEach></form:select>
									<form:radiobutton path="serviceType" value="2" cssStyle="margin-top:4px;" id="serviceType_repair" disabled="true" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrderType_repair" /></label>
									<form:radiobutton path="serviceType" value="3" cssStyle="margin-top:4px" id="serviceType_claim" disabled="true" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrderType_claim" /></label>
									<form:radiobutton path="serviceType" value="4" cssStyle="margin-top:4px" id="serviceType_outsiteService" disabled="true" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrderType_outsiteService" /></label> <form:input path="refJobID" class="textboxMockup" id="refJobID" maxlength="30" readonly="true" />
									<form:radiobutton path="serviceType" value="5" cssStyle="margin-top:4px" id="serviceType_refix" disabled="true" /><label style="float:left; margin-top:4px;"><fmt:message key="serviceOrderType_refix" /></label><form:input path="refServiceOrder" class="textboxMockup" id="refServiceOrder" maxlength="20" readonly="true" />
									<form:hidden path="serviceType"/>
								</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="appointmentDate"/>:</label></td>
						<td colspan="5">
							<div class="rowElem" style="z-index:200">
								<form:input path="appointmentDate" class="textboxMockup" id="appointmentDate"/>
							</div>
						</td>
					</tr>
					<tr align="left">
						<td colspan="6">
							<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="customerDetail" /></u></b></div>
						</td>
					</tr>
					<%--tr>
						<td><label><fmt:message key="customerType" />:</label></td>
						<td colspan="5">
							<div class="rowElem">
								<c:if test="${mode=='add'}">
									<form:radiobutton path="customerType" value="shop" cssStyle="margin-top:4px;" id="customerType_shop" onclick='checkCustomerType(); setCustID();'/><label style="float:left; margin-top:4px"><fmt:message key="customerType_shop" /></label> <form:radiobutton path="customerType" value="walkin" cssStyle="margin-top:4px" id="customerType_walkin" onclick="checkCustomerType(); setCustID();"/><label style="float:left; margin-top:4px;"><fmt:message key="customerType_walkin" /></label>
								</c:if>
								<c:if test="${mode=='edit'}">
									<form:radiobutton path="customerType" value="shop" cssStyle="margin-top:4px;" id="customerType_shop" onclick='checkCustomerType(); setCustID();' disabled="true"/><label style="float:left; margin-top:4px"><fmt:message key="customerType_shop" /></label> <form:radiobutton path="customerType" value="walkin" cssStyle="margin-top:4px" id="customerType_walkin" onclick="checkCustomerType(); setCustID();" disabled="true"/><label style="float:left; margin-top:4px;"><fmt:message key="customerType_walkin" /></label>
									<form:hidden path="customerType"/>
								</c:if>
							</div>
						</td>
					</tr--%>
					<tr>
						<td><label><fmt:message key="customerID" />:</label></td>
						<!-- td align="left"><div class="rowElem"><form:input path="customerID" class="textboxMockup" style="float:left" id="custID" readonly="true" size="8" maxlength="10"/> <input type="button" id="lov" value="..." > <label class="error" for="custID" generated="true" style="display: none; float:left; padding-left:10px"></label></div></td>
						<td><label><fmt:message key="companyName" />:</label></td>
						<td colspan="3" align="left"><div class="rowElem"><span id="company">&nbsp;</span></div></td-->
						
						<td align="left" colspan="5">
							<div class="rowElem">
								<c:if test="${mode=='add'}">
									<form:input path="customerID" class="textboxMockup" style="float:left" id="custID" readonly="true" size="9" maxlength="10"/> <input type="button" id="lov" value="..." > <label class="error" for="custID" generated="true" style="display: none; float:left; padding-left:10px"></label>
								</c:if>
								<c:if test="${mode=='edit'}">
									<form:input path="customerID" class="textboxMockup" style="float:left" id="custID" readonly="true" size="9" maxlength="10"/>
								</c:if>
							</div>
						</td>
					</tr>
					<%-- tr>
						<td><label><fmt:message key="address" />:</label></td>
						<td colspan="5" align="left"><div class="rowElem"><span id="address">${fullAddr}&nbsp;</span></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="contactName" />:</label></td>
						<td><div class="rowElem"><span id="contactName">${customer.name}&nbsp;${customer.surname}</span></td>
						<td><label><fmt:message key="tel" />:</label></td>
						<td><div class="rowElem"><span id="tel">${customer.tel}&nbsp;</span></td>
						<td><label><fmt:message key="mobileTel" />:</label></td>
						<td><div class="rowElem"><span id="mobileTel">${customer.mobileTel}&nbsp;</span></td>
					</tr--%>
					
					
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
									<td><div class="rowElem"><form:input path="deliveryCustomer" class="textboxMockup" style="float:left" id="deliveryCustomer" size="30" maxlength="255"/></div></td>
									<td><label><fmt:message key="email" />:</label></td>
									<td><div class="rowElem"><form:input path="deliveryEmail" class="textboxMockup" style="float:left" id="deliveryEmail" maxlength="50"/></div></td>
									<td><label><fmt:message key="tel" />:</label></td>
									<td><div class="rowElem"><form:input path="deliveryTel" class="textboxMockup" style="float:left" id="deliveryTel" maxlength="100"/></div></td>
									<td><label><fmt:message key="mobileTel"/>:</label></td>
									<td><div class="rowElem"><form:input path="deliveryMobileTel" class="textboxMockup" style="float:left" id="deliveryMobileTel" maxlength="100"/></div></td>
								</tr>
							</table>
						</td>
					</tr>
					
					
					
					
					<%-- tr>
						<td colspan="6">
							<div id="shopCustomerDetail">
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td width="13%"><label><fmt:message key="name" />:</label></td>
										<td><div class="rowElem"><span id="div_shopCus_name"></span>&nbsp;</div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="address" />:</label></td>
										<td><div class="rowElem"><span id="div_shopCus_addr">${fullAddr}</span>&nbsp;</div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="tel" />:</label></td>
										<td><div class="rowElem"><span id="div_shopCus_tel"></span>&nbsp;</div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="contactName" />:</label></td>
										<td><div class="rowElem"><span id="div_shopCus_contact"></span>&nbsp;</div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="remark" />:</label></td>
										<td><div class="rowElem"><span id="div_shopCus_remark"></span>&nbsp;</div></td>
									</tr>
								</table>
							</div>
							<div id="walkinCustomerDetail">
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td width="13%"><label><fmt:message key="name" />:</label></td>
										<td width="37%"><div class="rowElem"><span id="div_walkinCus_name">${customer.name}&nbsp;${customer.surname}</span>&nbsp;</div></td>
										<td width="13%"><label><fmt:message key="companyName" />:</label></td>
										<td><div class="rowElem"><span id="div_walkinCus_company"></span>&nbsp;</div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="address" />:</label></td>
										<td colspan="3"><div class="rowElem"><span id="div_walkinCus_addr">${fullAddr}&nbsp;</span></div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="tel" />:</label></td>
										<td colspan="3"><div class="rowElem"><span id="div_walkinCus_tel">${customer.tel}</span>&nbsp;</div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="mobileTel" />:</label></td>
										<td colspan="3"><div class="rowElem"><span id="div_walkinCus_mobileTel">${customer.mobileTel}</span>&nbsp;</div></td>
									</tr>
								</table>
							</div>
						</td>
					</tr--%>
					
					<tr align="left">
						<td colspan="6">
							<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="productDetail" /></u></b></div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="productID" />:</label></td>
						<td colspan="5">
							<div class="rowElem">
								<c:if test="${mode == 'add'}">
									<form:input path="productID" class="textboxMockup" style="float:left" id="productID" readonly="true" size="18" maxlength="20"/> <input type="button" id="productLov" value="..." > <label class="error" for="productID" generated="true" style="display: none; float:left; padding-left:10px"></label>
								</c:if>
								<c:if test="${mode == 'edit'}">
									<form:input path="productID" class="textboxMockup" style="float:left" id="productID" readonly="true" size="18" maxlength="20"/>
								</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="type" />:</label></td>
						<td colspan="2">
							<div class="rowElem">
								<%-->form:select path="typeID" id="type">
									<form:options items="${typeList}" itemValue="typeID" itemLabel="name"/>
								</form:select--%>
								<span id="typeTxt">${product.type.name}&nbsp;</span>
							</div>
						</td>
						<td><label><fmt:message key="brand" />:</label></td>
						<td colspan="2">
							<div class="rowElem">
								<%--form:select path="brandID" id="brand">
									<form:options items="${brandList}" itemValue="brandID" itemLabel="name"/>
								</form:select--%>
								<span id="brandTxt">${product.brand.name}&nbsp;</span>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="model" />:</label></td>
						<td colspan="2"><div class="rowElem"><%--form:input path="model" class="textboxMockup" maxlength="100"/--%><span id="modelTxt">${product.model.name}&nbsp;</span></span></div></td>
						<td><label><fmt:message key="serialNo" />:</label></td>
						<td colspan="2"><div class="rowElem"><%-->form:input path="serialNo" class="textboxMockup" maxlength="100"/--%><span id="serialNoTxt">${product.serialNo}&nbsp;</span></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="accessories" />:</label></td>
						<td colspan="2"><div class="rowElem"><form:input path="accessories" class="textboxMockup" style="width:98%" maxlength="5000"/></div></td>
						<td><label><fmt:message key="serviceOrder_desc" />:</label></td>
						<td colspan="2"><div class="rowElem"><form:input path="desc" class="textboxMockup" maxlength="255"/></div></td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:7px;"><label><fmt:message key="serviceOrder_problem" />:<font color="red">*</font></label></td>
						<td colspan="5" align="left"><div class="rowElem"><form:textarea path="problem" rows="5" col="30" class="textareaMockup" style="width:98%" name="problem" ></form:textarea><label class="error" for="problem" generated="true" style="display: none; float:left; padding-left:10px"></label></div></td>
					</tr>
					<tr align="center">
						<td colspan="6"><div class="rowElem"><input type="submit" value="<fmt:message key='button.ok' />" /></div></td>
					</tr>
				</table>
			</form:form>
		</td>
	</tr>
</table>

<!-- Start walk in customer lov -->

<div id="dialog-modal" title='<fmt:message key="searchCustomer" />'>
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
							<td><div class="rowElem"><input type="button" id="searchCustomerButton" value='<fmt:message key="button.search" />' ></div></td>
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
		<input type="hidden" id="lov_id" name="customerID">
		<input type="hidden" id="lov_name" name="name">
		<%--input type="hidden" id="lov_surname" name="surname">
		<input type="hidden" id="lov_fullName" name="fullName"--%>
		<input type="hidden" id="lov_company" name="company">
		<input type="hidden" id="lov_address" name="address">
		<input type="hidden" id="lov_tel" name="tel">
		<input type="hidden" id="lov_mobileTel" name="mobileTel">
		<input type="hidden" id="lov_email" name="email">
	</form>
</div>

<div id="add-form" title='<fmt:message key="addCustomer" />'>
	<form:form commandName="customerForm" id="customerForm" class="jqtransform" action="JavaScript:saveCustomer();">
		<table width="100%">
			<tr>
				<td width="20%"><label><fmt:message key="customerID" />:</label></td>
				<td><div class="rowElem"><form:input path="customerID" id="customerID" readonly="true" class="textboxMockup" /></div></td>
			</tr>
			<tr>
				<td><label><fmt:message key="customerType" />:</label></td>
				<td>
					<div class="rowElem">
						<form:select path="customerTypeID" id="customerType">
							<form:options items="${customerTypeList}" itemValue="customerTypeID" itemLabel="name"/>
						</form:select>
					</div>
				</td>
			</tr>
			<tr>
				<td><label><fmt:message key="name" />:<font style="color:red">*</font></label></td>
				<td><div class="rowElem"><form:input path="name" id="custName" class="required textboxMockup" maxlength="255" /> <label class="error" for="name" generated="true" style="display: none; padding-left:10px"></label></div></td>
			</tr>
			<tr>
				<td valign="top"><div class="rowElem"><label><fmt:message key="address" />:</label></div></td>
				<td>
					<div class="rowElem">
						<form:input path="address" id="address" class="textboxMockup" style="width:640px" maxlength="255"/><br><br>
						
						<div style="float:left; margin-top:5px;"><fmt:message key="subdistrict" />:</div>
						<form:select id="lovForm_subdistrict" path="subdistrictID" items="${subdistrictList}" itemValue="subdistrictID" itemLabel="name">
						</form:select>
						
						<div style="float:left; margin:5px 0 0 15px;"><fmt:message key="district" />:</div>
						<form:select id="lovForm_district" path="districtID" items="${districtList}" itemValue="districtID" itemLabel="name">
						</form:select>
						
						<div style="float:left; margin:5px 0 0 15px;"><fmt:message key="province" />:</div>
						<form:select id="lovForm_province" path="provinceID" items="${provinceList}" itemValue="provinceID" itemLabel="name" />
						
						<%--div style="float:left; margin:5px 0 0 15px;">zipcode:</div> <input type="text" size="5" readonly="readonly" value="22000"/--%>
					</div>
				</td>
			</tr>
			<tr>
				<td><label><fmt:message key="tel" />:</label></td>
				<td><div class="rowElem"><form:input path="tel" id="cTel" class="textboxMockup telephone" maxlength="100"/></div></td>
			</tr>
			<tr>
				<td><label><fmt:message key="mobileTel" />:</label></td>
				<td><div class="rowElem"><form:input path="mobileTel" id="cMobileTel" class="textboxMockup telephone" maxlength="100"/></div></td>
			</tr>
			<tr>
				<td><label><fmt:message key="email" />:</label></td>
				<td><div class="rowElem"><input type="text" name="email" id="email" class="textboxMockup" maxlength="50" /></div></td>
			</tr>
			<tr align="center">
				<td colspan="2"><div class="rowElem"><input type="submit" value='<fmt:message key="button.ok" />' /></div></td>
			</tr>
		</table>
	<%--/form--%>
	</form:form>
</div>

<!-- End walk in customer lov -->

<!-- Start shop customer lov -->

<div id="shop-customer-dialog-modal" title='<fmt:message key="searchCustomer" />'>
	<table>
		<tr>
			<td>
				<form id="shopCustomerLovForm" class="lov">
					<table>
						<tr>
							<td><label><fmt:message key="name" />:</label></td>
							<td><div class="rowElem"><input type="text" id="shopCustomerName" class="textboxMockup"></div></td>
						</tr>
						<tr>
							<td></td>
							<td><div class="rowElem"><input type="button" id="searchShopCustomerButton" value='<fmt:message key="button.search" />' ></div></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<table id="listShopCustomer"></table>
				<div id="pagerShopCustomer"></div>
			</td>
		</tr>
	</table>
	<form id="lovShopCustomerValueForm">
		<input type="hidden" id="lov_shopCustomer_id" name="cuscod"/>
		<input type="hidden" id="lov_shopCustomer_custyp" name="custyp"/>
		<input type="hidden" id="lov_shopCustomer_name" name="name"/>
		<input type="hidden" id="lov_shopCustomer_address" name="fullAddr"/>
		<input type="hidden" id="lov_shopCustomer_tel" name="tel"/>
		<input type="hidden" id="lov_shopCustomer_mobileTel" name="mobileTel"/>
		<input type="hidden" id="lov_shopCustomer_email" name="email"/>
		<%--input type="hidden" id="lov_shopCustomer_contact" name="contact"/--%>
		<input type="hidden" id="lov_shopCustomer_remark" name="remark"/>
	</form>
</div>

<!-- End shop customer lov -->


<!-- Start product lov -->

<div id="product-dialog-modal" title='<fmt:message key="searchProduct" />'>
	<table>
		<tr>
			<td>
				<!--form id="lovform" class="jqtransform" action="#"-->
				<form id="productLovForm" class="lov" action="#">
					<table>
						<tr>
							<td><label><fmt:message key="type" />:</label></td>
							<td>
								<div class="rowElem">
									<select id="lovType">
										<option value="">-</option>
										<c:forEach var="type" items="${typeList}">
											<option value="${type.typeID}">${type.name}</option>
										</c:forEach>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="brand" />:</label></td>
							<td id="brandRow" style="z-index:9">
								<div class="rowElem">
									<select id="lovBrand">
										<option value="">-</option>
											<c:forEach var="brand" items="${brandList}">
												<c:if test="${brand.brandID != null }">
													<option value="${brand.brandID}">${brand.name}</option>
												</c:if>
											</c:forEach>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="model" />:</label></td>
							<td id="modelRow" style="z-index:8">
								<div class="rowElem">
									<select id="lovModel">
										<option value="">-</option>
											<c:forEach var="model" items="${modelList}">
												<c:if test="${fn:length(model.modelID) > 0}">
													<option value="${model.modelID}">${model.name}</option>
												</c:if>
											</c:forEach>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><div class="rowElem"><input type="button" id="searchProductButton" value='<fmt:message key="button.search" />' ></div></td>
						</tr>
					</table>
					<%--table>
						<tr>
							<td><label><fmt:message key="name" />:</label></td>
							<td><div class="rowElem"><input type="text" id="name" class="textboxMockup"></div></td>
						</tr>
						<tr>
							<td></td>
							<td><div class="rowElem"><input type="button" id="searchCustomerButton" value='<fmt:message key="button.search" />' ></div></td>
						</tr>
					</table--%>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<table id="productList"></table>
				<div id="productPager"></div>
			</td>
		</tr>
	</table>
	<form id="lovProductForm">
		<input type="hidden" id="lov_product_id" name="productID"/>
		<input type="hidden" id="lov_product_type" name="typeName"/>
		<input type="hidden" id="lov_product_brand" name="brandName"/>
		<input type="hidden" id="lov_product_model" name="modelName"/>
		<input type="hidden" id="lov_product_serialNo" name="serialNo"/>
	</form>
</div>

<!-- End product lov -->


<!-- form:form commandName="docForm" id="printJasperForm" method="post" action="serviceOrder.xls?do=printExcel" target="original" onSubmit="window.open('', 'original', 'width=450,height=300,status=yes,resizable=yes,scrollbars=yes')"-->
<form:form commandName="docForm" id="printJasperForm" method="post" action="serviceOrder.xls?do=printExcel">
	<form:hidden path="serviceOrderID" />
	<form:hidden path="serviceOrderDate" />
	<form:hidden path="serviceOrderTime" />
	<form:hidden path="appointmentDate" />
	<form:hidden path="serviceType" />
	<form:hidden path="guaranteeNo" />
	<form:hidden path="refJobID" />
	<form:hidden path="refServiceOrder" />
 	<form:hidden path="customerID" />
	<form:hidden path="name" />
	<form:hidden path="address" />
	<form:hidden path="subdistrict" />
	<form:hidden path="district" />
	<form:hidden path="province" />
	<form:hidden path="zipcode" />
	<form:hidden path="email" />
	<form:hidden path="tel" />
	<form:hidden path="mobileTel" />
	<form:hidden path="deliveryCustomer" />
	<form:hidden path="deliveryEmail" />
	<form:hidden path="deliveryTel" />
	<form:hidden path="deliveryMobileTel" />
	<form:hidden path="typeID" />
	<form:hidden path="type" />
	<form:hidden path="brandID" />
	<form:hidden path="brand" />
	<form:hidden path="model" />
	<form:hidden path="serialNo" />
	<form:hidden path="accessories" />
	<form:hidden path="desc" />
	<form:hidden path="problem" />
	<form:hidden path="empOpenID" />
</form:form>

<div id="dialog" title="Feature not supported" style="display:none">
	<p>That feature is not supported.</p>
</div>

<c:url var="findDistrictByProvinceURL" value="/findDistrit.html" />
<c:url var="findSubdistrictByDistrictURL" value="/findSubdistrict.html" />
<c:url var="saveCustomerPopupURL" value="/customer.html?do=savePopup" />
<c:url var="findBrandURL" value="/brand.html?do=getBrandByType" />
<c:url var="findModelURL" value="/model.html?do=getModel" />

<script type="text/javascript">

$(document).ready(function(){
	
	//$("#appointmentDate").calendarsPicker($.extend({calendar: $.calendars.instance('thai','th')}));
	$('#appointmentDate').datetimeEntry({datetimeFormat: 'D/O/Y H:M'});
	
	$("#shopCustomerDetail").hide();
	$("#walkinCustomerDetail").hide();
	
	<c:if test="${action == 'print'}">
		document.forms["printJasperForm"].submit();
	</c:if>
	
	$("#form").validate({
		rules: {
			customerID: "required",
			problem:{
				required: true,
				maxlength: 1000
			},
			deliveryEmail: "email"
		}
	});
	
	$("#customerForm").validate({
		rules: {
			email: "email",
			tel: {require_from_group: [1,".telephone"]},
			mobileTel: {require_from_group: [1,".telephone"]}
		}
	});
	
	$("#button").click(function(){
		$("form").valid();
	});
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
	$("form.lov").jqTransform();
	$("form.customerForm").jqTransform();
	
	checkServiceType();
	
	// check type of customer for display detail
//	checkCustomerType();
	
	// init walk in customer lov dialog
	var tDialog = $( "#dialog-modal" ).dialog({
		autoOpen: false,
		height: 540,
		width: 530,
		modal: true
	});
	
	function initLov(){
		tDialog = $( "#dialog-modal" ).dialog({
			autoOpen: false,
			height: 540,
			width: 530,
			modal: true
		});
	}
	
	// init shop customer lov dialog
	var shopDialog = $( "#shop-customer-dialog-modal" ).dialog({
		autoOpen: false,
		height: 540,
		width: 700,
		modal: true
	});
	
	function initShopLov(){
		shopDialog = $( "#shop-customer-dialog-modal" ).dialog({
			autoOpen: false,
			height: 540,
			width: 700,
			modal: true
		});
	}

	// init product lov dialog
	var productDialog = $("#product-dialog-modal").dialog({
		autoOpen: false,
		height: 540,
		width: 700,
		modal: true
	});
	
	function initProductLov(){
		productDialog = $("#product-dialog-modal").dialog({
			autoOpen: false,
			height: 540,
			width: 700,
			modal: true
		});
	}
	
	// Add event click to lov button
	$("#lov").click( function(e) {
	/*	if(document.getElementById('customerType_shop').checked){
			shopDialog.dialog("open");
		}else 
		if(document.getElementById('customerType_walkin').checked){*/
			tDialog.dialog("open");
	//	}
		
	});
	
	// Add event click to product lov button
	$("#productLov").click( function(e){
		productDialog.dialog("open");
	});
	
	// walk in customer jqgrid
	jQuery("#list").jqGrid({
		url:"searchCustomer.html",
		datatype: "json",
		//height: 230,
		height: "100%",
		//autoheight: true,
		//width: "100%",
		autowidth: true,
		colNames:['<fmt:message key="customerID" />','<fmt:message key="name" />','<fmt:message key="company" />','<fmt:message key="address" />','<fmt:message key="tel" />','<fmt:message key="mobileTel" />','<fmt:message key="email" />'],
		colModel:[
			{name:'customerID',index:'customerID', sorttype:"int"},
			{name:'name',index:'name', editable:true, width:'315'},
			{name:'company',index:'company', hidden:true},
			{name:'address',index:'address', hidden:true},
			{name:'tel',index:'tel', hidden:true},
			{name:'mobileTel',index:'mobileTel', hidden:true},
			{name:'email',index:'email', hidden:true}],
		multiselect: false,
		//caption: "Manipulating Array Data",
		rownumbers: true,
		rowNum:10,
		rowList:[10,20,30],
		viewrecords: true,
		jsonReader:{
			repeatitems: false,
			id: "customerID"
		},
		pager: '#pager',
		toppager: true,
		onSelectRow: function(id){
			selectRow(id);
			//alert('Selected row ID ' + id);
			
			//$( "#dialog-modal:ui-dialog" ).dialog( "destroy" );
			//tDialog.dialog( "destroy" );
		}
	}).navGrid("#pager",{edit:false,add:false,del:false,search:false,refresh:false,cloneToTop:true})
	//}).navGrid("#pager",{edit:false,add:false,del:false,search:false,refresh:false})
	.navButtonAdd('#list_toppager',
		{
			//caption:"<fmt:message key='button.add' />", 
			caption:"", 
			title:"<fmt:message key='button.add' />",
			//buttonimg:"row.gif",
			buttonicon:"ui-icon-plus", 
			onClickButton: function(){ 
				// Call example popup form*/
				//$( "#dialog-form" ).dialog( "open" );
				
				$( "#add-form" ).dialog( "open" );
			}, 
			position:"last"
	});
	
	// walk in customer pager
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
		//var lov_name_val = $("#lov_name").val();
		//var lov_surname_val = $("#lov_surname").val();
		var lov_tel_val = $("#lov_tel").val();
		$("#custID").val(lov_id_val);
		//$("#contactName").html(lov_name_val+" "+lov_surname_val);
		//$("#contactName").html($("#lov_fullName").val());
		$("#contactName").html($("#lov_name").val());
		//$("#company").html($("#lov_company").val());
		$("#address").html($("#lov_address").val());
		$("#tel").html(lov_tel_val);
		$("#mobileTel").html($("#lov_mobileTel").val());
		$("#email").html($("#lov_email").val());
	
		
		
		$("#div_walkinCus_name").html($("#lov_fullName").val());
		$("#div_walkinCus_addr").html($("#lov_address").val());
		$("#div_walkinCus_company").html($("#lov_company").val());
		$("#div_walkinCus_tel").html(lov_tel_val);
		$("#div_walkinCus_mobileTel").html($("#lov_mobileTel").val());
		
		tDialog.dialog( "destroy" );
		// init lov for call again
		initLov();
	}
	
	
	$("#searchCustomerButton").click(function() {
		gridReload();
	});
	
	// add customer
	
	$( "#add-form" ).dialog({
		autoOpen: false,
		height: 420,
		width: 900,
		modal: true
	});
	
	//$("#province").change(
	$("#lovForm_province").change(
		function() {
			$.getJSON('${findDistrictByProvinceURL}', {
				provinceID : $(this).val(),
				ajax : 'true'
			}, function(data) {
				//var html = '<option value="">District</option>';
				var html = '';
				var len = data.length;
				if(len > 0){
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].districtID + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';
				}else{
					html += '<option value=""></option>';
				}
 
				$('#lovForm_district').html(html);
				
				// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
				// bug because ref to color id, it should be td id
				//var sty = $("#color div.jqTransformSelectWrapper").attr("style");
				
				var sels = $("#lovForm_district").removeClass("jqTransformHidden");
				var $par = $("#lovForm_district");
				$par.parent().replaceWith($par);
				sels.jqTransSelect();
				// bug because ref to color id, it should be td id
				//$("#color div.jqTransformSelectWrapper").attr("style", sty);
				
				// trigger event change of district select list
				$("#lovForm_district").change();
			});
		}
	);
	
	$("#lovForm_district").change(
		function(){
			$.getJSON('${findSubdistrictByDistrictURL}', {
				districtID : $(this).val(),
				ajax : 'true'
			}, function(data) {
				var html = '';
				var len = data.length;
				if(len > 0){
					for ( var i = 0; i < len; i++){
						html += '<option value="' + data[i].subdistrictID + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';
				}else{
					html += '<option value=""></option>';
				}
				
				$('#lovForm_subdistrict').html(html);
				
				// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
				
				// bug because ref to color id, it should be td id
				//var sty = $("#color div.jqTransformSelectWrapper").attr("style");
				
				var sels = $("#lovForm_subdistrict").removeClass("jqTransformHidden");
				var $par = $("#lovForm_subdistrict");
				$par.parent().replaceWith($par);
				sels.jqTransSelect();
				// bug because ref to color id, it should be td id
				//$("#color div.jqTransformSelectWrapper").attr("style", sty);
			});
		}
	);

	$("#type").change(
		function(){
			//alert($("#type").val());
			$.getJSON('${findBrandURL}', {
				typeID: $(this).val()
			}, function(data){
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
				
				// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
				// bug because ref to color id, it should be td id
				//var sty = $("#color div.jqTransformSelectWrapper").attr("style");
				
				var sels = $("#brand").removeClass("jqTransformHidden");
				var $par = $("#brand");
				$par.parent().replaceWith($par);
				sels.jqTransSelect();
				// bug because ref to color id, it should be td id
				//$("#color div.jqTransformSelectWrapper").attr("style", sty);
				
				// trigger event change of district select list
				$("#brand").change();
				
			});
		}
	);
	
	
	
	$("#lovType").change(
		function(){
			$.getJSON('${findBrandURL}', {
				typeID: $(this).val()
			}, function(data){
				var html = '';
				var len = data.length;
				html += '<option value="">-</option>';
				if(len > 0){
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].brandID + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';
				}//else{
				//	html += '<option value="">-</option>';
				//}
				$('#lovBrand').html(html);
				
				// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
				// bug because ref to color id, it should be td id
				//var sty = $("#brandRow div.jqTransformSelectWrapper").attr("style");
								
				var sels = $("#lovBrand").removeClass("jqTransformHidden");
				var $par = $("#lovBrand");
				$par.parent().replaceWith($par);
				sels.jqTransSelect();
				
				// bug because ref to color id, it should be td id
				$("#brandRow div.jqTransformSelectWrapper").css("z-index", 9);
				
				// trigger event change of brand select list
				$("#lovBrand").change();
				
			});
		}
	);
	
	
	$("#lovBrand").change(
		function(){
			$.getJSON('${findModelURL}', {
				typeID : $("#lovType").val(),
				brandID : $(this).val(),
				ajax : 'true'
			}, function(data){
				var html = '';
				var len = data.length;
				html += '<option value="">-</option>';
				if(len > 0){
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].modelID + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';
				}else{
					html += '<option value="">-</option>';
				}
				$('#lovModel').html(html);
				
				// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
				// bug because ref to color id, it should be td id
				//var sty = $("#modelRow div.jqTransformSelectWrapper").attr("style");
				
				var sels = $("#lovModel").removeClass("jqTransformHidden");
				var $par = $("#lovModel");
				$par.parent().replaceWith($par);
				sels.jqTransSelect();
				// bug because ref to color id, it should be td id
				$("#modelRow div.jqTransformSelectWrapper").css("z-index", 8);
								
			});
		}
	);
	
	
	$("#searchProductButton").click(function() {
		gridProductReload();
	});
	
	// product lov jqgrid
	jQuery("#productList").jqGrid({
		url:"searchProduct.html",
		datatype: "json",
		height: "100%",
		autowidth: true,
		colNames:['<fmt:message key="type" />','<fmt:message key="brand" />','<fmt:message key="model" />','<fmt:message key="productID" />','<fmt:message key="description" />','<fmt:message key="serialNo" />'],
		colModel:[
		    {name:'typeName',index:'typeName'},
		    {name:'brandName',index:'brandName'},
			{name:'modelName',index:'modelName'},
			{name:'productID',index:'productID'},
			{name:'description',index:'description'},
			{name:'serialNo',index:'serialNo'}],
		multiselect: false,
		rownumbers: true,
		rowNum:10,
		rowList:[10,20,30],
		viewrecords: true,
		jsonReader:{
			repeatitems: false,
			id: "productID"
		},
		pager: '#productPager',
		toppager: true,
		onSelectRow: function(id){
			selectProductRow(id);
		}
	}).navGrid("#productPager",{edit:false,add:false,del:false,search:false,refresh:false,cloneToTop:true});
	/*.navButtonAdd('#productList_toppager',
	{
		caption:"",
		title:"<fmt:message key='button.add' />",
		buttonicon:"ui-icon-plus",
		onClickButton: function(){
			$( "#add-form" ).dialog( "open" );
			//window.location = '<c:url value="/product.html?do=preAdd" />';
		}, 
		position:"last"
	});*/

	// product paging
	var topPagerDiv = $("#productList_toppager")[0];
	$("#productList_toppager_center", topPagerDiv).remove();
	$(".ui-paging-info", topPagerDiv).remove();
	
	var bottomPagerDiv = $("div#productPager")[0];
	$("#search_list", bottomPagerDiv).remove();
	
	function selectProductRow(id){
		// get value
		jQuery("#productList").GridToForm(id,"#lovProductForm");
		
		// set value to form
		var lov_id_val = $("#lov_product_id").val();
		var lov_type_val = $("#lov_product_type").val();
		var lov_brand_val = $("#lov_product_brand").val();
		var lov_model_val = $("#lov_product_model").val();
		var lov_serialNo_val = $("#lov_product_serialNo").val();
		
		$("#productID").val(lov_id_val);
		$("#typeTxt").html(lov_type_val);
		$("#brandTxt").html(lov_brand_val);
		$("#modelTxt").html(lov_model_val);
		$("#serialNoTxt").html(lov_serialNo_val);
		
		/*$("#custID").val(lov_id_val);
		$("#address").html($("#lov_shopCustomer_address").val());
		$("#tel").html(lov_tel_val);*/

		/*if(lov_custyp_val == '00'){
			$("#contactName").html($("#lov_shopCustomer_fullName").val());
			$("#company").html("-");
			//$("#company").html($("#lov_company").val());
		}else{
			// customer type is company
			$("#company").html($("#lov_shopCustomer_fullName").val());
			$("#contactName").html($("#lov_shopCustomer_contact").val());	
		}
		
		$("#contactName").html(lov_name_val);
		
		//$("#div_shopCus_name").html($("#lov_shopCustomer_fullName").val());
		$("#div_shopCus_name").html(lov_name_val);
		$("#div_shopCus_addr").html($("#lov_shopCustomer_address").val());
		$("#div_shopCus_tel").html($("#lov_shopCustomer_tel").val());
		$("#div_shopCus_contact").html($("#lov_shopCustomer_contact").val());
		$("#div_shopCus_remark").html($("#lov_shopCustomer_remark").val());*/
				
		productDialog.dialog( "destroy" );
		// init lov for call again
		initProductLov();
	}

	
	
	
	// shop customer jqgrid
	jQuery("#listShopCustomer").jqGrid({
		url:"searchShopCustomer.html",
		datatype: "json",
		//height: 230,
		height: "100%",
		//autoheight: true,
		//width: "100%",
		autowidth: true,
		colNames:['<fmt:message key="customerID" />','<fmt:message key="name" />','<fmt:message key="address" />','<fmt:message key="tel" />','<fmt:message key="contactName" />','Customer type','remark','mobileTel','email'],
		colModel:[
			{name:'cuscod',index:'cuscod',width:'100'},
			{name:'fullName',index:'fullName',width:'400'},
			{name:'fullAddr',index:'fullAddr',sortable:false, hidden:true},
			{name:'tel',index:'tel',sortable:false, hidden:true},
			{name:'contact',index:'contact'},
			{name:'custyp',index:'custyp',hidden:true},
			{name:'remark',index:'remark',hidden:true},
			{name:'mobileTel',index:'mobileTel',hidden:true},
			{name:'email',index:'email',hidden:true}],
		multiselect: false,
		//caption: "Manipulating Array Data",
		rownumbers: true,
		rowNum:10,
		rowList:[10,20,30],
		viewrecords: true,
		jsonReader:{
			repeatitems: false,
			id: "cuscod"
		},
		pager: '#pagerShopCustomer',
		toppager: true,
		onSelectRow: function(id){
			selectShopCustomerRow(id);
			
			//$( "#dialog-modal:ui-dialog" ).dialog( "destroy" );
			//tDialog.dialog( "destroy" );
		}
	}).navGrid("#pagerShopCustomer",{edit:false,add:false,del:false,search:false,refresh:false,cloneToTop:true});
	//}).navGrid("#pager",{edit:false,add:false,del:false,search:false,refresh:false})
	
	// walk in customer paging
	var topPagerDiv = $("#listShopCustomer_toppager")[0];
	$("#listShopCustomer_toppager_center", topPagerDiv).remove();
	$(".ui-paging-info", topPagerDiv).remove();
	
	var bottomPagerDiv = $("div#pagerShopCustomer")[0];
	$("#search_list", bottomPagerDiv).remove();
	
	function selectShopCustomerRow(id){
		// get value
		jQuery("#listShopCustomer").GridToForm(id,"#lovShopCustomerValueForm");
		
		// set value to form
		var lov_id_val = $("#lov_shopCustomer_id").val();
		var lov_name_val = $("#lov_shopCustomer_name").val();
		var lov_tel_val = $("#lov_shopCustomer_tel").val();
		var lov_custyp_val = $("#lov_shopCustomer_custyp").val();
		$("#custID").val(lov_id_val);
		$("#address").html($("#lov_shopCustomer_address").val());
		$("#tel").html(lov_tel_val);

		if(lov_custyp_val == '00'){
			$("#contactName").html($("#lov_shopCustomer_fullName").val());
			$("#company").html("-");
			//$("#company").html($("#lov_company").val());
		}else{
			// customer type is company
			$("#company").html($("#lov_shopCustomer_fullName").val());
			$("#contactName").html($("#lov_shopCustomer_contact").val());	
		}
		
		$("#contactName").html(lov_name_val);
		
		//$("#div_shopCus_name").html($("#lov_shopCustomer_fullName").val());
		$("#div_shopCus_name").html(lov_name_val);
		$("#div_shopCus_addr").html($("#lov_shopCustomer_address").val());
		$("#div_shopCus_tel").html($("#lov_shopCustomer_tel").val());
		$("#div_shopCus_contact").html($("#lov_shopCustomer_contact").val());
		$("#div_shopCus_remark").html($("#lov_shopCustomer_remark").val());
		
		
		
		shopDialog.dialog( "destroy" );
		// init lov for call again
		initShopLov();
	}
	
	$("#searchShopCustomerButton").click(function() {
		gridShopCustomerReload();
	});
	
});

function checkServiceType(){
	
	if(document.getElementById('serviceType_repair').checked || document.getElementById('serviceType_guarantee').checked || document.getElementById('serviceType_claim').checked){
		// disable ref job ID
		if(!document.getElementById('refJobID').disabled){
			document.getElementById('refJobID').value="";
			document.getElementById('refJobID').disabled = true;
			$("#refJobID").addClass('textboxMockup_disabled');
		}
		
		// disable ref service order
		if(!document.getElementById('refServiceOrder').disabled){
			document.getElementById('refServiceOrder').value="";
			document.getElementById('refServiceOrder').disabled = true;
			$("#refServiceOrder").addClass('textboxMockup_disabled');
		}
		
		if(document.getElementById('serviceType_guarantee').checked){
			document.getElementById('guaranteeNo').disabled = false;
		}else{
			document.getElementById('guaranteeNo').disabled = true;
		}
		
		<c:if test="${mode=='edit'}">
			document.getElementById('guaranteeNo').disabled = true;
		</c:if>
		
	}
	if(document.getElementById('serviceType_outsiteService').checked){
		document.getElementById('refJobID').disabled = false;
		$("#refJobID").removeClass('textboxMockup_disabled');
		
		// disable ref service order if it not disabled
		if(!document.getElementById('refServiceOrder').disabled){
			document.getElementById('refServiceOrder').value="";
			document.getElementById('refServiceOrder').disabled = true;
			$("#refServiceOrder").addClass('textboxMockup_disabled');
		}
	}
	if(document.getElementById('serviceType_refix').checked){
		document.getElementById('refServiceOrder').disabled = false;
		$("#refServiceOrder").removeClass('textboxMockup_disabled');
		
		// disable ref job ID if it not disabled
		if(!document.getElementById('refJobID').disabled){
			document.getElementById('refJobID').value="";
			document.getElementById('refJobID').disabled = true;
			$("#refJobID").addClass('textboxMockup_disabled');
		}
	}
}

function checkCustomerType(){
	if(document.getElementById('customerType_shop').checked){
		$("#shopCustomerDetail").show();
		$("#walkinCustomerDetail").hide();
	}
	if(document.getElementById('customerType_walkin').checked){
		$("#shopCustomerDetail").hide();
		$("#walkinCustomerDetail").show();
	}
}

function setCustID(){
	if(document.getElementById('customerType_shop').checked){
		$("#custID").val($("#lov_shopCustomer_id").val());
	}
	if(document.getElementById('customerType_walkin').checked){
		$("#custID").val($("#lov_id").val());
	}
}

function gridReload(){
	var name = jQuery("#name").val();
	//var surname = jQuery("#surname").val();
	//jQuery("#list").jqGrid('setGridParam',{url:"searchCustomer.html?name="+name+"&surname="+surname,page:1}).trigger("reloadGrid");
	jQuery("#list").jqGrid('setGridParam',{url:"searchCustomer.html?name="+name,page:1}).trigger("reloadGrid");
}

function gridShopCustomerReload(){
	var name = jQuery("#shopCustomerName").val();
	jQuery("#listShopCustomer").jqGrid('setGridParam',{url:"searchShopCustomer.html?name="+name,page:1}).trigger("reloadGrid");
}

function gridProductReload(){
	var typeID = jQuery("#lovType").val();
	var brandID = jQuery("#lovBrand").val();
	var modelID = jQuery("#lovModel").val();
	jQuery("#productList").jqGrid('setGridParam',{url:"searchProduct.html?&typeID="+typeID+"&brandID="+brandID+"&modelID="+modelID,page:1}).trigger("reloadGrid");
}

function saveCustomer(){
	$.getJSON('${saveCustomerPopupURL}', {
		customerID: '',
		customerTypeID: $("#customerType").val(), 
		name: $("#custName").val(),
		//surname: $("#custSurname").val(),
		address: $("#address").val(),
		subdistrictID: $("#lovForm_subdistrict").val(),
		districtID: $("#lovForm_district").val(),
		provinceID: $("#lovForm_province").val(),
		tel: $("#cTel").val(),
		mobileTel: $("#cMobileTel").val(),
		email: $("#email").val()
	}, function(data) {
		if(data.success == true){
			//alert('Add complete');
			jQuery("#dialog").text(data.message.toString());
			jQuery("#dialog").dialog( 
				{
					title: 'Success',
					modal: true,
					buttons: {"Ok": function()  {
						jQuery(this).dialog("close");
						jQuery("#add-form").dialog("close");
						gridReload();
						}
				    }
			});
		}else{
			jQuery("#dialog").text(data.message.toString());
			jQuery("#dialog").dialog( 
				{
					title: 'Fail',
					modal: true,
					buttons: {"Ok": function()  {
						jQuery(this).dialog("close");} 
				    }
			});
		}
	});
	
	/*$.ajax({
	    type: "GET",
	    url: "${saveCustomerPopupURL}",
	    contentType: "application/json; charset=tis620", // set charset don't work
	    dataType: "json",
	    data: ({customerID: '', name: $('#custName').val(), surname: $('#custSurname').val(), address: $('#address').val(), subdistrictID: $('#subdistrict').val(), districtID: $('#district').val(), provinceID: $('#province').val()}),
	    success: function(json) {
	    	alert('success');
	    	alert(json);
//	        $("#success").html("json.length=" + json.length);
//	        itemAddCallback(json);
	    },
	    error: function (xhr, textStatus, errorThrown) {
//	        $("#error").html(xhr.responseText);
	    }
	});*/
	
	
	//return false;
}

function doPrint(){
	document.forms["printForm"].submit();
}

$('#shopCustomerName').bind('keypress', function(event){
	if(event.keyCode == '13') {gridShopCustomerReload(); return false;}
	else return true;
});

function showShopCustomerDetail(){
	$("#shopCustomerDetail").show();
	$("#walkinCustomerDetail").hide();	
}

function showWalkinCustomerDetail(){
	$("#shopCustomerDetail").hide();
	$("#walkinCustomerDetail").show();
}


jQuery.validator.addMethod("require_from_group", function(value, element, options) {
	var numberRequired = options[0];
	var selector = options[1];
	//Look for our selector within the parent form
	var validOrNot = $(selector, element.form).filter(function() {
		// Each field is kept if it has a value
		return $(this).val();
		// Set to true if there are enough, else to false
	}).length >= numberRequired;

	//The elegent part - this element needs to check the others that match the
	//selector, but we don't want to set off a feedback loop where all the
	//elements check all the others which check all the others which
	//check all the others...
	//So instead we
	//  1) Flag all matching elements as 'currently being validated'
	//  using jQuery's .data()
	//  2) Re-run validation on each of them. Since the others are now
	//     flagged as being in the process, they will skip this section,
	//     and therefore won't turn around and validate everything else
	//  3) Once that's done, we remove the 'currently being validated' flag
	//     from all the elements
	/*if(!$(element).data('being_validated')) {
		var fields = $(selector, element.form);
		fields.data('being_validated', true);
		//.valid() means "validate using all applicable rules" (which 
		//includes this one)
		fields.valid();
		fields.data('being_validated', false);
	}*/
	return validOrNot;
	// {0} below is the 0th item in the options field
}, jQuery.format("<div style='padding-left:10px'>Please fill out at least {0} of these fields.</div>"));

</script>