/**
 * Contains custom JavaScript code
 */
var urlHolder = new Object();

function loadBodegas() {
    $.get(urlHolder.records, function(response) {
		
        $('#tableBodegas').find('tbody').remove();
 		
        for (var i=0; i<response.bodegas.length; i++) {
            var row = '<tr>';
            row += '<td><input type="radio" name="index" id="index" value="'+i+'"></td>';
            row += '<td>' + response.bodegas[i].codigo + '</td>';
            row += '<td>' + response.bodegas[i].direccion + '</td>';
            row += '</tr>';
            $('#tableBodegas').append(row);
        }
 		
        $('#tableBodegas').data('model', response.bodegas);
        toggleFormsBodega('hide'); 
    });
}

function submitNewBodega() {
    $.post(urlHolder.add, {
			codigo: $('#newCodigo').val(),
			direccion: $('#newDireccion').val()
		},
		function(response) {
			if (response != null) {
				loadBodegas();
				toggleFormsBodega('hide');;
				toggleCrudButtonsBodega('show');
				alert('Bodega creada exitosamente!');
			} else {
				alert('Error al crear la bodega, por favon intente nuevamente.');
			}
		}
	);
}

function submitDeleteBodega() {
    var selected = $('input:radio[name=index]:checked').val();
	
    $.post(urlHolder.del, {
        codigo: $('#tableBodegas').data('model')[selected].codigo
    },
    function(response) {
        if (response == true) {
            loadBodegas(urlHolder.records);
            alert('Bodega borrada exitosamente!');
        } else {
            alert('Error al borrar la bodega, por favon intente nuevamente.');
        }
    }
    );
}

function submitUpdateBodega() {
    $.post(urlHolder.edit, {
			codigo: $('#editCodigo').val(),
			direccion: $('#editDireccion').val(),
			codigoAnterior: $('#editCodigoAnterior').val()
		},
		function(response) {
			if (response != null) {
				loadBodegas();
				toggleFormsBodega('hide');;
				toggleCrudButtonsBodega('show');
				alert('Bodega actualizada exitosamente!');
			} else {
				alert('Error al actualizar la bodega, por favon intente nuevamente.');
			}
		}
	);
}


function hasSelectedBodega() {
    var selected = $('input:radio[name=index]:checked').val();
    if (selected == undefined) {
        alert('Seleccione una bodega!');
        return false;
    }
	
    return true;
}

function fillEditFormBodega() {
    var selected = $('input:radio[name=index]:checked').val();
    $('#editCodigoAnterior').val( $('#tableBodegas').data('model')[selected].codigo );
    $('#editCodigo').val( $('#tableBodegas').data('model')[selected].codigo );
    $('#editDireccion').val( $('#tableBodegas').data('model')[selected].direccion );
}

function resetNewFormBodega() {
    $('#newCodigo').val('');
    $('#newDireccion').val('');
}

function resetEditFormBodega() {
    $('#editCodigo').val('');
    $('#editDireccion').val('');
    $('#editCodigoAnterior').val('');
}

function toggleFormsBodega(id) {
    if (id == 'hide') {
        $('#newForm').hide();
        $('#editForm').hide();
		
    } else if (id == 'new') {
        resetNewFormBodega();
        $('#newForm').show();
        $('#editForm').hide();
 		
    } else if (id == 'edit') {
        resetEditFormBodega();
        $('#newForm').hide();
        $('#editForm').show();
    }
}

function toggleCrudButtonsBodega(id) {
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
