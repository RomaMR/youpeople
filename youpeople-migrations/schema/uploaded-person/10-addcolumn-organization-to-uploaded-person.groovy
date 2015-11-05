databaseChangeLog {
    changeSet(id: 'addcolumn-organization-to-uploaded-person', author: 'mudryi_roman') {
        addColumn(tableName: 't_uploaded_person'){
            column(name: 'organization_id', type: 'BIGINT')
        }
        addForeignKeyConstraint(baseColumnNames: 'organization_id', baseTableName: 't_uploaded_person', constraintName:
                'fk_uploaded_person_organization', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION',
                referencedColumnNames: 'id', referencedTableName: 't_organization')
    }
}