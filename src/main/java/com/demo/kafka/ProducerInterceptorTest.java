package com.demo.kafka;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerInterceptorTest implements ProducerInterceptor {
	private final Logger logger = LoggerFactory.getLogger(ProducerInterceptorTest.class);
	private int onSendCount;
	private int onAckCount;

	private static final String TRACKING_CORRELATION_ID = "trackingCorrelationId";

	@Override
	public ProducerRecord onSend(final ProducerRecord record) {
		onSendCount++;
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("onSend topic=%s key=%s value=%s %d \n", record.topic(), record.key(),
					record.value().toString(), record.partition()));
		} else {
			if (onSendCount % 100 == 0) {
				logger.info(String.format("onSend topic=%s key=%s value=%s %d \n", record.topic(), record.key(),
						record.value().toString(), record.partition()));
			}
		}

		System.out.print(String.format("onSend topic=%s key=%s value=%s %d \n", record.topic(), record.key(),
				record.value().toString(), record.partition()));

		record.headers().add(TRACKING_CORRELATION_ID, CorrelationIdGenerator.getId());

		return record;
	}

	@Override
	public void onAcknowledgement(final RecordMetadata metadata, final Exception exception) {
		onAckCount++;

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("onAck topic=%s, part=%d, offset=%d\n", metadata.topic(), metadata.partition(),
					metadata.offset()));
		} else {
			if (onAckCount % 100 == 0) {
				logger.info(String.format("onAck topic=%s, part=%d, offset=%d\n", metadata.topic(),
						metadata.partition(), metadata.offset()));
			}
		}

		System.out.print(String.format("onAck topic=%s, part=%d, offset=%d\n", metadata.topic(), metadata.partition(),
				metadata.offset()));

	}

	@Override
	public void close() {
	}

	@Override
	public void configure(Map<String, ?> configs) {
	}

	private static class CorrelationIdGenerator {

		private static final Charset CHARSET = Charset.forName("UTF-8");

		static byte[] getId() {
			return UUID.randomUUID().toString().getBytes(CHARSET);
		}
	}
}