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
								<%--form:checkboxes path="type" items="${typeList}" itemValue="typeID" itemLabel="name" element="div style='float:left'" /--%>
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
						
						<form id="editForm" action="getServiceOrder.html?do=preEdit" method="post">
							<input type="hidden" name="serviceOrderID"/>
						</form>
						
						<div id="dialog" title="Feature not supported" style="display:none">
							<p>That feature is not supported.</p>
						</div>
						
						<div id="confirmDialog" style="display:none">
						
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

	jQuery("#confirmDialog").dialog({
		modal: true,
		bgiframe: true,
		width: 300,
		height: 200,
		autoOpen: false
	});
	
	jQuery().ready(function (){
		//find all form with class jqtransform and apply the plugin
		$("form.jqtransform").jqTransform();
		
		jQuery("#list").jqGrid({
			url:"searchGetServiceOrder.html",
			datatype: "json",
			height: "100%",
			autowidth: true,
			colNames:['<fmt:message key="serviceOrderID" />','<fmt:message key="date" />','<fmt:message key="name" />','<fmt:message key="tel" />','<fmt:message key="mobileTel" />','<fmt:message key="status" />'],
			colModel:[
				{name:'serviceOrderID',index:'serviceOrderID'},
				{name:'serviceOrderDate', index:'serviceOrderDate', align:'center', sorttype:'date',formatter:'date', formatoptions: {srcformat:'d/m/Y',newformat:'d/m/Y'}, width:'100', firstSortOrder:'desc'},
				{name:'name',index:'name'},
				{name:'tel',index:'tel', sortable:false},
				{name:'mobileTel',index:'mobileTel', sortable:false},
				{name:'status',index:'status', formatter:statusFormatter, align:'center'}],
			multiselect: false,
			rownumbers: true,
			rowNum:10,
			rowList:[10,20,30],
			viewrecords: true,
			jsonReader:{
				repeatitems: false,
				id: "serviceOrderID"
			},
			pager: '#pager',
			toppager: true
		}).navGrid("#pager",{edit:false,add:false,del:false,search:false,refresh:false,cloneToTop:true})
		.navButtonAdd('#list_toppager',
		{
			caption:"",
			title:"<fmt:message key='button.getServiceOrder' />",
			buttonicon:"ui-icon-wrench",
			onClickButton: function(){
				var gsr = jQuery("#list").getGridParam('selrow');
				if(gsr){
					jQuery("#confirmDialog").text('<fmt:message key='msg.getServiceOrder' /> '+gsr+' ?');
					jQuery("#confirmDialog").dialog('option', 'buttons', {
						"Confirm" : function() {
							//alert('confirm');
							//window.location.href = theHREF;
							
							//url: 'serviceOrderID.html?do=delete&serviceOrderID='+gr});
							$.getJSON('getServiceOrder.html?do=getServiceOrder', {
								serviceOrderID: gsr
							}, function(data) {
								//alert(data.success+' : '+data.message);
								if(data.success == true){
									jQuery("#confirmDialog").dialog("close");		
									//jQuery("#dialog").text('<fmt:message key="msg.deleteSuccess" />');
									jQuery("#dialog").text(data.message.toString());
									jQuery("#dialog").dialog( 
										{
											title: 'Success',
									      	modal: true,
									     	buttons: {"Ok": function()  {
									     		jQuery(this).dialog("close");} 
									      	}
								    });
									gridReload();
								}else{
									jQuery("#confirmDialog").dialog("close");
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
						},
						"Cancel" : function() {
							$(this).dialog("close");
						}
					});
					//jQuery("#confirmDialog").dialog( 
					//	{
					//		title: 'Alert',
					 //     	modal: true,
					  //   	buttons: {"Confirm": function()  {
					 //    		jQuery(this).dialog("close");} 
					//      	}
				    //});
					jQuery("#confirmDialog").dialog("open");
					//jQuery("#list").GridToForm(gsr,"#editForm");
					//$("#editForm").submit();
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
			return "<fmt:message key='serviceOrder_status_new' />";
		}
	   return cellvalue;
	}
	
	function gridReload(){
		var name = jQuery("#name").val();
		//var surname = jQuery("#surname").val();
		var date = jQuery("#dateInput").val();
		var type = jQuery("#type").val();
		jQuery("#list").jqGrid('setGridParam',{url:"searchGetServiceOrder.html?name="+name+"&date="+date+"&type="+type,page:1}).trigger("reloadGrid");
	}
</script>