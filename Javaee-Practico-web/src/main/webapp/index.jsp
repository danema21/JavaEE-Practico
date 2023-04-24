<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CRUD Empresas</title>
</head>
<body>
<h1>Empresas</h1>
<form action="agregar-empresa" method="post">
    <label>
        Razon Social
        <input type="text" name="razonsocial">
    </label><br/>

    <label>
        Nombre Publico
        <input type="text" name="nombrepublico">
    </label><br/>

    <label>
        Direccion
        <input type="text" name="direccion">
    </label><br/>

    <button type="submit">Agregar</button>
</form>

<form action="listar-empresas" method="get">
    <label for="filtro">Listar Empresas: </label>
    <select id="filtro" name="seleccionar">
        <option name="todosloscampos">Todos los campos</option>
        <option name="todo">Todo</option>
        <option name="nro">Nro</option>
        <option name="razonsocial">Razon Social</option>
        <option name="nombrepublico">Nombre Publico</option>
        <option name="direccion">Direccion</option>
        <option name="fecha">Fecha</option>
    </select>
    <input type="text" name="filtro" placeholder="buscar..."/>
    <button type="submit">filtrar</button>
</form>

<form action="agregar-empresa-mdb" method="post">
    <label for="msj">Agregar Empresa mediante mensaje jms: </label>
    <input id="msj" type="text" name="mensaje" placeholder="escriba los atributos de la empresa separados por |..."/>
    <button type="submit">mandar mensaje</button>
</form>
</body>
</html>