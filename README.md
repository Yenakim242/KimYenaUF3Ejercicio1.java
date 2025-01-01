# 1 Ejercicio Guess the movie

Desarrolla un juego en Java donde se le propondrá al usuario adivinar (알아 맞히다) el título de una película dando las letras que lo forman. Es como un ahorcado.

Tu aplicación seleccionará un título de forma aleatoria de la lista que haya en un fichero de texto (lo tienes que crear tú y guardarlo en el workspace del proyecto), y mostrará por consola de cuantas letras está formado el título usando *.  Si el título contiene otros caracteres que no sean letras (espacios en blanco, guiones, etc) se mostrarán, solamente usaremos * para las letras.
El fichero de texto debe contener el título de al menos 10 películas distintas. 

En la siguiente imagen se muestra un ejemplo:
🎯🎯🎯 Guess the Movie 🎯🎯🎯
The movie title has 13 characters (including and punctuation)
You are guessing: ****** ******
Remaining turns: 10
Points: 0
Choose an option:
[1] Guess a letter
[2] Guess the movie's title
[3] Exit

El jugador tendrá máximo 10 intentos para adivinar el título. En cada intento le permitirás o bien introducir una letra o bien introducir directamente el título o salir de la aplicación. 

[1] Guess a letter: el usuario decide adivinar una letra
- Si introduce cualquier carácter que no sea una letra [a-z], la aplicación solicita de nuevo que introduzca una letra
- Si la letra se encuentra en el título, se mostrará en la posición o posiciones adecuadas y el usuario ganará 10 puntos
- Si la letra no se encuentra en el título, se mostrará en una lista de letras erróneas y el usuario perderá 10 puntos. 
- El usuario tendrá un máximo de 10 intentos, si los consume sin completar el total de letras del título, perderá el juego.  Si dentro de esos 10 intentos adivina todas las letras, ganará el juego.
- No distingas (구별하다) entre mayúsculas y minúsculas. Por ejemplo, si el título de la película es “star wars” y el jugador introduce la ‘S’ se dará por correcta mostrándola en su posición. Para conseguir esto, es una buena práctica decidir si trabajar con todo en mayúsculas o con todo en minúsculas…¡tú decides!. 
- El usuario puede equivocarse y repetir alguna letra (bien se encuentre en el título o no). ¿Qué hacemos si sucede esto? No lo tengas en cuenta, es decir no le restes intentos ni puntos, sino que le avises de que esa letra ya la ha dicho, y hagas que la aplicación vuelva a solicitar una nueva letra.

[2] Guess the movie’s title:  el usuario decide introducir el título de la película: 
- Si coincide con el título de la película (no distingas entre mayúsculas y minúsculas), el juego finaliza , el usuario gana el juego y acumula 20 puntos. 
- Si no coincide con el título de la película (no distingas entre mayúsculas y minúsculas), el juego finaliza, el usuario pierde el juego y se le restan 20 puntos. 

[3] Exit: el usuario decide salir del juego
- El juego finaliza y el usuario pierde el juego. 

Al finalizar el juego, mostraremos al usuario cuál era el título de la película y su puntuación definitiva, así como si ha ganado o ha perdido.


# Ranking de puntuaciones
En el workspace del proyecto la aplicación dispondrá de un fichero binario donde se guardarán hasta las 5 mejores puntuaciones de las distintas partidas del juego ordenadas de mayor a menor. Junto a la puntuación, se guardará el nickname que escoja el jugador. 

· Al finalizar el juego:
- Si la puntuación del jugador entra dentro de este ranking, le pediremos un nickname (por ejemplo: john) y registramos la info en el fichero en el orden correspondiente. Ten en cuenta que no se permitirán nicknames repetidos. Si el usuario introduce uno que ya esté en el fichero, se le volverá a pedir. 
- Si la puntuación del jugador está fuera de este ranking, se lo indicaremos por consola. 

En ambos casos mostraremos el ranking por consola y terminaremos la aplicación

# Juego de Adivinar Película

Este es un juego interactivo de consola donde el jugador tiene que adivinar el título de una película seleccionada aleatoriamente. El jugador puede adivinar letras o intentar adivinar el título completo.

## Funcionalidades

- **Selección aleatoria de película**: El juego selecciona una película de una lista predefinida de películas.
- **Adivinar una letra**: El jugador puede adivinar una letra del título. Si la letra está en el título, se revela en su lugar correspondiente.
- **Adivinar el título completo**: El jugador puede intentar adivinar el título completo de la película en cualquier momento.
- **Validación de entradas**: Se gestionan entradas inválidas y letras repetidas.
- **Ranking**: Al final del juego, se guarda la puntuación del jugador y se muestra el ranking con los mejores puntajes.

## Requisitos

- Java 11 o superior
- IntelliJ IDEA o cualquier IDE compatible con Java

### Explicación de cada sección:
1. **Nombre y descripción del juego**: Un breve resumen de lo que hace tu juego.
2. **Funcionalidades**: Explicas las características principales del juego.
3. **Requisitos**: Especificas las dependencias necesarias para ejecutar el proyecto (Java, IDE).
4. **Cómo ejecutar el proyecto**: Instrucciones para clonar el repositorio y ejecutar el proyecto.
5. **Estructura del proyecto**: Describe los archivos y su propósito en el juego.
6. **Funcionalidades del juego**: Detalles sobre cómo funciona el juego, las opciones del menú, y cómo se juega.
7. **Cómo contribuir**: Si otros desarrolladores quieren ayudar en el proyecto.
8. **Licencia**: Si decides añadir una licencia para el uso del código.

Puedes adaptar las partes del README a tu proyecto específico, pero esto te da una buena base. ¡Espero que te sirva!



