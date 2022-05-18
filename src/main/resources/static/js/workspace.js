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

function initializeOrderedItemsTable(){
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
            col_6: 'none',
            col_7: 'none',
            col_8: 'none',
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

        var partsTable = new TableFilter(document.querySelector('#tableWithParts'), tfConfig);
        partsTable.init();
};

function loadPart(id){
    var url = window.location.protocol + "//" + window.location.host;
    var jqxhr = $.post( url + "/items/getItemFromDb", {'partId':id})
          .done(function(data) {
            console.log( "orderedItem loaded from db" );
            addItemToTable(data);
            addHiddenItemInput(data);
          })
          .fail(function() {
            console.log( "error" );
            alert(' nie pobrano części z bazy');
          })
};


function addItemToTable(data){
    $('#tableWithParts tr:last').after(
       '<tr id="rowPart-' + data.id + '">' + `
            <td>` + data.partName + `</td>
            <td>` + data.qty +`</td>
            <td>` + data.orderNumber + `</td>
            <td>` + data.profile + `</td>
            <td>` + data.profileLength + `</td>
            <td>` + data.material + `</td>` +
            '<td><button type="button" class="btn btn-sm removeButton" id="remove-' + data.id + '" disabled>Usuń</button></td>' +`
            <td hidden>` + '<input type="number" name="partId" value = "' + data.id + '">' + `</td>
            <td hidden>` + '<input type="number" name="itemId" value="" hidden>' + `</td>
        </tr>`
    );
};

function addHiddenItemInput(data){
    $('#inputs').append('<input type="number" name="orderedItemId" value="' + data.id + '" hidden/>');
}

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
                    '<td><button type="button" class="btn btn-sm loadButton" id="loadItem-' + data[i].id + '">Wczytaj</button></td>' +
               `</tr>
            `
            };
          var tableEnding = "</table>";

        $('#tableWithOrderedItemsFromDb').html(tableBeginning + tableContent + tableEnding);
        initializeOrderedItemsFromDbTable();

        $('.loadButton').click(function(){
            var id = this.id.substring(9);
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
        $('tableWithOrderedItemsFromDb').html('Nie udało się wczytać danych');
      })
};

$(document).ready(function(){
    var partsFromDBModal = new bootstrap.Modal(document.getElementById('partsFromDbModal'));
    var showPartsFromDBButton = $('#showPartFromDb');

    $('#saveWSBtn').click(function(){
    var url = window.location.protocol + "//" + window.location.host;

    var jqxhr = $.post( url + "/wsrest/save")
          .done(function(data) {
            console.log("Saved succesfull");
          })
          .fail(function() {
            console.log( "error" );
            alert('error');
          })
    });

    showPartsFromDBButton.click(function(){
        partsFromDBModal.show();
        getPartsFromOrders();
    });
});