databaseChangeLog {
    changeSet(id: 'addforeignkey-to-linkedin-person', author: 'mudryi_roman') {
        addColumn(tableName: 't_person'){
            column(name: 'linkedin_person_id', type: 'BIGINT')
        }
        addForeignKeyConstraint(baseColumnNames: 'linkedin_person_id', baseTableName: 't_person', constraintName: 'fk_person_linkedin_person',
                deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id',
                referencedTableName: 't_linkedin_person')
    }
}