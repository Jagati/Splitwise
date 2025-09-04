package com.lldproject.splitwise.repository;

import com.lldproject.splitwise.model.Group;
import com.lldproject.splitwise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    @Query("SELECT gr FROM groups gr WHERE :person = gr.admin OR :person IN gr.members")
    List<Group> findAllByMembersOrAdmin(@Param("person")User person);
}
