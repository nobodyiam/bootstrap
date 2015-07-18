(function ($) {
    var app = angular.module('Greeting', [
        'ui.bootstrap'
    ]);

    app.controller('GreetingsController', function ($scope, $http, $modal) {
        this.pageSize = 10;
        this.greetings = [];

        var self = this;
        this.getGreetings = function (pageNo, pageSize) {
            $http.get('/greetings', {
                data: {
                    offset: (pageNo - 1) * pageSize,
                    limit: pageSize
                }
            }).success(function (data, status, headers, config) {
                self.greetings = data;

            }).error(function (data, status, headers, config) {
                console.error('error in loading greetings: ' + JSON.stringify(data) + ' status: ' + status);
            })
        }

        this.add = function () {

        }

        this.edit = function (item) {

        }

        this.delete = function (item) {

        }

        this.init = function () {
            this.getGreetings(1, this.pageSize);

        }

        this.init();
    });

})(jQuery);