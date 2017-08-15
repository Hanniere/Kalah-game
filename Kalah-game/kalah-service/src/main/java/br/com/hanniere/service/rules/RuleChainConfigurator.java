package br.com.hanniere.service.rules;

import org.springframework.stereotype.Component;

import br.com.hanniere.service.rules.impl.DistributeStonesRule;

@Component
public class RuleChainConfigurator {

	private KalahRule kalahRule;

	public RuleChainConfigurator() {
		//initializing the chain of rules
		this.kalahRule = new DistributeStonesRule();

		//applying the order of rules
		kalahRule.setNextRule(null);
	}

	public KalahRule getKalahRule() {
		return kalahRule;
	}

}
