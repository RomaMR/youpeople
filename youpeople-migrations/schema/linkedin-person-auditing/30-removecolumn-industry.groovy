databaseChangeLog {
    changeSet(id: 'removecolumn-industry', author: 'mudryi_roman') {
        dropColumn(tableName: 't_linkedin_person_aud', columnName: 'industry')
    }
}