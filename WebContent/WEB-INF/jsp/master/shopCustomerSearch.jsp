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
						
						<form id="editForm" action="shopCustomer.html?do=viewData" method="post">
							<input type="hidden" name="cuscod"/>
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
	jQuery().ready(function (){
		jQuery("#list").jqGrid({
			url:"searchShopCustomer.html",
			datatype: "json",
			height: "100%",
			autowidth: true,
			colNames:['<fmt:message key="customerID" />','<fmt:message key="customerType" />','<fmt:message key="name" />','<fmt:message key="address" />','<fmt:message key="tel" />'],
			colModel:[
				{name:'cuscod',index:'cuscod',width:'70'},
				{name:'custyp',index:'custyp',width:'40', formatter:custypFormatter, align:'center', sortable:false},
				{name:'fullName',index:'fullName'},
				{name:'fullAddr',index:'fullAddr',sortable:false},
				{name:'tel',index:'tel',sortable:false}],
			multiselect: false,
			rownumbers: true,
			rowNum:10,
			rowList:[10,20,30],
			viewrecords: true,
			jsonReader:{
				repeatitems: false,
				id: "cuscod"
			},
			pager: '#pager',
			toppager: true
		}).navGrid("#pager",{edit:false,add:false,del:false,search:false,refresh:false,cloneToTop:true})
		.navButtonAdd('#list_toppager',
		{
			caption:"",
			title:"<fmt:message key='button.view' />",
			buttonicon:"ui-icon-search", 
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
	});
	
	function gridReload(){
		var name = jQuery("#name").val();
		jQuery("#list").jqGrid('setGridParam',{search:true, url:"searchShopCustomer.html?name="+name,page:1}).trigger("reloadGrid");
	}
	
	function custypFormatter (cellvalue, options, rowObject)
	{
		if(cellvalue == '00'){
			return "<fmt:message key='custyp_00' />";
		}else if(cellvalue == '01'){
			return "<fmt:message key='custyp_01' />";
		}else if(cellvalue == '02'){
			return "<fmt:message key='custyp_02' />";
		}else if(cellvalue == '03'){
			return "<fmt:message key='custyp_03' />";
		}else if(cellvalue == '04'){
			return "<fmt:message key='custyp_04' />";
		}
	   return cellvalue;
	}
</script>