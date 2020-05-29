$("#myModal").on('show.bs.modal', function (e) {
    var userId = $(e.relatedTarget).data('user-id');

    var cols = $('#' + userId + ' td');
    var id = $(cols[0]).text();
    var firstName = $(cols[1]).text();
    var lastName = $(cols[2]).text();
    var age = $(cols[3]).text();
    var email = $(cols[4]).text();
    var role = $(cols[5]).text();

    $('#idInput').val(id);
    $('#firstNameInput').val(firstName);
    $('#lastNameInput').val(lastName);
    $('#emailInput').val(email);
    $('#ageInput').val(age);
    $('#roleInput').val(role);

});

$("#myModalDelete").on('show.bs.modal', function (e) {
    var user = $(e.relatedTarget).data('user-delete');

    var col = $('#' + user + ' td');
    var id = $(col[0]).text();
    var firstName = $(col[1]).text();
    var lastName = $(col[2]).text();
    var age = $(col[3]).text();
    var email = $(col[4]).text();
    var role = $(col[5]).text();

    $('#id').val(id);
    $('#firstName').val(firstName);
    $('#lastName').val(lastName);
    $('#email').val(email);
    $('#age').val(age);
    $('#role').val(role);
});


$("#myModal").on('hidden.bs.modal', function () {
    var form = $(this).find('form');
    form[0].reset();
});

