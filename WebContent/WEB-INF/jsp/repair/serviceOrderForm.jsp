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
						<td align="left" colspan="3"><div class="rowElem"><form:input path="serviceOrderDate" class="textboxMockup" /></div></td>
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
									<form:radiobutton path="serviceType" value="4" cssStyle="margin-top:4px" id="serviceType_outsiteService" onclick="checkServiceType()" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrderType_outsiteService" /></label><form:input path="refJobID" class="textboxMockup" id="refJobID" maxlength="30" size="16" />
									<form:radiobutton path="serviceType" value="5" cssStyle="margin-top:4px" id="serviceType_refix" onclick="checkServiceType()" /><label style="float:left; margin-top:4px;"><fmt:message key="serviceOrderType_refix" /></label><form:input path="refServiceOrder" class="textboxMockup" id="refServiceOrder" maxlength="20" size="16" />
								</c:if>
								<c:if test="${mode=='edit'}">
									<form:radiobutton path="serviceType" value="1" cssStyle="margin-top:4px" id="serviceType_guarantee" disabled="true" /><label style="float:left; margin-top:4px"><fmt:message key="serviceOrderType_guarantee" /></label><form:select path="guaranteeNo" id="guaranteeNo" disabled="true"><c:forEach var="i" begin="1" end="7" step="1"><form:option value="${i}" /></c:forEach></form:select>
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
					<tr>
						<td colspan="6">
							<table width="100%" cellpadding="0" cellspacing="0">
								<col width="13%">
								<col width="14%">
								<col width="6%">
								<col width="10%">
								<col width="11%">
								<col width="16%">
								<col width="14%">
								<col width="16%">
								<tr align="left">
								<tr>
									<td colspan="8">
										<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="productDetail" /></u></b></div>
									</td>
								</tr>
								<tr>
									<td><label><fmt:message key="productID" />:<font color="red">*</font></label></td>
									<td colspan="7">
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
									<td><div class="rowElem"><%--form:input path="model" class="textboxMockup" maxlength="100"/--%><span id="modelTxt">${product.model.name}&nbsp;</span></span></div></td>
									<td><label><fmt:message key="serialNo" />:</label></td>
									<td><div class="rowElem"><%-->form:input path="serialNo" class="textboxMockup" maxlength="100"/--%><span id="serialNoTxt">${product.serialNo}&nbsp;</span></div></td>
								</tr>
								<tr>
									<td><label><fmt:message key="accessories" />:</label></td>
									<td colspan="2"><div class="rowElem"><form:input path="accessories" class="textboxMockup" style="width:98%" maxlength="5000"/></div></td>
									<td><label><fmt:message key="serviceOrder_desc" />:</label></td>
									<td colspan="4"><div class="rowElem"><form:input path="desc" class="textboxMockup" style="width:98%" maxlength="255"/></div></td>
								</tr>		
							</table>
						</td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:7px;"><label><fmt:message key="serviceOrder_problem" />:<font color="red">*</font></label></td>
						<td colspan="5" align="left"><div class="rowElem"><form:textarea path="problem" rows="5" col="30" class="textareaMockup" style="width:98%" name="problem" ></form:textarea><label class="error" for="problem" generated="true" style="display: none; float:left; padding-left:10px"></label></div></td>
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
							<td><div class="rowElem"><input type="text" id="lovCustomer_name" class="textboxMockup"></div></td>
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
				<td width="145px"><label><fmt:message key="customerID" />:</label></td>
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
						<form:input path="address" id="lovForm_address" class="textboxMockup" style="width:640px" maxlength="255"/><br><br>
						
						<div style="float:left; margin-top:5px;"><fmt:message key="subdistrict" />:</div>
						<form:select id="lovForm_subdistrict" path="subdistrictID" items="${subdistrictList}" itemValue="subdistrictID" itemLabel="name">
						</form:select>
						
						<div style="float:left; margin:5px 0 0 15px;"><fmt:message key="district" />:</div>
						<form:select id="lovForm_district" path="districtID" items="${districtList}" itemValue="districtID" itemLabel="name">
						</form:select>
						
						<div style="float:left; margin:5px 0 0 15px;"><fmt:message key="province" />:</div>
						<form:select id="lovForm_province" path="provinceID" items="${provinceList}" itemValue="provinceID" itemLabel="name" />
						
						<br><br>
						<div style="float:left; margin:5px 0 0 0px;"><fmt:message key="zipcode" />:</div>
						<%--div id="lovForm_zipcode" style="float:left; padding:5px; 0 0 5px">
							<c:choose>
								<c:when test="${zipcode == '0'}">-</c:when>
								<c:otherwise>${zipcode}</c:otherwise>
							</c:choose>
						</div--%>
						<form:input path="zipcode" id="lovForm_zipcode" class="textboxMockup" size="5" maxlength="5"/>
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
				<td><div class="rowElem"><input type="text" name="email" id="cEmail" class="textboxMockup" maxlength="50" /></div></td>
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
									<select id="lovType" class="selectSearch">
										<option value="">All</option>
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
									<select id="lovBrand" class="selectSearch">
										<option value="">All</option>
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
									<select id="lovModel" class="selectSearch">
										<option value="">All</option>
											<c:forEach var="model" items="${modelList}">
												<c:if test="${model.modelID != null}">
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

