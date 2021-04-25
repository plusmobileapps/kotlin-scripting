import java.io.File
import java.io.FileWriter
import java.io.IOException
import com.github.doyaaaaaken.kotlincsv.client.CsvFileReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

class Logger(private val verbose: Boolean = false) {

    fun verbose(message: String) {
        if (verbose) println(message)
    }

    fun info(message: String) {
        println(message)
    }

}

class Shell(private val logger: Logger) {

    fun execute(command: String, dir: File? = null): Int {
        logger.verbose("Running in shell: $command")
        return ProcessBuilder("/bin/sh", "-c", command)
            .redirectErrorStream(true)
            .inheritIO()
            .directory(dir)
            .start()
            .waitFor()
    }
}

class FileWriter(private val logger: Logger) {
    companion object {
        val GENERATED_FILE_PATH = "StarWarsCharacters.kt"
    }

    @Throws(IOException::class)
    fun write(fileContent: String) {
        logger.verbose("Starting to write file to path: $GENERATED_FILE_PATH")
        FileWriter(GENERATED_FILE_PATH).use {
            it.write(fileContent)
        }
        logger.verbose("Finished writing file to: $GENERATED_FILE_PATH")
    }
}

class CsvReader(private val logger: Logger) {
    fun <T> open(filePath: String, csvFileReader: CsvFileReader.() -> T) {
        logger.verbose("Opening csv file at: $filePath")
        csvReader().open(filePath, csvFileReader)
        logger.verbose("Finished processing csv file.")
    }
}