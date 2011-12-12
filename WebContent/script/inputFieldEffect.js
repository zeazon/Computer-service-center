/*
 * Add focus, blur and hover effect to textbox and textarea
 * Set combobox widget to all select list
 */

$(document).ready(function(){
	
	$('input:text, input:password', this).addClass("textboxMockup");
	
	$(".textboxMockup")
		.focus(function(){$(this).addClass("textboxMockup_focus");})
		.blur(function(){$(this).removeClass("textboxMockup_focus");})
		.hover(function(){$(this).addClass("textboxMockup_hover");},function(){$(this).removeClass("textboxMockup_hover");});
	
	$(".textareaMockup")
		.focus(function(){$(this).addClass("textareaMockup_focus");})
		.blur(function(){$(this).removeClass("textareaMockup_focus");})
		.hover(function(){$(this).addClass("textareaMockup_hover");},function(){$(this).removeClass("textareaMockup_hover");});

	if($('select', this).hasClass("disabled")){
		$('select', this).combobox({disabled:true});
	}else{
		if($('select', this).hasClass("selectSearch")){
			$('select', this).combobox({showBlankValue:true});
		}else if(!$('select', this).hasClass("selectSearch")) {
			$('select', this).combobox();
		}
	}

});