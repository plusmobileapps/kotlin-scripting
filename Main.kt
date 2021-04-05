val PROJECT_NAME = "star-wars-characters"
val GIT_REPOSITORY = "git@github.com:plusmobileapps/$PROJECT_NAME.git"

fun main(args: MyArgs, logger: Logger, shell: Shell) {
    logger.verbose("Program has started")

    shell.execute("git clone $GIT_REPOSITORY")

    shell.execute("rm -rf $PROJECT_NAME")

    logger.verbose("Program has ended")
}