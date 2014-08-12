
$(function () {
   // Load the data from a Google Spreadsheet 
    // https://docs.google.com/a/highsoft.com/spreadsheet/pub?hl=en_GB&hl=en_GB&key=0AoIaUO7wH1HwdFJHaFI4eUJDYlVna3k5TlpuXzZubHc&output=html
    Highcharts.data({
        googleSpreadsheetKey: '0AoIaUO7wH1HwdFJHaFI4eUJDYlVna3k5TlpuXzZubHc',

        // custom handler when the spreadsheet is parsed
        parsed: function (columns) {
            
            // Read the columns into the data array
            var data = [];
            $.each(columns[0], function (i, code) {
                data.push({
                    code: code.toUpperCase(),
                    value: parseFloat(columns[2][i]),
                    name: columns[1][i]
                })
            });

            
            // Initiate the chart
            $('#map-container').highcharts('Map', {
                chart : {
                    borderWidth : 0
                },
                title : {
                    text : ''
                },

                mapNavigation: {
                    enabled: true
                },
                
                legend: {
                    title: {
                        text: '',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.textColor) || 'Black'
                        }
                    },
                    align: 'left',
					borderWidth:1,
                    verticalAlign: 'bottom',
                    floating: true,
                    layout: 'vertical',
                    valueDecimals: 0,
                    backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || 'rgba(255,255,255,0.6)',
                    symbolRadius: 0,
                    symbolHeight: 14,
					borderColor:'#d9dce3'
                },

                colorAxis: {
                    dataClasses: [{
                        to: 3,
						name: 'As Projected'
                    }, {
                        from: 3,
                        to: 10,
						name: 'High Negative' 
                    }, {
                        from: 10,
                        to: 30 ,
						name: 'Negative'
                    }, {
                        from: 30,
                        to: 50 ,
						name: 'High Positive'
                    }, {
                        from: 50,
                        to: 70 ,
						name: 'Positive'
                    }],
					minColor: '#eda78e',
                    maxColor: '#87d796'

                },
				
                series : [{
                    data : data,
                    mapData: Highcharts.maps['custom/world'],
                    joinBy: ['iso-a2', 'code'],
                    animation: true,
                    name: 'Population density',
                    states: {
                        hover: {
                            color: '#ccc'
                        }
                    },
                    tooltip: {
                        valueSuffix: '/km²'
                    },
                }]
            });
        },
        error: function () {
            $('#map-container').html('<div class="map-loading">' + 
                '<i class="icon-frown icon-large"></i> ' + 
                'Error loading data from Google Spreadsheets' + 
                '</div>');
        }
    });
});