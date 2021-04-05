import kotlinx.cli.*

class MyArgs(parser: ArgParser, args: Array<String>) {

    val verbose by parser.option(
        ArgType.Boolean,
        shortName = "v",
        fullName = "verbose",
        description = "Turn on verbose logging when running the script"
    ).default(false)

    val branch by parser.option(
        ArgType.String,
        shortName = "b",
        fullName = "branch",
        description = "the branch name to be used from the data set repository https://github.com/plusmobileapps/star-wars-characters"
    ).default("main")

    init {
        parser.parse(args)
    }
}