$(document).ready(() => {
	loadDatabase();
	$("#display").children().hide();
});

function formHandler() {
	event.preventDefault();

	let password = $("#pass").val();
	let username = $("#bNavn").val();

	if (password == "test" && username == "test") {
		$("#loginScreen").hide();
		$("#sh").show();
		$("#sr").show();
	} else {
		alert("Wrong username and/or password");
	}
}

function prepareWindow(input, text) {
	$("#display").children().hide();
	$("#resultText").text(`${text}`);
	$(`#` + `${input}`).show();
}

function createUser() {
	document.getElementById("loaderID").style.display = "block";

	event.preventDefault();
	const user = {
		cpr: $("#cpr").val(),
		name: $("#c_name").val(),
		initials: $("#c_initials").val(),
		password: $("#c_userPassword").val(),
		roles: $("#role").val(),
	};
	$.ajax({
		url: "https://api.mama.sh",
		method: "POST",
		data: JSON.stringify(user),
		contentType: "application/json",
		success: function (response) {
			/*
            Hide container -
            empty result container
            input response into result container
            Show result container
             */
			document.getElementById("loaderID").style.display = "none";
			$(".createUser").hide();
			$(".showResult").html(`<p> User ${user.name} added </p>`);
			$(".showResult").show();
		},
		error: function (jqXHR, text, error) {
			document.getElementById("loaderID").style.display = "none";
			alert(jqXHR.status + text + error);
		},
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
	$.ajax({
		url: "https://api.mama.sh/users",
		data: $("#form").serializeJSON(),
		contentType: "application/json",
		method: "PUT",
		success: function (data) {
			$(".resultContainer").append(data);
		},
		error: function (jqXHR, text, error) {
			alert(jqXHR.status + text + error);
		},
	});

	$("#updateUser").show();
}

/*$(document).ready(function () {
    $("#testknap").click(()=>{
        document.getElementById("loaderID").style.display="block";
    });
});*/

function getUser() {
	event.preventDefault();
	document.getElementById("loaderID").style.display = "block";

	const user = {
		userID: $("#userID").val(),
	};

	$.ajax({
		url: `https://api.mama.sh/${user.userID}`,
		method: "GET",
		datatype: "json",
		success: function (response) {
			document.getElementById("loaderID").style.display = "none";
			$(".getUser").hide();
			printerUser(response);
			$(".showResult").show();
		},
		error: function (jqXHR, text, error) {
			document.getElementById("loaderID").style.display = "none";
			alert(jqXHR.status + text + error);
		},
	});
}

function listUser() {
	$.ajax({
		url: "https://api.mama.sh/",
		contentType: "application/json",
		method: "GET",
		success: function (response) {
			$("#showResult").html("");
			let html =
				'<table class="tableOfUsers"> <tr><th>Name</th><th>Id</th></tr>';
			$.each(response, (i, item) => {
				html += `<tr>`;
				html += `<td> ${item.name}</td>`;
				html += `<td> ${item.id}</td>`;
				html += `</tr>`;
			});
			html += "</table>";
			console.log(html);
			$("#showResult").append(html);

			$("#showResult").show();
		},
		error: function (jqXHR, text, error) {
			alert(jqXHR.status + text + error);
		},
	});

	$("#list").show();
}

function deleteUser() {
	event.preventDefault();
	document.getElementById("loaderID").style.display = "block";

	const user = {
		dID: $("#deleteID").val(),
	};

	$.ajax({
		url: `https://api.mama.sh/${user.dID}`,
		method: "DELETE",
		success: function (response) {
			document.getElementById("loaderID").style.display = "none";
			$(".resultContainer").append(response);
			$(".deleteUser").hide();
			$(".showResult").html(`<p> User deleted </p>`);
			$(".showResult").show();
		},
		error: function (jqXHR, text, error) {
			document.getElementById("loaderID").style.display = "none";
			alert(jqXHR.status + text + error);
		},
	});
}

function loadDatabase() {}

function printerUser(user) {
	$(".showResult").html(
		`
        CPR nummer: ${user.cpr}<br>
        Fulde Navn: ${user.name}<br>
        Initialer: ${user.initials}<br>
        Roller: ${user.roles}<br>
        `
	);
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
