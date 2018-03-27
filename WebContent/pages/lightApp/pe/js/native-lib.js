/**
 * 轻应用本地支持接口库
 * @author MaDx
 */
var nativeLib = new (function(){
	var that = this;
	
	/* 初始化 */
	function init(){
		if(!sessionStorage.serviceBasePath){
			(function(callback) {
			    if (window.WebViewJavascriptBridge) {
			        callback(window.WebViewJavascriptBridge);
			    } else {
			        document.addEventListener(
			            'WebViewJavascriptBridgeReady'
			            , function() {
			                callback(window.WebViewJavascriptBridge)
			            },
			            false
			        );
			    }
			})(function(bridge) {
			    bridge.init(function(message, responseCallback) {
			    	sessionStorage.serviceBasePath = JSON.parse(message).appServiceUrl;
			    	that.getUserToken();
			    });
			});
		}else{
			lazyCall('t1',function(){
				if(appLoadComplate)
					appLoadComplate();
			}, 100);
		}
	}
	
	/**
	 * 获取Token
	 */ 
	this.getUserToken = function() {
		if(window.WebViewJavascriptBridge)
			window.WebViewJavascriptBridge.callHandler(
		        'getUserToken',
		        null,
		        function(responseData) {
		        	sessionStorage.userToken=responseData;
		    		$.cookie("pmsWeb_user_data", responseData, {"path":"/"});
		    		var userData = JSON.parse(sessionStorage.userToken);
		    		$.cookie("pmsWeb_ticket", userData.ticket, {"path":"/"});
		    		if(appLoadComplate)
		    			appLoadComplate();
		        }
		    );
	};
	
	/**
	 * 退出轻应用
	 */ 
	this.exitApp = function(){
		if(window.WebViewJavascriptBridge)
			window.WebViewJavascriptBridge.callHandler(
					'exitApp', 
					{"param":"exitApp"}, 
					function(responseData) {
						
					});
	};
	
	/** 
	 * 获取照片
	 * @param _setting	{action:"takePicture"|"picPicture", callback:Function(data)}
	 * <h3>说明</h3>
	 * <p>action 值为takePicture是使用相机拍摄照片，为picPicture是从相册中选取照片</p>
	 * <p>data 结构{statusCode:String, resultMsg:String, base64Img:String} </p>
	 */
	this.getPicture = function(_setting){
		if(window.WebViewJavascriptBridge)
			window.WebViewJavascriptBridge.callHandler(
		            'getPicture',
		            _setting.action,
		            function(responseData) {
		            	var data = JSON.parse(responseData);
		            	_setting.callback(data);
		            }
		        );
	};
	
	/**
	 * 扫码
	 * @param _setting	{callback:Function:(code)}
	 */
	this.scanCode = function(_setting) {
		if(window.WebViewJavascriptBridge)
			window.WebViewJavascriptBridge.callHandler(
		        'scanCode',
		        null,
		        function(responseData) {
		        	_setting.callback(responseData);
		        }
		    );
	};
	
	/**
	 * 根据坐标获取地址信息
	 * @param _setting	{point:{x:String, y:String}, callback:Function(data)}
	 * <h3>说明</h3>
	 * <p>data 结构{statusCode:String, resultMsg:String, data:String}</p> 
	 */
	this.getAddressName = function(_setting) {
		if(window.WebViewJavascriptBridge)
			window.WebViewJavascriptBridge.callHandler(
					"encoding",
					_setting.point,
					function(responseData) {
						var data=JSON.parse(responseData);
						_setting.callback(data);
					});
	};
	
	/**
	 * 获取位置信息
	 * @param _setting	{callback:Function(data)}
	 * <h3>说明</h3>
	 * <p>data 结构{time:, locType:, latitude:, lontitude:, radius:, speed:, satellite:, height:, direction:, addr:, describe:, operationers:,}</p> 
	 */
	this.getLocation = function(_setting){
		if(window.WebViewJavascriptBridge)
			window.WebViewJavascriptBridge.callHandler(
	                'getLocation',
	                null,
	                function(responseData) {
	                	var data = $.parseJSON(responseData);
	                	_setting.callback(data);
	                }
	            );
	};
	
	/**
	 * 监听退出按键
	 * @param _setting	{callback:Function(data)}
	 */
	this.onBack = function(_setting){
		if(window.WebViewJavascriptBridge)
			window.WebViewJavascriptBridge.callHandler(
			        'overrideBackbutton',
			        null,
			        function(responseData) {
			        	if(_setting.callback()){
			        		that.exitApp();
			        	}
			        }
			);
	};
	
	
	/* 执行初始化，写在最后 */
	init();
})();