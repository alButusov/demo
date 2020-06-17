create TABLE IF NOT EXISTS contact
(
    CONTACT_ID VARCHAR(30) PRIMARY KEY
);

insert into contact
values ('100'),
       ('abcd'),
       ('empty');

create TABLE IF NOT EXISTS request
(
    ID             INT AUTO_INCREMENT PRIMARY KEY,
    APPLICATION_ID VARCHAR(30) NOT NULL,
    DT_CREATED     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRODUCT_NAME   VARCHAR(30) NOT NULL,
    CONTACT_ID     VARCHAR(30) REFERENCES contact (CONTACT_ID)
);


insert into request (ID, APPLICATION_ID, DT_CREATED, PRODUCT_NAME, CONTACT_ID)
values (1, '101', parsedatetime('19-01-2019 19:01:19.01', 'dd-MM-yyyy hh:mm:ss.SS'), 'lol', '100'),
       (2, 'abce', parsedatetime('20-01-2020 20:01:20.01', 'dd-MM-yyyy hh:mm:ss.SS'), 'kek', '100'),
       (3, '202', parsedatetime('18-01-2018 18:01:18.01', 'dd-MM-yyyy hh:mm:ss.SS'), 'lol', 'abcd'),
       (4, 'edf', parsedatetime('17-01-2017 17:01:17.01', 'dd-MM-yyyy hh:mm:ss.SS'), 'kek', 'abcd');