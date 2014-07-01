/* 
 IE < 8 or another unsuported browser.
 Web storage is supported in Internet Explorer 8+, Firefox, Opera, Chrome, and Safari.
 */
if (typeof (Storage) === "undefined") {
    window.location.assign('/eventos/update.html');
}

/*
 Helper functions
 */

/* Return actual tab. */
function getTab() {
    var url = document.location.toString();

    if (url.match('#')) {
        return url.split('#')[1].split('?')[0];
    } else {
        return null;
    }
}

/* Return the value of given key on query string. */
function getQueryStringValue(key) {
    return unescape(window.location.href.replace(new RegExp("^(?:.*[&\\?]" + escape(key).replace(/[\.\+\*]/g, "\\$&") + "(?:\\=([^&]*))?)?.*$", "i"), "$1"));
}

/* Force to fetch an image on ng-src. */
function fetchImage(src) {
    if (!src)
        return;
    var img = new Image();
    img.src = src;
}

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function setLoginInfo(id, login, fullName, photoUrl, isAuthenticated) {
    window.localStorage.userId = id || -1;
    window.localStorage.login = login || "";
    window.localStorage.username = fullName || "";
    window.localStorage.photoUrl = photoUrl || "";
    window.localStorage.isAuthenticated = isAuthenticated || false;
}

/*
 Session timeout control.
 */

function checkTimeout() {
    // Only validate session timeout if user is logged in.
    if (window.localStorage.isAuthenticated !== "true") {
        return;
    }

    var now = (new Date()).getTime();
    var lastAccess = Date.parse(localStorage.lastAccess);

    if ((now - lastAccess) > (10/*min*/ * 60/*sec*/ * 1000/*mili*/)) {
        alert('Session timeout.');
        setLoginInfo();
        window.location.assign('/eventos/signin.html');
    }
}
;

setInterval(function() {
    checkTimeout();
}, 60/*sec*/ * 1000/*mili*/);

$(document).ready(function() {
    checkTimeout();
    window.localStorage.lastAccess = new Date();
});

/*
 AngularJS modules.
 */
var App = angular.module('App', [])
        .factory('lastAccessInterceptor', function() {
            return {
                'request': function(config) {
                    window.localStorage.lastAccess = new Date();
                    return config;
                },
                'response': function(response) {
                    return response;
                }};
        })
        .config(['$httpProvider', function($httpProvider) {
                $httpProvider.interceptors.push('lastAccessInterceptor');
            }])
        .filter('SimNao', function() {
            return function(input) {
                return input ? "Sim" : "Não";
            };
        })
        .filter('Answer', function() {
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
        .filter('Status', function() {
            return function(input) {
                switch (input) {
                    case 1:
                        return "Vai";
                        break;
                    case 2:
                        return "Não vai";
                        break;
                    case 3:
                        return "Talvez irá";
                        break;
                    case 4:
                        return "Não Sabe";
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
                        {id: 4, name: "Não Sei"}, ];
                } else {
                    return [{id: 1, name: "Sim"},
                        {id: 2, name: "Não"},
                        {id: 3, name: "Talvez"}, ];
                }
            };

            $rootScope.showAlertBox = function(msg, type, dismiss) {
                $rootScope.showAlert = true;
                $rootScope.alertMessage = msg;
                $rootScope.alertClass = "";

                switch (type)
                {
                    case "i":
                        $rootScope.alertClass += "alert-info";
                        break;
                    case "w":
                        $rootScope.alertClass += "alert-warning";
                        break;
                    case "e":
                        $rootScope.alertClass += "alert-danger";
                        break;
                    case "s":
                        $rootScope.alertClass += "alert-success";
                        break;
                }

                if (dismiss) {
                    $rootScope.alertClass += " alert-dismissible";
                    $rootScope.dismiss = dismiss;
                }
            };

            $rootScope.goToProfile = function(p) {
                window.location.hash = '#profile?id=' + p.Id;
            };

            $rootScope.goToEvent = function(e) {
                window.location.hash = '#event?id=' + e.Id;
            };

            $rootScope.showAlert = false;
            $rootScope.message = "";
            $rootScope.alertClass = "";
            $rootScope.dismiss = false;
        });

App.controller('LoginCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {

        $scope.invalidLogin = false;
        $scope.user = null;
        $scope.pwd = null;

        $scope.validateLogin = function(user, pwd) {
            return user && pwd;
        };

        $scope.doLogin = function() {
            if ($scope.validateLogin($scope.user, $scope.pwd)) {

                $http({
                    method: 'GET',
                    url: '/eventos/api/user/' + $scope.user,
                }).success(function(data, status, headers, config) {
                    setLoginInfo(data.id, data.login, data.fullName, data.photoUrl, true);
                    window.location.assign("/eventos");
                }).error(function(data, status, headers, config) {
                    console.log(data);
                    setLoginInfo();
                    $scope.invalidLogin = true;
                });

            } else {
                $scope.invalidLogin = true;
            }
        };

        $scope.createAccount = function() {
            window.location.assign('/eventos/signup.html');
        };
    }]);

