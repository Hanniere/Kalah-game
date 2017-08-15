package br.com.hanniere.service.rules;

import org.springframework.stereotype.Component;

import br.com.hanniere.service.rules.impl.*;

@Component
public class RuleChainConfigurator {

	private KalahRule distributeStoneRule;

	public RuleChainConfigurator() {

		//initializing the chain of rules
		this.distributeStoneRule = new DistributeStonesRule();
		KalahRule lastDropPlayerStoreRule = new LastDropPlayerStoreRule();
		KalahRule lastDropPlayerHouseRule = new LastDropPlayerHouseRule();
		KalahRule checkEndGameRule = new CheckEndGameRule();
		KalahRule checkPlayerRule = new ChangePlayerRule();


		//applying the order of rules
		distributeStoneRule.setNextRule(lastDropPlayerStoreRule);
		lastDropPlayerStoreRule.setNextRule(lastDropPlayerHouseRule);
		lastDropPlayerHouseRule.setNextRule(checkEndGameRule);
		checkEndGameRule.setNextRule(checkPlayerRule);
	}

	public KalahRule getaDistributeRule() {
		return distributeStoneRule;
	}

}
