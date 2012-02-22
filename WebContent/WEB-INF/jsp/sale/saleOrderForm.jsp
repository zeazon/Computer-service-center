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
			<form:form commandName="form" id="form" class="jqtransform" action="saleOrder.html?do=save">
				<input type="hidden" name="mode" value="${mode}"/>
				<form:hidden path="saleOrderID"/>
				<table width="100%">
					<col width="13%">
					<col width="18%">
					<col width="6%">
					<col width="14%">
					<col width="11%">
					<col width="13%">
					<col width="13%">
					<col width="12%">
					<tr>
						<td><label><fmt:message key="saleMan"/></label></td>
						<td colspan="7">
							<div class="rowElem">
								<form:select path="employeeID" id="employee">
									<%--form:options items="${employeeList}" itemValue="employeeID" itemLabel="name"/--%>
									<c:forEach items="${employeeList}" var="employee">
										<form:option value="${employee.employeeID}">${employee.name} ${employee.surname}</form:option>
									</c:forEach>
								</form:select>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="date"/></label></td>
						<td colspan="7"><div class="rowElem"><form:input path="saleDate" id="saleDate" class="textboxMockup" size="8" readonly="true"/></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="customerID" />:<font color="red">*</font></label></td>					
						<td align="left" colspan="7">
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
						<td><label><fmt:message key="productID" />:</label></td>
						<td colspan="7">
							<div class="rowElem">
								<form:input path="productID" class="textboxMockup" style="float:left" id="productID" readonly="true" size="18" maxlength="20"/> <input type="button" id="productLov" value="..." >
								<%--c:if test="${mode == 'add'}">
									<form:input path="productID" class="textboxMockup" style="float:left" id="productID" size="18" maxlength="20"/> <!-- input type="button" id="productLov" value="..." > <label class="error" for="productID" generated="true" style="display: none; float:left; padding-left:10px"></label-->
								</c:if>
								<c:if test="${mode == 'edit'}">
									<form:input path="productID" class="textboxMockup" style="float:left" id="productID" readonly="true" size="18" maxlength="20"/>
								</c:if--%>
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
						<td><div class="rowElem"><span id="modelTxt">${product.model.name}&nbsp;</span></span></div></td>
						<td><label><fmt:message key="serialNo" />:</label></td>
						<td><div class="rowElem"><span id="serialNoTxt">${product.serialNo}&nbsp;</span></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="warrantyDate"/>:</label></td>
						<td colspan="7"><div class="rowElem"><form:input path="warrantyDate" id="warrantyDate" class="textboxMockup" readonly="true" size="10" /></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="warrantyExpire"/>:</label></td>
						<td colspan="7"><div class="rowElem"><form:input path="warrantyExpire" id="warrantyExpire" class="textboxMockup" readonly="true" size="10" /></div></td>
					</tr>
					<!-- tr>
						<td><label><fmt:message key="type" />:</label></td>
						<td colspan="7">
							<div class="rowElem">
								<form:select path="typeID" id="type">
									<form:options items="${typeList}" itemValue="typeID" itemLabel="name"/>
								</form:select>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="brand" />:</label></td>
						<td id="brandRow" colspan="7">
							<div class="rowElem">
								<form:select path="brandID" id="brand">
									<form:options items="${brandList}" itemValue="brandID" itemLabel="name"/>
								</form:select>
							</div>
							<label class="error" for="brand" generated="true" style="display: none; padding-left:10px;"></label>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="model" />:</label></td>
						<td id="modelRow" colspan="7">
							<div class="rowElem">
								<form:select path="modelID" id="model">
									<form:options items="${modelList}" itemValue="modelID" itemLabel="name"/>
								</form:select>
								&nbsp;<input type="button" value="+" id="showAddModel"/>
							</div>
							<label class="error" for="model" generated="true" style="display: none; padding-left:10px;"></label>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="serialNo" />:<font style="color:red">*</font></label></td>
						<td colspan="7"><div class="rowElem"><form:input path="serialNo" id="serialNo" class="textboxMockup" /> <label class="error" for="serialNo" generated="true" style="display: none; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="description" />:</label></td>
						<td colspan="7"><div class="rowElem"><form:input type="text" path="description" class="textboxMockup" /></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="warrantyDate"/>:</label></td>
						<td colspan="7"><div class="rowElem"><form:input path="warrantyDate" id="warrantyDate" class="textboxMockup" readonly="true" size="10" /></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="warrantyExpire"/>:</label></td>
						<td colspan="7"><div class="rowElem"><form:input path="warrantyExpire" id="warrantyExpire" class="textboxMockup" readonly="true" size="10" /></div></td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:7px;"><label><fmt:message key="remark"/>:</label></td>
						<td colspan="7"><div class="rowElem"><form:textarea path="remark" rows="5" col="30" class="textareaMockup" style="width:98%" name="remark" ></form:textarea></div></td>
					</tr-->
					<tr align="center">
						<td colspan="8"><div class="rowElem"><input type="submit" value="<fmt:message key='button.ok' />" /></div></td>
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
						<form:select id="subdistrict" path="subdistrictID" items="${subdistrictList}" itemValue="subdistrictID" itemLabel="name">
						</form:select>
						
						<div style="float:left; margin:5px 0 0 15px;"><fmt:message key="district" />:</div>
						<form:select id="district" path="districtID" items="${districtList}" itemValue="districtID" itemLabel="name">
						</form:select>
						
						<div style="float:left; margin:5px 0 0 15px;"><fmt:message key="province" />:</div>
						<form:select id="province" path="provinceID" items="${provinceList}" itemValue="provinceID" itemLabel="name" />
					
						<br><br>
						<div style="float:left; margin:5px 0 0 0px;"><fmt:message key="zipcode" />:</div>
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

