databaseChangeLog {
    changeSet(id: '1438783344964-9', author: 'odisseus (generated)') {
        addForeignKeyConstraint(baseColumnNames: 'organization_id', baseTableName: 't_job', constraintName: 'fk_job_organization', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 't_organization')
    }
}