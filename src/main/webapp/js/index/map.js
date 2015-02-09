var Map = function(onMapClick, onMapRightClick) {

    this.__mode = undefined;
    this.__map = createMap('#map', 55.285, 80.233, 8, onMapClick, onMapRightClick);

    this.addObject = function(object) {
        var marker = object.marker;
        this.__map.addMarker({
            lat: marker.lat,
            lng: marker.lng,
            title: object.name,
            icon: '/img/icon/marker.png',
            click: function(e) {
                alert('You clicked in this marker');
            },
            rightclick: function(e) {
                alert('You RC in this marker');
            }
        });
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