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
						<td width="13%"><label><fmt:message key="outsiteServiceID" />:</label></td>
						<td colspan="2"><div class="rowElem"><form:input path="outsiteServiceID" readonly="true" class="textboxMockup" /></div></td>
						<td><label><fmt:message key="date" />:</label></td>
						<td colspan="2"><div class="rowElem"><form:input path="outsiteServiceDate" readonly="true" class="textboxMockup" /></div></td>
					</tr>
					<tr>
						<td><div class="rowElem"><label><fmt:message key="outsiteServiceServiceType" />:</label></div></td>
						<td colspan="5"><div class="rowElem"><form:radiobutton path="serviceType" value="warranty" cssStyle="margin-top:4px;" /><label style="float:left; margin-top:4px"><fmt:message key="outsiteServiceServiceType_inWarranty" /></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><form:radiobutton  path="serviceType" value="repair" cssStyle="margin-top:4px;" /><label style="float:left; margin-top:4px;" ><fmt:message key="outsiteServiceServiceType_outWarranty" /></label></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="serviceOrderID" />:<font color="red">*</font></label></td>
						<td colspan="5"><div class="rowElem"><form:input path="serviceOrderID" class="required textboxMockup" style="float:left" id="serviceOrderID" readonly="readonly" size="10"/> <input type="button" id="lov" class="lov_button" value="..." > <label class="error" id="ui-state-error" for="serviceOrderID" generated="true" style="display: none; float:left; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td colspan="6">
							<fieldset style="background-color:#eeeeee; margin: 0 10px 0 10px; ">
								<legend class="ui-widget-header ui-corner-all">&nbsp;<fmt:message key="serviceOrder_data" />&nbsp;</legend>
								<table width="100%">
									<tr>
										<td width="13%"><label><fmt:message key="serviceOrderDate" />:</label></td>
										<td><div class="rowElem"><span id="serviceOrderDate">${form.serviceOrder.serviceOrderDate}&nbsp;</span></div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="serviceOrderType" />:</label></td>
										<td colspan="5">
											<div class="rowElem">
												<span id="serviceOrderType">
													<c:if test="${form.serviceOrder.serviceType == '1'}">
														<fmt:message key="serviceOrderType_repair" />
													</c:if>
													<c:if test="${form.serviceOrder.serviceType == '2'}">
														<fmt:message key="serviceOrderType_refix" />
													</c:if>
												</span>
											</div>
										</td>
									</tr>
									<tr align="left">
										<td colspan="6">
											<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="customerDetail" /></u></b></div>
										</td>
									</tr>
									<tr>
										<td><label><fmt:message key="customerID" />:</label></td>
										<td align="left"><div class="rowElem"><span id="customerID">${form.serviceOrder.customer.customerID}&nbsp;</span></div></td>
										<td><label><fmt:message key="company" />:</label></td>
										<td colspan="3" align="left"><div class="rowElem"><span id="company">${form.serviceOrder.customer.company}&nbsp;</span></div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="address" />:</label></td>
										<td colspan="5" align="left"><div class="rowElem"><span id="address">${fullAddr}&nbsp;</span></div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="contactName" />:</label></td>
										<td><div class="rowElem"><span id="contactName">${form.serviceOrder.customer.name} ${form.serviceOrder.customer.surname}</span></div></td>
										<td><label><fmt:message key="tel" />:</label></td>
										<td><div class="rowElem"><span id="tel">${form.serviceOrder.customer.tel}&nbsp;</span></div></td>
										<td><label><fmt:message key="mobileTel" />:</label></td>
										<td width="120px"><div class="rowElem"><span id="mobileTel">${form.serviceOrder.customer.mobileTel}</span></div></td>
									</tr>
									<tr align="left">
										<td colspan="6">
											<div class="rowElem"><br>&nbsp;&nbsp;&nbsp;<b><u><fmt:message key="productDetail" /></u></b></div>
										</td>
									</tr>
									<tr>
										<td><label><fmt:message key="type" />:</label></td>
										<td colspan="2">
											<div class="rowElem"><span id="type">${form.serviceOrder.type.name}&nbsp;</span></div>
										</td>
										<td><label><fmt:message key="brand" />:</label></td>
										<td colspan="2">
											<div class="rowElem"><span id="brand">${form.serviceOrder.brand.name}&nbsp;</span></div>
										</td>
									</tr>
									<tr>
										<td><label><fmt:message key="model" />:</label></td>
										<td colspan="2"><div class="rowElem"><span id="model">${form.serviceOrder.model}&nbsp;</span></div></td>
										<td><label><fmt:message key="serialNo" />:</label></td>
										<td colspan="2"><div class="rowElem"><span id="serialNo">${form.serviceOrder.serialNo}&nbsp;</span></div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="accessories" />:</label></td>
										<td colspan="2"><div class="rowElem"><span id="accessories">${form.serviceOrder.accessories}</span></div></td>
										<td><label><fmt:message key="serviceOrder_desc" />:</label></td>
										<td colspan="2"><div class="rowElem"><span id="serviceOrder_desc">${form.serviceOrder.description}&nbsp;</span></div></td>
									</tr>
									<tr>
										<%--td valign="top" style="padding-top:9px;"><label><fmt:message key="problem" />:</label></td--%>
										<%--td colspan="5" align="left"><div class="rowElem"><textarea rows="5" col="30" readonly="readonly" class="ignore textareaMockup" style="width:98%" name="problem" ></textarea></div></td--%>
										<td valign="top"><label><fmt:message key="problem" />:</label></td>
										<td colspan="5" align="left" valign="top"><div class="rowElem"><pre id="problem" class="display" >${form.serviceOrder.problem}&nbsp;</pre></div></td>
									</tr>
									<tr>
										<td><label><fmt:message key="serviceOrder_empCreate" />:</label></td>
										<td colspan="5"><div class="rowElem"><span id="empOpen">${form.serviceOrder.empOpen.name}&nbsp;${form.serviceOrder.empOpen.surname}</span></div></td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
					<tr>
						<td style="width:13%"><div class="rowElem"><label><fmt:message key="outsiteService_accessories" />:</label></div></td>
						<td colspan="5"><div class="rowElem"><form:input path="accessories" class="textboxMockup" style="width:99%"/></div></td>
					</tr>
					<tr>
						<td><div class="rowElem"><label><fmt:message key="outsiteService_outsiteCompany" />:</label></div></td>
						<td colspan="2">
							<div class="rowElem">
								<form:select path="outsiteCompanyID" id="outsiteCompany" items="${outsiteCompanyList}" itemValue="outsiteCompanyID" itemLabel="name">
								</form:select>
							</div>
						</td>
						<td><div class="rowElem"><label><fmt:message key="transportCompany" />:</label></div></td>
						<td colspan="2">
							<div class="rowElem">
								<form:select path="transportCompanyID" id="transportCompany" items="${transportCompanyList}" itemValue="transportCompanyID" itemLabel="name">
								</form:select>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center"><div class="rowElem"><input type="submit" value="<fmt:message key='button.ok' />" /></div></td>
					</tr>
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
						<tr>
							<td><label><fmt:message key="surname" />:</label></td>
							<td><div class="rowElem"><input type="text" id="surname" class="textboxMockup"></div></td>
						</tr>
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
		<input type="hidden" id="lov_customerID" name="customer.customerID" />
		<input type="hidden" id="lov_name" name="name" />
		<input type="hidden" id="lov_surname" name="surname" />
		<input type="hidden" id="lov_company" name="company" />
		<input type="hidden" id="lov_address" name="customerFullAddress" />
		<input type="hidden" id="lov_tel" name="tel" />
		<input type="hidden" id="lov_mobileTel" name="mobileTel" />
		<input type="hidden" id="lov_type" name="type" />
		<input type="hidden" id="lov_brand" name="brand" />
		<input type="hidden" id="lov_model" name="model" />
		<input type="hidden" id="lov_serialNo" name="serialNo" />
		<input type="hidden" id="lov_accessories" name="accessories" />
		<input type="hidden" id="lov_description" name="description" />
		<input type="hidden" id="lov_problem" name="problem" />
		<input type="hidden" id="lov_empOpen" name="empOpen" />
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


