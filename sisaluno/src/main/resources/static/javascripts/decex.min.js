var DECEx=DECEx||{};

DECEx.onSidebarToggleRequest=function(a){a.preventDefault(),
$(this).blur(),
$(".js-sidebar, .js-content").toggleClass("is-toggled")},

DECEx.onSearchModalShowRequest=function(a){a.preventDefault(),
$(".js-search-modal").fadeIn("slow"),
$("body").addClass("decex-no-scroll"),
$(".js-search-modal-input").val("").select()},

DECEx.onSearchModalCloseRequest=function(a){a.preventDefault(),
$(".js-search-modal").hide(),
$("body").removeClass("decex-no-scroll")},

DECEx.showLoadingComponent=function(){$(".js-loading-overlay").css("display","table").hide().fadeIn("slow")},
DECEx.hideLoadingComponent=function(){$(".js-loading-component").fadeOut("fast")},

DECEx.initStickyTableHeaders=function(){if($(window).width()>=992){var a=$(".js-sticky-reference"),b=$(".js-sticky-table");
a&&b&&b.stickyTableHeaders({fixedOffset:a})}},

DECEx.onMenuGroupClick=function(a){var b=$(this).parent().find("ul");

b.length&&(a.preventDefault(),$(this).parent().toggleClass("is-expanded"))},
DECEx.initMenu=function(){$(".js-menu > ul > li > a").bind("click",DECEx.onMenuGroupClick),
$(".decex-menu__item .is-active").parents(".decex-menu__item").addClass("is-expanded is-active")},
$(function(){DECEx.init&&DECEx.init(),

DECEx.initMenu(),
DECEx.initStickyTableHeaders(),
$(".js-tooltip").tooltip(),
$(".js-sidebar-toggle").bind("click",DECEx.onSidebarToggleRequest),
$(".js-search-modal-trigger-show").bind("click",DECEx.onSearchModalShowRequest),
$(".js-search-modal-close").bind("click",DECEx.onSearchModalCloseRequest)});