package com.springboot.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.*;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSelector;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.filter.MessageFilter;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.model.Address;
import com.springboot.model.Student;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfig {
	
	public String INPUT_DIR = "the_source_dir";
    public String OUTPUT_DIR = "the_dest_dir";
    public String FILE_PATTERN = "*.txt";
    
    @Bean
    public MessageChannel fileChannel() {
        return new DirectChannel();
    }
    
    @Bean
    public MessageChannel pubSubFileChannel() {
        return new PublishSubscribeChannel();
    }
    
    @Bean
    @InboundChannelAdapter(value = "fileChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource sourceReader= new FileReadingMessageSource();
        sourceReader.setDirectory(new File(INPUT_DIR));
        sourceReader.setFilter(new SimplePatternFileListFilter(FILE_PATTERN));
        return sourceReader;
    }
 
    @Bean
    @ServiceActivator(inputChannel= "fileChannel")
    public MessageHandler fileWritingMessageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }

	@Bean
	public MessageChannel recieverChannel() {
		return new DirectChannel();
	}

	// @Bean
	// public MessageChannel replyChannel() {
	// return new DirectChannel();
	// }

	// Filter Example
	/*
	 * @Filter(inputChannel = "router.channel")
	 * 
	 * @Bean public MessageFilter filter() { MessageFilter filter = new
	 * MessageFilter(new MessageSelector() {
	 * 
	 * @Override public boolean accept(Message<?> message) { // TODO Auto-generated
	 * method stub return message.getPayload() instanceof Student; } });
	 * filter.setOutputChannelName("student.channel"); return filter; }
	 */

	// Transformer Example
	@Bean
	@Transformer(inputChannel = "integration.student.gateway.channel", outputChannel = "integration.student.toConvertObject.channel")
	public HeaderEnricher enrichHeader() {
		Map<String, HeaderValueMessageProcessor<String>> headersToAdd = new HashMap<>();
		headersToAdd.put("header1", new StaticHeaderValueMessageProcessor<String>("Test Header 1"));
		headersToAdd.put("header2", new StaticHeaderValueMessageProcessor<String>("Test Header 2"));
		HeaderEnricher enricher = new HeaderEnricher(headersToAdd);
		return enricher;
	}

	@Bean
	@Transformer(inputChannel = "integration.student.toConvertObject.channel", outputChannel = "integration.student.objectToJson.channel")
	public ObjectToJsonTransformer objectToJsonTransformer() {
		return new ObjectToJsonTransformer(getMapper());
	}

	@Bean
	public Jackson2JsonObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		return new Jackson2JsonObjectMapper(mapper);
	}

	@Bean
	@Transformer(inputChannel = "integration.student.jsonToObject.channel", outputChannel = "integration.student.jsonToObject.fromTransformer.channel")
	JsonToObjectTransformer jsonToObjectTransformer() {
		return new JsonToObjectTransformer(Student.class);
	}

	// PayloadTypeRouter Example
	@ServiceActivator(inputChannel = "router.channel")
	@Bean
	public PayloadTypeRouter payloadRouter() {
		PayloadTypeRouter router = new PayloadTypeRouter();
		router.setChannelMapping(Student.class.getName(), "student.enrich.header.channel");
		router.setChannelMapping(Address.class.getName(), "address.enrich.header.channel");
		return router;
	}

	// Header Value Router
	@ServiceActivator(inputChannel = "header.payload.router.channel")
	@Bean
	public HeaderValueRouter headerRouter() {
		HeaderValueRouter router = new HeaderValueRouter("testHeader");
		router.setChannelMapping("student", "student.channel");
		router.setChannelMapping("address", "address.channel");
		return router;
	}

	// Recipient List Router
	@ServiceActivator(inputChannel = "recipient.payload.router.channel")
	@Bean
	public RecipientListRouter recipientListRouter() {
		RecipientListRouter router = new RecipientListRouter();
		router.setSendTimeout(1_234L);
		router.setIgnoreSendFailures(true);
		router.setApplySequence(true);
		router.addRecipient("student.channel");
		router.addRecipient("address.channel");
		//router.addRecipient("channel3");
		return router;
	}
}