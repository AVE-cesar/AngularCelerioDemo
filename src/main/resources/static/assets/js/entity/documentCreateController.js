//
// Source code generated by Celerio, a Jaxio product.
// Documentation: http://www.jaxio.com/documentation/celerio/
// Follow us on twitter: @jaxiosoft
// Need commercial support ? Contact us: info@jaxio.com
// Template pack-custom:angularjs/assets/js/entity/EntityCreateController.e.vm.js
//

app.controller("DocumentCreateController", ["$scope", "$window", "$aside", 
"$log", "DocumentRestService", 
		"$alert", "$timeout", "mode", function(scope, window, aside, log, 
		documentRestService, 
				alertService, timeoutService, mode) {

	log.info("inside DocumentEditController, mode: " + mode);
	scope.mode = mode;
		
			
	/** Creates or updates an item */
	scope.saveItem = function() {
		log.info("Creating or updating an item");
		
		// defines the success behavior inside a methode
		var onSaveSuccess = function success(data) {
			console.log('success, got data: ', data);
		
			window.showAlert = function(){
				
				var userALert = alertService({
		                    title: "SUCCESS:",
		                    content: '<BR>Your document have been <i>created or updated</i>. You can find it in the result table. See <a href="#"><B>older messages</B></a> !',
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
			                    content: "<BR>Your document haven't been created. Try again !",
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
			documentRestService.update(scope.item, onSaveSuccess, onSaveError);
		} else {
			// creation mode
			documentRestService.create(scope.item, onSaveSuccess, onSaveError);
		}
	};

	/** Removes one item or a list of items (selected ones) */
	scope.remove = function(b) {
		console.log(scope.selectAll);
		var r = confirm("Are you sure ?");
		if (r == true) {
			if (b) {				
				// one item deletion mode
				documentRestService.delete({id: b.id}, function success(data) {
					scope.refresh();
				}, function failure(err) {
					alert('request failed');
				});
			}	
		}
	};

}]);
