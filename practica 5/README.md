# Sistema de Contratación de Personal

Sistema desarrollado con **Programación Orientada a Objetos (POO)** e **Interfaz Gráfica** usando Java Swing.

## 📋 Estructura del Proyecto

### Clases (POO):
- **`Direccion.java`**: Clase para manejar direcciones (Composición)
- **`Empleado.java`**: Clase para empleados con dirección (Composición con Direccion)
- **`Empresa.java`**: Clase para empresa con lista de empleados (Agregación)
- **`SistemaContratacion.java`**: Interfaz gráfica principal

## 🚀 Cómo Ejecutar

### Opción 1: Usando el script batch
```bash
ejecutar.bat
```

### Opción 2: Compilar y ejecutar manualmente
```bash
# Compilar todas las clases
javac Clase\*.java SistemaContratacion.java

# Ejecutar la aplicación
java SistemaContratacion
```

### Opción 3: Desde el IDE
1. Abre el proyecto en tu IDE (IntelliJ, Eclipse, NetBeans, etc.)
2. Ejecuta la clase `SistemaContratacion.java`

## 🖥️ Funcionalidades

### Pestaña 1: Formulario de Solicitud
- Los candidatos pueden completar un formulario con:
  - Nombre completo
  - Puesto deseado
  - Dirección (Ciudad, Calle, Número)
- Validación de campos
- Envío de solicitud

### Pestaña 2: Gestión de Empleados
- Ver todas las solicitudes recibidas
- Ver detalles de cada solicitud
- **Contratar** empleados
- **Rechazar** solicitudes
- Ver lista de empleados contratados

## 💡 Conceptos de POO Implementados

✅ **Encapsulación**: Atributos privados con getters/setters  
✅ **Composición**: Empleado tiene una Direccion  
✅ **Agregación**: Empresa tiene una lista de Empleados  
✅ **Métodos**: toString(), contratar(), listar_empleados()

## ⚠️ Solución de Problemas

Si la ventana no aparece:
1. Verifica que Java esté instalado: `java -version`
2. Verifica que todos los archivos .class estén generados
3. Ejecuta desde la línea de comandos (CMD o PowerShell)
4. Asegúrate de estar en el directorio correcto del proyecto

