
$j(function () {
   // Load the data from a Google Spreadsheet 
    // https://docs.google.com/a/highsoft.com/spreadsheet/pub?hl=en_GB&hl=en_GB&key=0AoIaUO7wH1HwdFJHaFI4eUJDYlVna3k5TlpuXzZubHc&output=html

        // custom handler when the spreadsheet is parsed
            // Read the columns into the data array
        
            var dataMap = [{ 'code': 'mt', value: 48 },
                    { 'code': 'kz', value: 49 },
                    { 'code': 'mn', value: 50 },
                    { 'code': 'sr', value: 51 },
                    { 'code': 'ie', value: 52 },
                    { 'code': 'dm', value: 53 },
                    { 'code': 'bj', value: 54 },
                    { 'code': 'ng', value: 55 },
                    { 'code': 'be', value: 56 },
                    { 'code': 'tg', value: 57 },
                    { 'code': 'de', value: 58 },
                    { 'code': 'lk', value: 59 },
                    { 'code': 'gb', value: 60 },
                    { 'code': 'gy', value: 61 },
                    { 'code': 'cr', value: 62 },
                    { 'code': 'cm', value: 63 },
                    { 'code': 'kas', value: 64 },
                    { 'code': 'km', value: 65 },
                    { 'code': 'ug', value: 66 },
                    { 'code': 'tm', value: 67 },
                    { 'code': 'tt', value: 68 },
                    { 'code': 'nl', value: 69 },
                    { 'code': 'td', value: 70 },
                    { 'code': 'ge', value: 71 },
                    { 'code': 'ro', value: 72 },
                    { 'code': 'scr', value: 73 },
                    { 'code': 'lv', value: 74 },
                    { 'code': 'bz', value: 75 },
                    { 'code': 'mm', value: 76 },
                    { 'code': 'af', value: 77 },
                    { 'code': 'bi', value: 78 },
                    { 'code': 'by', value: 79 },
                    { 'code': 'gd', value: 80 },
                    { 'code': 'lr', value: 81 },
                    { 'code': 'gr', value: 82 },
                    { 'code': 'ls', value: 83 },
                    { 'code': 'gl', value: 84 },
                    { 'code': 'ad', value: 85 },
                    { 'code': 'mz', value: 86 },
                    { 'code': 'tj', value: 87 },
                    { 'code': 'th', value: 88 },
                    { 'code': 'ht', value: 89 },
                    { 'code': 'mx', value: 90 },
                    { 'code': 'zw', value: 91 },
                    { 'code': 'lc', value: 92 },
                    { 'code': 'in', value: 93 },
                    { 'code': 'vc', value: 94 },
                    { 'code': 'bt', value: 95 },
                    { 'code': 'vn', value: 96 },
                    { 'code': 'no', value: 97 },
                    { 'code': 'cz', value: 98 },
                    { 'code': 'ag', value: 99 },
                    { 'code': 'fj', value: 100 },
                    { 'code': 'hn', value: 101 },
                    { 'code': 'mu', value: 102 },
                    { 'code': 'do', value: 103 },
                    { 'code': 'lu', value: 104 },
                    { 'code': 'il', value: 105 },
                    { 'code': 'sm', value: 106 },
                    { 'code': 'pe', value: 107 },
                    { 'code': 'id', value: 108 },
                    { 'code': 'vu', value: 109 },
                    { 'code': 'mk', value: 110 },
                    { 'code': 'cd', value: 111 },
                    { 'code': 'cg', value: 112 },
                    { 'code': 'is', value: 113 },
                    { 'code': 'et', value: 114 },
                    { 'code': 'um', value: 115 },
                    { 'code': 'co', value: 116 },
                    { 'code': 'ser', value: 117 },
                    { 'code': 'bw', value: 118 },
                    { 'code': 'md', value: 119 },
                    { 'code': 'mg', value: 120 },
                    { 'code': 'ec', value: 121 },
                    { 'code': 'sn', value: 122 },
                    { 'code': 'tl', value: 123 },
                    { 'code': 'fr', value: 124 },
                    { 'code': 'lt', value: 125 },
                    { 'code': 'rw', value: 126 },
                    { 'code': 'zm', value: 127 },
                    { 'code': 'gm', value: 128 },
                    { 'code': 'fo', value: 129 },
                    { 'code': 'gt', value: 130 },
                    { 'code': 'dk', value: 131 },
                    { 'code': 'ua', value: 132 },
                    { 'code': 'au', value: 133 },
                    { 'code': 'at', value: 134 },
                    { 'code': 've', value: 135 },
                    { 'code': 'pw', value: 136 },
                    { 'code': 'ke', value: 137 },
                    { 'code': 'la', value: 138 },
                    { 'code': 'bjn', value: 139 },
                    { 'code': 'tr', value: 140 },
                    { 'code': 'jp', value: 141 },
                    { 'code': 'al', value: 142 },
                    { 'code': 'om', value: 143 },
                    { 'code': 'it', value: 144 },
                    { 'code': 'bn', value: 145 },
                    { 'code': 'tn', value: 146 },
                    { 'code': 'hu', value: 147 },
                    { 'code': 'ru', value: 148 },
                    { 'code': 'lb', value: 149 },
                    { 'code': 'bb', value: 150 },
                    { 'code': 'br', value: 151 },
                    { 'code': 'ci', value: 152 },
                    { 'code': 'rs', value: 153 },
                    { 'code': 'gq', value: 154 },
                    { 'code': 'us', value: 155 },
                    { 'code': 'se', value: 156 },
                    { 'code': 'az', value: 157 },
                    { 'code': 'gw', value: 158 },
                    { 'code': 'sz', value: 159 },
                    { 'code': 'ca', value: 160 },
                    { 'code': 'kv', value: 161 },
                    { 'code': 'kr', value: 162 },
                    { 'code': 'mw', value: 163 },
                    { 'code': 'sk', value: 164 },
                    { 'code': 'cy', value: 165 },
                    { 'code': 'ba', value: 166 },
                    { 'code': 'pga', value: 167 },
                    { 'code': 'sg', value: 168 },
                    { 'code': 'tw', value: 169 },
                    { 'code': 'so', value: 170 },
                    { 'code': 'sol', value: 171 },
                    { 'code': 'uz', value: 172 },
                    { 'code': 'cf', value: 173 },
                    { 'code': 'pl', value: 174 },
                    { 'code': 'kw', value: 175 },
                    { 'code': 'er', value: 176 },
                    { 'code': 'ga', value: 177 },
                    { 'code': 'ee', value: 178 },
                    { 'code': 'es', value: 179 },
                    { 'code': 'iq', value: 180 },
                    { 'code': 'sv', value: 181 },
                    { 'code': 'ml', value: 182 },
                    { 'code': 'st', value: 183 },
                    { 'code': 'ir', value: 184 },
                    { 'code': 'sl', value: 185 },
                    { 'code': 'cnm', value: 186 },
                    { 'code': 'bs', value: 187 },
                    { 'code': 'sb', value: 188 },
                    { 'code': 'nz', value: 189 },
                    { 'code': 'mc', value: 190 },
                    { 'code': 'ss', value: 191 },
                    { 'code': 'kg', value: 192 },
                    { 'code': 'ae', value: 193 },
                    { 'code': 'ar', value: 194 },
                    { 'code': 'sd', value: 195 },
                    { 'code': 'bh', value: 196 },
                    { 'code': 'am', value: 197 },
                    { 'code': 'pg', value: 198 },
                    { 'code': 'cu', value: 199 }];
            // Initiate the chart
            function intiateMap(data)
            {
            $j('#map-container').highcharts('Map', {
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
			joinBy: ['hc-key', 'code'],
                        name: 'Random data',
                        states: {
                            hover: {
                                color: '#BADA55'
                            }
                        },
                        dataLabels: {
                            enabled: false, /*to disable showing lebles*/
                            format: '{point.name}'
                        },
                    tooltip: {
                        valueSuffix: '/kmÂ²'
                    }
                }]
            });
console.log("Inside mapcore");
            }
});