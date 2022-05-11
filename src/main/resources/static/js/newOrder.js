
function initializeFilteredTable(){
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
            col_8: 'none',
            col_types: [
                'number',
                'string',
                'string',
                'string',
                'string',
                'number',
                'number'
            ],
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

        var filteredTable = new TableFilter(document.querySelector('#mainTable'), tfConfig);
        filteredTable.init();
};

function getDataFromDB(){
    var client = $( "#contrahent option:selected" ).text();
    var url = window.location.protocol + "//" + window.location.host;

    var jqxhr = $.post( url + "/orders/getPartsFromDb", {'contrahent':client})
      .done(function(data) {
        console.log( "all parts loaded from db" );
        showData(data);
      })
      .fail(function() {
        console.log( "error" );
        $('tableContent').html('Nie udało się wczytać danych');
      })
};

function addPartToTable(data){
    $('#partsTable tr:last').after(
    `   <tr>
            <td hidden>` + '<input type="number" name="partId" value = "' + data.id + '">' + `</td>
            <td>` + data.partName + `</td>
            <td>` + '<input type = "number" class="form-control form-control-sm" name = "ammountOfPart" step="1" min="0" value = "0"></input></td>' +`
            <td>` + data.profile + `</td>
            <td>` + data.profileLength + `</td>
            <td>` + data.material + `</td>
            <td>` + "akcja" + `</td>
            <td hidden>` + '<input type="number" name="itemId" value="" hidden>' + `</td>
        </tr>`
    );
};

function loadPart(id){
    var url = window.location.protocol + "//" + window.location.host;
    var jqxhr = $.post( url + "/orders/getPartFromDb", {'partId':id})
          .done(function(data) {
            console.log( "part loaded from db" );
            addPartToTable(data);
          })
          .fail(function() {
            console.log( "error" );
            alert(' nie pobrano części z bazy');
          })
};

function showData(data){
    var tableBeginning = `
    <table class="table table-sm table-hover" id="mainTable">
        <tr>
            <th>Id</th>
            <th>Nazwa części</th>
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

    $('#tableContent').html(tableBeginning + tableContent + tableEnding);
    initializeFilteredTable();
    $('.loadButton').click(function(){
        console.log(this.id);
        var id = this.id.substring(5);
        loadPart(id);
    });

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

