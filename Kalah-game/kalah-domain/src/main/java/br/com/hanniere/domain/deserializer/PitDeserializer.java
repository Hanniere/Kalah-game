package br.com.hanniere.domain.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import br.com.hanniere.domain.game.Pit;
import br.com.hanniere.domain.game.pit.impl.House;
import br.com.hanniere.domain.game.pit.impl.Store;

public class PitDeserializer extends StdDeserializer<Pit> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public PitDeserializer() {
		this(null);
		// TODO Auto-generated constructor stub
	}

	public PitDeserializer(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pit deserialize(JsonParser jasonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		 JsonNode jsonNode = jasonParser.getCodec().readTree(jasonParser);
		 String type = jsonNode.get("type").asText();
		 Integer stones = jsonNode.get("stones").asInt();


		 if(type.equals(Pit.TYPE_HOUSE)){
			 return new House(stones);
		 }
		 else{
			 return new Store(stones);
		 }
	}

}
