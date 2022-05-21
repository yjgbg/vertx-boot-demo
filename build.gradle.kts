plugins {
    scala
    id("com.google.protobuf") version "0.8.18"
}

group = "com.github.yjgbg"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    mavenCentral()
}

val luceneVersion = "9.1.0"
dependencies {
    implementation("org.apache.lucene:lucene-analyzers-common:8.11.1")
    implementation("org.apache.lucene:lucene-misc:${luceneVersion}") // core
    implementation("org.apache.lucene:lucene-facet:${luceneVersion}") // core
//    implementation("org.apache.lucene:lucene-queries:${luceneVersion}")
    implementation("org.apache.lucene:lucene-queryparser:${luceneVersion}") //lucene-queries,lucene-core
//    implementation("org.apache.lucene:lucene-core:${luceneVersion}")
    implementation("com.github.yjgbg:vertx-boot:1.4-SNAPSHOT")
    implementation("com.google.protobuf:protobuf-javalite:3.20.1")
    implementation("io.vertx:vertx-grpc-server:4.3.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
//protobuf {
//    protoc {
//        artifact = "com.google.protobuf:protoc:$protoVersion"
//    }
//    generateProtoTasks {
//        all().forEach {
//            it.builtins {
//                this["java"].option("lite")
//            }
//        }
//    }
//}