<div id="add-product-form" title='<fmt:message key="addProduct" />'>
	<form:form commandName="productForm" id="productForm" class="jqtransform" action="JavaScript:saveProduct();">
		<table width="100%">
			<tr>
				<td width="20%"><label><fmt:message key="productID" />:</label></td>
				<td>
					<div class="rowElem">
						<form:input path="productID" id="lovForm_productID" class="textboxMockup" readonly="true" maxlength="20" />
					</div>
				</td>
			</tr>
			<tr>
				<td><label><fmt:message key="type" />:</label></td>
				<td>
					<div class="rowElem">
						<form:select path="typeID" id="lovForm_type">
							<form:options items="${typeList}" itemValue="typeID" itemLabel="name"/>
						</form:select>
					</div>
				</td>
			</tr>
			<tr>
				<td><label><fmt:message key="brand" />:</label></td>
				<td id="brandRow">
					<div class="rowElem">
						<form:select path="brandID" id="lovForm_brand">
							<form:options items="${brandList}" itemValue="brandID" itemLabel="name"/>
						</form:select>
					</div>
					<label class="error" for="brand" generated="true" style="display: none; padding-left:10px;"></label>
				</td>
			</tr>
			<tr>
				<td><label><fmt:message key="model" />:</label></td>
				<td id="modelRow">
					<div class="rowElem">
						<form:select path="modelID" id="lovForm_model">
							<form:options items="${modelList}" itemValue="modelID" itemLabel="name"/>
						</form:select>
					</div>
					<label class="error" for="model" generated="true" style="display: none; padding-left:10px;"></label>
				</td>
			</tr>
			<tr>
				<td><label><fmt:message key="serialNo" />:<font style="color:red">*</font></label></td>
				<td><div class="rowElem"><form:input path="serialNo" id="lovForm_serialNo" class="textboxMockup" maxlength="100"/> <label class="error" for="serialNo" generated="true" style="display: none; padding-left:10px"></label></div></td>
			</tr>
			<tr>
				<td><label><fmt:message key="description" />:</label></td>
				<td><div class="rowElem"><form:input path="description" id="lovForm_description" class="textboxMockup" maxlength="255" /></div></td>
			</tr>
			<tr>
				<td><label><fmt:message key="warrantyDate"/>:</label></td>
				<td><div class="rowElem"><form:input path="warrantyDate" id="lovForm_warrantyDate" class="textboxMockup" readonly="true" size="10" /></div></td>
			</tr>
			<tr>
				<td><label><fmt:message key="warrantyExpire"/>:</label></td>
				<td><div class="rowElem"><form:input path="warrantyExpire" id="lovForm_warrantyExpire" class="textboxMockup" readonly="true" size="10" /></div></td>
			</tr>
			<tr>
				<td valign="top" style="padding-top:7px;"><label><fmt:message key="remark"/>:</label></td>
				<td><div class="rowElem"><form:textarea path="remark" id="lovForm_remark" rows="5" col="30" class="textareaMockup" style="width:98%" name="remark" ></form:textarea></div></td>
			</tr>
			<tr align="center">
				<td colspan="2"><div class="rowElem"><input type="submit" value='<fmt:message key="button.ok" />' /></div></td>
			</tr>
		</table>
	</form:form>
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
	<form:hidden path="warrantyDate" />
	<form:hidden path="warrantyExpire" />
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
<c:url var="findZipcodeBySubdistrictURL" value="/findZipcode.html" />
<c:url var="saveCustomerPopupURL" value="/customer.html?do=savePopup" />
<c:url var="saveProductPopupURL" value="/product.html?do=savePopup" />
<c:url var="findBrandURL" value="/brand.html?do=getBrandByType" />
<c:url var="findModelURL" value="/model.html?do=getModel" />
<c:url var="getCustomerByProductURL" value="/saleOrder.html?do=getCustomerByProduct" />

