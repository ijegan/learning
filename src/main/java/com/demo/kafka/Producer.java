package com.demo.kafka;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class Producer {
	public static void produce(String brokers, String topicName) throws IOException {

		// Set properties used to configure the producer
		Properties properties = new Properties();

		properties.put("acks", "all");
		properties.put("retries", 0);

//		properties.put("batch.size", 16384);
//		properties.put("linger.ms", 1);
//		properties.put("buffer.memory", 33554432);

		properties.setProperty("bootstrap.servers", brokers);
		properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// Install interceptor list - config "interceptor.classes"
//		properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorTest.class.getName());

		// Partitioner
//		properties.put("partitioner.class", "com.demo.kafka.CustomPartitioner");

		// specify the protocol for Domain Joined clusters
		// properties.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
		// "SASL_PLAINTEXT");

		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

		String[] sentences = new String[] { "Delhi", "Mumbai", "Chennai" };

		// odd / even sample input to write to partitions 1 & 2
//		String[] sentences = new String[] { "1", "2", "3", "4", "5", "6" };

		for (int i = 0; i < sentences.length; i++) {
			String sentence = sentences[i];
			try {
				RecordMetadata cf = (RecordMetadata) producer
						.send(new ProducerRecord<String, String>(topicName, sentence)).get();

				System.out.println("offset:" + cf.offset() + " topic:" + cf.topic() + " partition: " + cf.partition());

			} catch (Exception ex) {
				System.out.print(ex.getMessage());
				throw new IOException(ex.toString());
			}
		}
	}
}