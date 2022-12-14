# MEMORIAS ESCRITAS TRABAJO POO GRUPO 2 EQUIPO 7

## 1.	Descripción general de la solución.

La siguiente aplicación denominada como Sistema de consulta de estados académicos estudiantiles nace de la verificación y observación de distintos tipos de sistemas académicos en donde no se tenía claridad acerca de las becas (requisitos de acceso e información concreta de las mismas) ofrecidas dentro de las universidades tanto para estudiantes como para profesores, de igual forma, notamos una falta de claridad y organización para las líneas de énfasis que pueden ofrecerse en diferentes facultades dentro de la universidad, esta aplicación también nos proporcionara información básica de los estudiantes y profesores, subsidios estudiantiles y más. 
El diseño consiste en 4 paquetes principales los cuales son:
•	baseDatos: Este contiene el serializador 
•	gestorAplicacion.gestion: Contiene lo siguiente, “Asignatura.java”, “Beca.java”, “Facultad.java” y “Subsidio.java” y también un par de enumerados que se usaran a lo largo de todas estas clases los cuales son “CalidadEstudiante.java” y “LineasDeEnfasis.java”.
•	gestorAplicacion.personas: Contiene las clases restantes en la aplicación las cuales son “Persona.java” clase abstracta ya que define características y métodos generales para, “Estudiante.java” y “Profesor.java”.
•	uiMain: Este paquete es donde está contenida nuestra clase principal “Administrador.java”.
Para la implementación de nuestra app utilizamos diferentes tipos de métodos en todas y cada una de los paquetes mencionados anteriormente se dividen en siete acciones visualizadas por consola al ejecutar la aplicación las acciones son. Recomendar asignatura, calidad estudiante, información becas, información subsidios estudiantiles, consultar posición de un estudiante, consultar vinculados, salir del sistema. Gracias estas se pueden realizar interacciones entre todas y cada una de nuestras clases y objetos obteniendo así diferentes funcionalidades al interior de la aplicación y que dependen de la información que quiera ser obtenida por el administrador (secretaria) que use la aplicación.


## 2.	Descripción de la implementación.
La variable “espaciado” la cual se encuentra en el paquete uiMain en la clase “Administrador.java” es una constante. Podemos encontrar la clase abstracta “Persona.java” en el paquete “gestionAplicacion.personas” y es utilizado para definir atributos en común entre estudiante y profesor, de igual forma podemos visualizar 3 metodos abstractos en la misma. En la clase “Estudiante.java” y “Profesor.java” podemos notar la implementación de la interfaz comparable que se utiliza para comparar promedios de profesores y estudiantes para así poder organizar a los mismos dentro de la aplicación. La herencia podemos verla muy claramente en las clases “Estudiante.java” y “Profesor.java” que heredan atributos y métodos de la clase abstracta “Persona.java” aunque todas las clases tiene atributos y métodos de clase pongamos, por ejemplo, en el paquete “gestorAplicacion.gestion” la clase “Beca.java” tenemos el atributo “listaBecas” el cual no es más que un atributo de clase que define un array para las becas que se tienen en la aplicación desarrollada, de igual forma podemos observar los métodos getListaBecas y setListaBecas que son métodos de clase y nos permiten modificar y obtener los valores dentro de esta lista. El encapsulamiento está presente en toda la aplicación y como se vio en clase se utiliza precisamente para mantener el más alto grado de privacidad sin perder funcionalidad en el software y puede encontrarse en cualquiera de las clases de la aplicación. La sobrecarga de métodos podemos encontrarla en el “gestionAplicacion.personas” en la clase “Profesor.java” para el método “calcularPromedio” el cual era un método de la clase abstracta (y debía usarse en sus hijos) se sobrecarga el método para especificar la estructura de este método para la clase “Profesor.java”. Viendo de cerca el paquete “gestionAplicacion.gestion” en la clase “Facultad.java” y “Beca.java” sus constructores tanto con sus atributos como el constructor por defecto en ambos demarcando de manera muy específica la estructura de la entrada de los datos para los objetos de estas clases. En la clase “Profesor.java” en el método compereTo podemos notar el uso del this para realizar una comparación entre diferentes profesores y de esta forma lograr organizar en un orden especifico a los mismos. De igual forma podemos ver lo mismo en la clase “Estudiante.java” con el método compereTo para desambiguar #falta poner casos de uso del this() #. Finalmente podemos observar el uso de dos enumeraciones las cuales se encuentran en el paquete “gestionAplicacion.gestion” con el nombre de “CalidadEstudiante.java” y “LineasEnfasis.java” y estas nos sirven dentro del programa en ciertas funcionalidades con la obtención de valores predefinidos en la misma.

