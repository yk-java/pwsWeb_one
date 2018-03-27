/**
 * 将字符串转为js日期类型
 * @param {string} str 格式 2008-08-01 12:12:12
 * @return Date
 */
function getDate(str) {
    let strs = str.split(" ");
    let strYmd = strs[0].split("-");
    let strHm = strs[1].split(":");
    return new Date(strYmd[0], parseInt(strYmd[1] - 1), strYmd[2], strHm[0], strHm[1]);
}

function isNotBlank(strIn) {
    if (strIn !== undefined) {
        return true;
    } else if (strIn !== null) {
        return true;
    } else return strIn !== "";
}

function isBlank(strIn) {
    if (strIn === undefined) {
        return true;
    } else if (strIn == null) {
        return true;
    } else return strIn == "";
}

/**
 * 创建地图 默认以IP定位获取当前城市初始化地图
 * @return BMap
 * @param mapId
 */
function createBMap(mapId) {
    let map = new BMap.Map(mapId);
    // map.centerAndZoom(new BMap.Point(longitude, latitude), level);
    map.addControl(new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP, BMAP_HYBRID_MAP]}));
    //map.setMapType(new MapType().BMAP_SATELLITE_MAP);
    map.enableScrollWheelZoom();//启用滚轮放大缩小，默认禁用
    //map.enableContinuousZoom();	//启用地图惯性拖拽，默认禁用

    //map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
    //map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}));   //右上角，仅包含平移和缩放按钮
    //map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT, type: BMAP_NAVIGATION_CONTROL_PAN}));   //左下角，仅包含平移按钮
    //map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, type: BMAP_NAVIGATION_CONTROL_ZOOM})); //右下角，仅包含缩放按钮

    //map.addControl(new BMap.ScaleControl());	// 添加默认比例尺控件
    //map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT}));	 // 左上
    //map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_RIGHT}));    // 右上
    //map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT}));	 // 左下
    //map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT})); // 右下
    return map;
}

/**
 * 创建单个标注
 * @param {Object} map BMap
 * @param {number} longitude 经度
 * @param {number} latitude 纬度
 * @param {String} msg 标注信息标题
 * @param icon 图片
 */
function createMarker(map, longitude, latitude, msg, icon) {
    if (isNotBlank(longitude) && isNotBlank(latitude)) {
        let thePoint = new BMap.Point(longitude, latitude);
        let marker = new BMap.Marker(thePoint);  // 创建标注
        if (isNotBlank(icon)) {
            marker.setIcon(icon);
        }
        marker.setTitle(msg);
        map.addOverlay(marker); // 将标注添加到地图中

        //信息窗口设置
        let opts = {
            //width : 250, // 信息窗口宽度
            //height: 100, // 信息窗口高度
            title: isNotBlank(msg) ? msg : '' // 信息窗口标题
        };
        let sContent = ""; //弹框内容
        let infoWindow = new BMap.InfoWindow(sContent, opts); //创建信息窗口
        marker.addEventListener("click", function () {
            this.openInfoWindow(infoWindow);
        });

        return marker;
    }
}

/**
 * 创建标注点，并画线
 * @param map
 * @param arr
 * @returns
 */
function createMarkersPolyline(map, arr) {
    if (isBlank(arr) || arr.length < 1) {
        return null;
    }
    let now = new Date();
    let points = [];
    for (let i = 0; i < arr.length; i++) {
        if (isBlank(arr[i])) {
            continue;
        }
        let pl = arr[i];
        let thePoint = new BMap.Point(pl.longitudeBaidu, pl.latitudeBaidu);
        points.push(thePoint);
        let marker = new BMap.Marker(thePoint);  // 创建标注
        let icon = i == 0 ? endIconBaidu : (i === arr.length - 1 ? startIconBaidu : null);
        if (isBlank(icon)) {
            let dn = parseInt((now - getDate(arr[i].keepTime)) / 1000 / 60);     //把相差的毫秒数转换为分钟
            if (dn > 10 && dn <= 30) {
                icon = dull_redBaidu;
            }
            else if (dn > 30) {
                icon = greyIconBaidu; // 标识为灰色
            }
        }
        if (isNotBlank(icon)) {
            marker.setIcon(icon);
        }
        marker.setTitle(pl.realName);
        let title = pl.realName + " 手机号：" + pl.phone + " 时间：" + pl.keepTime;
        let address = "当前位于：" + (isNotBlank(pl.addressBaidu) ? pl.addressBaidu : '未知');
        fun_infoWin(marker, title, address);
        map.addOverlay(marker);            // 将标注添加到地图中
    }
    drawPolyline(map, points);
}

/**
 * 画线
 * @param bMap
 * @param points
 */
function drawPolyline(bMap, points) {
    if (points == null || points.length <= 1) {
        return;
    }
    bMap.addOverlay(new BMap.Polyline(points, {
        strokeColor: "blue",
        strokeWeight: 3,
        strokeOpacity: 0.5
    })); // 画线
}

