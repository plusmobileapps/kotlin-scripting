val PROJECT_NAME = "star-wars-characters"
val GIT_REPOSITORY = "git@github.com:plusmobileapps/$PROJECT_NAME.git"

fun main(
    args: MyArgs,
    logger: Logger,
    shell: Shell,
    csvReader: CsvReader,
    enumGenerator: EnumGenerator,
    fileWriter: FileWriter
) {
    logger.verbose("Program has started")
    shell.execute("""
        git clone $GIT_REPOSITORY
        cd $PROJECT_NAME
        git checkout ${args.branch}
        cd ../
    """.trimIndent())

    try {
        csvReader.open("$PROJECT_NAME/star-wars-characters.csv") {
            readAllWithHeaderAsSequence().forEach { row: Map<String, String> ->
                enumGenerator.processRow(row)
            }
        }

        fileWriter.write(enumGenerator.fileContent)
    } catch (exception: Exception) {
        logger.info(exception.toString())
    } finally {
        shell.execute("rm -rf $PROJECT_NAME")
        logger.verbose("Program has ended")
    }
}