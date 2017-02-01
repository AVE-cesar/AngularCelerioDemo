app.controller("testController", ["$scope", "$aside", "$log", "$alert", "$timeout", 
	function(scope, aside, log, alertService, timeoutService) {

		log.info("inside testController");
		
		//scope.alert = {title: 'Holy guacamole!', content: 'Best check yo self, you\'re not looking too good.', type: 'info'};
		
		/** defines the search aside */
		var searchAside = aside({
			title: 'scope',
			/*templateUrl: "tpl/search.html",*/
			show: false,
			container: 'container'
			});
		
		/** Opens an aside */
		showForm = function(aside) {		
			aside.show();
		};
		
		/** Opens an aside */
		scope.showAside = function() {	
			log.info("enter in showAside");	
			showForm(searchAside);
		};
		
		showForm(searchAside);
		
	}]); /* end of testController */