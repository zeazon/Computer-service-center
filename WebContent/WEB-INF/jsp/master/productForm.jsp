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
			<form:form commandName="form" id="form" class="jqtransform" action="product.html?do=save">
				<input type="hidden" name="mode" value="${mode}" />
				<table width="100%">
					<tr>
						<td width="40%"><label><fmt:message key="productID" />:</label></td>
						<td>
							<div class="rowElem">
								<form:input path="productID" id="productID" class="textboxMockup" readonly="true" maxlength="20"/>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="type" />:</label></td>
						<td>
							<div class="rowElem">
								<form:select path="typeID" id="type">
									<form:option value="" />
									<form:options items="${typeList}" itemValue="typeID" itemLabel="name"/>
								</form:select>
								<label class="error" for="type" generated="true" style="display: none; padding-left:10px;"></label>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="brand" />:</label></td>
						<td id="brandRow">
							<div class="rowElem">
								<form:select path="brandID" id="brand">
									<form:option value="" />
									<form:options items="${brandList}" itemValue="brandID" itemLabel="name"/>
								</form:select>
								<label class="error" for="brand" generated="true" style="display: none; padding-left:10px;"></label>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="model" />:</label></td>
						<td id="modelRow">
							<div class="rowElem">
								<form:select path="modelID" id="model">
									<form:option value="" />
									<form:options items="${modelList}" itemValue="modelID" itemLabel="name"/>
								</form:select>
								&nbsp;<input type="button" value="+" id="showAddModel"/>
								<label class="error" for="model" generated="true" style="display: none; padding-left:10px;"></label>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="serialNo" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input path="serialNo" id="serialNo" class="textboxMockup" maxlength="100" size="30"/> <label class="error" for="serialNo" generated="true" style="display: none; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="description" />:</label></td>
						<td><div class="rowElem"><form:input path="description" class="textboxMockup" maxlength="255"/></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="installedBy" />:</label></td>
						<td>
							<div class="rowElem">
								<form:select path="installedBy" id="installedBy" cssClass="selectSearch">
									<form:option value="" label="-"/>
									<c:forEach items="${employeeList}" var="employee">
										<form:option value="${employee.employeeID}">${employee.name} ${employee.surname}</form:option>
									</c:forEach>
								</form:select>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="installedDate" />:</label></td>
						<td><div class="rowElem"><form:input path="installedDate" id="installedDate" readonly="true" size="10"/></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="warrantyDate"/>:</label></td>
						<td><div class="rowElem"><form:input path="warrantyDate" id="warrantyDate" class="textboxMockup" readonly="true" size="10" /></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="warrantyExpire"/>:</label></td>
						<td><div class="rowElem"><form:input path="warrantyExpire" id="warrantyExpire" class="textboxMockup" readonly="true" size="10" /></div></td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:7px;"><label><fmt:message key="remark"/>:</label></td>
						<td><div class="rowElem"><form:textarea path="remark" rows="5" col="30" class="textareaMockup" style="width:98%" name="remark" ></form:textarea></div></td>
					</tr>
					<tr align="center">
						<td colspan="2"><div class="rowElem"><input type="submit" value="<fmt:message key='button.ok' />" /></div></td>
					</tr>
				</table>
			</form:form>
		</td>
	</tr>
</table>

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

<div id="dialog" title="Feature not supported" style="display:none">
	<p>That feature is not supported.</p>
</div>

<c:url var="findBrandURL" value="/brand.html?do=getBrandByType" />
<c:url var="findModelURL" value="/model.html?do=getModel" />
<c:url var="saveModelPopupURL" value="/model.html?do=savePopup" />
<c:url var="countSerialNoURL" value="/product.html?do=countSerialNo" />
<c:url var="countSerialNoForEditURL" value="/product.html?do=countSerialNoForEdit" />

<script type="text/javascript">
$.ajaxSetup({ cache: false });

$("#warrantyDate").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
$("#warrantyExpire").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
$("#installedDate").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));

$(document).ready(function(){
	
	$("#form").validate({
		rules: {
			serialNo: {required:true},
			typeID: "required",
			brandID: "required",
			modelID: "required",
			remark:{
				maxlength: 1000
			}
		}
	});
	
	if('${mode}' == 'add'){
		$("#serialNo").rules("add", { checkDupSerialNo: true });
	}else if('${mode}' == 'edit'){
		$("#serialNo").rules("add", { checkDupSerialNoForEdit: true });
	}
	
	// add model
	$( "#add-model-form" ).dialog({
		autoOpen: false,
		height: 240,
		width: 500,
		modal: true
	});
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
	
	// Add event click to add model button
	$("#showAddModel").click( function(e){
		$("#add-model-form").dialog("open");
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
	
	
	// check for enter key
	$('#serialNo').bind('keypress', function(event){
		if(event.keyCode == '13') {return false;}
		else return true;
	});
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
						if(($('#type').val() == $('#lovModel_type').val()) && ($('#brand').val() == $('#lovModel_brand').val())){
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

jQuery.validator.addMethod("checkDupSerialNoForEdit", function(value, element, options) {
	var validOrNot = true;
	$.ajax({
		url: '${countSerialNoForEditURL}', 
		data: {serialNo:value, productID:$('#productID').val()},
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