<script type="text/javascript">

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




$(document).ready(function(){
	
	$("#guaranteeNo_autoComplete").css('width','20px');
	
	//$("#appointmentDate").calendarsPicker($.extend({calendar: $.calendars.instance('thai','th')}));
	$('#appointmentDate').datetimeEntry({datetimeFormat: 'D/O/Y H:M'});
	$('#serviceOrderDate').datetimeEntry({datetimeFormat: 'D/O/Y H:M'});
	
	$("#shopCustomerDetail").hide();
	$("#walkinCustomerDetail").hide();
	
	$("#lovForm_warrantyDate").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
	$("#lovForm_warrantyExpire").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
	
	<c:if test="${action == 'print'}">
		document.forms["printJasperForm"].submit();
	</c:if>
	
	$("#form").validate({
		rules: {
			customerID: "required",
			productID: "required",
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
			mobileTel: {require_from_group: [1,".telephone"]},
			zipcode: {checkZipcode:true}
		}
	});
	
	$("#productForm").validate({
		rules: {
			serialNo: "required",
			brandID: "required",
			modelID: "required",
			remark:{
				maxlength: 1000
			}
		}
	});
	
	$("#button").click(function(){
		$("form").valid();
	});
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
	$("form.lov").jqTransform();
	$("form.customerForm").jqTransform();
	
	// check type of customer for display detail
//	checkCustomerType();	

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
		rowList:[10,20,30,40,50],
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
			caption:"<fmt:message key='button.add' />", 
			title:"<fmt:message key='button.add' />",
			//buttonimg:"row.gif",
			buttonicon:"ui-icon-plus", 
			onClickButton: function(){ 
				// Call example popup form*/
				//$( "#dialog-form" ).dialog( "open" );
				
				$( "#add-form" ).dialog( "open" );
				$("#custName").focus();
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
		height: 450,
		width: 950,
		modal: true
	});
	
	// add product
	
	$( "#add-product-form" ).dialog({
		autoOpen: false,
		height: 540,
		width: 900,
		modal: true
	});
	
	
	$( "#lovForm_province_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#lovForm_province");
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
			 		// Call original valud if it didn't match anything
					
					//$( "#lovForm_province_autoComplete" ).data( "autocomplete" ).term = "";
					$( this ).val(select.children( ":selected" ).text());
					return false;
				}
			 }else{
				 $.getJSON('${findDistrictByProvinceURL}', {
					provinceID : select.val(),
					ajax : 'true'
				}, function(data) {
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
					
					$('#lovForm_district_autoComplete').width($('#lovForm_district').width());
					$('#lovForm_district_autoComplete').val($("#lovForm_district :selected").text());
					
					//$("#brandRow").css("z-index", 9);
					
					$("#lovForm_district_autoComplete").trigger('change');
					getSubdistrictSelectList();
				});
			 }
		}
	});
	
	$( "#lovForm_district_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#lovForm_district");
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
			 		// Call original valud if it didn't match anything
					
					//$( "#lovForm_district_autoComplete" ).data( "autocomplete" ).term = "";
					$( this ).val(select.children( ":selected" ).text());
					return false;
				}
			 }else{
				$.getJSON('${findSubdistrictByDistrictURL}', {
					districtID : select.val(),
					ajax : 'true'
				}, function(data) {
					var html = '';
					var len = data.length;
					if(len > 0){
						for ( var i = 0; i < len; i++) {
							html += '<option value="' + data[i].subdistrictID + '">'
									+ data[i].name + '</option>';
						}
						html += '</option>';
					}else{
						html += '<option value=""></option>';
					}
					
					$('#lovForm_subdistrict').html(html);
					
					$('#lovForm_subdistrict_autoComplete').width($('#lovForm_subdistrict').width());
					$('#lovForm_subdistrict_autoComplete').val($("#lovForm_subdistrict :selected").text());
					
					//$("#brandRow").css("z-index", 9);
					
					$("#lovForm_subdistrict_autoComplete").trigger('change');
					getZipcode();
				});
			 }
		}
	});
	
	$( "#lovForm_subdistrict_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#lovForm_subdistrict");
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
			 		// Call original valud if it didn't match anything

					$( "#lovForm_subdistrict_autoComplete" ).data( "autocomplete" ).term = "";					
					$( this ).val(select.children( ":selected" ).text());
					return false;
				}
			 }else{
				if(select.val() != null){
					$.getJSON('${findZipcodeBySubdistrictURL}', {
						subdistrictID : select.val(),
						ajax : 'true'
					}, function(data) {
						if(data == '0'){
							$("#lovForm_zipcode").val("-");
						}else{
							$("#lovForm_zipcode").val(data);
						}
					});
				}else{
					$("#lovForm_zipcode").val("-");
				}
			 }
		}
	});
	
	function getSubdistrictSelectList(){
		var select = $("#lovForm_district");
		$.getJSON('${findSubdistrictByDistrictURL}', {
			districtID : select.val(),
			ajax : 'true'
		}, function(data) {
			var html = '';
			var len = data.length;
			if(len > 0){
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].subdistrictID + '">'
							+ data[i].name + '</option>';
				}
				html += '</option>';
			}else{
				html += '<option value=""></option>';
			}
			
			$('#lovForm_subdistrict').html(html);
			
			$('#lovForm_subdistrict_autoComplete').width($('#lovForm_subdistrict').width());
			$('#lovForm_subdistrict_autoComplete').val($("#lovForm_subdistrict :selected").text());
			
			//$("#brandRow").css("z-index", 9);
			
			$("#lovForm_subdistrict_autoComplete").trigger('change');
			getZipcode();
		});	 
	}
	
	function getZipcode(){	
		var select = $("#lovForm_subdistrict");
		if(select.val() != null){
			$.getJSON('${findZipcodeBySubdistrictURL}', {
				subdistrictID : select.val(),
				ajax : 'true'
			}, function(data) {
				if(data == '0'){
					$("#lovForm_zipcode").val("-");
				}else{
					$("#lovForm_zipcode").val(data);
				}
			});
		}else{
			$("#lovForm_zipcode").val("-");
		}
	}
	
	
	//$("#province").change(
	/*$("#lovForm_province").change(
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
	);*/
	
	/*$("#lovForm_district").change(
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
				
				// trigger event change of subdistrict select list
				$("#lovForm_subdistrict").change();
			});
		}
	);

	$("#lovForm_subdistrict").change(
		function(){
			if($(this).val() != null){
				$.getJSON('${findZipcodeBySubdistrictURL}', {
					subdistrictID : $(this).val(),
					ajax : 'true'
				}, function(data) {
					if(data == '0'){
						$("#lovForm_zipcode").val("-");
					}else{
						$("#lovForm_zipcode").val(data);
					}
				});
			}else{
				$("#lovForm_zipcode").val("-");
			}
		}
	);*/
	
	
	$( "#lovForm_type_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#lovForm_type");
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
					$( "#lovForm_type_autoComplete" ).data( "autocomplete" ).term = "";
					// get text from blank value option
					$( this ).val(select.children( ":selected" ).text());
					
					//$("#brand_autoComplete").trigger('change');
					//getModelSelectList();
					return false;
				}
			 }else{
				 $.getJSON('${findBrandURL}', {
					typeID : select.val()
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
					
					$('#lovForm_brand').html(html);
					
					$('#lovForm_brand_autoComplete').width($('#lovForm_brand').width());
					$('#lovForm_brand_autoComplete').val($("#lovForm_brand :selected").text());
					
					$("#lovForm_brandRow").css("z-index", 9);
					
					$("#lovForm_brand_autoComplete").trigger('change');
					getModelSelectList();
				});
			 }
		}
	});
	
	$( "#lovForm_brand_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#lovForm_brand");
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
				//	$( "#lovForm_brand_autoComplete" ).data( "autocomplete" ).term = "";
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
	
	$( "#lovForm_model_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#lovForm_model");
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
				//	$( "#lovForm_model_autoComplete" ).data( "autocomplete" ).term = "";
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
		var select = $("#lovForm_brand");
		$.getJSON('${findModelURL}', {
			typeID : $("#lovForm_type").val(),
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
			
			$('#lovForm_model').html(html);
			
			$('#lovForm_model_autoComplete').width($('#lovForm_model').width());
			$('#lovForm_model_autoComplete').val($("#lovForm_model :selected").text());
			
			$("#modelRow").css("z-index", 8);
		});
	}
	
	
	
	/*$("#lovForm_type").change(
		function(){
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
				$('#lovForm_brand').html(html);
				
				// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
				// bug because ref to color id, it should be td id
				//var sty = $("#color div.jqTransformSelectWrapper").attr("style");
				
				var sels = $("#lovForm_brand").removeClass("jqTransformHidden");
				var $par = $("#lovForm_brand");
				$par.parent().replaceWith($par);
				sels.jqTransSelect();
				// bug because ref to color id, it should be td id
				//$("#color div.jqTransformSelectWrapper").attr("style", sty);
				
				// trigger event change of district select list
				$("#lovForm_brand").change();
				$("#brandRow div.jqTransformSelectWrapper").css("z-index", 9);
			});
		}
	);*/
	
	
	/*$("#lovForm_brand").change(
		function() {
			$.getJSON('${findModelURL}', {
				typeID : $("#lovForm_type").val(),
				brandID : $(this).val(),
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
				
				$('#lovForm_model').html(html);
				
				// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
				
				var sty = $("#modelRow div.jqTransformSelectWrapper").attr("style");
				
				var sels = $("#lovForm_model").removeClass("jqTransformHidden");
				var $par = $("#lovForm_model");
				$par.parent().replaceWith($par);
				sels.jqTransSelect();
				//$("#brandRow div.jqTransformSelectWrapper").attr("style", sty);
				//$("#brandRow div.jqTransformSelectWrapper").attr("style", "z-index:9;");
				
				$("#modelRow div.jqTransformSelectWrapper").css("z-index", 8);
			});
		}
	);*/
	
	
	$( "#lovType_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#lovType");
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
					$( "#lovType_autoComplete" ).data( "autocomplete" ).term = "";
					// get text from blank value option
					$( this ).val(select.children( ":selected" ).text());
					
					//$("#brand_autoComplete").trigger('change');
					//getModelSelectList();
					return false;
				}
			 }else{
				 $.getJSON('${findBrandURL}', {
					typeID : select.val()
				}, function(data) {
					var html = '';
					var len = data.length;
					html += '<option value="">All</option>';
					if(len > 0){
						for ( var i = 0; i < len; i++) {
							html += '<option value="' + data[i].brandID + '">'
									+ data[i].name + '</option>';
						}
						html += '</option>';
					}
					
					$('#lovBrand').html(html);
					
					$('#lovBrand_autoComplete').width($('#lovBrand').width());
					$('#lovBrand_autoComplete').val($("#lovBrand :selected").text());
					
					$("#lovBrandRow").css("z-index", 9);
					
					$("#lovBrand_autoComplete").trigger('change');
					getModelSelectList_lovSearch();
				});
			 }
		}
	});
	
	$( "#lovBrand_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#lovBrand");
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
				//	$( "#lovForm_brand_autoComplete" ).data( "autocomplete" ).term = "";
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
				 getModelSelectList_lovSearch();
			 }
		}
	});
	
	$( "#lovModel_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#lovModel");
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
				//	$( "#lovForm_model_autoComplete" ).data( "autocomplete" ).term = "";
					// get text from blank value option
					$( this ).val(select.children( ":selected" ).text());
					
					//$("#brand_autoComplete").trigger('change');
					getModelSelectList_lovSearch();
					return false;
				}
			 }
		}
	});
	
	function getModelSelectList_lovSearch(){
		var select = $("#lovBrand");
		$.getJSON('${findModelURL}', {
			typeID : $("#lovType").val(),
			brandID :select.val(),
			ajax : 'true'
		}, function(data) {
			var html = '';
			var len = data.length;
			html += '<option value="">All</option>';
			if(len > 0){
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].modelID + '">'
							+ data[i].name + '</option>';
				}
				html += '</option>';
			}
			
			$('#lovModel').html(html);
			
			$('#lovModel_autoComplete').width($('#lovModel').width());
			$('#lovModel_autoComplete').val($("#lovModel :selected").text());
			
			$("#modelRow").css("z-index", 8);
		});
	}
	
	/*$("#lovType").change(
		function(){
			$.getJSON('${findBrandURL}', {
				typeID: $(this).val()
			}, function(data){
				var html = '';
				var len = data.length;
				html += '<option value="">All</option>';
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
	);*/
	
	
	/*$("#lovBrand").change(
		function(){
			$.getJSON('${findModelURL}', {
				typeID : $("#lovType").val(),
				brandID : $(this).val(),
				ajax : 'true'
			}, function(data){
				var html = '';
				var len = data.length;
				html += '<option value="">All</option>';
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
	);*/
	
	
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
		rowList:[10,20,30,40,50],
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
	}).navGrid("#productPager",{edit:false,add:false,del:false,search:false,refresh:false,cloneToTop:true})//;
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
	.navButtonAdd('#productList_toppager',
		{
			caption:"<fmt:message key='button.add' />", 
			title:"<fmt:message key='button.add' />",
			//buttonimg:"row.gif",
			buttonicon:"ui-icon-plus", 
			onClickButton: function(){ 
				// Call example popup form*/
				//$( "#dialog-form" ).dialog( "open" );
				
				$("#add-product-form").dialog({
					open: function(event, ui){
						$("#lovForm_warrantyDate").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
						$("#lovForm_warrantyExpire").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
					}
				});
				
				$( "#add-product-form" ).dialog( "open" );
				$("#lovForm_serialNo").focus();
				
			}, 
			position:"last"
	});

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
		
		// get customer who buy this product from sale order
		$.getJSON('${getCustomerByProductURL}', {
			productID : id,
			ajax : 'true'
		}, function(data) {
			if(data != null){
				var fullAddress = "";
				if(data.address != ''){
					fullAddress += data.address+' '+'<fmt:message key="subdistrict" />'+' '+data.subdistrict.name+' '+'<fmt:message key="district" />'+' '+data.district.name+' '+'<fmt:message key="province" />'+' '+data.province.name+' '+data.subdistrict.zipcode;;
				}
				// set data to customer
				$("#custID").val(data.customerID);
				$("#contactName").html(data.name);
				$("#address").html(fullAddress);
				$("#tel").html(data.tel);
				$("#mobileTel").html(data.mobileTel);
				$("#email").html(data.email);
			}
		});
		
		
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
		rowList:[10,20,30,40,50],
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
	
	// check service type for disable radio button
	checkServiceType();
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
	var name = jQuery("#lovCustomer_name").val();
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
		address: $("#lovForm_address").val(),
		subdistrictID: $("#lovForm_subdistrict").val(),
		districtID: $("#lovForm_district").val(),
		provinceID: $("#lovForm_province").val(),
		tel: $("#cTel").val(),
		mobileTel: $("#cMobileTel").val(),
		email: $("#cEmail").val(),
		zipcode: $("#lovForm_zipcode").val()
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
						//gridReload();
						
						tDialog.dialog( "destroy" );
						// init lov for call again
						initLov();
						setCustomerToForm(data.data);
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

