(function( $ ) {
	$.widget( "ui.combobox", {
		options:{
			showBlankValue: false,
			disabled: false
		},
		_create: function() {
			var self = this,
				select = this.element.hide(),
				selected = select.children( ":selected" ),
				value = selected.val() ? selected.text() : "";
			var span = this.span = $("<span></span>").insertAfter(select);
			var input = this.input = $( "<input id='"+self.element.context.id+"_autoComplete'>" )
				//.insertAfter( select )
				.appendTo(span)
				.val( value )
				.autocomplete({
					delay: 0,
					minLength: 0,
					source: function( request, response ) {
						var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
						response( select.children( "option" ).map(function() {
							var text = $( this ).text();
							if(self.options.showBlankValue){
								// if search keyword is blank show all option
								if(request.term == ''){
									return {
										label: text.replace(
											new RegExp(
												"(?![^&;]+;)(?!<[^<>]*)(" +
												$.ui.autocomplete.escapeRegex(request.term) +
												")(?![^<>]*>)(?![^&;]+;)", "gi"
											), "$1" ),
										value: text,
										option: this
									};
								}
								else{ 
									if ( this.value && ( !request.term || matcher.test(text) ) )
										return {
											label: text.replace(
												new RegExp(
													"(?![^&;]+;)(?!<[^<>]*)(" +
													$.ui.autocomplete.escapeRegex(request.term) +
													")(?![^<>]*>)(?![^&;]+;)", "gi"
												), "$1" ),
											value: text,
											option: this
										};
								}
							}else{
								if ( this.value && ( !request.term || matcher.test(text) ) )
									return {
										label: text.replace(
											new RegExp(
												"(?![^&;]+;)(?!<[^<>]*)(" +
												$.ui.autocomplete.escapeRegex(request.term) +
												")(?![^<>]*>)(?![^&;]+;)", "gi"
											), "$1" ),
										value: text,
										option: this
									};
								}								
							}
						) );
					},
					select: function( event, ui ) {
						ui.item.option.selected = true;
						self._trigger( "selected", event, {
							item: ui.item.option
						});
					},
					change: function( event, ui ) {
						if ( !ui.item ) {							
							var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
								valid = false;
							select.children( "option" ).each(function() {
								if ( $( this ).text().match( matcher ) ) {
									this.selected = valid = true;
									return false;
								}
							});
							if ( !valid ) {
								// remove invalid value, as it didn't match anything
								$( this ).val( "" );
								select.val( "" );
								input.data( "autocomplete" ).term = "";
								
								if(self.options.showBlankValue){
									input.val(selected.text());
								}
								
								return false;
							}
						}
						//self.checkVal(ui);
					}/*,search: function(event, ui){
						var re = $.ui.autocomplete.escapeRegex(req.term);
				        var matcher = new RegExp( "^" + re, "i" );
				        var a = $.grep( wordlist, function(item,index){
				            return matcher.test(item);
				        });
				        responseFn( a );
					}*/
					
				});
				//.addClass( "ui-widget ui-widget-content ui-corner-left" );
			
			input.data( "autocomplete" )._renderMenu= function( ul, items ) {
				var self = this;
				
				if(select.children( "option" ).length > 7){
					ul.addClass("ui-autocomplete-overflow");
				}else if(select.children( "option" ).length <= 7 && ul.hasClass("ui-autocomplete-overflow")){
					ul.removeClass("ui-autocomplete-overflow");
				}
				
				ul.removeClass("ui-corner-all");
				/*var width = ul.css("width");
				alert(width);
				width = width.substring(0, width.indexOf('px'));
				width = width*1;
				width += 30;
				alert(width);
				ul.css("width",width);*/
				
				$.each( items, function( index, item ) {
					self._renderItem( ul, item );
				});
			},
			
			input.data( "autocomplete" )._renderItem = function( ul, item ) {
				/* If select list has option more than 6, add scroll bar to option list */
				
				/*var width = ul.css("width");
				width = width.substring(0, width.indexOf('px'));
				width = width*1;
				width += 30;
				ul.css("width",width);*/
				return $( "<li></li>" )
					.data( "item.autocomplete", item )
					.append( "<a>" + item.label + "</a>" )
					.appendTo( ul );
			};

			this.button = $( "<button type='button'>&nbsp;</button>" )
				.attr( "tabIndex", -1 )
				.attr( "title", "Show All Items" )
				.insertAfter( input )
				/*.button({
					icons: {
						primary: "ui-icon-triangle-1-s"
					},
					text: false
				})*/
				//.removeClass( "ui-corner-all" )
				//.addClass( "ui-corner-right ui-button-icon" )
				
				/*.removeClass( "ui-widget" )
				.removeClass( "ui-corner-all" )
				.removeClass( "ui-state-default" )
				.removeClass( "ui-button-text-only" )*/
				.addClass("ui-autocomplete-button")
				.click(function() {
					// close if already visible
					if ( input.autocomplete( "widget" ).is( ":visible" ) ) {
						input.autocomplete( "close" );
						return;
					}

					// work around a bug (likely same cause as #5265)
					$( this ).blur();

					// pass empty string as value to search for, displaying all results
					input.autocomplete( "search", "" );
					input.focus();
				});
		
			// set input value to initial select list.
			input.val($("#"+self.element.context.id+" :selected").text());
			
			if(self.options.disabled){
				input.attr("disabled", "disabled");
				this.button.attr("disabled", "disabled");
			}
		},

		destroy: function() {
			this.input.remove();
			this.button.remove();
			this.element.show();
			$.Widget.prototype.destroy.call( this );
		},
	
		checkVal: function(ui){
			var select = this.element;
			var selected = select.children( ":selected" );
			
			if ( !ui.item ) {
				var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this.input).val() ) + "$", "i" ),
					valid = false;
				select.children( "option" ).each(function() {
					if ( $( this ).text().match( matcher ) ) {
						this.selected = valid = true;
						return false;
					}
				});
				if ( !valid ){
					// remove invalid value, as it didn't match anything
					$(this.input).val( "" );
					select.val( "" );
					this.input.data( "autocomplete" ).term = "";
					return false;
				}
			}
			
		}
		
		
		
		
	});
	
})( jQuery );