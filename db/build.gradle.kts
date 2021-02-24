import nu.studer.gradle.jooq.JooqEdition
import org.jooq.meta.jaxb.ForcedType
import org.jooq.meta.jaxb.Property

plugins {
    id("msc.ais.soleerp.java-library-conventions")
    id("nu.studer.jooq") version "5.2"
}

dependencies {
    implementation(project(":model"))
    implementation("org.postgresql:postgresql:42.2.18")
    implementation("org.apache.commons:commons-dbcp2:2.8.0")
    // jOOQ
    // implementation ("org.jooq:jooq:3.14.7")
    implementation ("org.jooq:jooq-meta:3.14.7")
    implementation ("org.jooq:jooq-codegen:3.14.7")
    jooqGenerator("org.postgresql:postgresql:42.2.14")
}

jooq {
    version.set("3.14.7")  // default (can be omitted)
    edition.set(JooqEdition.OSS)  // default (can be omitted)

    configurations {
        create("main") {  // name of the jOOQ configuration
            generateSchemaSourceOnCompilation.set(true)  // default (can be omitted)

            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://snf-879544.vm.okeanos.grnet.gr:5432/postgres?currentSchema=erp"
                    user = "postgres"
                    password = "ms_ais_postgres_2021"
                    properties.add(Property().withKey("ssl").withValue("false"))
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "erp"
                        forcedTypes.addAll(arrayOf(
                            ForcedType()
                                .withName("varchar")
                                .withIncludeExpression(".*")
                                .withIncludeTypes("JSONB?"),
                            ForcedType()
                                .withName("varchar")
                                .withIncludeExpression(".*")
                                .withIncludeTypes("INET")
                        ).toList())
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                    }
                    target.apply {
                        packageName = "msc.ais.soleerp.db.jooq.generated"
                        directory = "build/generated-src/jooq/main"  // default (can be omitted)
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}

tasks.named<nu.studer.gradle.jooq.JooqGenerate>("generateJooq") {
    allInputsDeclared.set(true)
}
