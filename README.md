# MANUAL RECAUDAPP v1.0 #

**RECAUDAPP** es una aplicación de gestión de bares y recaudación que trabajaba bajo el Sistema Operativo Android , en esta primera versión.

La aplicación además de cargar una lista de locales a través de una base de datos SQL , tiene la funcionalidad de generar una factura con los datos obtenidos del proceso de recaudación.

##LOGIN DE USUARIO ##

Al iniciar la aplicación se muestra la Actividad de Login , en la que hay que introducir un nombre de Usuario y contraseña

En la versión inicial se ha creado un usuario y contraseña de prueba:

- **Usuario** -> (`Wence`) 
- **Contraseña** -> (`123`)

<img src=https://raw.githubusercontent.com/WenceCB/Recaudap/master/Capturas/login.png?raw=true width=270 height=480/>

## MENÚ PRINCIPAL ##

En el menú principal podemos encontrar los botones que dan acceso a diferentes funciones del programa

- **Bares** -> Contiene Listado de Bares para seleccionar con cual se quiere trabajar
- **Recaudar Rápido** -> Permite una recaudación introduciendo todos los datos manualmente
- **Opciones Avanzadas** -> Permite actualizar la base de datos en local *(`v2.0`)

<img src=https://raw.githubusercontent.com/WenceCB/Recaudap/master/Capturas/Mprincipal.png?raw=true width=270 height=480/>

## BARES ##

Muestra un listado de Bares disponibles , pulsando sobre el que se desea , se abre una nueva actividad con los siguientes campos

<img src=https://raw.githubusercontent.com/WenceCB/Recaudap/master/Capturas/Bar.png?raw=true width=270 height=480/>

- Nombre del bar
- Dueño del Bar
- Dirección del Bar
- Máquina de la que dispone
- Botón Recaudar
- Mapa con posición

Pulsando sobre el Marcador en el mapa , se puede proceder al guiado desde la posición actual hasta el destino(`Marcador`)
Pulsando en el botón Recaudar , se abre la actividad Recaudar

<img src=https://raw.githubusercontent.com/WenceCB/Recaudap/master/Capturas/MapsActivity.png?raw=true width=270 height=480/>

##RECAUDAR##

En esta actividad los Contadores anteriores ya están introducidos y el usuario solo tendría que completar con los Contadores Actuales y las Tasas

<img src=https://raw.githubusercontent.com/WenceCB/Recaudap/master/Capturas/Recaudar.png?raw=true width=270 height=480/>

Cuenta con dos botones

- Recaudar : Genera las operaciones necesarias para calcular la parte correspondiente al Total , Local y Empresa
- Generación PDF : Permite confeccionar un PDF en forma de factura que se adjunta automáticamente al correo electrónico con los datos de la recaudación.

<img src=https://raw.githubusercontent.com/WenceCB/Recaudap/master/Capturas/Informe.png?raw=true width=270 height=480/>

##RECAUDAR RÁPIDO##

Al igual que el anterior genera las operaciones para recaudar pero insertando todos los datos de manera manual

<img src=https://raw.githubusercontent.com/WenceCB/Recaudap/master/Capturas/RecaudarRapido.png?raw=true width=270 height=480/>

## OPCIONES AVANZADAS ##

Cuenta con un login para poder ejecutar un script que interactúe con la base de datos ( Actualmente no está disponible porque se pretende trabajar con una base de datos en servidor para limitar al cliente )

- **Contraseña** -> (`123`)

<img src=https://raw.githubusercontent.com/WenceCB/Recaudap/master/Capturas/OpcionesAvanzadas.png?raw=true width=270 height=480/>
