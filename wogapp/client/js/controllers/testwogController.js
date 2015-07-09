app.controller('WoGCtrl', ['$scope', 'wogService',
	function($scope, wogService){
		//var WoG = $resource('/api/books');
		$scope.sText='';
		$scope.dspBook='';
		$scope.dspChapter='';
		$scope.selBook=null;

		$scope.onchgbook =function(){
			/*
			var cur3=$resource('/api/books/:book');
			cur3.query({'book':$scope.selBook}, function(data){
				$scope.books=data;
			});
			*/
			wogService.getBookByName($scope.selBook).then(
					function(data){
						$scope.books=data;
			});

		}

		/*
		WoG.query(function(results){
				$scope.books=results;
		});

		var cur1=$resource('/api/books/:book');

		cur1.query({'book':'genesis'}, function(data){
			$scope.wogbook=data;
		});

		var cur2=$resource('/api/books/col/book');

		cur2.query(function(results){
			$scope.bookarr = results;
		});
		*/

		wogService.getAllBooks().then(
				function(data){
					$scope.books=data;
		});
		wogService.getBookByName($scope.selBook).then(
				function(data){
					$scope.books=data;
		});
		wogService.getAllDistinctBooks().then(
				function(data){
					$scope.bookarr=data;
		});

}]);