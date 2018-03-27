/* 获取当前URL中的参数 */
var getUrlParams=function(url){
	var params={};
	if(!url){
		url = location.href;
	}
	var start = url.indexOf("?", 0)+1;
	var paramsStr = url.substring(start, url.length);
	var paramsArr = paramsStr.split("&");
	for(var i=0;i<paramsArr.length;i++){
		var kv = paramsArr[i].split("=");
		var k = kv[0];
		var v = kv[1];
		params[k]=decodeURIComponent(v);
	}
	return params;
};
var urlParamsToObj=function(urlParams){
	var params={};
	var paramsArr = urlParams.split("&");
	for(var i=0;i<paramsArr.length;i++){
		var kv = paramsArr[i].split("=");
		var k = kv[0];
		var v = kv[1];
		params[k]=decodeURIComponent(v);
	}
	return params;
};
/* 参数对象转换为URL字符串参数 */
var toUrlParams=function(data){
	var paramsStr="";
	for(var k in data){
		if(paramsStr.length>0){
			paramsStr+="&";
		}
		paramsStr+=k+"="+encodeURIComponent(data[k]);
	}
	return paramsStr;
};
var appendParams=function(url, data){
	for(var k in data){
		if(url.indexOf("?")==-1){
			url+="?";
		}
		if(url.indexOf("?")<url.length-1){
			url+="&";
		}
		url+=k+"="+encodeURIComponent(data[k]);
	}
	return url;
};
var serializeArrayToMap=function(serializeArray){
	var paramsMap={};
	for(var i=0; i<serializeArray.length; i++){
		var item = serializeArray[i];
		paramsMap[item.name]=item.value;
	}
	return paramsMap;
}
/* 指定位数，不足位数前面补0 */
var numFormat=function(num, place){
	var numStr=num.toString();
	if(num.toString().length<place){
		var zeroCnt = place-num.toString().length;
		for(var i=0; i<zeroCnt; i++){
			numStr="0"+numStr;
		}
	}
	return numStr;
};
/* 获取本周星期天的日期 */
var getSunday=function(date){
	var day=date.getDay();
	if(day==0){
		return date;
	}else{
		var sundayDate = new Date();
		sundayDate.setTime(date.getTime()-day*24*60*60*1000);
		return sundayDate;
	}
};
/* 获取本周星期六的日期 */
var getSaturday=function(date){
	var day=date.getDay();
	if(day==6){
		return date;
	}else{
		var saturdayDate = new Date();
		saturdayDate.setTime(date.getTime()+(6-day)*24*60*60*1000);
		return saturdayDate;
	}
};
/* 获取指定月的天数 */
var getMonthDays = function(year, month){
	if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
		return 31;
	}else if(month==4||month==6||month==9||month==11){
		return 30;
	}else{
		if(isLeapYear(year)){
			return 29;
		}else{
			return 28;
		}
	}
};
/* 是否闰年 */
var isLeapYear = function(year) {
	if (year % 4 == 0 && year % 100 != 0) {
		return true;
	} else {
		if (year % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}
};
var strEncode = function(str){
	return encodeURIComponent(str);
};
var strDecode = function(str){
	return decodeURIComponent(str);
};
//把数组转为字符串
function arrToStr(arr){
	var str="";
	for(i=0; i<arr.length; i++){
		if(i>0){
			str+=",";
		}
		str+=arr[i];
	}
	return str;
}
// 根据值删除数据元素
function arrRemove(arr, val){
	var flag=false;
	for(i=0; i<arr.length; i++){
		if(arr[i] == val){
			flag=true;
		}
		if(flag && i<arr.length-1){
			arr[i]=arr[i+1];
		}
	}
	if(flag)
		arr.pop();
}
// 通过索引删除数组元素
function removeAt(arr, index){
	for(var i=index; i<arr.length-1; i++){
		var temp = arr[i];
		arr[i]=arr[i+1];
		arr[i+1]=temp;
	}
	arr.pop();
}
// 元素是否存在
function isExist(arr, val){
	for(i=0; i<arr.length; i++){
		if(arr[i] == val){
			return true;
		}
	}
	return false;
}
/* 跳转时带上参数 */
function hrefTo(setting){
	var url = setting.url;
	var paramsStr = toUrlParams(setting.data);
	if(url.indexOf("?")==-1){
		url+="?";
		url+=paramsStr;
	}else{
		url+="&"+paramsStr;
	}
	location.href=url;
}
/* 四舍五入 */
function decim(num, len){
	if(!isNaN(num)){
		var num_temp = num.toFixed(len);
		var num_temp = num_temp.replace(/0*$/g, '').replace(/\.*$/g, '');
		return num_temp;
	}else{
		return num;
	}
}
/* 属性选择拷贝 */
function propertyChooseCopy(fromObj, toObj, fromPros, toPros){
	for(var i=0; i<fromPros.length; i++){
		var fPro=fromPros[i];
		var tPro=fPro;
		if(toPros){
			tPro = toPros[i];
		}
		if(fromObj[fPro]){
			toObj[tPro]=fromObj[fPro];
		}
	}
}

function proCopyRelyToObj(fromObj, toObj){
	for(var pro in toObj){
		if(fromObj[pro]){
			toObj[pro]=fromObj[pro];
		}
	}
}

function proCopyRelyFromObj(fromObj, toObj){
	for(var pro in fromObj){
		if(fromObj[pro]){
			toObj[pro]=fromObj[pro];
		}
	}
}
/* 合并属性 */
function propertyMerge(fromObj, toObj){
	for(var pro in fromObj){
		toObj[pro]=fromObj[pro];
	}
}
/* 延时调用 */
var lazyCallTimers=[];
function lazyCall(timerId, fn, t){
	if(lazyCallTimers[timerId]){
		fn();
		clearTimeout(lazyCallTimers[timerId]);
		lazyCallTimers[timerId]=null;
	}else{
		lazyCallTimers[timerId] = setTimeout(function(){lazyCall(timerId, fn, t)}, t);
	}
}
/* 创建表单 */
function createForm(_args){
	var formEl = document.createElement("form");
	formEl.method=_args.method;
	formEl.action=_args.action;
	for(var name in _args.data){
		var value = _args.data[name];
		createInput(formEl, name, value);
	}
	return formEl;
}
function createInput(formEl, name, value){
	var inputEl = document.createElement("input");
	inputEl.type="hidden";
	inputEl.name=name;
	inputEl.value=value;
	formEl.appendChild(inputEl);
}
function undeToEmp(val){
	if(typeof(val)=="undefined"){
		val="";
	}
	return val;
}