$j(function () {
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

$('#b4').click(function () {
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

		});