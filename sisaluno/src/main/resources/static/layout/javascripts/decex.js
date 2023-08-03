var DECEx = DECEx || {};

DECEx.onSidebarToggleRequest = function(event) {
  event.preventDefault();
  $(this).blur();

  $('.js-sidebar, .js-content').toggleClass('is-toggled');
};

DECEx.onSearchModalShowRequest = function(event) {
  event.preventDefault();

  $('.js-search-modal').fadeIn('slow');
  $('body').addClass('decex-no-scroll');
  
  $('.js-search-modal-input').val('').select();
  
};

DECEx.onSearchModalCloseRequest = function(event) {
  event.preventDefault();

  $('.js-search-modal').hide();
  $('body').removeClass('decex-no-scroll');
};

//DECEx.onFormLoadingSubmit = function(event) {
  //event.preventDefault();

  //DECEx.showLoadingComponent();

  //setTimeout(function() {
  //  DECEx.hideLoadingComponent();
  //}, 2000);
//};

DECEx.showLoadingComponent = function() {
  $('.js-loading-overlay').css('display', 'table').hide().fadeIn('slow');
};

DECEx.hideLoadingComponent = function() {
  $('.js-loading-component').fadeOut('fast');
};

DECEx.initStickyTableHeaders = function() {
  if ($(window).width() >= 992) { 
    var stickyRef = $('.js-sticky-reference');
    var stickyTable = $('.js-sticky-table');

    if (stickyRef && stickyTable) {
      stickyTable.stickyTableHeaders({fixedOffset: stickyRef});
    }
  }
};

DECEx.onMenuGroupClick = function(event) {
  var subItems = $(this).parent().find('ul');

  if (subItems.length) {
    event.preventDefault();
    $(this).parent().toggleClass('is-expanded');
  }
};

DECEx.initMenu = function() {
  $('.js-menu > ul > li > a').bind('click', DECEx.onMenuGroupClick);
  $('.decex-menu__item .is-active').parents('.decex-menu__item').addClass('is-expanded is-active');
};

$(function() {
  if (DECEx.init) {
    DECEx.init();
  }

  DECEx.initMenu();
  DECEx.initStickyTableHeaders();
  
  // Enable Bootstrap tooltip
  $('.js-tooltip').tooltip();
  
  // Bind events
  $('.js-sidebar-toggle').bind('click', DECEx.onSidebarToggleRequest);
  $('.js-search-modal-trigger-show').bind('click', DECEx.onSearchModalShowRequest);
  $('.js-search-modal-close').bind('click', DECEx.onSearchModalCloseRequest);
  //$('.js-form-loading').bind('submit', DECEx.onFormLoadingSubmit);
});