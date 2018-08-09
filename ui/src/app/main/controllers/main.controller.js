'use strict';

/**
 * @ngdoc function
 * @name uiApp.controller:MainController
 * @description
 * # MainController
 * Controller of the uiApp
 */
angular.module('uiApp')
  .controller('MainController', function ($scope, $http) {

    $http.post('/api/users/login', {userId: 1}).then(function (data) {
      $http.get('/api/users/currentUser').then(function (data) {
        $scope.hello = "Hello " + data.fullName;
      }, function (error) {
        console.debug(error);
      });
    }, function (error) {
      console.debug(error);
    });


  });
