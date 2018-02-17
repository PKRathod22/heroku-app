app.directive('flywinSpinner',function(){
    return{
        restrict:'E',
        scope:{
            spinnerStatus:"="
        },
        template:'<div class="flywin-loader" ng-show="spinnerStatus">' +
        
        '</div>'
    }
});