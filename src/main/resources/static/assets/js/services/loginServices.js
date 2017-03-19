'use strict';

/* first define a service named 'Session' */
app.service('Session', function () {

	/* create function */
    this.create = function (data) {
        this.id = data.id;
        this.login = data.login;
        this.firstName = data.firstName;
        this.lastName = data.familyName;
        this.email = data.email;
        this.userRoles = [];
        angular.forEach(data.authorities, function (value, key) {
            this.push(value.name);
        }, this.userRoles);
    };
    
    /* invalidate function */
    this.invalidate = function () {
        this.id = null;
        this.login = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.userRoles = null;
    };
    
    return this;
});

/* rest calls to the server */
app.service('AuthSharedService', function ($rootScope, $http, $resource, $log, authService, Session) {
    return {
        login: function (userName, password, rememberMe) {
            var config = {
                ignoreAuthModule: 'ignoreAuthModule',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            };
            /* rest call with parameters */
            $http.post('authenticate', $.param({
                username: userName,
                password: password,
                rememberme: rememberMe
            }), config)
                .success(function (data, status, headers, config) {
                	$log.info("login successful");
                    authService.loginConfirmed(data);
                    $rootScope.authenticated= true;
                })
                .error(function (data, status, headers, config) {
                	$log.info("login failed !");
                    $rootScope.authenticationError = true;
                    
                    Session.invalidate();
                });
        },
        
        getAccount: function () {
            $rootScope.loadingAccount = true;
            $http.get('security/account')
                .then(function (response) {
                    authService.loginConfirmed(response.data);
                });
        },
        
        isAuthorized: function (authorizedRoles) {
            if (!angular.isArray(authorizedRoles)) {
                if (authorizedRoles == '*') {
                    return true;
                }
                authorizedRoles = [authorizedRoles];
            }
            var isAuthorized = false;
            angular.forEach(authorizedRoles, function (authorizedRole) {
                var authorized = (!!Session.login &&
                Session.userRoles.indexOf(authorizedRole) !== -1);
                if (authorized || authorizedRole == '*') {
                    isAuthorized = true;
                }
            });
            return isAuthorized;
        },
        logout: function () {
            $rootScope.authenticationError = false;
            $rootScope.authenticated = false;
            $rootScope.account = null;
            $http.get('logout');
            Session.invalidate();
            /* send event name:  event:auth-loginCancelled*/
            authService.loginCancelled();
        }
    };
});

