$(function() {
    var MODES = {
        NONE: 0,
        NEW_MARKER_MODE: 1,
        EDIT_MARKER_MODE: 2,
        VIEW_MARKER_MODE: 3
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

    function addObject(object) {
        var id = object.id;
        if (!__objects[id]) {
            map.addObject(object);
        }
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

    function loadObjectTypes(cb) {
    	REST.get('/objectType', function(err, types) {
    		if (err) {
    			alert(err);
    			return;
    		}

    		cb(types);
    	});
    }

    // toolbar listeners
    $('#newMarkerBtn').on('click', function() {
        __mode = MODES.NEW_MARKER_MODE;
        $('#newMarkerBtnHelper').removeClass('hidden');
    });

    var __objects = {};

    var map = new Map(onMapClick.bind(this), onMapRightClick.bind(this));
    var objectModal = new ObjectModal(addObject.bind(this));

    loadMarkers(function(objects) {
        objects.forEach(addObject.bind(this));
    }.bind(this));

    loadObjectTypes(objectModal.fillObjectTypes);
});