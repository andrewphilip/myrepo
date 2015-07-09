var express=require('express'),
	app =express(),
	mongoose=require('mongoose'),
	bodyParser=require('body-parser'),
	verbumDeiCtrl=require('./server/controllers/verbumDeiCtrl.js');

var cookieParser=require('cookie-parser');
var expressSession=require('express-session');
var passport = require('passport');
var passportLocal= require('passport-local');
var passportHttp = require('passport-http');
mongoose.connect('mongodb://localhost/test');

app.use(bodyParser.urlencoded({extended:false}));
app.use(cookieParser());

app.use(expressSession({
		secret: process.env.SESSION_SECRET || 'secret',
		resave:false,
		saveUninitialized:false
		}));

app.use(passport.initialize());
app.use(passport.session());

app.use('/js',express.static(__dirname + '/client/js'));
app.use('/video',express.static(__dirname + '/client/videos'));
app.use('/images',express.static(__dirname + '/client/images'));
app.use('/styles',express.static(__dirname + '/client/styles'));
app.use('/include',express.static(__dirname + '/client/views'));
app.use('/audio',express.static(__dirname + '/client/videos'));



app.set('views',__dirname +'/client/views');
app.engine('html', require('ejs').renderFile);
app.set('view engine', 'html');
app.set('json replacer', null);
app.set('json spaces', 2);
/*
app.get('/',function(req,res){
		res.sendfile(__dirname + '/client/views/index.html');
	});
*/


passport.use(new passportLocal.Strategy(function(username,password,done){
	if(username == password){
		done(null, {id:username, name:username});
	}
	else{
		done(null, null);
	}
	//done(null, null);
	//done(new Error('Oops!'));
}));


passport.use(new passportHttp.BasicStrategy(function(username,password,done){
	if(username == password){
		done(null, {id:username, name:username});
	}
	else{
		done(null, null);
	}
}
));


passport.serializeUser(function(user, done){
	done(null, user.id);
});

passport.deserializeUser(function(id,done){
	done(null,{id:id, name: id});
});

function ensureAuth(req,res,next){
	if(req.isAuthenticated){
		next();
	}
	else{
		//res.redirect('/login');
		res.send(403);
	}
}

app.get('/', function(req,res){
	res.render('index.html',{ isAuthenticated: req.isAuthenticated(), user: req.user});
});

app.get('/login', function(req,res){
	res.render('login.html');
});

app.get('/home', ensureAuth, function(req,res){
	res.render('home.html');
});
app.get('/media/*', ensureAuth, function(req,res){
	res.render('multimedia.html');
});
app.get('/about', ensureAuth, function(req,res){
	res.render('about.html');
});

app.get('/logout', function(req,res){
	req.logout();
	res.redirect('/login');
});

app.use('/api', passport.authenticate('basic'));
app.use('/home', passport.authenticate('basic'));

//REST apis
app.get('/api/books', verbumDeiCtrl.getAllBooks);
app.get('/api/books/:book', verbumDeiCtrl.findByBook);
app.get('/api/books/col/book', verbumDeiCtrl.getBooksOnly);
app.get('/api/books/chaps/:book', verbumDeiCtrl.getChapsByBook);
app.get('/api/books/:book/:chapter', verbumDeiCtrl.findByBookAndChap);

app.post('/login', passport.authenticate('local'),function(req,res){
	res.redirect('/');
});
app.listen(3000, function(){
	console.log('Server is listening...');
	console.log('__dirname:'+__dirname);
	})