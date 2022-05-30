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

function initializeProfilesFromDbTable(){
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

        var filteredTable = new TableFilter(document.querySelector('#tableWithProfilesFromDb'), tfConfig);
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

function loadProfile(signature){
    var url = window.location.protocol + "//" + window.location.host;
    var jqxhr = $.post( url + "/stock/getsignature", {'signature':signature})
          .done(function(data) {
            console.log( "profile loaded from db" );
            addProfileToTable(data);
            addHiddenProfileInput(data);
          })
          .fail(function() {
            console.log( "error" );
            alert(' nie pobrano profila z bazy');
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
            '<td><button type="button" class="btn btn-sm removePartButton" id="removePart-' + data.id + '">Usuń</button></td>' +`
            <td hidden>` + '<input type="number" name="partId" value = "' + data.id + '">' + `</td>
            <td hidden>` + '<input type="number" name="itemId" value="" hidden>' + `</td>
        </tr>`
    );

    $('.removePartButton').click(function(){
            partId = this.id.substring(11);
            removeOrderedItem(partId);
        });
};

function addProfileToTable(data){
    $('#tableWithProfiles tr:last').after(
       '<tr id="rowProfile-' + data.signature + '">' + `
            <td>` + data.signature + `</td>
            <td>` + data.profile + `</td>
            <td>` + data.material +`</td>
            <td>` + data.profileLength + `</td>
            <td>` + data.qty + `</td>
            <td>` + data.availableQty + `</td>
            <td>` + data.singleWeight + `</td>
            <td>` + data.wholeWeight + `</td>` +
            '<td><button type="button" class="btn btn-sm removeStockButton" id="removeStock-' + data.signature + '">Usuń</button></td>' +`
        </tr>`
    );

    $('.removeStockButton').click(function(){
        stockId = this.id.substring(12);
        removeStockItem(stockId);
    });
};

function addHiddenItemInput(data){

    $('#inputs').append('<input type="number" class="orderedItemId" name="orderedItemId[]" value="' + data.id + '" id="part-' + data.id + '" readonly hidden />');

}

function removeOrderedItem(partId){
    $('#rowPart-' + partId).remove();
    $('#part-' + partId).remove();
}


function removeStockItem(stockId){
    $('#rowProfile-' + stockId).remove();
    $('#profile-' + stockId).remove();
}

function addHiddenProfileInput(data){

    $('#inputs').append('<input type="text" class="stockItemId" name="stockItemId[]" value="' + data.signature + '" id="profile-' + data.signature + '" readonly hidden />');

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

function getProfilesFromStock(){

    var url = window.location.protocol + "//" + window.location.host;

    var jqxhr = $.post( url + "/stock/getstock")
      .done(function(data) {
        console.log( "all profiles loaded from db" );
        showProfilesInModal(data);
      })
      .fail(function() {
        console.log( "error" );
        //$('tableWithOrderedItemsFromDb').html('Nie udało się wczytać danych');
      })
};

function showProfilesInModal(data){
    var tableBeginning = `
            <table class="table table-sm table-hover" id="profilesTable">
                <tr>
                    <th>Certyfikat</th>
                    <th>Profil</th>
                    <th>Materiał</th>
                    <th>Długość [mm]</th>
                    <th>Ilość</th>
                    <th>Ilość dostępna</th>
                    <th>Masa sztuki [kg]</th>
                    <th>Masa całkowita [kg]</th>
                    <th>Akcje</th>
                </tr>

                `;
             var tableContent="";
             for(let i=0; i< data.length; i++){
                 tableContent = tableContent + `
                   <tr>
                        <td>` + data[i].signature + `</td>
                        <td>` + data[i].profile + `</td>
                        <td>` + data[i].material + `</td>
                        <td>` + data[i].profileLength + `</td>
                        <td>` + data[i].qty + `</td>
                        <td>` + data[i].availableQty + `</td>
                        <td>` + data[i].singleWeight + `</td>
                        <td>` + data[i].wholeWeight + `</td>` +
                        '<td><button type="button" class="btn btn-sm loadProfileButton" id="loadProfile-' + data[i].signature + '">Wczytaj</button></td>' +
                   `</tr>
                `
                };
              var tableEnding = "</table>";

            $('#tableWithProfilesFromDb').html(tableBeginning + tableContent + tableEnding);
            initializeProfilesFromDbTable();

            $('.loadProfileButton').click(function(){
                var signature = this.id.substring(12);
                loadProfile(signature);
            });
};

function showSingleProfileNesting(profiles){

    for(var i = 0; i < profiles.length; i++){
        $('#nestingDetails').append(`
            <div class='row my-3 bg-dark bg-gradient p-2 bg-opacity-10'>
                <div class="col-1">Profil: </div>
                <div class="col-11"><span class = "fw-bold">` + profiles[i].stockItem.signature + `</span><span class="fw-light fw-italic"> (L= ` + profiles[i].stockItem.profileLength + ` mm)</span> powtórzeń: ` + profiles[i].repetition + `</div>` +
                showPartsOnSingleProfile(profiles[i].itemsOnProfile) + `
            </div>
        `);
    };

};

function showPartsOnSingleProfile(data){
    var html = "";
    for(var i=0; i< data.length; i++){
        html += `<div class="col-4 offset-2">Część: <span class="fw-bold">` + data[i].partName + `</span></div><div class="col-3">L [mm]= <span class="fw-bold">` + data[i].profileLength + `</span></div><div class="col-3"> sztuk: <span class="fw-bold">` + data[i].nestedQty + `</span></div>`;
    };
    return html;
}

function showNestingDetails(data){
    $('#nestingDetails').empty();
    showSingleProfileNesting(data.profileNestedList);

};

function runNesting(){
    var stockItemsSignatures = [];
    $('input[name="stockItemId[]"]').each(function() {
        stockItemsSignatures.push($(this).val());
    });

    var orderedItemsIds = [];
    $('input[name="orderedItemId[]"]').each(function() {
        orderedItemsIds.push($(this).val());
    });

    var profileMargin = $('#profileMargin').val();
    var partDistance = $('#partDistance').val();
    var minRemnantLength = $('#minRemnantLength').val();


    var url = window.location.protocol + "//" + window.location.host;
    var jqxhr = $.post( url + "/wsrest/nest", {
            "stockItemsSignatures" : stockItemsSignatures,
            "orderedItemsIds": orderedItemsIds,
            "profileMargin": profileMargin,
            "partDistance": partDistance,
            "minRemnantLength": minRemnantLength
          })
          .done(function(data) {
            console.log("Nesting done");
            showNestingDetails(data);
            showItemsStatus(data);
            showRemnants(data);
            $('#makeReservationBtn').click(function (){
                $(this).show();
            });

          })
          .fail(function() {
            console.log( "error" );
            alert('Nie udało się wykonać nestingu, sprawdź czy wczytałeś części oraz materiał wejściowy');
          });
};

function makeReservationAction(){
    var stockItemsSignatures = [];
    $('input[name="stockItemId[]"]').each(function() {
        stockItemsSignatures.push($(this).val());
    });

    var orderedItemsIds = [];
    $('input[name="orderedItemId[]"]').each(function() {
        orderedItemsIds.push($(this).val());
    });

    var profileMargin = $('#profileMargin').val();
    var partDistance = $('#partDistance').val();
    var minRemnantLength = $('#minRemnantLength').val();


    var url = window.location.protocol + "//" + window.location.host;
    var jqxhr = $.post( url + "/wsrest/makeReservation", {
            "stockItemsSignatures" : stockItemsSignatures,
            "orderedItemsIds": orderedItemsIds,
            "profileMargin": profileMargin,
            "partDistance": partDistance,
            "minRemnantLength": minRemnantLength
          })
          .done(function(data) {
            console.log("Reservation done");
            showNestingDetails(data);
            showItemsStatus(data);
            showRemnants(data);
            $('#makeReservationBtn').click(function (){
                $(this).hide();
            });
            $('#makeReservationBtn').click(function (){
                $(this).hide();
            });
          })
          .fail(function() {
            console.log( "error" );
            alert('Nie udało się wykonać rezerwacji');
          });
};

function showItemsStatus(data){
    $('#nestedPartsDetails').empty();
    var parts = data.orderedItemsExtendedList;

    var type;
    for (var i = 0; i< parts.length; i++){

        if(parts[i].qty == parts[i].nestedQty) type = `<div class="row my-3 bg-success bg-gradient p-2 bg-opacity-10">`;
        if((parts[i].qty > parts[i].nestedQty) && (parts[i].nestedQty != 0)) type = `<div class="row my-3 bg-primary bg-gradient p-2 bg-opacity-10">`;
        if(parts[i].nestedQty == 0) type = `<div class="row my-3 p-2 bg-warning bg-gradient bg-opacity-10">`;

        $('#nestedPartsDetails').append(
            type +
            `
                    <div class="col-5">Część:</div>
                    <div class="fw-bold col-7">` + parts[i].partName + `</div>
                    <div class="col-5">Długość:</div>
                    <div class="fw-bold col-7">` + parts[i].profileLength + `</div>
                    <div class="col-5">Zlecenie:</div>
                    <div class="fw-bold col-7">` + parts[i].orderNumber + `</div>
                    <div class="col-5">Ilość zlecona:</div>
                    <div class="fw-bold col-7">` + parts[i].qty + `</div>
                    <div class="col-5">Ilość rozłożona:</div>
                    <div class="fw-bold col-7">` + parts[i].nestedQty + `</div>
                </div>
            `
        );

    }

};


function showRemnants(data){
    $('#remnantsDetails').empty();
    var remnants = data.remnantList;

    for (var i = 0; i< remnants.length; i++){

        $('#remnantsDetails').append(
           `<div class="row my-3 p-2 bg-dark bg-gradient bg-opacity-10">
                <div class="col-5">Nazwa:</div>
                <div class="fw-bold col-7">` + remnants[i].signature + `</div>
                <div class="col-5">Długość:</div>
                <div class="fw-bold col-7">` + remnants[i].profileLength + `</div>
                <div class="col-5">Ilość:</div>
                <div class="fw-bold col-7">` + remnants[i].qty + `</div>
            </div>
            `
        );

    }

};

$(document).ready(function(){
    var partsFromDBModal = new bootstrap.Modal(document.getElementById('partsFromDbModal'));
    var showPartsFromDBButton = $('#showPartFromDb');

    var profilesFromDBModal = new bootstrap.Modal(document.getElementById('profilesFromDbModal'));
    var showProfilesFromDBButton = $('#showProfilesFromDb');

    var runNestingButton = $('#runNestingBtn');
    var makeReservation = $('#makeReservationBtn');


    showPartsFromDBButton.click(function(){
        partsFromDBModal.show();
        getPartsFromOrders();
    });

    showProfilesFromDBButton.click(function(){
        profilesFromDBModal.show();
        getProfilesFromStock();
    });

    runNestingButton.click(function(){
        runNesting();
    });

    makeReservation.click(function(){
        makeReservationAction();
    });
});