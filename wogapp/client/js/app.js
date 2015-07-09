var app= angular.module('wogApp',['ngResource','ngRoute','angularSpinner']);

app.config(config);

config.$inject=['$routeProvider', '$locationProvider'];

function config($routeProvider, $locationProvider){

		$routeProvider
			.when('/api/books',
					{
						controller:'WoGCtrl',
						templateUrl:'views/home.html',
					})
			.when('/api/books/:book',
					{
						controller:'WoGCtrl',
						templateUrl:'views/home.html',
				})
			.when('/api/books/col/book',
					{
						controller:'WoGCtrl',
						templateUrl:'views/home.html',
			})

			.when('/api/books/chaps/:book',
					{
						controller:'WoGCtrl',
						templateUrl:'views/home.html',
			})

			.when('/api/books/:book/:chapter',
					{
						controller:'WoGCtrl',
						templateUrl:'views/home.html',
			})
			.otherwise({redirectTo:'/'});

	$locationProvider.html5Mode(true);
}

app.filter('trimspace', function(){
		return function(val){
			//console.log(val);
			return (!val)?	'': val.replace(/^\s+|\s+$/g,'');
		}
});

/*
app.filter('highlight', function($sce){
	return function(text,phrase){
			if(phrase){
				text= text.replace(new RegExp('('+phrase+')','gi'),'<span class="highlighted">$1</span>');
			}
			return $sce.trustAsHtml(text);
	}
});
*/
app.filter('highlight', function($sce,$rootScope){
			return function(text,phrase){
				$rootScope.hghLoad=true;
				var srchExp= new RegExp("("+phrase+")", 'gi');
				if(phrase){
					text = text.replace(srchExp, '<span class="highlighted">$1</span>');
				}
				$rootScope.hghLoad=false;
				return $sce.trustAsHtml(text);
			}
});

app.filter('searchMatches', ['$rootScope', function($rootScope){
			return function(items, searchKey){
				$rootScope.hghLoad=true;
				var filtered=[];
				//console.log('$rootScope.hghLoad:'+$rootScope.hghLoad);
				if(searchKey == undefined || searchKey == ''){
					filtered.push(items);
					console.log('inside searchMatches with no key');
					$rootScope.count = 0;
					$rootScope.hghLoad=false;
					//console.log('>>$rootScope.hghLoad:'+$rootScope.hghLoad);
					return items;
				}
				console.log('inside searchMatches with  key');
				var srchExp= new RegExp(searchKey, 'gi');
				var num=0;
				for(var n=0; n< items.length; n++){
					var aRec= items[n];
					var arr = aRec.text.match(srchExp);
					if(arr != null){
						//console.log("Matches:"+arr.length+" data:"+arr);
						num = num + arr.length;
						filtered.push(aRec);
					}

				}
				$rootScope.count = num;
				$rootScope.hghLoad=false;
				//console.log('>>$rootScope.hghLoad:'+$rootScope.hghLoad);
				return filtered;
			}
}]);

app.controller('clockCtrl',function($scope,$filter){
		console.log('In clockCtrl');
		setInterval(function(){
					$scope.$apply(function(){
						$scope.clock = $filter('date')(new Date(), 'dd/M/yyyy hh:m:s');
						//hghLoadconsole.log($scope.clock);
					});
		}, 1000)
});
