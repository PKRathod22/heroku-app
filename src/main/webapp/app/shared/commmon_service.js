app.factory('ObjService', function() {
 var savedData = {}
 function set(data) {
   savedData = data;
 }
 function get() {
  return savedData;
 }

 return {
  set: set,
  get: get
 }

});

app.service('CommonService',function(){
	
	 this.isObjectEmpty = function(obj) {
		    for(var prop in obj) {
		        if(obj.hasOwnProperty(prop))
		            return false;
		    }
		    return JSON.stringify(obj) === JSON.stringify({});
		}
	
	 this.years = function(startYear) {
         var currentYear = new Date().getFullYear(), years = [];
         startYear = startYear || 1980;

         while ( startYear <= currentYear ) {
                 years.push(startYear++);
         } 

         return years;
 }
	 
	 
});