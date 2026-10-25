package com.proyecto.backend

import com.proyecto.backend.config.DatabaseFactory
import com.proyecto.backend.models.User
import com.proyecto.backend.models.UserCreateInput
import com.proyecto.backend.models.Users
import io.javalin.Javalin
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    // 1. Inicializar la base de datos SQLite y crear tablas
    DatabaseFactory.init()

    // 2. Crear y arrancar el servidor Javalin en el puerto 8080
    val app = Javalin.create().start(8080)

    // Ruta base de prueba
    app.get("/") { ctx ->
        ctx.result("¡API REST activa y funcionando con Javalin + SQLite!")
    }

    // Ruta GET /users -> Obtener la lista de usuarios
    app.get("/users") { ctx ->
        val userList = transaction {
            Users.selectAll().map {
                User(
                    id = it[Users.id],
                    name = it[Users.name],
                    email = it[Users.email]
                )
            }
        }
        ctx.json(userList)
    }

    // Ruta POST /users -> Crear un nuevo usuario
    app.post("/users") { ctx ->
        val input = ctx.bodyAsClass(UserCreateInput::class.java)

        transaction {
            Users.insert {
                it[name] = input.name
                it[email] = input.email
            }
        }

        ctx.status(201).result("Usuario creado exitosamente")
    }
}