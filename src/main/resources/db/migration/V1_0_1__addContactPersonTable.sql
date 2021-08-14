CREATE TYPE valid_statuses AS ENUM ('active', 'deleted');

CREATE TABLE contact_person
(
    id           serial
        constraint contact_person_pk
            primary key,
    first_name   varchar(255),
    last_name    varchar(255),
    email        varchar(255) not null,
    phone_number varchar(255),
    description  text,
    company_id   int          not null,
    status       valid_statuses,
    created_at   timestamp    not null,
    modified_at  timestamp
);

create unique index contact_person_email_uindex
    on contact_person (email);

