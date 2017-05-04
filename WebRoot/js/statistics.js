var myChart=echarts.init(document.getElementById('ProductStatistics'));
	var option={
		title : {
			text : 'ECharts 入门示例'
		},
		tooltip : {},
		legend : {
			data : [ '合格率' ]
		},
		xAxis : {
			data : [ "小李锁厂", "小黄锁产", "小何打铁铺", "老李头门铃厂", "老赵门铃", "大东门锁" ]
		},
		yAxis : {},
		series : [ {
			name : '合格率',
			type : 'bar',
			data : [ 80, 88, 90, 89, 91, 92 ]
		} ]
	};
	myChart.setOption(option);  