App.controller('JoinCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {

        $scope.reset = function() {
            $scope.Dto = {
                name: "",
                surName: "",
                email: "",
                login: "",
                pwd: "",
                pwdConfirmation: "",
            };
        };

        $scope.reset();

        $scope.joinSubmit = function() {

            if ($scope.Dto.pwd !== $scope.Dto.pwdConfirmation) {
                $rootScope.showAlertBox("As senhas não conferem.", "e", false);
                return;
            }

            $scope.Dto.login = $scope.Dto.email;

            var data = {
                id: 0,
                fullName: "",
                name: $scope.Dto.name,
                surName: $scope.Dto.surName,
                photoUrl: "",
                login: $scope.Dto.login,
                pwd: $scope.Dto.pwd
            };

            $http({
                method: 'POST',
                url: '/eventos/api/user',
                data: data
            }).success(function(data, status, headers, config) {
                $scope.reset();
                window.location.assign('/eventos/signin.html');
            }).error(function(data, status, headers, config) {
                $rootScope.showAlertBox(data[0].message, "e", false);
            });
        };

        $scope.gotoLogin = function() {
            window.location.assign('/eventos/signin.html');
        };
    }]);

App.controller('ProfileCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {

        $scope.reload = function() {

            var id = parseInt(getQueryStringValue("id"));

            if (id && !isNaN(id))
            {
                $http({
                    method: 'GET',
                    url: '/eventos/api/user/' + id,
                }).success(function(data, status, headers, config) {

                    $scope.Dto = {
                        FullName: data.fullName,
                        Email: data.login,
                        PhotoUrl: data.photoUrl
                    };

                }).error(function(data, status, headers, config) {
                    console.log(data);
                    $rootScope.showAlertBox("Usuário não encontrado.", "e", false);
                });
            }
            else
            {
                $scope.Dto = {
                    FullName: window.localStorage.username,
                    Email: window.localStorage.login,
                    PhotoUrl: window.localStorage.photoUrl
                };
            }
        };

        $scope.joinSubmit = function() {
            window.location.assign('/eventos');
        };

        $('a[href="#profile"]').on('show.bs.tab', function(e) {
            $scope.reload();
            $scope.$digest();
        });
    }]);

App.controller('MyEventsCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {
        $scope.UserEvents = [];

        $scope.reload = function() {
            $scope.UserEvents = [
                {
                    Owner: {
                        FullName: "Dick Pirocona Dura",
                        PhotoUrl: "img/covers/jpg/5.jpg",
                        Login: "",
                        Id: 2,
                    },
                    Id: 1,
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
                    Owner: {
                        FullName: "Dick Pirocona Dura",
                        PhotoUrl: "img/covers/jpg/5.jpg",
                        Login: "",
                        Id: 2,
                    },
                    Id: 2,
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

            _.each($scope.UserEvents, function(item) {
                item.stList = $rootScope.getEventStatus(item.Due)
                item.Status = _.findWhere($rootScope.getEventStatus(item.Due), {id: item.Answer});
            });
        };

        $scope.updateEventStatus = function(e) {

            var data = {
                UseId: parseInt(window.localStorage.userId),
                EventId: e.Id,
                Answer: e.Status.id,
            };
            console.log(data);
        };

        $('a[href="#myevents"]').on('show.bs.tab', function(e) {
            $scope.reload();
            $scope.$digest();
        });
    }]);

