import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EnumGeneratorTest {
    private lateinit var testMe: EnumGenerator

    @MockK
    private lateinit var logger: Logger

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        testMe = EnumGenerator(logger)
    }

    @Test
    fun `C-3PO name`() {
        validateRowInGeneratedFile("""C_3PO("C-3PO", "Tatooine", "Droid")""")
    }

    private fun validateRowInGeneratedFile(row: String) {
        val expected = """
            //this file is auto-generated
            enum class StarWarsCharacter(val name: String, val homeworld: String, val species: String) {
            
                $row,
            
            }
        """.trimIndent()
        assertEquals(expected, testMe.fileContent)
    }
}