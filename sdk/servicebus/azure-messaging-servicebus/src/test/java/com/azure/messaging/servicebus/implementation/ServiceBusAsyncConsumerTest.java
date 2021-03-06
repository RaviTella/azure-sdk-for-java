// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.messaging.servicebus.implementation;

import com.azure.core.amqp.AmqpEndpointState;
import com.azure.core.amqp.AmqpRetryPolicy;
import com.azure.core.amqp.implementation.AmqpReceiveLink;
import com.azure.core.amqp.implementation.MessageSerializer;
import com.azure.core.util.logging.ClientLogger;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import org.apache.qpid.proton.message.Message;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.Disposable;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.UUID;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

class ServiceBusAsyncConsumerTest {
    private final DirectProcessor<Message> messageProcessor = DirectProcessor.create();
    private final FluxSink<Message> messageProcessorSink = messageProcessor.sink(FluxSink.OverflowStrategy.BUFFER);
    private final DirectProcessor<AmqpEndpointState> endpointProcessor = DirectProcessor.create();
    private final FluxSink<AmqpEndpointState> endpointProcessorSink = endpointProcessor.sink(FluxSink.OverflowStrategy.BUFFER);
    private final ClientLogger logger = new ClientLogger(ServiceBusAsyncConsumer.class);

    private ServiceBusReceiveLinkProcessor linkProcessor;

    @Mock
    private ServiceBusAmqpConnection connection;
    @Mock
    private AmqpReceiveLink link;
    @Mock
    private AmqpRetryPolicy retryPolicy;
    @Mock
    private Disposable parentConnection;
    @Mock
    private MessageSerializer serializer;
    @Mock
    private Function<ServiceBusReceivedMessage, Mono<Void>> onComplete;
    @Mock
    private Function<ServiceBusReceivedMessage, Mono<Void>> onAbandon;

    @BeforeAll
    static void beforeAll() {
        StepVerifier.setDefaultTimeout(Duration.ofSeconds(20));
    }

    @AfterAll
    static void afterAll() {
        StepVerifier.resetDefaultTimeout();
    }

    @BeforeEach
    void setup(TestInfo testInfo) {
        logger.info("[{}]: Setting up.", testInfo.getDisplayName());

        MockitoAnnotations.initMocks(this);
        linkProcessor = Flux.<AmqpReceiveLink>create(sink -> sink.next(link))
            .subscribeWith(new ServiceBusReceiveLinkProcessor(10, retryPolicy, parentConnection));

        when(connection.getEndpointStates()).thenReturn(Flux.create(sink -> sink.next(AmqpEndpointState.ACTIVE)));

        when(link.getEndpointStates()).thenReturn(endpointProcessor);
        when(link.receive()).thenReturn(messageProcessor);

        when(onComplete.apply(any(ServiceBusReceivedMessage.class))).thenReturn(Mono.empty());
    }

    @AfterEach
    void teardown(TestInfo testInfo) {
        logger.info("[{}]: Tearing down.", testInfo.getDisplayName());

        Mockito.framework().clearInlineMocks();
    }

    /**
     * Verifies that we can receive messages from the processor and auto complete them.
     */
    @Test
    void receiveAutoComplete() {
        // Arrange
        final boolean isAutoComplete = true;
        final ServiceBusAsyncConsumer consumer = new ServiceBusAsyncConsumer(linkProcessor, serializer, isAutoComplete, onComplete, onAbandon);

        final Message message1 = mock(Message.class);
        final Message message2 = mock(Message.class);
        final ServiceBusReceivedMessage receivedMessage1 = mock(ServiceBusReceivedMessage.class);
        final UUID lockToken1 = UUID.randomUUID();
        final ServiceBusReceivedMessage receivedMessage2 = mock(ServiceBusReceivedMessage.class);
        final UUID lockToken2 = UUID.randomUUID();

        when(receivedMessage1.getLockToken()).thenReturn(lockToken1);
        when(receivedMessage2.getLockToken()).thenReturn(lockToken2);

        when(serializer.deserialize(message1, ServiceBusReceivedMessage.class)).thenReturn(receivedMessage1);
        when(serializer.deserialize(message2, ServiceBusReceivedMessage.class)).thenReturn(receivedMessage2);

        // Act and Assert
        StepVerifier.create(consumer.receive().take(2))
            .then(() -> {
                endpointProcessorSink.next(AmqpEndpointState.ACTIVE);
                messageProcessorSink.next(message1);
                messageProcessorSink.next(message2);
            })
            .expectNext(receivedMessage1, receivedMessage2)
            .verifyComplete();

        verify(onComplete).apply(receivedMessage1);
        verify(onComplete).apply(receivedMessage2);
    }

    /**
     * Verifies that we can receive messages from the processor and it does not auto complete them.
     */
    @Test
    void receiveNoAutoComplete() {
        // Arrange
        final boolean isAutoComplete = false;
        final ServiceBusAsyncConsumer consumer = new ServiceBusAsyncConsumer(linkProcessor, serializer, isAutoComplete, onComplete, onAbandon);

        final Message message1 = mock(Message.class);
        final Message message2 = mock(Message.class);
        final ServiceBusReceivedMessage receivedMessage1 = mock(ServiceBusReceivedMessage.class);
        final UUID lockToken1 = UUID.randomUUID();
        final ServiceBusReceivedMessage receivedMessage2 = mock(ServiceBusReceivedMessage.class);
        final UUID lockToken2 = UUID.randomUUID();

        when(receivedMessage1.getLockToken()).thenReturn(lockToken1);
        when(receivedMessage2.getLockToken()).thenReturn(lockToken2);

        when(serializer.deserialize(message1, ServiceBusReceivedMessage.class)).thenReturn(receivedMessage1);
        when(serializer.deserialize(message2, ServiceBusReceivedMessage.class)).thenReturn(receivedMessage2);

        // Act and Assert
        StepVerifier.create(consumer.receive())
            .then(() -> {
                endpointProcessorSink.next(AmqpEndpointState.ACTIVE);
                messageProcessorSink.next(message1);
            })
            .expectNext(receivedMessage1)
            .then(() -> {
                messageProcessorSink.next(message2);
            })
            .expectNext(receivedMessage2)
            .thenCancel()
            .verify();

        verifyZeroInteractions(onComplete);
    }

    /**
     * Verifies that if we dispose the consumer, it also completes.
     */
    @Test
    void canDispose() {
        // Arrange
        final boolean isAutoComplete = false;
        final Function<ServiceBusReceivedMessage, Mono<Void>> onComplete = (message) -> {
            Assertions.fail("Should not complete");
            return Mono.empty();
        };
        final String transferEntityPath = "some-transfer-path";
        final ServiceBusAsyncConsumer consumer = new ServiceBusAsyncConsumer(linkProcessor, serializer, isAutoComplete, onComplete, onAbandon);

        final Message message1 = mock(Message.class);
        final ServiceBusReceivedMessage receivedMessage1 = mock(ServiceBusReceivedMessage.class);

        when(serializer.deserialize(message1, ServiceBusReceivedMessage.class)).thenReturn(receivedMessage1);

        // Act and Assert
        StepVerifier.create(consumer.receive())
            .then(() -> {
                endpointProcessorSink.next(AmqpEndpointState.ACTIVE);
                messageProcessorSink.next(message1);
            })
            .expectNext(receivedMessage1)
            .then(() -> consumer.close())
            .verifyComplete();
    }
}
