$(document).ready(function(){
    var partsFromDBModal = new bootstrap.Modal(document.getElementById('partsFromDB'));
    var showPartsFromDBButton = $('#showPartDB');

    showPartsFromDBButton.click(function(){
        partsFromDBModal.show();
    });

});

