databaseChangeLog {
    changeSet(id: 'create-person-table', author: 'mudryi_roman') {
        createTable(tableName: 't_person') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'uploaded_person_id', type: 'BIGINT')
            column(name: 'created_by', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'created_date', type: 'TIMESTAMP WITHOUT TIME ZONE') { constraints(nullable: false) }
            column(name: 'last_modified_by', type: 'VARCHAR(255)')
            column(name: 'last_modified_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
        }
        addPrimaryKey(columnNames: 'id', constraintName: 'pk_person', tableName: 't_person')
        addForeignKeyConstraint(baseColumnNames: 'uploaded_person_id', baseTableName: 't_person', constraintName: 'fk_person_uploaded_person',
                deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id',
                referencedTableName: 't_uploaded_person')
    }
}