plugins {
    kotlin("jvm")
    `java-library`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    // Each module can declare its own dependencies
    implementation(kotlin("stdlib"))
}
