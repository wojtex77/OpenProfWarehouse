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
            '<td><button type="button" class="btn btn-sm removeButton" id="remove-' + data.id + '" disabled>Usuń</button></td>' +`
            <td hidden>` + '<input type="number" name="partId" value = "' + data.id + '">' + `</td>
            <td hidden>` + '<input type="number" name="itemId" value="" hidden>' + `</td>
        </tr>`
    );
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
            '<td><button type="button" class="btn btn-sm removeButton" id="remove-' + data.signature + '" disabled>Usuń</button></td>' +`
        </tr>`
    );
};

function addHiddenItemInput(data){

    $('#inputs').append('<input type="number" class="orderedItemId" name="orderedItemId[]" value="' + data.id + '" id="part-' + data.id + '" readonly />');

}

function addHiddenProfileInput(data){

    $('#inputs').append('<input type="text" class="stockItemId" name="stockItemId[]" value="' + data.signature + '" id="profile-' + data.signature + '" readonly />');

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

function showSingleProfileNesting(profile){


    var strBuilder = [];
    for(key in profile.itemsOnProfile) {
      if (profile.itemsOnProfile.hasOwnProperty(key)) {
        strBuilder.push("Key is " + key + ", value is " + profile.itemsOnProfile[key] + "\n");

        var stringToParse = key.substring(20).replace(/'/g, '"');
        console.log(stringToParse);
        var object = jQuery.parseJSON(stringToParse);

      }
    }

    alert(strBuilder.join(""));

    $('#nestingDetails').append(`
        <div class="row">
            <div class="col-1">Certyfikat: </div>
            <div class="fw-bold col-11">` + profile.profileSignature + `</div>
            <div class="col-2 offset-2">Część: <span class="fw-bold">` + profile.itemsOnProfile + `</span></div><div class="col-1"> L= 650</div><div class="col-7"> sztuk 3</div>
            <div class="col-2 offset-2">Część: <span class="fw-bold">234</span></div><div class="col-1"> L= 125</div><div class="col-7"> sztuk 8</div>
        </div>
    `);

};

function showNestingDetails(data){

    showSingleProfileNesting(data[0]);

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


    var url = window.location.protocol + "//" + window.location.host;
    var jqxhr = $.post( url + "/wsrest/nest", {
            "stockItemsSignatures" : stockItemsSignatures,
            "orderedItemsIds": orderedItemsIds
          })
          .done(function(data) {
            console.log("Nesting done");
            showNestingDetails(data);

          })
          .fail(function() {
            console.log( "error" );
            alert('Nie udało się wykonać nestingu, sprawdź czy wczytałeś części oraz materiał wejściowy');
          });
};

$(document).ready(function(){
    var partsFromDBModal = new bootstrap.Modal(document.getElementById('partsFromDbModal'));
    var showPartsFromDBButton = $('#showPartFromDb');

    var profilesFromDBModal = new bootstrap.Modal(document.getElementById('profilesFromDbModal'));
    var showProfilesFromDBButton = $('#showProfilesFromDb');

    var runNestingButton = $('#runNestingBtn');

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

    showProfilesFromDBButton.click(function(){
        profilesFromDBModal.show();
        getProfilesFromStock();
    });

    runNestingButton.click(function(){
        runNesting();

    });
});