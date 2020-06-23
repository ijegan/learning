package com.demo.kafka;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
//import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;

public class ProducerAvroExample {

	public static void main(final String[] args) throws IOException {

		Properties props = new Properties();

		props.setProperty("bootstrap.servers", "localhost:9092");

		props.put("acks", "all");
		props.put("retries", 0);

		// Add additional properties.

//		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//		props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
//		props.put("schema.registry.url", "http://localhost:8081");

		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
//		props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
		props.put("schema.registry.url", "http://localhost:8081");

		try (KafkaProducer producer = new KafkaProducer<>(props)) {
			for (int i = 31; i <= 40; i++) {
//				ProducerRecord record = new ProducerRecord<>("test", new Message("Message-" + i, 1, "Extra"+i));

//				ProducerRecord record = new ProducerRecord<>("payment", new Payment("Tran" + i, i, "testMsg" + i));
				ProducerRecord record = new ProducerRecord<>("payment", new Payment("Tran" + i, i));

				producer.send(record);
			}
		}
		System.out.println("Done");
	}

}