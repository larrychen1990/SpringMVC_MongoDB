<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<c:url value="/productos/records" var="recordsUrl"/>
<c:url value="/productos/bodegas" var="bodegasUrl"/>
<c:url value="/productos/proveedores" var="proveedoresUrl"/>
<c:url value="/productos/create" var="addUrl"/>
<c:url value="/productos/update" var="editUrl"/>
<c:url value="/productos/delete" var="deleteUrl"/>


<html>
    <head>
        <link rel='stylesheet' type='text/css' media='screen' href='<c:url value="/resources/css/style.css"/>'/>
        <script type='text/javascript' src='<c:url value="/resources/js/jquery-1.6.4.min.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/resources/js/producto.js"/>'></script>

        <title>Productos</title>

        <script type='text/javascript'>
            $(function() {
                // init
                urlHolder.records = '${recordsUrl}';
                urlHolder.bodegas = '${bodegasUrl}';
                urlHolder.proveedores = '${proveedoresUrl}';
                urlHolder.add = '${addUrl}';
                urlHolder.edit = '${editUrl}';
                urlHolder.del = '${deleteUrl}';
                loadProductos();
		
                $('#newBtn').click(function() {                    
                    toggleFormsProducto('new');
                    toggleCrudButtonsProducto('hide');
                    loadBodegas();
                    loadProveedores();
                });
		
                $('#editBtn').click(function() {
                    if (hasSelectedProducto()) {                        
                        toggleFormsProducto('edit');
                        toggleCrudButtonsProducto('hide');
                        fillEditFormProducto();
                        loadBodegas();
                        loadProveedores();
                    }
                });
		
                $('#reloadBtn').click(function() {
                    loadProductos();
                });

                $('#deleteBtn').click(function() {
                    if (hasSelectedProducto()) {
                        submitDeleteProducto();
                    }
                });
		
                $('#newForm').submit(function() {
                    event.preventDefault();
                    submitNewProducto();
                });
		
                $('#editForm').submit(function() {
                    event.preventDefault();
                    submitUpdateProducto();
                });

                $('#closeNewForm').click(function() {
                    toggleFormsProducto('hide');
                    toggleCrudButtonsProducto('show');
                });
		
                $('#closeEditForm').click(function() {
                    toggleFormsProducto('hide');
                    toggleCrudButtonsProducto('show');
                });
            });
        </script>
    </head>

    <body>
        <center>
            <a href="bodegas.htm">Bodegas</a>
            <a href="proveedores.htm">Proveedores</a>
            <a href="productos.htm">Productos</a>
        </center>
        <h1 id='banner'>Productos</h1>
        <hr/>        
        <table id='tableBodegas'>
            <caption></caption>
            <thead>
                <tr>
                    <th></th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Proveedor</th>
                    <th>Bodega</th>
                </tr>
            </thead>
        </table>

        <div id='controlBar'>
            <input type='button' value='Crear' id='newBtn' />
            <input type='button' value='Borrar' id='deleteBtn' />
            <input type='button' value='Editar' id='editBtn' />
            <input type='button' value='Listar' id='reloadBtn' />
        </div>

        <div id='newForm'>
            <form action="" >
                <fieldset>
                    <legend>Crear Producto</legend>
                    <label for='newNombre'>Nombre</label><input type='text' id='newNombre'/><br/>
                    <label for='newDireccion'>Precio</label><input type='text' id='newDireccion'/><br/>
                    <label for='newTelefono'>Cantidad</label><input type='text' id='newTelefono'/><br/>
                    
                    <label for='proveedoresNew'>Proveedores</label>
                    <select id='proveedoresNew'>
                    </select><br/>

                    <label for='bodegasNew'>Bodega</label>
                    <select id='bodegasNew'>
                    </select><br/>
                </fieldset>
                <input type='button' value='Cancelar' id='closeNewForm' />
                <input type='submit' value='Crear'/>
            </form>
        </div>

        <div id='editForm'>
            <form action="" >
                <fieldset>
                    <legend>Editar Producto</legend>
                    <input type='hidden' id='editNombreAnterior'/>
                    <label for='editNombre'>Nombre</label><input type='text' id='editNombre'/><br/>
                    <label for='editDireccion'>Precio</label><input type='text' id='editDireccion'/><br/>
                    <label for='editTelefono'>Cantidad</label><input type='text' id='editTelefono'/><br/>                    

                    <label for='proveedoresEdit'>Proveedores</label>
                    <select id='proveedoresEdit'>
                    </select><br/>

                    <label for='bodegasEdit'>Bodega</label>
                    <select id='bodegasEdit'>
                    </select><br/>

                </fieldset>
                <input type='button' value='Cancelar' id='closeEditForm' />
                <input type='submit' value='Actualizar'/>
            </form>
        </div>

    </body>
</html>