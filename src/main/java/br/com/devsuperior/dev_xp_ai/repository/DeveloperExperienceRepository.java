package br.com.devsuperior.dev_xp_ai.repository;

import br.com.devsuperior.dev_xp_ai.entity.DeveloperExperienceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperExperienceRepository extends CrudRepository<DeveloperExperienceEntity, Long> {

    Optional<DeveloperExperienceEntity> findByDeveloperId(Long developerId);
}

