#!/usr/bin/env kscript

//kotlinx-cli
@file:MavenRepository("kotlinx", "https://kotlin.bintray.com/kotlinx/")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-cli-jvm:0.3.1")

//csv parser
@file:DependsOn("com.github.doyaaaaaken:kotlin-csv-jvm:0.15.1")

//local files
@file:Include("Main.kt")
@file:Include("MyArgs.kt")
@file:Include("ScriptUtils.kt")
@file:Include("EnumGenerator.kt")

//testing
@file:DependsOn("junit:junit:4.12")
@file:DependsOn("io.mockk:mockk:1.10.6")
@file:Include("MainTest.kt")
@file:Include("EnumGeneratorTest.kt")

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