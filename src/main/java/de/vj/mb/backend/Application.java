package de.vj.mb.backend;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		if (envCheck()) {
			SpringApplication.run(Application.class, args);
		}
	}

	private static boolean envCheck() {
		LOG.info("SYSTEM ENVIRONMENT");
		StringBuilder sbEnv = new StringBuilder();
		System.getenv().entrySet().forEach(e -> sbEnv.append(String.format("%n%s = %s", e.getKey(), e.getValue())));
		String sysenvs = sbEnv.toString();
		LOG.info(sysenvs);

		if (StringUtils.isBlank(System.getenv(Properties.GCP_PROJECT_ID))) {
			LOG.error("Missing enviroment variable {}", Properties.GCP_PROJECT_ID);
			return false;

		}
		if (StringUtils.isBlank(System.getenv(Properties.GOOGLE_APPLICATION_CREDENTIALS))) {
			LOG.warn("Missing enviroment variable {}", Properties.GOOGLE_APPLICATION_CREDENTIALS);
		}

		LOG.info("SYSTEM PROPERTIES");
		StringBuilder sb = new StringBuilder();
		System.getProperties().entrySet().forEach(e -> sb.append(String.format("%n%s = %s", e.getKey(), e.getValue())));
		String sysprops = sb.toString();
		LOG.info(sysprops);

		return true;
	}

}
