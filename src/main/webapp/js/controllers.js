function ListEndpointsController($scope, $resource, $timeout) {
    var refreshEndpointsList = function(setTimeout) {
        var endpoints = endpointResource.query({}, function() {
            $scope.endpoints = endpoints;
        });

        if (setTimeout) {
            $timeout(function() {
                refreshEndpointsList(true);
            }, 5000);
        }
    };
    var endpointResource = $resource('/rest/endpoint/', {}, {
            'delete': {method: 'DELETE', url: '/rest/endpoint/:group/:name'}
    });

    $scope.endpoints = [];
    $scope.start = function(key) {
        console.log(key);
    };
    $scope.pause = function(key) {
        console.log(key);
    };
    $scope.delete = function(key) {
        endpointResource.delete(key, function() {
            refreshEndpointsList(false);
        });
    };

    $timeout(function() {
        refreshEndpointsList(true);
    }, 500);
}
function AddEndpointsController($scope, $resource, $location) {
    $scope.save = function(endpoint) {
        var endpointResource = $resource('/rest/endpoint/');
        endpointResource.save(endpoint);
        $location.path('/endpoints/list');
    };
    $scope.cancel = function() {
        $location.path('/endpoints/list');
    };
}