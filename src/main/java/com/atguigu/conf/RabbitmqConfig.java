package com.atguigu.conf;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;

@Configuration
@Slf4j
public class RabbitmqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    public static final String EXCHANGE_A = "my-mq-exchange_A";
    public static final String EXCHANGE_B = "my-mq-exchange_B";
    public static final String EXCHANGE_C = "my-mq-exchange_C";


    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";


    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);

        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange(){
        return new DirectExchange(EXCHANGE_A);
    }

    /**
     * 获取队列A
     */
    @Bean
    public Queue queueA(){
        return new Queue(QUEUE_A,true);
    }

    //绑定队列和交换机
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitmqConfig.ROUTINGKEY_A);
    }

    /**
     * 获取队列B
     */
    @Bean
    public Queue queueB(){
        return new Queue(QUEUE_B,true);
    }

    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(queueB()).to(defaultExchange()).with(RabbitmqConfig.ROUTINGKEY_B);
    }

    @Bean
    public Queue queueC(){
        return new Queue(QUEUE_C,true);
    }

    @Bean
    public Binding bindingC(){
        return BindingBuilder.bind(queueC()).to(defaultExchange()).with(RabbitmqConfig.ROUTINGKEY_C);
    }

    /**
     * 消息处理机制
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(){
        //加载处理消息A的队列
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        //设置接收多个队列里面的消息，这里设置接收队列A
        //加入想一个消费者处理多个队列里面的消息可以如下设置
        //container.setQueues(queueA(),queueB(),queueC());
        container.setQueues(queueA(),queueB(),queueC());
        container.setExposeListenerChannel(true);
        //设置最大的并发的消费者数量
        container.setMaxConcurrentConsumers(10);
        //设置最小的并发的消费者数量
        container.setMaxConcurrentConsumers(1);
        //设置确认模式 手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                /**
                 * 通过basic.qos方法设置prefetch_count=1,这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理一个Message,
                 * 换句话说，在接收到该Consumer的ack前，它不会将新的Mesage分发给它
                 */
                channel.basicQos(1);
                byte[] body = message.getBody();
                String[] queueNames = container.getQueueNames();
                log.info("队列名称"+Arrays.toString(queueNames));
                log.info("接收处理队列ABC当中的消息："+new String(body));
                /**
                 * 为了保证永远不丢失消息，RabbitMQ支持消息应答机制。
                 * 当消费者接收到消息并完成任务后会往RabbitMQ服务器发送一条确认的命令，然后RabbitMQ才会将消息删除。
                 */
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }
        });
        return container;
    }

}
