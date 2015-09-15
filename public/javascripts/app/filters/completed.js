define(['./module'], function(filters){
	filters.filter('completed',function(){
		  return function(tasklist){
		    var size = 0, i=0;
		    for(i=0; i<tasklist.length; ++i){
		      if(tasklist[i].completed===true){
		        size++;
		      }
		      
		    }
		    return size;
		  };
		});
})