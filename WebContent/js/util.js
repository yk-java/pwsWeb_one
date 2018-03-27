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
var toUrlParams=function(params){
	var paramsStr="";
	for(var k in params){
		if(paramsStr.length>0){
			paramsStr+="&";
		}
		paramsStr+=k+"="+encodeURIComponent(params[k]);
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
	do{
		flag=false;
		for(i=0; i<arr.length; i++){
			if(arr[i] === val){
				flag=true;
			}
			if(flag && i<arr.length-1){
				arr[i]=arr[i+1];
			}
		}
		if(flag)
			arr.pop();
	}while(flag);
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
function leftMove(arr, index){
	if(!arr || index<=0)
		return;
	var temp = arr[index-1];
	arr[index-1]=arr[index];
	arr[index]=temp;
}
function rightMove(arr, index){
	if(!arr || index>=arr.length-1)
		return;
	var temp = arr[index];
	arr[index]=arr[index+1];
	arr[index+1]=temp;
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


//将数值四舍五入(保留2位小数)后格式化成金额形式 
function formatCurrency(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
        num = "0";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*100+0.50000000001);  
    cents = num%100;  
    num = Math.floor(num/100).toString();  
    if(cents<10)  
    cents = "0" + cents;  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num + '.' + cents);  
}  

//将数值四舍五入(保留1位小数)后格式化成金额形式 
function formatCurrencyTenThou(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
    num = "0";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*10+0.50000000001);  
    cents = num%10;  
    num = Math.floor(num/10).toString();  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num + '.' + cents);  
}  


function DX(source,dest) {
	 var num=$(source).val();
	  var strOutput = "";
	  var strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';
	  num += "00";
	  var intPos = num.indexOf('.');
	  if (intPos >= 0)
	    num = num.substring(0, intPos) + num.substr(intPos + 1, 2);
	  strUnit = strUnit.substr(strUnit.length - num.length);
	  for (var i=0; i < num.length; i++)
	    strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(num.substr(i,1),1) + strUnit.substr(i,1);
	  var result= strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");
	  $("#"+dest).val(result);
	};
	

	/* 
	 * 行合并数据格式化
	 * setting: {data:[], params:[{name:"proNo_rowspan", fields:["proNo"]},{name:"name_rowspan", fields:["proNo","workDate","worker"]}]}
	 * 数据中要合并的元素会加上params[n].name为属性的值
	 */
	var rowspanFormat=function(setting){
		// 用于计算合并行数
		var count=function(params){
			var count=0;
			for(var i=0; i<setting.data.length; i++){
				var item=setting.data[i];
				var flag=false;
				for(var k in params){
					if(item[k]!=params[k]){
						flag=true;
						break;
					}
				}
				if(!flag){
					count++;
				}
			}
			return count;
		};
		// 用于判断是否为新的合并行
		var isFirst=function(params, key){
			var isNotFirst=false;
			for(var i=0; i<setting.data.length; i++){
				var item=setting.data[i];
				var flag=false;
				for(var k in params){
					if(item[k]!=params[k]){
						flag=true;
						break;
					}
				}
				if(!flag){
					if(item[key]){
						isNotFirst=true;
					}
				}
			}
			if(!isNotFirst){
				return true;
			}
		};
		// 处理数据
		for(var i=0; i<setting.data.length; i++){
			var item=setting.data[i];
			for(var j in setting.params){
				var param = setting.params[j];
				var conds = {};
				for(var k in param.fields){
					var field = param.fields[k];
					conds[field]=item[field];
				}
				if(isFirst(conds, param.name)){
					item[param.name]=count(conds);
				}
			}
		}
	};