$(document).ready(function () {
  var modal = document.getElementById("myModal");
  var span = document.getElementsByClassName("close")[0];
  var update = false;
  var identification = -1;

  function hideTheModal() {
    modal.style.display = "none";
  }

  $(".close, #modal-close").click(() => {
    hideTheModal();
  });

  window.onclick = function () {
    if (this.event.target == modal) {
      hideTheModal();
    }
  };

  function updateList() {
    $.ajax({
      url: "https://api.mama.sh/users",
      type: "GET",
      success: function (data, textStatus, xhr) {
        $("#oot").html("");
        jQuery.each(data, function (index, item) {
          var li = $("<tr></tr>");
          var name = $("<td></td>").text(item.name);
          var edit = $('<td><i class="fa fa-truck"></i></td>')
          var dele = $('<td><i class="fa fa-trash"></i></td>')
          li.append(name);
          li.append(edit);
          li.append(dele);
          $("#oot").append(li);
          //Creates the update-user-form
          edit.on("click", function () {
            update = true;
            identification = item.id;
            modal.style.display = "block";
            $("#info").css("display", "block");
            $("#initials").val(item.initials);
            $("#cpr").val(item.cpr);
            $("#password").val(item.password);
            $("#name").val(item.name);
            //Checks all the roles that the user has.
            $(".roles").prop("checked", false);
            item.roles.forEach(function (i, e) {
              $("#" + i).prop("checked", true);
            });
            $("#save").val("UPDATE USER");
          });
            dele.on('click', function () {
                $.ajax({
                  url: `https://api.mama.sh/users/${item.id}`,
                  type: "DELETE",
                  success: function (msg) {
                    hideTheModal();
                    updateList();
                  },
                });
            });
        });
      },
      error: function (xhr, textStatus, errorThrown) {
        console.log("Fejler bejler");
      },
    });
  }

  $("#crbtn").click(() => {
    update = false;
    modal.style.display = "block";
    $("#initials").val("");
    $("#cpr").val("");
    $("#password").val("");
    $("#name").val("");
    //Checks all the roles that the user has.
    $(".roles").prop("checked", false);
    $("#save").val("SAVE USER");
  });

  $("#get").click(function () {
    updateList();
  });

  $("#save").click(function () {
    var roles = [];
    $(".roles:checked").each(function (i, e) {
      roles.push($(this).val());
    });

    var user = {
      name: $("#name").val(),
      initials: $("#initials").val(),
      cpr: $("#cpr").val(),
      password: $("#password").val(),
      roles: roles,
    };

    if (update) {
      user["id"] = identification;
      $.ajax({
        url: `https://api.mama.sh/users/${identification}`,
        type: "PUT",
        data: JSON.stringify(user),
        dataType: "json",
        contentType: "application/json",
        success: function (msg) {
          hideTheModal();
          updateList();
          alert(msg);
        },
      });
    } else {
      $.ajax({
        url: "https://api.mama.sh/users",
        type: "POST",
        data: JSON.stringify(user),
        dataType: "json",
        contentType: "application/json",
        success: function (msg) {
          hideTheModal();
          updateList();
          alert(msg);
        },
      });
    }
  });
});
