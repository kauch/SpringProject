var myMap;
ymaps.ready(init);
function init() {
	myMap = new ymaps.Map('map', {
		center: [51.654215, 39.193193],
		zoom: 18
		}, {
	searchControlProvider: 'yandex#search'
	});
}