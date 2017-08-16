package br.com.hanniere.service.rules;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class RuleChainConfigurator {

	@Resource(name="distributeStonesRule")
	private KalahRule distributeStonesRule;

	@Resource(name="lastDropPlayerStoreRule")
	private KalahRule lastDropPlayerStoreRule;

	@Resource(name="lastDropPlayerHouseRule")
	private KalahRule lastDropPlayerHouseRule;

	@Resource(name="checkEndGameRule")
	private KalahRule checkEndGameRule;

	@Resource(name="changePlayerRule")
	private KalahRule changePlayerRule;

	@PostConstruct
	public void configureRules() {

		//applying the order of rules
		distributeStonesRule.setNextRule(lastDropPlayerStoreRule);
		lastDropPlayerStoreRule.setNextRule(lastDropPlayerHouseRule);
		lastDropPlayerHouseRule.setNextRule(checkEndGameRule);
		checkEndGameRule.setNextRule(changePlayerRule);
	}

	public KalahRule getaDistributeRule() {
		return distributeStonesRule;
	}

}
