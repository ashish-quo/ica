$(function () {
	$('#container-front-first').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            xAxis: {
               categories: ['Mon', 'Tues', 'Wed', 'Thus', 'Fri', 'Sat','Sun']
            },
            yAxis: {
                //min: 0,
				type: 'logarithmic',
                title: {
                    text: ''
                }
				
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: 'Silent',
                data: [135.6, 148.5, 216.4, 194.1, 95.6, 54.4, 84.4],
                color:'#bfaea0'
            }, {
                name: 'Value',
                data: [105.0, 104.3, 91.2, 83.5, 76.6, 42.3, 54.4],
                color:'#434348'
    
            }, {
                name: 'Premium',
                data: [105.0, 104.3, 91.2, 83.5, 66.6, 32.3, 54.4],
                color:'#e8a26a'
    
            }]
			
        });
		
		$("#rom-opt-b").click(function(){
			$('#container-front-first').highcharts().yAxis[0].update({ type: 'linear'});
		});
		$("#rom-opt-a").click(function(){
			$('#container-front-first').highcharts().yAxis[0].update({ type: 'logarithmic'});
		});
	
		
       $('#container-back-first').highcharts({
            title: {
                text: ''
            },
            xAxis: {
                categories: ['Mon', 'Tues', 'Wed', 'Thus', 'Fri', 'Sat','Sun']
            },
            yAxis: {
				type: 'logarithmic',
                title: {
                    text: ''
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '°C'
            },
            legend: {
                layout: 'horizontal',
               // align: 'right',
                //verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{				
                name: 'Silent',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 11.5],
                color:'#bfaea0'
            }, {
                name: 'Value',
                data: [24.8, 24.1, 20.1, 14.1, 8.6, 2.5, 21.5],
                color:'#434348'
            }, {
                name: 'Premium',
                data: [18.6, 17.9, 14.3, 9.0, 3.9, 1.0, 5.5],
                color:'#e8a26a'
            }]
        });
		$("#rom-opt-d").click(function(){
			$('#container-back-first').highcharts().yAxis[0].update({ type: 'linear'});
		});
		$("#rom-opt-c").click(function(){
			$('#container-back-first').highcharts().yAxis[0].update({ type: 'logarithmic'});
		});
		
		
		
		
		$('#container-front-second').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            xAxis: {
               categories: ['Mon', 'Tues', 'Wed', 'Thus', 'Fri', 'Sat','Sun']
            },
            yAxis: {
                type: 'logarithmic',
                title: {
                    text: ''
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: 'MT',
                data: [135.6, 148.5, 216.4, 194.1, 95.6, 54.4, 84.4],
                color:'#f684ba'
            }, {
                name: 'MO',
                data: [105.0, 104.3, 91.2, 83.5, 66.6, 22.3, 54.4],
                color:'#4ed3e7'
    
            }]
        });
		
				
		
		
		$('#container-back-second').highcharts({
            title: {
                text: ''
            },
            xAxis: {
                categories: ['Mon', 'Tues', 'Wed', 'Thus', 'Fri', 'Sat','Sun']
            },
            yAxis: {
				type: 'logarithmic',
                title: {
                    text: ''
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '°C'
            },
            legend: {
                layout: 'horizontal',
               // align: 'right',
                //verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{				
                name: 'MT',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 11.5],
				color:'#f684ba'
            }, {
                name: 'MO',
                data: [24.8, 24.1, 20.1, 14.1, 8.6, 2.5, 21.5],
				color:'#4ed3e7'
            }]
        });
		
		
				
		$('#container-front-third').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            xAxis: {
               categories: ['Mon', 'Tues', 'Wed', 'Thus', 'Fri', 'Sat','Sun']
            },
            yAxis: {
                type: 'logarithmic',
                title: {
                    text: ''
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
				showInLegend:false,
                name: 'MT',
                data: [135.6, 148.5, 216.4, 194.1, 95.6, 54.4, 84.4],
                color:'#fdc798'
            }
			]
        });
	
		
		
		$('#container-back-third').highcharts({
            title: {
                text: ''
            },
            xAxis: {
                categories: ['Mon', 'Tues', 'Wed', 'Thus', 'Fri', 'Sat','Sun']
            },
            yAxis: {
				type: 'logarithmic',
                title: {
                    text: ''
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#959099'
                }]
            },
            tooltip: {
                valueSuffix: '°C'
            },
            legend: {
                layout: 'horizontal',
               // align: 'right',
                //verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
				showInLegend:false,
                name: 'Berlin',
                data: [18.6, 17.9, 14.3, 9.0, 3.9, 1.0, 5.5],
				color: '#fdc798'
				
            }]
        });
	
		
		
		
		$('#container-front-four').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            xAxis: {
               categories: ['Mon', 'Tues', 'Wed', 'Thus', 'Fri', 'Sat','Sun']
            },
            yAxis: {
                type: 'logarithmic',
                title: {
                    text: ''
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
				showInLegend:false,
                name: 'MT',
                data: [135.6, 148.5, 216.4, 194.1, 95.6, 54.4, 84.4],
                color:'#fae492'
            }]
        });
		
		
		
		
		$('#container-back-four').highcharts({
            title: {
                text: ''
            },
            xAxis: {
                categories: ['Mon', 'Tues', 'Wed', 'Thus', 'Fri', 'Sat','Sun']
            },
            yAxis: {
				type: 'logarithmic',
                title: {
                    text: ''
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#959099'
                }]
            },
            tooltip: {
                valueSuffix: '°C'
            },
            legend: {
                layout: 'horizontal',
               // align: 'right',
                //verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
				showInLegend:false,
                name: 'Tokyo',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 11.5],
				 color: '#fcb322'
            }]
        });
	
	
	
	
	
	
	
	$('#bar-chart').highcharts({
            chart: {
                type: 'bar',
				height: 150,
            },
            title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                
                categories: ['Category 1', 'Category 2'],
                title: {
                    text: null
                }
            },
            yAxis: {
                
                min: 0,
                gridLineWidth: 0,
                minorGridLineWidth: 0,
                title: {
                    text: '',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' %'
            },
            plotOptions: {
                bar: {
                    colorByPoint: true,
                    dataLabels: {
                        enabled: true
                    }
                }
           
            },
            
            credits: {
                enabled: false
            },
            series: [{
                showInLegend:false,
                name: 'Year 1800',
                data: [107, 31],
   
            }],
            colors: ['#5dadb2','#ee9d4e'],
        });
		$('#column-chart').highcharts({
            chart: {
                type: 'column',
				height: 170,
            },
            title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                
                categories: ['Category 1', 'Category 2','Category 3', 'Category 4'],
                title: {
                    text: null
                }
            },
            yAxis: {
                
                min: 0,
                gridLineWidth: 0,
                minorGridLineWidth: 0,
                title: {
                    text: 'Category 2',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' %'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
           
            },
            
            credits: {
                enabled: false
            },
            series: [{
                showInLegend:false,
                name: 'Year 1800',
                data: [107, 31,50, 80],
   
            }],
            colors: ['#5dadb2'],
        });
    
    
		
    });
    
	
	
	
	
	
	