<script type="text/javascript">

$(document).ready(function(){
	
	<c:if test="${action == 'print'}">
		document.forms["printJasperForm"].submit();
	</c:if>
	
	$("#form").validate({
		rules: {
			serviceOrderID: "required"
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
		colNames:['<fmt:message key="serviceOrderID" />','<fmt:message key="serviceOrderDate" />', 'serviceType', 'customerID','<fmt:message key="name" />','<fmt:message key="surname" />','<fmt:message key="company" />','<fmt:message key="address" />','<fmt:message key="tel" />','<fmt:message key="mobileTel" />','type','brand','model','serialNo','accessories','description','problem','empOpen'],
		colModel:[
			{name:'serviceOrderID',index:'serviceOrderID', width:'180'},
			{name:'serviceOrderDate', index:'serviceOrderDate', align:'center', sorttype:'date',formatter:'date', formatoptions: {srcformat:'d/m/Y H:i',newformat:'d/m/Y H:i'}, width:'140', firstSortOrder:'desc'},
			{name:'serviceType',index:'serviceType', hidden:true},
			{name:'customer.customerID',index:'customerID', hidden:true},
			{name:'name',index:'name', editable:true},
			{name:'surname',index:'surname', editable:true},
			{name:'company',index:'company', hidden:true},
			{name:'customerFullAddress',index:'address', hidden:true},
			{name:'tel',index:'tel', hidden:true},
			{name:'mobileTel',index:'mobileTel', hidden:true},
			{name:'type',index:'type', hidden:true},
			{name:'brand',index:'brand', hidden:true},
			{name:'model',index:'model', hidden:true},
			{name:'serialNo',index:'serialNo', hidden:true},
			{name:'accessories',index:'accessories', hidden:true},
			{name:'description',index:'description', hidden:true},
			{name:'problem',index:'problem', hidden:true},
			{name:'empOpen',index:'empOpen', hidden:true}],
		multiselect: false,
		//caption: "Manipulating Array Data",
		rownumbers: true,
		rowNum:10,
		rowList:[10,20,30],
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
		var lov_surname_val = $("#lov_surname").val();
		var lov_tel_val = $("#lov_tel").val();
		$("#serviceOrderID").val(lov_id_val);
		$("#serviceOrderDate").html($("#lov_serviceOrderDate").val());
		$("#serviceOrderType").html($("#lov_serviceType").val());
		$("#customerID").html($("#lov_customerID").val());
		$("#contactName").html(lov_name_val+" "+lov_surname_val);
		//$("#contactName").val(lov_name_val+" "+lov_surname_val);
		$("#company").html($("#lov_company").val());
		$("#address").html($("#lov_address").val());
		$("#tel").html(lov_tel_val);
		$("#mobileTel").html($("#lov_mobileTel").val());
		$("#type").html($("#lov_type").val());
		$("#brand").html($("#lov_brand").val());
		$("#model").html($("#lov_model").val());
		$("#serialNo").html($("#lov_serialNo").val());
		$("#accessories").html($("#lov_accessories").val());
		$("#description").html($("#lov_description").val());
		$("#problem").html($("#lov_problem").val());
		$("#empOpen").html($("#lov_empOpen").val());
		
		tDialog.dialog( "destroy" );
		// init lov for call again
		initLov();
	}
	
	$("#searchServiceOrderButton").click(function() {
		gridReload();
	});
	
	
});

function gridReload(){
	var name = jQuery("#name").val();
	var surname = jQuery("#surname").val();
	jQuery("#list").jqGrid('setGridParam',{url:"searchServiceOrder.html?name="+name+"&surname="+surname,page:1}).trigger("reloadGrid");
}





function doPrint(){
	document.forms["printForm"].submit();
}


</script>