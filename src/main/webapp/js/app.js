$(function() {
	var MODES = {
		NONE: 0,
		NEW_MARKER_MODE: 1,
		EDIT_MARKER_MODE: 2,
		VIEW_MARKER_MODE: 3
	};

	function onMapClick(e) {
		switch (__mode) {
			case MODES.NEW_MARKER_MODE:
				$('#newMarkerBtnHelper').addClass('hidden');
				$('#objectModal').modal('show');

				__mode = MODES.NONE;
				__editedObject = emptyObject(e.latLng.lat(), e.latLng.lng())
				break;
		}
	}

	function onMapRightClick(e) {
		__mode = MODES.NONE;

		$('#newMarkerBtnHelper').addClass('hidden');

		__editedObject = undefined;
	}

	function saveObject() {
		$('#objectFieldError').addClass('hidden');
		$('#objectServerError').addClass('hidden');

		if (__mode !== MODES.NEW_MARKER_MODE && __mode !== MODES.EDIT_MARKER_MODE) {
			return;
		}

		__editedObject.name = $('#objName').val();
		__editedObject.type = $('#objType').val();

		if (!__editedObject.name ||
			!__editedObject.type) {
			$('#objectFieldError').removeClass('hidden');
			return false;
		}

		$.ajax({
			url: '/object',
			method: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(__editedObject)
		}).done(function(res) {
			$('#objectModal').modal('toggle');
		}).fail(function() {
			$('#objectServerError').removeClass('hidden');
		});
	}

    var __editedObject = undefined;

    var __mode = MODES.NONE;
	var map = createMap('#map', 55.285, 80.233, 8, onMapClick.bind(this), onMapRightClick.bind(this));

	$('#newMarkerBtn').on('click', function() {
		__mode = MODES.NEW_MARKER_MODE;
		$('#newMarkerBtnHelper').removeClass('hidden');
	});

	$('#saveObjectBtn').on('click', saveObject.bind(this));
});

function emptyObject(_lat, _lng) {
	return {
		marker: {
			id: undefined,
			lat: _lat ? _lat : undefined,
			lng: _lng ? _lng : undefined
		},

		id: undefined,
		name: undefined,
		type: undefined
	};
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
	map.setMapTypeId('osm');

	return map;
}