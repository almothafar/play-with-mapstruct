'use strict';

/**
 * @ngdoc overview
 * @name uiApp
 * @description
 * # uiApp
 *
 * Main module of the application.
 */

function configureDefaults() {
  moment.locale('en', {
    week: {
      dow: 1, // Monday is the first day of the week.
      doy: 4 // The week that contains Jan 4th is the first week of the year.
    }
  });
}

angular
  .module('uiApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap',
    'ui.select',
    'truncate',
    'toastr',
    'blockUI',
    'ui.grid', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav', 'ui.grid.grouping',
    'ngTable',
    'underscore',
    'toggle-switch',
    'ui.mask'
  ])
  .config(function ($routeProvider, $locationProvider, blockUIConfig) {
    blockUIConfig.autoBlock = false;
    $locationProvider.html5Mode(true);
    $routeProvider
      .otherwise({
        redirectTo: '/view/home'
      });
  }).run(configureDefaults);
