import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EnumGeneratorTest {
    private lateinit var testMe: EnumGenerator

    @MockK
    private lateinit var logger: Logger

    private val homeworld = "homeworld" to "some homeworld"
    private val species = "species" to "some species"

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        testMe = EnumGenerator(logger)
    }

    @Test
    fun `dashes in name will convert to lower case - C-3PO`() {
        val expected = "name" to "C-3PO"
        testMe.processRow(mapOf(expected, homeworld, species))
        validateRowInGeneratedFile(name = "C-3PO", formattedName = "C_3PO")
    }

    private fun validateRowInGeneratedFile(name: String, formattedName: String) {
        val expected = """
            //this file is auto-generated
            enum class StarWarsCharacter(val name: String, val homeworld: String, val species: String) {
            
                $formattedName("$name", "${homeworld.second}", "${species.second}"),
            
            }
        """.trimIndent()
        assertEquals(expected, testMe.fileContent)
    }
}