
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
    $.ajax('java/sql/main', post)

    //load data into view displayet to user
    $('.createUser').load(data)

    //show container
    $('createUser').show();
}

function updateUser() {

}

function getUser() {

}

function listUser() {

}

function deleteUser() {

}

function loadDatabase() {

}
