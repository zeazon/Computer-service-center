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
			<form:form commandName="form" id="form" class="jqtransform" action="outsiteCompany.html?do=save">
				<table width="100%">
					<tr>
						<td width="40%"><label><fmt:message key="outsiteCompanyID" />:</label></td>
						<td><div class="rowElem"><form:input path="ocID" readonly="true" class="textboxMockup" /></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="name" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input path="name" class="textboxMockup" maxlength="100"/> <label class="error" for="name" generated="true" style="display: none; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td valign="top" style="padding-top:11px"><label><fmt:message key="address" />:</label></td>
						<td>
							<div class="rowElem">
								<form:input type="text" path="addr" class="textboxMockup" style="width:640px" /><br><br>
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
								<div id="zipcode" style="float:left; padding:5px; 0 0 5px">${zipcode}</div>
							</div>
						</td>
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
<c:url var="findZipcodeBySubdistrictURL" value="/findZipcode.html" />

<script type="text/javascript">

$(document).ready(function(){
	$("#form").validate({
		rules: {
			name: "required"
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
	
	$("#subdistrict").change(
		function(){
			$.getJSON('${findZipcodeBySubdistrictURL}', {
				subdistrictID : $(this).val(),
				ajax : 'true'
			}, function(data) {
				$("#zipcode").html(data);
			});
		}
	);
	
});

</script>