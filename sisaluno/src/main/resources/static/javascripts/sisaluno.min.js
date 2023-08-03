var DECEX=DECEX||{};

DECEX.onSidebarToggleRequest=function(a){
	a.preventDefault(),
	$(this).blur(),
	$(".js-sidebar, .js-content").toggleClass("is-toggled")
},

DECEX.onSearchModalShowRequest=function(a){
	a.preventDefault(),
	$(".js-search-modal").fadeIn("slow"),
	$("body").addClass("decex-no-scroll"),
	$(".js-search-modal-input").val("").select()
},

DECEX.onSearchModalCloseRequest=function(a){
	a.preventDefault(),
	$(".js-search-modal").hide(),
	$("body").removeClass("decex-no-scroll")
},

DECEX.showLoadingComponent=function(){
	$(".js-loading-overlay").css("display","table").hide().fadeIn("slow")
},

DECEX.hideLoadingComponent=function(){
	$(".js-loading-component").fadeOut("fast")
},

DECEX.initStickyTableHeaders=function(){
	if($(window).width()>=992){
		var a=$(".js-sticky-reference"),
		b=$(".js-sticky-table");a&&b&&b.stickyTableHeaders({
			fixedOffset:a})}
},

DECEX.onMenuGroupClick=function(a){
	var b=$(this).parent().find("ul");
	b.length&&(a.preventDefault(),
	$(this).parent().toggleClass("is-expanded"))
},

DECEX.initMenu=function(){
	$(".js-menu > ul > li > a").bind("click",DECEX.onMenuGroupClick),
	$(".decex-menu__item .is-active").parents(".decex-menu__item").addClass("is-expanded is-active")
},

$(function(){
	DECEX.init&&DECEX.init(),
	DECEX.initMenu(),
	DECEX.initStickyTableHeaders(),
	$(".js-tooltip").tooltip(),
	$(".js-sidebar-toggle").bind("click",DECEX.onSidebarToggleRequest),
	$(".js-search-modal-trigger-show").bind("click",DECEX.onSearchModalShowRequest),
	$(".js-search-modal-close").bind("click",DECEX.onSearchModalCloseRequest)});