<!-- Start model popup -->

<div id="add-model-form" title='<fmt:message key="addModel" />'>
	<form:form commandName="modelForm" id="modelForm" class="jqtransform" action="JavaScript:saveModel();">
		<table width="100%">
			<tr>
				<td width="20%"><label><fmt:message key="type" />:</label></td>
				<td>
					<div class="rowElem">
						<form:select path="typeID" id="lovModel_type">
							<form:options items="${typeList}" itemValue="typeID" itemLabel="name"/>
						</form:select>
					</div>
				</td>
			</tr>
			<tr>
				<td><label><fmt:message key="brand" />:</label></td>
				<td id="lovModel_brandRow">
					<div class="rowElem">
						<form:select path="brandID" id="lovModel_brand">
							<form:options items="${brandList}" itemValue="brandID" itemLabel="name"/>
						</form:select>
					</div>
				</td>
			</tr>
			<tr>
				<td><label><fmt:message key="name" />:<font style="color:red">*</font></label></td>
				<td><div class="rowElem"><form:input path="name" id="lovModel_name" cssClass="required" maxlength="100" size="50"/> <label class="error" for="name" generated="true" style="display: none; padding-left:10px"></label></div></td>
			</tr>
			<tr align="center">
				<td colspan="2"><div class="rowElem"><input type="submit" value='<fmt:message key="button.ok" />' /></div></td>
			</tr>
		</table>
	</form:form>
</div>

<!-- End model popup -->


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
							<td><label><fmt:message key="serialNo" />:</label></td>
							<td><div class="rowElem"><input type="text" name="serialNo" id="lovSerialNo" class="textboxMockup" size="30"/></div></td>
						</tr>
						<tr>
							<td></td>
							<td><div class="rowElem"><input type="button" id="searchProductButton" value='<fmt:message key="button.search" />' ></div></td>
						</tr>
					</table>
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
		<input type="hidden" id="lov_product_warrantyDate" name="warrantyDate"/>
		<input type="hidden" id="lov_product_warrantyExpire" name="warrantyExpire"/>
	</form>
</div>

