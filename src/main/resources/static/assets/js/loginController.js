
/* Controller that hanldes login/logout */

app.controller("LoginController", [
	"$rootScope",
	"$scope",  
	"$log",
	"$state",
	"credential",
	function(rootscope, scope, log, state, credential) {

log.info("inside LoginController, login: " + credential.login);
scope.credential = credential;

/* global variable to persist if the user is logged or not */
rootscope.authenticated = false;
log.info("inside LoginController, authenticated: " + rootscope.authenticated);

scope.doLogin = function(data) {
	log.info("doLogin, password: " + data.password);
	if ("admin" === data.password) {
		state.go('book');
	} else {
		scope.credential.wrong = true;
		log.info("wrong credential");
	}
}
}]);


/** main REST client to deal with security calls */
app.factory('SecurityRestService', function ($resource) {
	return $resource('api/books/bypage/?page=:page&size=:size', {}, {
		'get': {
			method: 'GET',
			url: 'api/books/:id',
			transformResponse: function (data) {
				data = angular.fromJson(data);
				return data;
			}
		}
	});
});
