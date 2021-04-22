import com.github.doyaaaaaken.kotlincsv.client.CsvFileReader
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MainTest {
    @MockK
    private lateinit var args: MyArgs

    @MockK
    private lateinit var logger: Logger

    @MockK
    private lateinit var shell: Shell

    @MockK
    private lateinit var enumGenerator: EnumGenerator

    @MockK
    private lateinit var csvReader: CsvReader

    @MockK
    private lateinit var fileWriter: FileWriter

    private val expectedRemoteBranch = "some-amazing-branch"
    private val expectedGeneratedContent = "some generated kotlin enum tested in EnumGeneratorTest"

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        every { args.branch } returns expectedRemoteBranch
        every { shell.execute(any(), null) } returns 0
        every { enumGenerator.fileContent } returns expectedGeneratedContent
    }

    @Test
    fun `happy path - downloads git repository, process csv, and write generated enum to file`() {
        val expectedRow = mapOf("name" to "some value")
        val rows = sequenceOf(expectedRow)
        val csvSlot = slot<CsvFileReader.() -> Unit>()
        val csvFileReader = mockk<CsvFileReader>()
        every { csvFileReader.readAllWithHeaderAsSequence() } returns rows
        every { csvReader.open("$PROJECT_NAME/star-wars-characters.csv", capture(csvSlot)) } answers {
            csvFileReader.apply { csvSlot.captured(this) }
        }

        main(
            args = args,
            logger = logger,
            shell = shell,
            csvReader = csvReader,
            enumGenerator = enumGenerator,
            fileWriter = fileWriter
        )

        verifyShellExecution {
            """
            git clone $GIT_REPOSITORY
            cd $PROJECT_NAME
            git checkout $expectedRemoteBranch
            cd ../
        """.trimIndent()
        }
        verify { enumGenerator.processRow(expectedRow) }
        verify { fileWriter.write(expectedGeneratedContent) }
        verifyShellExecution { "rm -rf $PROJECT_NAME" }
    }

    private fun verifyShellExecution(command: () -> String) {
        verify { shell.execute(command = command()) }
    }
}