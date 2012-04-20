/**
 * Contains custom JavaScript code
 */
var urlHolder = new Object();

function loadProveedores() {
    $.get(urlHolder.records, function(response) {
		
        $('#tableBodegas').find('tbody').remove();
 		
        for (var i=0; i<response.proveedores.length; i++) {
            var row = '<tr>';
            row += '<td><input type="radio" name="index" id="index" value="'+i+'"></td>';
            row += '<td>' + response.proveedores[i].nombre + '</td>';
            row += '<td>' + response.proveedores[i].direccion + '</td>';
            row += '<td>' + response.proveedores[i].telefono + '</td>';
            row += '<td>' + response.proveedores[i].mail + '</td>';
            row += '</tr>';
            $('#tableBodegas').append(row);
        }
 		
        $('#tableBodegas').data('model', response.proveedores);
        toggleFormsProveedor('hide');
    });
}

function submitNewProveedor() {
    $.post(urlHolder.add, {
			nombre: $('#newNombre').val(),
			direccion: $('#newDireccion').val(),
			telefono: $('#newTelefono').val(),
			mail: $('#newMail').val()
		},
		function(response) {
			if (response != null) {
				loadProveedores();
				toggleFormsProveedor('hide');;
				toggleCrudButtonsProveedor('show');
				alert('Proveedor creado exitosamente!');
			} else {
				alert('Error al crear el proveedor, por favon intente nuevamente.');
			}
		}
	);
}

function submitDeleteProveedor() {
    var selected = $('input:radio[name=index]:checked').val();
	
    $.post(urlHolder.del, {
        nombre: $('#tableBodegas').data('model')[selected].nombre
    },
    function(response) {
        if (response == true) {
            loadProveedores(urlHolder.records);
            alert('Proveedor borrado exitosamente!');
        } else {
            alert('Error al borrar el proveedor, por favon intente nuevamente.');
        }
    }
    );
}

function submitUpdateProveedor() {
    $.post(urlHolder.edit, {
			nombre: $('#editNombre').val(),
			direccion: $('#editDireccion').val(),
			telefono: $('#editTelefono').val(),
			mail: $('#editMail').val(),
			nombreAnterior: $('#editNombreAnterior').val()
		},
		function(response) {
			if (response != null) {
				loadProveedores();
				toggleFormsProveedor('hide');;
				toggleCrudButtonsProveedor('show');
				alert('Proveedor actualizado exitosamente!');
			} else {
				alert('Error al actualizar el proveedor, por favon intente nuevamente.');
			}
		}
	);
}


function hasSelectedProveedor() {
    var selected = $('input:radio[name=index]:checked').val();
    if (selected == undefined) {
        alert('Seleccione un proveedor!');
        return false;
    }
	
    return true;
}

function fillEditFormProveedor() {
    var selected = $('input:radio[name=index]:checked').val();
    $('#editNombreAnterior').val( $('#tableBodegas').data('model')[selected].nombre );
    $('#editNombre').val( $('#tableBodegas').data('model')[selected].nombre );
    $('#editDireccion').val( $('#tableBodegas').data('model')[selected].direccion );
    $('#editTelefono').val( $('#tableBodegas').data('model')[selected].telefono );
    $('#editMail').val( $('#tableBodegas').data('model')[selected].mail );
}

function resetNewFormProveedor() {
    $('#newNombre').val('');
    $('#newDireccion').val('');
    $('#newTelefono').val('');
    $('#newMail').val('');
}

function resetEditFormProveedor() {
    $('#editNombre').val('');
    $('#editDireccion').val('');
    $('#editTelefono').val('');
    $('#editMail').val('');
    $('#editNombreAnterior').val('');
}

function toggleFormsProveedor(id) {
    if (id == 'hide') {
        $('#newForm').hide();
        $('#editForm').hide();
		
    } else if (id == 'new') {
        resetNewFormProveedor();
        $('#newForm').show();
        $('#editForm').hide();
 		
    } else if (id == 'edit') {
        resetEditFormProveedor();
        $('#newForm').hide();
        $('#editForm').show();
    }
}

function toggleCrudButtonsProveedor(id) {
    if (id == 'show') {
        $('#newBtn').removeAttr('disabled');
        $('#editBtn').removeAttr('disabled');
        $('#deleteBtn').removeAttr('disabled');
        $('#reloadBtn').removeAttr('disabled');
		
    } else if (id == 'hide'){
        $('#newBtn').attr('disabled', 'disabled');
        $('#editBtn').attr('disabled', 'disabled');
        $('#deleteBtn').attr('disabled', 'disabled');
        $('#reloadBtn').attr('disabled', 'disabled');
    }
}
