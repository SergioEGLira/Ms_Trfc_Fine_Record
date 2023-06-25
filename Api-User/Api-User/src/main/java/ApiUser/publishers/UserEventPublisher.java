package ApiUser.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ApiUser.dto.UserEventDTO;
import ApiUser.enums.ActionType;

@Component
public class UserEventPublisher {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Value(value = "${api.broker.exchange.userevent}")
	private String exchangeUserEvent;

	public void publishUserEvent(UserEventDTO userEventDto, ActionType actionType) {
		userEventDto.setActionType(actionType.toString());
		rabbitTemplate.convertAndSend(exchangeUserEvent, "", userEventDto);
	}
}
