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
								<form:input path="productID" class="textboxMockup" readonly="true" maxlength="20"/>
								<%--c:choose>
									<c:when test="${mode == 'add'}"><form:input path="productID" class="textboxMockup" maxlength="20" /></c:when>
									<c:otherwise><form:input path="productID" class="textboxMockup" readonly="true" maxlength="20"/></c:otherwise>
								</c:choose--%>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="type" />:</label></td>
						<td>
							<div class="rowElem">
								<form:select path="typeID" id="type">
									<form:options items="${typeList}" itemValue="typeID" itemLabel="name"/>
								</form:select>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="brand" />:</label></td>
						<td id="brandRow">
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
						<td id="modelRow">
							<div class="rowElem">
								<form:select path="modelID" id="model">
									<form:options items="${modelList}" itemValue="modelID" itemLabel="name"/>
								</form:select>
							</div>
							<label class="error" for="model" generated="true" style="display: none; padding-left:10px;"></label>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="serialNo" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input path="serialNo" class="textboxMockup" maxlength="100"/> <label class="error" for="name" generated="true" style="display: none; padding-left:10px"></label></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="description" />:</label></td>
						<td><div class="rowElem"><form:input path="description" class="textboxMockup" maxlength="255"/></div></td>
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

<c:url var="findBrandURL" value="/brand.html?do=getBrandByType" />
<c:url var="findModelURL" value="/model.html?do=getModel" />

<script type="text/javascript">

$("#warrantyDate").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));
$("#warrantyExpire").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));

$(document).ready(function(){
	$("#form").validate({
		rules: {
			serialNo: "required",
			brandID: "required",
			modelID: "required",
			remark:{
				maxlength: 1000
			}
		}
	});
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
	
	$("#type").change(
		function() {
			$.getJSON('${findBrandURL}', {
				typeID : $(this).val(),
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
				
				// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
				
				var sty = $("#brandRow div.jqTransformSelectWrapper").attr("style");
				//alert($("#brandRow div.jqTransformSelectWrapper").attr("style"));
				
				var sels = $("#brand").removeClass("jqTransformHidden");
				var $par = $("#brand");
				$par.parent().replaceWith($par);
				sels.jqTransSelect();
				//$("#brandRow div.jqTransformSelectWrapper").attr("style", sty);
				//$("#brandRow div.jqTransformSelectWrapper").attr("style", "z-index:9;");
				
				// trigger event change of brand select list
				$("#brand").change();
				$("#brandRow div.jqTransformSelectWrapper").css("z-index", 9);
			});
		}
	);
	
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
					html += '<option value=""></option>';
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
				
				$("#modelRow div.jqTransformSelectWrapper").css("z-index", 8);
			});
		}
	);
});

</script>