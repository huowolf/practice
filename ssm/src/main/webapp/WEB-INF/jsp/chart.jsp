<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EChart demo</title>
<script src="http://cdn.bootcss.com/echarts/3.4.0/echarts.common.js"></script>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
	<h1>数据统计</h1>
	  <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main1" style="width: 600px;height:400px;"></div>
    <div id="main2" style="width: 600px;height:400px;"></div>
    
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart1 = echarts.init(document.getElementById('main1'));
        var myChart2 = echarts.init(document.getElementById('main2'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 柱状图 '
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: []
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart1.setOption(option);
        
        myChart2.setOption({
            title: {
                text: 'Echart 折线图 '
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['销量']
            },
            toolbox: {
               /*  show: true,
                feature: {
                    magicType: {show: true, type: ['stack', 'tiled']},
                    saveAsImage: {show: true}
                } */
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: []
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                name: '销量',
                type: 'line',
                smooth: true,
                data: []
            }]
           
        });
        
        var names=[];    //类别数组（实际用来盛放X轴坐标值）
        var nums=[];    //销量数组（实际用来盛放Y坐标值）
        
        $.ajax({
            type : "post",
            url : "productBar",    
            dataType : "json",        //返回数据形式为json
            success : function(result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                if (result) {
                       for(var i=0;i<result.length;i++){       
                          names.push(result[i].name);    //挨个取出类别并填入类别数组
                        }
                       for(var i=0;i<result.length;i++){       
                           nums.push(result[i].num);    //挨个取出销量并填入销量数组
                         }
                       myChart1.hideLoading();    //隐藏加载动画
                       myChart1.setOption({        //加载数据图表
                           xAxis: {
                               data: names
                           },
                           series: [{
                               // 根据名字对应到相应的系列
                               name: '销量',
                               data: nums
                           }]
                       });
                       
                       myChart2.setOption({        //加载数据图表
                           xAxis: {
                               data: names
                           },
                           series: [{
                               // 根据名字对应到相应的系列
                               name: '销量',
                               data: nums
                           }]
                       });
                        
                }
             
           },
            error : function(errorMsg) {
                //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart1.hideLoading();
            }
       })
    </script>
</body>
</html>