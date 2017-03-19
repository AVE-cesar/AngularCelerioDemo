//
// Source code generated by Celerio, a Jaxio product.
// Documentation: http://www.jaxio.com/documentation/celerio/
// Follow us on twitter: @jaxiosoft
// Need commercial support ? Contact us: info@jaxio.com
// Template angular-lab:angularjs/static/assets/js/entity/EntityEditController.e.vm.js
//

					
									
									
									
						
				
app.controller("AppParameterEditController", ["$scope", "$window", "$aside", 
"$log", "AppParameterRestService", 
		"AppParameterRestInvRelationService", "$alert", "$timeout", "item", "mode", function(scope, window, aside, log, 
		appParameterRestService, 
						appParameterRestInvRelationService, alertService, timeoutService, item, mode) {
	
	log.info("inside AppParameterEditController, mode: " + mode);
	log.info("inside AppParameterEditController, item: " + item);
	scope.mode = mode;
	scope.item = item;

					
         
	/** Clear item */
	scope.clear = function () {
			scope.item = null;
	};
	
	/** Creates or updates an item */
	scope.saveItem = function() {
		log.info("Creating or updating an item");
		
		// defines the success behavior inside a methode
		var onSaveSuccess = function success(data) {
			console.log('success, got data: ', data);
		
			window.showAlert = function(){
				
				var userALert = alertService({
		                    title: "SUCCESS:",
		                    content: '<BR>Your appParameter have been <i>created or updated</i>. You can find it in the result table. See <a href="#"><B>older messages</B></a> !',
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
			                    content: "<BR>Your appParameter haven't been created. Try again !",
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
		
		if (scope.item.id != null) {
			// update mode
			console.log("update mode");
						appParameterRestService.update(scope.item, onSaveSuccess, onSaveError);
		} else {
			// creation mode
			console.log("creation mode");
			appParameterRestService.save(scope.item, onSaveSuccess, onSaveError);
		}
	};

	/** Removes one item or a list of items (selected ones) */
	scope.remove = function(item) {
		
		var r = confirm("Are you sure ?");
		if (r == true) {
			if (item) {				
				// one item deletion mode
												appParameterRestService.delete({id: item.id}, function success(data) {
					scope.clear();
					alert('item deleted');
				}, function failure(err) {
					alert('request failed');
				});
			}	
		}
	};

}]);
