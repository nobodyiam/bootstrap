(function ($) {
    var app = angular.module('Greeting', [
        'ui.bootstrap',
        'toastr',
        'angular-loading-bar',
        'httpInterceptors' //custom http interceptor
    ]);

    var GREETINGS_PATH = '/greetings';

    app.controller('GreetingsController', function ($scope, $http, $modal, toastr) {
        this.currentPage = 1;
        this.pageSize = 10;
        this.greetings = [];
        this.totalItems = 0;
        var self = this;

        this.getGreetings = function (pageNo, pageSize) {
            $http.get(GREETINGS_PATH, {
                params: {
                    offset: (pageNo - 1) * pageSize,
                    limit: pageSize
                }
            }).success(function (data, status, headers, config) {
                self.greetings = data && data.items;
                self.totalItems = data && data.total;

            }).error(function (data, status, headers, config) {
                toastr.error((data && data.msg) || 'Loading failed');
            })
        };

        this.add = function () {
            var modalInstance = this.popUpEditModal({});

            modalInstance.result.then(function (addedItem) {
                self.totalItems++;
                if (self.greetings.length < self.pageSize) {
                    self.greetings.push(addedItem);
                }
            });
        };

        this.edit = function (greeting) {
            var modalInstance = this.popUpEditModal(greeting);

            modalInstance.result.then(function (updatedItem) {
                var matchingIndex = self.findGreetingIndex(updatedItem);

                if (matchingIndex < 0) {
                    return;
                }
                self.greetings[matchingIndex] = updatedItem;
            });
        };

        this.popUpEditModal = function (greeting) {
            return $modal.open({
                templateUrl: 'templates/edit.html',
                controller: 'GreetingController',
                controllerAs: 'greetingCtrl',
                size: 'lg',
                backdrop: 'static',
                keyboard: false,
                resolve: {
                    greeting: function () {
                        return angular.copy(greeting);
                    }
                }
            });
        };

        this.delete = function (greeting) {
            if (!confirm('Are you sure to delete this greeting?')) {
                return;
            }

            $http.delete(GREETINGS_PATH + '/' + greeting.id).success(function (data, status, headers, config) {
                toastr.success('Deleted successfully');

                var matchingIndex = self.findGreetingIndex(data);

                if (matchingIndex < 0) {
                    return;
                }

                if (self.greetings.length == self.pageSize) {
                    self.refresh();
                    return;
                }
                self.greetings.splice(matchingIndex, 1);
                self.totalItems--;
            }).error(function (data, status, headers, config) {
                toastr.error((data && data.msg) || 'Deleted failed');
            })
        };

        this.findGreetingIndex = function (greeting) {
            return _.findIndex(self.greetings, function (item) {
                return item.id == greeting.id;
            });
        };

        this.pageChanged = function () {
            this.refresh();
        };

        this.refresh = function () {
            this.getGreetings(this.currentPage, this.pageSize);
        };

        this.refresh();
    });

    app.controller('GreetingController', function ($scope, $http, $modalInstance, toastr, greeting) {
        this.item = greeting;
        var self = this;
        var requesting = false;

        this.save = function () {
            if (this.requesting) {
                return;
            }
            this.requesting = true;
            var finallyCallback = function () {
                self.requesting = false;
            };
            this.item.id ? this.update(finallyCallback) : this.create(finallyCallback);
        };

        this.create = function (finallyCallback) {
            $http.post(GREETINGS_PATH, this.item).success(function (data, status, headers, config) {
                finallyCallback && finallyCallback.call();
                toastr.success('Saved successfully');
                self.close(data);
            }).error(function (data, status, headers, config) {
                finallyCallback && finallyCallback.call();
                toastr.error((data && data.msg) || 'Saved failed');
            })
        };

        this.update = function (finallyCallback) {
            $http.put(GREETINGS_PATH + '/' + this.item.id, this.item).success(function (data, status, headers, config) {
                finallyCallback && finallyCallback.call();
                toastr.success('Updated successfully');
                self.close(data);
            }).error(function (data, status, headers, config) {
                finallyCallback && finallyCallback.call();
                toastr.error((data && data.msg) || 'Updated failed');
            })
        };

        this.close = function (data) {
            $modalInstance.close(data);
        };

        this.cancel = function () {
            if (this.requesting) {
                return;
            }
            if ($scope.greetingForm.$pristine || confirm('Changes not saved, are you sure to give up?')) {
                $modalInstance.dismiss('cancel');
            }
        }
    });

})(jQuery);