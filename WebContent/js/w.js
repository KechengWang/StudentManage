$(document).ready(function() {
updateInfo();

});

$("#city").change(function() {
    updateInfo();
});

//获取指定城市的天气预报数据
function getWeather(cityName) {
    var result = "error";
    //使用ajax()方法验证账号
    $.ajax({
        url : "https://free-api.heweather.com/v5/weather",
        method : "POST",
        data : {
            city : cityName,
            key : "f0671b6589ff43019e72970d334ea93e"
        },
        dataType : "json",
        async : false,
        success : function(data) {
            result = data;
        }
    });
    return result;
}

//更新页面数据
function updateInfo() {
    //获取当前城市名称
    var cityName = $("#city").val();
    //获取当前城市的全部天气数据
    var data = getWeather(cityName);

    if (data == "error")
        return;
    //ajax通讯失败
    if (data.HeWeather5[0].status != "ok")
        return;
    //状态未获取

    //1、当前气候
    var now = data.HeWeather5[0].now;

    //更新当前气候相关数据
    $("#icon").attr("src", "image/icons/" + now.cond.code + ".png");//图标
    $("#tmp").text(now.tmp);//气温
    $("#cond_txt").text(now.cond.txt);//气候（晴、多云等描述）

    $("#fl").text(now.fl);//体感温度
    $("#hum").text(now.hum);//湿度
    $("#pcpn").text(now.pcpn);//降水量
    $("#pres").text(now.pres);//气压
    $("#vis").text(now.pres);//能见度
    $("#wind_sc").text(now.wind.sc);//风力等级
    $("#wind_dir").text(now.wind.dir);//风力方向

    //2、建议
    var suggestion = data.HeWeather5[0].suggestion;

    //2-1、出行建议
    var trav = suggestion.trav;

    //更新出行建议相关数据
    $("#trav_brf").text(trav.brf);
    $("#trav_txt").text(trav.txt);

    //2-2、防晒建议
    var uv = suggestion.uv;

    //更新防晒建议相关数据
    $("#uv_brf").text(uv.brf);
    $("#uv_txt").text(uv.txt);
}