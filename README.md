# T6-ArbolBinario-JosephRomo
---

## 1. Descripción del problema

La Universidad requiere un programa que represente la organización jerárquica de sus dependencias académicas mediante un **árbol binario**. Cada nodo almacena el nombre de una dependencia y tiene como máximo dos hijos (izquierdo y derecho).

La aplicación permite **construir, visualizar y analizar** la estructura: número de nodos, número de hojas, altura, grado de un nodo y si un nodo es hoja.

## 2. Estructura del árbol

```
             UNIVERSIDAD
             /         \
       FISEI             DIDE
       /   \             /   \
   SOFTWARE  TI     INVEST.  VINCULACIÓN
    /   \
  NIVEL1 NIVEL3
```

| Propiedad | Valor |
|-----------|-------|
| Nodos     | 9     |
| Hojas     | 5 (TI, INVEST., VINCULACIÓN, NIVEL1, NIVEL3) |
| Altura    | 3 aristas (UNIVERSIDAD → FISEI → SOFTWARE → NIVEL1) |

## 3. Organización del proyecto

```
src/
 ├── modelo/
 │    └── Nodo.java
 ├── negocio/
 │    └── ArbolBinario.java
 └── app/
      └── Main.java
```

- **`modelo/Nodo.java`**: clase genérica `Nodo<T>` con `dato`, `izquierdo`, `derecho`, `esHoja()`, `grado()`, getters y setters.
- **`negocio/ArbolBinario.java`**: lógica del árbol (`crearRaiz`, `agregarIzquierdo`, `agregarDerecho`, `contarNodos`, `contarHojas`, `altura`, `esVacio`, `mostrarArbol`).
- **`app/Main.java`**: menú interactivo con `Scanner`.

### Restricciones cumplidas

- No se usan `TreeSet`, `TreeMap`, `PriorityQueue`, `NavigableSet` ni `NavigableMap`.
- `contarNodos()`, `contarHojas()` y `altura()` están implementados con **recursividad**.
- Convención de altura: árbol vacío = **-1**, árbol de un solo nodo = **0**.

## 4. Instrucciones de ejecución

### Requisitos
- JDK 17 o superior
- Visual Studio Code con *Extension Pack for Java* (opcional)

### Desde la terminal

```bash
# 1. Clonar el repositorio
git clone https://github.com/[usuario]/T6-ArbolBinario-[ApellidoNombre].git
cd T6-ArbolBinario-[ApellidoNombre]

# 2. Compilar
javac -d out src/modelo/Nodo.java src/negocio/ArbolBinario.java src/app/Main.java

# 3. Ejecutar
java -cp out app.Main
```

### Desde Visual Studio Code
1. Abrir la carpeta del proyecto.
2. Abrir `src/app/Main.java`.
3. Pulsar **Run** sobre el método `main`.

## 5. Menú del programa

```
1. Crear el árbol inicial
2. Visualizar el árbol
3. Consultar el número de nodos
4. Consultar el número de hojas
5. Consultar la altura
6. Consultar el grado de un nodo
7. Verificar si un nodo es hoja
8. Salir
```

## 6. Casos de prueba y resultados

| # | Caso de prueba | Resultado esperado | Resultado obtenido |
|---|----------------|--------------------|--------------------|
| 1 | Árbol vacío | 0 nodos, altura -1 | [✔ / captura] |
| 2 | Árbol con un solo nodo | 1 nodo, 1 hoja, altura 0 | [✔ / captura] |
| 3 | Árbol inicial | 9 nodos, 5 hojas, altura 3 | [✔ / captura] |
| 4 | Insertar un hijo en una posición ocupada | Se rechaza la operación con mensaje de error | [✔ / captura] |
| 5 | Grado de FISEI | 2 | [✔ / captura] |
| 6 | Grado de SOFTWARE | 2 | [✔ / captura] |

## 7. Capturas de pantalla

### Ejecución del menú
![Menú principal](capturas/menu.png)

### Visualización del árbol
![Árbol](capturas/arbol.png)

### Casos de prueba
![Árbol vacío](capturas/caso1_vacio.png)
![Un solo nodo](capturas/caso2_un_nodo.png)
![Árbol inicial](capturas/caso3_inicial.png)
![Posición ocupada](capturas/caso4_ocupada.png)
![Grado de FISEI y SOFTWARE](capturas/caso5_grados.png)

## 8. Historial de commits

El desarrollo se realizó de forma incremental, con commits como:

1. `[Agrega clase Nodo<T> con esHoja() y grado()]`
2. `[Implementa ArbolBinario con inserción y operaciones recursivas]`
3. `[Agrega menú interactivo, casos de prueba y README]`

## 9. Autor

**Joseph Romo**
Ingeniería de Software – Tercer nivel
Universidad Técnica de Ambato – FISEI
