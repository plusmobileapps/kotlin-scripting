import java.io.File

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