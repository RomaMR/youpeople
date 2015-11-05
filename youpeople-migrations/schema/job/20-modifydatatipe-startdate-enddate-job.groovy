databaseChangeLog {
    changeSet(id: '1438783344964-13', author: 'odisseus') {
        comment('Fixed t_job column types.')
        modifyDataType(tableName: 't_job', columnName: 'start_date', newDataType: 'TIMESTAMP WITHOUT TIME ZONE')
        modifyDataType(tableName: 't_job', columnName: 'end_date', newDataType: 'TIMESTAMP WITHOUT TIME ZONE')
    }
}