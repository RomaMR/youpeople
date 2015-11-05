databaseChangeLog {
    changeSet(id: 't_user_organization', author: 'mudryi_roman') {
        createTable(tableName: 't_user_organization') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'user_id', type: 'BIGINT')
            column(name: 'organization_id', type: 'BIGINT')
            column(name: 'position', type: 'VARCHAR(255)')
            column(name: 'created_by', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'created_date', type: 'TIMESTAMP WITHOUT TIME ZONE') { constraints(nullable: false) }
            column(name: 'last_modified_by', type: 'VARCHAR(255)')
            column(name: 'last_modified_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
        }
        addPrimaryKey(columnNames: 'id', constraintName: 'pk_user_organization', tableName: 't_user_organization')
        addForeignKeyConstraint(baseColumnNames: 'user_id', baseTableName: 't_user_organization', constraintName:
                'fk_user_to_user_organization',  deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION',
                referencedColumnNames: 'id', referencedTableName: 't_user')
        addForeignKeyConstraint(baseColumnNames: 'organization_id', baseTableName: 't_user_organization', constraintName:
                'fk_organization_to user_organization', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION'
                , referencedColumnNames: 'id', referencedTableName: 't_organization')
    }
}