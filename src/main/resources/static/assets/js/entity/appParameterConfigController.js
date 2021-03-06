//
// Source code generated by Celerio, a Jaxio product.
// Documentation: http://www.jaxio.com/documentation/celerio/
// Follow us on twitter: @jaxiosoft
// Need commercial support ? Contact us: info@jaxio.com
// Template angular-lab:angularjs/assets/js/entity/EntityConfigController.e.vm.js
//

app.controller("AppParameterConfigController", ["$scope", "$window", "$aside", 
"$log", "AppParameterRestService", 
		"$alert", "$timeout", "config", function(scope, window, aside, log, 
		appParameterRestService, 
		alertService, timeoutService, config) {

	log.info("inside AppParameterConfigController, config.value: " + config.value);
	scope.configValue = angular.fromJson(config.value);

	/** Saves config into database */
	scope.saveConfig = function (configValue) {
		log.info("call method saveConfig inside AppParameterConfigController");
		
		// defines the success behavior inside a method
		var onSaveSuccess = function success(data) {
			console.log('success, got data: ', data);
		
			window.showAlert = function(){
				
				var userALert = alertService({
		                    title: "SUCCESS:",
		                    content: '<BR>Your store have been <i>created or updated</i>. You can find it int the result table. See <a href="#"><B>older messages</B></a> !',
		                    placement: "top-right",
		                    type: "theme",
		                    container: ".alert-container-top-right",
		                    show: !1,
		                    animation: "mat-grow-top-right"
		                    });
		    
				timeoutService(function() {
					userALert.show()
		        	}, 1 /* timeout duration */);
			};
			
			window.showAlert();
			};
		
			// defines the error behavior inside a methode
			var onSaveError = function (result) {
				window.showAlert = function(){
					var userALert = alertService({
			                    title: "ERROR:",
			                    content: "<BR>Your store haven't been created. Try again !",
			                    placement: "top-right",
			                    type: "theme",
			                    container: ".alert-container-top-right",
			                    show: !1,
			                    animation: "mat-grow-top-right"
			                    });
			    
					timeoutService(function() {
						userALert.show()
			        	}, 1 /* timeout duration */);
				};
				window.showAlert();
			};
		
		// we have to recreate a JSON valid parameter	
		config.value = angular.toJson(configValue, true);
		appParameterRestService.update(config, onSaveSuccess, onSaveError);
	};
}]);
