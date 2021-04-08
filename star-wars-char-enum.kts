#!/usr/bin/env kscript

@file:MavenRepository("kotlinx", "https://kotlin.bintray.com/kotlinx/")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-cli-jvm:0.3.1")
@file:DependsOn("com.github.doyaaaaaken:kotlin-csv-jvm:0.15.1")
@file:Include("Main.kt")
@file:Include("MyArgs.kt")
@file:Include("ScriptUtils.kt")
@file:Include("CsvReader.kt")
@file:Include("EnumGenerator.kt")

import kotlinx.cli.ArgParser

val parser = ArgParser(
    programName = "./star-wars-char-enum.kts"
)

val myArgs = MyArgs(parser, args)
val logger = Logger(myArgs.verbose)

main(
    args = myArgs,
    logger = logger,
    shell = Shell(logger),
    csvReader = CsvReader(logger),
    enumGenerator = EnumGenerator(logger),
    fileWriter = FileWriter(logger)
)