App.controller('FindEventsCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {

        $scope.Event = null;
        $scope.AllEvents = [];

        $scope.reload = function() {

            var id = parseInt(getQueryStringValue("id"));

            $scope.SingleEvent = id && !isNaN(id);

            if ($scope.SingleEvent) {
                $scope.Event = {
                    Owner: {
                        FullName: "Dick Piroquinha",
                        PhotoUrl: "img/covers/jpg/1.jpg",
                        Login: "",
                        Id: 1,
                    },
                    Id: 1,
                    CoverUrl: "img/covers/jpg/5.jpg",
                    Description: "Um texto bem grande para ocupar mais de uma linha no exmplo a ser motrado como foi feito.",
                    Title: "Evento 1",
                    Date: "seg, 14 de julho, 19:00",
                    Location: "Blumenau",
                    Detail: "",
                    TotalGuests: "151 pessoas vão",
                    Answer: 0,
                    Due: false,
                    Like: false,
                    Likes: 101,
                    Shared: true,
                    Comments: [{
                            User: {FullName: "Dick Piroquinha",
                                PhotoUrl: "img/covers/jpg/1.jpg",
                                Login: "",
                                Id: 1, },
                            Comment: "Muito legal",
                            Like: false,
                            Likes: "1 curtida",
                        },
                        {
                            User: {FullName: "Dick Piroquinha",
                                PhotoUrl: "img/covers/jpg/2.jpg",
                                Login: "",
                                Id: 1, },
                            Comment: "Show!!",
                            Like: true,
                            Likes: "10 curtidas",
                        }],
                };
            } else {

                $scope.AllEvents = [
                    {
                        Owner: {
                            FullName: "Dick Piroquinha",
                            PhotoUrl: "img/covers/jpg/1.jpg",
                            Login: "",
                            Id: 1,
                        },
                        Id: 1,
                        CoverUrl: "img/covers/jpg/5.jpg",
                        Title: "Evento 1",
                        Date: "seg, 14 de julho, 19:00",
                        Location: "Blumenau",
                        Detail: "",
                        Guests: "João Silva, Marcelo Pinto, Kid Bengala e mais",
                        TotalGuests: "151 pessoas vão",
                        Answer: 0,
                        Due: false,
                    },
                    {
                        Owner: {
                            FullName: "Dick Pirocona",
                            PhotoUrl: "img/covers/jpg/2.jpg",
                            Login: "",
                            Id: 2,
                        },
                        Id: 2,
                        CoverUrl: "img/covers/jpg/12.jpg",
                        Title: "Evento 2",
                        Date: "sex, 27 de junho, 12:00",
                        Location: "Rio do Sul",
                        Detail: "",
                        Guests: "Sasha Gray, Rocco, Kid Bengala e mais",
                        TotalGuests: "300 pessoas foram",
                        Answer: 0,
                        Due: true,
                    },
                    {
                        Owner: {
                            FullName: "Dick Pirocona Dura",
                            PhotoUrl: "img/covers/jpg/5.jpg",
                            Login: "",
                            Id: 2,
                        },
                        Id: 2,
                        CoverUrl: "img/covers/jpg/15.jpg",
                        Title: "Evento 2",
                        Date: "sex, 27 de junho, 12:00",
                        Location: "Rio do Sul",
                        Detail: "",
                        Guests: "Sasha Gray, Rocco, Kid Bengala e mais",
                        TotalGuests: "300 pessoas foram",
                        Answer: 0,
                        Due: true,
                    }
                ];

                _.each($scope.AllEvents, function(item) {
                    //fetchImage(item.Owner.PhotoUrl);
                    item.stList = $rootScope.getEventStatus(item.Due)
                    item.Status = _.findWhere($rootScope.getEventStatus(item.Due), {id: item.Answer});
                });
            }
        };

        $scope.updateEventStatus = function(e, status) {

            e.Answer = status;
            var data = {
                UseId: parseInt(window.localStorage.userId),
                EventId: e.Id,
                Answer: status,
            };
            console.log(data);
        };

        $('a[href="#find"], a[href="#event"]').on('show.bs.tab', function(e) {
            $scope.reload();
            $scope.$digest(); // ou $scope.$apply(function() { $()... });
        });
    }]);

App.controller('NewEventCtrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {

        $scope.reset = function() {
            $scope.Dto = {
                CoverUrl: "",
                Title: "",
                InitialDate: null,
                FinalDate: null,
                Location: "",
                Detail: "",
                Guests: "",
            };
        };

        $scope.reloadCover = function() {
            $scope.src = "img/covers/jpg/" + getRandomInt(1, 30) + ".jpg";
            fetchImage($scope.src);
        };

        $scope.reloadCover();

        $scope.submit = function() {

            var data = {
                id: 0,
                owner: window.localStorage.userId,
                coverUrl: $scope.Dto.CoverUrl,
                title: $scope.Dto.Title,
                initialdate: $scope.Dto.InitialDate,
                finaldate: $scope.Dto.FinalDate,
                location: $scope.Dto.Location,
                detail: $scope.Dto.Detail,
                guests: $scope.Dto.Guest
            };

            $http({
                method: 'POST',
                url: '/eventos/api/event',
                data: data
            }).success(function(data, status, headers, config) {
                $rootScope.showAlertBox("Evento cadastrado com sucesso.", "i", true);
                $scope.reset();
            }).error(function(data, status, headers, config) {
                $rootScope.showAlertBox("Erro ao processar request. Código: " + status, "e", true);
            });
        };

        $('a[href="#new"]').on('show.bs.tab', function(e) {
        });
    }]);

App.controller('Ctrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {
    }]);