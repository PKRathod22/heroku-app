'use strict';
app.config(function($stateProvider, $urlRouterProvider) {
	console.log('inside config222');
    $urlRouterProvider.otherwise('/');
    
    $stateProvider
        .state('layout', {
            templateUrl: 'mainLayout.html'
        })
         .state('layout.home', {
            url: '/',
            controller :'dashboardCtrl',
            templateUrl : 'app/component/dashboard/dashboard.html'
        })
        
        .state('layout.login', {
            url: '/login',
            controller :'headerCtrl',
            templateUrl : 'app/component/login/login.html'
        })
        .state('layout.employee', {
            url: '/employee',
            controller :'dashboardCtrl',
            templateUrl : 'app/component/master/employee/emolyee.html'
        })
        
        
});




