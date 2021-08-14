alter table contact_person
    add constraint contact_person_company_id_fk
        foreign key (company_id) references company;

