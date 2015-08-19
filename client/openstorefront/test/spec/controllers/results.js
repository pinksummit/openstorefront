 'use strict';

 describe('Controller: ResultsCtrl', function () {

   // load the controller's module
   beforeEach(module('openstorefrontApp'));

   var ResultsCtrl,
     scope;

   // Initialize the controller and a mock scope
   beforeEach(inject(function ($controller, $rootScope) {
     scope = $rootScope.$new();
     ResultsCtrl = $controller('ResultsCtrl', {
       $scope: scope
     });
   }));

   it('should attach a list of awesomeThings to the scope', function () {
     expect(scope._scopename).toEqual('results');
     expect(scope.orderProp).toEqual('');
     expect(scope.query).toEqual('');
     expect(scope.lastUsed.toString()).toEqual((new Date()).toString());
     expect(scope.modal).toEqual({});
     expect(scope.details).toEqual({});
     expect(scope.data).toEqual({});
     expect(scope.isPage1).toEqual(true);
     expect(scope.showSearch).toEqual(false);
     expect(scope.showDetails).toEqual(false);
     expect(scope.showMessage).toEqual(false);
     expect(scope.modal.isLanding).toEqual(false);
     expect(scope.single).toEqual(false);
     expect(scope.isArticle).toEqual(false);
     expect(scope.searchCode).toEqual(null);
     expect(scope.filteredTotal).toEqual(null);
     expect(scope.searchTitle).toEqual(null);
     expect(scope.searchDescription).toEqual(null);
     expect(scope.details.details).toEqual(null);
     expect(scope.typeahead).toEqual(null);
     expect(scope.searchGroup).toEqual(null);
     expect(scope.searchKey).toEqual(null);
     expect(scope.filters).toEqual(null);
     expect(scope.resetFilter).toEqual(null);
     expect(scope.total).toEqual(null);
     expect(scope.ratingsFilter).toEqual(0);
     expect(scope.rowsPerPage).toEqual(200);
     expect(scope.pageNumber).toEqual(1);
     expect(scope.maxPageNumber).toEqual(1);
     expect(scope.showBreadCrumbs).toEqual(false);
   });
 });
