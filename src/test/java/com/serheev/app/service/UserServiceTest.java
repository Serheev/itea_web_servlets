package com.serheev.app.service;

import com.serheev.app.entities.UserEntity;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class UserServiceTest {
    private static Logger log = Logger.getLogger(UserServiceTest.class);

    private UserService service;
    private UserEntity newRecord;
    private UserEntity user;
    private Optional<UserEntity> recordFromDB;

    @Before
    public void initDataBeforeTest() {
        service = new UserService();
        newRecord = new UserEntity();
        newRecord.setName("Sarah Connor");
        newRecord.setPassword("qwerty");
        user = service.save(newRecord);
        recordFromDB = service.get(user.getId());
    }

    @After
    public void clearDataAfterTest() {
        recordFromDB = service.get(user.getId());
        if (recordFromDB.isPresent()) {
            service.delete(service.get(user.getId()).map(UserEntity::getId).get());
        }
    }

    @Test
    public void userShouldBeSavedToDataBaseTable() {
        log.info(service.get(user.getId()).map(UserEntity::getId).get());
        log.info(recordFromDB.map(UserEntity::getName).get() + " : " + recordFromDB.map(UserEntity::getPassword).get());

        assertTrue(user.getName() == recordFromDB.map(UserEntity::getName).get());
        assertTrue(user.getPassword() == recordFromDB.map(UserEntity::getPassword).get());
    }

    @Test
    public void recordShouldBeUpdated() {
        long entryIdBeforeUpdate = recordFromDB.map(UserEntity::getId).get();
        service.update(recordFromDB.get(), new String[]{"Anna Karenina", "newpassword"});

        log.info(recordFromDB.map(UserEntity::getName).get() + " : " + recordFromDB.map(UserEntity::getPassword).get());

        long entryIdAfterUpdate = recordFromDB.map(UserEntity::getId).get();
        assertTrue(entryIdBeforeUpdate == entryIdAfterUpdate);
        assertTrue(recordFromDB.map(UserEntity::getName).get() == "Anna Karenina");
        assertTrue(recordFromDB.map(UserEntity::getPassword).get() == "newpassword");
    }

    @Test
    public void allRecordsShouldBeRead() {
        List<UserEntity> users = service.getAll();
        users.stream().map(r -> r.getId() + " : " + r.getName() + " : " + r.getPassword() + ";").forEach(log::info);

        assertTrue(users.stream().count() == 2);
    }
}