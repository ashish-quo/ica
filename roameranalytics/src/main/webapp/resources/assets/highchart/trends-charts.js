$(function () {
       $('#container').highcharts({
            title: {
                text: ''
            },
            xAxis: {
                categories: ['Mon', 'Tues', 'Wed', 'Thus', 'Fri', 'Sat','Sun']
            },
            yAxis: {
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
				showInLegend:false,
                name: 'Tokyo',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 11.5]
            }, {
				showInLegend:false,
                name: 'New York',
                data: [24.8, 24.1, 20.1, 14.1, 8.6, 2.5, 21.5]
            }, {
				showInLegend:false,
                name: 'Berlin',
                data: [18.6, 17.9, 14.3, 9.0, 3.9, 1.0, 5.5]
            }, {
				showInLegend:false,
                name: 'London',
                data: [17.0, 16.6, 14.2, 10.3, 6.6, 4.8, 1.5]
            }]
        });
		$('#container2').highcharts({
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
                min: 0,
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
                data: [105.0, 104.3, 91.2, 83.5, 106.6, 92.3, 54.4],
                color:'#4ed3e7'
    
            }]
        });
		$('#container3').highcharts({
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
                min: 0,
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
                color:'#edcf7d'
            }, {
                name: 'Value',
                data: [105.0, 104.3, 91.2, 83.5, 106.6, 92.3, 54.4],
                color:'#434348'
    
            }, {
                name: 'Premium',
                data: [59.0, 59.6, 52.4, 65.2, 59.3, 51.2, 74.4],
				color:'#c49ffc'
    
            }]
        });
		$('#container4').highcharts({
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
                min: 0,
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
                color:'#edcf7d'
            }, {
                name: 'Value',
                data: [105.0, 104.3, 91.2, 83.5, 106.6, 92.3, 54.4],
                color:'#434348'
    
            }, {
                name: 'Premium',
                data: [59.0, 59.6, 52.4, 65.2, 59.3, 51.2, 74.4],
				color:'#c49ffc'
    
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
    