import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
//    kotlin("kapt") version "1.9.0"
}

allprojects {
    repositories {
        maven {
            url = uri("https://mirrors.huaweicloud.com/repository/maven/")
        }
    }
    buildscript {
        repositories {
            maven {
                url = uri("https://mirrors.huaweicloud.com/repository/maven/")
            }
        }
    }
}

group = "com.ci"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.6")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("com.mybatis-flex:mybatis-flex-spring-boot3-starter:1.8.9")
    implementation("com.mybatis-flex:mybatis-flex-kotlin-extensions:1.0.8")
//    annotationProcessor("com.mybatis-flex:mybatis-flex-processor:1.8.9")
    implementation("cn.hutool:hutool-all:5.8.26")
//    kapt("com.mybatis-flex:mybatis-flex-processor:1.5.6")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("com.alibaba.fastjson2:fastjson2-kotlin:2.0.50")
    implementation("com.github.pagehelper:pagehelper:6.1.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
