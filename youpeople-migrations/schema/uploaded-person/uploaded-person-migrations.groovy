databaseChangeLog {
    changeSet(id: 'create-uploaded-person-table', author: 'mudryi_roman') {
        createTable(tableName: 't_uploaded_person') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'first_name', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'last_name', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'gender', type: 'VARCHAR(255)')
            column(name: 'work_email', type: 'VARCHAR(255)')
            column(name: 'personal_email', type: 'VARCHAR(255)')
            column(name: 'work_phone', type: 'VARCHAR(255)')
            column(name: 'personal_phone', type: 'VARCHAR(255)')
            column(name: 'position', type: 'VARCHAR(255)')
            column(name: 'start_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
            column(name: 'end_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
            column(name: 'school', type: 'VARCHAR(255)')
            column(name: 'university', type: 'VARCHAR(255)')
            column(name: 'country', type: 'VARCHAR(255)')
            column(name: 'city', type: 'VARCHAR(255)')
            column(name: 'address', type: 'VARCHAR(255)')
            column(name: 'created_by', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'created_date', type: 'TIMESTAMP WITHOUT TIME ZONE') { constraints(nullable: false) }
            column(name: 'last_modified_by', type: 'VARCHAR(255)')
            column(name: 'last_modified_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
        }
        addPrimaryKey(columnNames: 'id', constraintName: 'pk_uploaded_person', tableName: 't_uploaded_person')
    }
}