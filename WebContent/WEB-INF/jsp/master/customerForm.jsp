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
			<form:form commandName="form" id="form" class="jqtransform" action="customer.html?do=save">
				<table width="100%">
					<tr>
						<td width="40%"><label><fmt:message key="customerID" />:</label></td>
						<td><div class="rowElem"><form:input type="text" path="customerID" readonly="true" class="textboxMockup" /></div></td>
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
					<%--tr>
						<td><label><fmt:message key="company" />:</label></td>
						<td><div class="rowElem"><form:input type="text" path="company" class="textboxMockup" /></div></td>
					</tr--%>
					<tr>
						<td><label><fmt:message key="name" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input path="name" class="textboxMockup" maxlength="255" /> <label class="error" for="name" generated="true" style="display: none; padding-left:10px"></label></div></td>
					</tr>
					<%--tr>
						<td><label><fmt:message key="surname" />:</label></td>
						<td><div class="rowElem"><form:input type="text" path="surname" class="textboxMockup" /></div></td>
					</tr--%>
					<tr>
						<td valign="top" style="padding-top:11px"><label><fmt:message key="address" />:</label></td>
						<td>
							<div class="rowElem">
								<form:input type="text" path="address" class="textboxMockup" maxlength="255" style="width:640px" /><br><br>
								<div style="float:left; margin-top:5px;"><fmt:message key="subdistrict" />:</div>
								<form:select id="subdistrict" path="subdistrictID" items="${subdistrictList}" itemValue="subdistrictID" itemLabel="name">
								</form:select>
								
								<div style="float:left; margin:5px 0 0 15px;"><fmt:message key="district" />:</div>
								<form:select id="district" path="districtID" items="${districtList}" itemValue="districtID" itemLabel="name">
								</form:select>
								
								<div style="float:left; margin:5px 0 0 15px;"><fmt:message key="province" />:</div>
								<form:select id="province" path="provinceID" items="${provinceList}" itemValue="provinceID" itemLabel="name" />
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="tel" />:</label></td>
						<td><div class="rowElem"><form:input path="tel" class="textboxMockup telephone" maxlength="100" /></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="mobileTel" />:</label></td>
						<td><div class="rowElem"><form:input path="mobileTel" class="textboxMockup telephone" maxlength="100" /></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="email" />:</label></td>
						<td><div class="rowElem"><form:input path="email" class="textboxMockup" maxlength="50" /></div></td>
					</tr>
					<tr align="center">
						<td colspan="2"><div class="rowElem"><input type="submit" value="<fmt:message key='button.ok' />" /></div></td>
					</tr>
				</table>
			</form:form>
		</td>
	</tr>
</table>

<c:url var="findDistrictByProvinceURL" value="/findDistrit.html" />
<c:url var="findSubdistrictByDistrictURL" value="/findSubdistrict.html" />

<script type="text/javascript">

$(document).ready(function(){
	$("#form").validate({
		rules: {
			name: "required",
			email: "email",
			tel: {require_from_group: [1,".telephone"]},
			mobileTel: {require_from_group: [1,".telephone"]}
		}
	});
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
	
	//$("#district").change(alert('district change'));
	
	$("#province").change(
		function() {
			$.getJSON('${findDistrictByProvinceURL}', {
				provinceID : $(this).val(),
				ajax : 'true'
			}, function(data) {
				//var html = '<option value="">District</option>';
				var html = '';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].districtID + '">'
							+ data[i].name + '</option>';
				}
				html += '</option>';
 
				$('#district').html(html);
				
				// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
				
				var sty = $("#color div.jqTransformSelectWrapper").attr("style");
				
				var sels = $("#district").removeClass("jqTransformHidden");
				var $par = $("#district");
				$par.parent().replaceWith($par);
				sels.jqTransSelect();
				$("#color div.jqTransformSelectWrapper").attr("style", sty);
				
				// trigger event change of district select list
				$("#district").change();
			});
		}
	);
	
	$("#district").change(
		function(){
			$.getJSON('${findSubdistrictByDistrictURL}', {
				districtID : $(this).val(),
				ajax : 'true'
			}, function(data) {
				var html = '';
				var len = data.length;
				for ( var i = 0; i < len; i++){
					html += '<option value="' + data[i].subdistrictID + '">'
							+ data[i].name + '</option>';
				}
				html += '</option>';
				
				$('#subdistrict').html(html);
				
				// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
				
				var sty = $("#color div.jqTransformSelectWrapper").attr("style");
				
				var sels = $("#subdistrict").removeClass("jqTransformHidden");
				var $par = $("#subdistrict");
				$par.parent().replaceWith($par);
				sels.jqTransSelect();
				$("#color div.jqTransformSelectWrapper").attr("style", sty);
			});
		}
	);
	
	
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
		/*alert($(element).data('being_validated'));
		if($(element).data('being_validated') == null){
			alert('!');
		}*/
		//if(!$(element).data('being_validated')) {
		/*if($(element).data('being_validated') == null) {
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
	
});

</script>