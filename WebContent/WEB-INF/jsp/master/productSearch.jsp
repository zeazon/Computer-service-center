<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<table width="100%">
	<tr>
		<td>
			<form:form commandName="searchForm" id="searchForm" class="jqtransform" method="post" onsubmit="gridReload(); return false;">
				<table>
					<tr>
						<td><label><fmt:message key="type" />:</label></td>
						<td>
							<div class="rowElem">
								<form:select path="typeID" id="type">
									<form:option value="" label="-"/>
									<form:options items="${typeList}" itemValue="typeID" itemLabel="name"/>
								</form:select>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="brand" />:</label></td>
						<td id="brandRow">
							<div class="rowElem" style="z-index:100">
								<form:select path="brandID" id="brand">
									<form:option value="" label="-"/>
									<form:options items="${brandList}" itemValue="brandID" itemLabel="name"/>
								</form:select>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="model" />:</label></td>
						<td id="modelRow">
							<div class="rowElem">
								<form:select path="modelID" id="model">
									<form:option value="" label="-"/>
								</form:select>
							</div>
						</td>
					</tr>
					<tr>
						<td><label><fmt:message key="description" />:</label></td>
						<td><div class="rowElem"><form:input path="description" type="text" id="description" class="textboxMockup" /></div></td>
					</tr>
					<tr>
						<td colspan="2"><div class="rowElem"><input type="submit" id="searchButton" value="<fmt:message key='button.search' />" /></div></td>
					</tr>
				</table>
			</form:form>
		</td>
	</tr>
	<tr>
		<td>
			<table width="99%" style="margin:0px auto 0px auto;">
				<tr>
					<td>
						<table id="list"></table>
						<div id="pager"></div>
						
						<form id="editForm" action="product.html?do=preEdit" method="post">
							<input type="hidden" name="productID"/>
						</form>
						
						<div id="dialog" title="Feature not supported" style="display:none">
							<p>That feature is not supported.</p>
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<c:url var="findBrandURL" value="/brand.html?do=getBrandByType" />
<c:url var="findModelURL" value="/model.html?do=getModel" />

<script type="text/javascript">
	jQuery().ready(function (){
		jQuery("#list").jqGrid({
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
			pager: '#pager',
			toppager: true
		}).navGrid("#pager",{edit:false,add:false,del:false,search:false,refresh:false,cloneToTop:true})
		.navButtonAdd('#list_toppager',
		{
			caption:"",
			title:"<fmt:message key='button.add' />",
			buttonicon:"ui-icon-plus",
			onClickButton: function(){ 
				window.location = '<c:url value="/product.html?do=preAdd" />';
			}, 
			position:"last"
		})
		.navButtonAdd('#list_toppager',
		{
			caption:"",
			title:"<fmt:message key='button.edit' />",
			buttonicon:"ui-icon-pencil", 
			onClickButton: function(){ 
				 var gsr = jQuery("#list").getGridParam('selrow');
				if(gsr){
					jQuery("#list").GridToForm(gsr,"#editForm");
					$("#editForm").submit();
				} else {
					//alert("<fmt:message key='msg.pleaseSelectRow' />");
					jQuery("#dialog").text('<fmt:message key='msg.pleaseSelectRow' />');
					jQuery("#dialog").dialog( 
						{
							title: 'Alert',
					      	modal: true,
					     	buttons: {"Ok": function()  {
					     		jQuery(this).dialog("close");} 
					      	}
				    });
				}         
			}, 
			position:"last"
		})
		.navButtonAdd('#list_toppager',
		{
			caption:"",
			title:"<fmt:message key='button.delete' />",
			buttonicon:"ui-icon-trash",
			onClickButton: function(){
				var gr = jQuery("#list").getGridParam("selrow");
				if( gr != null ) {
					jQuery("#list").delGridRow(gr,{
						beforeShowForm: function(form) {
							jQuery(".delmsg").replaceWith('<span style="white-space: pre;">' +
								'<fmt:message key="msg.deleteSelectedRecord" />' + '</span>');
						},
						afterSubmit: function(xhr,postdata){
							var result = eval('(' + xhr.responseText + ')');
							var errors = "";
							if (result.success == false) {
								for (var i = 0; i < result.message.length; i++) {
									errors +=  result.message[i] + "";
								}
							}else{
								jQuery("#dialog").text('<fmt:message key="msg.deleteSuccess" />');
								jQuery("#dialog").dialog( 
									{
										title: 'Success',
								      	modal: true,
								     	buttons: {"Ok": function()  {
								     		jQuery(this).dialog("close");} 
								      	}
							    });
							}
							// only used for adding new records
			                var new_id = null;
							return [result.success, errors, new_id];
						}, 
						url: 'product.html?do=delete&productID='+gr}); 
				} else { 
					//alert("<fmt:message key='msg.pleaseSelectRow' />");
					jQuery("#dialog").text('<fmt:message key='msg.pleaseSelectRow' />');
					jQuery("#dialog").dialog( 
						{
							title: 'Alert',
					      	modal: true,
					     	buttons: {"Ok": function()  {
					     		jQuery(this).dialog("close");} 
					      	}
				    });
				};
			},
			position:"last"
		});
		// remove unuse button
		var topPagerDiv = $("#list_toppager")[0];
		$("#list_toppager_center", topPagerDiv).remove();
		$(".ui-paging-info", topPagerDiv).remove();
		
		var bottomPagerDiv = $("div#pager")[0];
		$("#search_list", bottomPagerDiv).remove();
		
		$(function() {
			//find all form with class jqtransform and apply the plugin
			$("form.jqtransform").jqTransform();
			
			//$("button#searchButton").click(function() {
			//	gridReload();
			//});
			
		});
		
		
		$("#type").change(
			function() {
				$.getJSON('${findBrandURL}', {
					typeID : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '';
					var len = data.length;
					if(len > 0){
						html += '<option value="">-</option>';
						for ( var i = 0; i < len; i++) {
							html += '<option value="' + data[i].brandID + '">'
									+ data[i].name + '</option>';
						}
						html += '</option>';
					}else{
						html += '<option value="">-</option>';
					}
					
					$('#brand').html(html);
					
					// set change select list dynamic, ref http://www.code-pal.com/the-select-problem-after-using-jqtransform-and-its-solution/ 
					
					var sty = $("#brandRow div.jqTransformSelectWrapper").attr("style");
					
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
						html += '<option value="">-</option>';
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
					
					$("#modelRow div.jqTransformSelectWrapper").css("z-index", 8);
				});
			}
		);
		
	});
	
	function gridReload(){
		var description = jQuery("#description").val();
		var typeID = jQuery("#type").val();
		var brandID = jQuery("#brand").val();
		var modelID = jQuery("#model").val();
		jQuery("#list").jqGrid('setGridParam',{url:"searchProduct.html?description="+description+"&typeID="+typeID+"&brandID="+brandID+"&modelID="+modelID,page:1}).trigger("reloadGrid");
	}
</script>