$output.resource("static\assets\js", "app.js")##

var app = angular.module("mainApp", 
		["ngResource", /* for REST WS calls */
		 "ui.router", /* pour d�couper l'appli en liens */
		 "pascalprecht.translate", /* for label translation */
		 "ngAnimate", 
		 "ngSanitize", 
		 "mgcrea.ngStrap", /* ces 3 derni�res libs pour mgcrea */
		 "ngCsv", /* for downloading entites as CSV files, see https://github.com/asafdav/ng-csv */
		 "http-auth-interceptor" /* permet de faciliter le login d'un user en angularjs */
]);
