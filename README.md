# AE-3: Criptografía

![1](https://github.com/LibertadDiana/AE3_Criptografia/assets/124418682/13ff20bc-77f1-416b-91a5-d745ddac3049)


# Programación de Servicios y Procesos 
 
# Grupo 2 : 
Cristian David Quiceno Laverde 
Gabriela Prieto Herrera 
Libertad Gamarra La Rosa 
 
# Repositorio Git:
https://github.com/LibertadDiana/AE3_Criptografia.git 

# Metodología. 
Teniendo en cuenta la importancia de los conceptos de esta actividad, hemos decidido que para esta actividad que cada miembro del grupo realice el proyecto de criptografía de la manera que crea más correcta, creando así 3 proyectos con diferentes metodologías. 

Para realizar los proyectos se han tenido en cuenta las clases y los ejemplos mostrados en las mismas, llevando estas especificaciones a los diferentes proyectos planteados por el grupo.
Basando la composición en los métodos de encriptación y desencriptación, más la realización de resúmenes hash, de los contenidos que se especifican en la actividad.

Una vez terminados los proyectos, en reunión grupal se han expuesto los diferentes casos y sus conclusiones. Se han comprobado los diferentes códigos con sus diferencias y se han depurado los detalles del proyecto elegido por el grupo para presentarlo como el proyecto principal.

# Enunciado. 

# Requerimiento 1
Se pide hacer una aplicación que encripte frases introducidas por el usuario de manera simétrica.
La aplicación mostrará el siguiente menú
 Salir del programa
 Encriptar frase
 Desencriptar frase
Con la opción 1 el programa le pedirá al usuario una frase, la encriptará y la guardará en memoria.
Con la opción 2 el programa mostrará la frase desencriptándola.
Solo se guardará una frase al mismo tiempo. Se utilizará un método de encriptación simétrico.
# Requerimiento 2
Se pide agregar seguridad a la aplicación para poder entrar. El programa arrancará con 3 objetos usuario que tendrá su nombre de usuario y su contraseña “hasheada”. Los objetos permanecerán en memoria durante todo el programa.
Antes de mostrar el menú, el programa pedirá que se introduzca el nombre del usuario y su contraseña (sin “hashear”), en caso de que sea correcto, se mostrará el menú y un mensaje de bienvenida al usuario con su nombre, en caso contrario se le volverá a pedir hasta un máximo de tres veces. Si en tres intentos no se ha conseguido introducir bien los datos de ningún usuario registrado, la aplicación se detendrá.
 

# Contenido

# Requerimiento 1: Encriptar y Desencriptar Frases
 Menú de Opciones:
 Salir del programa.
 Encriptar una frase ingresada por el usuario.
 Desencriptar la última frase encriptada.

![1 1](https://github.com/LibertadDiana/AE3_Criptografia/assets/124418682/88758a67-d72f-4b3a-b2eb-3e45c4cbcc13)

Encriptar Frase:
 El programa solicita al usuario una frase y la encripta.
 La frase encriptada se almacena en memoria.
 
![1 2](https://github.com/LibertadDiana/AE3_Criptografia/assets/124418682/d680e997-d87b-40fd-abb7-37df06e79f59)

Desencriptar Frase:
 El programa muestra la última frase encriptada y la desencripta.
 
![1 3](https://github.com/LibertadDiana/AE3_Criptografia/assets/124418682/4d0711e4-24dd-463f-aa7f-20a9120bbacd)


# Requerimiento 2: Seguridad de Acceso
 Autenticación de Usuarios:
 Al iniciar el programa, se solicita al usuario ingresar su nombre de usuario y contraseña. El sistema  
 verifica estos datos con una lista predefinida de usuarios y contraseñas.
 
![1](https://github.com/LibertadDiana/AE3_Criptografia/assets/124418682/9a27dbca-d290-4ac4-afe3-18dd5988c996)

 Si los datos coinciden, se da acceso al programa y se muestra el menú.
 
![2](https://github.com/LibertadDiana/AE3_Criptografia/assets/124418682/04f72396-7816-4052-b9b2-5a66f37e7d6e)

 Si los datos son incorrectos, se permite un máximo de tres intentos para ingresar correctamente.
 
![4](https://github.com/LibertadDiana/AE3_Criptografia/assets/124418682/54dab6f7-48fe-46ca-a9b0-de3076717401)

# Conclusión. 

La tarea requería la creación de una aplicación de consola en Java que ofreciera encriptación y desencriptación de frases utilizando un método simétrico junto con un sistema de autenticación de usuarios.

Realizar esta actividad nos proporcionó la oportunidad de trabajar con entrada de usuario, lógica de encriptación y lógica de control de flujo para la autenticación. Además de seguridad y enfoques para manejar datos sensibles como contraseñas.

