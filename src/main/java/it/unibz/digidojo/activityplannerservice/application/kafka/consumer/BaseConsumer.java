package it.unibz.digidojo.activityplannerservice.application.kafka.consumer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

import lombok.RequiredArgsConstructor;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

import it.unibz.digidojo.sharedmodel.marshaller.Marshaller;
import it.unibz.digidojo.activityplannerservice.config.KafkaConfig;

@RequiredArgsConstructor
public abstract class BaseConsumer {
    protected final Marshaller marshaller = new Marshaller();

    public static RecordFilterStrategy<String, String> generateFilterStrategy(final String desiredPattern) {
        final Pattern pattern = Pattern.compile(desiredPattern);

        return new RecordFilterStrategy<String, String>() {
            @Override
            public boolean filter(final @NotNull ConsumerRecord<String, String> consumerRecord) {
                return StreamSupport.stream(
                                            consumerRecord.headers().headers(KafkaConfig.EVENT_TYPE_KEY).spliterator(),
                                            false
                                    )
                                    .noneMatch(header -> hasDesiredEventType(pattern, new String(header.value())));
            }
        };
    }

    private static boolean hasDesiredEventType(final Pattern pattern, final String currentEventType) {
        final Matcher matcher = pattern.matcher(currentEventType);
        return matcher.find();
    }
}
