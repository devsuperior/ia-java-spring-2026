package br.com.devsuperior.dev_xp_ai.repository;

import br.com.devsuperior.dev_xp_ai.entity.DeveloperUserEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperUserRepository extends CrudRepository<DeveloperUserEntity, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByNicknameIgnoreCase(String nickname);

    @Query("""
            SELECT u.*
            FROM tb_developer u
            JOIN tb_developer_experience e ON u.id = e.developer_id
            WHERE (:uf IS NULL OR u.uf = :uf)
              AND (:language IS NULL OR LOWER(e.primary_language) = LOWER(:language))
            ORDER BY u.id
            """)
    List<DeveloperUserEntity> findAllByFilters(@Param("uf") String uf, @Param("language") String language);
}

