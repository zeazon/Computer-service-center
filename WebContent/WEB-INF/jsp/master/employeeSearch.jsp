<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
	
	<table width="100%">
		<tr>
			<td>
				<form:form commandName="searchForm" id="searchForm" class="jqtransform" method="post" onsubmit="return false;">
					<table>
						<tr>
							<td><label><fmt:message key="name" />:</label></td>
							<td><div class="rowElem"><form:input path="name" type="text" id="name" class="textboxMockup" /></div></td>
						</tr>
						<tr>
							<td><label><fmt:message key="surname" />:</label></td>
							<td><div class="rowElem"><form:input path="surname" type="text" id="surname" class="textboxMockup" /></div></td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="rowElem">
									<!-- input type="button" id="searchButton" value="<fmt:message key='button.search' />" /-->
									<input type="submit" id="searchButton" value="<fmt:message key='button.search' />" />
								</div>
							</td>
						</tr>
					</table>
				</form:form>
			</td>
		</tr>
		<tr>
			<td>
				<table width="99%" style="margin:0px auto 0px auto;"><tr><td>
				<!--div style="margin:20px 0px 0px 0px; width:98%;"-->
					<!--div style="margin:0px auto 0px auto; width:100%; border:1px solid"-->
						<!--input type="button" value="Add"/> <input type="button" value="Edit"/> <input type="button" value="Delete"/-->
							<table id="list">
							</table>
							<div id="pager"></div>
				
							<form id="editForm" action="employee.html?do=preEdit" method="post">
								<input type="hidden" name="employeeID"/>
								<!-- input type="text" name="name"/-->
							</form>
		
							<div id="dialog" title="Feature not supported" style="display:none">
								<p>That feature is not supported.</p>
							</div>
					<!--/div-->
				<!--/div-->
				</td></tr></table>
			</td>
		</tr>
	</table>

