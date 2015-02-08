REST = new function() {

	this.get = function(url, params, cb) {
	 	if (params instanceof Function) {
	 		cb = params;
	 	}

		var requestUrl = this.__replaceUrlParams(url, params)
		this.__mapCallback(
			$.ajax({
				url: requestUrl,
				method: 'GET'
			}), cb);
	}

	this.put = function(url, params, cb) {
		this.__mapCallback(
			$.ajax({
				url: url,
				method: 'PUT',
				contentType: 'application/json',
				data: JSON.stringify(params)
			}), cb);
	}

	this.__mapCallback = function(request, cb) {
		request
		.done(function(result) {
			cb(null, result);
		})
		.fail(function(e) {
			cb(e);
		});
	}

	this.__replaceUrlParams = function(url, params) {
		var result = url;
		for (var p in params) {
			result = result.replace('{' + p + '}', params[p]);
		}

		return result;
	}
};