var ObjectModal = function(saveCallback) {

    this.__editedObject = null;

    this.__saveCallback = saveCallback;

    this.show = function(object) {
        this.__editedObject = object;
        this.__cleanFields();
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

        if (!this.__editedObject.name ||
            !this.__editedObject.type) {
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

                this.__saveCallback(object);
            }.bind(this));
    }

    this.__showAddress = function() {
        var checked = $('#addressChBox').is(':checked');
        if (checked) {
            if (!this.__editedObject.address) {
                this.__readAddress(this.__editedObject);
            }

            if (!!this.__editedObject.address) {
                $('#administrativeArea').val(this.__editedObject.address.administrativeArea);
                $('#locality').val(this.__editedObject.address.locality);
                $('#street').val(this.__editedObject.address.street);
                $('#streetNumber').val(this.__editedObject.address.streetNumber);
            }
            $('#addressRow').removeClass('hidden');
        } else {
            $('#addressRow').addClass('hidden');
        }
    }

    this.__readAddress = function(object) {
        var lat = object.marker.lat;
        var lng = object.marker.lng;
        GoogleAddressService.getAddress(lat, lng, function(address) {
            object.address = address;
        }.bind(this));
    }

    this.__cleanFields = function() {
        $('#objName').val('');
        $('#objType').val(1);

        $('#addressChBox').prop('checked', false);
        $('#addressRow').addClass('hidden');
        $('#administrativeArea').val('');
        $('#locality').val('');
        $('#street').val('');
        $('#streetNumber').val('');

        $('#objectFieldError').addClass('hidden');
        $('#objectServerError').addClass('hidden');
    }


    $('#saveObjectBtn').on('click', this.__saveObject.bind(this));
    $('#addressChBox').change(this.__showAddress.bind(this));
};