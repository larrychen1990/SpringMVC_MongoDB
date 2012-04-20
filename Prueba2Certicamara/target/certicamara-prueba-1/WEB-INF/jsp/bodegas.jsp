<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<c:url value="/bodegas/records" var="recordsUrl"/>
<c:url value="/bodegas/create" var="addUrl"/>
<c:url value="/bodegas/update" var="editUrl"/>
<c:url value="/bodegas/delete" var="deleteUrl"/>

<html>
    <head>
        <link rel='stylesheet' type='text/css' media='screen' href='<c:url value="/resources/css/style.css"/>'/>
        <script type='text/javascript' src='<c:url value="/resources/js/jquery-1.6.4.min.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/resources/js/bodega.js"/>'></script>

        <title>Bodegas</title>

        <script type='text/javascript'>
            $(function() {
                // init
                urlHolder.records = '${recordsUrl}';
                urlHolder.add = '${addUrl}';
                urlHolder.edit = '${editUrl}';
                urlHolder.del = '${deleteUrl}';
                loadBodegas();
		
                $('#newBtn').click(function() {
                    toggleFormsBodega('new');
                    toggleCrudButtonsBodega('hide');
                });
		
                $('#editBtn').click(function() {
                    if (hasSelectedBodega()) {
                        toggleFormsBodega('edit');
                        toggleCrudButtonsBodega('hide');
                        fillEditFormBodega();
                    }
                });
		
                $('#reloadBtn').click(function() {
                    loadBodegas();
                });

                $('#deleteBtn').click(function() {
                    if (hasSelectedBodega()) {
                        submitDeleteBodega();
                    }
                });
		
                $('#newForm').submit(function() {
                    event.preventDefault();
                    submitNewBodega();
                });
		
                $('#editForm').submit(function() {
                    event.preventDefault();
                    submitUpdateBodega();
                });

                $('#closeNewForm').click(function() {
                    toggleFormsBodega('hide');
                    toggleCrudButtonsBodega('show');
                });
		
                $('#closeEditForm').click(function() {
                    toggleFormsBodega('hide');
                    toggleCrudButtonsBodega('show');
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
        <h1 id='banner'>Bodegas</h1>
        <hr/>

        <table id='tableBodegas'>
            <caption></caption>
            <thead>
                <tr>
                    <th></th>
                    <th>Codigo</th>
                    <th>Direccion</th>
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
                    <legend>Crear Bodega</legend>
                    <label for='newCodigo'>Codigo</label><input type='text' id='newCodigo'/><br/>
                    <label for='newDireccion'>Direccion</label><input type='text' id='newDireccion'/><br/>
                </fieldset>
                <input type='button' value='Cancelar' id='closeNewForm' />
                <input type='submit' value='Crear'/>
            </form>
        </div>

        <div id='editForm'>
            <form action="" >
                <fieldset>
                    <legend>Editar Bodega</legend>
                    <input type='hidden' id='editCodigoAnterior'/>
                    <label for='editCodigo'>Codigo</label><input type='text' id='editCodigo'/><br/>
                    <label for='editDireccion'>Direccion</label><input type='text' id='editDireccion'/><br/>
                </fieldset>
                <input type='button' value='Cancelar' id='closeEditForm' />
                <input type='submit' value='Actualizar'/>
            </form>
        </div>

    </body>
</html>