databaseChangeLog {
    changeSet(id: 'remove-notnull-constraints-linkedin-person', author: 'mudryi_roman') {
        dropNotNullConstraint(tableName: 't_linkedin_person', columnDataType: 'VARCHAR(255)', columnName: 'first_name')
        dropNotNullConstraint(tableName: 't_linkedin_person', columnDataType: 'VARCHAR(255)', columnName: 'last_name')
    }
}