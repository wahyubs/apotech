/*
	 * jQuery.fn.moveNext()
	 *
	 * places the focus in the next form field. if the field element is
	 * the last in the form array, it'll return to the top.
	 *
	 * returns a jQuery object pointing to the next field element
	 *
	 * NOTE: if the selector returns multiple items, the first item is used.
	 *
	 *
	 * Examples:
	 * $("#firstName").moveNext();
	 * > Moves the focus to the next form field found after firstName
	 *
	 */
	// the moveNext() method
	$.fn.moveNext = function(){
		return this.moveIndex("next");
	};

	/*
	 * jQuery.fn.movePrev()
	 *
	 * places the focus in the previous form field. if the field element is
	 * the first in the form array, it'll return to the last element.
	 *
	 * returns a jQuery object pointing to the previos field element
	 *
	 * NOTE: if the selector returns multiple items, the first item is used
	 *
	 * Examples:
	 * $("#firstName").movePrev();
	 * > Moves the focus to the next form field found after firstName
	 *
	 */
	// the movePrev() method
	$.fn.movePrev = function(){
		return this.moveIndex("prev");
	};

	/*
	 * jQuery.fn.moveIndex()
	 *
	 * Places the tab index into the specified index position
	 *
	 * returns a jQuery object pointing to the previos field element
	 *
	 * NOTE: if the selector returns multiple items, the first item is used
	 *
	 * Examples:
	 * $("#firstName").movePrev();
	 * > Moves the focus to the next form field found after firstName
	 *
	 */
	// the moveIndex() method
	$.fn.moveIndex = function(i){

		// get the current position and elements
		var pos = getFieldPosition(this);
		
		// if a string option has been specified, calculate the position
		if( i == "next" ) i = pos[0] + 1; // get the next item
		else if( i == "prev" ) i = pos[0] - 1; // get the previous item

		// make sure the index position is within the bounds of the elements array
		if( i < 0 ) i = pos[1].length - 1;
		else if( i >= pos[1].length ) i = 0;

		return $(pos[1][i]).trigger("focus");
	};

	/*
	 * jQuery.fn.getTabIndex()
	 *
	 * gets the current tab index of the first element found in the selector
	 *
	 * NOTE: if the selector returns multiple items, the first item is used
	 *
	 * Examples:
	 * $("#firstName").getTabIndex();
	 * > Gets the tabIndex for the firstName field
	 *
	 */
	// the getTabIndex() method
	$.fn.getTabIndex = function(){
		// return the position of the form field
		return getFieldPosition(this)[0];
	};
	
	$.fn.getMaxTabIndex = function(){
		// return the position of the form field
		return getFieldPosition(this)[1].length;
	};

	var getFieldPosition = function (jq){
		var
			// get the first matching field
			$field = jq.filter("input, select, textarea").get(0),
			// store items with a tabindex
			tabIndex = [],
			// store items with no tabindex
			posIndex = [];

		// if there is no match, return 0
		if( !$field ) return [-1, []];

		// make a single pass thru all form elements
		$.each(
			$field.form.elements,
			function (i, o){
				if( o.tagName != "FIELDSET" && !o.disabled && jQuery(o).is(":visible") ){
					if( o.tabindex > 0 ){
						tabIndex.push(o);
					} else {
						posIndex.push(o);
					}
				}
			}
		);

		// sort the fields that had tab indexes
		tabIndex.sort(
			function (a, b){
				return a.tabindex - b.tabindex;
			}
		);

		// merge the elements to create the correct tab position
		tabIndex = $.merge(tabIndex, posIndex);

		for( var i=0; i < tabIndex.length; i++ ){
			if( tabIndex[i] == $field ) return [i, tabIndex];
		}

		return [-1, tabIndex];
	};
		
	/*--------------------------------------------------------------------
	* jQuery pixel/em conversion plugins: toEm() and toPx()
	* by Scott Jehl (scott@filamentgroup.com), http://www.filamentgroup.com
	* Copyright (c) Filament Group
	* Dual licensed under the MIT (filamentgroup.com/examples/mit-license.txt) or GPL (filamentgroup.com/examples/gpl-license.txt) licenses.
	* Article: http://www.filamentgroup.com/lab/update_jquery_plugin_for_retaining_scalable_interfaces_with_pixel_to_em_con/
	* Options:
	scope: string or jQuery selector for font-size scoping
	* Usage Example: $(myPixelValue).toEm(); or $(myEmValue).toPx();
	--------------------------------------------------------------------*/

	$.fn.toEm = function(settings){
	settings = jQuery.extend({
	scope: 'body'
	}, settings);
	var that = parseInt(this[0],10),
	scopeTest = jQuery('<div style="display: none; font-size: 1em; margin: 0; padding:0; height: auto; line-height: 1; border:0;">&nbsp;</div>').appendTo(settings.scope),
	scopeVal = scopeTest.height();
	scopeTest.remove();
	return (that / scopeVal).toFixed(8) + 'em';
	};

	$.fn.toPx = function(settings){
	settings = jQuery.extend({
	scope: 'body'
	}, settings);
	var that = parseFloat(this[0]),
	scopeTest = jQuery('<div style="display: none; font-size: 1em; margin: 0; padding:0; height: auto; line-height: 1; border:0;">&nbsp;</div>').appendTo(settings.scope),
	scopeVal = scopeTest.height();
	scopeTest.remove();
	return Math.round(that * scopeVal) + 'px';
	};

	/*-------------------------------------------------------------------- 
	 * JQuery Plugin: "EqualHeights"
	 * by:	Scott Jehl, Todd Parker, Maggie Costello Wachs (http://www.filamentgroup.com)
	 *
	 * Copyright (c) 2008 Filament Group
	 * Licensed under GPL (http://www.opensource.org/licenses/gpl-license.php)
	 *
	 * Description: Compares the heights or widths of the top-level children of a provided element 
	 		and sets their min-height to the tallest height (or width to widest width). Sets in em units 
	 		by default if pxToEm() method is available.
	 * Dependencies: jQuery library, pxToEm method	(article: 
			http://www.filamentgroup.com/lab/retaining_scalable_interfaces_with_pixel_to_em_conversion/)							  
	 * Usage Example: $(element).equalHeights();
	  		Optional: to set min-height in px, pass a true argument: $(element).equalHeights(true);
	 * Version: 2.0, 08.01.2008
	--------------------------------------------------------------------*/

	$.fn.equalHeights = function(px) {
		$(this).each(function(){
			var currentTallest = 0;
			$(this).children().each(function(i){
				if ($(this).height() > currentTallest) { currentTallest = $(this).height(); }
			});
			if (!px) currentTallest = $(currentTallest).toEm(); //use ems unless px is specified
			// for ie6, set height since min-height isn't supported
			if ($.browser.msie && $.browser.version == 6.0) { $(this).children().css({'height': currentTallest}); }
			$(this).children().css({'min-height': currentTallest}); 
		});
		return this;
	};

