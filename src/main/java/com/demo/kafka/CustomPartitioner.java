package com.demo.kafka;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class CustomPartitioner implements Partitioner {

	public CustomPartitioner() {
	}

	@Override
	public void configure(Map<String, ?> configs) {

	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

		int partition = 0;

		int val = Integer.parseInt(value.toString());

		if (val % 2 == 0) {
			partition = 1;
		} else {
			partition = 2;
		}

		return partition;

	}

	@Override
	public void close() {

	}

}