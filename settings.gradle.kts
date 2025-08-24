plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "BlogApp"
include("core")
include("persistence")
include("service")
include("api")
include("config")
