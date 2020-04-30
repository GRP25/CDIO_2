
$(document).ready(() => {
    loadDatabase();
});


    function formHandler() {
        event.preventDefault();

        let password = $('#pass').val();
        let username = $('#bNavn').val();

        if(password == 'test' && username == 'test') {
            $('#loginScreen').hide();
            $('#sh').show();
            $('#sr').show();
        } else {
            alert("Wrong username and/or password");

        }
    }

function createUser() {
    $('#resultview').hide();

    //call function
    $.ajax({url: 'java/data/main',
        data: $('#form').serializeJSON(),
        contentType: "application/json",
        method: 'POST', 
        success: function (data) {
            $(".resultContainer").html(data);
       },
        error: function (jqXHR, text, error) {
            alert(jqXHR.status + text + error);
        }
    });
    /*
    Jeg har kigget på powerPoint slides fra lektion 11 omkring
    REST og Javascript og det er hvad jeg kom frem til.

    Kig videre på: data $('#from').serializeJSON()
    Da det er her hvor dataen bliver loadet ind.
     */


    //load data into view displayet to user
    $('#createUser').load(data);

    //show container
    $('#user').show();

}

function updateUser() {

}

function getUser() {

}

function listUser() {
        $('#list').show();
}

function deleteUser() {

}

function loadDatabase() {

}

/*
Er der en funktion for meget eller mangler der en i
html koden og derved også i CSS?
 */
