//var serviceBasePath="http://42.96.144.54/pmsWeb/";
var serviceBasePath="http://localhost:8080/pmsWeb/";
var filePath="http://localhost:8080/pmsWeb/eap/pmsServices/fileServerClientService";
//var filePath="http://42.96.144.54:8070/pmsWeb/eap/pmsServices/fileServerClientService";

function noLogin(href){
	if(href.indexOf("mobile/pages")>-1){
		top.location.href=serviceBasePath+"mobile/pages/login.html";
	}else{
		top.location.href=serviceBasePath+"pages/login.html";
	}
}