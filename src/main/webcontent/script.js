
$(document).ready(() => {
    loadDatabase();
    $('#display').children().hide();
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

    /*$('.createUser').on('click',function () {
        createUser();
    });*/


function prepareWindow (input,text) {
    $('#display').children().hide();
    $('#resultText').text(`${text}`)
    $(`#`+ `${input}`).show();
}



function createUser() {
    event.preventDefault();
    const user = {
        cpr: $('#cpr').val(),
        name: $('#c_name').val(),
        initials: $('#c_initials').val(),
        password: $('#c_userPassword').val(),
        roles: $('#role').val()
    };
    $.ajax({url: 'https://api.mama.sh/users',
        method: 'POST',
        data: JSON.stringify(user),
        contentType: "application/json",
        success: function (response) {
            $(".resultContainer").append(response);
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
    //$('#createUser').load(data);

    //show container
   // $('#user').show();

}

function updateUser() {

    $.ajax({url: 'https://api.mama.sh/users',
        data: $('#form').serializeJSON(),
        contentType: "application/json",
        method: 'PUT',
        success: function (data) {
            $(".resultContainer").append(data);
        },
        error: function (jqXHR, text, error) {
            alert(jqXHR.status + text + error);
        }
    });

    $('#updateUser').show();
}

function getUser() {

    $.ajax({url: 'https://api.mama.sh/users',
        data: $('#form').serializeJSON(),
        contentType: "application/json",
        method: 'GET',
        success: function (data) {
            $(".resultContainer").html(data);
        },
        error: function (jqXHR, text, error) {
            alert(jqXHR.status + text + error);
        }
    });
}

function listUser() {

    $.ajax({url: 'https://api.mama.sh/users',
        data: $('#form').serializeJSON(),
        contentType: "application/json",
        method: 'GET',
        success: function (data) {
            $(".resultContainer").html(data);
        },
        error: function (jqXHR, text, error) {
            alert(jqXHR.status + text + error);
        }
    });

    $('#list').show();
}

function deleteUser() {

    $.ajax({url: 'https://api.mama.sh/users',
        data: $('#form').serializeJSON(),
        contentType: "application/json",
        method: 'DELETE',
        success: function (data) {
            $(".resultContainer").html(data);
        },
        error: function (jqXHR, text, error) {
            alert(jqXHR.status + text + error);
        }
    });
}

function loadDatabase() {

}

/*
Er der en funktion for meget eller mangler der en i
html koden og derved også i CSS?
const test = $('#userForm').submit(function(data) {
    //const data = $('form #userForm').serializeArray();
    let form = $(this);
    alert(form);
    console.log('form' + form);
    let aData = convertArrayToObject(data, 'name');
    console.log('this is data' + aData);
    //call function
    $.ajax({
        url: 'https://api.mama.sh/users',
        method: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response) {
            alert(response);
            $(".resultContainer").append(data);
        },
        error: function (jqXHR, text, error) {
            alert(jqXHR.status + text + error);
        }
    })
});
 */

const convertArrayToObject = (array, key) => {
    const initialValue = {};
    return array.reduce((obj, item) => {
        return {
            ...obj,
            [item[key]]: item,
        };
    }, initialValue);
};
