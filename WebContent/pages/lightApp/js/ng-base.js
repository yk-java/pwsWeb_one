var ngScopeTemp;
simpleController=function(_setting){
	
	var moduleName="moduleName";
	var controllerName="controllerName";
	
	// setting
	if(_setting.moduleName){
		moduleName=_setting.moduleName;
	}
	if(_setting.controllerName){
		controllerName=_setting.controllerName;
	}
	// setting END
	
	var app = angular.module(moduleName, []);
	app.controller(controllerName, function($scope, $http){
		app.ngScope = $scope;
		$scope.userData;
		$scope.ticket;
		$scope.currentPage=1;
		$scope.viewState={};
		
		var serviceBasePath = "";
		if(sessionStorage.userToken && sessionStorage.serviceBasePath){
			$scope.userData = JSON.parse(sessionStorage.userToken);// from Javascript Bridge
			$scope.ticket=$scope.userData.ticket;
			serviceBasePath = sessionStorage.serviceBasePath;
		}
		$scope.appLoadComplate=function(){
			$scope.userData = JSON.parse(sessionStorage.userToken);// from Javascript Bridge
			$scope.ticket=$scope.userData.ticket;
			serviceBasePath = sessionStorage.serviceBasePath;
			_setting.complate($scope, $http);
		};
		
		// init view state
		if(_setting.viewStateInit){
			_setting.viewStateInit($scope, $scope.viewState);
			// params restore
			var urlParams = getUrlParams();
			if(urlParams.viewState){
				var viewStateStr = $.base64.atob(urlParams.viewState);
				var viewStateParams = urlParamsToObj(viewStateStr);
				proCopyRelyToObj(viewStateParams, $scope.viewState);
				if($scope.viewState.currentPage){
					$scope.currentPage = $scope.viewState.currentPage; 
				}
			}
		}
		
		
		// http get
		// url,params,success,pageChange
		$scope.httpGet = function(_conf){
			var currentPage = $scope.currentPage;
			if(_conf.currentPage){
				currentPage=_conf.currentPage;
			}
			var commonParams = "currentPage="+currentPage+"&ticket="+$scope.ticket;
			var params = "";
			if(_conf.data){
				params = toUrlParams(_conf.data);
			}
			if(_conf.url.indexOf("?")==-1){
				if(params.length>0){
					params="?"+params;
					commonParams="&"+commonParams;
				}else{
					commonParams="?"+commonParams;
				}
			}else{
				params="&"+params;
				commonParams="&"+commonParams;
			}
			$http.get(serviceBasePath+_conf.url+params+commonParams).success(function(response){
					if(response.statusCode==401){
						new ShowDlog().prompt({
							icon:"warning",
							msg:"用户登录过期！请重新登录！",
							done:function(){
								window.top.location.href=serviceBasePath+"pages/login.html";
							},
							t:2800
						});
						return;
					}
					_conf.success(response);
					if(_conf.pageChange){
						var showPageInfo=true;
						if(_conf.showPageInfo==false){
							showPageInfo=false;
						}
						$scope.createPageBar(response.totalPage, response.totalRecord, response.currentPage, _conf.pageChange, _conf.btnCount, showPageInfo);
					}
				}).error(function(response){
					new ShowDlog().confirm({
						icon:"error",
						msg:"网络连接失败！",
						ok:function(){
						}
					});
				});
		};
		$scope.httpPost = function(_conf){
			var currentPage = $scope.currentPage;
			if(_conf.currentPage){
				currentPage=_conf.currentPage;
			}
			var commonParams = "currentPage="+currentPage+"&ticket="+$scope.ticket;
			
			if(_conf.url.indexOf("?")==-1){
				commonParams="?"+commonParams;
			}else{
				commonParams="&"+commonParams;
			}
			$http.post(serviceBasePath+_conf.url+commonParams, _conf.data, {
				headers:{"Content-Type":"application/x-www-form-urlencoded; charset=UTF-8"},
				transformRequest:function(data){
					return $.param(data);
				}
			}).success(function(response, status, headers, config){
					if(response.statusCode==401){
						new ShowDlog().prompt({
							icon:"warning",
							msg:"用户登录过期！请重新登录！",
							done:function(){
								window.top.location.href=serviceBasePath+"pages/login.html";
							},
							t:2800
						});
						return;
					}
					_conf.success(response, status, headers, config);
					if(_conf.pageChange){
						var showPageInfo=true;
						if(_conf.showPageInfo==false){
							showPageInfo=false;
						}
						$scope.createPageBar(response.totalPage, response.totalRecord, response.currentPage, _conf.pageChange, _conf.btnCount, showPageInfo);
					}
				}).error(function(response, status, headers, config){
					
					new ShowDlog().confirm({
						icon:"error",
						msg:"网络连接失败！",
						ok:function(){
						}
					});
				});;
		};
		$scope.httpExecute = function(_conf){
			var currentPage = $scope.currentPage;
			if(_conf.currentPage){
				currentPage=_conf.currentPage;
			}
			var commonParams = "currentPage="+currentPage+"&ticket="+$scope.ticket;
			var params = "";
			if(_conf.url.indexOf("?")==-1){
				if(params.length>0){
					params="?"+params;
					commonParams="&"+commonParams;
				}else{
					commonParams="?"+commonParams;
				}
			}else{
				params="&"+params;
				commonParams="&"+commonParams;
			}
			$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

			  /**
			   * The workhorse; converts an object to x-www-form-urlencoded serialization.
			   * @param {Object} obj
			   * @return {String}
			   */ 
			  var param = function(obj) {
			    var query = '', name, value, fullSubName, subName, subValue, innerObj, i;
			      
			    for(name in obj) {
			      value = obj[name];
			        
			      if(value instanceof Array) {
			        for(i=0; i<value.length; ++i) {
			          subValue = value[i];
			          fullSubName = name + '[' + i + ']';
			          innerObj = {};
			          innerObj[fullSubName] = subValue;
			          query += param(innerObj) + '&';
			        }
			      }
			      else if(value instanceof Object) {
			        for(subName in value) {
			          subValue = value[subName];
			          fullSubName = name + '[' + subName + ']';
			          innerObj = {};
			          innerObj[fullSubName] = subValue;
			          query += param(innerObj) + '&';
			        }
			      }
			      else if(value !== undefined && value !== null)
			        query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
			    }
			      
			    return query.length ? query.substr(0, query.length - 1) : query;
			  };

			  // Override $http service's default transformRequest
			  $http.defaults.transformRequest = [function(data) {
			    return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
			  }];
			  
			var p = null;
			if(_conf.method.toUpperCase()=="POST"){
				p = $http({
				  method: _conf.method,
				  url: serviceBasePath+_conf.url+commonParams,
				  data: _conf.data
				});
			}else if(_conf.method.toUpperCase()=="GET"){
				p = $http({
				  method: _conf.method,
				  url: serviceBasePath+_conf.url+commonParams,
				  params: _conf.data
				});
			}
			p.success(function(response, status, headers, config){
				if(response.statusCode==401){
					new ShowDlog().prompt({
						icon:"warning",
						msg:"用户登录过期！请重新登录！",
						done:function(){
							window.top.location.href=serviceBasePath+"pages/login.html";
						},
						t:2800
					});
					return;
				}
				_conf.success(response);
				if(_conf.pageChange){
					var showPageInfo=true;
					if(_conf.showPageInfo==false){
						showPageInfo=false;
					}
					$scope.createPageBar(response.totalPage, response.totalRecord, response.currentPage, _conf.pageChange, _conf.btnCount, showPageInfo);
				}
			}).error(function(response){
				new ShowDlog().confirm({
					icon:"error",
					msg:"网络连接失败！",
					ok:function(){
					}
				});
			});;

		};
		
		// page
		$scope.createPageBar = function(totalPage, totalRecord, curPage, pageFn, btnCount, showPageInfo){
			if(!totalRecord){
				totalRecord=0;
			}
			new PageBar().init({
				renderTo:"#page_bar",
				totalPage:totalPage,
				totalRecord:totalRecord,
				showPageInfo:showPageInfo,
				curPage:curPage,
				btnCount:btnCount,
				prevPageText:"上一页",
				nextPageText:"下一页",
				onPage:function(pageNum){
					$scope.currentPage=pageNum;
					pageFn(pageNum);
				}
			});
		};
		$scope.to=function(setting){
			var url = setting.url;
			var paramsStr = toUrlParams(setting.data);
			var viewStateParams="";
			if($.base64){
				var viewStateStr = $.base64.btoa(toUrlParams($scope.viewState));
				viewStateParams="viewState="+viewStateStr;
			}
			if(url.indexOf("?")==-1){
				url+="?";
				url+=paramsStr+"&"+viewStateParams;
			}else{
				url+="&"+paramsStr+"&"+viewStateParams;
			}
			location.href=url;
		};
		_setting.init($scope, $http, $scope.viewState);
		ngScopeTemp = $scope;
	});
	return app;
};
function callNgFn(_setting){
	var scope = angular.element(_setting.ctrlEl).scope();
	scope.$apply(function(){
		_setting.ngFn(scope);
	});
}
function appLoadComplate(){
	ngScopeTemp.appLoadComplate();
}