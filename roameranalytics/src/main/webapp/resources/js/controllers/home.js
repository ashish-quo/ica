(function(){
	var homeC = angular.module("app.home",[]);
	
		var roamerJsonMap=[];	
		var moJsonMap=[];
		var mtJsonMap=[];
		var dataJsonMap=[];
		var top10RoamerJson=[]
		var top10MoJson=[]
		var top10MtJson=[]
		var top10DataJson=[]
		var top10SmsJson=[]
		
		var top10roamerBarX = [];
		var top10moBarX = [];
		var top10mtBarX = [];
		var top10dataBarX = [];
		
		var top10roamerBarData = [];
		var top10moBarData = [];
		var top10mtBarData = [];
		var top10dataBarData = [];
		
		var colorAxisText= {
            dataClasses: [{
                to: 30,
				name: 'Positive'
            }, {
                from: 30,
                to: 65,
				name: 'Expected' 
            }, {
                from: 65,
                to: 100 ,
				name: 'Negative'
            }],
			minColor: '#87d796',
            maxColor: '#fca5ad'

        };
		
		var colorAxisRange={
            dataClasses: [{
                to: 3
            }, {
                from: 3,
                to: 10
            }, {
                from: 10,
                to: 30
            }, {
                from: 30,
                to: 100
            },{
                from: 100
            }],
				

        };
	/* Buuble Chart Intialization Start	*/
		var Bubbles, root, texts;

		root = typeof exports !== "undefined" && exports !== null ? exports : this;

		Bubbles = function() {
		  var chart, clear, click, collide, collisionPadding, connectEvents, data, force, gravity, hashchange, height, idValue, jitter, label, margin, maxRadius, minCollisionRadius, mouseout, mouseover, node, rScale, rValue, textValue, tick, transformData, update, updateActive, updateLabels, updateNodes, width;
		  width = 240;
		  height = 290;
		  data = [];
		  node = null;
		  label = null;
		  margin = {
		    top: 5,
		    right: 0,
		    bottom: 0,
		    left: 0
		  };
		  maxRadius = 40;
		  rScale = d3.scale.sqrt().range([0, maxRadius]);
		  rValue = function(d) {
		    return parseInt(d.count);
		  };
		  idValue = function(d) {
		    return d.name;
		  };
		  textValue = function(d) {
		    return d.name;
		  };
		  collisionPadding = 10;
		  minCollisionRadius = 12;
		  jitter = 0.5;
		  transformData = function(rawData) {
		    rawData.forEach(function(d) {
		      d.count = parseInt(d.count);
		      return rawData.sort(function() {
		        return 0.5 - Math.random();
		      });
		    });
		    return rawData;
		  };
		  tick = function(e) {
		    var dampenedAlpha;
		    dampenedAlpha = e.alpha * 0.1;
		    node.each(gravity(dampenedAlpha)).each(collide(jitter)).attr("transform", function(d) {
		      return "translate(" + d.x + "," + d.y + ")";
		    });
		    return label.style("left", function(d) {
		      return ((margin.left + d.x) - d.dx / 2) + "px";
		    }).style("top", function(d) {
		      return ((margin.top + d.y) - d.dy / 2) + "px";
		    });
		  };
		  force = d3.layout.force().gravity(0).charge(0).size([width, height]).on("tick", tick);
		  chart = function(selection) {
		    return selection.each(function(rawData) {
		      var maxDomainValue, svg, svgEnter;
		      data = transformData(rawData);
		      maxDomainValue = d3.max(data, function(d) {
		        return rValue(d);
		      });
		      rScale.domain([0, maxDomainValue]);
		      svg = d3.select(this).selectAll("svg").data([data]);
		      svgEnter = svg.enter().append("svg");
		      svg.attr("width", width + margin.left + margin.right);
		      svg.attr("height", height + margin.top + margin.bottom);
		      node = svgEnter.append("g").attr("id", "bubble-nodes").attr("transform", "translate(" + margin.left + "," + margin.top + ")");
		      node.append("rect").attr("id", "bubble-background").attr("width", width).attr("height", height).on("click", clear);
		      label = d3.select(this).selectAll("#bubble-labels").data([data]).enter().append("div").attr("id", "bubble-labels");
		      update();
		      hashchange();
		      d3.select(this).selectAll("circle").append("svg:title")
		      .text(function(d) { 
		    	  return d.name; });
		      d3.select(this).selectAll("div.bubble-label-name").attr("title",function(d) { 
		    	  return d.name; });
		      
		      return d3.select(window).on("hashchange", hashchange);
		    });
		  };
		  update = function() {
		    data.forEach(function(d, i) {
		      return d.forceR = Math.max(minCollisionRadius, rScale(rValue(d)));
		    });
		    force.nodes(data).start();
		    updateNodes();
		    return updateLabels();
		  };
		  updateNodes = function() {
		    node = node.selectAll(".bubble-node").data(data, function(d) {
		      return idValue(d);
		    });
		    node.exit().remove();
		    return node.enter().append("a").attr("class", "bubble-node").attr("xlink:href", function(d) {
		      return "#" + (encodeURIComponent(idValue(d)));
		    }).call(force.drag).call(connectEvents).append("circle").attr("r", function(d) {
		      return rScale(rValue(d));
		    });
		  };
		  updateLabels = function() {
		    var labelEnter;
		    label = label.selectAll(".bubble-label").data(data, function(d) {
		      return idValue(d);
		    });
		    label.exit().remove();
		    labelEnter = label.enter().append("a").attr("class", "bubble-label").attr("href", function(d) {
		      return "#" + (encodeURIComponent(idValue(d)));
		    }).call(force.drag).call(connectEvents);
		    labelEnter.append("div").attr("class", "bubble-label-name").attr("dy", ".3em").style("text-anchor", "middle").text(function(d) {
		      return textValue(d).substring(0, Math.sqrt(rScale(rValue(d))))
		    });
		    label.style("font-size", function(d) {
		      return Math.max(8, rScale(rValue(d) / 2)) + "px";
		    }).style("width", function(d) {
		      return 2.5 * rScale(rValue(d)) + "px";
		    });
		    label.append("span").text(function(d) {
		      return textValue(d);
		    }).each(function(d) {
		      return d.dx = Math.max(2.5 * rScale(rValue(d)), this.getBoundingClientRect().width);
		    }).remove();
		    label.style("width", function(d) {
		      return d.dx + "px";
		    });
		    return label.each(function(d) {
		      return d.dy = this.getBoundingClientRect().height;
		    });
		  };
		  gravity = function(alpha) {
		    var ax, ay, cx, cy;
		    cx = width / 2;
		    cy = height / 2;
		    ax = alpha;
		    ay = alpha / 4;
		    return function(d) {
		      d.x += (cx - d.x) * ax;
		      return d.y += (cy - d.y) * ay;
		    };
		  };
		  collide = function(jitter) {
		    return function(d) {
		      return data.forEach(function(d2) {
		        var distance, minDistance, moveX, moveY, x, y;
		        if (d !== d2) {
		          x = d.x - d2.x;
		          y = d.y - d2.y;
		          distance = Math.sqrt(x * x + y * y);
		          minDistance = d.forceR + d2.forceR + collisionPadding;
		          if (distance < minDistance) {
		            distance = (distance - minDistance) / distance * jitter;
		            moveX = x * distance;
		            moveY = y * distance;
		            d.x -= moveX;
		            d.y -= moveY;
		            d2.x += moveX;
		            return d2.y += moveY;
		          }
		        }
		      });
		    };
		  };
		  connectEvents = function(d) {
		    d.on("click", click);
		    d.on("mouseover", mouseover);
		    return d.on("mouseout", mouseout);
		  };
		  clear = function() {
		    return location.replace("#");
		  };
		  click = function(d) {
		    location.replace("#" + encodeURIComponent(idValue(d)));
		    return d3.event.preventDefault();
		  };
		  hashchange = function() {
		    var id;
		    id = decodeURIComponent(location.hash.substring(1)).trim();
		    return updateActive(id);
		  };
		  updateActive = function(id) {
		    node.classed("bubble-selected", function(d) {
		      return id === idValue(d);
		    });
		    if (id.length > 0) {
		      return d3.select("#status").html("<h3>The word <span class=\"active\">" + id + "</span> is now active</h3>");
		    } else {
		      return d3.select("#status").html("<h3>No word is active</h3>");
		    }
		  };
		  mouseover = function(d) {
		    return node.classed("bubble-hover", function(p) {
		      return p === d;
		    });
		  };
		  mouseout = function(d) {
		    return node.classed("bubble-hover", false);
		  };
		  chart.jitter = function(_) {
		    if (!arguments.length) {
		      return jitter;
		    }
		    jitter = _;
		    force.start();
		    return chart;
		  };
		  chart.height = function(_) {
		    if (!arguments.length) {
		      return height;
		    }
		    height = _;
		    return chart;
		  };
		  chart.width = function(_) {
		    if (!arguments.length) {
		      return width;
		    }
		    width = _;
		    return chart;
		  };
		  chart.r = function(_) {
		    if (!arguments.length) {
		      return rValue;
		    }
		    rValue = _;
		    return chart;
		  };
		  return chart;
		};

		root.plotData = function(selector, data, plot) {
		  $j(selector).html("");
		  return d3.select(selector).datum(data).call(plot);
		};

		texts = [
		  {
		    key: "sherlock",
		    file: "top_sherlock.csv",
		    name: "The Adventures of Sherlock Holmes"
		  }
		];
	/* Bubble Chart Intialization End */	
		
	   
	   // Initiate the HeatMap
       function initiateMap(data,colorAxisJson,suffixLable,nameLable)
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

           colorAxis: colorAxisJson,
	
			
           series : [{
               data : data,
               mapData: Highcharts.maps['custom/world'],
		joinBy: ['name', 'countryName'],
                   name: nameLable,
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
                   valueSuffix: suffixLable
               }
           }],
           credits: {
        	      enabled: false
        	  }
       });
