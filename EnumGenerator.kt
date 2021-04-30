class EnumGenerator(private val logger: Logger) {

    private var content = """
        //this file is auto-generated
        enum class StarWarsCharacter(val name: String, val homeworld: String, val species: String) {
        
        
    """.trimIndent()

    val fileContent: String
        get() {
            content += """
                
                }
            """.trimIndent()
            return content
        }

    fun processRow(row: Map<String, String>) {
        logger.verbose("Processing row: $row")
        val enumName = row["name"].orEmpty()
            .split("(-)|( )".toRegex()) //split name into words
            .joinToString(separator = "_", transform = String::toUpperCase) //capitalize and join words with _
            .replace("É", "E")
        content += """    $enumName("${row["name"]}", "${row["homeworld"]}", "${row["species"]}"),"""
        content += "\n"
    }
}