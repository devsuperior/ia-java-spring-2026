package br.com.devsuperior.dev_xp_ai.repository;

import br.com.devsuperior.dev_xp_ai.entity.DeveloperExperienceEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperExperienceRepository extends CrudRepository<DeveloperExperienceEntity, Long> {

    Optional<DeveloperExperienceEntity> findByUserId(Long userId);

    @Query("""
            SELECT e.* FROM tb_developer_experience e
            INNER JOIN tb_developer u ON u.id = e.user_id
            WHERE (:uf IS NULL OR u.uf = :uf)
              AND (:language IS NULL OR LOWER(e.primary_language) = LOWER(:language))
            ORDER BY u.id
            """)
    List<DeveloperExperienceEntity> findAllByFilters(@Param("uf") String uf, @Param("language") String language);
}


