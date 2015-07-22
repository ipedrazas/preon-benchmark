'use strict';
angular.module('myApp',[])
    .controller('HomeCtrl',
        [ "proxyApi", "$scope", "$interval", function ( proxyApi, $scope, $interval) {

            $interval(function(){
                proxyApi.getCustomer().then(function (result) {
                    var data = result.data;
                    console.log(data);
                    $scope.customer = data;
                    $scope.sdocustomer = data * 7;
                });
            },1000);

        }]

    ).factory('proxyApi', ['$http', function($http) {
        return {
            getCustomer: function(){
                return $http.get('http://192.168.59.103:8080/customers');
            },
            getJobs: function(){
                return $http.get('http://192.168.59.103:5000/api/jobs_data');
            },
            getRequestData: function(){
                return $http.get('http://192.168.59.103:5000/api/requests_data');
            },
        };
    }]);
