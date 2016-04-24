/**
 * Created by ksu on 17.04.16.
 */
$(document).ready(function () {
    var tempID;
    $('.update').on('click', function (e) {
        tempID = this.id;
        $('#title').val($('#title' + this.id).data("title"));
    });
    $('#run').on('click', function () {
        $('#run').attr('href','/ss?action=edit&id='+tempID+'&title='+$('#title').val());
    })
});
