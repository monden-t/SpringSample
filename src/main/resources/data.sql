INSERT INTO employee(employee_id, employee_name, age)
VALUES(1, '山田太郎', 30);

INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('test@test.com','$2a$08$pMRANsr.lRvZUwpsHjHOIu5VbYXDg.v58RVdGavWa8tnfsfijr5li','山田太郎', '1990-01-01', 28, false, 'ROLE_ADMIN');

INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('test2@test.com','$2a$08$pMRANsr.lRvZUwpsHjHOIu5VbYXDg.v58RVdGavWa8tnfsfijr5li','ぱんぴー', '1990-01-01', 28, false, 'ROLE_GENERAL');