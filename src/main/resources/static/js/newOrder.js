function getDataFromDB(){
    var client = $( "#contrahent option:selected" ).text();

    var jqxhr = $.post( "http://localhost:8080/orders/getPartsFromDb", {'contrahent':client})
      .done(function(data) {
        console.log( "second success" );
        showData(data);
      })
      .fail(function() {
        console.log( "error" );
        $('tableContent').html('Nie udało się wczytać danych');
      })
};

function showData(data){
    var tableBeginning = `
    <table class="table table-hover" id="mainTable">
        <tr>
            <th>Id</th>
            <th>Nazwa części</th>
            <th>Rysunek</th>
            <th>Artykuł</th>
            <th>Kontrahent</th>
            <th>Materiał</th>
            <th>Profil</th>
            <th>Długość</th>
            <th>Masa [kg]</th>
        </tr>

        `;
     var tableContent="";
     for(let i=0; i< data.length; i++){
         tableContent = tableContent + `
           <tr>
                <td>` + data[i].id + `</td>
                <td>` + data[i].partName + `</td>
                <td>` + data[i].drawing + `</td>
                <td>` + data[i].article + `</td>
                <td>` + data[i].contrahent + `</td>
                <td>` + data[i].material + `</td>
                <td>` + data[i].profile + `</td>
                <td>` + data[i].profileLength + `</td>
                <td>` + data[i].weight + `</td>
           </tr>
        `
        };
      var tableEnding = "</table>";

    $('#tableContent').html(tableBeginning + tableContent + tableEnding);

};

function showSpinner(){
    $('tableContent').html(`
                <div class="spinner-grow text-success text-center" role="status">
                    <span class="visually-hidden">Loading...</span>
                </div>
    `);
};


$(document).ready(function(){
    var partsFromDBModal = new bootstrap.Modal(document.getElementById('partsFromDB'));
    var showPartsFromDBButton = $('#showPartDB');

    showPartsFromDBButton.click(function(){
        showSpinner();
        partsFromDBModal.show();
        getDataFromDB();
    });

});

