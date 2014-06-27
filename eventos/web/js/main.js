var App = angular.module('App', [])
        .filter('SimNao', function() {
            return function(input) {
                return input ? "Sim" : "Não";
            };
        })
        .run(function($rootScope, $http) {
            $http.defaults.headers.common['x-ng-request'] = true;

            $rootScope.User = null;
        });

App.controller('LoginCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {
    }]);