<div id="add-product-form" title='<fmt:message key="addProduct" />'>
	<form:form commandName="productForm" id="productForm" class="jqtransform" action="JavaScript:saveProduct();">
		<table width="100%">
			<tr>
				<td width="20%"><label><fmt:message key="productID" />:<font style="color:red">*</font></label></td>
				<td>
					<div class="rowElem">
						<form:input path="productID" id="lovForm_productID" readonly="true" class="textboxMockup"  />
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
						&nbsp;<input type="button" value="+" id="showAddModel"/>
					</div>
					<label class="error" for="model" generated="true" style="display: none; padding-left:10px;"></label>
				</td>
			</tr>
			<tr>
				<td><label><fmt:message key="serialNo" />:<font style="color:red">*</font></label></td>
				<td><div class="rowElem"><form:input path="serialNo" id="lovForm_serialNo" class="textboxMockup" maxlength="100" size="30" /> <label class="error" for="name" generated="true" style="display: none; padding-left:10px"></label></div></td>
			</tr>
			<tr>
				<td><label><fmt:message key="description" />:</label></td>
				<td><div class="rowElem"><form:input path="description" id="lovForm_description" class="textboxMockup" /></div></td>
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
				<td><div class="rowElem"><form:textarea path="remark" id="lovForm_remark" rows="5" col="30" class="textareaMockup" style="width:98%" ></form:textarea></div></td>
			</tr>
			<tr align="center">
				<td colspan="2"><div class="rowElem"><input type="submit" value='<fmt:message key="button.ok" />' /></div></td>
			</tr>
		</table>
	</form:form>
</div>

<!-- End product lov -->

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
<c:url var="saveModelPopupURL" value="/model.html?do=savePopup" />
<c:url var="countSerialNoURL" value="/product.html?do=countSerialNo" />

<script type="text/javascript">
$.ajaxSetup({ cache: false });

//init walk in customer lov dialog
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

$(document).ready(function(){
	
	$("#saleDate").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
	
	$("#warrantyDate").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
	$("#warrantyExpire").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
	
	$("#warrantyDate_t").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
	$("#warrantyExpire_t").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
	
	$("#form").validate({
		rules: {
			customerID: "required",
			serialNo: {required:true, checkDupSerialNo:true},
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
			serialNo: {required:true, checkDupSerialNo:true},
			brandID: "required",
			modelID: "required",
			remark:{
				maxlength: 1000
			}
		}
	});
	
	$("#modelForm").validate({
		rules: {
			name: "required"
		}
	});
	
	$("#button").click(function(){
		$("form").valid();
	});
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
	$("form.lov").jqTransform();
	$("form.customerForm").jqTransform();
	$("form.productForm").jqTransform();
	
	// Add event click to lov button
	$("#lov").click( function(e) {
		tDialog.dialog("open");
	});
	
	// Add event click to product lov button
	$("#productLov").click( function(e){
		productDialog.dialog("open");
	});
	
	// Add event click to add model button
	$("#showAddModel").click( function(e){
		$("#add-model-form").dialog("open");
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
	
	// add model
	
	$( "#add-model-form" ).dialog({
		autoOpen: false,
		height: 240,
		width: 500,
		modal: true
	});
	
	$( "#province_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#province");
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
					
					$('#district').html(html);
					
					$('#district_autoComplete').width($('#district').width());
					$('#district_autoComplete').val($("#district :selected").text());
					
					//$("#brandRow").css("z-index", 9);
					
					$("#district_autoComplete").trigger('change');
					getSubdistrictSelectList();
				});
			 }
		}
	});
	
	$( "#district_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#district");
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
					
					$('#subdistrict').html(html);
					
					$('#subdistrict_autoComplete').width($('#subdistrict').width());
					$('#subdistrict_autoComplete').val($("#subdistrict :selected").text());
					
					//$("#brandRow").css("z-index", 9);
					
					$("#subdistrict_autoComplete").trigger('change');
					getZipcode();
				});
			 }
		}
	});
	
	$( "#subdistrict_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#subdistrict");
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

					$( "#subdistrict_autoComplete" ).data( "autocomplete" ).term = "";					
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
		var select = $("#district");
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
			
			$('#subdistrict').html(html);
			
			$('#subdistrict_autoComplete').width($('#subdistrict').width());
			$('#subdistrict_autoComplete').val($("#subdistrict :selected").text());
			
			//$("#brandRow").css("z-index", 9);
			
			$("#subdistrict_autoComplete").trigger('change');
			getZipcode();
		});	 
	}
	
	function getZipcode(){	
		var select = $("#subdistrict");
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
					
					$("#brandRow").css("z-index", 9);
					
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
	
	
	
	
	$("#brand").change(
		function() {
			$.getJSON('${findModelURL}', {
				typeID : $("#type").val(),
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
					html += '<option value="">-</option>';
				}
				
				$('#model').html(html);
				
				// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
				
				var sty = $("#modelRow div.jqTransformSelectWrapper").attr("style");
				
				var sels = $("#model").removeClass("jqTransformHidden");
				var $par = $("#model");
				$par.parent().replaceWith($par);
				sels.jqTransSelect();
				//$("#brandRow div.jqTransformSelectWrapper").attr("style", sty);
				//$("#brandRow div.jqTransformSelectWrapper").attr("style", "z-index:9;");
				
				$("#modelRow div.jqTransformSelectWrapper").css("z-index", 7);
			});
		}
	);
	
	
