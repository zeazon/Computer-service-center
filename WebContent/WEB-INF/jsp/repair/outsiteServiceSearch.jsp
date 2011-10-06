<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<table width="100%">
	<tr>
		<td>
			<form:form commandName="searchForm" id="searchForm" class="jqtransform" method="post" onsubmit="gridReload(); return false;">
				<table>
					<tr>
						<td><label><fmt:message key="name" />:</label></td>
						<td><div class="rowElem"><form:input path="name" type="text" id="name" class="textboxMockup" /></div></td>
					</tr>
					<%--tr>
						<td><label><fmt:message key="surname" />:</label></td>
						<td><div class="rowElem"><form:input path="surname" type="text" id="surname" class="textboxMockup" /></div></td>
					</tr--%>
					<tr>
						<td><label><fmt:message key="date" />:</label></td>
						<td><div class="rowElem" style="z-index:200"><form:input path="date" type="text" class="textboxMockup" id="dateInput" size="9"/></div></td>
					</tr>
					<tr>
						<td><label><fmt:message key="type" />:</label></td>
						<td>
							<div class="rowElem" style="z-index:100">
								<form:select path="type" id="type">
									<form:option value="">&nbsp;</form:option>
									<form:options items="${typeList}" itemValue="typeID" itemLabel="name"/>
								</form:select>
							</div>
						</td>
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
						
						<form id="editForm" action="outsiteService.html?do=preEdit" method="post">
							<input type="hidden" name="outsiteServiceID"/>
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

<script type="text/javascript">
	//$("#dateInput").calendarsPicker($.extend({calendar: $.calendars.instance('thai','th')}));
	$("#dateInput").calendarsPicker($.extend({calendar: $.calendars.instance('gregorian','th')}));

	jQuery().ready(function (){
		//find all form with class jqtransform and apply the plugin
		$("form.jqtransform").jqTransform();
		
		jQuery("#list").jqGrid({
			url:"searchOutsiteService.html",
			datatype: "json",
			height: "100%",
			autowidth: true,
			colNames:['<fmt:message key="outsiteServiceID" />','<fmt:message key="date" />','<fmt:message key="name" />','<fmt:message key="type" />','<fmt:message key="brand" />','<fmt:message key="model" />','<fmt:message key="serviceOrder_problem" />','<fmt:message key="status" />'],
			colModel:[
				{name:'outsiteServiceID',index:'outsiteServiceID'},
				{name:'outsiteServiceDate', index:'outsiteServiceDate', align:'center', sorttype:'date',formatter:'date', formatoptions: {srcformat:'d/m/Y',newformat:'d/m/Y'}, width:'100', firstSortOrder:'desc'},
				{name:'name',index:'name'},
				{name:'type',index:'type', sortable:false, width:'90'},
				{name:'brand',index:'brand', sortable:false, width:'90'},
				{name:'model',index:'model', sortable:false, width:'90'},
				{name:'problem',index:'problem', sortable:false, width:'300'},
				{name:'status',index:'status', formatter:statusFormatter, align:'center', sortable:false, width:'90'}],
			multiselect: false,
			rownumbers: true,
			rowNum:10,
			rowList:[10,20,30],
			viewrecords: true,
			jsonReader:{
				repeatitems: false,
				id: "outsiteServiceID"
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
				window.location = '<c:url value="/outsiteService.html?do=preAdd" />';
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
							//jQuery('#dData').html('Del').removeClass('fm-button-icon-left');
							
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
						//url: 'serviceOrderID.html?do=delete&serviceOrderID='+gr}); 
						url: 'outsiteService.html?do=delete&serviceOrderID='+gr});
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
		
	});
	
	function statusFormatter (cellvalue, options, rowObject)
	{
		if(cellvalue == 'new'){
			return "<fmt:message key='outsiteService_status_new' />";
		}else if(cellvalue == 'sent'){
			return "<fmt:message key='outsiteService_status_sent' />";
		}else if(cellvalue == 'close'){
			return "<fmt:message key='outsiteService_status_close' />";
		}
	   return cellvalue;
	}
	
	function gridReload(){
		var name = jQuery("#name").val();
		var date = jQuery("#dateInput").val();
		var type = jQuery("#type").val();
		jQuery("#list").jqGrid('setGridParam',{url:"searchOutsiteService.html?name="+name+"&date="+date+"&type="+type,page:1}).trigger("reloadGrid");
	}
</script>