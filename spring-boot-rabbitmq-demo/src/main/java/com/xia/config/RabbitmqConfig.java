package com.xia.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright @ 2019 SHANZHEN fuli Co. Ltd.
 * All right reserved.
 *
 * @Author xiafobao
 * @Date 2019/4/18
 */
@Configuration
public class RabbitmqConfig {

    //ExchangeType包括direct，Fanout，Topic，headers
    //direct：direct类型的Exchange路由规则也很简单，它会把消息路由到那些binding key与routing key完全匹配的Queue中。
    //Binding key等于Routing key
    //===============以下是验证direct Exchange的队列==========
    @Bean
    public Queue summerQueue(){
        return new Queue("summer");
    }
    //===============以下是验证direct Exchange的队列==========


    //topic类型的Exchange在匹配规则上进行了扩展，它与direct类型的Exchage相似，
    // 也是将消息路由到binding key与routing key相匹配的Queue中，但这里的匹配规则有些不同，
    // 它约定：routing key为一个句点号“. ”分隔的字符串（我们将被句点号“. ”分隔开的每一段独立的字符串称为一个单词），
    // 如“stock.usd.nyse”、“nyse.vmw”、“quick.orange.rabbit”；binding key与routing key一样也是句点号“. ”分隔的字符串；
    // binding key中可以存在两种特殊字符“*”与“#”，用于做模糊匹配，其中“*”用于匹配一个单词，“#”用于匹配多个单词（可以是零个）。
    //===============以下是验证topic Exchange的队列==========
    @Bean
    public Queue queueMessage(){
        return new Queue("topic.message");
    }

    @Bean
    public Queue queueMessages() {
        return new Queue("topic.messages");
    }
    //===============以上是验证topic Exchange的队列==========


    //fanout：fanout类型的Exchange路由规则非常简单，它会把所有发送到该Exchange的消息路由到所有与它绑定的Queue中。
    //===============以下是验证Fanout Exchange的队列(广播)==========
    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }
    //===============以下是验证Fanout Exchange的队列==========

    //headers类型的Exchange不依赖于routing key与binding key的匹配规则来路由消息，
    // 而是根据发送的消息内容中的headers属性进行匹配。在绑定Queue与Exchange时指定一组键值对；
    // 当消息发送到Exchange时，RabbitMQ会取到该消息的headers（也是一个键值对的形式），
    // 对比其中的键值对是否完全匹配Queue与Exchange绑定时指定的键值对；如果完全匹配则消息会路由到该Queue，否则不会路由到该Queue。

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
}
