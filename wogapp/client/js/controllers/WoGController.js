app.controller('WoGCtrl', ['$scope', '$rootScope','$filter','wogService',
	function($scope, $rootScope, $filter, wogService){
		$scope.sText='';
		$scope.dspBook='';
		$scope.dspChapter='';
		$scope.selBook=null;
		$scope.selChap=null;
		$scope.indBook=false;
		$scope.indChap=false;
		$scope.showLoad=true;
		$rootScope.hghLoad=false;
		$scope.clock ;
		console.log('inside WogCtrl...');
		$scope.fil4Search = function(){
			$scope.books = $filter('searchMatches')($scope.books,$scope.sText);
		}

		/*
		$scope.$watch('sText', function(newVal, oldVal){
			console.log('inside watch(sText) :'+ newVal);
			if(newVal !== oldVal){
				setTimeout(function(){
					$scope.$apply(function(){
						$scope.books = $filter('searchMatches')($scope.books,newVal);
						$scope.showLoad=false;
					});
				}, 1000)
			}
		});
		*/

		$scope.onchgbook =function(){
			$scope.showLoad=true;
			console.log('inside onchgbook()');
			wogService.getBookByName($scope.selBook).then(
					function(data){
						$scope.showLoad = false;
						$scope.books = data;
						console.log('Returning from getBookByName()');

			});

			wogService.getChapsByBook($scope.selBook).then(
					function(data){
						$scope.chapsarr=data;
						$scope.showLoad=false;
						console.log('Returning from getChapsByBook()'+ data);
			});

		}

		$scope.onchgchap =function(){
			//$scope.showLoad=true;
			wogService.getBookByNameAndChap($scope.selBook, $scope.selChap).then(
					function(data){
						$scope.books=data;

						console.log('Returning from getBookByNameAndChap()');

			});
		}

		wogService.getAllBooks().then(
				function(data){
					$scope.books=data;
					$scope.showLoad=false;
					console.log('Returning from getAllBooks()');
		});
		/*
		wogService.getBookByName($scope.selBook).then(

				function(data){
					$scope.books=data;
					$scope.showLoad=false;
					console.log('::Returning from getBookByName()');
		});
		*/
		wogService.getAllDistinctBooks().then(
				function(data){
					$scope.bookarr=data;
					console.log('Returning from getAllDistinctBooks()');
		});

}]);