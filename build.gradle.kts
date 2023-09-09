plugins {
    id("java")
    id("org.openapi.generator") version "7.0.0"
    id("org.springframework.boot") version "2.5.1"
    id("io.spring.dependency-management") version "1.1.3"
}

apply(plugin = "org.openapi.generator")

group = "ru.sber.beautifulcode"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-test")

    implementation("io.swagger.core.v3:swagger-annotations:2.2.10")
}

val rootPackage = "ru.sber.beautifulcode"

openApiGenerate {
    generatorName.set("spring")
    outputDir.set("$buildDir/generated-sources/openapi/")
    inputSpec.set("$rootDir/openapi/check-brackets-api.yml")
    apiPackage.set("ru.sber.beautifulcode.api")
    modelPackage.set("ru.sber.beautifulcode.model")
    configOptions.putAll(mapOf(
            "interfaceOnly" to "true",
            "serializationLibrary" to "jackson",
            "useBeanValidation" to "true",
            "interfaceOnly" to "true",
            "skipDefaultInterface" to "true",
            "openApiNullable" to "false"
    ))
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
}

tasks.compileJava {
    dependsOn(tasks.getByName("openApiGenerate"))
}

sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME)
        .java.srcDir("$buildDir/generated-sources/openapi/src/main/java")