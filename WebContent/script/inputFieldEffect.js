/*
 * Add focus, blur and hover effect to textbox and textarea
 */

$(document).ready(function(){
	$(".textboxMockup")
		.focus(function(){$(this).addClass("textboxMockup_focus");})
		.blur(function(){$(this).removeClass("textboxMockup_focus");})
		.hover(function(){$(this).addClass("textboxMockup_hover");},function(){$(this).removeClass("textboxMockup_hover");})
	
	$(".textareaMockup")
		.focus(function(){$(this).addClass("textareaMockup_focus");})
		.blur(function(){$(this).removeClass("textareaMockup_focus");})
		.hover(function(){$(this).addClass("textareaMockup_hover");},function(){$(this).removeClass("textareaMockup_hover");})

});