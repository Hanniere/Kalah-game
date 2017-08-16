package br.com.hanniere.repository;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hanniere.domain.game.Game;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * This class has CRUD operations of the cache that stores matches.
 * The cache is configured in resources/ehcache.xml
 *
 * @author Hanniere
 *
 */
@Component
public class GameCache {
	@Autowired
	private CacheManager cacheManager;

	private static String GAME_CACHE_NAME = "gameMatchs";

	private Cache cache;

	@PostConstruct
	private void initializeService(){
		cache = cacheManager.getCache(GAME_CACHE_NAME);
	}

	public String insertCache(Game game) {
		UUID matchId = UUID.randomUUID();
		cache.put(new Element(matchId.toString(), game));
		return matchId.toString();
	}

	public Game retrieveGame(String matchId){
		Element ele = cache.get(matchId);
		if(ele == null)
			return null;

		return (Game) ele.getObjectValue();
	}

	public void updateCache(Game game, String matchId) {
		cache.replace(new Element(matchId, game));
	}

	public void removeFromCache(String matchId){
		cache.remove(matchId);
	}

}
