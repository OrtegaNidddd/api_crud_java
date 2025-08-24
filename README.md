# 🏥 Registros - Sistema de Gestión de Citas Médicas

Proyecto en **Java 21 + Spring Boot** para gestionar un consultorio médico: pacientes, médicos, especialidades y citas.  
Permite registrar, listar, actualizar y eliminar información a través de endpoints REST.

---

## 🚀 Tecnologías

- **Java 21**
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **Jakarta Validation**
- **MySQL o PostgreSQL** (configurable)
- **Maven**

---

## 📂 Estructura principal

```
src/main/java/com/registros/
 ├── controller/        # Controladores REST (Cita, Paciente, Medico, Especialidad)
 ├── entity/            # Entidades JPA con validaciones
 ├── repository/        # Interfaces JpaRepository
 └── service/           # Servicios con lógica CRUD
```

---

## ⚙️ Configuración

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/OrtegaNidddd/api_crud_java.git
   cd registros
   ```

2. Crear la base de datos vacía:
   ```sql
   -- MySQL
   CREATE DATABASE registros CHARACTER SET utf8mb4_0900_ai_ci;

   -- PostgreSQL
   CREATE DATABASE registros;
   ```

3. Copiar el archivo de configuración:
   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```

4. Editar `application.properties` con tus credenciales de BD:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/registros?useSSL=false&serverTimezone=UTC
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password
   ```

5. Ejecutar el proyecto:
   ```bash
   mvn spring-boot:run
   ```

---

## 📌 Endpoints principales

### 🔹 Pacientes
- `POST /api/paciente` → Crear paciente
- `GET /api/paciente` → Listar todos
- `GET /api/paciente/{id}` → Obtener por ID
- `PUT /api/paciente/{id}` → Actualizar
- `DELETE /api/paciente/{id}` → Eliminar

### 🔹 Médicos
- `POST /api/medico` → Crear médico (requiere `especialidad.id`)
- `GET /api/medico` → Listar médicos
- `GET /api/medico/{id}` → Obtener por ID
- `PUT /api/medico/{id}` → Actualizar
- `DELETE /api/medico/{id}` → Eliminar

### 🔹 Especialidades
- `POST /api/especialidad` → Crear especialidad
- `GET /api/especialidad` → Listar todas
- `GET /api/especialidad/{id}` → Obtener por ID
- `PUT /api/especialidad/{id}` → Actualizar
- `DELETE /api/especialidad/{id}` → Eliminar

### 🔹 Citas
- `POST /api/citas` → Crear cita (requiere `paciente.id` y `medico.id`)
- `GET /api/citas` → Listar citas con relaciones
- `GET /api/citas/{id}` → Obtener por ID
- `PUT /api/citas/{id}` → Actualizar (parcial o completo)
- `DELETE /api/citas/{id}` → Eliminar

---

## 🧪 Ejemplo de uso (crear cita)

**POST** `/api/citas`
```json
{
  "estado": 1,
  "fechaCita": "2025-09-01",
  "horaCita": "10:00:00",
  "paciente": { "id": 1 },
  "medico": { "id": 1 }
}
```

Respuesta:
```json
{
  "mensaje": "Cita creada correctamente",
  "cita": {
    "id": 1,
    "estado": 1,
    "fechaCita": "2025-09-01",
    "fechaAsignacion": "2025-08-24T12:03:49.575293",
    "horaCita": "10:00:00",
    "paciente": {
      "id": 1,
      "nombre": "Juan Gómez",
      "email": "juan@example.com",
      "telefono": "3001234567",
      "direccion": "Av. Siempre Viva 123"
    },
    "medico": {
      "id": 1,
      "nombre": "Dra. Ana Pérez",
      "especialidad": {
        "id": 1,
        "nombre": "Cardiología"
      }
    }
  },
  "status": 200
}
```

---

## 📝 Notas
- El archivo `application.properties` real está **ignorado en Git** para no exponer credenciales.
- Usa `application.properties.example` como plantilla para configuraciones locales.
- Validaciones activadas con `@Valid` (ej. email, longitud de nombre, `@NotNull` en campos obligatorios).
- Relaciones configuradas con `EAGER` para evitar problemas de serialización en JSON.

---