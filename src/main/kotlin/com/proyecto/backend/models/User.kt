package com.proyecto.backend.models

import org.jetbrains.exposed.sql.Table

// Definición de la tabla en SQLite
object Users : Table("users") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    val email = varchar("email", 100)

    override val primaryKey = PrimaryKey(id)
}

// Data Classes para transferir datos
data class User(val id: Int, val name: String, val email: String)
data class UserCreateInput(val name: String, val email: String)