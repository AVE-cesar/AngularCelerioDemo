//
// Source code generated by Celerio, a Jaxio product.
// Documentation: http://www.jaxio.com/documentation/celerio/
// Follow us on twitter: @jaxiosoft
// Need commercial support ? Contact us: info@jaxio.com
// Template pack-custom:angularjs/assets/js/applicationStates.p.vm.js
//



app.config(function($stateProvider, $urlRouterProvider) {
	//
	// For any unmatched url, redirect to homepage
	$urlRouterProvider.otherwise("/home");
	//
  
  /* entities states */
  /* to go to books search screen */
  $stateProvider
        .state('book', {
	    	url: "/book",
			resolve: {
				config : ['$stateParams', 'AppParameterRestService', '$log', function($stateParams, appParameterRestService, log) {
					return appParameterRestService.getParameter({domain: 'SCREEN_CONFIG', key: 'Book'}).$promise.then (function (result) {
						if (!result.value) {
                   			// no data has been found inside the dabatase, we need to create a fresh one
							return appParameterRestService.create({"domain": "SCREEN_CONFIG", "key": "Book", "value": "{ \"id\": true, \"title\": true, \"description\": true, \"publicationDate\": true, \"authorId\": true, \"price\": true, \"barcodeid\": true}"});
						} else {
							return result;
						}
						});
                    }],
                savedSearch : function() {
      				return {title: "a", query: "test"};
    			}
                    		
				},
			views: {
            	/*"searchView": {templateUrl: "assets/tpl/apps/book/bookSearch.html",
            		controller: "BookController"},*/     
				"mainView": {templateUrl: "assets/tpl/apps/book/book.html",
					controller: "BookController"},
				"footerView": {templateUrl: "assets/tpl/commons/footer.html"}
				}
            })	            
     
        /* to go in Edit mode on a book entity */
		.state('editBook', {
			url: "/book/{id}",
			views: {
				"mainView": {
					templateUrl: "assets/tpl/apps/book/bookEdit.html",
					controller: "BookEditController"
					},
				"footerView": {templateUrl: "assets/tpl/commons/footer.html"}
				},
			resolve: {
				mode : function() {
      				return "EDIT";
    			},
				item : ['$stateParams', 'BookRestService', '$log', function($stateParams, BookRestService, log) {
					return BookRestService.get({id : $stateParams.id}, function success(result) {}, function failure(result){
						log.info("something goes wrong !");
						}).$promise;
                    }]
				}
		})   
		
		/* to go in create mode to add a new book */
		.state('createBook', {
			url: "/book/create",	    			
			views: {
				"mainView": {
					templateUrl: "assets/tpl/apps/book/bookEdit.html",
					controller: "BookCreateController"
					},
				"footerView": {templateUrl: "assets/tpl/commons/footer.html"}
				},
			resolve: {
				mode : function() {
      				return "CREATE";
    			}
			}
		})
		
		/* to go in read only mode on a book entity */
		.state('viewBook', {
			url: "/book/view/{id}",	    			
			views: {
				"mainView": {
					templateUrl: "assets/tpl/apps/book/bookEdit.html",
					controller: "BookEditController"
					},
				"footerView": {templateUrl: "assets/tpl/commons/footer.html"}
				},
			resolve: {
				mode : function() {
      				return "VIEW";
    			},
				item : ['$stateParams', 'BookRestService', '$log', function($stateParams, BookRestService, log) {
					return BookRestService.get({id : $stateParams.id}, function success(result) {}, function failure(result){
						log.info("something goes wrong !");
						}).$promise;
                    }]
				}
		});
  
  /* common states */
  $stateProvider
		.state('home', {
            url: "/",
            views: {
				"mainView": {templateUrl: "assets/tpl/commons/home.html"},
				"footerView": {templateUrl: "assets/tpl/commons/footer.html"}
				}
            })
		.state('form', {
            url: "/form",
            views: {
				"mainView": {templateUrl: "assets/tpl/commons/form.html"},
				"footerView": {templateUrl: "assets/tpl/commons/footer.html"}
				}
            })
		.state('table', {
            url: "/table",
            views: {
            	"searchView": {templateUrl: "assets/tpl/commons/search.html"},
				"mainView": {templateUrl: "assets/tpl/commons/table.html"},
				"footerView": {templateUrl: "assets/tpl/commons/emptyFooter.html"}
				}
            })
        .state('testAlert', {
            url: "/testAlert",
            views: {
				"mainView": {
					templateUrl: "assets/tpl/commons/testAlert.html",
					controller: "TestAlertController"},
				"footerView": {templateUrl: "assets/tpl/commons/emptyFooter.html"}
				}
            })
        .state('testIcon', {
            url: "/testIcon",
            views: {
				"mainView": {
					templateUrl: "assets/tpl/commons/testIcon.html"
				},
				"footerView": {templateUrl: "assets/tpl/commons/emptyFooter.html"}
				}
            })
		.state('testAside', {
            url: "/testAside",
            views: {
				"mainView": {
					templateUrl: "assets/tpl/commons/testAside.html",
					controller: "TestAsideController"
				},
				"footerView": {templateUrl: "assets/tpl/commons/emptyFooter.html"}
				}
            })
        .state('testModal', {
            url: "/tesModal",
            views: {
				"mainView": {
					templateUrl: "assets/tpl/commons/testModal.html",
					controller: "TestModalController"
				},
				"footerView": {templateUrl: "assets/tpl/commons/emptyFooter.html"}
				}
            });
  
	// Now set up the states
	$stateProvider
    	.state('dashboard', {
      		url: "/dashboard",
			views: {
				"mainView": {
					templateUrl: "assets/tpl/commons/dashboard.html"
				},
				"footerView": {templateUrl: "assets/tpl/commons/emptyFooter.html"}
				}
	});
	
	$stateProvider
    	.state('settings', {
      		url: "/settings",
			views: {
				"mainView": {
					templateUrl: "assets/tpl/commons/settings.html"
				},
				"footerView": {templateUrl: "assets/tpl/commons/emptyFooter.html"}
				}
    });
    
    $stateProvider
    	.state('logLevels', {
      		url: "/logLevels",
			views: {
				"mainView": {
					templateUrl: "assets/tpl/commons/logLevels.html"
				},
				"footerView": {templateUrl: "assets/tpl/commons/emptyFooter.html"}
				}
    });
    
    $stateProvider
    	.state('translation', {
      		url: "/translation",
			views: {
				"mainView": {
					templateUrl: "assets/tpl/apps/admin/translation.html"
				},
				"footerView": {templateUrl: "assets/tpl/commons/emptyFooter.html"}
				}
    });
    
    
    
    
    /*
     * 
     * Authentication part of the application: login, logout
     * 
     */
    
    /* to redirect users to the login page */
    $stateProvider
		.state('login', {
	  		url: "/login",
			views: {
				"mainView": {
					templateUrl: "assets/tpl/commons/login.html",
					controller : "LoginController"
				},
				"footerView": {templateUrl: "assets/tpl/commons/emptyFooter.html"}
				},
			resolve: {
				credential : function() {
	  				return {"login": "admin", "password": "admin", "error": false};
				}
			}
	});

    /* to redirect users to the logout page */
    $stateProvider
		.state('logout', {
	  		url: "/logout",
			views: {
				"mainView": {
					templateUrl: "assets/tpl/commons/logout.html",
					controller: "LogoutController"
				},
				"footerView": {templateUrl: "assets/tpl/commons/emptyFooter.html"}
				}
	});

});