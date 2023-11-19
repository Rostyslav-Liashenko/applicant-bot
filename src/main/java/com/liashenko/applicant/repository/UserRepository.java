package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByChatId(String chatId);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.userSetting WHERE u.userSetting.isShowNotification")
    List<User> findByEnabledNotification(@Param("notificationValue") boolean notificationValue);
}
