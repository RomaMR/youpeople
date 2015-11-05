databaseChangeLog {
    changeSet(id: 'person-auditing', author: 'mudryi_roman') {
        createTable(tableName: 't_linkedin_person_aud') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'rev', type: 'int4') { constraints(nullable: false) }
            column(name: 'first_name', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'last_name', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'work_email', type: 'VARCHAR(255)')
            column(name: 'personal_email', type: 'VARCHAR(255)')
            column(name: 'linkedin_profile_id', type: 'varchar(255)')
            column(name: 'created_by', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'created_date', type: 'TIMESTAMP WITHOUT TIME ZONE') { constraints(nullable: false) }
            column(name: 'last_modified_by', type: 'VARCHAR(255)')
            column(name: 'last_modified_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
        }
        addPrimaryKey(columnNames: 'id,rev', constraintName: 'pk_person_aud', tableName: 't_linkedin_person_aud')
    }
}