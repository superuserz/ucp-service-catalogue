package com.nagp.ucp.service.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.opentracing.Tracer;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({ "com.nagp.ucp" })
@EnableFeignClients
public class UcpServiceCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(UcpServiceCatalogueApplication.class, args);
	}

	@Bean
	public Tracer initTracer() {
		SamplerConfiguration samplerConfiguration = new SamplerConfiguration().withType("const").withParam(1);
		ReporterConfiguration reporterConfiguration = ReporterConfiguration.fromEnv().withLogSpans(true);
		return Configuration.fromEnv("ucpcatalog").withSampler(samplerConfiguration).withReporter(reporterConfiguration)
				.getTracer();
	}

}
