$output.resource("static\assets\js", "app.js")##

var app = angular.module("mainApp", 
		["ngResource",/* for REST WS calls */
		 "ui.router",
		 "pascalprecht.translate", /* for label translation */
		 "ngAnimate", 
		 "ngSanitize", 
		 "mgcrea.ngStrap", /* ces 3 libs pour mgcrea */
		 "ngCsv" /* for downloading entites as CSV files, see https://github.com/asafdav/ng-csv */
]);
