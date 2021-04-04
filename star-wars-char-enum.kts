#!/usr/bin/env kscript

@file:MavenRepository("kotlinx", "https://kotlin.bintray.com/kotlinx/")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-cli-jvm:0.3.1")
@file:DependsOn("com.github.doyaaaaaken:kotlin-csv-jvm:0.15.1")
@file:Include("Main.kt")
@file:Include("Shell.kt")
@file:Include("CsvReader.kt")
@file:Include("Logger.kt")
@file:Include("MyArgs.kt")
@file:Include("EnumGenerator.kt")
@file:Include("FileWriter.kt")

println("Hello from Kotlin!")