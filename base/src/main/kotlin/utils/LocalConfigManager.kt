package utils

import java.io.FileInputStream
import java.util.*

object LocalConfigManager {

    private val properties = Properties()

    fun init() {
        val propertiesFile = "base/local.properties"
        FileInputStream(propertiesFile).use { inputStream ->
            properties.load(inputStream)
        }
    }

    fun getProperty(key: String): String? {
        return properties.getProperty(key)
    }
}