var stockDataset = {
		label: "",
		backgroundColor: 'rgba(0, 255, 0, 0.5)',
        borderColor: 'rgb(0, 255, 0)',
        fill: false,
        data: []
};

var config = {
	type: 'line',
	data: {
		labels: [],
		datasets: [stockDataset]
	},
	options: {
        title:{
            text: "Stock over time",
        },
		scales: {
			xAxes: [{
				type: "time",
				time: {
					format: 'MM/DD/YYYY HH:mm',
					// round: 'day'
					tooltipFormat: 'll HH:mm'
				},
				scaleLabel: {
					display: true,
					labelString: 'Date'
				}
			}, ],
			yAxes: [{
				scaleLabel: {
					display: true,
					labelString: 'Stock'
				}
			}]
		},
	}
};

function updateGraph() {
	$.getJSON("rest/product/history/" + productId, function(data) {
    	$.each(data.stockDataPoint, function(key, value) {
    		stockDataset.data.push({"x": moment(value.time), "y": value.quantity});
    	});
    	
    	window.myLine = new Chart($("#canvas"), config);
    	$("#stockchart").removeClass("hidden");
    });
}