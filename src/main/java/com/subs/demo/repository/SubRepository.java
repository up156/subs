package com.subs.demo.repository;

import com.subs.demo.dto.SubStatDto;
import com.subs.demo.model.Sub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubRepository extends JpaRepository<Sub, Long> {

    List<Sub> findAllByUserId(Long userId);

    @Query(
            value = """
                        SELECT s.service_name AS serviceName, COUNT(s.user_id) AS userCount
                        FROM subscriptions s
                        GROUP BY s.service_name
                        ORDER BY COUNT(s.user_id) DESC
                        LIMIT 3
                    """,
            nativeQuery = true)
    List<SubStatDto> getTopSubs();
}
