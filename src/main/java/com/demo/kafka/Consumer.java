package com.demo.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Consumer {
	public static int consume(String brokers, String groupId, String topicName) {
		// Create a consumer
		KafkaConsumer<String, String> consumer;
		// Configure the consumer
		Properties properties = new Properties();
		// Point it to the brokers
		properties.setProperty("bootstrap.servers", brokers);
		// Set the consumer group (all consumers must belong to a group).
		properties.setProperty("group.id", groupId);
		// Set how to serialize key/value pairs
		properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		// When a group is first created, it has no offset stored to start reading from.
		// This tells it to start
		// with the earliest record in the stream.
		properties.setProperty("auto.offset.reset", "earliest");

		// specify the protocol for Domain Joined clusters
		// properties.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
		// "SASL_PLAINTEXT");

		consumer = new KafkaConsumer<>(properties);
		
		
//		ArrayList alist=new ArrayList();
//		alist.add("integrate");
//		alist.add("test");
//		
//		String[] str=  {"str","str2"}; 

		// Subscribe to the 'test' topic
		consumer.subscribe(Arrays.asList(topicName));

		// Loop until ctrl + c
		int count = 0;
		while (true) {
			// Poll for records
			ConsumerRecords<String, String> records = consumer.poll(200);
			// Did we get any?
			if (records.count() == 0) {
				// timeout/nothing to read
			} else {
				// Yes, loop over records
				for (ConsumerRecord<String, String> record : records) {
					// Display record and count
					count += 1;
					System.out.println(count + ": " + record.value());
				}
			}
		}
	}
}