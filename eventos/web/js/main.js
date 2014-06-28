if (typeof (Storage) === "undefined") {
    window.location.assign('/eventos/update.html');
}

/* Force to fetch. */
function fetchImage(src) {
    var img = new Image();
    img.src = src;
}

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

var App = angular.module('App', [])
        .filter('SimNao', function() {
            return function(input) {
                return input ? "Sim" : "Não";
            };
        })
        .filter('Aswer', function() {
            return function(input) {
                switch (input) {
                    case 1:
                        return "Sim";
                        break;
                    case 2:
                        return "Não";
                        break;
                    case 3:
                        return "Talvez";
                        break;
                    case 4:
                        return "Não Sei";
                        break;
                    default:
                        return "";
                        break;
                }
            };
        })
        .run(function($rootScope, $http) {
            $http.defaults.headers.common['x-ng-request'] = true;

            $rootScope.doLogout = function() {
                window.localStorage.isAuthenticated = false;
                window.location.assign('/eventos/signin.html');
            };

            $rootScope.username = function() {
                return window.localStorage.username;
            };

            $rootScope.fetchImage = function(src) {
                if (src)
                    fetchImage(src);
                return src;
            };

            $rootScope.getEventStatus = function(due) {

                if (due) {
                    return [{id: 1, name: "Sim"},
                        {id: 2, name: "Não"},
                        {id: 4, name: "Não Sei"},];
                } else {
                    return [{id: 1, name: "Sim"},
                        {id: 2, name: "Não"},
                        {id: 3, name: "Talvez"},];
                }
            };
        });

App.controller('LoginCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {

        $scope.invalidLogin = false;
        $scope.user = null;
        $scope.pwd = null;

        $scope.doLogin = function() {
            if ($scope.user && $scope.pwd) {

                window.localStorage.login = $scope.user;
                window.localStorage.username = "UserName";
                window.localStorage.isAuthenticated = true;

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

        $scope.gotoLogin = function() {
            window.location.assign('/eventos/signin.html');
        };
    }]);

App.controller('ProfileCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {

        $scope.Dto = {
            Name: "Alexandre",
            SurName: "Vicenzi",
            Email: "foo@bar.com",
            PhotoUrl: "https://lh5.googleusercontent.com/--fK08SiAPcA/AAAAAAAAAAI/AAAAAAAAABU/7GiuAp4r3RA/s120-c/photo.jpg",
        };

        $scope.Dto.FullName = $scope.Dto.Name + ' ' + $scope.Dto.SurName;

        $scope.joinSubmit = function() {
            window.location.assign('/eventos');
        };

        $scope.getPhoto = function() {
            var src = $scope.Dto.PhotoUrl;
            fetchImage(src);
            return src;
        };
    }]);

App.controller('MyEventsCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {
        $scope.UserEvents = [
            {
                CoverUrl: "img/covers/jpg/1.jpg",
                Title: "Evento 1",
                Date: "seg, 14 de julho, 19:00",
                Location: "Blumenau",
                Detail: "",
                Guests: "João Silva, Marcelo Pinto, Kid Bengala e mais",
                TotalGuests: "151 pessoas vão",
                Answer: 1,
                Due: false,
            },
            {
                CoverUrl: "img/covers/jpg/2.jpg",
                Title: "Evento 2",
                Date: "sex, 27 de junho, 12:00",
                Location: "Rio do Sul",
                Detail: "",
                Guests: "Sasha Gray, Rocco, Kid Bengala e mais",
                TotalGuests: "300 pessoas foram",
                Answer: 2,
                Due: true,
            }, ];
        
        _.each($scope.UserEvents, function (item) {
            item.stList = $rootScope.getEventStatus(item.Due)
            item.Status = _.findWhere($rootScope.getEventStatus(item.Due), {id : item.Answer});
        });
    }]);

App.controller('FindEventsCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {
        $scope.AllEvents = [];
    }]);

App.controller('NewEventCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {

        $scope.Dto = {
            CoverUrl: "",
            Title: "",
            InitialDate: null,
            FinalDate: null,
            Location: "",
            Detail: "",
            Guests: "",
        };

        $scope.src = "img/covers/jpg/" + getRandomInt(1, 30) + ".jpg";
        fetchImage($scope.src);

        $scope.submit = function() {

            console.log($scope.Dto);

            $http({
                method: 'POST',
                url: '/eventos/api/events',
                data: $scope.Dto,
            })
                    .success(function(data, status, headers, config) {
                        alert(status);
                    })
                    .error(function(data, status, headers, config) {
                        alert(status);
                    });
        };
    }]);

App.controller('Ctrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {
    }]);