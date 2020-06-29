package com.demo.kafka;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerInterceptorExample {
	public static void produce(String brokers, String topicName) throws IOException {

		// Set properties used to configure the producer
		Properties properties = new Properties();

		properties.put("acks", "all");
		properties.put("retries", 0);

		properties.put("latency.ms", 2000);
//		properties.put("linger.ms", 2000);
		properties.put("batch.size", 16350);
//		properties.put("buffer.memory", 33554432);

		properties.setProperty("bootstrap.servers", brokers);
		properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// Interceptor config "interceptor.classes"
		properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorTest.class.getName());

		// confluent interceptor monitoring
//		properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
//				"io.confluent.monitoring.clients.interceptor.MonitoringProducerInterceptor");
//
//		properties.setProperty("confluent.monitoring.interceptor.topic", "_confluent-monitoring_confluent-monitoring");
//		properties.setProperty("confluent.monitoring.interceptor.publishMs", "1000");

		// Partitioner
		properties.put("partitioner.class", "com.demo.kafka.CustomPartitioner");

		// specify the protocol for Domain Joined clusters
		// properties.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
		// "SASL_PLAINTEXT");

		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

		for (Integer i = 85; i <= 95; i++) {
			try {
//				RecordMetadata cf = (RecordMetadata) producer
//						.send(new ProducerRecord<String, String>(topicName, i.toString())).get();
//
//				System.out.println(
//						"offset:" + cf.offset() + " topic:" + cf.topic() + " partition: " + cf.partition() + "\n");

				producer.send(new ProducerRecord<String, String>(topicName, i.toString())).get();

			} catch (Exception ex) {
				System.out.print(ex.getMessage());
				throw new IOException(ex.toString());
			}
		}

	}
}