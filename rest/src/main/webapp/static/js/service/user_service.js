'use strict';

angular.module('dndTroopsApp').factory('UserService', ['$http', '$q', function($http, $q) {
    var REST_SERVICE_URI = 'http://localhost:8080/pa165/users/';
    var factory = {
        getUser:getUser
    };
    return factory;

    function getAllUsers() {
        var defered = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    defered.resolve(response.data)
                },
                function (errResponse) {
                    console.error('Error while getting user');
                    defered.reject(errResponse);
                }
            );
        return defered.promise;
    }
}]);