// Change event of model form
	
	$( "#lovModel_type_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#lovModel_type");
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
					$( "#lovModel_type_autoComplete" ).data( "autocomplete" ).term = "";
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
					
					$('#lovModel_brand').html(html);
					
					$('#lovModel_brand_autoComplete').width($('#lovModel_brand').width());
					$('#lovModel_brand_autoComplete').val($("#lovModel_brand :selected").text());
					
					$("#lovModel_brandRow").css("z-index", 9);
					
					$("#lovModel_brand_autoComplete").trigger('change');
				});
			 }
		}
	});
	
	$( "#lovModel_brand_autoComplete" ).autocomplete({
		change: function(event, ui) {
			var select = $("#lovModel_brand");
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
			 }
		}
	});
	
	
	// lov search product
	
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
					$( "#lovType_autoComplete" ).data( "autocomplete" ).term = "";
					// get text from blank value option
					$( this ).val(select.children( ":selected" ).text());
					
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
					}else{
						html += '<option value=""></option>';
					}
					
					$('#lovBrand').html(html);
					
					$('#lovBrand_autoComplete').width($('#lovBrand').width());
					$('#lovBrand_autoComplete').val($("#lovBrand :selected").text());
					
					$("#brandRow").css("z-index", 9);
					
					$("#lovBrand_autoComplete").trigger('change');
					getModelLovSelectList();
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

					// get text from blank value option
					$( this ).val(select.children( ":selected" ).text());
					
					return false;
				}
			 }else{
				 getModelLovSelectList();
			 }
		}
	});
	
	/* $("#lovType").change(
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
	); */
	
	
	/* $("#lovBrand").change(
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
	); */
	
	
	$("#searchProductButton").click(function() {
		gridProductReload();
	});
	
	// product lov jqgrid
	jQuery("#productList").jqGrid({
		url:"searchProduct.html",
		datatype: "json",
		height: "100%",
		autowidth: true,
		colNames:['<fmt:message key="type" />','<fmt:message key="brand" />','<fmt:message key="model" />','<fmt:message key="productID" />','<fmt:message key="description" />','<fmt:message key="serialNo" />','<fmt:message key="warrantyDate" />','<fmt:message key="warrantyExpire" />'],
		colModel:[
		    {name:'typeName',index:'typeName'},
		    {name:'brandName',index:'brandName'},
			{name:'modelName',index:'modelName'},
			{name:'productID',index:'productID'},
			{name:'description',index:'description'},
			{name:'serialNo',index:'serialNo'},
			{name:'warrantyDate',index:'warrantyDate',hidden:true},
			{name:'warrantyExpire',index:'warrantyExpire',hidden:true}],
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
	}).navGrid("#productPager",{edit:false,add:false,del:false,search:false,refresh:false,cloneToTop:true})
	.navButtonAdd('#productList_toppager',
		{
			//caption:"<fmt:message key='button.add' />", 
			caption:"", 
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
						$("#lovForm_warrantyDate").val($("#warrantyDate").val());
						$("#lovForm_warrantyExpire").val($("#warrantyExpire").val());
					}
				});
				
				$( "#add-product-form" ).dialog( "open" );
				
			}, 
			position:"last"
	});
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
		var lov_warrantyDate_val = $("#lov_product_warrantyDate").val();
		var lov_warrantyExpire_val = $("#lov_product_warrantyExpire").val();
		
		$("#productID").val(lov_id_val);
		$("#typeTxt").html(lov_type_val);
		$("#brandTxt").html(lov_brand_val);
		$("#modelTxt").html(lov_model_val);
		$("#serialNoTxt").html(lov_serialNo_val);
		$("#warrantyDate").val(lov_warrantyDate_val);
		$("#warrantyExpire").val(lov_warrantyExpire_val);
		
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
	
	function gridProductReload(){
		var typeID = jQuery("#lovType").val();
		var brandID = jQuery("#lovBrand").val();
		var modelID = jQuery("#lovModel").val();
		var serialNo = jQuery("#lovSerialNo").val();
		jQuery("#productList").jqGrid('setGridParam',{url:"searchProduct.html?&typeID="+typeID+"&brandID="+brandID+"&modelID="+modelID+"&serialNo="+serialNo,page:1}).trigger("reloadGrid");
	}
	
	// check for enter key
	$('#name').bind('keypress', function(event){
		if(event.keyCode == '13') {gridReload(); return false;}
		else return true;
	});
	
	// check for enter key
	$('#serialNo').bind('keypress', function(event){
		if(event.keyCode == '13') {return false;}
		else return true;
	});
	
});

