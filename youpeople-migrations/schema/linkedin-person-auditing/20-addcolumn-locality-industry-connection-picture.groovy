databaseChangeLog {
    changeSet(id: 'addcolumn-locality-industry-connection-picture-auditing', author: 'mudryi_roman') {
        addColumn(tableName: 't_linkedin_person_aud'){
            column(name: 'industry', type: 'varchar(255)')
            column(name: 'locality', type: 'varchar(255)')
            column(name: 'number_of_connections', type: 'int4')
            column(name: 'profile_picture', type: 'varchar(255)')
        }
    }
}