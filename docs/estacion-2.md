# Estación 2: clases del dominio y primera ejecución

## Objetivo y alcance

Crear las clases del dominio y ejecutar la primera versión de la aplicación utilizando exactamente los fragmentos proporcionados para esta estación. Se trabaja en `estacion-02`, sin Maven, Gradle ni dependencias externas. No se implementan las estaciones 3 a 8.

La documentación de la estación 1 se conserva intacta. No se incorporaron documentos originales de la asignatura ni se realizaron commits, merges o push.

## Clases y archivos creados

```text
docs/
    estacion-2.md
src/
    biblioteca/
        Estudiante.java
        Sala.java
        Main.java
```

Todos los archivos Java declaran `package biblioteca;`. La carpeta `src` se creó directamente en la raíz del repositorio, sin agregar otro proyecto dentro de este.

| Archivo | Responsabilidad en esta estación |
| --- | --- |
| `src/biblioteca/Estudiante.java` | Representa el identificador y el nombre del estudiante. Su constructor rechaza valores nulos o en blanco y ofrece métodos de consulta. |
| `src/biblioteca/Sala.java` | Representa el código, nombre y capacidad de una sala. Su constructor valida esos datos; sus getters permiten consultarlos y `toString()` produce la representación que se imprime. |
| `src/biblioteca/Main.java` | Contiene el punto de entrada, crea la lista fija de las cuatro salas e imprime sus datos. |
| `docs/estacion-2.md` | Documenta la implementación, validaciones, punto de control y evidencias. |

El contenido de las tres clases se extrajo directamente de los fragmentos del adjunto y se comparó con ellos. No se añadieron atributos, métodos, constructores ni validaciones.

## Entorno y comandos ejecutados

Se ejecutaron desde la raíz del repositorio:

```powershell
git status --short --branch
git branch --show-current
git log -1 --oneline
java -version
javac -version
```

Resultados: rama `estacion-02`, sin cambios pendientes al comenzar; último commit existente `f98fe9a`, correspondiente a la documentación de la estación 1. Tanto `java` como `javac` informaron la versión **24.0.1**, superior al mínimo requerido.

Se creó una carpeta temporal externa con un nombre único y se usaron los siguientes comandos de compilación y ejecución. `$outputDir` contenía la ruta de esa carpeta:

```powershell
javac -encoding UTF-8 --release 17 -d $outputDir src/biblioteca/Estudiante.java src/biblioteca/Sala.java src/biblioteca/Main.java
java -cp $outputDir biblioteca.Main
```

La carpeta utilizada fue `%TEMP%\biblioteca-estacion-02-65e0607b213846e4804bb406dcf2b5a9`. No se generaron archivos `.class` dentro del repositorio. `--release 17` permite usar el JDK instalado y generar clases compatibles con Java 17.

Para repetir la comprobación en una terminal PowerShell nueva, desde la raíz del repositorio:

```powershell
java -version
javac -version
$outputDir = Join-Path ([System.IO.Path]::GetTempPath()) ('biblioteca-estacion-02-' + [guid]::NewGuid().ToString('N'))
New-Item -ItemType Directory -Path $outputDir | Out-Null
javac -encoding UTF-8 --release 17 -d $outputDir src/biblioteca/Estudiante.java src/biblioteca/Sala.java src/biblioteca/Main.java
if ($LASTEXITCODE -ne 0) { throw 'Fallo de compilación' }
Write-Output 'Compilación correcta'
java -cp $outputDir biblioteca.Main
if ($LASTEXITCODE -ne 0) { throw 'Fallo de ejecución' }
```

## Resultado esperado y resultado real

La salida esperada es un encabezado seguido de exactamente cuatro salas, en orden A, B, C y D, con capacidades 2, 4, 6 y 8.

La compilación terminó con código de salida **0**, sin errores ni mensajes del compilador. La ejecución también terminó con código **0** y produjo esta salida real:

```text
Salas de la biblioteca:
Sala A (A, capacidad: 2)
Sala B (B, capacidad: 4)
Sala C (C, capacidad: 6)
Sala D (D, capacidad: 8)
```

Se comparó automáticamente la salida completa con las cinco líneas esperadas y coincidieron exactamente.

### Validaciones realizadas