function setCustomerToForm(dataID){
	var customerID = dataID;
	var name = $("#custName").val();
	var address = $("#lovForm_address").val();
	var subdistrict = $('#lovForm_subdistrict :selected').text();
	var district = $('#lovForm_district :selected').text();
	var province = $('#lovForm_province :selected').text();
	var zipcode = $("#lovForm_zipcode").val();
	var tel = $("#cTel").val();
	var mobileTel = $("#cMobileTel").val();
	var email = $("#cEmail").val();
	
	$("#custID").val(customerID);
	$("#contactName").html(name);
	$("#address").html(address+' <fmt:message key="subdistrict_abbr"/> '+subdistrict+' <fmt:message key="district_abbr"/> '+district+' <fmt:message key="province_abbr"/> '+province+' '+zipcode);
	$("#tel").html(tel);
	$("#mobileTel").html(mobileTel);
	$("#email").html(email);
}

function saveProduct(){
	$.getJSON('${saveProductPopupURL}', {
		productID: $("#lovForm_productID").val(),
		typeID: $("#lovForm_type").val(),
		brandID: $("#lovForm_brand").val(),
		modelID: $("#lovForm_model").val(),
		serialNo:  $("#lovForm_serialNo").val(),
		description: $("#lovForm_description").val(),
		warrantyDate: $("#lovForm_warrantyDate").val(),
		warrantyExpire: $("#lovForm_warrantyExpire").val(),
		remark: $("#lovForm_remark").val()
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
						jQuery("#add-product-form").dialog("close");
						//gridProductReload();
						
						setProductToForm(data.data);
						$("#product-dialog-modal").dialog("close");
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
	
}

function setProductToForm(dataID){
	// set value to form
	var type = $("#lovForm_type :selected").text();
	var brand = $("#lovForm_brand :selected").text();
	var model = $("#lovForm_model :selected").text();
	var serialNo = $("#lovForm_serialNo").val();
	
	$("#productID").val(dataID);
	$("#typeTxt").html(type);
	$("#brandTxt").html(brand);
	$("#modelTxt").html(model);
	$("#serialNoTxt").html(serialNo);
}

function doPrint(){
	document.forms["printForm"].submit();
}

// check for enter key
$('#lovCustomer_name').bind('keypress', function(event){
	if(event.keyCode == '13') {gridReload(); return false;}
	else return true;
});

//check for enter key
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

jQuery.validator.addMethod("checkZipcode", function(value, element, param) {
	return value.match(new RegExp("^[0-9\-]+$"));
},"<fmt:message key='error.checkZipcode' />");

</script>