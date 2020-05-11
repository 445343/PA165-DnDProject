'use strict';

angular.module('dndTroopsApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.users=[];
    
    getAllUsers();
    
    function getAllUsers() {
        UserService.getAllUsers()
            .then(
                function(d) {
                    self.users = d;
                },
                function (errResponse) {
                    console.error('Error getting all users');
                }
            );
    }
}])