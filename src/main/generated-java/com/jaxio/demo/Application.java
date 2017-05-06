/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template angular-lab:springboot/src/main/java/Application.p.vm.java
 */
package com.jaxio.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jaxio.demo.config.ApplicationProperties;

@SpringBootApplication
@EnableJpaRepositories("com.jaxio.demo.repository")
@EnableElasticsearchRepositories("com.jaxio.demo.searchrepository")
@EnableConfigurationProperties({ ApplicationProperties.class })
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        Environment env = app.run(args).getEnvironment();

        String contextPath = env.getProperty("server.contextPath");
        String serverPort = env.getProperty("server.port");

        log.info("\n\nAccess URLs:\n----------------------------------------------------------\n\t" + "Local: \t\thttp://127.0.0.1:" + serverPort + contextPath
                + "\n\n");
    }
}
