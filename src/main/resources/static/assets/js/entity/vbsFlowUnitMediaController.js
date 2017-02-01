//
// Source code generated by Celerio, a Jaxio product.
// Documentation: http://www.jaxio.com/documentation/celerio/
// Follow us on twitter: @jaxiosoft
// Need commercial support ? Contact us: info@jaxio.com
// Template pack-custom:angularjs/assets/js/entity/EntityController.e.vm.js
//


app.controller("VbsFlowUnitMediaController", ["$scope", "$window", "$aside", "PlaceholderTextService", 
"$log", "VbsFlowUnitMediaRestService", "VbsFlowUnitMediaRestSearchService", 
		"VbsFlowUnitMediaRestIndexService", "VbsFlowUnitMediaRestMassDeleteService",
					"$alert", "$timeout", "config", function(scope, window, c, d, log, 
		vbsFlowUnitMediaRestService, vbsFlowUnitMediaRestSearchService, vbsFlowUnitMediaRestIndexService, 
		vbsFlowUnitMediaRestMassDeleteService, 
					alertService, timeoutService, config) {

log.info("inside VbsFlowUnitMediaController, config.value: " + config.value);
scope.configValue = angular.fromJson(config.value);
		
scope.settings = {
		singular: "Item",
		plural: "Items",
		cmd: "Add"
};

// pagination variables
scope.pagination = {};
scope.totalElementsPerPage = 20;
scope.busy = false;

// saved search
scope.savedSearch = {};

// checkbox in the grid header
scope.selectAll = false;

// data to fill the grid
scope.data = [];

/** Refresh the result grid via a REST call, gets only the first page */
scope.refresh = function () {
	log.info("call method refresh inside VbsFlowUnitMediaController");
	vbsFlowUnitMediaRestService.query({page: 0, size: scope.totalElementsPerPage}, function(result) {
		log.info("receiving info from server side");
		
		log.info("result: " + result);
		scope.data = result.content;
		log.info("data post refresh:" + scope.data.length);
	});
	
	scope.selectAll = false;
};

/** Gets data page per page */
scope.refreshByPage = function (page, size, addMode) {
	log.info("call method refreshByPage inside VbsFlowUnitMediaController");
	vbsFlowUnitMediaRestService.query({page: page, size: size}, function(result) {
		log.info("receiving info from server side in page mode");
		
		log.info("result: " + result);
		if (addMode) {
			for (var i = 0; i < result.content.length; i++) {
				scope.data.push(result.content[i]);
			}
		} else {
			scope.data = result.content;
		}
		
		// fill pagination variables
		scope.pagination.first = result.first;
		scope.pagination.last = result.last;
		scope.pagination.totalElements = result.totalElements;
		scope.pagination.totalPages = result.totalPages;
		scope.pagination.number = result.number;
		
		log.info("data post refresh:" + scope.data.length);
		log.info("page number: " + scope.pagination.number);
		scope.busy = false;
	});
	
	scope.selectAll = false;
};

/** Executes the search with criteria on the server side */
scope.startSearch = function(item) {
	log.info("startSearch, criteria: " + scope.item);

	// call search on the server side and refresh the grid
	vbsFlowUnitMediaRestService.search(item, function success(result){
		log.info("receiving info from server side");
		
		// refresh data and so the grid
		scope.data = result.content;
		
		// fill pagination variables
		scope.pagination.first = result.first;
		scope.pagination.last = result.last;
		scope.pagination.totalElements = result.totalElements;
		scope.pagination.totalPages = result.totalPages;
		scope.pagination.number = result.number;
		
		log.info("data post refresh:" + scope.data.length);
		log.info("page number: " + scope.pagination.number);
	});
	
	// close the search aside
	hideForm(searchAside);
};

/** Gets first page */
scope.first = function () {
	log.info("call method first inside VbsFlowUnitMediaController for page: 0");
	scope.refreshByPage(0, scope.totalElementsPerPage, false);		
}; 

/** Gets previous page */
scope.prev = function () {
	log.info("call method prev inside VbsFlowUnitMediaController for page: " + (scope.pagination.number - 1));
	scope.refreshByPage(scope.pagination.number - 1, scope.totalElementsPerPage, false);		
}; 

/** Gets next page */
scope.next = function () {
	log.info("call method next inside VbsFlowUnitMediaController for page: " + (scope.pagination.number + 1));
	scope.refreshByPage(scope.pagination.number + 1, scope.totalElementsPerPage, false);		
}; 

/** Gets last page */
scope.last = function () {
	log.info("call method last inside VbsFlowUnitMediaController for page: " + (scope.pagination.totalPages - 1));
	scope.refreshByPage(scope.pagination.totalPages - 1, scope.totalElementsPerPage, false);		
};

			
/** Clear the result grid */
scope.clear = function () {
	log.info("call method clear inside VbsFlowUnitMediaController");
	log.info(scope.data.length);
	scope.data = [];
};

/** clear saved search */
scope.clearSavedSearch = function () {
	log.info("call method clearSavedSearch inside VbsFlowUnitMediaController");
	scope.savedSearch = {};
};

/** Shows search aside */
scope.searchItem = function() {
	log.info("searchItem: ");
	var b = {
			icon: d.createIcon(!0),
			editing: !0
	};
	scope.item = b, scope.settings.cmd = "Search", scope.item.title='todo';
	
	showForm(searchAside);
};

/** Executes the Elastic search on the server side */
scope.startElasticSearch = function(item) {
	// get criteria
	var query = scope.item.query;
	log.info("startElasticSearch: " + query);
	
	scope.data = [];
	
	// call search on the server side via REST
	vbsFlowUnitMediaRestSearchService.query({query: query}, function success(result){
		log.info("receiving info from server side");
		
		// refresh data and so the grid
		scope.data = result;
		log.info("data post refresh:" + result);
	});
	
	// close the search aside
	hideForm(searchAside);
};

/** Loads only one item with its ID */
scope.loadOneItem = function(id) {
	vbsFlowUnitMediaRestService.get({id: id}, function success(result) {
		scope.item = result;

		log.info("item loaded: " + result);
	});
};

/* fill the result grid by default (first page only) */
scope.refreshByPage(0, scope.totalElementsPerPage);

/** defines the CRUD aside */
var crudAside = c({
	scope: scope,
	template: "assets/tpl/apps/crud-vbsFlowUnitMedia.html",
	show: !1,
	placement: "left",
	backdrop: !1,
	animation: "am-slide-left"
	});

/** defines the search aside */
var searchAside = c({
	scope: scope,
	template: "assets/tpl/apps/vbsFlowUnitMedia/vbsFlowUnitMediaSearch.html",
	show: !1,
	placement: "left",
	backdrop: !1,
	animation: "am-slide-left"
	});

/** Permet de vider le tableau des éléments */
scope.clearAll = function() {
	scope.data = [];
};

scope.checkBoxValue = function() {
	log.info(scope.selectAll);
};

/** Selects all items */
scope.checkAll = function() {
	log.info("function checkAll called");
	angular.forEach(scope.data, function(scope) {
		scope.selected = !scope.selected
	});
	log.info(scope.selectAll);
	scope.selectAll = !scope.selectAll;
	log.info(scope.selectAll);
};

/** Opens and fills the CRUD aside with an item in EDIT mode */
scope.editItem = function(item) {
	//scope.loadOneItem(item.id);
	vbsFlowUnitMediaRestService.get({id: item.id}, function success(result) {
		scope.item = result;
		
		item && (item.editing = !0, scope.item = item, scope.settings.cmd = "Edit", showForm(crudAside));
		
		log.info("item loaded: " + result);
	});
};

/** Opens and fills the CRUD aside with an item in VIEW mode */
/* deprecated
scope.viewItem = function(b) {
	scope.loadOneItem(b.id);
	
	b && (b.editing = !1, scope.item = b, scope.settings.cmd = "View", showForm(crudAside))
};
*/

/** Opens the CRUD aside in CREATION mode */
scope.createItem = function() {
	var b = {
			icon: d.createIcon(!0),
			editing: !0
	};
	scope.item = b, scope.settings.cmd = "New";
	showForm(crudAside);
};
	
/** Creates or updates an item */
scope.saveItem = function() {
	log.info("Creating or updating an item");
	
	// defines the success behavior inside a methode
	var onSaveSuccess = function success(data) {
		console.log('success, got data: ', data);
	
		"New" == scope.settings.cmd, hideForm(crudAside);
	
		scope.refresh();
	
		window.showAlert = function(){
			
			var userALert = alertService({
	                    title: "SUCCESS:",
	                    content: '<BR>Your vbsFlowUnitMedia have been <i>created or updated</i>. You can find it int the result table. See <a href="#"><B>older messages</B></a> !',
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
		                    content: "<BR>Your vbsFlowUnitMedia haven't been created. Try again !",
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
		vbsFlowUnitMediaRestService.update(scope.item, onSaveSuccess, onSaveError);
	} else {
		// creation mode
		vbsFlowUnitMediaRestService.save(scope.item, onSaveSuccess, onSaveError);
	}
};

/** Removes one item or a list of items (selected ones) */
scope.remove = function(b) {
	console.log(scope.selectAll);
	var r = confirm("Are you sure ?");
	if (r == true) {/*
		(b ? scope.data.splice(scope.data.indexOf(b), 1) : (scope.data = scope.data.filter(function(a) {
		return !a.selected
		}), scope.selectAll = !1)); 
	 	*/
		if (!b) {
			// mass deletion mode
			var ids = "";
			// we browse all items in the table
			for	(index = 0; index < scope.data.length; index++) {
				if (scope.data[index].selected) {
					// we find a selected item
					console.log(index + ' ' + scope.data[index].title);
					
					if (ids.length == 0) {
						// we add the first item, so without the comma 
						ids = scope.data[index].id;
					} else {
						// another item, so with a separating comma
						ids = ids + "," + scope.data[index].id;
					}
				} 
			} 

			vbsFlowUnitMediaRestMassDeleteService.massDelete({id: ids}, function success(data) {
				scope.refresh();
				
				window.showAlert = function(){
				    var userALert = alertService({
				                    title: "SUCCESS:",
				                    content: '<BR>Your vbsFlowUnitMedia have been deleted. The result table have been <b>updated</b>.',
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
			}, function failure(err) {
				alert('request failed');
			});

		} else {
			// one item deletion mode
			vbsFlowUnitMediaRestService.delete({id: b.id}, function success(data) {
				scope.refresh();
			}, function failure(err) {
				alert('request failed');
			});
		}	

	// uncheck the selectAll checkbox in the grid header
	scope.selectAll = false;
	}
};

/* Get data in csv format for download */
scope.getCSVData = function() {
	return scope.data;
};

/** Indexes all items on the server side */
scope.index = function() {
	vbsFlowUnitMediaRestIndexService.index();
};

/** Opens an aside */
showForm = function(aside) {
	angular.element(".tooltip").remove();
	aside.show();
};

/** Closes an aside */
hideForm = function(aside) {
	aside.hide()
};

/** Closes all asides */
scope.$on("$destroy", function() {
	hideForm(crudAside);
	hideForm(searchAside);
	})
}]);

					
									
									
						
				/** main REST client for managing (4 CRUD calls) VbsFlowUnitMedia entity */
app.factory('VbsFlowUnitMediaRestService', function ($resource) {
	return $resource('api/vbsFlowUnitMedias/bypage/?page=:page&size=:size', {}, {
			/* sorting sample: &sort=aColumnName,desc&sort=anotherColumnName,asc */
		'query': { method: 'GET', isArray: false},
		'get': {
			method: 'GET',
			url: 'api/vbsFlowUnitMedias/:id',
			transformResponse: function (data) {
				data = angular.fromJson(data);
				return data;
			}
		},
		'create': { method:'POST', url: 'api/vbsFlowUnitMedias/:id'},
		'update': { method:'PUT', url: 'api/vbsFlowUnitMedias/:id'},
		'delete': { method:'DELETE', url: 'api/vbsFlowUnitMedias/:id' },
		'search': { method: 'POST', url: 'api/vbsFlowUnitMedias/search/', isArray: false}
	});
});

/** REST client for managing Elastic search calls on VbsFlowUnitMedia entity */
app.factory('VbsFlowUnitMediaRestSearchService', function ($resource) {
	return $resource('api/vbsFlowUnitMedias/esearch/:query', {}, {
		'query': { method: 'GET', isArray: true}
	});
});

/** REST client for indexing VbsFlowUnitMedia entity */
app.factory('VbsFlowUnitMediaRestIndexService', function ($resource) {
	return $resource('api/vbsFlowUnitMedias/indexAll', {}, {
		'index': { method: 'GET'}
	});
});

/** REST client for managing mass deletion calls on VbsFlowUnitMedia entity */
app.factory('VbsFlowUnitMediaRestMassDeleteService', function ($resource) {
	return $resource('api/vbsFlowUnitMedias/mass/:id', {}, {
		'massDelete': { method: 'DELETE'}
	});
});



/** REST client for managing inverse relation */
app.factory('VbsFlowUnitMediaRestInvRelationService', function ($resource) {
	return $resource('api/void/:id', {}, {
		'void': { method: 'GET'} /* dummy method, to be sure to have at least one */

                             	});
});
