<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<c:url value="/proveedores/records" var="recordsUrl"/>
<c:url value="/proveedores/create" var="addUrl"/>
<c:url value="/proveedores/update" var="editUrl"/>
<c:url value="/proveedores/delete" var="deleteUrl"/>

<html>
    <head>
        <link rel='stylesheet' type='text/css' media='screen' href='<c:url value="/resources/css/style.css"/>'/>
        <script type='text/javascript' src='<c:url value="/resources/js/jquery-1.6.4.min.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/resources/js/proveedor.js"/>'></script>

        <title>Proveedores</title>

        <script type='text/javascript'>
            $(function() {
                // init
                urlHolder.records = '${recordsUrl}';
                urlHolder.add = '${addUrl}';
                urlHolder.edit = '${editUrl}';
                urlHolder.del = '${deleteUrl}';
                loadProveedores();
		
                $('#newBtn').click(function() {
                    toggleFormsProveedor('new');
                    toggleCrudButtonsProveedor('hide');
                });
		
                $('#editBtn').click(function() {
                    if (hasSelectedProveedor()) {
                        toggleFormsProveedor('edit');
                        toggleCrudButtonsProveedor('hide');
                        fillEditFormProveedor();
                    }
                });
		
                $('#reloadBtn').click(function() {
                    loadProveedores();
                });

                $('#deleteBtn').click(function() {
                    if (hasSelectedProveedor()) {
                        submitDeleteProveedor();
                    }
                });
		
                $('#newForm').submit(function() {
                    event.preventDefault();
                    submitNewProveedor();
                });
		
                $('#editForm').submit(function() {
                    event.preventDefault();
                    submitUpdateProveedor();
                });

                $('#closeNewForm').click(function() {
                    toggleFormsProveedor('hide');
                    toggleCrudButtonsProveedor('show');
                });
		
                $('#closeEditForm').click(function() {
                    toggleFormsProveedor('hide');
                    toggleCrudButtonsProveedor('show');
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
        <h1 id='banner'>Proveedores</h1>
        <hr/>        
        <table id='tableBodegas'>
            <caption></caption>
            <thead>
                <tr>
                    <th></th>
                    <th>Nombre</th>
                    <th>Direccion</th>
                    <th>Telefono</th>
                    <th>Mail</th>
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
                    <legend>Crear Proveedor</legend>
                    <label for='newNombre'>Nombre</label><input type='text' id='newNombre'/><br/>
                    <label for='newDireccion'>Direccion</label><input type='text' id='newDireccion'/><br/>
                    <label for='newTelefono'>Telefono</label><input type='text' id='newTelefono'/><br/>
                    <label for='newMail'>Mail</label><input type='text' id='newMail'/><br/>
                </fieldset>
                <input type='button' value='Cancelar' id='closeNewForm' />
                <input type='submit' value='Crear'/>
            </form>
        </div>

        <div id='editForm'>
            <form action="" >
                <fieldset>
                    <legend>Editar Proveedor</legend>
                    <input type='hidden' id='editNombreAnterior'/>
                    <label for='editNombre'>Nombre</label><input type='text' id='editNombre'/><br/>
                    <label for='editDireccion'>Direccion</label><input type='text' id='editDireccion'/><br/>
                    <label for='editTelefono'>Telefono</label><input type='text' id='editTelefono'/><br/>
                    <label for='editMail'>Mail</label><input type='text' id='editMail'/><br/>
                </fieldset>
                <input type='button' value='Cancelar' id='closeEditForm' />
                <input type='submit' value='Actualizar'/>
            </form>
        </div>

    </body>
</html>