package br.com.hanniere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hanniere.domain.player.Player;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