## 3.	Funcionalidades
•	Recomendación de asignaturas: Esta nos permite relacionar la clase “Asignatura.java” con las clases “Estudiante.java” y “Profesor.java”, ya que lo que hace es filtrar una recomendación asignaturas por línea de énfasis y básicas opciones 1 y 2 respectivamente, cuando realizamos la búsqueda por línea de énfasis tenemos dos opciones la cual es recomendar asignaturas a un único estudiante para lo que se necesitara la identificación del mismo y recomendación de asignaturas para todos los estudiantes que nos brindara una lista con todos los estudiantes y una recomendación de asignaturas que dependen de la línea de énfasis de los mismos.


Podemos Resumir el funcionamiento con el siguiente modelo de secuencia.


![DIAGRAMA_SECUENCIA_1](https://github.com/POO2022-02-UNALMED/practica-g2-equipo7/blob/master/lib/secuencia1.png)

## Podemos observar una vista en la consola de este resultado.

Podemos observar una vista en la consola de este resultado.

![CAPTURA_1](https://github.com/POO2022-02-UNALMED/practica-g2-equipo7/blob/master/lib/ra_le_eu.png)

## También podemos observar como relacionamos esto con la clase profesor ya que seleccionar la opción uno podemos observar los nombres de los profesores encargados de dictar esa materia.

![CAPTURA_2](https://github.com/POO2022-02-UNALMED/practica-g2-equipo7/blob/master/lib/profesores.png)

## Por otro lado, al realizarlo para todos los estudiantes obtenemos lo siguiente.

![CAPTURA_3](https://github.com/POO2022-02-UNALMED/practica-g2-equipo7/blob/master/lib/todos_le.png)

## Podemos observar algo similar para la opción de asignaturas básicas. Recomendación por estudiante.

![CAPTURA_4](https://github.com/POO2022-02-UNALMED/practica-g2-equipo7/blob/master/lib/basica_es.png)

## También podemos ver los profesores de estas asignaturas.

![CAPTURA_5](https://github.com/POO2022-02-UNALMED/practica-g2-equipo7/blob/master/lib/profesores_bas.png)

## También podemos visualizar todas las asignaturas basicas recomendadas para los estudiantes

![CAPTURA_6](https://github.com/POO2022-02-UNALMED/practica-g2-equipo7/blob/master/lib/basicas_te.png)

•	Calidad de estudiante: Esta funcionalidad nos muestra la calidad de estudiante, y puede filtrarse individualmente para lo que se necesitara la identificación del mismo, de igual forma, también puede hacerse el filtrado por facultad y línea de énfasis. Relacionando a las clases “Facultad.java”, “Estudiante.java” y el enumerado de línea de énfasis.
Podemos observar el funcionamiento con el diagrama de secuencias siguiente:

![SECUENCIA_2](https://github.com/POO2022-02-UNALMED/practica-g2-equipo7/blob/master/lib/secuencia2.png) 

![caputura6.1](https://github.com/POO2022-02-UNALMED/practica-g2-equipo7/blob/master/lib/calidad_estudia.png) 

Consulta por documento.

 ![CAPTURA_7](https://github.com/POO2022-02-UNALMED/practica-g2-equipo7/blob/master/lib/ce_individual.png)

Consulta por facultad me filtra por las facultades en la base de datos y finalmente me presenta una tabla con los valores requeridos.

![CAPTURA_6](https://github.com/POO2022-02-UNALMED/practica-g2-equipo7/blob/master/lib/ce_facultad.png)

Consulta por línea de énfasis, filtrando por línea de énfasis y mostrando una tabla al final.

![CAPTURA_6](https://github.com/POO2022-02-UNALMED/practica-g2-equipo7/blob/master/lib/ce_linea_enfasis.png)