- Verificación de la rama antes de implementar: `estacion-02`.
- Comparación de los tres archivos con los fragmentos proporcionados, conservando su código.
- Compilación de las tres clases y ejecución de `biblioteca.Main`.
- Comparación exacta de la salida, incluidas las cuatro capacidades.
- Inspección automatizada del código: los tres archivos pertenecen a `biblioteca`; Estudiante tiene dos atributos `private final` y Sala tiene tres; no hay setters.
- Lectura de la cabecera de `Main.class`: versión mayor 61, correspondiente a Java 17.
- Comparación SHA-256 de ambos archivos de la estación 1 antes y después del trabajo y revisión con `git diff`: permanecen sin cambios.

No se agregaron pruebas ni modificaciones a los fragmentos originales. La ejecución de Main no instancia Estudiante, por lo que no se presenta como una prueba de sus validaciones de constructor. Las capturas de pantalla quedan a cargo del estudiante.

### Incidencias encontradas y resueltas

El primer intento de ejecución incluyó un argumento `-Dfile.encoding=UTF-8` que PowerShell interpretó incorrectamente. Se repitió la ejecución con `java -cp $outputDir biblioteca.Main` y funcionó sin modificar ninguna clase.

La utilidad opcional `javap` no estaba disponible en PATH. La verificación de atributos se realizó sobre el código fuente, y la versión del bytecode se comprobó leyendo la cabecera del archivo compilado. No se encontraron errores de compilación ni fue necesario corregir los fragmentos.

## Punto de control: ¿por qué los atributos son privados y finales?

Los atributos son **privados** para aplicar encapsulamiento: el código externo no puede acceder directamente a los campos ni asignarles valores. Debe utilizar los métodos públicos disponibles. En estas clases, los getters permiten consultar los datos sin exponer una operación para modificarlos.

Son **finales** para que cada campo se asigne durante la construcción y no pueda reasignarse después. Así, una sala mantiene el mismo código, nombre y capacidad, y un estudiante conserva el identificador y nombre con los que fue creado.

La combinación de campos privados y finales, junto con la **ausencia de setters**, protege los datos y da estabilidad a los objetos después de validar sus valores en el constructor. Una sala no puede pasar a tener una capacidad inválida mediante una asignación posterior.

`final` por sí solo no vuelve inmutable cualquier objeto referenciado: impide reasignar el campo. En este caso, los campos son de tipo `String`, que es inmutable, o `int`, que es un tipo primitivo. Por ello, los datos representados por estas clases permanecen estables a través de la API proporcionada.

## Capturas reales para la sustentación

| Nombre recomendado | Contenido que debe verse | Momento exacto | Qué demuestra |
| --- | --- | --- | --- |
| `01-estructura-estacion-02.png` | Explorador del editor con `src/biblioteca/`, sus tres archivos y `docs/estacion-2.md`; también la documentación de la estación 1. | Después de guardar los cuatro archivos, antes del commit manual. | Estructura correcta dentro del repositorio y alcance de la entrega. |
| `02-codigo-estudiante.png` | `Estudiante.java` completo, incluido el paquete, campos, constructor y getters. | Después de abrir el archivo guardado. | Encapsulamiento, atributos finales y validación original. |
| `03-codigo-sala.png` | `Sala.java` completo, incluido `toString()`. Ampliar la ventana o usar dos capturas si no cabe legible. | Después de abrir el archivo guardado. | Representación de salas, capacidades y código original. |
| `04-codigo-main.png` | `Main.java` completo con la lista de cuatro salas y las instrucciones de impresión. | Después de abrir el archivo guardado. | Punto de entrada y capacidades 2, 4, 6 y 8. |
| `05-compilacion-y-ejecucion.png` | Terminal PowerShell con versiones de Java, comando de compilación, mensaje de compilación correcta y ejecución con las cinco líneas de salida. Usar dos capturas si es necesario. | Inmediatamente después de repetir el bloque de comprobación anterior y comprobar que termina sin error. | Compilación y ejecución reales con JDK 17 o superior. |
| `06-rama-y-commit.png` | Terminal con `git branch --show-current`, `git log -1 --oneline` y `git status --short --branch`. | Después de realizar tú mismo el commit de la estación 2. | Rama `estacion-02`, identificador y mensaje del nuevo commit, y estado real del repositorio. |

No se generaron imágenes ni se fabricaron resultados. El commit de la estación 2 y su eventual publicación no forman parte de las acciones ejecutadas aquí.
