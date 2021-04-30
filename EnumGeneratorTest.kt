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
    fun `dashes in name will convert to lower case`() {
        validateRowInGeneratedFile(name = "C-3PO", formattedName = "C_3PO")
    }

    @Test
    fun `space in name will be replaced with underscore and letters capitalized`() {
        validateRowInGeneratedFile(name = "Luke Skywalker", formattedName = "LUKE_SKYWALKER")
    }

    @Test
    fun `specieal e character converts to e in enum`() {
        validateRowInGeneratedFile(name = "Ric Olié", formattedName = "RIC_OLIE")
    }

    private fun validateRowInGeneratedFile(name: String, formattedName: String) {
        testMe.processRow(mapOf("name" to name, homeworld, species))
        val expected = """
            //this file is auto-generated
            enum class StarWarsCharacter(val name: String, val homeworld: String, val species: String) {
            
                $formattedName("$name", "${homeworld.second}", "${species.second}"),
            
            }
        """.trimIndent()
        assertEquals(expected, testMe.fileContent)
    }
}