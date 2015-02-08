GoogleAddressService = new function() {

	this.getAddress = function(lat, lng, cb) {
        REST.get(
        	'http://maps.googleapis.com/maps/api/geocode/json?latlng={lat},{lng}&sensor=false&language=ru',
        	{ lat: lat, lng: lng },
        	function(err, result) {
        		if (err || result.status != 'OK') {
        			return;
        		}
				var address = this.__extractAddress(result);
				cb(address);
        	}.bind(this), true);
	}

	this.__extractAddress = function(result) {
    	var address = result.results[0].address_components;
		if (address.length < 7) {
			return null;
		}

    	var administrativeArea = address[4].short_name
        var locality = address[2].short_name
        var street = address[1].short_name
        var streetNumber = address[0].short_name
		return {
            administrativeArea: administrativeArea,
            locality: locality,
            street: street,
            streetNumber: streetNumber
		};
	}
};