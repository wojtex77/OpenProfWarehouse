function initializeOrderedItemsFromDbTable(){
        var tfConfig = {
            alternate_rows: true,
            btn_reset: {
                text: 'Wyczyść filtry'
            },
            rows_counter: {
                text: 'Wpisów: '
            },
            no_results_message: true,
            mark_active_columns: {
                highlight_column: true
            },
            highlight_keywords: true,
            no_results_message: true,
            col_0: 'none',
            col_10: 'none',
            extensions: [{
                name: 'sort'
            }],
            paging: true,


            /** Bootstrap integration */

            // aligns filter at cell bottom when Bootstrap is enabled
            filters_cell_tag: 'th',

            // allows Bootstrap table styling
            themes: [{
                name: 'transparent'
            }]
        };

        var filteredTable = new TableFilter(document.querySelector('#tableWithOrderedItemsFromDb'), tfConfig);
        filteredTable.init();
};


function showOrderedItemsInModal(data){
    var tableBeginning = `
        <table class="table table-sm table-hover" id="mainTable">
            <tr>
                <th>Id</th>
                <th>Nazwa części</th>
                <th>Ilość</th>
                <th>Zlecenie</th>
                <th>Rysunek</th>
                <th>Artykuł</th>
                <th>Materiał</th>
                <th>Profil</th>
                <th>Długość</th>
                <th>Masa [kg]</th>
                <th>Akcje</th>
            </tr>

            `;
         var tableContent="";
         for(let i=0; i< data.length; i++){
             tableContent = tableContent + `
               <tr>
                    <td>` + data[i].id + `</td>
                    <td>` + data[i].partName + `</td>
                    <td>` + data[i].qty + `</td>
                    <td>` + data[i].orderNumber + `</td>
                    <td>` + data[i].drawing + `</td>
                    <td>` + data[i].article + `</td>
                    <td>` + data[i].material + `</td>
                    <td>` + data[i].profile + `</td>
                    <td>` + data[i].profileLength + `</td>
                    <td>` + data[i].weight + `</td>` +
                    '<td><button class="btn btn-sm loadButton" id="load-' + data[i].id + '">Wczytaj</button></td>' +
               `</tr>
            `
            };
          var tableEnding = "</table>";

        $('#tableWithOrderedItemsFromDb').html(tableBeginning + tableContent + tableEnding);
        initializeOrderedItemsFromDbTable();

        $('.loadButton').click(function(){
            var id = this.id.substring(5);
            loadPart(id);
        });
};

function getPartsFromOrders(){

    var url = window.location.protocol + "//" + window.location.host;

    var jqxhr = $.post( url + "/items/getAllParts")
      .done(function(data) {
        console.log( "all orderedItems loaded from db" );
        showOrderedItemsInModal(data);
      })
      .fail(function() {
        console.log( "error" );
        //$('tableContent').html('Nie udało się wczytać danych');
      })
};

$(document).ready(function(){
    var partsFromDBModal = new bootstrap.Modal(document.getElementById('partsFromDbModal'));
    var showPartsFromDBButton = $('#showPartFromDb');

    showPartsFromDBButton.click(function(){
        partsFromDBModal.show();
        getPartsFromOrders();
    });
});