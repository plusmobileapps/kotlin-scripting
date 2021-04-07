import com.github.doyaaaaaken.kotlincsv.client.CsvFileReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

class CsvReader(private val logger: Logger) {
    fun <T> open(filePath: String, csvFileReader: CsvFileReader.() -> T) {
        logger.verbose("Opening csv file at: $filePath")
        csvReader().open(filePath, csvFileReader)
        logger.verbose("Finished processing csv file.")
    }
}