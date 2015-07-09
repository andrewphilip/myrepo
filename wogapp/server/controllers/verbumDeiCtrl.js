var WoG=require('../models/WoG');

module.exports.getAllBooks=function(req,res){
	WoG.find({}, function(err, results){
			res.json(results);
		});
};

module.exports.findByBook = function(req,res){
	console.log('Book:'+req.params.book);
	var bookdata;
	WoG.find({'book':req.params.book},function(err,results){
			console.log('Got results');
			bookdata= results;
			res.json(results);
	});


};

module.exports.getBooksOnly = function(req,res){
	console.log('Booksonly');
	/*
	WoG.find().distinct('book', function(err,results){
		console.log(results);
		res.json(results);
	});
	*/

	WoG.aggregate(
		[{$group:{_id:'$bid',  book:{$first:'$book'}}},{$sort:{_id:1}}]
		,
		function(err,results){
			console.log(results);
			res.json(results);
		}
	)

}


module.exports.getChapsByBook = function(req,res){
	console.log('Book:'+req.params.book);

	WoG.find({'book':req.params.book}).distinct('chapter', function(err,results){
		console.log(results);
		res.json(results);
	});

};

module.exports.findByBookAndChap = function(req,res){
	console.log(req.params);
	console.log('Book:'+req.params.book+ " Chap: "+req.params.chapter);
	WoG.find({'book':req.params.book, 'chapter':req.params.chapter},function(err,results){
					console.log('Got results');
					//console.log(results);
					res.json(results);
	});
};



