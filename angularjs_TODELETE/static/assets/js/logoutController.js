'use strict';

/* we define here all controllers */

/* controller dedicated to login page */
app.controller('LogoutController', function ($rootScope, $scope, AuthSharedService, $log) {

	AuthSharedService.logout();
	
    });