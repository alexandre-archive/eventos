var App = angular.module('App', [])
        .filter('SimNao', function() {
            return function(input) {
                return input ? "Sim" : "Não";
            };
        })
        .run(function($rootScope, $http) {
            $http.defaults.headers.common['x-ng-request'] = true;

            $rootScope.user = null;
            
            $rootScope.doLogout = function () {
                $rootScope.user = null;
                window.location.assign('/eventos/signin.html');
            };
        });

App.controller('LoginCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {

        $scope.invalidLogin = false;
        $scope.user = null;
        $scope.pwd = null;

        $scope.doLogin = function() {
            if ($scope.user && $scope.pwd) {

                $rootScope.user = {
                    login: user,
                };

                window.location.assign("/eventos");
            } else {
                $scope.invalidLogin = true;
            }
        };

        $scope.createAccount = function() {
            window.location.assign('/eventos/signup.html');
        };
    }]);

App.controller('JoinCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {

        $scope.Dto = {
            Name: "",
            SurName: "",
            Email: "",
            Pwd: "",
            PwdConfirmation: "",
        };

        $scope.joinSubmit = function() {
            window.location.assign('/eventos');
        };
    }]);