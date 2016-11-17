var threshold = 0;

var stockDataset = {
		label: "",
		backgroundColor: 'rgba(0, 255, 0, 0.5)',
        borderColor: 'rgb(0, 255, 0)',
        fill: false,
        data: []
};

var thresholdDataset = {
	  data: [],
	  fill: true,
	  radius: 0,
	  borderColor: "rgb(255, 255, 0)",
	  backgroundColor: "rgba(255,255,0, 0.2)"
};

var config = {
	type: 'line',
	data: {
		labels: [],
		datasets: [stockDataset, thresholdDataset]
	},
	options: {
        title:{
            text: "Niveau d'inventaire selon le temps",
        },
        legend: {
            display: false
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
					labelString: 'Temps'
				}
			}, ],
			yAxes: [{
				scaleLabel: {
					display: true,
					labelString: 'Quantité'
				}
			}]
		},
	}
};

function updateGraph() {
	$.getJSON("rest/product/history/" + productId, function(data) {
    	$.each(data.stockDataPoint, function(key, value) {
    		stockDataset.data.push({"x": moment(value.time), "y": value.quantity});
    		thresholdDataset.data.push({"x": moment(value.time), "y": threshold});
    	});
    	
    	window.myLine = new Chart($("#canvas"), config);
    	$("#stockchart").removeClass("hidden");
    });
}