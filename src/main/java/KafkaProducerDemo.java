import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();
        // Bootstrap server
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        // Property acks
        properties.setProperty("acks", "1");
        properties.setProperty("retries", "3");
        properties.setProperty("linger.ms", "1");

        Producer<String, String> producer =
                new org.apache.kafka.clients.producer.KafkaProducer<String, String>(properties);

//        ProducerRecord<String, String> producerRecord =
//                new ProducerRecord<String, String>("second_topic", "3", "message test");

        for (int key = 0; key < 10; key++) {
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<String, String>("second_topic", Integer.toString(key),
                            "message with key: " + key);
            producer.send(producerRecord);
        }
        producer.close();
    }
}
