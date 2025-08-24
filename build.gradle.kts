import java.awt.Desktop
import java.net.URI

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.jetbrains.kotlin.plugin.allopen") version "1.9.25"
}

springBoot {
	buildInfo()
	mainClass.set("com.example.BlogApp.BlogAppApplicationKt")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {

	implementation(project(":core"))
	implementation(project(":api"))
	implementation(project(":config"))
	implementation(project(":persistence"))
	implementation(project(":service"))

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("org.springframework.boot.autoconfigure.SpringBootApplication")
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}


tasks.withType<Test> {
	useJUnitPlatform()
}

/**
 * Custom Gradle task to run app locally and auto-open Swagger UI
 */
task("runLocalWebApplication", JavaExec::class) {
	classpath =
		sourceSets.main.get().runtimeClasspath + files(sourceSets.test.get().resources.srcDirs)
	mainClass.set("com.example.BlogApp.BlogAppApplicationKt")


	val httpsPort = "9090"

	args = listOf(
		"--debug",
		"--server.port=$httpsPort"
	)

	doFirst {

		val currentUsername = System.getProperty("user.name")
		println("Current User: $currentUsername")

		Desktop.getDesktop().browse(URI("http://localhost:$httpsPort/swagger"))
	}
}

allprojects {
	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "java-library")

	java {
		toolchain {
			languageVersion.set(JavaLanguageVersion.of(17))
		}
	}

	dependencies {
		implementation(kotlin("stdlib"))
	}
}
