// Rend les lignes cliquables
jQuery(document).ready(function($) {
	$(".clickable-row").click(function() {
        window.location = $(this).data("href");
	});
});