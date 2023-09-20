package ru.wwerlosh.footprint.util

class CarFuelDB {
    private val carFuelData = mapOf(
        "Седан" to mapOf("Дизель" to 0.20419, "Бензин" to 0.27807, "Смешанное топливо" to 0.1448, "Электричество" to 0.0),
        "Пикап" to mapOf("Дизель" to 0.20419, "Бензин" to 0.27807, "Смешанное топливо" to 0.1448, "Электричество" to 0.0),
        "Хэтчбек" to mapOf("Дизель" to 0.16637, "Бензин" to 0.18659, "Смешанное топливо" to 0.10698, "Электричество" to 0.0),
        "Универсал" to mapOf("Дизель" to 0.16637, "Бензин" to 0.18659, "Смешанное топливо" to 0.10698, "Электричество" to 0.0),
        "Лифтбек" to mapOf("Дизель" to 0.16637, "Бензин" to 0.18659, "Смешанное топливо" to 0.10698, "Электричество" to 0.0),
        "Минивен" to mapOf("Дизель" to 0.13721, "Бензин" to 0.14836, "Смешанное топливо" to 0.10275, "Электричество" to 0.0),
        "Купе" to mapOf("Дизель" to 0.20419, "Бензин" to 0.27807, "Смешанное топливо" to 0.1448, "Электричество" to 0.0),
        "Кроссовер" to mapOf("Дизель" to 0.16637, "Бензин" to 0.18659, "Смешанное топливо" to 0.10698, "Электричество" to 0.0),
        "Мотоцикл" to mapOf("Дизель" to 0.0, "Бензин" to 0.08277, "Смешанное топливо" to 0.0, "Электричество" to 0.0)
    )

    private val carTypes = arrayOf(
        "Выберите тип кузова",
        "Седан",
        "Пикап",
        "Хэтчбек",
        "Универсал",
        "Лифтбек",
        "Минивен",
        "Купе",
        "Кроссовер",
        "Мотоцикл"
    )

    private val fuelTypes = arrayOf(
        "Выберите тип топлива",
        "Дизель",
        "Бензин",
        "Смешанное топливо",
        "Электричество"
    )

    fun getData(): Map<String, Map<String, Double>> {
        return carFuelData
    }

    fun getCarTypes(): Array<String> {
        return carTypes
    }

    fun getFuelTypes(): Array<String> {
        return fuelTypes
    }
}