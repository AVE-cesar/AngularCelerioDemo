//
// Source code generated by Celerio, a Jaxio product.
// Documentation: http://www.jaxio.com/documentation/celerio/
// Follow us on twitter: @jaxiosoft
// Need commercial support ? Contact us: info@jaxio.com
// Template angular-lab:angularjs/static/assets/js/admin/translations.p.vm.js
//

app.config(['$translateProvider', function ($translateProvider) {
	$translateProvider.translations('en', {
	
		  'BOOK_TITLE': 'Book',
		  'BOOK_MENU': 'Book',
		  'APPAUTHORITY_TITLE': 'AppAuthority',
		  'APPAUTHORITY_MENU': 'AppAuthority',
		  'APPPARAMETER_TITLE': 'AppParameter',
		  'APPPARAMETER_MENU': 'AppParameter',
		  'APPTOKEN_TITLE': 'AppToken',
		  'APPTOKEN_MENU': 'AppToken',
		  'APPTRANSLATION_TITLE': 'AppTranslation',
		  'APPTRANSLATION_MENU': 'AppTranslation',
		  'APPUSER_TITLE': 'AppUser',
		  'APPUSER_MENU': 'AppUser',
		  'APPUSERAUTHORITY_TITLE': 'AppUserAuthority',
		  'APPUSERAUTHORITY_MENU': 'AppUserAuthority',
	
    	'ENTITY_DESCRIPTION': 'Use this page to manage book entities. You will be able to search, view, edit and delete items.',
    
      	'ID': 'Id',
      	'TITLE': 'Title',
      	'AUTHOR': 'Author',
      	'DESCRIPTION': 'Description',
      	'PRICE': 'Price',
      	'PUBLICATION_DATE': 'Publication date',
      	'NB_ITEMS_AVAILABLE': '{{nb}} {{item}} available',
      
      	// common translation
      	'ACTIONS': 'Actions',
      	'INFO': 'Info',
      	'SEARCH': 'Search',
      	'ELASTIC_SEARCH': 'Elastic search',
      	'QUERY': 'Query',
      	'REFRESH_GRID': 'Refresh the grid',
      	'CLEAR_GRID': 'Clear the grid',
      	'INDEX_ITEMS': 'Index all items',
      	'CONFIG_ENTITY': 'Configure this entity',
      	'VIEW_ITEM': 'View item',
      	'EDIT_ITEM': 'Edit item',
      	'DELETE_ITEM': 'Delete item',
      	'ADD_ITEM': 'Add an item',
      	'SAVE': 'Save',
      	'SCROLL_TO_TOP': 'Scroll to top',
      	'CLOSE': 'Close this aside',
      	'MORE_TOOLTIP': 'Click here to see more',
      	'MORE': 'More',
      	'LESS': 'Less',
      	'NO_DATA': 'There isn\'t record for this entity !',
      	
      	// menu
      	'MENU_ENTITIES': 'Entities',
      	'MENU_CONFIGURATION': 'Configuration',
      	'MENU_ADMINISTRATION': 'Administration'
  	});
 
 	$translateProvider.translations('fr', {
    	'TITLE': 'Entité livre',
    	'DESCRIPTION': 'à faire'
  	});
 
 	/** sets default language */
 	$translateProvider.fallbackLanguage('en');
 
  	$translateProvider.preferredLanguage('en');
  
  	/** Stores the language over cross http requests */
  	/**$translateProvider.useCookieStorage();*/
  
 	/** Enable escaping of HTML */
  	$translateProvider.useSanitizeValueStrategy('sanitizeParameters');
}]);