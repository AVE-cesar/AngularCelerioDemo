'use strict';

/* we define here all controllers */

/* controller dedicated to login page */
app.controller('LoginController', function ($rootScope, $scope, AuthSharedService, $log) {

        $scope.rememberMe = true;
        
        $scope.doLogin = function () {
        	$log.info("call login on server side for: " + $scope.username);
        	
            $rootScope.authenticationError = false;
            
            AuthSharedService.login(
                $scope.username,
                $scope.password,
                $scope.rememberMe
            );
        }
    })
;