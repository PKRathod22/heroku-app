package com.pk.repositoy;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.model.UserMessage;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {

}