/**
 * 设置弹出窗体信息
 *
 * @param map
 * @param marker
 * @param params
 */
function equipmentInfoWin(map,marker,params) {
    let info = {"设备名称":params.amname,"设备类型":params.voltagelevel,"经度":params.jd,"纬度":params.wd};
    let sContent = "<table style='border-collapse:collapse;padding-left:4px'>";
    for (let item in info) {
        sContent += "<tr><td width='100' style='color:#4CA9FF;font-weight:bold'>" + item + "</td><td>" + info[item] + "</td> </tr>";
    }
    sContent += "</table>";
    marker.addEventListener("click", function () {
        this.openInfoWindow(new BMap.InfoWindow(sContent));
    });
    let label = new BMap.Label(params.amname,{offset:new BMap.Size(-15,30)});
    label.setStyle({
        color : "#000",
        fontSize : "12px",
        height : "24px",
        lineHeight : "24px",
        backgroundColor:"#f4f4f4",
        borderRadius:"5px",
        boxShadow:"2px 3px 5px #666",
        borderColor:"#0066cc",
        fontFamily:"微软雅黑"
    });
    map.addOverlay(marker);
    marker.setLabel(label); //添加百度label
    map.centerAndZoom(marker.getPosition(), 15);
}

/**
 * 设置弹出窗体信息
 *
 * @param map
 * @param marker
 * @param params
 */
function lineInfoWin(map,marker,params) {
    let info = {"线路名称":params.amname,"设备类型":params.voltagelevel,"经度":params.jd,"纬度":params.wd};
    let sContent = "<table style='border-collapse:collapse;padding-left:4px'>";
    for (let item in info) {
        sContent += "<tr><td width='100' style='color:#4CA9FF;font-weight:bold'>" + item + "</td><td>" + info[item] + "</td> </tr>";
    }
    sContent += "</table>";
    marker.addEventListener("click", function () {
        this.openInfoWindow(new BMap.InfoWindow(sContent));
    });
    let label = new BMap.Label(params.amname,{offset:new BMap.Size(-15,30)});
    label.setStyle({
        color : "#000",
        fontSize : "12px",
        height : "24px",
        lineHeight : "24px",
        backgroundColor:"#f4f4f4",
        borderRadius:"5px",
        boxShadow:"2px 3px 5px #666",
        borderColor:"#0066cc",
        fontFamily:"微软雅黑"
    });
    map.addOverlay(marker);
    marker.setLabel(label); //添加百度label
    map.centerAndZoom(marker.getPosition(), 15);
}

function fun_infoWin(marker, title, content) {
    //信息窗口设置
    let opts = {
        title: isNotBlank(title) ? title : ''  // 信息窗口标题
    };
    marker.addEventListener("click", function () {
        this.openInfoWindow(new BMap.InfoWindow(content, opts));
    });
}

/**
 * 创建多个标注
 * @param {Object} map BMap
 * @param {Array} arr 数组
 */
function createMarkersLbl(map, arr) {
    if (isBlank(arr) || arr.length < 1) {
        return null;
    }
    map.clearOverlays();
    let points = [];
    for (let i = 0; i < arr.length; i++) {
        if (isBlank(arr[i])) {
            continue;
        }
        let pl = arr[i];
        let thePoint = new BMap.Point(pl.longitudeBaidu, pl.latitudeBaidu);
        points.push(thePoint);
        let marker = new BMap.Marker(thePoint);  // 创建标注
        marker.setTitle(pl.realName);
        let title = pl.realName + " 手机号：" + pl.phone + " 时间：" + pl.keepTime;
        let address = "当前位于：" + (isNotBlank(pl.addressBaidu) ? pl.addressBaidu : '未知');
        fun_infoWin(marker, title, address);
        map.addOverlay(marker);            // 将标注添加到地图中
        marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        // 创建文本标注对象 在图标标注点上方
        let opts = {
            position: thePoint,    // 指定文本标注所在的地理位置
            offset: new BMap.Size(-20, -58)    //设置文本偏移量(位于标注点上方)
        };
        let label = new BMap.Label(name, opts);
        label.setStyle({
            "padding": "2px",
            "border": "1px solid #CCCCCC",
            "display": "block",
            "filter": "alpha(opacity=62)",
            "-moz-opacity": "0.62",
            "opacity": "0.62",
            "fontSize": "13px",
        });
        map.addOverlay(label);
    }
    map.setViewport(points);
}

/**
 * 起始标记图片
 */
var startIconBaidu = new BMap.Icon("../images/icons/marker_start.png", new BMap.Size(40, 40));

/**
 * 结束标记图片
 */
var endIconBaidu = new BMap.Icon("../images/icons/marker_end.png", new BMap.Size(40, 40));

/**
 * 灰色标记图片
 */
var greyIconBaidu = new BMap.Icon("../images/icons/marker_grey.png", new BMap.Size(20, 30));

/**
 * 暗红色标记图片
 */
var dull_redBaidu = new BMap.Icon("../images/icons/marker_dull_red.png", new BMap.Size(20, 30));