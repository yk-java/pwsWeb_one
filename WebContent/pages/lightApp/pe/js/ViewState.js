/**
 * Save view state into sessionStorage
 * author Madx 2016-11-01
 */
function ViewState(storageKey){
	if(!storageKey){
		storageKey = $("body").attr("storagekey");
	}
	this.storageKey=storageKey;
	this.data={};
	this.data = this.loadStorage(storageKey);
	this.review();
	return this;
};
ViewState.prototype.loadStorage = function(storageKey){
	if(sessionStorage){
		var storageVal = sessionStorage.getItem(storageKey);
		if(storageVal){
			return JSON.parse(storageVal);
		}
	}
	return {};
};
ViewState.prototype.saveStorage = function(){
	if(sessionStorage){
		var storageVal = JSON.stringify(this.data);
		sessionStorage.setItem(this.storageKey, storageVal);
	}
};
ViewState.prototype.put = function(name, value){
	this.data[name]=value;
	this.saveStorage();
};
ViewState.prototype.get = function(name){
	if(typeof(this.data[name])=="undefined"){
		return "";
	}else{
		return this.data[name];
	}
};
ViewState.prototype.review = function(){
	var viewState = this;
	$("*[viewstate]").each(function(){
		var name = $(this).attr("viewstate");
		var value = viewState.get(name);
		if(typeof(value)=="undefined"){
			value="";
		}
		if("text"==$(this).attr("type") || "hidden"==$(this).attr("type")){
			if(value && value.length>0)
				$(this).val(value);
		}else if("checkbox"==$(this).attr("type") || "radio"==$(this).attr("type")){
			if($(this).val()==value){
				$(this)[0].checked=true;
			}else{
				$(this)[0].checked=false;
			}
		}
	});
};
ViewState.prototype.collect = function(){
	var viewState = this;
	$("*[viewstate]").each(function(){
		var name = $(this).attr("viewstate");
		if("text"==$(this).attr("type") || "hidden"==$(this).attr("type")){
			var value = $(this).val();
			if(typeof(value)=="undefined"){
				value="";
			}
			viewState.put(name, value);
		}else if("checkbox"==$(this).attr("type") || "radio"==$(this).attr("type")){
			if($(this)[0].checked){
				var value = $(this).val();
				if(typeof(value)=="undefined"){
					value="";
				}
				viewState.put(name, value);
			}
		}
		
	});
};
//var viewState;
//$(document).ready(function(){
//	viewState=new ViewState();
//});