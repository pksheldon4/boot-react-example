package com.pksheldon4.bootreact.repository;

import com.pksheldon4.bootreact.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
