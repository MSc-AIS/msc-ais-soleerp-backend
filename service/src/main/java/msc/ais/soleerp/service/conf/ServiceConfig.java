package msc.ais.soleerp.service.conf;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public class ServiceConfig {

    private static final Config SERVICE_CONFIG;

    static {
        SERVICE_CONFIG = ConfigFactory.parseResources("service.conf").resolve();
        // Path f = Paths.get("./" + filename);
        // this.config = ConfigFactory.parseFile(f.toFile()).withFallback(config).resolve();
    }

    private ServiceConfig() {
    }

    public static String getFont() {
        return SERVICE_CONFIG.getConfig("transformation").getString("font-ttf");
    }

}
