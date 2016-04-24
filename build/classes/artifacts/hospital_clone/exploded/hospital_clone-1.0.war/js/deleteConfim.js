/**
 * Created by ksu on 17.04.16.
 */
$(document).ready(function (){
    $('.glyphicon-trash').on('click',function(e){
        $('#confirm').attr('href','/ms?action=delete&id='+this.id)
    })
});
