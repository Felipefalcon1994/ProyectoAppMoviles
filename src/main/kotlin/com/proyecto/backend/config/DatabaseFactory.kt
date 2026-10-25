package com.proyecto.backend.config

import com.proyecto.backend.models.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        // Conexión al archivo SQLite local (se creará automáticamente como data.db)
        Database.connect("jdbc:sqlite:./data.db", driver = "org.sqlite.JDBC")

        // Crear la tabla si no existe
        transaction {
            SchemaUtils.create(Users)
        }
    }
}