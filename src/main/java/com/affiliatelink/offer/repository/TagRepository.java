package com.affiliatelink.offer.repository;

import com.affiliatelink.offer.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = """
    INSERT INTO offer_tags (name)
    VALUES (:name)
    ON CONFLICT (name) DO NOTHING
    """, nativeQuery = true)
    void insertIfNotExists(@Param("name") String name);
}
