databaseChangeLog {
    changeSet(id: '1438783344964-1', author: 'mudryi_roman') {
        addColumn(tableName: 't_linkedin_person_aud'){
            column(name: 'revtype', type: 'smallint')
        }
    }
}