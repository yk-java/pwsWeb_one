//var serviceBasePath="http://42.96.144.54/pmsWeb/";
var serviceBasePath="http://192.168.1.230:8080/pmsWeb/";
var filePath="http://192.168.1.230:8080/pmsWeb/eap/pmsServices/fileServerClientService";
//var filePath="http://42.96.144.54:8070/pmsWeb/eap/pmsServices/fileServerClientService";

function toIndex(href){
	if(href.indexOf("mobile/pages")>-1){
		top.location.href=serviceBasePath+"mobile/pages/login.html";
	}else{
		top.location.href=serviceBasePath+"pages/index.html";
	}
}