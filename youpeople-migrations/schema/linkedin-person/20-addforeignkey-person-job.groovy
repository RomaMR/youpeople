databaseChangeLog {
    changeSet(id: '1438783344964-10', author: 'odisseus (generated)') {
        addForeignKeyConstraint(baseColumnNames: 'person_id', baseTableName: 't_job', constraintName: 'fk_job_linkedin_person', deferrable: false,
                initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 't_linkedin_person')
    }
}