databaseChangeLog {
    changeSet(id: '1438783344964-1', author: 'odisseus') {
        addColumn(tableName: 't_linkedin_person'){
            column(name: 'linkedin_profile_id', type: 'varchar(255)')
        }
    }
}
