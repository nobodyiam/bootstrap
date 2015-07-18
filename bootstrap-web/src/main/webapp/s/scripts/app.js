(function ($) {
    var app = angular.module('Greeting', [
        'ui.bootstrap',
        'toastr',
        'httpInterceptors' //custom http interceptor
    ]);

    app.controller('GreetingsController', function ($scope, $http, $modal, toastr) {
        this.pageNo = 1;
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
                toastr.error((data && data.msg) || 'Loading failed');
            })
        }

        this.add = function () {
            var modalInstance = $modal.open({
                templateUrl: 'templates/edit.html',
                controller: 'GreetingController',
                controllerAs: 'greetingCtrl',
                size: 'lg',
                resolve: {
                    greeting: function () {
                        return {};
                    }
                }
            });

            modalInstance.result.then(function (addedItem) {
                if (self.pageNo == 1 && self.greetings.length < self.pageSize) {
                    self.greetings.push(addedItem);
                }
            });
        };

        this.edit = function (greeting) {
            var modalInstance = $modal.open({
                templateUrl: 'templates/edit.html',
                controller: 'GreetingController',
                controllerAs: 'greetingCtrl',
                size: 'lg',
                resolve: {
                    greeting: function () {
                        return angular.copy(greeting);
                    }
                }
            });

            modalInstance.result.then(function (updatedItem) {
                var matchingIndex = _.findIndex(self.greetings, function (item) {
                    return item.id == greeting.id;
                });

                if (matchingIndex < 0) {
                    return;
                }
                self.greetings[matchingIndex] = updatedItem;
            });
        };

        this.delete = function (item) {
            if (!confirm('Are you sure to delete this greeting?')) {

            }
        };

        this.init = function () {
            this.getGreetings(this.pageNo, this.pageSize);

        };

        this.init();
    });

    app.controller('GreetingController', function ($scope, $http, $modalInstance, toastr, greeting) {
        this.item = greeting;
        var self = this;

        this.save = function () {
            this.item.id ? this.update() : this.create();
        };

        this.create = function () {
            $http.post('/greetings', this.item).success(function (data, status, headers, config) {
                toastr.success('Saved successfully');
                self.close(data);
            }).error(function (data, status, headers, config) {
                toastr.error((data && data.msg) || 'Saved failed');
            })
        };

        this.update = function () {
            $http.put('/greetings/' + this.item.id, this.item).success(function (data, status, headers, config) {
                toastr.success('Updated successfully');
                self.close(data);
            }).error(function (data, status, headers, config) {
                toastr.error((data && data.msg) || 'Updated failed');
            })
        };

        this.close = function (data) {
            $modalInstance.close(data);
        };
    });

})(jQuery);