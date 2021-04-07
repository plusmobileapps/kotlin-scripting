class EnumGenerator(private val logger: Logger) {

    private var content = """
        /**
         *
         *
         */
         enum class StarWarsCharacter(val name: String, val home: String, val species: String) {
    """.trimIndent()

    val fileContent: String
        get() {
            content += "}"
            return content
        }

    fun processRow(row: Map<String, String>) {
        logger.verbose("Processing row: $row")

    }
}