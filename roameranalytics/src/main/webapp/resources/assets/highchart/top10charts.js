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
		 $('#container3').highcharts({
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
		 $('#container4').highcharts({
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
		});