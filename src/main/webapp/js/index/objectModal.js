var ObjectModal = function(saveCallback) {
    this.__view = false;

    this.__editedObject = null;
    this.__mapMarker = null;

    this.__saveCallback = saveCallback;

    this.show = function(object, mapMarker, viewMode) {
        this.__view = !!viewMode;
        this.__editedObject = object;
        this.__mapMarker = mapMarker;

        this.__fillFields(object);
        this.__viewMode(viewMode);
        $('#objectModal').modal('show');
    }

    this.fillObjectTypes = function(types) {
        var select = $('#objType');
        select.find('option').remove();

        types.forEach(function(t) {
            var option = $('<option>');
            option.text(t.name);
            option.attr('value', t.id);
            select.append(option);
        });
    }

    this.__saveObject = function() {
        $('#objectFieldError').addClass('hidden');
        $('#objectServerError').addClass('hidden');

        this.__editedObject.name = $('#objName').val();
        this.__editedObject.type = $('#objType').val();

        if ($('#addressChBox').is(':checked')) {
            this.__editedObject.address = {};
            this.__editedObject.address.administrativeArea = $('#administrativeArea').val();
            this.__editedObject.address.locality = $('#locality').val();
            this.__editedObject.address.street = $('#street').val();
            this.__editedObject.address.streetNumber = $('#streetNumber').val();
        } else {
            this.__editedObject.address = undefined;
        }

		var address = this.__editedObject.address;
        if (!this.__editedObject.name ||
            !this.__editedObject.type ||
			address && (
				!address.administrativeArea ||
				!address.locality ||
				!address.street ||
				!address.streetNumber)) {
            $('#objectFieldError').removeClass('hidden');
            return false;
        }

        REST.put('/object', this.__editedObject,
            function(err, object) {
                if (err) {
                    $('#objectServerError').removeClass('hidden');
                    return;
                }
                $('#objectModal').modal('hide');

                this.__saveCallback(object, this.__mapMarker);
            }.bind(this));
    }

    this.__showAddress = function() {
        var checked = $('#addressChBox').is(':checked');
        if (checked) {
            if (!!this.__editedObject.address) {
                $('#administrativeArea').val(this.__editedObject.address.administrativeArea);
                $('#locality').val(this.__editedObject.address.locality);
                $('#street').val(this.__editedObject.address.street);
                $('#streetNumber').val(this.__editedObject.address.streetNumber);
            }
            $('#addressAutoFillBtn').removeClass('hidden');
            $('#addressRow').removeClass('hidden');
        } else {
            $('#addressRow').addClass('hidden');
        }
    }

    this.__autoFillAddress = function(e) {
        var lat = this.__editedObject.marker.lat;
        var lng = this.__editedObject.marker.lng;
        GoogleAddressService.getAddress(lat, lng, function(address) {
            this.__editedObject.address = address;
        }.bind(this));

        if (!!this.__editedObject.address) {
            $('#administrativeArea').val(this.__editedObject.address.administrativeArea);
            $('#locality').val(this.__editedObject.address.locality);
            $('#street').val(this.__editedObject.address.street);
            $('#streetNumber').val(this.__editedObject.address.streetNumber);
        }

        e.stopPropagation();
    }

    this.__fillFields = function(object) {
        $('#objName').val(object.name);
        $('#objType').val(object.type);

		var address = object.address ? object.address : {};
		$('#addressChBox').prop('checked', !!object.address);
        if (!!object.address) {
        	$('#addressRow').removeClass('hidden');
        	$('#addressAutoFillBtn').removeClass('hidden');
        } else {
        	$('#addressRow').addClass('hidden');
        	$('#addressAutoFillBtn').addClass('hidden');
        }

        $('#administrativeArea').val(address.administrativeArea);
        $('#locality').val(address.locality);
        $('#street').val(address.street);
        $('#streetNumber').val(address.streetNumber);

        $('#objectFieldError').addClass('hidden');
        $('#objectServerError').addClass('hidden');
    }

    this.__viewMode = function(view) {
        view = !!view;
        $('#objectModal').find('input').prop('disabled', view);
        $('#objectModal').find('select').prop('disabled', view);

        if (view) {
        	$('#objectModal').find('.modal-footer').addClass('hidden');
			$('#addressAutoFillBtn').addClass('hidden');
        } else {
        	$('#objectModal').find('.modal-footer').removeClass('hidden');
        }
    }

    $('#saveObjectBtn').on('click', this.__saveObject.bind(this));
    $('#addressAutoFillBtn').on('click', this.__autoFillAddress.bind(this));

    $('#addressChBox').change(this.__showAddress.bind(this));
};