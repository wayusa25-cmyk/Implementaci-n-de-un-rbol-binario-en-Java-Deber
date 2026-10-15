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

### Desde la Git Desktop

```bash
# 1. Clonar el repositorio
Copiar el HTTP desde el repositorio y clonarlo desde la opcion de URL

# 2. Compilar
Abrir en Visual Studio
# 3. Ejecutar
Ejecutar el main para correr el codigo
```

### Desde Visual Studio Code
1. Abrir la carpeta del proyecto.
2. Abrir `src/app/Main.java`.
3. Pulsar **Run** sobre el método `main`.

## 5. Menú del programa

```
========== ÁRBOL BINARIO - DEPARTAMENTOS ==========
1. Crear el árbol (ingresar nodos manualmente)
2. Visualizar el árbol
3. Consultar el número de nodos
4. Consultar el número de hojas
5. Consultar la altura
6. Consultar el grado de un nodo
7. Verificar si un nodo es hoja
8. Crear árbol predeterminado
9. Salir
===================================================
Seleccione una opción:
```

## 6. Casos de prueba y resultados

| # | Caso de prueba | Resultado esperado | Resultado obtenido |
|---|----------------|--------------------|--------------------|
| 1 | Árbol vacío | 0 nodos, altura -1 | <img width="473" height="307" alt="image" src="https://github.com/user-attachments/assets/95f4f594-4da9-478d-b6ea-cd39656721c6" />
 |
| 2 | Árbol con un solo nodo | 1 nodo, 1 hoja, altura 0 | <img width="475" height="306" alt="image" src="https://github.com/user-attachments/assets/5e908906-3117-4eb0-aa97-640bf8971677" />
 |
| 3 | Árbol inicial | 9 nodos, 5 hojas, altura 3 | <img width="273" height="236" alt="image" src="https://github.com/user-attachments/assets/2ab3b357-3691-49fd-a0b9-bf424e1fdc68" />
 |
| 4 | Insertar un hijo en una posición ocupada | Se rechaza la operación con mensaje de error | [✔ / captura] |
| 5 | Grado de FISEI | 2 | <img width="258" height="100" alt="image" src="https://github.com/user-attachments/assets/17c7dc6e-bff4-4196-9e2e-895ff45e94f7" />
 |
| 6 | Grado de SOFTWARE | 2 |  |

## 7. Capturas de pantalla

### Ejecución del menú
![Menú principal](<img width="481" height="242" alt="image" src="https://github.com/user-attachments/assets/d3bcff89-e48d-4dcf-a8b8-38511babefdf" />
)

### Visualización del árbol
![Árbol](<img width="275" height="231" alt="image" src="https://github.com/user-attachments/assets/387b2342-6bb8-4dfb-b3d0-255d0e34e380" />
)

### Casos de prueba
![Árbol vacío](<img width="271" height="92" alt="image" src="https://github.com/user-attachments/assets/96454bab-4f16-4057-809d-b8bf91c2cc83" />
)
![Un solo nodo](<img width="245" height="76" alt="image" src="https://github.com/user-attachments/assets/5106b8ce-9c32-4e1d-98c7-432d1a24c303" />
)
![Árbol inicial](<img width="273" height="236" alt="image" src="https://github.com/user-attachments/assets/2ab3b357-3691-49fd-a0b9-bf424e1fdc68" />)
![Posición ocupada](<img width="797" height="247" alt="image" src="https://github.com/user-attachments/assets/8a24530a-3074-4953-b217-42db5dfc73f8" />
)
![Grado de FISEI y SOFTWARE](<img width="235" height="91" alt="image" src="https://github.com/user-attachments/assets/426ee85c-f2fc-4817-abb5-a492344f42b6" />
  <img width="236" height="95" alt="image" src="https://github.com/user-attachments/assets/67f6ace1-93d3-4c31-924a-563bf849d82c" />
)


## 8. Autor

**Joseph Romo**
Ingeniería de Software – Tercer nivel
Universidad Técnica de Ambato – FISEI
