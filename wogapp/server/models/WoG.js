var mongoose=require('mongoose');

module.exports=mongoose.model('WoG',{
	book:String,
	chapter:Number,
	verse:Number,
	text:String,
	bid:Number
},'bible');