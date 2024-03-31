
$(document).ready(function() {
    console.log("custom common js loaded");

   $(document).on("click","#but button",function() {
        alert("click bound to document listening for #but");
        $.ajax({
        type: 'GET',
        url:'/bin/OpenWeatherMap.json',
             contentType:'json',
        success: function(data){
            console.log(data);
            // Create the dynamic element '#test-element'
            let min,max,dt='';
            if(data.cod === "200")
            {
				max = data.list[0].main.temp_max;
                min = data.list[0].main.temp_min;
                dt = data.list[0].dt_txt;
            }
            if(!$('#weatherData').length > 0)
    			$('#but').append('<div id="weatherData"><p>MAX:'+max+'</p><p>MIN:'+min+'</p><p>DATE:'+dt+'</p></div>');

        }

    	});
    });


});