<script type="text/javascript">
			jQuery().ready(function (){
				jQuery("#list").jqGrid({
				url:"searchEmp.html",
				//datatype: "local",
				datatype: "json",
				//height: 230,
				height: "100%",
				//autoheight: true,
				//width: "100%",
				autowidth: true,
				colNames:['<fmt:message key="employeeID" />','<fmt:message key="name" />','<fmt:message key="surname" />'],
				/*colModel:[
				{name:'id',index:'id', sorttype:"int"},
				{name:'invdate',index:'invdate', sorttype:"date", width:100, align:"center"},
				{name:'name',index:'name'},
				{name:'amount',index:'amount', align:"right",sorttype:"float"},
				{name:'tax',index:'tax', align:"right",sorttype:"float"}, {name:'total',index:'total', align:"right",sorttype:"float"}, {name:'note',index:'note', sortable:false} ],*/
				colModel:[
				{name:'employeeID',index:'employeeID', sorttype:"int"},
				{name:'name',index:'name'},
				{name:'surname',index:'surname'}],
				multiselect: false,
				//caption: "Manipulating Array Data",
				rownumbers: true,
				rowNum:10,
				rowList:[10,20,30],
				viewrecords: true,
				jsonReader:{
					repeatitems: false,
					id: "employeeID"
				},
				pager: '#pager',
				//toolbar: [true,"top"]
				toppager: true//,
				}).navGrid("#pager",{edit:false,add:false,del:false,search:false,refresh:false,cloneToTop:true})
				.navButtonAdd('#list_toppager',
					{
						caption:"<fmt:message key='button.add' />", 
						title:"<fmt:message key='button.add' />",
						//buttonimg:"row.gif",
						buttonicon:"ui-icon-plus", 
						onClickButton: function(){ 
							window.location = '<c:url value="/employee.html?do=preAdd" />';
						}, 
						position:"last"
				})
				.navButtonAdd('#list_toppager',
					{
						caption:"<fmt:message key='button.edit' />",
						title:"<fmt:message key='button.edit' />",
						//buttonimg:"row.gif",
						buttonicon:"ui-icon-pencil", 
						onClickButton: function(){ 
							//alert("Editing Row");
							 var gsr = jQuery("#list").getGridParam('selrow');
							if(gsr){
								jQuery("#list").GridToForm(gsr,"#editForm");
								$("#editForm").submit();
							} else {
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
						caption:"<fmt:message key='button.delete' />", 
						title:"<fmt:message key='button.delete' />",
						//buttonimg:"row.gif",
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
											//jQuery("#dialog").text('Entry has been deleted successfully');
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
										//alert ('After Submit: ' + postdata +'\n'+'xhr.message = '+xhr.message);
										//return [true]
										return [result.success, errors, new_id];
									}, 
									url: 'employee.html?do=delete&employeeID='+gr}); 
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
				

				/*var mydata = [
				{id:"1",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
				{id:"2",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
				{id:"3",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"4",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
				{id:"5",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
				{id:"6",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"7",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
				{id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
				{id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"10",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"11",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"12",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"13",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"14",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"15",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"16",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"17",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"18",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"19",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"20",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"21",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"22",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
				{id:"23",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}
				];*/
				var mydata = [
				{employeeID:"1",name:"Bancha",surname:"Jumpee"},
				{employeeID:"2",name:"Jarin",surname:"Pukam"},
				{employeeID:"3",name:"Malee",surname:"Laowongthai"},
				{employeeID:"4",name:"Werachat",surname:"Saejung"}
				];
				/*for(var i=0;i<=mydata.length;i++)
					jQuery("#list").jqGrid('addRowData',i+1,mydata[i]);
				*/
				//jQuery("#list").jqGrid('filterToolbar');

				var topPagerDiv = $("#list_toppager")[0];
				$("#list_toppager_center", topPagerDiv).remove();
				$(".ui-paging-info", topPagerDiv).remove();

				var bottomPagerDiv = $("div#pager")[0];
				$("#search_list", bottomPagerDiv).remove();
				//$("#add_list", bottomPagerDiv).remove();
				//$("#edit_list", bottomPagerDiv).remove();
				//$("#del_list", bottomPagerDiv).remove();
				//$("#pager4_left", bottomPagerDiv).empty();
				
				
				//$("#list").toolbarButtonAdd("#t_list",{caption:"",position:"first",title:"Refresh", align:"right", buttonicon :'ui-icon-refresh', onClickButton:function(){ $("#list").trigger("reloadGrid"); } });
				
				
				$(function() {
					//find all form with class jqtransform and apply the plugin
					$("form.jqtransform").jqTransform();
					
					$("button#searchButton").click(function() {
						gridReload();
					});
					
				});

			});	
			
			
			
			
			//	$.fn.extend({
					/*
					*  
					* The toolbar has the following properties
					*	id of top toolbar: t_<tablename>
					*	id of bottom toolbar: tb_<tablename>
					*	class of toolbar: ui-userdata
					* elem is the toolbar name to which button needs to be added. This can be 
					*		#t_tablename - if button needs to be added to the top toolbar
					*		#tb_tablename - if button needs to be added to the bottom toolbar
					*/
			//		toolbarButtonAdd: function(elem,p){
			//			p = $.extend({
			//			caption : "newButton",
			//			title: '',
			//			buttonicon : 'ui-icon-newwin',
			//			onClickButton: null,
			//			position : "last"
			//		}, p ||{});
			//		var $elem = $(elem);
			//		var tableString="<table style='float:left;table-layout:auto;' cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class='ui-toolbar-table'>";
			//		tableString+="<tbody> <tr></tr></table>";
					//console.log("In toolbar button add method");
						/* 
						* Step 1: check whether a table is already added. If not add
						* Step 2: If there is no table already added then add a table
						* Step 3: Make the element ready for addition to the table 
						* Step 4: Check the position and corresponding add the element
						* Step 5: Add other properties 
						*/
						//step 1 
			//			return this.each(function() {
			//				if( !this.grid)  { return; }
			//				if(elem.indexOf("#") != 0) { 
			//					elem = "#"+elem; 
			//				}
							//step 2
			//				if($(elem).children('table').length === 0){
			//					$(elem).append(tableString);
			//				}	
							//step 3
			//				var tbd = $("<td style=\"padding-left:1px;padding-right:1px\"></td>");
			//				$(tbd).addClass('ui-toolbar-button ui-corner-all').append("<div class='ui-toolbar-div'><span class='ui-icon "+p.buttonicon+"'></span>"+"<span>"+p.caption+"</span>"+"</div>").attr("title",p.title  || "")
			//				.click(function(e){
			//					if ($.isFunction(p.onClickButton) ) { p.onClickButton(); }
			//					return false;
			//				})
			//				.hover(
			//					function () {$(this).addClass("ui-state-hover");},
			//					function () {$(this).removeClass("ui-state-hover");}
			//				);
			//				if(p.id) {$(tbd).attr("id",p.id);}
			//				if(p.align) {$(elem).attr("align",p.align);}
			//				var findnav=$(elem).children('table');
			//				if(p.position ==='first'){
			//					if($(findnav).find('td').length === 0 ) {
			//						$("tr",findnav).append(tbd);
			//					} else {
			//						$("tr td:eq(0)",findnav).before(tbd);
			//					}
			//				} else {
								//console.log("not first");
			//					$("tr",findnav).append(tbd);
			//				}
			//			});
			//		},
			//	});
			
			

				
			function gridReload(){
				var name = jQuery("#name").val();
				var surname = jQuery("#surname").val();
				jQuery("#list").jqGrid('setGridParam',{url:"searchEmp.html?name="+name+"&surname="+surname,page:1}).trigger("reloadGrid");
			}
				
			/*$("#searchForm").keyup(function(event){
				if(event.keyCode == 13){
					$("#searchButton").click();
				}
			});*/
				
</script>