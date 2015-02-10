$(function() {
    var MODES = {
        NONE: 0,
        NEW_MARKER_MODE: 1
    };
    var __mode = MODES.NONE;

    function onMapClick(e) {
        switch (__mode) {
            case MODES.NEW_MARKER_MODE:
                $('#newMarkerBtnHelper').addClass('hidden');
                var editedObject = emptyObject(e.latLng.lat(), e.latLng.lng());
                objectModal.show(editedObject);

                break;
        }

        __mode = MODES.NONE;
    }

    function onMapRightClick(e) {
        __mode = MODES.NONE;

        $('#newMarkerBtnHelper').addClass('hidden');

        __editedObject = undefined;
    }

    function emptyObject(_lat, _lng) {
    	return {
    		marker: {
    			id: undefined,
    			lat: _lat ? _lat : undefined,
    			lng: _lng ? _lng : undefined
    		},

    		address: undefined,

    		id: undefined,
    		name: undefined,
    		type: undefined
    	};
    }

    function addObject(object, marker) {
		if (marker) {
			marker.setMap(null);
		}
        var id = object.id;
		map.addObject(object);

        __objects[id] = object;
    }

    function loadMarkers(cb) {
    	REST.get('/object', function(err, objects) {
    		if (err) {
    			alert(err);
    			return;
    		}

    		cb(objects);
    	});
    }

    function fillMap() {
        loadMarkers(function(objects) {
            objects.forEach(function(o) {
            	addObject(o);
			}.bind(this));
        }.bind(this));
    }

    function loadObjectTypes(cb) {
    	REST.get('/objectType', function(err, types) {
    		if (err) {
    			alert(err);
    			return;
    		}

    		cb(types);
    	});
    }

    function createMarkerInfo(object, marker) {
        var info = new google.maps.InfoWindow({
            content:
                '<h4>'+ object.name +'</h4>'+
                '<div>'+
                    '<p><b>Тип: </b>' + __objectTypes[object.type] + '</p>' +
                    '<div class="btn-group">' +
                        '<button id="editObjectBtn" class="btn btn-default btn-xs">Редактировать</button>' +
                        '<button id="viewObjectBtn" class="btn btn-info btn-xs">Просмотр</button>' +
                    '</div>' +
                '</div>'
        });

        google.maps.event.addListener(info, 'domready', function() {
            $('#editObjectBtn').on('click', editObj.bind(this, object, marker));
            $('#viewObjectBtn').on('click', viewObj.bind(this, object));
        }.bind(this));

        return info;
    }

    function editObj(object, marker) {
        objectModal.show(object, marker);
    }

    function viewObj(object) {
		objectModal.show(object, null, true);
    }

    // toolbar listeners
    $('#newMarkerBtn').on('click', function() {
        __mode = MODES.NEW_MARKER_MODE;
        $('#newMarkerBtnHelper').removeClass('hidden');
    });

    var __objects = {};
    var __objectTypes = {};

    var map = new Map(createMarkerInfo.bind(this), onMapClick.bind(this), onMapRightClick.bind(this));
    var objectModal = new ObjectModal(addObject.bind(this));

    loadObjectTypes(function(types) {
        types.forEach(function(t) {
            __objectTypes[t.id] = t.name;
        });
        objectModal.fillObjectTypes(types);
    }.bind(this));

    fillMap();
});