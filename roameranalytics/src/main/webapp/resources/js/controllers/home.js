(function(){
	var homeC = angular.module("app.home",[]);
	
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
       
       function intiateTop10Bubble(){
    	   
    	   var point = {
    			    x: null,
    			    y: null
    			};

    			var chart = new Highcharts.Chart({
    				 title: {
    			            text: '',
    			            x: -20 //center
    			        },

    			    chart: {
    			        renderTo: 'container'
    			    },

    			    xAxis: {
    			        categories: []
    			    },

    			    series: [{
    					showInLegend:false,
    			        type: 'column',
    			        name: 'third',
    			        data: [95.6, 54.4, 29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1],
    					color: '#51bfe3',
    			    }]
    			});
    			$j('#b1').click(function () {
    			    var no = 2; //third element
    			    // removed march, hoping we'd only show 11 months...
    			    chart.xAxis[0].setCategories(['Jan', 'Feb', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'], false);
    			    var data = [];
    			    for (i = 0; i < chart.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart.series[0].data[i].x, chart.series[0].data[i].y]);
    			        } else if (i === no) {
    			            point.x = chart.series[0].data[i].x;
    			            point.y = chart.series[0].data[i].y;
    			        } else if (i > no) {
    			            data.push([chart.series[0].data[i].x - 1, chart.series[0].data[i].y]);
    			        }
    			    }
    			    chart.series[0].setData(data);
    			});

    			$j('#b2').click(function () {
    			    var no = 2; //third element
    			    var data = [];
    			    for (i = 0; i < chart.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart.series[0].data[i].x, chart.series[0].data[i].y]);
    			        } else if (i === no) {
    			            data.push([point.x, point.y]);
    			            data.push([chart.series[0].data[i].x + 1, chart.series[0].data[i].y]);
    			        } else if (i > no) {
    			            data.push([chart.series[0].data[i].x + 1, chart.series[0].data[i].y]);
    			        }
    			    }
    			    chart.xAxis[0].setCategories(['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']);
    			    chart.series[0].setData(data);
    			});
    						  
    						  


    			var point = {
    			    x: null,
    			    y: null
    			};

    			var chart2 = new Highcharts.Chart({
    				
    				title: {
    			            text: '',
    			            x: -20 //center
    			        },

    			    chart: {
    			        renderTo: 'container2'
    			    },

    			    xAxis: {
    			        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    			    },

    			    series: [{
    					showInLegend:false,
    			        type: 'column',
    			        name: 'third',
    			        data: [95.6, 54.4, 29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1],
    					color: '#41cac0',
    			    }]
    			});
    			$j('#b3').click(function () {
    			    var no = 2; //third element
    			    // removed march, hoping we'd only show 11 months...
    			    chart2.xAxis[0].setCategories(['Jan', 'Feb', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'], false);
    			    var data = [];
    			    for (i = 0; i < chart2.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart2.series[0].data[i].x, chart2.series[0].data[i].y]);
    			        } else if (i === no) {
    			            point.x = chart2.series[0].data[i].x;
    			            point.y = chart2.series[0].data[i].y;
    			        } else if (i > no) {
    			            data.push([chart2.series[0].data[i].x - 1, chart2.series[0].data[i].y]);
    			        }
    			    }
    			    chart2.series[0].setData(data);
    			});

    			$j('#b4').click(function () {
    			    var no = 2; //third element
    			    var data = [];
    			    for (i = 0; i < chart2.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart2.series[0].data[i].x, chart2.series[0].data[i].y]);
    			        } else if (i === no) {
    			            data.push([point.x, point.y]);
    			            data.push([chart2.series[0].data[i].x + 1, chart2.series[0].data[i].y]);
    			        } else if (i > no) {
    			            data.push([chart2.series[0].data[i].x + 1, chart2.series[0].data[i].y]);
    			        }
    			    }
    			    chart2.xAxis[0].setCategories(['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']);
    			    chart2.series[0].setData(data);
    			});


    					
    					


    			var point = {
    			    x: null,
    			    y: null
    			};

    			var chart3 = new Highcharts.Chart({
    				
    				title: {
    			            text: '',
    			            x: -20 //center
    			        },

    			    chart: {
    			        renderTo: 'container3'
    			    },

    			    xAxis: {
    			        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    			    },

    			    series: [{
    					showInLegend:false,
    			        type: 'column',
    			        name: 'third',
    			        data: [95.6, 54.4, 29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1],
    					color: '#8075c4',
    			    }]
    			});
    			$j('#b5').click(function () {
    			    var no = 2; //third element
    			    // removed march, hoping we'd only show 11 months...
    			    chart3.xAxis[0].setCategories(['Jan', 'Feb', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'], false);
    			    var data = [];
    			    for (i = 0; i < chart3.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart3.series[0].data[i].x, chart3.series[0].data[i].y]);
    			        } else if (i === no) {
    			            point.x = chart3.series[0].data[i].x;
    			            point.y = chart3.series[0].data[i].y;
    			        } else if (i > no) {
    			            data.push([chart3.series[0].data[i].x - 1, chart3.series[0].data[i].y]);
    			        }
    			    }
    			    chart3.series[0].setData(data);
    			});

    			$j('#b6').click(function () {
    			    var no = 2; //third element
    			    var data = [];
    			    for (i = 0; i < chart3.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart3.series[0].data[i].x, chart3.series[0].data[i].y]);
    			        } else if (i === no) {
    			            data.push([point.x, point.y]);
    			            data.push([chart3.series[0].data[i].x + 1, chart3.series[0].data[i].y]);
    			        } else if (i > no) {
    			            data.push([chart3.series[0].data[i].x + 1, chart3.series[0].data[i].y]);
    			        }
    			    }
    			    chart3.xAxis[0].setCategories(['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']);
    			    chart3.series[0].setData(data);
    			});




    					

    			var point = {
    			    x: null,
    			    y: null
    			};

    			var chart4 = new Highcharts.Chart({
    				
    				title: {
    			            text: '',
    			            x: -20 //center
    			        },

    			    chart: {
    			        renderTo: 'container4'
    			    },

    			    xAxis: {
    			        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    			    },

    			    series: [{
    					showInLegend:false,
    			        type: 'column',
    			        name: 'third',
    			        data: [95.6, 54.4, 29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1],
    					color: '#fc983f',
    			    }]
    			});
    			$j('#b7').click(function () {
    			    var no = 2; //third element
    			    // removed march, hoping we'd only show 11 months...
    			    chart4.xAxis[0].setCategories(['Jan', 'Feb', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'], false);
    			    var data = [];
    			    for (i = 0; i < chart4.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart4.series[0].data[i].x, chart4.series[0].data[i].y]);
    			        } else if (i === no) {
    			            point.x = chart4.series[0].data[i].x;
    			            point.y = chart4.series[0].data[i].y;
    			        } else if (i > no) {
    			            data.push([chart4.series[0].data[i].x - 1, chart4.series[0].data[i].y]);
    			        }
    			    }
    			    chart4.series[0].setData(data);
    			});

    			$j('#b8').click(function () {
    			    var no = 2; //third element
    			    var data = [];
    			    for (i = 0; i < chart4.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart4.series[0].data[i].x, chart4.series[0].data[i].y]);
    			        } else if (i === no) {
    			            data.push([point.x, point.y]);
    			            data.push([chart4.series[0].data[i].x + 1, chart4.series[0].data[i].y]);
    			        } else if (i > no) {
    			            data.push([chart4.series[0].data[i].x + 1, chart4.series[0].data[i].y]);
    			        }
    			    }
    			    chart4.xAxis[0].setCategories(['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']);
    			    chart4.series[0].setData(data);
    			});
       }
       
       
       function   intiateTop10Bar(){
    	   
    	   var point = {
    			    x: null,
    			    y: null
    			};

    			var chart = new Highcharts.Chart({
    				 title: {
    			            text: '',
    			            x: -20 //center
    			        },

    			    chart: {
    			        renderTo: 'container'
    			    },

    			    xAxis: {
    			        categories: []
    			    },

    			    series: [{
    					showInLegend:false,
    			        type: 'column',
    			        name: 'third',
    			        data: [95.6, 54.4, 29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1],
    					color: '#51bfe3',
    			    }]
    			});
    			$j('#b1').click(function () {
    			    var no = 2; //third element
    			    // removed march, hoping we'd only show 11 months...
    			    chart.xAxis[0].setCategories(['Jan', 'Feb', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'], false);
    			    var data = [];
    			    for (i = 0; i < chart.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart.series[0].data[i].x, chart.series[0].data[i].y]);
    			        } else if (i === no) {
    			            point.x = chart.series[0].data[i].x;
    			            point.y = chart.series[0].data[i].y;
    			        } else if (i > no) {
    			            data.push([chart.series[0].data[i].x - 1, chart.series[0].data[i].y]);
    			        }
    			    }
    			    chart.series[0].setData(data);
    			});

    			$j('#b2').click(function () {
    			    var no = 2; //third element
    			    var data = [];
    			    for (i = 0; i < chart.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart.series[0].data[i].x, chart.series[0].data[i].y]);
    			        } else if (i === no) {
    			            data.push([point.x, point.y]);
    			            data.push([chart.series[0].data[i].x + 1, chart.series[0].data[i].y]);
    			        } else if (i > no) {
    			            data.push([chart.series[0].data[i].x + 1, chart.series[0].data[i].y]);
    			        }
    			    }
    			    chart.xAxis[0].setCategories(['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']);
    			    chart.series[0].setData(data);
    			});
    						  
    						  


    			var point = {
    			    x: null,
    			    y: null
    			};

    			var chart2 = new Highcharts.Chart({
    				
    				title: {
    			            text: '',
    			            x: -20 //center
    			        },

    			    chart: {
    			        renderTo: 'container2'
    			    },

    			    xAxis: {
    			        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    			    },

    			    series: [{
    					showInLegend:false,
    			        type: 'column',
    			        name: 'third',
    			        data: [95.6, 54.4, 29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1],
    					color: '#41cac0',
    			    }]
    			});
    			$j('#b3').click(function () {
    			    var no = 2; //third element
    			    // removed march, hoping we'd only show 11 months...
    			    chart2.xAxis[0].setCategories(['Jan', 'Feb', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'], false);
    			    var data = [];
    			    for (i = 0; i < chart2.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart2.series[0].data[i].x, chart2.series[0].data[i].y]);
    			        } else if (i === no) {
    			            point.x = chart2.series[0].data[i].x;
    			            point.y = chart2.series[0].data[i].y;
    			        } else if (i > no) {
    			            data.push([chart2.series[0].data[i].x - 1, chart2.series[0].data[i].y]);
    			        }
    			    }
    			    chart2.series[0].setData(data);
    			});

    			$j('#b4').click(function () {
    			    var no = 2; //third element
    			    var data = [];
    			    for (i = 0; i < chart2.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart2.series[0].data[i].x, chart2.series[0].data[i].y]);
    			        } else if (i === no) {
    			            data.push([point.x, point.y]);
    			            data.push([chart2.series[0].data[i].x + 1, chart2.series[0].data[i].y]);
    			        } else if (i > no) {
    			            data.push([chart2.series[0].data[i].x + 1, chart2.series[0].data[i].y]);
    			        }
    			    }
    			    chart2.xAxis[0].setCategories(['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']);
    			    chart2.series[0].setData(data);
    			});


    					
    					


    			var point = {
    			    x: null,
    			    y: null
    			};

    			var chart3 = new Highcharts.Chart({
    				
    				title: {
    			            text: '',
    			            x: -20 //center
    			        },

    			    chart: {
    			        renderTo: 'container3'
    			    },

    			    xAxis: {
    			        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    			    },

    			    series: [{
    					showInLegend:false,
    			        type: 'column',
    			        name: 'third',
    			        data: [95.6, 54.4, 29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1],
    					color: '#8075c4',
    			    }]
    			});
    			$j('#b5').click(function () {
    			    var no = 2; //third element
    			    // removed march, hoping we'd only show 11 months...
    			    chart3.xAxis[0].setCategories(['Jan', 'Feb', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'], false);
    			    var data = [];
    			    for (i = 0; i < chart3.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart3.series[0].data[i].x, chart3.series[0].data[i].y]);
    			        } else if (i === no) {
    			            point.x = chart3.series[0].data[i].x;
    			            point.y = chart3.series[0].data[i].y;
    			        } else if (i > no) {
    			            data.push([chart3.series[0].data[i].x - 1, chart3.series[0].data[i].y]);
    			        }
    			    }
    			    chart3.series[0].setData(data);
    			});

    			$j('#b6').click(function () {
    			    var no = 2; //third element
    			    var data = [];
    			    for (i = 0; i < chart3.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart3.series[0].data[i].x, chart3.series[0].data[i].y]);
    			        } else if (i === no) {
    			            data.push([point.x, point.y]);
    			            data.push([chart3.series[0].data[i].x + 1, chart3.series[0].data[i].y]);
    			        } else if (i > no) {
    			            data.push([chart3.series[0].data[i].x + 1, chart3.series[0].data[i].y]);
    			        }
    			    }
    			    chart3.xAxis[0].setCategories(['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']);
    			    chart3.series[0].setData(data);
    			});




    					

    			var point = {
    			    x: null,
    			    y: null
    			};

    			var chart4 = new Highcharts.Chart({
    				
    				title: {
    			            text: '',
    			            x: -20 //center
    			        },

    			    chart: {
    			        renderTo: 'container4'
    			    },

    			    xAxis: {
    			        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    			    },

    			    series: [{
    					showInLegend:false,
    			        type: 'column',
    			        name: 'third',
    			        data: [95.6, 54.4, 29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1],
    					color: '#fc983f',
    			    }]
    			});
    			$j('#b7').click(function () {
    			    var no = 2; //third element
    			    // removed march, hoping we'd only show 11 months...
    			    chart4.xAxis[0].setCategories(['Jan', 'Feb', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'], false);
    			    var data = [];
    			    for (i = 0; i < chart4.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart4.series[0].data[i].x, chart4.series[0].data[i].y]);
    			        } else if (i === no) {
    			            point.x = chart4.series[0].data[i].x;
    			            point.y = chart4.series[0].data[i].y;
    			        } else if (i > no) {
    			            data.push([chart4.series[0].data[i].x - 1, chart4.series[0].data[i].y]);
    			        }
    			    }
    			    chart4.series[0].setData(data);
    			});

    			$j('#b8').click(function () {
    			    var no = 2; //third element
    			    var data = [];
    			    for (i = 0; i < chart4.series[0].data.length; i++) {
    			        if (i < no) {
    			            data.push([chart4.series[0].data[i].x, chart4.series[0].data[i].y]);
    			        } else if (i === no) {
    			            data.push([point.x, point.y]);
    			            data.push([chart4.series[0].data[i].x + 1, chart4.series[0].data[i].y]);
    			        } else if (i > no) {
    			            data.push([chart4.series[0].data[i].x + 1, chart4.series[0].data[i].y]);
    			        }
    			    }
    			    chart4.xAxis[0].setCategories(['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']);
    			    chart4.series[0].setData(data);
    			});

       }
	
	homeC.controller('RoamingStatisticsControllerHome',
			['$scope','$rootScope','$http','util',  function($scope,$rootScope,$http,util) {
				console.log("Call inside2");
				$scope.totalRoamer = 0;
				$scope.silentRoamer = 0;
				$scope.valueRoamer = 0;
				$scope.premiumRoamer = 0;
				
				$scope.totalMo = 0;
				$scope.homeMo=0;
				$scope.localMo = 0;
				$scope.intlMo=0;
				
				$scope.totalMt = 0;
				$scope.totalData=0;
				$scope.totalSms=0;
				
				var data = {
						'params' : util.getParamsFromFilter($rootScope.filters)
				};
				console.log("datamap"+$scope.totalRoamer);
				intiateMap(dataMap);
				intiateTop10Bubble();
				intiateTop10Bar();
				$http.get("getRoamingStatistics", data).success(function(result) {
					$scope.roamingStatistics = result;
					$scope.totalRoamer = result.totalRoamer;
					$scope.silentRoamer = result.silentRoamer;
					$scope.valueRoamer = result.valueRoamer;
					$scope.premiumRoamer = result.premiumRoamer;
					
					$scope.totalMo = result.totalMo;
					$scope.homeMo=result.homeMo;
					$scope.localMo = result.localMo;
					$scope.intlMo=result.intlMo;
					
					$scope.totalMt = result.totalMt;
					$scope.totalData=result.totalData;
					$scope.totalSms=result.totalSms;
					console.log("Call inside1");
				});
				
				
				$rootScope.$on('refresh-roaming-statistics', function (event) {
					
					console.log($rootScope.tabIndex);
					$scope.totalRoamer = 0;
					$scope.silentRoamer = 0;
					$scope.valueRoamer = 0;
					$scope.premiumRoamer = 0;
					
					$scope.totalMo = 0;
					$scope.homeMo=0;
					$scope.localMo = 0;
					$scope.intlMo=0;
					
					$scope.totalMt = 0;
					$scope.totalData=0;
					$scope.totalSms=0;
					console.log("Call inside2");
					var latestData = {
						'params' : util.getParamsFromFilter($rootScope.filters)
					};
					$http.get("getRoamingStatistics", latestData).success(function(result) {
						$scope.roamingStatistics = result;
						$scope.totalRoamer = result.totalRoamer;
						$scope.silentRoamer = result.silentRoamer;
						$scope.valueRoamer = result.valueRoamer;
						$scope.premiumRoamer = result.premiumRoamer;
						
						$scope.totalMo = result.totalMo;
						$scope.homeMo=result.homeMo;
						$scope.localMo = result.localMo;
						$scope.intlMo=result.intlMo;
						
						$scope.totalMt = result.totalMt;
						$scope.totalData=result.totalData;
						$scope.totalSms=result.totalSms;
						console.log("Call inside3");
					});
					
				});
				
				
				
				
						
				
		
	}]);
	
	homeC.controller('HeatMapController',
			['$scope','$rootScope','$http','util',  function($scope,$rootScope,$http,util) {
		
				
				var data = {
						'params' : util.getParamsFromFilter($rootScope.filters)
				};
				$http.get("getHeatMap", data).success(function(result) {
					
				});
				
				$rootScope.$on('refresh-heatmap-home', function (event) {
					
					
					var latestData = {
						'params' : util.getParamsFromFilter($rootScope.filters)
					};
					$http.get("getHeatMap", latestData).success(function(result) {
						
					});
				});
				
						
				
		
	}]);
	
	
	homeC.controller('BubbleChartController',
			['$scope','$rootScope','$http','util',  function($scope,$rootScope,$http,util) {
		
				
				var data = {
						'params' : util.getParamsFromFilter($rootScope.filters)
				};
				$http.get("getBubbleChart", data).success(function(result) {
					
				});
				
				$rootScope.$on('refresh-bubblechart-home', function (event) {
					
					
					var latestData = {
						'params' : util.getParamsFromFilter($rootScope.filters)
					};
					$http.get("getBubbleChart", latestData).success(function(result) {
						
					});
				});
				
						
				
		
	}]);
	
})();