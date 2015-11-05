databaseChangeLog {
    changeSet(id: 'changecolumn-industry', author: 'mudryi_roman') {
        dropColumn(tableName: 't_linkedin_person', columnName: 'industry')
        addColumn(tableName: 't_linkedin_person'){
            column(name: 'industry_id', type: 'BIGINT')
        }
    }
}