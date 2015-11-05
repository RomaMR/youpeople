databaseChangeLog {
    changeSet(id: 'foreignkey-person-education', author: 'odisseus (generated)') {
        addForeignKeyConstraint(baseColumnNames: 'person_id', baseTableName: 't_education', constraintName: 'fk_education_linkedin_person',
                deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id',
                referencedTableName: 't_linkedin_person')
    }
}