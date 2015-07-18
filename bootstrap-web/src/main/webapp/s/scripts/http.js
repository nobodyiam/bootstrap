(function ($) {
    var httpInterceptors = angular.module('httpInterceptors', []);

    httpInterceptors.factory('httpInterceptor', function ($q) {
        return {
            'request': function (config) {
                var t = new Date().getTime();

                if (config.url.indexOf('.htm') != -1 || config.url.indexOf('?_=') != -1) {
                    return config;
                }

                config.url = config.url + '?_=' + t;

                return config;
            },
            'response': function (response) {
                if (typeof response.data === 'object') {
                    if (response.data.code != null && response.data.code != 200) {
                        return $q.reject(response);
                    }
                }

                return response;
            }
        };
    });

    httpInterceptors.config(function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    });
})(jQuery);
