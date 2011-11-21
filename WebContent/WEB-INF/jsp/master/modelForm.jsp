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
			<form:form commandName="form" id="form" class="jqtransform" action="model.html?do=save">
				<table width="100%">
					<tr>
						<td width="40%"><label><fmt:message key="modelID" />:</label></td>
						<td><div class="rowElem"><form:input type="text" path="modelID" readonly="true" class="textboxMockup" /></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="type" />:</label></td>
						<td>
							<div class="rowElem">
								<form:select path="typeID" id="type">
									<form:option value=""/>
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
									<form:option value=""/>
									<form:options items="${brandList}" itemValue="brandID" itemLabel="name"/>
								</form:select>
								<label class="error" for="brand" generated="true" style="display: none; padding-left:10px;"></label>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="name" />:<font style="color:red">*</font></label></td>
						<td><div class="rowElem"><form:input type="text" path="name" class="textboxMockup" /> <label class="error" for="name" generated="true" style="display: none; padding-left:10px"></label></div></td>
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

<script type="text/javascript">

$(document).ready(function(){
	$("#form").validate({
		rules: {
			name: "required",
			typeID: "required",
			brandID: "required"
		}
	});
	
	//find all form with class jqtransform and apply the plugin
	$("form.jqtransform").jqTransform();
	
	
	
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
					$(this).val( "" );
					select.val( "" );
					//this.data( "autocomplete" ).term = "";
					$( "#type_autoComplete" ).data( "autocomplete" ).term = "";
					
					// set brand to empty
					$( "#brand_autoComplete" ).val("");
					$( "#brand").val("");
					$( "#brand_autoComplete" ).data( "autocomplete" ).term = "";
					return false;
				}
			 }else{
				 $.getJSON('${findBrandURL}', {
					typeID : select.val(),
					ajax : 'true'
				}, function(data) {
					var html = '';
					var len = data.length;
					html += '<option value=""></option>'
					if(len > 0){
						for ( var i = 0; i < len; i++) {
							html += '<option value="' + data[i].brandID + '">'
									+ data[i].name + '</option>';
						}
						html += '</option>';
					}/*else{
						html += '<option value=""></option>';
					}*/
					
					$('#brand').html(html);
					
					$('#brand_autoComplete').width($('#brand').width());
					$('#brand_autoComplete').val($("#brand :selected").text());
					
					$("#brandRow").css("z-index", 9);
				});
			 }
		}
	});
	
	
	
	/*$("#type_autoComplete").change(
		function() {
			alert('change');
			$.getJSON('${findBrandURL}', {
				typeID : $(this).val(),
				ajax : 'true'
			}, function(data) {
				var html = '';
				var len = data.length;
				html += '<option value=""></option>';
				if(len > 0){
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].brandID + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';
				}/*else{
					html += '<option value=""></option>';
				}*/
				
				/*$('#brand').html(html);
				
				$('#brand_autoComplete').width($('#brand').width());
				$('#brand_autoComplete').val($("#brand :selected").text());*/
				
				// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
				
				/*var sty = $("#brandRow div.jqTransformSelectWrapper").attr("style");
				//alert($("#brandRow div.jqTransformSelectWrapper").attr("style"));
				
				var sels = $("#brand").removeClass("jqTransformHidden");
				var $par = $("#brand");
				$par.parent().replaceWith($par);
				sels.jqTransSelect();
				//alert(sty);
				//$("#brandRow div.jqTransformSelectWrapper").attr("style", sty);
				//$("#brandRow div.jqTransformSelectWrapper").attr("style", "z-index:9;");
				
				//// trigger event change of model select list
				//$("#brand").change();
				$("#brandRow div.jqTransformSelectWrapper").css("z-index", 9);*/
			/*});
		}
	);*/
});

</script>