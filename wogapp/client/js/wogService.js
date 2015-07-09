app.factory('wogService',function($resource,$q){

	return {
		getAllBooks : function(){
				var cur = $resource('/api/books');
				var deferred = $q.defer();
				console.log('inside getAllBooks()');
				cur.query(function(results){
					deferred.resolve(results);
				});
				return deferred.promise;
		},

		getBookByName : function(book){
			var cur1=$resource('/api/books/:book');
				var deferred = $q.defer();
				var bk = (book == null || book == '') ? '' : book.book;
				console.log('inside getBookByName()'+bk);
				cur1.query({'book': bk}, function(results){
					console.log('got data in getBookByName()');
					deferred.resolve(results);
				});
				return deferred.promise;
		},

		getAllDistinctBooks : function(){
			var cur2=$resource('/api/books/col/book');
			var deferred = $q.defer();
			console.log('inside getAllDistinctBooks()');
			cur2.query(function(results){
					deferred.resolve(results);
			});
			return deferred.promise;
		},


		getChapsByBook : function(book){
			var cur3=$resource('/api/books/chaps/:book');
			var deferred = $q.defer();
			console.log('inside getChapsByBook()');
			var bk = (book == null || book == '') ? '' : book.book;
			cur3.query({'book': bk}, function(data){
					deferred.resolve(data);
			});
			return deferred.promise;

		},

		getBookByNameAndChap : function(book,chap){
			var bk = (book == null || book == '') ? '' : book.book;
			var cur4=$resource('/api/books/:book/:chapter',{'book':bk,'chapter':chap});
				var deferred = $q.defer();
				console.log('inside getBookByNameAndChap()'+chap);
				cur4.query( function(data){
					deferred.resolve(data);
				});
				return deferred.promise;
		},

	}
});