//function to update model select list in search product lov screen
function getModelLovSelectList(){
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
		}else{
			html += '<option value=""></option>';
		}
		
		$('#lovModel').html(html);
		
		$('#lovModel_autoComplete').width($('#lovModel').width());
		$('#lovModel_autoComplete').val($("#lovModel :selected").text());
		
		$("#modelRow").css("z-index", 8);
	});
}

//function to update model select list in add product screen
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

function gridReload(){
	var name = jQuery("#name").val();
	//var surname = jQuery("#surname").val();
	//jQuery("#list").jqGrid('setGridParam',{url:"searchCustomer.html?name="+name+"&surname="+surname,page:1}).trigger("reloadGrid");
	jQuery("#list").jqGrid('setGridParam',{url:"searchCustomer.html?name="+name,page:1}).trigger("reloadGrid");
}

function saveCustomer(){
	$.getJSON('${saveCustomerPopupURL}', {
		customerID: '',
		customerTypeID: $("#customerType").val(), 
		name: $("#custName").val(),
		//surname: $("#custSurname").val(),
		address: $("#lovForm_address").val(),
		subdistrictID: $("#subdistrict").val(),
		districtID: $("#district").val(),
		provinceID: $("#province").val(),
		tel: $("#cTel").val(),
		mobileTel: $("#cMobileTel").val(),
		email: $("#cEmail").val(),
		zipcode: $("#lovForm_zipcode").val()
	}, function(data) {
		if(data.success == true){
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
	
}

function setCustomerToForm(dataID){
	var customerID = dataID;
	var name = $("#custName").val();
	var address = $("#lovForm_address").val();
	var subdistrict = $('#subdistrict :selected').text();
	var district = $('#district :selected').text();
	var province = $('#province :selected').text();
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
	var warrantyDate = $("#lovForm_warrantyDate").val();
	var warrantyExpire = $("#lovForm_warrantyExpire").val();
	
	$("#productID").val(dataID);
	$("#typeTxt").html(type);
	$("#brandTxt").html(brand);
	$("#modelTxt").html(model);
	$("#warrantyDate").val(warrantyDate);
	$("#warrantyExpire").val(warrantyExpire);
}


function saveModel(){
	$.getJSON('${saveModelPopupURL}', {
		typeID: $("#lovModel_type").val(),
		brandID: $("#lovModel_brand").val(),
		name: $("#lovModel_name").val()
	}, function(data) {
		if(data.success == true){
			jQuery("#dialog").text(data.message.toString());
			jQuery("#dialog").dialog( 
				{
					title: 'Success',
					modal: true,
					buttons: {"Ok": function()  {
						// If add product screen choose same type and brand with added model, then update model list.
						if(($('#lovForm_type').val() == $('#lovModel_type').val()) && ($('#lovForm_brand').val() == $('#lovModel_brand').val())){
							// select model list in product form
							getModelSelectList();	
						}
						
						jQuery(this).dialog("close");
						jQuery("#add-model-form").dialog("close");						
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

jQuery.validator.addMethod("checkDupSerialNo", function(value, element, options) {
	var validOrNot = true;
	$.ajax({
		url: '${countSerialNoURL}', 
		data: {serialNo:value},
		async: false,
		success:
			function(msg){
				if(msg.success == false){
					validOrNot = false;
				}else{
					if(msg.data > 0){
						validOrNot = false;
					}else{
						validOrNot = true;
					}
				}
			}
	});
	return validOrNot;
},'<fmt:message key="error.duplicateSerialNo" />');

</script>