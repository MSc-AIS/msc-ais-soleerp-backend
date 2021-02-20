package msc.ais.soleerp.service.transofmration.conf;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public class FreeMarkerDefaultConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(FreeMarkerDefaultConfig.class);
    private static final Configuration FREEMARKER_CONFIG;

    static {
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
            configuration.setDirectoryForTemplateLoading(
                Paths.get("src/main/resources/templates").toFile());
            // Recommended settings for new projects:
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setLogTemplateExceptions(false);
            configuration.setWrapUncheckedExceptions(true);
            configuration.setFallbackOnNullLoopVariable(false);
            FREEMARKER_CONFIG = configuration;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalStateException(
                "Error... Failed to initialize freemarker default configuration.");
        }
    }

    private FreeMarkerDefaultConfig() {
    }

    /**
     * Get the default PDF configuration.
     *
     * @return The configuration
     */
    public static Configuration getConfig() {
        return FREEMARKER_CONFIG;
    }

}
