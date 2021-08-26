var app = angular.module('myApp', ['ngRoute']);


app.config(function($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);
    let domain = "http://localhost:8080/partial/";

    $routeProvider
        .when('/', {
            templateUrl: domain + 'content.html',
            controller: 'homeController'
        })
        .when('/about', {
            templateUrl: domain + 'about.html',
            controller: 'aboutController'
        })
        .when('/post', {
            templateUrl: domain + 'post.html'
        })
        .when('/contact', {
            templateUrl: domain + 'contact.html',
            controller: 'contactController'
        })

    .otherwise({ redirectTo: '/' })

})

app.controller('aboutController', function($scope, $http) {
    var URLDataAbout = "http://localhost:8080/api/about?action=getabout";
    $http.get(URLDataAbout).then(function(response) {
        $scope.data = response.data.data;
    }, function(error) {})
})

app.controller('contactController', function($scope, $http) {
    var URLDataAbout = "http://localhost:8080/api/contact?action=getcontact";
    $http.get(URLDataAbout).then(function(response) {
        $scope.data = response.data.data;
    }, function(error) {})
})

app.controller('homeController', function($scope, $http) {
    var URLDataAbout = "http://localhost:8080/api/home?action=gethome";
    $http.get(URLDataAbout).then(function(response) {
        $scope.data = response.data.data;
    }, function(error) {})

    var URLDataPost = "http://localhost:8080/api/post?action=getpost";
    $http.get(URLDataPost).then(function(response) {
        $scope.listPosts = response.data.data;
    }, function(error) {})
})