var Map = function(createInfoWindowCallback, onMapClick, onMapRightClick) {

    this.__map = createMap('#map', 55.285, 80.233, 8, onMapClick, onMapRightClick);
    this.__infoWindows = [];

    this.addObject = function(object) {
        var marker = this.__map.addMarker({
            lat: object.marker.lat,
            lng: object.marker.lng,
            title: object.name,
            icon: '/img/icon/marker.png',
            rightclick: function(e) {
                alert('You RC in this marker');
            }
        });

        var infoWindow = createInfoWindowCallback(object, marker);
        this.__infoWindows.push(infoWindow);
        google.maps.event.addListener(marker, 'click', function() {
            this.__infoWindows.forEach(function(iw) { iw.close(); });

            infoWindow.open(this.__map.map, marker);
        }.bind(this));
    }
}

function createMap(id, lat, lng, zoom, click, rightclick) {
    var map = new GMaps({
        div: id,
        lat: lat,
        lng: lng,
        zoom: zoom,
        mapTypeControlOptions: {
            mapTypeIds : ['osm', 'roadmap', 'satellite']
        },

        click: click,
        rightclick: rightclick
    });

	map.addMapType('osm', {
		getTileUrl: function(coord, zoom) {
			return 'http://tile.openstreetmap.org/' + zoom + '/' + coord.x + '/' + coord.y + '.png';
		},
		tileSize: new google.maps.Size(256, 256),
		name: 'OpenStreetMap',
		maxZoom: 18
	});

	return map;
}