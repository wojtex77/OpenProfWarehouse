function getDataFromDB(){
    var jqxhr = $.post( "http://localhost:8080/orders/getPartsFromDb", function(data, status) {
      console.log( "success" );
    })
      .done(function(data) {
        console.log( "second success" );
        showData(data);
      })
      .fail(function() {
        console.log( "error" );
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



$(document).ready(function(){
    var partsFromDBModal = new bootstrap.Modal(document.getElementById('partsFromDB'));
    var showPartsFromDBButton = $('#showPartDB');

    showPartsFromDBButton.click(function(){
        partsFromDBModal.show();
        getDataFromDB();
    });

});

