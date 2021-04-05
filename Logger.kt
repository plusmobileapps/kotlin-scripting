class Logger(private val verbose: Boolean) {

    fun verbose(message: String) {
        if (verbose) println(message)
    }

    fun info(message: String) {
        println(message)
    }

}