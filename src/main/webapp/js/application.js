var overseer = angular.module('overseer', ['ngRoute', 'ngResource'])
    .config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.headers.common['Authorization'] = 'Token test';
        $httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
    }])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/endpoints/list', {templateUrl: 'html/endpoints/list.html', controller: ListEndpointsController})
            .when('/endpoints/add', {templateUrl: 'html/endpoints/add.html', controller: AddEndpointsController})
            .otherwise({redirectTo: '/endpoints/list'});
    }]);