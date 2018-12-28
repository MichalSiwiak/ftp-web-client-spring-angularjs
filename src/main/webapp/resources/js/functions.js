var app = angular.module("FtpManagement", []);

//Controller Part
app.controller("FtpManagementController", function ($scope, $http) {

    //Initialize page with default data which is blank in this example
    $scope.files = [];

    //Now load the data from server
    _refreshPageData();

    /* Private Methods */

    //HTTP GET- get all files
    function _refreshPageData() {
        $http({
            method: 'GET',
            url: '/ftp/demo/files'
        }).then(function successCallback(response) {
            $scope.files = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.submitFileName = function ( fileName ) {
        $http({
            method: "POST",
            url: '/ftp/demo/files',
            data: fileName,
            headers: {
                'Content-Type': 'text/plain'
            }
        }).then(_success, _error);
    };

    function _success() {
        _refreshPageData();
        _clearForm()
    }

    function _error(response) {
        console.log(response.statusText);
    }

});