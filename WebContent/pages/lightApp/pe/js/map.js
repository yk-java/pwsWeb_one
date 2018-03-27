function initMap(x, y) {
			alert(x+" "+y)
			mapProxy = new BMap.Map("iCenter", {
					
				mapType: BMAP_NORMAL_MAP,enableMapClick:false
			});
			
			mapProxy.enableScrollWheelZoom();
			mapProxy.addControl(new BMap.MapTypeControl());
			var top_left_navigation = new BMap.NavigationControl();
			mapProxy.addControl(top_left_navigation);
			
			if (x && y) {
				
				var poi = new BMap.Point(x, y);
				mapProxy.centerAndZoom(poi, 16);
			}else if(areaName!="" && areaName!=undefined){
				
				mapProxy.centerAndZoom(areaName,15);
			}
			else {
				
				mapProxy.centerAndZoom("南京",11);
			}
			
			//drawTool
		    //实例化鼠标绘制工具
		    var drawingManager = new BMapLib.DrawingManager(mapProxy, {
		        isOpen: false, //是否开启绘制模式
		        enableDrawingTool: true, //是否显示工具栏
		        drawingToolOptions: {
		            anchor: BMAP_ANCHOR_BOTTOM_RIGHT, //位置
		            offset: new BMap.Size(5, 5), //偏离值
		            drawingModes: [BMAP_DRAWING_MARKER]
		        }
		    });  
			 //添加鼠标绘制工具监听事件，用于获取绘制结果
		    drawingManager.addEventListener('overlaycomplete', drawPointHandler);
		    
		}
		
		function initXY() {
			
			//var _x = "118.8028910000";
			//var _y = "32.0647350000";
			
			
			
			
			if (_x && _y) {
				
				setXY(_x, _y);
				
				drawPoint(_x, _y);
			}
		}
		
		var locations = "";
		function drawPointHandler(event) {
			
			var p = event.overlay;
			if (p) {
				var lnglat = p.getPosition();
				setXY(lnglat.lng, lnglat.lat);
				mapProxy.clearOverlays();
				locations = "locations";
				drawPoint(lnglat.lng, lnglat.lat);
				
			}
		}
		
		function drawPoint(x, y) {
			var point = new BMap.Point(Number(x), Number(y));
			if(locations==""){
				mapProxy.centerAndZoom(point, 15);
			}
			
			mapProxy.clearOverlays();
			
			var marker = new BMap.Marker(point);  // 创建标注
			mapProxy.addOverlay(marker);              // 将标注添加到地图中
			marker.enableDragging();
			marker.addEventListener("dragend",function(e){
				
				//alert(e.point.lng+","+e.point.lat);
				setXY(e.point.lng,e.point.lat);
				returnx=e.point.lng;
				returny=e.point.lat;
				
			});
			//alert(Number(x)+","+Number(y));
			returnx=Number(x);
			returny=Number(y);
			
			
			
			//var label = new BMap.Label(getLabelHtml({text:damName}),{offset:new BMap.Size(-30, -24)});
			//marker.setLabel(label);
		}
		
		Function.prototype.delayInvoke = function(o, delay, args) {
		
			var fn = this;
			return setTimeout(function() {fn.apply(o, args || []);}, delay);
		};
		
		function setXY(_x, _y) {
			
			document.getElementById("x1").value = _x;
			document.getElementById("y1").value = _y;
			
			setX123(_x);
			setY123(_y);
		}
		var models = "map";
		function onModelCheck(model) {
			models = model;
			switch(model) {
				
				case "map":
					document.getElementById("iCenter").style.display = "block";
					document.getElementById("lnglatDiv1").style.display = "none";
					document.getElementById("lnglatDiv2").style.display = "none";
					
					if (document.getElementById("x1").value 
					&& document.getElementById("y1").value) {
						
						drawPoint(document.getElementById("x1").value, document.getElementById("y1").value);
					}
					break;
				case "1":
					document.getElementById("iCenter").style.display = "none";
					document.getElementById("lnglatDiv1").style.display = "block";
					document.getElementById("lnglatDiv2").style.display = "none";
					break;
				case "2":
					document.getElementById("iCenter").style.display = "none";
					document.getElementById("lnglatDiv1").style.display = "none";
					document.getElementById("lnglatDiv2").style.display = "block";
					break;
			}
		}
		
		function setX123(x) {
		
			if (x) {
				
				var lng = Number(x);
				var _d = Math.floor(lng);
				var _l = (lng - _d) * 60;
				var _m = Math.floor(_l);
				var _s = (_l - _m) * 60;
				
				//document.getElementById("x2").value = _d + "." + _m + "." + _s + "''";
				document.getElementById("x11").value = _d;
				document.getElementById("x12").value = _l;
				document.getElementById("x13").value = _s;
			}
		}
		
		function setY123(y) {
			
			var lat = Number(y);
			var _d = Math.floor(lat);
			var _l = (lat - _d) * 60;
			var _m = Math.floor(_l);
			var _s = (_l - _m) * 60;
			
			document.getElementById("y11").value = _d;
			document.getElementById("y12").value = _l;
			document.getElementById("y13").value = _s;
		}
		
		function onX1BlurHandler() {
			
			if (document.getElementById("x1").value != "") {
				
				var lng = Number(document.getElementById("x1").value);
				var _d = Math.floor(lng);
				var _l = (lng - _d) * 60;
				var _m = Math.floor(_l);
				var _s = (_l - _m) * 60;
				
				//document.getElementById("x2").value = _d + "." + _m + "." + _s + "''";
				document.getElementById("x11").value = _d;
				document.getElementById("x12").value = _l;
				document.getElementById("x13").value = _s;
				
				checkXYAndDrawPoint();
			}
		}
		
		function onY1BlurHandler() {
		
			if (!document.getElementById("y1").value || document.getElementById("y1").value == "") return;
			
			var lat = Number(document.getElementById("y1").value);
			var _d = Math.floor(lat);
			var _l = (lat - _d) * 60;
			var _m = Math.floor(_l);
			var _s = (_l - _m) * 60;
			
			document.getElementById("y11").value = _d;
			document.getElementById("y12").value = _l;
			document.getElementById("y13").value = _s;
			
			checkXYAndDrawPoint();
		}
		
		function onOkClickHandler() {
			//alert("retrunx:"+returnx+""+"retruny:"+returny);
			
			parent.$dlog.data={x:returnx,y:returny};
			parent.$dlog.close();

			if(models==1){
				var x1 = document.getElementById("x1").value;
				var y1 = document.getElementById("y1").value;
				
				if(x1==""){
					document.getElementById("x1_label").innerHTML = "请输入东纬度";
				}else if(isDecimal(x1)){
					document.getElementById("x1_label").innerHTML = "请输入正确的东纬度";
				}else{
					document.getElementById("x1_label").innerHTML = "";
				}
				if(y1==""){
					document.getElementById("y1_label").innerHTML = "请输入北纬度";
				}else if(isDecimal(y1)){
					document.getElementById("y1_label").innerHTML = "请输入正确的北纬度";
				}else{
					document.getElementById("x1_label").innerHTML = "";
				}
				if(x1!="" && y1!="" && isDecimal(x1)=="" && isDecimal(y1)==""){
					//parent.art.dialog.data("x", document.getElementById("x1").value);
					//parent.art.dialog.data("y", document.getElementById("y1").value);
					//var dialogId = parent.art.dialog.data("dialogId");
		        	//parent.art.dialog.removeData("dialogId");
					//parent.art.dialog.close(dialogId);
				}
			}else if(models==2){
				var x11 = document.getElementById("x11").value;
				var x12 = document.getElementById("x12").value;
				var x13 = document.getElementById("x13").value;
				var y11 = document.getElementById("y11").value;
				var y12 = document.getElementById("y12").value;
				var y13 = document.getElementById("y13").value;
				if(x11==""){
					document.getElementById("xy").innerHTML = "请输入东经(度)";
				}else if(isDecimal(x11)){
					document.getElementById("xy").innerHTML = "请输入正确的东经(度)";
				}else if(x12==""){
					document.getElementById("xy").innerHTML = "请输入东经(分))";
				}else if(isDecimal(x12)){
					document.getElementById("xy").innerHTML = "请输入正确的东经(分)";
				}else if(x13==""){
					document.getElementById("xy").innerHTML = "请输入东经(秒)";
				}else if(isDecimal(x13)){
					document.getElementById("xy").innerHTML = "请输入正确的东经(秒)";
				}else if(y11==""){
					document.getElementById("xy").innerHTML = "请输入北纬(度)";
				}else if(isDecimal(y11)){
					document.getElementById("xy").innerHTML = "请输入正确的北纬(度)";
				}else if(y12==""){
					document.getElementById("xy").innerHTML = "请输入北纬(分)";
				}else if(isDecimal(y12)){
					document.getElementById("xy").innerHTML = "请输入正确的北纬(分)";
				}else if(y13==""){
					document.getElementById("xy").innerHTML = "请输入北纬(秒)";
				}else if(isDecimal(y13)){
					document.getElementById("xy").innerHTML = "请输入正确的北纬(秒)";
				}
				if(x11!="" && x12!="" && x13!="" && y11!="" && y12!="" && y13!="" && isDecimal(x11)=="" && isDecimal(x12)=="" && isDecimal(x13)=="" && isDecimal(y11)=="" && isDecimal(y12)=="" && isDecimal(y13)==""){
					//parent.art.dialog.data("x", document.getElementById("x1").value);
					//parent.art.dialog.data("y", document.getElementById("y1").value);
					//var dialogId = parent.art.dialog.data("dialogId");
		        	//parent.art.dialog.removeData("dialogId");
					//parent.art.dialog.close(dialogId);
				}
			}else{
				//parent.art.dialog.data("x", document.getElementById("x1").value);
				//parent.art.dialog.data("y", document.getElementById("y1").value);
				//var dialogId = parent.art.dialog.data("dialogId");
	        	////parent.art.dialog.removeData("dialogId");
				//parent.art.dialog.close(dialogId);
			}
		}
		function isDecimal(str){ 
			return str.replace(/^(\d*(\.\d*)?)$/,'')  
		} 
		
		function onCancelClickHandler() {
			
			//var dialogId = parent.art.dialog.data("dialogId");
        	//parent.art.dialog.removeData("dialogId");
			//parent.art.dialog.close(dialogId);
			parent.$dlog.close();
		}
		
		function onX11BlurHandler() {
			
			if (document.getElementById("x11").value && document.getElementById("x11").value != "") {
				
				g_lng += Number(document.getElementById("x11").value);
				
				checkAndSetLng();
			}
		}
		
		function onX12BlurHandler() {
			
			if (document.getElementById("x12").value && document.getElementById("x12").value != "") {
				
				g_lng += Number(document.getElementById("x12").value)/60.0;
				
				checkAndSetLng();
			}
		}
		
		function onX13BlurHandler() {
			
			if (document.getElementById("x13").value && document.getElementById("x13").value != "") {
				
				g_lng += Number(document.getElementById("x13").value)/3600.0;
				
				checkAndSetLng();
			}
		}
		
		function onY11BlurHandler() {
			
			if (document.getElementById("y11").value && document.getElementById("y11").value != "") {
				
				g_lat += Number(document.getElementById("y11").value);
				
				checkAndSetLat();
			}
		}
		
		function onY12BlurHandler() {
			
			if (document.getElementById("y12").value && document.getElementById("y12").value != "") {
				
				g_lat += Number(document.getElementById("y12").value)/60.0;
				
				checkAndSetLat();
			}
		}
		
		function onY13BlurHandler() {
			
			if (document.getElementById("y13").value && document.getElementById("y13").value != "") {
				
				g_lat += Number(document.getElementById("y13").value)/3600.0;
				
				checkAndSetLat();
			}
		}
		
		function checkAndSetLng() {
			
			if (g_lng && g_lng > 1) {
				
				document.getElementById("x1").value = g_lng;
				
				checkXYAndDrawPoint();
			}
		}
		
		function checkAndSetLat() {
			
			if (g_lat && g_lat > 1) {
				
				document.getElementById("y1").value = g_lat;
				
				checkXYAndDrawPoint();
			}
		}
		
		function checkXYAndDrawPoint() {
			
			if (document.getElementById("x1").value 
			&& document.getElementById("x1").value != "" 
			&& document.getElementById("y1").value 
			&& document.getElementById("y1").value != "") {
				
				drawPoint(document.getElementById("x1").value, document.getElementById("y1").value);
			}
		}
		
		function getLabelHtml(options) {
		
			var htmls = [];
			htmls.push("<div style='fontSize:12px;lineHeight:18px;width:100px;paddingLeft:4px;paddingRight:4px;whiteSpace:nowrap;text-align:center;");
			if (options.className) {
				
				htmls.push("' class='");
				htmls.push(options.className);
				htmls.push("'>");
			}
			else {
				
				htmls.push("border:solid 1px #FF0000;background:#FFFFFF'>");
				
			}
			htmls.push(options.text);
			htmls.push("</div>");
			return htmls.join("");
		}
		function btnok(){
			var name=$("#areaName").val();
			mapProxy.centerAndZoom(name,18);
		}
		
		document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
                      
             if(e && e.keyCode==13){ // enter 键
                 btnok();
            }
        }; 