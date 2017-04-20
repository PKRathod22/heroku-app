/**
 * Created by saravanan on 5/4/16.
 */
app.directive('inputFocus',
    function($timeout) {
        return {
            link : function(scope, element,attrs) {
                attrs.$observe('inputFocus',function(value){
                    if (value === 'true') {
                        $timeout(function() {
                            element[0].focus();
                        },0,false);
                    }
                });
                //scope.$watch('trigger', function(value) {
                //
                //});
            }
        };
    });