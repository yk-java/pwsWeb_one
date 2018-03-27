		var mapProxy;
		var g_lng = 0;
		var g_lat = 0;
		var damName;
		var returnx;
		var returny;
		var _x = "";
		var _y = "";
		var _point="";
		var _text="";
		var  _overText="";
		var _div="";
		var _span="";
		var _map;
		
		var damAreaName = "";//区名称
		
		function initMap() {
			
			mapProxy = new BMap.Map("iCenter", {
					
				mapType: BMAP_NORMAL_MAP,enableMapClick:false
			});
			
			mapProxy.enableScrollWheelZoom();
			mapProxy.addControl(new BMap.MapTypeControl());
			var top_left_navigation = new BMap.NavigationControl();
			mapProxy.addControl(top_left_navigation);
			
			  
		    
		    
		}

		
		var locations = "";
		
		function drawPoint(x, y,dormName,status) {
			
			
			//mapProxy.centerAndZoom("中国",12); 
			
			var point = new BMap.Point(Number(x), Number(y));

			var opts = {
			  position : point,    // 指定文本标注所在的地理位置
			  offset   : new BMap.Size(-85, -60)    //设置文本偏移量
			};
			var tempname="";
			if(dormName.length>11){
				tempname=dormName.substring(0,11)+"...";				
			}else{
				tempname=dormName;
			}
			if(status==1){//闲置
				var label = new BMap.Label(getLabelHtml({text:dormName,tempname:tempname}),opts);
				label.setStyle({background : "", border : "0px" });
				mapProxy.addOverlay(label);
			}else if(status==2){//在租
				var label = new BMap.Label(getLabelHtml1({text:dormName,tempname:tempname}),opts);
				label.setStyle({background : "", border : "0px" });
				mapProxy.addOverlay(label);
			}
			
			
			//marker.setLabel(label);
		}
		
		
		
		
		
		function getLabelHtml(options) {
			//alert(options);
			var htmls = [];
			htmls.push("<div title='"+options.text+"' style='cursor:pointer;padding-top:7px;background-image: url(&quot;../../../../img/map/lie.png&quot;);width:180px;height:28px;text-align:center;font-family:&quot;微软雅黑&quot;'");
			
				htmls.push("'>");
				
			
			htmls.push(options.tempname);
			htmls.push("</div>");
			return htmls.join("");
		}
		
		function getLabelHtml1(options) {
			//alert(options);
			var htmls = [];
			htmls.push("<div title='"+options.text+"' style='cursor:pointer;padding-top:7px;background-image: url(&quot;../../../../img/map/rent.png&quot;);width:180px;height:28px;text-align:center;font-family:&quot;微软雅黑&quot;'");
			
				htmls.push("'>");
				
			
			htmls.push(options.tempname);
			htmls.push("</div>");
			return htmls.join("");
		}
		