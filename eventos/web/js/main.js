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
                window.location.hash = '#profile?id=' + p.id;
            };

            $rootScope.goToEvent = function(e) {
                window.location.hash = '#event?id=' + e.id;
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
                console.log(data);
                if (status === 412) {
                    $rootScope.showAlertBox(data[0].message, "e", false);
                } else {
                    $rootScope.showAlertBox("Erro ao processar request. Status: " + status, "e", false);
                }
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

            $http({
                method: 'GET',
                url: '/eventos/api/event',
            }).success(function(data, status, headers, config) {
                console.log(data);
                $scope.UserEvents = data;

                _.each($scope.UserEvents, function(item) {
                    item.stList = $rootScope.getEventStatus(item.due);
                    item.status = _.findWhere($rootScope.getEventStatus(item.due), {id: item.answer});
                });

            }).error(function(data, status, headers, config) {
                console.log(data);
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

                $http({
                    method: 'GET',
                    url: '/eventos/api/event/' + id,
                }).success(function(data, status, headers, config) {
                    console.log(data);
                    $scope.Event = data;

                }).error(function(data, status, headers, config) {
                    console.log(data);
                });

            } else {

                $http({
                    method: 'GET',
                    url: '/eventos/api/event',
                }).success(function(data, status, headers, config) {
                    console.log(data);
                    $scope.AllEvents = data;

                    _.each($scope.AllEvents, function(item) {
                        //fetchImage(item.Owner.PhotoUrl);
                        item.stList = $rootScope.getEventStatus(item.due);
                        item.status = _.findWhere($rootScope.getEventStatus(item.due), {id: item.answer});
                    });

                }).error(function(data, status, headers, config) {
                    console.log(data);
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
        
        $scope.like = function (event) {
            
        };
        
        $scope.unlike = function (event) {
            
        };
        
        $scope.share = function (event) {
            
        };
        
        $scope.likeComment = function (event, comm) {
            
        };
        
        $scope.unlikeComment = function (event, comm) {
            
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

            $('#initTime').val(""); // Não ta atualizando o model do angular.
            $('#endTime').val("");
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
                coverUrl: $scope.src,
                title: $scope.Dto.Title,
                initialdate: $('#initTime').val(), // Não ta atualizando o model do angular.
                finaldate: $('#endTime').val(),
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
                console.log(data);
                $rootScope.showAlertBox("Erro ao processar request. Código: " + status, "e", true);
            });
        };

        $('a[href="#new"]').on('show.bs.tab', function(e) {
        });
    }]);

App.controller('Ctrl', ['$scope', '$http', '$sce', '$rootScope', '$q', function($scope, $http, $sce, $rootScope, $q) {
    }]);