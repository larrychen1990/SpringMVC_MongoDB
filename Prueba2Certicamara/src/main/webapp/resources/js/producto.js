/**
 * Contains custom JavaScript code
 */
var urlHolder = new Object();

function loadProductos() {
    
    $.get(urlHolder.records, function(response) {

        $('#tableBodegas').find('tbody').remove();

        for (var i=0; i<response.productos.length; i++) {
            var row = '<tr>';
            row += '<td><input type="radio" name="index" id="index" value="'+i+'"></td>';
            row += '<td>' + response.productos[i].nombre + '</td>';
            row += '<td>' + response.productos[i].precio + '</td>';
            row += '<td>' + response.productos[i].cantidad + '</td>';
            if(response.productos[i].proveedor == null ){
                row += '<td></td>';
            } else {
                row += '<td>' + response.productos[i].proveedor.nombre + '</td>';
            }

            if(response.productos[i].bodega == null ){
                row += '<td></td>';
            } else {
                row += '<td>' + response.productos[i].bodega.codigo + '</td>';
            }  
            row += '</tr>';
            $('#tableBodegas').append(row);

        }
        $('#tableBodegas').data('model', response.productos);
        toggleFormsProducto('hide');;
    });
    
}

function loadBodegas(){
    $.get(urlHolder.bodegas, function(response) {

        $('#bodegasNew').find('option').remove();

        for (var i=0; i<response.bodegas.length; i++) {
            var row = '<option value="' + response.bodegas[i].codigo + '">';
            row += response.bodegas[i].direccion ;
            row += ' (' + response.bodegas[i].codigo + ')';
            row += '</option>';
            $('#bodegasNew').append(row);

        }
        $('#bodegasNew').data('model', response.bodegas);

    });

    $.get(urlHolder.bodegas, function(response) {

        $('#bodegasEdit').find('option').remove();

        for (var i=0; i<response.bodegas.length; i++) {
            var row = '<option value="' + response.bodegas[i].codigo + '">';
            row += response.bodegas[i].direccion ;
            row += ' (' + response.bodegas[i].codigo + ')';
            row += '</option>';
            $('#bodegasEdit').append(row);

        }
        $('#bodegasEdit').data('model', response.bodegas);

    });

}

function loadProveedores(){

    $.get(urlHolder.proveedores, function(response) {

        $('#proveedoresNew').find('option').remove();

        for (var i=0; i<response.proveedores.length; i++) {
            var row = '<option value="' + response.proveedores[i].nombre + '">';
            row += response.proveedores[i].nombre ;
            row += ' (' + response.proveedores[i].direccion + ')';
            row += '</option>';
            $('#proveedoresNew').append(row);

        }
        $('#proveedoresNew').data('model', response.proveedores);

    });

    $.get(urlHolder.proveedores, function(response) {

        $('#proveedoresEdit').find('option').remove();

        for (var i=0; i<response.proveedores.length; i++) {
            var row = '<option value="' + response.proveedores[i].nombre + '">';
            row += response.proveedores[i].nombre ;
            row += ' (' + response.proveedores[i].direccion + ')';
            row += '</option>';
            $('#proveedoresEdit').append(row);

        }
        $('#proveedoresEdit').data('model', response.proveedores);

    });

}

function submitNewProducto() {
    $.post(urlHolder.add, {
			nombre: $('#newNombre').val(),
			precio: $('#newDireccion').val(),
			cantidad: $('#newTelefono').val(),
			nombreProveedor: $('#proveedoresNew').val(),
			codigoBodega: $('#bodegasNew').val()
		},
		function(response) {
			if (response != null) {
				loadProductos();
				toggleFormsProducto('hide');;
				toggleCrudButtonsProducto('show');
				alert('Producto creado exitosamente!');
			} else {
				alert('Error al crear el Producto, por favon intente nuevamente.');
			}
		}
	);
}

function submitDeleteProducto() {
    var selected = $('input:radio[name=index]:checked').val();
	
    $.post(urlHolder.del, {
        nombre: $('#tableBodegas').data('model')[selected].nombre
    },
    function(response) {
        if (response == true) {
            loadProductos(urlHolder.records);
            alert('Producto borrado exitosamente!');
        } else {
            alert('Error al borrar el Producto, por favon intente nuevamente.');
        }
    }
    );
}

function submitUpdateProducto() {
    $.post(urlHolder.edit, {
			nombre: $('#editNombre').val(),
			precio: $('#editDireccion').val(),
			cantidad: $('#editTelefono').val(),
                        nombreProveedor: $('#proveedoresEdit').val(),
			codigoBodega: $('#bodegasEdit').val(),
			nombreAnterior: $('#editNombreAnterior').val()
		},
		function(response) {
			if (response != null) {
				loadProductos();
				toggleFormsProducto('hide');;
				toggleCrudButtonsProducto('show');
				alert('Producto actualizado exitosamente!');
			} else {
				alert('Error al actualizar el Producto, por favon intente nuevamente.');
			}
		}
	);
}


function hasSelectedProducto() {
    var selected = $('input:radio[name=index]:checked').val();
    if (selected == undefined) {
        alert('Seleccione un Producto!');
        return false;
    }
	
    return true;
}

function fillEditFormProducto() {
    var selected = $('input:radio[name=index]:checked').val();
    $('#editNombreAnterior').val( $('#tableBodegas').data('model')[selected].nombre );
    $('#editNombre').val( $('#tableBodegas').data('model')[selected].nombre );
    $('#editDireccion').val( $('#tableBodegas').data('model')[selected].precio );
    $('#editTelefono').val( $('#tableBodegas').data('model')[selected].cantidad );
}

function resetNewFormProducto() {
    $('#newNombre').val('');
    $('#newDireccion').val('');
    $('#newTelefono').val('');
}

function resetEditFormProducto() {
    $('#editNombre').val('');
    $('#editDireccion').val('');
    $('#editTelefono').val('');
    $('#editNombreAnterior').val('');
}

function toggleFormsProducto(id) {
    if (id == 'hide') {
        $('#newForm').hide();
        $('#editForm').hide();
		
    } else if (id == 'new') {
        resetNewFormProducto();
        $('#newForm').show();
        $('#editForm').hide();
 		
    } else if (id == 'edit') {
        resetEditFormProducto();
        $('#newForm').hide();
        $('#editForm').show();
    }
}

function toggleCrudButtonsProducto(id) {
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