console.log("Inside mapcore");
       }
       
       
       // Initiate the Top10 Bar Chart
       function initiateTop10Bar(selector,dataTop10Bar,xAxis,suffixLable){
    	   $j(selector).html("");
    	   var subtitle='';
    	   if(dataTop10Bar.length <= 0){
    		   subtitle = 'No Data Found';
    	   }
    	   
    	   var chart = new Highcharts.Chart({
    			 title: {
    		            text: '',
    		            x: -20 //center
    		        },
    		        subtitle: {
    	                text: subtitle,
    	                x: 5, 
						y: 145
    		        },
    		    chart: {
    		        renderTo: selector
    		    },

    		    xAxis: {
    		        categories: xAxis
    		    },
    		    series: [{
    				showInLegend:false,
    		        type: 'column',
    		        name: suffixLable,
    		        data: dataTop10Bar,
    				color: '#51bfe3',
    		    }],
    		    credits: {
    		        enabled: false
    		    }
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
				
				$http.get($scope.roamType +"/getRoamingStatistics", data).success(function(result) {
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
				});
				
				
				$rootScope.$on('refresh-roaming-statistics', function (event) {
					
					
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
					var latestData = {
						'params' : util.getParamsFromFilter($rootScope.filters)
					};
					$http.get($scope.roamType + "/getRoamingStatistics", latestData).success(function(result) {
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
					});
					
				});
	}]);
	
	
	
	
	homeC.controller('HeatMapControllerHome',
			['$scope','$rootScope','$http','util',  function($scope,$rootScope,$http,util) {
		
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
				
				$scope.top10CsvText='';
				$scope.mapUnit = 'roamers';
				
				function setTop10CSVData(countryData)
				{
					var header='Country,Total Roamer,Silent Roamer,Value Roamer,Premium Roamer,Total MO,Local MO,Home MO,Intl Mo,MT,Data Usage,Sms\r\n';
					for(var i=0;i<countryData.length;i++) {
						
						header=header+''+countryData[i].countryCode+','+countryData[i].roamerTotal
						+','+countryData[i].roamerSilent+','+countryData[i].roamerValue+','
						+countryData[i].roamerPremium+','+countryData[i].moTotal+','
						+countryData[i].moLocal+','+countryData[i].moHome+','+countryData[i].moIntl+','
						+countryData[i].mt+','+countryData[i].dataUsage+','+countryData[i].smsUsage+'\r\n';
					}
					
					$scope.top10CsvText=header;
					
					
				}
				
				function displayTop10Bubbles(top10RoamerJson,top10MoJson,top10MtJson,top10DataJson,top10SmsJson,removeHitterCount)
				{
						top10roamerJsonMap = [];
						top10moJsonMap = [];
						top10mtJsonMap = [];
						top10dataJsonMap = [];
						
						top10roamerBarData = [];
						top10moBarData = [];
						top10mtBarData = [];
						top10dataBarData = [];
						
						
						angular.forEach(top10RoamerJson, function(countryData) {
							
							if(countryData.roamerTotal>0){
								top10roamerJsonMap.push({
									name : countryData.countryCode,
									count : countryData.roamerTotal
								});
								top10roamerBarX.push(countryData.countryCode);
								top10roamerBarData.push(countryData.roamerTotal)
							}
							});
						
						angular.forEach(top10MoJson, function(countryData) {
							if(countryData.moTotal>0){
								top10moJsonMap.push({
									name : countryData.countryCode,
									count : countryData.moTotal
								});
								top10moBarX.push(countryData.countryCode);
								top10moBarData.push(countryData.moTotal);
							}
							
							});
						
						angular.forEach(top10MtJson, function(countryData) {
							if(countryData.mt>0){
								top10mtJsonMap.push({
									name : countryData.countryCode,
									count : countryData.mt
								});
								top10mtBarX.push(countryData.countryCode);
								top10mtBarData.push(countryData.mt);
							}
							});
						angular.forEach(top10DataJson, function(countryData) {
							if(countryData.dataUsage>0){
								top10dataJsonMap.push({
									name : countryData.countryCode,
									count : countryData.dataUsage
								});
								top10dataBarX.push(countryData.countryCode);
								top10dataBarData.push(countryData.dataUsage);
							}
							});
						

						var display, key, plot, text;
						plot = Bubbles();
						plotMo = Bubbles();
						plotMt = Bubbles();
						plotDataUsage = Bubbles();
						
						

						displayRoamer = function(data) {
							return plotData("#vis", data, plot);
						};
						displayMo = function(data) {
							return plotData("#vis2", data, plotMo);
						};
						displayMt = function(data) {
							return plotData("#vis3", data, plotMt);
						};

						displayData = function(data) {
							return plotData("#vis4", data,
									plotDataUsage);
						};
						
						plotHitter = Bubbles();
						plotMoHitter = Bubbles();
						plotMtHitter = Bubbles();
						plotDataUsageHitter = Bubbles();
						displayRoamerHitter = function(data) {
							return plotData("#vis1", data, plotHitter);
						};
						displayMoHitter = function(data) {
							return plotData("#vis2n", data, plotMoHitter);
						};
						displayMtHitter = function(data) {
							return plotData("#vis3n", data, plotMtHitter);
						};

						displayDataHitter = function(data) {
							return plotData("#vis4n", data,
									plotDataUsageHitter);
						};

						key = decodeURIComponent(location.search)
						.replace("?", "");
						text = texts.filter(function(t) {
							return t.key === key;
						})[0];
						if (!text) {
							text = texts[0];
						}
						$j("#text-select").val(key);
						d3
						.select("#jitter")
						.on(
								"input",
								function() {
									plot
									.jitter(parseFloat(this.output.value));
									plotMo
									.jitter(parseFloat(this.output.value));
									plotMt
									.jitter(parseFloat(this.output.value));
								});
						d3
						.select("#text-select")
						.on(
								"change",
								function(e) {
									key = $j(this).val();
									location.replace("#");
									return location.search = encodeURIComponent(key);
								});
						d3.select("#book-title").html(text.name);
						d3.json($scope.roamType + '/getBubbleChartJson?data='+ JSON.stringify(top10roamerJsonMap),displayRoamer);
						d3.json($scope.roamType + '/getBubbleChartJson?data='+ JSON.stringify(top10moJsonMap), displayMo);
						d3.json($scope.roamType + '/getBubbleChartJson?data='+ JSON.stringify(top10mtJsonMap), displayMt);
						d3.json($scope.roamType + '/getBubbleChartJson?data='+ JSON.stringify(top10dataJsonMap),displayData);
						
						d3.json($scope.roamType + '/getBubbleChartJson?data='+ JSON.stringify(top10roamerJsonMap.slice(removeHitterCount,roamerJsonMap.length)),displayRoamerHitter);
						d3.json($scope.roamType + '/getBubbleChartJson?data='+ JSON.stringify(top10moJsonMap.slice(removeHitterCount,moJsonMap.length)), displayMoHitter);
						d3.json($scope.roamType + '/getBubbleChartJson?data='+ JSON.stringify(top10mtJsonMap.slice(removeHitterCount,mtJsonMap.length)), displayMtHitter);
						d3.json($scope.roamType + '/getBubbleChartJson?data='+ JSON.stringify(top10dataJsonMap.slice(removeHitterCount,dataJsonMap.length)),displayDataHitter);
						
						initiateTop10Bar('container',top10roamerBarData,top10roamerBarX,'Roamer Count');
						initiateTop10Bar('container2',top10moBarData,top10moBarX,'MO (Minute)');
						initiateTop10Bar('container3',top10mtBarData,top10mtBarX,'MT (Minute)');
						initiateTop10Bar('container4',top10dataBarData,top10dataBarX,'Data (MB)');
						
						
					
				}
				
				function setHeatMapJson(result)
				{
					
					roamerJsonMap=[];	
					moJsonMap=[];
					mtJsonMap=[];
					dataJsonMap=[];
					
					var sortedRoamer =[];
					 var sortedMo=[];
					 var sortedMt =[];
					 var sortedData =[];
					 var sortedSms =[];
					 var top10CSVobjectsTemp=[];
					 var top10CSVobjects=[];
					
					angular.forEach(result, function(countryData) {
						
						roamerJsonMap.push({
					        'countryName': countryData.countryCode,
					        value: countryData.roamerTotal
					    });
						
						moJsonMap.push({
					        'countryName': countryData.countryCode,
					        value: countryData.moTotal
					    });
						
						mtJsonMap.push({
					        'countryName': countryData.countryCode,
					        value: countryData.mt
					    });
						
						dataJsonMap.push({
					        'countryName': countryData.countryCode,
					        value: countryData.dataUsage
					    });
						
						sortedRoamer.push(countryData);
						sortedMo.push(countryData);
						sortedMt .push(countryData);
						sortedData.push(countryData);
						sortedSms .push(countryData);
						
						$scope.totalRoamer = $scope.totalRoamer + parseInt(countryData.roamerTotal, 10);
						$scope.silentRoamer = $scope.silentRoamer + parseInt(countryData.roamerSilent, 10);
						$scope.valueRoamer = $scope.valueRoamer + parseInt(countryData.roamerValue, 10);
						$scope.premiumRoamer = $scope.premiumRoamer + parseInt(countryData.roamerPremium, 10);
						
						$scope.totalMo = $scope.totalMo + parseInt(countryData.moTotal, 10);
						$scope.homeMo=$scope.homeMo + parseInt(countryData.moHome, 10);
						$scope.localMo = $scope.localMo + parseInt(countryData.moLocal, 10);
						$scope.intlMo=$scope.intlMo + parseInt(countryData.moIntl, 10);
						
						$scope.totalMt = $scope.totalMt + parseInt(countryData.mt, 10);
						$scope.totalData=$scope.totalData + parseInt(countryData.dataUsage, 10);
						$scope.totalSms=$scope.totalSms + parseInt(countryData.smsUsage, 10);
						
					});
					
					 
					 
					 sortedRoamer.sort(function(a, b){
					        return parseInt(b['roamerTotal'],10) - parseInt(a['roamerTotal'],10);
					    });
					 
					 sortedMt.sort(function(a, b){
						 return parseInt(b['mt'],10) - parseInt(a['mt'],10);
					    });
					 sortedMo.sort(function(a, b){
						 return parseInt(b['moTotal'],10) - parseInt(a['moTotal'],10);
					    });
					 sortedData.sort(function(a, b){
						 return parseInt(b['dataUsage'],10) - parseInt(a['dataUsage'],10);
					    });
					 sortedSms.sort(function(a, b){
						 return parseInt(b['smsUsage'],10) - parseInt(a['smsUsage'],10);
					    });
					
					 	top10RoamerJson=JSON.stringify(sortedRoamer.slice(0, 10));
						top10MoJson=JSON.stringify(sortedMo.slice(0, 10));
						top10MtJson=JSON.stringify(sortedMt.slice(0, 10));
						top10DataJson=JSON.stringify(sortedData.slice(0, 10));
						top10SmsJson=JSON.stringify(sortedSms.slice(0, 10));
						/* function to initiate top10 Bubble */
						displayTop10Bubbles(sortedRoamer.slice(0, 10),sortedMo.slice(0, 10),sortedMt.slice(0, 10),sortedData.slice(0, 10),sortedSms.slice(0, 10),1);
						
						top10CSVobjectsTemp=sortedRoamer.slice(0, 10).concat(sortedMo.slice(0, 10),sortedMt.slice(0, 10),sortedData.slice(0, 10));
						
						/* get unique set of top 10 objects */
						var unique = {};
						var distinct = [];
						for( var i in top10CSVobjectsTemp ){
						if( typeof(unique[top10CSVobjectsTemp[i].countryCode]) == "undefined"){
							 top10CSVobjects.push(top10CSVobjectsTemp[i]);
						 }
						 unique[top10CSVobjectsTemp[i].countryCode] = '';
						}
						
						
						if(top10CSVobjects.length>0)	
							setTop10CSVData(top10CSVobjects);
						
						
						sortedRoamer =[];
						 sortedMo=[];
						sortedMt =[];
						 sortedData =[];
						 sortedSms =[];
					 
			}
				
				var data = {
						'params' : util.getParamsFromFilter($rootScope.filters)
				};
				$http.get($scope.roamType +"/getHeatMap", data).success(function(result) {
							
					setHeatMapJson(result);
					initiateMap(roamerJsonMap,colorAxisRange,'','Roamer count');
					
				});
				
				$rootScope.$on('refresh-heatmap-home', function (event) {
					
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
					
					var latestData = {
						'params' : util.getParamsFromFilter($rootScope.filters)
					};
					$http.get($scope.roamType  + "/getHeatMap", latestData).success(function(result) {
						
						setHeatMapJson(result);
						if ($scope.mapUnit=='roamers') {
							initiateMap(roamerJsonMap,colorAxisRange,'','Roamer Count');
						}else if ($scope.mapUnit=='mt') {
							initiateMap(mtJsonMap,colorAxisRange,'','MT Count');
						}else if ($scope.mapUnit=='mo') {
							initiateMap(moJsonMap,colorAxisRange,'','MO Count');
						}else if ($scope.mapUnit=='data') {
							initiateMap(dataJsonMap,colorAxisRange,' MB','Data Usage');
						}else{
							initiateMap(roamerJsonMap,colorAxisRange,'','Roamer Count');
						}
					});
				});
				
				$scope.$watch("mapUnit", function (newValue, oldValue) {
					if ($scope.mapUnit=='roamers') {
						initiateMap(roamerJsonMap,colorAxisRange,'','Roamer Count');
					}else if ($scope.mapUnit=='mt') {
						initiateMap(mtJsonMap,colorAxisRange,'','MT Count');
					}else if ($scope.mapUnit=='mo') {
						initiateMap(moJsonMap,colorAxisRange,'','MO Count');
					}else if ($scope.mapUnit=='data') {
						initiateMap(dataJsonMap,colorAxisRange,' MB','Data Usage');
					}else{
						initiateMap(roamerJsonMap,colorAxisRange,'','Roamer Count');
					}
					
				});
				
						
				
		
	}]);
	
	
	$j('#roamer-chart').live('click',function(){
		 if ($j(this).is(':checked')){
			 initiateTop10Bar('container',top10roamerBarData.slice(1,top10roamerBarData.length),top10roamerBarX.slice(1,top10roamerBarX.length),'Roamer Count');
		 }else{
			 initiateTop10Bar('container',top10roamerBarData,top10roamerBarX,'Roamer Count');
 
		 }
	});
	
	$j('#mo-chart').live('click',function(){
		 if ($j(this).is(':checked')){
			 initiateTop10Bar('container2',top10moBarData.slice(1,top10moBarData.length),top10moBarX.slice(1,top10moBarX.length),'MO (Minute)');
		 }else{
			 initiateTop10Bar('container2',top10moBarData,top10moBarX,'Roamer Count');

		 }
	});
	
	$j('#mt-chart').live('click',function(){
		 if ($j(this).is(':checked')){
			 initiateTop10Bar('container3',top10mtBarData.slice(1,top10mtBarData.length),top10mtBarX.slice(1,top10mtBarX.length),'MT (Minute)');
		 }else{
			 initiateTop10Bar('container3',top10mtBarData,top10mtBarX,'Roamer Count');

		 }
	});
	
	$j('#data-chart').live('click',function(){
		 if ($j(this).is(':checked')){
			 initiateTop10Bar('container4',top10dataBarData.slice(1,top10dataBarData.length),top10dataBarX.slice(1,top10dataBarX.length),'Data (MB)');
		 }else{
			 initiateTop10Bar('container4',top10dataBarData,top10dataBarX,'Roamer Count');

		 }
	});
	
})();
