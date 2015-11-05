databaseChangeLog {
    changeSet(id: 'foreignkey-person-volunteering', author: 'odisseus (generated)') {
        addForeignKeyConstraint(baseColumnNames: 'person_id', baseTableName: 't_volunteering', constraintName: 'fk_volunteering_linkedin_person',
                deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id',
                referencedTableName: 't_linkedin_person')
    }
}